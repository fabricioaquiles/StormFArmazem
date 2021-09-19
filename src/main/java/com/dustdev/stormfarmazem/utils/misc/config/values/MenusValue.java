package com.dustdev.stormfarmazem.utils.misc.config.values;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.TranslateColors;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.function.Function;

@Getter
@TranslateColors
@Accessors(fluent = true)
@ConfigFile("menus.yml")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MenusValue implements ConfigurationInjectable {

    @Getter private static final MenusValue instance = new MenusValue();

    @ConfigField("Menus.principal.armazem.name") private String armazemName;
    @ConfigField("Menus.principal.armazem.lore") private List<String> armazemLore;

    @ConfigField("Menus.principal.permissoes.name") private String permName;
    @ConfigField("Menus.principal.permissoes.lore") private List<String> permLore;

    @ConfigField("Menus.armazem.bedrock.name") private String bedrockName;
    @ConfigField("Menus.armazem.bedrock.lore") private List<String> bedrockLore;

    @ConfigField("Menus.armazem.endstone.name") private String endstoneName;
    @ConfigField("Menus.armazem.endstone.lore") private List<String> endstoneLore;

    @ConfigField("Menus.armazem.obsidian.name") private String obsidianName;
    @ConfigField("Menus.armazem.obsidian.lore") private List<String> obsidianLore;

    @ConfigField("Menus.armazem.depositar.name") private String depositarName;
    @ConfigField("Menus.armazem.depositar.lore") private List<String> depositarLore;

    public static <T> T get(Function<MenusValue, T> function) {
        return function.apply(instance);
    }
}