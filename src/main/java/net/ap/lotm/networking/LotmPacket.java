package net.ap.lotm.networking;

import net.ap.lotm.Lotm;
import net.ap.lotm.networking.S2C.SyncLotmDataS2CPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Lotm.MOD_ID)
public class LotmPacket {

    @SubscribeEvent
    public static void onRegister(RegisterPayloadHandlersEvent event) {

        PayloadRegistrar registrar = event.registrar(Lotm.MOD_ID)
                .versioned("1.0.0");



        registrar.playToClient(SyncLotmDataS2CPacket.TYPE, SyncLotmDataS2CPacket.STREAM_CODEC, SyncLotmDataS2CPacket::handle);

    }

}
