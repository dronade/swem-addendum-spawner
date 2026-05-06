package com.dronade.addendumspawner;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModConfig {

    public static final ForgeConfigSpec SPEC;

    /**
     * ─── ⋆⋅✦⋅⋆ ───
     * Breed Toggles
     * ─── ⋆⋅✦⋅⋆ ───
     */

    public static ForgeConfigSpec.BooleanValue IRISH_DRAUGHT;
    public static ForgeConfigSpec.BooleanValue ARABIAN;
    public static ForgeConfigSpec.BooleanValue FJORD;
    public static ForgeConfigSpec.BooleanValue SHIRE;
    public static ForgeConfigSpec.BooleanValue BRETON;
    public static ForgeConfigSpec.BooleanValue KNABSTRUPPER;
    public static ForgeConfigSpec.BooleanValue KLADRUBER;
    public static ForgeConfigSpec.BooleanValue AMERICAN_QUARTER_HORSE;
    public static ForgeConfigSpec.BooleanValue MUSTANG;
    public static ForgeConfigSpec.BooleanValue TURKOMAN;
    public static ForgeConfigSpec.BooleanValue WARMBLOOD;
    public static ForgeConfigSpec.BooleanValue DONKEY;
    public static ForgeConfigSpec.BooleanValue MULE;
    public static ForgeConfigSpec.BooleanValue MARWARI;
    public static ForgeConfigSpec.BooleanValue FRIESIAN;
    public static ForgeConfigSpec.BooleanValue IRISH_DRAUGHT_PEGASUS;
    public static ForgeConfigSpec.BooleanValue PEGASUS;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("breeds");

        IRISH_DRAUGHT = builder.define("irish_draught", true);
        ARABIAN = builder.define("arabian", true);
        FJORD = builder.define("fjord", true);
        SHIRE = builder.define("shire", true);
        BRETON = builder.define("breton", true);
        KNABSTRUPPER = builder.define("knabstrupper", true);
        KLADRUBER = builder.define("kladruper", true);
        AMERICAN_QUARTER_HORSE = builder.define("american_quarter_horse", true);
        MUSTANG = builder.define("mustang", true);
        TURKOMAN = builder.define("turkoman", true);
        WARMBLOOD = builder.define("warmblood", true);
        DONKEY = builder.define("donkey", true);
        MULE = builder.define("mule", true);
        MARWARI = builder.define("marwari", true);
        FRIESIAN = builder.define("friesian", true);
        IRISH_DRAUGHT_PEGASUS = builder.define("irish_draught_pegasus", true);
        PEGASUS = builder.define("pegasus", true);

        builder.pop();

        SPEC = builder.build();
    }
}