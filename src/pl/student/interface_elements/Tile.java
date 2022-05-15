package pl.student.interface_elements;

import pl.student.Game;
import pl.student.Point;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tile extends JButton implements ActionListener {
    private Game game;
    private Point position;

    public Tile(Point position, Game game) {
        this.game = game;
        this.position = position;
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(new Color(255,255,255));
        this.setFocusable(false);
        this.addActionListener(this);
        this.setText(" ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.getGame().getWorld().getOrganism(this.getPosition()) == null) {
            OrganismsMenu menu = new OrganismsMenu(this.getPosition(), this.getGame());
        }
    }

    public Point getPosition() {
        return position;
    }

    public Game getGame() {
        return game;
    }
}
