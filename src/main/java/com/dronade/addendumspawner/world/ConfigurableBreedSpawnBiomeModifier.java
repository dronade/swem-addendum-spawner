package com.dronade.addendumspawner.world;

import com.dronade.addendumspawner.ModConfig;
import com.dronade.addendumspawner.registry.ModBiomeModifiers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

import java.util.Optional;
import java.util.Set;

public record ConfigurableBreedSpawnBiomeModifier(
        String breedKey,
        MobSpawnSettings.SpawnerData spawnerData
) implements BiomeModifier {

    public static final Codec<ConfigurableBreedSpawnBiomeModifier> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ExtraCodecs.NON_EMPTY_STRING.fieldOf("breed_key").forGetter(ConfigurableBreedSpawnBiomeModifier::breedKey),
                    MobSpawnSettings.SpawnerData.CODEC.fieldOf("spawner").forGetter(ConfigurableBreedSpawnBiomeModifier::spawnerData)
            ).apply(instance, ConfigurableBreedSpawnBiomeModifier::new)
    );

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase != Phase.ADD) return;

        ModConfig.BreedConfig cfg = ModConfig.getBreed(breedKey);
        if (cfg == null || !cfg.isEnabled()) return;

        if (!isBiomeAllowed(biome, cfg)) return;

        builder.getMobSpawnSettings().addSpawn(spawnerData.type.getCategory(), spawnerData);
    }

    private boolean isBiomeAllowed(Holder<Biome> biome, ModConfig.BreedConfig cfg) {
        Optional<ResourceKey<Biome>> keyOpt = biome.unwrapKey();
        if (keyOpt.isEmpty()) return false;

        ResourceLocation biomeId = keyOpt.get().location();
        Set<ResourceLocation> allowedBiomes = cfg.getBiomeWhitelist();
        return allowedBiomes.contains(biomeId);
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return ModBiomeModifiers.CONFIGURABLE_BREED_SPAWN.get();
    }
}