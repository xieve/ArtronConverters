package com.mrcyberdragon.artronconverters.init;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class ModEnergy extends EnergyStorage implements INBTSerializable<CompoundNBT> {

    public ModEnergy(int capacity, int inputRate, int outputRate){
        super(capacity,inputRate,outputRate);
    }

    public void setEnergy(int energy)
    {
        this.energy = energy;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("energy", getEnergyStored());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        setEnergy(nbt.getInt("energy"));
    }
}
