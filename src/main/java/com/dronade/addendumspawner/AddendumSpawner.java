package com.dronade.addendumspawner;

import com.dronade.addendumspawner.registry.ModBiomeModifiers;
import com.dronade.addendumspawner.world.FoalSpawnControl;
import com.dronade.addendumspawner.world.ModSpawnPlacements;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.List;

@Mod(AddendumSpawner.MODID)
public class AddendumSpawner {
    public static final String MODID = "addendumspawner";

    private static ResourceLocation resource(String path) {
        return ResourceLocation.fromNamespaceAndPath("swemaddendum", path);
    }

    public static final List<ResourceLocation> SPAWNED_ENTITY_IDS = List.of(
            resource("american_quarter_horse"),
            resource("arabian"),
            resource("breton"),
            resource("donkey"),
            resource("fjord"),
            resource("friesian"),
            resource("irish_draught"),
            resource("irish_draught_pegasus"),
            resource("kladruper"),
            resource("knabstrupper"),
            resource("marwari"),
            resource("mule"),
            resource("mustang"),
            resource("pegasus"),
            resource("shire"),
            resource("thoroughbred"),
            resource("turkoman"),
            resource("warmblood")
    );

    public AddendumSpawner(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, com.dronade.addendumspawner.ModConfig.SPEC);
        ModBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(modEventBus);
        modEventBus.addListener(ModSpawnPlacements::onSpawnPlacementRegister);
        NeoForge.EVENT_BUS.addListener(AddendumSpawner::onPlayerStartTracking);
        NeoForge.EVENT_BUS.addListener(FoalSpawnControl::onFinalizeSpawn);
    }

    private static void onPlayerStartTracking(PlayerEvent.StartTracking event) {
        if (!(event.getEntity() instanceof ServerPlayer)) return;

        Entity target = event.getTarget();
        ResourceLocation entityId = BuiltInRegistries.ENTITY_TYPE.getKey(target.getType());
        if (entityId == null || !SPAWNED_ENTITY_IDS.contains(entityId)) return;

        FoalSpawnControl.onPlayerStartTrackingTarget(target);
    }
}
