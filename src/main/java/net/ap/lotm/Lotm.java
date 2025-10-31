package net.ap.lotm;

import net.ap.lotm.client.LotmClientAbilityHandler;
import net.ap.lotm.register.AttachmentRegistry;
import net.ap.lotm.register.ItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import java.awt.*;

@Mod(Lotm.MOD_ID)
public class Lotm {

    public static final String MOD_ID = "lotm";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Lotm(IEventBus modEventBus, ModContainer modContainer) {

        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, LotmConfig.SPEC);


        // Registry
        AttachmentRegistry.register(modEventBus);
        ItemRegistry.register(modEventBus);


        LotmClientAbilityHandler.addCombo("ZXCV", player -> {
            player.sendSystemMessage(Component.literal("ZXCV COMBO ACTIVATED!").withStyle(ChatFormatting.RED));
        });

    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
