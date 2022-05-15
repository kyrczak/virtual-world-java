package pl.student.plants;

import pl.student.*;
import pl.student.Point;

import java.awt.*;

public class Grass extends Plant {
    public Grass(Point position, World world) {
        super(position,world);
        this.force = 0;
    }
    public Grass(Point position, int force, int age, World world) {
        super(position, force, age, world);
    }

    @Override
    public char getSign() {
        return 'g';
    }

    @Override
    public String getName() {
        return "Grass";
    }

    @Override
    public Color getColor() {
        return new Color(0x00ff00);
    }

    @Override
    public void createOrganism(Point position, World world) {
        this.getWorld().addOrganism(new Grass(position,world));
    }
}
