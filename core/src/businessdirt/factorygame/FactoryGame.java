package businessdirt.factorygame;

import businessdirt.factorygame.game.screens.LoadingScreen;
import businessdirt.factorygame.game.util.AssetLoader;
import businessdirt.factorygame.game.util.Config;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Graphics;

import java.awt.*;

public class FactoryGame extends Game {

	public static FactoryGame instance;
	public static Config config;
	public static AssetLoader assets;

	public static Graphics.DisplayMode fullscreen;
	public static Dimension windowed;

	@Override
	public void create() {
		this.setScreen(new LoadingScreen());
	}

	public static FactoryGame get() {
		if (FactoryGame.instance == null) FactoryGame.instance = new FactoryGame();
		return FactoryGame.instance;
	}

	public static FactoryGame init(Graphics.DisplayMode fullscreen, Dimension windowed) {
		FactoryGame.fullscreen = fullscreen;
		FactoryGame.windowed = windowed;
		return FactoryGame.get();
	}
}
