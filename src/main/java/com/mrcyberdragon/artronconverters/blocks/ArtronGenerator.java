package com.mrcyberdragon.artronconverters.blocks;

import com.mrcyberdragon.artronconverters.init.TileEntityInit;
import com.mrcyberdragon.artronconverters.tileentities.TileEntityArtronGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.tardis.mod.constants.TardisConstants;

import java.util.List;

public class ArtronGenerator extends Block {

    public ArtronGenerator(Properties prop){
        super(prop);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityInit.ARTRON_GENERATOR.get().create();
    }

    @Override
    public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(TardisConstants.Translations.TOOLTIP_HOLD_SHIFT);
        if (Screen.hasShiftDown()) {
            tooltip.clear();
            tooltip.add(0, stack.getDisplayName());
            tooltip.add(new TranslationTextComponent("tooltip.artron_generator.purpose"));
            tooltip.add(new TranslationTextComponent("tooltip.artron_generator.redstone"));
        }

    }



}
