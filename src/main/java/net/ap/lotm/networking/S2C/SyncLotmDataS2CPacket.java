package net.ap.lotm.networking.S2C;

import net.ap.lotm.Lotm;
import net.ap.lotm.data.ILotmData;
import net.ap.lotm.data.LotmCapabilityHandler;
import net.ap.lotm.register.CapabilityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SyncLotmDataS2CPacket(CompoundTag nbt) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<SyncLotmDataS2CPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Lotm.MOD_ID, "sync_lotm_data"));
    public static final StreamCodec<? super RegistryFriendlyByteBuf, SyncLotmDataS2CPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.COMPOUND_TAG,
            SyncLotmDataS2CPacket::nbt,
            SyncLotmDataS2CPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return type();
    }

    public void handle(IPayloadContext ctx) {
        ctx.enqueueWork(() -> {

            Player player = Minecraft.getInstance().player;

            if(player == null) return;

            ILotmData cap = player.getCapability(CapabilityRegistry.LOTM_CAPABILITY);

            if(cap == null) return;

            cap.deserializeNBT(player.registryAccess(), nbt);

        });
    }

}
