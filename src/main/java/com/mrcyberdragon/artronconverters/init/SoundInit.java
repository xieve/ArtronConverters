package com.mrcyberdragon.artronconverters.init;

import com.mrcyberdragon.artronconverters.ArtronConverters;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class SoundInit {

    public static final SoundEvent ARTRON_GEN;

    static {
        ARTRON_GEN = addSoundsToRegistry("generator");
    }

    private static SoundEvent addSoundsToRegistry(String soundId) {
        ResourceLocation shotSoundLocation = new ResourceLocation(ArtronConverters.MODID, soundId);
        SoundEvent soundEvent = new SoundEvent(shotSoundLocation);
        soundEvent.setRegistryName(shotSoundLocation);
        return soundEvent;
    }
}
