package com.dronade.addendumspawner;

import com.dronade.addendumspawner.registry.ModBiomeModifiers;
import com.dronade.addendumspawner.world.FoalSpawnControl;
import com.dronade.addendumspawner.world.ModSpawnPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

@Mod(AddendumSpawner.MODID)
public class AddendumSpawner {
    public static final String MODID = "addendumspawner";

    private static ResourceLocation resource(String path) {
        // ResourceLocation is depreciated, but optifine crashes on its alternative 'fromNamespaceAndPath',
        // so we're stuck with this for 1.20.1. :/
        return new ResourceLocation("swemaddendum", path);
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

    public AddendumSpawner(FMLJavaModLoadingContext context) {
        context.registerConfig(Type.COMMON, ModConfig.SPEC);
        ModBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(context.getModEventBus());
        context.getModEventBus().addListener(ModSpawnPlacements::onSpawnPlacementRegister);
        MinecraftForge.EVENT_BUS.addListener(AddendumSpawner::onPlayerStartTracking);
        MinecraftForge.EVENT_BUS.addListener(FoalSpawnControl::onFinalizeSpawn);
    }

    private static void onPlayerStartTracking(PlayerEvent.StartTracking event) {
        if (!(event.getEntity() instanceof ServerPlayer)) return;

        Entity target = event.getTarget();
        ResourceLocation entityId = ForgeRegistries.ENTITY_TYPES.getKey(target.getType());
        if (entityId == null || !SPAWNED_ENTITY_IDS.contains(entityId)) return;

        FoalSpawnControl.onPlayerStartTrackingTarget(target);
        target.onAddedToWorld();
    }
}
