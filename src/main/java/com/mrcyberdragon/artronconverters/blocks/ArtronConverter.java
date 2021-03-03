package com.mrcyberdragon.artronconverters.blocks;

import com.mrcyberdragon.artronconverters.tileentities.TileEntityArtronConverter;
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
import net.tardis.mod.constants.Constants;

import java.util.List;

public class ArtronConverter extends Block {

    public ArtronConverter(Properties prop){
        super(prop);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        TileEntityType<?> type = new TileEntityArtronConverter().getType();
        return type.create();
    }

    @Override
    public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(Constants.Translations.TOOLTIP_HOLD_SHIFT);
        tooltip.add(Constants.Translations.TOOLTIP_REDSTONE_REQUIRED);
        if (Screen.hasShiftDown()) {
            tooltip.clear();
            tooltip.add(0, stack.getDisplayName());
            tooltip.add(new TranslationTextComponent("tooltip.artron_converter.purpose"));
        }
    }
}
