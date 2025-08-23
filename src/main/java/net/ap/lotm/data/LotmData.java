package net.ap.lotm.data;

import net.ap.lotm.networking.S2C.SyncLotmDataS2CPacket;
import net.ap.lotm.register.AttachmentRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.UnknownNullability;

public class LotmData implements ILotmData {

    private final LivingEntity entity;
    private boolean initialized;

    public LotmData(LivingEntity entity) {
        this.entity = entity;
    }

    @Override
    public void tick() {

        if(!this.initialized) {
            this.initialized = true;
            gen();
        }

    }


    @Override
    public void gen() {
        this.initialized = true;

        if(this.entity instanceof ServerPlayer serverPlayer) {
            PacketDistributor.sendToPlayer(serverPlayer, new SyncLotmDataS2CPacket(this.serializeNBT(serverPlayer.registryAccess())));
        }

    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();

        tag.putBoolean("init", this.initialized);

        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {

        this.initialized = tag.getBoolean("init");

    }



}
