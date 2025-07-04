package net.ap.lotm.data;

import net.ap.lotm.Lotm;
import net.ap.lotm.networking.S2C.SyncLotmDataS2CPacket;
import net.ap.lotm.register.CapabilityRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = Lotm.MOD_ID)
public class LotmCapabilityHandler {


    @SubscribeEvent
    public static void onPlayerPreTickEvent(PlayerTickEvent.Pre event) {

        Player player = event.getEntity();

        if(player.isDeadOrDying()) return;


        ILotmData cap = player.getCapability(CapabilityRegistry.LOTM_CAPABILITY);

        if(cap == null) return;


        cap.tick();

    }



    @SubscribeEvent
    public static void onPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {

        if(!(event.getEntity() instanceof ServerPlayer player)) return;


        ILotmData cap = player.getCapability(CapabilityRegistry.LOTM_CAPABILITY);

        if(cap == null) return;

        //PacketDistributor.sendToPlayer(player, new SyncLotmDataS2CPacket(cap.serializeNBT(player.registryAccess())));
    }


    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event) {

        Player old = event.getEntity();

        ILotmData cap = old.getCapability(CapabilityRegistry.LOTM_CAPABILITY);

        if(cap == null) return;

        if(event.isWasDeath()) {

        }

    }


    @SubscribeEvent
    public static void onPlayerChangeDimensionEvent(PlayerEvent.PlayerChangedDimensionEvent event) {

        if(!(event.getEntity() instanceof ServerPlayer player)) return;

        ILotmData cap = player.getCapability(CapabilityRegistry.LOTM_CAPABILITY);

        if(cap == null) return;

        PacketDistributor.sendToPlayer(player, new SyncLotmDataS2CPacket(cap.serializeNBT(player.registryAccess())));
    }


    @SubscribeEvent
    public static void onPlayerRespawnEvent(PlayerEvent.PlayerRespawnEvent event) {

        if(!(event.getEntity() instanceof ServerPlayer player)) return;

        ILotmData cap = player.getCapability(CapabilityRegistry.LOTM_CAPABILITY);

        if(cap == null) return;

        PacketDistributor.sendToPlayer(player, new SyncLotmDataS2CPacket(cap.serializeNBT(player.registryAccess())));
    }


}
