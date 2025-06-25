package com.github.tutorialmod.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ChiselItem extends Item {

    private static final Map<Block, Block> blocksToTransform = Map.of(
        Blocks.STONE, Blocks.STONE_BRICKS,
        Blocks.END_STONE, Blocks.END_STONE_BRICKS,
        Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS,
        Blocks.TUFF, Blocks.TUFF_BRICKS
    );

    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        final var level = context.getLevel();
        final var clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
        final var player = context.getPlayer();

        if (blocksToTransform.containsKey(clickedBlock) && !level.isClientSide() && player != null) {
            level.setBlockAndUpdate(context.getClickedPos(), blocksToTransform.get(clickedBlock).defaultBlockState());

            context.getItemInHand().hurtAndBreak(
                1,
                ((ServerLevel) level),
                context.getPlayer(),
                item -> player.onEquippedItemBroken(item, EquipmentSlot.MAINHAND)
            );

            level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
        }

        return InteractionResult.SUCCESS;
    }
}
