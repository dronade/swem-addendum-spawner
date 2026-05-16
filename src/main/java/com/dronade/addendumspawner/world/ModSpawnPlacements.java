package com.dronade.addendumspawner.world;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public final class ModSpawnPlacements {
    private ModSpawnPlacements() {}

    private static final List<ResourceLocation> ENTITY_IDS = List.of(
            ResourceLocation.fromNamespaceAndPath("swemaddendum", "american_quarter_horse"),
            ResourceLocation.fromNamespaceAndPath("swemaddendum", "arabian"),
            ResourceLocation.fromNamespaceAndPath("swemaddendum", "breton"),
            ResourceLocation.fromNamespaceAndPath("swemaddendum", "donkey"),
            ResourceLocation.fromNamespaceAndPath("swemaddendum", "fjord"),
            ResourceLocation.fromNamespaceAndPath("swemaddendum", "friesian"),
            ResourceLocation.fromNamespaceAndPath("swemaddendum", "irish_draught"),
            ResourceLocation.fromNamespaceAndPath("swemaddendum", "irish_draught_pegasus"),
            ResourceLocation.fromNamespaceAndPath("swemaddendum", "kladruper"),
            ResourceLocation.fromNamespaceAndPath("swemaddendum", "knabstrupper"),
            ResourceLocation.fromNamespaceAndPath("swemaddendum", "marwari"),
            ResourceLocation.fromNamespaceAndPath("swemaddendum", "mule"),
            ResourceLocation.fromNamespaceAndPath("swemaddendum", "mustang"),
            ResourceLocation.fromNamespaceAndPath("swemaddendum", "pegasus"),
            ResourceLocation.fromNamespaceAndPath("swemaddendum", "shire"),
            ResourceLocation.fromNamespaceAndPath("swemaddendum", "thoroughbred"),
            ResourceLocation.fromNamespaceAndPath("swemaddendum", "turkoman"),
            ResourceLocation.fromNamespaceAndPath("swemaddendum", "warmblood")
    );

    public static void onSpawnPlacementRegister(SpawnPlacementRegisterEvent event) {
        for (ResourceLocation id : ENTITY_IDS) {
            EntityType<?> rawType = ForgeRegistries.ENTITY_TYPES.getValue(id);
            if (rawType == null) continue;

            @SuppressWarnings("unchecked")
            EntityType<? extends Animal> animalType = (EntityType<? extends Animal>) rawType;

            event.register(
                    animalType,
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    ModSpawnPlacements::canSpawnOnDrySurface,
                    SpawnPlacementRegisterEvent.Operation.REPLACE
            );
        }
    }

    private static boolean canSpawnOnDrySurface(EntityType<? extends Animal> entityType,
                                                ServerLevelAccessor level,
                                                MobSpawnType spawnType,
                                                BlockPos pos,
                                                RandomSource random) {
        if (!level.canSeeSky(pos)) return false;
        if (!level.getFluidState(pos).isEmpty()) return false;
        if (!level.getFluidState(pos.below()).isEmpty()) return false;

        return Animal.checkAnimalSpawnRules(entityType, level, spawnType, pos, random);
    }
}

