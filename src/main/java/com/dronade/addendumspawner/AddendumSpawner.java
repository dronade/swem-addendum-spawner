package com.dronade.addendumspawner;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;

@Mod("addendumspawner")
public class AddendumSpawner {
    public AddendumSpawner() {
        ModLoadingContext.get().registerConfig(Type.COMMON, ModConfig.SPEC);
    }
}
