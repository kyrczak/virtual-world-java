package pl.student.interface_elements;

import pl.student.Point;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Tile extends JButton {
    private Point position;

    public Tile(Point position) {
        this.position = position;
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(new Color(255,255,255));
        this.setFocusable(false);
        this.setText(" ");
    }

    public Point getPosition() {
        return position;
    }
}
