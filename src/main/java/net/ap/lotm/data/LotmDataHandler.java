package net.ap.lotm.data;

import net.ap.lotm.Lotm;
import net.ap.lotm.networking.S2C.SyncLotmDataS2CPacket;
import net.ap.lotm.register.AttachmentRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = Lotm.MOD_ID)
public class LotmDataHandler {


    @SubscribeEvent
    public static void onPlayerPreTickEvent(PlayerTickEvent.Pre event) {

        Player player = event.getEntity();

        if(player.isDeadOrDying()) return;


        ILotmData data = player.getData(AttachmentRegistry.LOTM_DATA_ATTACHMENT);


        data.tick();

    }



    @SubscribeEvent
    public static void onPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {

        if(!(event.getEntity() instanceof ServerPlayer player)) return;


        ILotmData data = player.getData(AttachmentRegistry.LOTM_DATA_ATTACHMENT);


        PacketDistributor.sendToPlayer(player, new SyncLotmDataS2CPacket(data.serializeNBT(player.registryAccess())));
    }


    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event) {

        Player old = event.getEntity();

        ILotmData data = old.getData(AttachmentRegistry.LOTM_DATA_ATTACHMENT);


        if(event.isWasDeath()) {

        }

    }


    @SubscribeEvent
    public static void onPlayerChangeDimensionEvent(PlayerEvent.PlayerChangedDimensionEvent event) {

        if(!(event.getEntity() instanceof ServerPlayer player)) return;

        ILotmData data = player.getData(AttachmentRegistry.LOTM_DATA_ATTACHMENT);


        PacketDistributor.sendToPlayer(player, new SyncLotmDataS2CPacket(data.serializeNBT(player.registryAccess())));
    }


    @SubscribeEvent
    public static void onPlayerRespawnEvent(PlayerEvent.PlayerRespawnEvent event) {

        if(!(event.getEntity() instanceof ServerPlayer player)) return;

        ILotmData data = player.getData(AttachmentRegistry.LOTM_DATA_ATTACHMENT);

        PacketDistributor.sendToPlayer(player, new SyncLotmDataS2CPacket(data.serializeNBT(player.registryAccess())));
    }


}
