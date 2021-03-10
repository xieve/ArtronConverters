package com.mrcyberdragon.artronconverters.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;

import java.util.List;

public class UnattunedConverter extends Block {

    public UnattunedConverter(Properties prop){
        super(prop);
    }

    @Override
    public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.artron_generator.attunement"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
