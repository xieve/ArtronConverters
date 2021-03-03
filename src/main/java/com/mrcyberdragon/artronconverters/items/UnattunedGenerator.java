package com.mrcyberdragon.artronconverters.items;

import com.mrcyberdragon.artronconverters.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.tardis.mod.constants.Constants;
import net.tardis.mod.items.misc.IAttunable;
import net.tardis.mod.tileentities.ConsoleTile;


public class UnattunedGenerator extends BlockItem implements IAttunable {

    public UnattunedGenerator(Block block, Properties prop) {super(block, prop);}

    @Override
    public ItemStack onAttuned(ItemStack itemStack, ConsoleTile consoleTile) {
        return new ItemStack(BlockInit.ARTRON_GENERATOR.get());
    }

    @Override
    public int getAttunementTime() {
        return Constants.ATTUNEMENT_TIME_LESSER;
    }
}
