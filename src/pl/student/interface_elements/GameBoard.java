package pl.student.interface_elements;

import pl.student.Game;
import pl.student.Point;
import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    private Game game;
    public GameBoard(Game game) {
        this.game = game;

        this.setLayout(new GridLayout(game.getWorld().getHeight(),game.getWorld().getWidth(),0,0));
        for(int i = 0; i<game.getWorld().getHeight(); i++) {
            for(int j = 0; j<game.getWorld().getWidth(); j++) {
                this.add(new Tile(new Point(j,i),this.game));
            }
        }
    }
}
