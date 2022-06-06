package businessdirt.factorygame.game.screens;

import businessdirt.factorygame.FactoryGame;
import businessdirt.factorygame.game.rendering.IsometricRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends AbstractScreen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private IsometricRenderer isometricRenderer;

    protected GameScreen() {
        super(FactoryGame.assets.getSkin("skins/game/skin.json"), Color.TEAL);
    }

    @Override
    public void show() {
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 1920f, 1080f);
        this.camera.position.set(0, this.camera.position.y, this.camera.position.z);

        this.batch = new SpriteBatch();
        this.isometricRenderer = new IsometricRenderer(camera);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) FactoryGame.get().setScreen(new MenuScreen());
        isometricRenderer.input(delta);

        super.render(delta);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        isometricRenderer.draw(batch, delta);
        batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
