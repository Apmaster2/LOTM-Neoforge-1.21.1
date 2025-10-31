package net.ap.lotm.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.ap.lotm.Lotm;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;


@EventBusSubscriber(modid = Lotm.MOD_ID, value = Dist.CLIENT)
public class KeyBindings {



    public static final KeyMapping ABILITY_SYMBOL_1 = new KeyMapping(
            "key.lotm.ability_symbol_1",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_Z,
            "key.category.lotm"
    );

    public static final KeyMapping ABILITY_SYMBOL_2 = new KeyMapping(
            "key.lotm.ability_symbol_2",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_X,
            "key.category.lotm"
    );

    public static final KeyMapping ABILITY_SYMBOL_3 = new KeyMapping(
            "key.lotm.ability_symbol_3",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            "key.category.lotm"
    );

    public static final KeyMapping ABILITY_SYMBOL_4 = new KeyMapping(
            "key.lotm.ability_symbol_4",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            "key.category.lotm"
    );

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(ABILITY_SYMBOL_1);
        event.register(ABILITY_SYMBOL_2);
        event.register(ABILITY_SYMBOL_3);
        event.register(ABILITY_SYMBOL_4);
    }

    @SubscribeEvent
    public static void onKeyUse(InputEvent.Key event) {
        Player player = Minecraft.getInstance().player;


        if(player == null) return;


        if(ABILITY_SYMBOL_1.consumeClick()) {
            LotmClientAbilityHandler.addBufferKey('Z');
        }
        if(ABILITY_SYMBOL_2.consumeClick()) {
            LotmClientAbilityHandler.addBufferKey('X');
        }
        if(ABILITY_SYMBOL_3.consumeClick()) {
            LotmClientAbilityHandler.addBufferKey('C');
        }
        if(ABILITY_SYMBOL_4.consumeClick()) {
            LotmClientAbilityHandler.addBufferKey('V');
        }

    }

}
