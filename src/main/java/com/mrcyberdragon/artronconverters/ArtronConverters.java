package com.mrcyberdragon.artronconverters;

import com.mrcyberdragon.artronconverters.init.*;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
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
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
        LOGGER.debug("Registered AutoConfig");

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);

        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        TileEntityInit.TILES.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new SoundRegistry());

        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (client, parent) -> AutoConfig.getConfigScreen(ModConfig.class, parent).get());
        LOGGER.debug("Registered Config Screen");
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }

}
