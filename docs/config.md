**What is a config?**
- A config is a plain-text settings file the mod reads to determine behavior. For example: which breeds can spawn, spawn weights, and herd sizes. These files are usually TOML format and live in your Minecraft `config` folder.


**Where to find the config**
- For most players this will be in your Minecraft `config` folder on the computer you run the game from. The file is named `addendumspawner-common.toml`. Edit this file locally and keep a backup before changing anything.
If you do not make any changes, the mod will use its default values, where all breeds are enabled, using default weights, default biomes, and foals spawning in herds are disabled.

**After changing the config**, you **must** restart Minecraft or the server for changes to take effect.

**Key settings explained**
- **spawnFoals:** `true`/`false` — if `true`, foals (baby horses) can spawn with herds. Default: `false`. Note: if you enable this, you must have foals coats added to your resource pack.
- **breeds:** A section that contains one entry per breed. Each breed exposes:
  - **enabled:** `true`/`false` — whether that breed can spawn naturally.
  - **weight:** integer — spawn weight used when this breed is added to biome spawn tables. Higher weights make a breed more likely to be picked to spawn. Guidance:
    - If you enable many breeds in the same biomes, consider lowering individual weights to keep overall spawn rates balanced.
    - If you only enable a few breeds, you can bump their weights so they appear more often.
    - If you do decide to increase weights, try to keep weights at or below `5` for parity with common horse spawn rates — extremely high weights can crowd out other creatures.
  - **minCount:** integer — minimum group size when the breed spawns (the smallest herd you can get).
  - **maxCount:** integer — maximum group size when the breed spawns (always treated as >= `minCount`). These values control herd sizes: for example `minCount = 1` and `maxCount = 4` means single animals up to groups of four may spawn.
  - **biome_whitelist:** array of biome ids — only these biome ids may spawn the breed (example id: `minecraft:plains`). Use namespaced IDs in the form `namespace:biome_name`.
    - Examples:
      - Vanilla biomes: `minecraft:plains`, `minecraft:desert`, `minecraft:dark_forest`.
      - Modded biomes: `modid:your_biome_name` (e.g. `biomesoplenty:coniferous_forest`, `regions_unexplored:barley_fields`).
    - If you're not sure of a biome id, check the mod's documentation or the game logs when entering the biome (some mods print biome ids), or use an in-game datapack/mod that shows biome ids.

**Quick examples (TOML)**
- Disable a breed (example: `arabian`):
locate the breed's category and set the value to false
```
[breeds.arabian]
enabled = false
```

- Change spawn weight and group size for `mustang`: locate the breed's category and set the numbers in `weight`,`minCount` and `maxCount` to your desired values.

```
[breeds.mustang]
weight = 5
minCount = 2
maxCount = 5
```

- Change a breed to add a modded biome(example: a biome from Biomes O' Plenty): Locate the breed's category and add your biome between the [ ], ensure the id is correct, and surrounded by quotes "". As a note, every biome except for the last one must be comma seperated.

```
[breeds.american_quarter_horse]
biome_whitelist = ["minecraft:plains", "minecraft:savanna", "biomesoplenty:coniferous_forest"]
```

**Practical tips**
- **Back up** the config file before editing. Keep a copy named `addendumspawner.toml.bak`.
- **Test first in a fresh world:** Always try configuration changes in a new or throwaway world before applying them to a world you care about. This avoids accidental world-impacting changes and makes it easier to evaluate spawn balance.
- **Biome IDs must be namespaced**, e.g. `minecraft:desert`.
- **If the config breaks**, delete the mod's config file to regenerate defaults, or restore your backup.
- **Resource packs & textures:** Swem addendum requires coats added via a resource pack, if you leave a breed enabled that you do not have a coat for - it **will** appear black, its the same requirement for setting spawnFoals to true; you must have foals coats added.

**Troubleshooting**
- If changes don't apply, check the game log for TOML parse errors.
- If spawn behavior seems wrong, confirm the breed is `enabled` and that the biome you tested is listed in `biome_whitelist`.

**Server**
- **Dedicated Servers:** For dedicated servers, edit the `config` folder on the server host; changes made on your client will not affect the server.
- **Edit workflow for server hosts:**
  - Download the file from the server, edit locally, then upload it back. Keep a backup named `addendumspawner.toml.bak` before replacing.

- **When changes take effect:** Always restart the dedicated server or the single-player world after editing the file. Running servers may cache spawn tables; a restart ensures the new settings are loaded.

For any issues or questions, please reach out to dronade on Discord.