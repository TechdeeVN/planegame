package engine.windows.node.scenes;

import engine.windows.GameWindows;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuScene extends Scene {

    public MenuScene(GameWindows gameWindows) {
        super(gameWindows);
        gameWindows.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getX() > 200 && e.getX() < 400 && e.getY() > 200 && e.getY() < 250) {
                    gameWindows.getSceneStack().push(new BattleScene(gameWindows));
                }

                if (e.getX() > 200 && e.getX() < 400 && e.getY() > 400 && e.getY() < 450) {
                    System.exit(0);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void draw(Graphics g) {
        super.draw(g);
        g.drawRect(200, 200, 200, 50);
        g.drawString("PLAY", 210, 210);

        g.drawRect(200, 400, 200, 50);
        g.drawString("QUIT", 210, 410);
    }
}
