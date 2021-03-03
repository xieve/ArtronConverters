package com.mrcyberdragon.artronconverters;

import com.mrcyberdragon.artronconverters.init.BlockInit;
import com.mrcyberdragon.artronconverters.init.SoundRegistry;
import com.mrcyberdragon.artronconverters.init.TileEntityInit;
import com.mrcyberdragon.artronconverters.init.ItemInit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("artrongen")
public class ArtronConverters
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "artrongen";

    public ArtronConverters() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);

        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        TileEntityInit.TILES.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new SoundRegistry());
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }

}
