package com.github.tutorialmod.item;

import com.github.tutorialmod.TutorialMod;
import com.github.tutorialmod.item.custom.ChiselItem;
import com.github.tutorialmod.item.custom.FuelItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TutorialMod.MOD_ID);

    public static final DeferredItem<Item> BISMUTH = ITEMS.registerSimpleItem("bismuth");
    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.registerSimpleItem("raw_bismuth");

    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel", () -> new ChiselItem(
        new Item.Properties().durability(32)));

    public static final DeferredItem<Item> RADISH = ITEMS.register("radish", () -> new Item(
        new Item.Properties().food(ModFoodProperties.RADISH)));

    public static final DeferredItem<Item> FROSTFIRE_ICE = ITEMS.register("frostfire_ice", () -> new FuelItem(
        new Item.Properties(), 800
    ));

    public static final DeferredItem<Item> STARLIGHT_ASHES = ITEMS.registerSimpleItem("starlight_ashes");

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
