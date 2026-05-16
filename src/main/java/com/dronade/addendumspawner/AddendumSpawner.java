package com.dronade.addendumspawner;

import com.dronade.addendumspawner.registry.ModBiomeModifiers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AddendumSpawner.MODID)
public class AddendumSpawner {
    public static final String MODID = "addendumspawner";

    public AddendumSpawner(FMLJavaModLoadingContext context) {
        context.registerConfig(Type.COMMON, ModConfig.SPEC);
        ModBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(context.getModEventBus());
    }
}
