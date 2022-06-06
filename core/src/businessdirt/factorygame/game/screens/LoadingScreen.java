package businessdirt.factorygame.game.screens;

import businessdirt.factorygame.FactoryGame;
import businessdirt.factorygame.game.util.AssetLoader;
import businessdirt.factorygame.game.util.Config;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

public class LoadingScreen extends ScreenAdapter {

    public LoadingScreen() {
        FactoryGame.config = Config.getConfig();

        FactoryGame.assets = new AssetLoader();
        FactoryGame.assets.load();
    }

    @Override
    public void render(float delta) {
        // clear the screen from the previous frame
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // if the assets finished loading it will enter the main menu
        if (FactoryGame.assets.update()) FactoryGame.get().setScreen(new MenuScreen());
    }

    @Override
    public void hide() {
        this.dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
