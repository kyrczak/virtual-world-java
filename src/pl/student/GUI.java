package pl.student;
import javax.swing.*;

public class GUI {
    private JFrame window;
    public GUI() {
        this.window = new JFrame();
        this.window.setVisible(true);
        this.window.setSize(500,500);
        this.window.add(new JButton("organizm"));
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
