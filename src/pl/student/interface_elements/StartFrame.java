package pl.student.interface_elements;

import pl.student.Game;

import javax.swing.*;
import java.awt.*;

public class StartFrame extends JFrame {
    private JTextField width;
    private JTextField height;
    private JButton newGame;
    private JButton loadGame;
    public StartFrame() {
        this.setTitle("Patryk Korczak - 188618 - Informatyka");
        this.width = new JTextField();
        this.height = new JTextField();
        this.newGame = new JButton("New game");
        this.loadGame = new JButton("Load game");
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        this.newGame.addActionListener(e -> {
            if(!width.getText().isEmpty() && !height.getText().isEmpty()) {
                try {
                    new Game(Integer.parseInt(width.getText()), Integer.parseInt(height.getText()));
                    setVisible(false);
                    dispose();
                }
                catch(NumberFormatException n) {
                    JFrame errorFrame = new JFrame();
                    errorFrame.setSize(200,200);
                    errorFrame.setLayout(new FlowLayout());
                    errorFrame.add(new JLabel("Please provide numbers only"));
                    errorFrame.setVisible(true);
                }
            }
        });
        this.loadGame.addActionListener(e -> {
                Game game = new Game();
                game.getGui().setVisible(false);
                game.loadGame();
                game.getGui().setVisible(true);
                this.setVisible(false);
                this.dispose();
        });
        this.setSize(700,400);
        c.gridx=0;
        c.gridy=0;
        c.weighty=1;
        c.weightx=0;
        this.add(new JLabel("Width"),c);
        c.gridy=1;
        this.add(width,c);
        c.gridy=2;
        this.add(new JLabel("Height"),c);
        c.gridy=3;
        this.add(height,c);
        c.gridy=4;
        this.add(newGame,c);
        c.gridy=5;
        this.add(loadGame,c);
        this.setVisible(true);
    }
}
