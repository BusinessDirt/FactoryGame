package businessdirt.factorygame.game.rendering;

import businessdirt.factorygame.FactoryGame;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Tile {

    private Vector2 worldPosition;
    private Vector2 tileMapPosition;
    private String texture;
    private OrthographicCamera camera;

    public Tile(String texture, Vector2 worldPosition, Vector2 tileMapPosition, OrthographicCamera camera) {
        this.texture = texture;
        this.worldPosition = worldPosition;
        this.tileMapPosition = tileMapPosition;
        this.camera = camera;
    }

    public void render(SpriteBatch batch, float delta, Vector2 centerTile) {
        if (onScreen()) {
            if (Math.abs(tileMapPosition.x - centerTile.x) < 5 && Math.abs(tileMapPosition.y - centerTile.y) < 5) {
                batch.draw(FactoryGame.assets.getTexture(texture), worldPosition.x, worldPosition.y, TileMap.TILE_WIDTH, TileMap.TILE_HEIGHT);
            }
        }
    }

    private boolean onScreen() {
        float xo = camera.viewportWidth / 2f - camera.position.x;
        float yo = camera.viewportHeight / 2f - camera.position.y;
        return (tileMapPosition.x + xo > - TileMap.TILE_WIDTH && tileMapPosition.y + yo > - TileMap.TILE_HEIGHT) &&
                (tileMapPosition.x + xo < camera.viewportWidth + TileMap.TILE_WIDTH && tileMapPosition.y + yo < camera.viewportHeight);
    }
}
