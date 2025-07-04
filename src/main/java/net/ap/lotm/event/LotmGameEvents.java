package net.ap.lotm.event;

import net.ap.lotm.Lotm;
import net.ap.lotm.data.ILotmData;
import net.ap.lotm.register.CapabilityRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Lotm.MOD_ID)
public class LotmGameEvents {

    @SubscribeEvent
    public static void onPlayerTickEvent(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        ILotmData cap =  player.getCapability(CapabilityRegistry.LOTM_CAPABILITY);

        if(cap == null) return;

        player.sendSystemMessage(Component.literal("Current Age: " + cap.getAge()));

        if(player.isShiftKeyDown()) {
            cap.setAge(cap.getAge() + 1);
            player.setShiftKeyDown(false);
        }
    }


}
