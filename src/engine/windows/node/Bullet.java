package engine.windows.node;

import engine.windows.common.Position;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Bullet extends GameObject {
    private static final int UPDATE_PER_SECOND = 60;

    private int damage = 0;

    private int speed; //pixel per seconds

    public Bullet(Position anchorPosition, int speed, int damage) {
        super(anchorPosition);
        //Anchor Position: center of the bullet
        try {
            image = ImageIO.read(new File("Resources/BULLET.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.position.x -= image.getWidth() / 2;
        this.position.y -= image.getHeight();
        this.speed = speed;
        this.damage = damage;
    }

    @Override
    public void update() {
        this.position.y -= speed / UPDATE_PER_SECOND;
    }

    @Override
    public void collideWith(GameObject target) {
        if(!(target instanceof Bullet)) {
            this.destroyGameObject();
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }
}
