package businessdirt.factorygame.game.rendering;

import businessdirt.factorygame.FactoryGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.Iterator;

public class IsometricRenderer {

    public static final int TILE_WIDTH = 128;
    public static final int TILE_HEIGHT = 128;

    private Texture ground;
    private Texture sky;
    private OrthographicCamera camera;
    private TiledMap map;

    public IsometricRenderer(OrthographicCamera camera) {
        this.camera = camera;
        ground = FactoryGame.assets.getTexture("textures/game/ground.png");
        sky = FactoryGame.assets.getTexture("textures/game/sky.png");
    }

    public void draw(SpriteBatch batch, float delta) {
        for (int row = 10; row >= 0; row--) {
            for (int col = 10; col >= 0; col--) {
                Vector2 screenCoordinates = toScreenCoordinates(new Vector2(row, col));
                float xo = camera.viewportWidth / 2f - camera.position.x;
                float yo = camera.viewportHeight / 2f - camera.position.y;
                if (screenCoordinates.x + xo > - TILE_WIDTH && screenCoordinates.y + yo > - TILE_HEIGHT) {
                    if (screenCoordinates.x + xo < camera.viewportWidth + TILE_WIDTH && screenCoordinates.y + yo < camera.viewportHeight)
                        batch.draw(ground, screenCoordinates.x - TILE_WIDTH / 2f, screenCoordinates.y + selected(row, col), TILE_WIDTH, TILE_HEIGHT);
                }
            }
        }
    }

    public void input(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.position.y += 300 * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.position.x -= 300 * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.position.y -= 300 * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.position.x += 300 * delta;
        }
    }

    private float selected(float x, float y) {
        float centerX = camera.viewportWidth / 2f - camera.position.x;
        float centerY = camera.viewportHeight / 2f + camera.position.y;

        Vector2 gridCoordinates = toGridCoordinates(new Vector2(Gdx.input.getX() - centerX, centerY - Gdx.input.getY()));
        if (Math.ceil(gridCoordinates.x) == x && Math.ceil(gridCoordinates.y) == y) {
            return 4f;
        }

        return 0f;
    }

    public static Vector2 toScreenCoordinates(Vector2 tile) {
        return new Vector2(tile.x * 0.5f * TILE_WIDTH + tile.y * -0.5f * TILE_WIDTH, tile.x * 0.25f * TILE_HEIGHT + tile.y * 0.25f * TILE_WIDTH);
    }

    public static Vector2 toGridCoordinates(Vector2 screen) {
        float a = TILE_WIDTH * 0.5f;
        float b = - TILE_WIDTH * 0.5f;
        float c = TILE_HEIGHT * 0.25f;

        float[] invertedMatrix = invertMatrix(a, b, c, c);
        return new Vector2(screen.x * invertedMatrix[0] + screen.y * invertedMatrix[1] - 2, screen.x * invertedMatrix[2] + screen.y * invertedMatrix[3] - 2);
    }

    public static float[] invertMatrix(float a, float b, float c, float d) {
        float det = 1 / (a * d - b * c);
        return new float[] {det * d, det * -b, det * -c, det * a};
    }
}
