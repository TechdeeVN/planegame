package engine.windows.node.scenes;

import engine.windows.GameWindows;
import engine.windows.common.Position;
import engine.windows.node.Background;
import engine.windows.node.GameObject;
import engine.windows.node.enemy.EnemyPlane;
import engine.windows.node.player.Plane;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class BattleScene extends Scene {
    private Background gameBackground;

    public BattleScene(GameWindows gameWindows) {
        super(gameWindows);
        listGameObject.add(new Plane(
                new Position(250, 100),
                500,
                50,
                2,
                4
        ));
        listGameObject.add(new EnemyPlane(new Position(150, 200)));
        Plane playerPlane = new Plane(
                new Position(300, 300),
                300,
                150,
                4,
                1
        );
        listGameObject.add(playerPlane);
        gameBackground = new Background(gameWindows.getWidth(), gameWindows.getHeight());
        this.gameWindows.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    listGameObject.add(playerPlane.shot());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.gameWindows.addKeyListener(playerPlane.getKeyListener());
    }

    public void update() {
        super.update();
        checkCollide();
        removeDestroyedGameObjects();
    }

    @Override
    public void draw(Graphics g) {
        gameBackground.draw(g);
        super.draw(g);
    }

    private void removeDestroyedGameObjects() {
        List<GameObject> destroyedObjects = new ArrayList<>();
        for (GameObject gameObject : listGameObject) {
            if (gameObject.isDestroy()) {
                destroyedObjects.add(gameObject);
            }
        }
        listGameObject.removeAll(destroyedObjects);
    }

    void checkCollide() {
        for (int i = 0; i < listGameObject.size(); i++) {
            for (int j = i + 1; j < listGameObject.size(); j++) {
                GameObject gameObjectA = listGameObject.get(i);
                GameObject gameObjectB = listGameObject.get(j);
                if (gameObjectA.isCollide(gameObjectB)) {
                    gameObjectA.collideWith(gameObjectB);
                    gameObjectB.collideWith(gameObjectA);
                }
            }
        }
    }
}
