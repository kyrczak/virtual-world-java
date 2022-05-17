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
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weighty=1;
        c.weightx=1;
        c.gridx=0;
        c.gridy=0;
        this.add(nextTurn,c);
        c.gridx=1;
        c.gridy=0;
        this.add(saveGame,c);
        c.gridx=2;
        c.gridy=0;
        this.add(loadGame,c);
    }
}
