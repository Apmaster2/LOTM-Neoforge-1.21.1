package net.ap.lotm.register;

import net.ap.lotm.Lotm;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
                    .food(LotmFoodProperties.potion)
            )
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }



    public static class LotmFoodProperties {

        public static FoodProperties potion = new FoodProperties.Builder()
                .nutrition(0)
                .saturationModifier(0)
                .alwaysEdible()
                .usingConvertsTo(Items.GLASS_BOTTLE.asItem())
                .effect(() -> new MobEffectInstance(MobEffects.POISON, 200, 3), 100)
                .build();

    }

}
