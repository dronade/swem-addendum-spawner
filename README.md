# SWEM Addendum Spawner

A simple & lightweight Minecraft Forge mod that adds [swem addendum horses](https://www.curseforge.com/minecraft/mc-mods/swem-addendum) to the spawn table; allowing them to occur naturally in the world. 

## Breed Spawn Table

Below is a table showing which horse breeds spawn in which biomes. A ✔️ indicates the breed spawns in that biome.

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
	arabian = true
	fjord = true
	shire = true
	breton = true
	knabstrupper = true
	kladruper = true
	mustang = true
	turkoman = true
	warmblood = true
	donkey = true
	mule = true
	marwari = true
	friesian = true
	irish_draught = true
	american_quarter_horse = true
	irish_draught_pegasus = true
	pegasus = true
```

- Setting a breed to `true` enables its natural spawning.
- Setting a breed to `false` disables its natural spawning.

After changing the config, restart Minecraft for changes to take effect.

## Requirements

This mod requires the SWEM Addendum mod to be installed and loaded, alongside any of their dependecies. Please ensure you have SWEM Addendum in your mods folder for this addon to work.

You must also have a community pack with swem addenum compatible coat's loaded - without this, horses will appear black.  For more information on how to do this, please see SWEM addendum's wiki.

## Support

For questions, suggestions, or issues, please open an issue here on github, reach out on curseforge, send a message on discord.
