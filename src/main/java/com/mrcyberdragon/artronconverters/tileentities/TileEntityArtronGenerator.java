package com.mrcyberdragon.artronconverters.tileentities;

import com.mrcyberdragon.artronconverters.init.ModEnergy;
import com.mrcyberdragon.artronconverters.init.SoundInit;
import com.mrcyberdragon.artronconverters.init.TileEntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.tardis.mod.helper.TardisHelper;
import net.tardis.mod.particles.TParticleTypes;
import net.tardis.mod.tileentities.ConsoleTile;

import java.util.Random;

public class TileEntityArtronGenerator extends TileEntity implements ITickableTileEntity {

    private ModEnergy energy = new ModEnergy(100000, 4096, 100000);
    private LazyOptional<EnergyStorage> energyHolder = LazyOptional.of(() -> energy);
    private int tick = 7;
    private boolean spawnParticle=false;

    private ConsoleTile tile;

    public TileEntityArtronGenerator(){
        super(TileEntityInit.ARTRON_GENERATOR.get());
    }

    @Override
    public void tick(){
        this.getCapability(CapabilityEnergy.ENERGY).ifPresent(cap -> {
            if (tile == null) return;

        if(tile.getArtron() < tile.getMaxArtron()) {
            int ConversionAmount = 25000;
            if (!world.isBlockPowered(this.getPos())) {
                if (0 < tick && tick <= 7) {
                    tick--;
                }
                if (energy.getEnergyStored() >= ConversionAmount) {
                    if (tick == 0) {
                        tile.setArtron(tile.getArtron()+1);
                        spawnParticle=true;
                        world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 2);
                        energy.extractEnergy(ConversionAmount, false);
                        if (!world.isRemote()) {
                            world.playSound(null, this.getPos(), SoundInit.ARTRON_GEN, SoundCategory.BLOCKS, 1F, 1F);
                        }
                        tick = 7;
                    }
                    else {
                        world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 2);
                        spawnParticle = false;
                    }
                }
                else {
                    world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 2);
                    spawnParticle = false;
                }
            }
            else {
                world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 2);
                spawnParticle = false;
            }
        }
        else {
            world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 2);
            spawnParticle = false;
        }
        });
        if(tile == null || tile.isRemoved())
            TardisHelper.getConsoleInWorld(world).ifPresent(console -> this.tile = console);

    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putBoolean("particle",spawnParticle);
        compound.put("energy", energy.serializeNBT());
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        energy.deserializeNBT(nbt.getCompound("energy"));
        this.spawnParticle= nbt.getBoolean("particle");
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket(){
        CompoundNBT nbtTag = new CompoundNBT();
        this.write(nbtTag);
        return new SUpdateTileEntityPacket(this.getPos(), -1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt){
        this.read(this.getBlockState(), pkt.getNbtCompound());
        Random random = new Random();
        if(spawnParticle) {
            for (int i = 5; i > 0; i--) {
                world.addParticle(TParticleTypes.ARTRON.get(), this.getPos().getX() + random.nextFloat(), this.getPos().getY() + random.nextFloat(), this.getPos().getZ() + random.nextFloat(), 0, 0, 0);
            }
        }
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == CapabilityEnergy.ENERGY ? this.energyHolder.cast() : super.getCapability(cap, side);
    }

}
