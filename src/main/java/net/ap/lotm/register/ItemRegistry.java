package net.ap.lotm.register;

import net.ap.lotm.Lotm;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ItemRegistry {


    private static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(Lotm.MOD_ID);


    public static final Supplier<Item> POTION = ITEMS.register(
            "potion",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.EPIC)
            )
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
