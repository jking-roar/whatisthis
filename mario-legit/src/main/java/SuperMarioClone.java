import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SuperMarioClone extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    Vector2 position;
    Vector2 velocity;
    float gravity;
    boolean isJumping;

    @Override
    public void create() {
        batch = new SpriteBatch(1);
        img = new Texture("mario.jpg");
        position = new Vector2(0, 0);
        velocity = new Vector2(0, 0);
        gravity = -9.8f;
        isJumping = false;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= 5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += 5;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            isJumping = true;
            velocity.y = 10;
        }

        if (isJumping) {
            velocity.y += gravity;
            position.y += velocity.y;
            if (position.y <= 0) {
                isJumping = false;
                position.y = 0;
                velocity.y = 0;
            }
        }

        batch.begin();
        batch.draw(img, position.x, position.y);
        batch.end();
    }

    public static void main(String[] args) {
        SuperMarioClone superMarioClone = new SuperMarioClone();
        superMarioClone.create();
    }
}
