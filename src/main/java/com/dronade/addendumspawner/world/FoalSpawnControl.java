package com.dronade.addendumspawner.world;

import com.dronade.addendumspawner.AddendumSpawner;
import com.dronade.addendumspawner.ModConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.MobSpawnType;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;

public final class FoalSpawnControl {
    private static final String FORCE_ADULT_TAG = "addendumspawner.force_adult";

    private FoalSpawnControl() {}

    public static void onFinalizeSpawn(FinalizeSpawnEvent event) {
        if (ModConfig.spawnFoals()) return;

        MobSpawnType spawnType = event.getSpawnType();
        if (spawnType != MobSpawnType.NATURAL && spawnType != MobSpawnType.CHUNK_GENERATION) return;

        ResourceLocation entityId = BuiltInRegistries.ENTITY_TYPE.getKey(event.getEntity().getType());
        if (entityId == null || !AddendumSpawner.SPAWNED_ENTITY_IDS.contains(entityId)) return;

        if (!(event.getEntity() instanceof AgeableMob)) return;

        // Mark managed natural spawns; actual age correction is done when a player starts tracking the entity.
        event.getEntity().getPersistentData().putBoolean(FORCE_ADULT_TAG, true);
    }

    public static void onPlayerStartTrackingTarget(Entity entity) {
        if (ModConfig.spawnFoals()) return;
        if (!(entity instanceof AgeableMob ageable)) return;
        if (!entity.getPersistentData().getBoolean(FORCE_ADULT_TAG)) return;

        if (ageable.isBaby()) {
            ageable.setAge(0);
        }

        entity.getPersistentData().remove(FORCE_ADULT_TAG);
    }
}

