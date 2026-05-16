package com.dronade.addendumspawner;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.*;
import java.util.function.Predicate;

public class ModConfig {
    public static final ForgeConfigSpec SPEC;

    public static final class BreedConfig {
        private final ForgeConfigSpec.BooleanValue enabled;
        private final ForgeConfigSpec.ConfigValue<List<? extends String>> biomeWhitelist;

        private BreedConfig(ForgeConfigSpec.BooleanValue enabled,
                            ForgeConfigSpec.ConfigValue<List<? extends String>> biomeWhitelist) {
            this.enabled = enabled;
            this.biomeWhitelist = biomeWhitelist;
        }

        public boolean isEnabled() {
            return enabled.get();
        }

        public Set<ResourceLocation> getBiomeWhitelist() {
            Set<ResourceLocation> out = new HashSet<>();
            for (String s : biomeWhitelist.get()) {
                ResourceLocation rl = ResourceLocation.tryParse(s);
                if (rl != null) out.add(rl);
            }
            return out;
        }

    }

    private static final Map<String, BreedConfig> BREEDS = new HashMap<>();

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("breeds");

        // Config is the source of truth for per-breed biome whitelists.
        defineBreed(builder, "american_quarter_horse", true, List.of("minecraft:plains", "minecraft:savanna", "minecraft:badlands"));
        defineBreed(builder, "arabian", true, List.of("minecraft:desert", "minecraft:savanna", "minecraft:savanna_plateau"));
        defineBreed(builder, "breton", true, List.of("minecraft:forest", "minecraft:birch_forest", "minecraft:taiga"));
        defineBreed(builder, "donkey", true, List.of("minecraft:desert", "minecraft:savanna", "minecraft:plains"));
        defineBreed(builder, "fjord", true, List.of("minecraft:snowy_slopes", "minecraft:snowy_taiga", "minecraft:grove", "minecraft:windswept_hills"));
        defineBreed(builder, "friesian", true, List.of("minecraft:dark_forest", "minecraft:forest", "minecraft:taiga"));
        defineBreed(builder, "irish_draught", true, List.of("minecraft:plains", "minecraft:meadow", "minecraft:forest"));
        defineBreed(builder, "irish_draught_pegasus", true, List.of("minecraft:windswept_hills", "minecraft:jagged_peaks", "minecraft:grove"));
        defineBreed(builder, "kladruper", true, List.of("minecraft:plains", "minecraft:meadow", "minecraft:forest"));
        defineBreed(builder, "knabstrupper", true, List.of("minecraft:plains", "minecraft:sunflower_plains", "minecraft:savanna"));
        defineBreed(builder, "marwari", true, List.of("minecraft:desert", "minecraft:badlands", "minecraft:eroded_badlands"));
        defineBreed(builder, "mule", true, List.of("minecraft:plains", "minecraft:savanna", "minecraft:forest"));
        defineBreed(builder, "mustang", true, List.of("minecraft:plains", "minecraft:savanna", "minecraft:desert"));
        defineBreed(builder, "pegasus", true, List.of("minecraft:jagged_peaks"));
        defineBreed(builder, "shire", true, List.of("minecraft:plains", "minecraft:meadow", "minecraft:forest"));
        defineBreed(builder, "thoroughbred", true, List.of("minecraft:plains", "minecraft:savanna"));
        defineBreed(builder, "turkoman", true, List.of("minecraft:desert", "minecraft:badlands", "minecraft:eroded_badlands"));
        defineBreed(builder, "warmblood", true, List.of("minecraft:plains", "minecraft:meadow", "minecraft:forest"));

        builder.pop();
        SPEC = builder.build();
    }

    private static void defineBreed(ForgeConfigSpec.Builder builder, String key, boolean defaultEnabled, List<String> defaultBiomes) {
        Predicate<Object> validBiomeString = o -> o instanceof String s && ResourceLocation.tryParse(s) != null;
        builder.push(key);

        ForgeConfigSpec.BooleanValue enabled = builder
                .comment("Enable/disable natural spawning for this breed.")
                .define("enabled", defaultEnabled);

        ForgeConfigSpec.ConfigValue<List<? extends String>> biomes = builder
                .comment("Biome whitelist for this breed. Only these biome ids can spawn this breed.")
                .defineListAllowEmpty("biome_whitelist", defaultBiomes, validBiomeString);

        builder.pop();
        BREEDS.put(key, new BreedConfig(enabled, biomes));
    }

    public static BreedConfig getBreed(String breedKey) {
        return BREEDS.get(breedKey);
    }
}