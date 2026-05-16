package com.dronade.addendumspawner;

import com.dronade.addendumspawner.registry.ModBiomeModifiers;
import com.dronade.addendumspawner.world.FoalSpawnControl;
import com.dronade.addendumspawner.world.ModSpawnPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

@Mod(AddendumSpawner.MODID)
public class AddendumSpawner {
    public static final String MODID = "addendumspawner";

    public static final List<ResourceLocation> SPAWNED_ENTITY_IDS = List.of(
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
