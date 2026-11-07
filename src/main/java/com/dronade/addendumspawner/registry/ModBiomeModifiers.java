package com.dronade.addendumspawner.registry;

import com.dronade.addendumspawner.AddendumSpawner;
import com.dronade.addendumspawner.world.ConfigurableBreedSpawnBiomeModifier;
import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {
    public static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, AddendumSpawner.MODID);

    public static final DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<ConfigurableBreedSpawnBiomeModifier>> CONFIGURABLE_BREED_SPAWN =
            BIOME_MODIFIER_SERIALIZERS.register("configurable_breed_spawn",
                    () -> ConfigurableBreedSpawnBiomeModifier.CODEC);
}
