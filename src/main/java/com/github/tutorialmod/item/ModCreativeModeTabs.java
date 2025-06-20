package com.github.tutorialmod.item;

import com.github.tutorialmod.TutorialMod;
import com.github.tutorialmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {

    public static final String BISMUTH_ITEMS_TAB_ID = "bismuth_items_tab";

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TutorialMod.MOD_ID);

    static {
        CREATIVE_MODE_TABS.register(BISMUTH_ITEMS_TAB_ID, () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.BISMUTH_BLOCK))
            .title(Component.translatable("creativetab.tutorialmod.bismuth_items"))
            .displayItems((itemDisplayParameters, output) -> {
                ModItems.ITEMS.getEntries().forEach((item) -> output.accept(item.get()));
                ModBlocks.BLOCKS.getEntries().forEach((block) -> output.accept(block.get()));
            })
            .build()
        );
    }

    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }
}
