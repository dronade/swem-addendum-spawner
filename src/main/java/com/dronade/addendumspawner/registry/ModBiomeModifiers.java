package com.dronade.addendumspawner.registry;

import com.dronade.addendumspawner.AddendumSpawner;
import com.dronade.addendumspawner.world.ConfigurableBreedSpawnBiomeModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomeModifiers {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, AddendumSpawner.MODID);

    public static final RegistryObject<Codec<ConfigurableBreedSpawnBiomeModifier>> CONFIGURABLE_BREED_SPAWN =
            BIOME_MODIFIER_SERIALIZERS.register("configurable_breed_spawn",
                    () -> ConfigurableBreedSpawnBiomeModifier.CODEC);
}