package businessdirt.factorygame.game.util;

import businessdirt.factorygame.core.config.ConfigHandler;
import businessdirt.factorygame.core.config.data.Property;
import businessdirt.factorygame.core.config.data.PropertyType;
import com.badlogic.gdx.graphics.Color;

import java.io.File;

public class Config extends ConfigHandler {

    private static Config instance;

    @Property(
            type = PropertyType.SLIDER,
            name = "SFX Volume",
            description = "Volume for Sound effects (Button clicks, etc)",
            category = "Audio",
            subcategory = "Volume",
            max = 100
    )
    public static Integer sfxVolume = 50;

    @Property(
            type = PropertyType.SLIDER,
            name = "Music Volume",
            description = "Volume for Music",
            category = "Audio",
            subcategory = "Volume",
            max = 100
    )
    public static Integer musicVolume = 50;

    public Config() {
        super(new File(Util.getConfigFolder(), "\\config.toml"));
        this.initialize();
    }

    public static Config getConfig() {
        if (Config.instance == null) Config.instance = new Config();
        return Config.instance;
    }
}
