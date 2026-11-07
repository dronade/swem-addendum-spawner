package com.dronade.addendumspawner.world;

import com.dronade.addendumspawner.AddendumSpawner;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;


public final class ModSpawnPlacements {
    private ModSpawnPlacements() {}


    public static void onSpawnPlacementRegister(RegisterSpawnPlacementsEvent event) {
        for (ResourceLocation id : AddendumSpawner.SPAWNED_ENTITY_IDS) {
            EntityType<?> rawType = BuiltInRegistries.ENTITY_TYPE.get(id);
            if (rawType == null) continue;

            @SuppressWarnings("unchecked")
            EntityType<? extends Animal> animalType = (EntityType<? extends Animal>) rawType;

            event.register(
                    animalType,
                    SpawnPlacementTypes.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    ModSpawnPlacements::canSpawnOnDrySurface,
                    RegisterSpawnPlacementsEvent.Operation.REPLACE
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
