package net.ap.lotm.client;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class LotmClientAbilityHandler {


    private static List<Character> keyBuffer = new ArrayList<>();
    private static HashMap<String, Consumer<Player>> abilities = new HashMap<>();

    public static void addCombo(String combo, Consumer<Player> action) {
        abilities.put(combo, action);
    }

    public static void addBufferKey(Character key) {
        Minecraft.getInstance().player.playNotifySound(SoundEvents.UI_BUTTON_CLICK.value(), SoundSource.NEUTRAL, 1f, 1f);
        keyBuffer.add(key);

        if(keyBuffer.size() > 4) keyBuffer.removeFirst();
        checkCombo();
    }

    public static void clear() {
        keyBuffer.clear();
    }

    private static void checkCombo() {
        Minecraft client = Minecraft.getInstance();
        Player player = client.player;

        StringBuilder comboIndicator = new StringBuilder();
        for(int i = 0; i < 4; i++) {
            if(i < keyBuffer.size() && keyBuffer.get(i) != null) {
                comboIndicator.append(keyBuffer.get(i).toString());
                if(i < 3) comboIndicator.append("-");
            } else {
                comboIndicator.append("*");
                if(i < 3) comboIndicator.append("-");
            }
        }
        client.gui.setTimes(0, 20, 10);
        client.gui.setTitle(Component.literal(comboIndicator.toString()).withStyle(ChatFormatting.GREEN));
        client.gui.resetTitleTimes();

        if(!(keyBuffer.size() > 3)) return;
        System.out.println(keyBuffer.get(0).toString() + keyBuffer.get(1).toString() + keyBuffer.get(2).toString() + keyBuffer.get(3).toString());
        String combo = keyBuffer.get(0).toString() + keyBuffer.get(1).toString() + keyBuffer.get(2).toString() + keyBuffer.get(3).toString();


        if(abilities.containsKey(combo)) {
            abilities.get(combo).accept(player);
        }
        keyBuffer.clear();
    }




}
