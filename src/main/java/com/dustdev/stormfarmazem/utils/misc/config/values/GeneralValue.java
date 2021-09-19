package com.dustdev.stormfarmazem.utils.misc.config.values;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.TranslateColors;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.function.Function;

@Getter
@TranslateColors
@Accessors(fluent = true)
@ConfigFile("config.yml")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GeneralValue implements ConfigurationInjectable {

    @Getter private static final GeneralValue instance = new GeneralValue();

    @ConfigField("Geral.sql-type") private String sqltype;
    @ConfigField("Geral.faction-type") private String factiontype;

    public static <T> T get(Function<GeneralValue, T> function) {
        return function.apply(instance);
    }
}
