package com.dustdev.stormfarmazem.utils.misc.config.values;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigSection;
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
@ConfigFile("mensagens.yml")
@ConfigSection("Mensagens")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MensagensValue implements ConfigurationInjectable {

    @Getter
    private static final MensagensValue instance = new MensagensValue();

    @ConfigField("semFaccao") private String semFaccao;
    @ConfigField("semPermissao") private String semPermissao;
    @ConfigField("semBlocos") private String semBlocos;

    public static <T> T get(Function<MensagensValue, T> function) {
        return function.apply(instance);
    }
}