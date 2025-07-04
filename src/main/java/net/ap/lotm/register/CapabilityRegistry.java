package net.ap.lotm.register;

import net.ap.lotm.Lotm;
import net.ap.lotm.data.ILotmData;
import net.ap.lotm.data.LotmData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = Lotm.MOD_ID)
public class CapabilityRegistry {

    public static final EntityCapability<ILotmData, Void> LOTM_CAPABILITY = EntityCapability.create(
            ResourceLocation.fromNamespaceAndPath(Lotm.MOD_ID, "lotm_capability"),
            ILotmData.class,
            Void.class
    );


    @SubscribeEvent
    public static void onCapabilityRegisterEvent(RegisterCapabilitiesEvent event) {
        event.registerEntity(
            LOTM_CAPABILITY, EntityType.PLAYER, (entity, ctx) -> new LotmData(entity)
        );
    }

}
