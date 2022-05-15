package pl.student.interface_elements;

import pl.student.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel{
    private JButton nextTurn;
    private JButton saveGame;
    private JButton loadGame;
    private Game game;
    public Menu(Game game) {
        this.game = game;

        this.nextTurn = new JButton("Next turn");
        this.saveGame = new JButton("Save game");
        this.loadGame = new JButton("Load game");
        this.nextTurn.setFocusable(false);
        this.saveGame.setFocusable(false);
        this.loadGame.setFocusable(false);

        this.nextTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.gameNextTurn();
            }
        });
        this.saveGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.saveGame();
            }
        });
        this.loadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.loadGame();
            }
        });
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(nextTurn);
        this.add(saveGame);
        this.add(loadGame);
    }
}
