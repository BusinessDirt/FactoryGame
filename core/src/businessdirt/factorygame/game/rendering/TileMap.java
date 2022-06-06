package businessdirt.factorygame.game.rendering;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class TileMap {

    public static final int MAP_SIZE = 32;
    public static final int TILE_WIDTH = 128;
    public static final int TILE_HEIGHT = 128;

    public ArrayList<Tile> tiles;
    private String texture;
    private OrthographicCamera camera;

    public TileMap(OrthographicCamera camera) {
        this.tiles = new ArrayList<>();
        this.texture = "textures/game/ground.png";
        this.camera = camera;

        fillTileMap();
    }

    public void renderMap(SpriteBatch batch, float delta) {
        for (Tile tile : tiles) {
            tile.render(batch, delta, new Vector2(MAP_SIZE / 2f, MAP_SIZE / 2f));
        }
    }

    private void fillTileMap() {
        for (int row = MAP_SIZE; row >= 0; row--) {
            for (int col = MAP_SIZE; col >= 0; col--) {
                Vector2 screenCoordinates = IsometricRenderer.toScreenCoordinates(new Vector2(row, col));
                tiles.add(new Tile(texture, new Vector2(row, col), new Vector2(screenCoordinates.x, screenCoordinates.y), camera));
            }
        }
    }
}
