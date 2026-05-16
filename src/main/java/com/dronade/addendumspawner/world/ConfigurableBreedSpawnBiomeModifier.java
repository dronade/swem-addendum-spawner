package com.dronade.addendumspawner.world;

import com.dronade.addendumspawner.ModConfig;
import com.dronade.addendumspawner.registry.ModBiomeModifiers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Optional;
import java.util.Set;

public record ConfigurableBreedSpawnBiomeModifier(
        String breedKey,
        ResourceLocation entityType
) implements BiomeModifier {

    public static final Codec<ConfigurableBreedSpawnBiomeModifier> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ExtraCodecs.NON_EMPTY_STRING.fieldOf("breed_key").forGetter(ConfigurableBreedSpawnBiomeModifier::breedKey),
                    ResourceLocation.CODEC.fieldOf("entity_type").forGetter(ConfigurableBreedSpawnBiomeModifier::entityType)
            ).apply(instance, ConfigurableBreedSpawnBiomeModifier::new)
    );

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase != Phase.ADD) return;

        ModConfig.BreedConfig cfg = ModConfig.getBreed(breedKey);
        if (cfg == null || !cfg.isEnabled()) return;

        if (!isBiomeAllowed(biome, cfg)) return;

        EntityType<?> rawType = ForgeRegistries.ENTITY_TYPES.getValue(entityType);
        if (rawType == null) return;

        @SuppressWarnings("unchecked")
        EntityType<? extends Mob> mobType = (EntityType<? extends Mob>) rawType;

        MobSpawnSettings.SpawnerData spawnData = new MobSpawnSettings.SpawnerData(
                mobType,
                cfg.getWeight(),
                cfg.getMinCount(),
                cfg.getMaxCount()
        );

        builder.getMobSpawnSettings().addSpawn(mobType.getCategory(), spawnData);
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
