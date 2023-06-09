package engine.windows.node.enemy;

import engine.windows.node.Bullet;
import engine.windows.common.Position;
import engine.windows.node.GameObject;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class EnemyPlane extends GameObject {
    public EnemyPlane(Position position) {
        super(position);
        try {
            image = ImageIO.read(new File("Resources/enemy1.png"));
            this.position = position;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void collideWith(GameObject target) {
        if(target instanceof Bullet) {
            hitByBullet((Bullet) target);
        }
    }

    private void hitByBullet(Bullet b) {
        System.out.println("HIT!");
        destroyGameObject();
    }

    @Override
    public void update() {

    }
}
