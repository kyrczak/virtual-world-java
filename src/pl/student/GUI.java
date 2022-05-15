package pl.student;
import pl.student.interface_elements.GameBoard;
import pl.student.interface_elements.Menu;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{
    private Menu menu;
    private GameBoard board;
    private Game game;

    private int width = 600;
    private int height = 800;
    public GUI(Game game) {
        this.game = game;
        this.setSize(width,height);
        this.setTitle("Virtual World");
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.menu = new Menu(game);
        this.board = new GameBoard(game);

        c.gridwidth=3;
        c.gridheight=1;
        c.weightx=1;
        c.weighty=0;
        c.gridx=0;
        c.gridy=0;
        c.fill=GridBagConstraints.NONE;
        this.add(menu,c);
        c.gridwidth=3;
        c.gridheight=3;
        c.weighty=1;
        c.weightx=1;
        c.gridx=0;
        c.gridy=1;
        c.fill=GridBagConstraints.BOTH;
        this.add(board,c);
        //TODO Journal

        this.setVisible(true);
    }

    public Component[] getTiles() {
        return this.board.getComponents();
    }
}
