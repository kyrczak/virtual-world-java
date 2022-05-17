package pl.student.interface_elements;

import pl.student.Game;
import pl.student.Point;
import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    private Game game;
    public GameBoard(Game game) {
        this.game = game;

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx=1;
        c.weighty=1;
        for(int i = 0; i<game.getWorld().getHeight(); i++) {
            for(int j = 0; j<game.getWorld().getWidth(); j++) {
                c.gridx = j;
                c.gridy = i;
                this.add(new Tile(new Point(j,i),this.game),c);
            }
        }
    }
}
