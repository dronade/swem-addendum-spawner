# SWEM Addendum Spawner

A simple & lightweight Minecraft Forge mod that adds swem addendum horses to the spawn table; allowing them to occur naturally in the world. 

## Breed Spawn Table

Below is a table showing which breeds spawn in which biomes. A ✔️ indicates the breed spawns in that biome.

| Biome               | American Quarter Horse | Arabian | Breton | Donkey | Fjord | Friesian | Irish Draught Pegasus | Irish Draught | Kladruper | Knabstrupper | Marwari | Mule | Mustang | Pegasus | Shire | Thoroughbred | Turkoman | Warmblood |
|---------------------|:---------------------:|:-------:|:------:|:------:|:-----:|:--------:|:--------------------:|:-------------:|:---------:|:------------:|:-------:|:----:|:-------:|:-------:|:-----:|:------------:|:--------:|:---------:|
| Plains              | ✔️                    |         |        | ✔️     |       |          |                      | ✔️            | ✔️        | ✔️           |         | ✔️  | ✔️      |         | ✔️    | ✔️           |          | ✔️        |
| Savanna             | ✔️                    | ✔️      |        | ✔️     |       |          |                      |               |           | ✔️           |         | ✔️  | ✔️      |         |      | ✔️           |          |          |
| Badlands            | ✔️                    |         |        |        |       |          |                      |               |           |              | ✔️      |     |         |         |      |             | ✔️       |          |
| Desert              |                       | ✔️      |        | ✔️     |       |          |                      |               |           |              | ✔️      |     | ✔️      |         |      |             | ✔️       |          |
| Savanna Plateau     |                       | ✔️      |        |        |       |          |                      |               |           |              |         |     |         |         |      |             |          |          |
| Forest              |                       |         | ✔️     |        |       | ✔️       |                      | ✔️            | ✔️        |              |         | ✔️  |         |         | ✔️    |             |          | ✔️        |
| Birch Forest        |                       |         | ✔️     |        |       |          |                      |               |           |              |         |     |         |         |      |             |          |          |
| Taiga               |                       |         | ✔️     |        |       | ✔️       |                      |               |           |              |         |     |         |         |      |             |          |          |
| Snowy Slopes        |                       |         |        |        | ✔️    |          |                      |               |           |              |         |     |         |         |      |             |          |          |
| Snowy Taiga         |                       |         |        |        | ✔️    |          |                      |               |           |              |         |     |         |         |      |             |          |          |
| Grove               |                       |         |        |        | ✔️    |          | ✔️                   |               |           |              |         |     |         |         |      |             |          |          |
| Windswept Hills     |                       |         |        |        | ✔️    |          | ✔️                   |               |           |              |         |     |         |         |      |             |          |          |
| Jagged Peaks        |                       |         |        |        |       |          | ✔️                   |               |           |              |         |     |         | ✔️      |      |             |          |          |
| Meadow              |                       |         |        |        |       |          |                      | ✔️            | ✔️        |              |         |     |         |         | ✔️    |             |          | ✔️        |
| Sunflower Plains    |                       |         |        |        |       |          |                      |               |           | ✔️           |         |     |         |         |      |             |          |          |

## Config

The mod generates a configuration file named `addendumspawner-common.toml` in your Minecraft `config` folder (e.g., `config/addendumspawner-common.toml`).

This file allows you to enable or disable the natural spawning of each breed. Example config section:

```toml
[breeds]
    irishDraught = true
    irishDraughtPegasus = true
    arabian = true
    fjord = true
    shire = true
    breton = true
    knabstrupper = true
    kladruper = true
    quarterHorse = true
    mustang = true
    turkoman = true
    warmblood = true
    donkey = true
    mule = true
    marwari = true
    friesian = true
```

- Setting a breed to `true` enables its natural spawning.
- Setting a breed to `false` disables its natural spawning.

After changing the config, restart Minecraft for changes to take effect.

## Requirements

See our curseforge page on how to install this mod

## Support

For questions, suggestions, or issues, please open an issue, reach out on curseforge or on discord.
