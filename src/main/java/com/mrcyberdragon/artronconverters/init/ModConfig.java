package com.mrcyberdragon.artronconverters.init;

import com.mrcyberdragon.artronconverters.ArtronConverters;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = ArtronConverters.MODID + "-server")
public class ModConfig implements ConfigData {
    @ConfigEntry.Gui.CollapsibleObject
    @Comment("Converts given amount of FE to 1 AU every 7 ticks")
    public ArtronInterface artronGenerator = new ArtronInterface(25000, 100000, 4096);
    @ConfigEntry.Gui.CollapsibleObject
    @Comment("Converts 1 AU to given amount of FE every 7 ticks")
    public ArtronInterface artronConverter = new ArtronInterface(1000, 100000, 1000);

    public static class ArtronInterface {
        public int conversionRate;
        public int buffer;
        public int maxThroughputFE;

        public ArtronInterface(int conversionRate, int buffer, int maxThroughputFE) {
            this.conversionRate = conversionRate;
            this.buffer = buffer;
            this.maxThroughputFE = maxThroughputFE;
        }
    }
}
