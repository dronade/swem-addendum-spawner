package com.dronade.addendumspawner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Predicate;

public class ModConfig {
    public static final ForgeConfigSpec SPEC;
    private static final String DEFAULTS_RESOURCE = "/addendumspawner/spawn_defaults.json";
    private static ForgeConfigSpec.BooleanValue SPAWN_FOALS;

    public static final class BreedConfig {
        private final ForgeConfigSpec.BooleanValue enabled;
        private final ForgeConfigSpec.IntValue weight;
        private final ForgeConfigSpec.IntValue minCount;
        private final ForgeConfigSpec.IntValue maxCount;
        private final ForgeConfigSpec.ConfigValue<List<? extends String>> biomeWhitelist;

        private BreedConfig(ForgeConfigSpec.BooleanValue enabled,
                            ForgeConfigSpec.IntValue weight,
                            ForgeConfigSpec.IntValue minCount,
                            ForgeConfigSpec.IntValue maxCount,
                            ForgeConfigSpec.ConfigValue<List<? extends String>> biomeWhitelist) {
            this.enabled = enabled;
            this.weight = weight;
            this.minCount = minCount;
            this.maxCount = maxCount;
            this.biomeWhitelist = biomeWhitelist;
        }

        public boolean isEnabled() {
            return enabled.get();
        }

        public int getWeight() {
            return weight.get();
        }

        public int getMinCount() {
            return minCount.get();
        }

        public int getMaxCount() {
            return Math.max(maxCount.get(), minCount.get());
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

    private record BreedDefaults(boolean enabled, int weight, int minCount, int maxCount, List<String> biomes) {}

    private static final Map<String, BreedConfig> BREEDS = new HashMap<>();

    static {
        Map<String, BreedDefaults> defaultsByBreed = loadDefaults();

        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        SPAWN_FOALS = builder
                .comment("If true, enables foals to spawn with herds")
                .define("spawnFoals", false);

        builder.push("breeds");

        for (Map.Entry<String, BreedDefaults> entry : defaultsByBreed.entrySet()) {
            defineBreed(builder, entry.getKey(), entry.getValue());
        }

        builder.pop();
        SPEC = builder.build();
    }

    public static boolean spawnFoals() {
        return SPAWN_FOALS.get();
    }

    private static void defineBreed(ForgeConfigSpec.Builder builder, String key, BreedDefaults defaults) {
        Predicate<Object> validBiomeString = o -> o instanceof String s && ResourceLocation.tryParse(s) != null;
        builder.push(key);

        ForgeConfigSpec.BooleanValue enabled = builder
                .comment("-> Enable/disable natural spawning for this breed.")
                .define("enabled", defaults.enabled());

        ForgeConfigSpec.IntValue weight = builder
                .comment("-> Spawn weight used when this breed is added to biome spawn tables.")
                .defineInRange("weight", defaults.weight(), 1, 1000);

        ForgeConfigSpec.IntValue minCount = builder
                .comment("-> Minimum group size for this breed.")
                .defineInRange("minCount", defaults.minCount(), 1, 32);

        ForgeConfigSpec.IntValue maxCount = builder
                .comment("-> Maximum group size for this breed.")
                .defineInRange("maxCount", defaults.maxCount(), 1, 32);

        ForgeConfigSpec.ConfigValue<List<? extends String>> biomes = builder
                .comment("-> Biome whitelist for this breed. Only these biome ids can spawn this breed.")
                .defineListAllowEmpty("biome_whitelist", defaults.biomes(), validBiomeString);

        builder.pop();
        BREEDS.put(key, new BreedConfig(enabled, weight, minCount, maxCount, biomes));
    }

    private static Map<String, BreedDefaults> loadDefaults() {
        try (InputStream inputStream = ModConfig.class.getResourceAsStream(DEFAULTS_RESOURCE)) {
            if (inputStream == null) {
                throw new IllegalStateException("Missing defaults resource: " + DEFAULTS_RESOURCE);
            }

            JsonObject root = JsonParser.parseReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).getAsJsonObject();
            JsonObject breeds = root.getAsJsonObject("breeds");
            if (breeds == null || breeds.size() == 0) {
                throw new IllegalStateException("No breed defaults found in " + DEFAULTS_RESOURCE);
            }

            Map<String, BreedDefaults> out = new LinkedHashMap<>();
            for (Map.Entry<String, JsonElement> entry : breeds.entrySet()) {
                String breedKey = entry.getKey();
                JsonObject obj = entry.getValue().getAsJsonObject();

                boolean enabled = getRequiredBoolean(obj, "enabled", breedKey);
                int weight = getRequiredInt(obj, "weight", breedKey);
                int minCount = getRequiredInt(obj, "minCount", breedKey);
                int maxCount = getRequiredInt(obj, "maxCount", breedKey);

                if (weight < 1 || minCount < 1 || maxCount < 1 || maxCount < minCount) {
                    throw new IllegalStateException("Invalid spawn defaults for breed " + breedKey + " in " + DEFAULTS_RESOURCE);
                }

                JsonArray biomeArray = obj.getAsJsonArray("biomes");
                if (biomeArray == null || biomeArray.isEmpty()) {
                    throw new IllegalStateException("Breed " + breedKey + " must define at least one biome in " + DEFAULTS_RESOURCE);
                }

                List<String> biomes = new ArrayList<>();
                for (JsonElement biomeElement : biomeArray) {
                    String biomeId = biomeElement.getAsString();
                    if (ResourceLocation.tryParse(biomeId) == null) {
                        throw new IllegalStateException("Invalid biome id " + biomeId + " for breed " + breedKey + " in " + DEFAULTS_RESOURCE);
                    }
                    biomes.add(biomeId);
                }

                out.put(breedKey, new BreedDefaults(enabled, weight, minCount, maxCount, List.copyOf(biomes)));
            }

            return Collections.unmodifiableMap(out);
        } catch (IOException e) {
            throw new IllegalStateException("Failed reading defaults resource: " + DEFAULTS_RESOURCE, e);
        }
    }

    private static boolean getRequiredBoolean(JsonObject obj, String key, String breedKey) {
        JsonElement element = obj.get(key);
        if (element == null) {
            throw new IllegalStateException("Missing key " + key + " for breed " + breedKey + " in " + DEFAULTS_RESOURCE);
        }
        return element.getAsBoolean();
    }

    private static int getRequiredInt(JsonObject obj, String key, String breedKey) {
        JsonElement element = obj.get(key);
        if (element == null) {
            throw new IllegalStateException("Missing key " + key + " for breed " + breedKey + " in " + DEFAULTS_RESOURCE);
        }
        return element.getAsInt();
    }

    public static BreedConfig getBreed(String breedKey) {
        return BREEDS.get(breedKey);
    }
}
