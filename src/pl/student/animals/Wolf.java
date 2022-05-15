package pl.student.animals;

import pl.student.*;
import pl.student.Point;

import java.awt.*;

public class Wolf extends Animal{
    public Wolf(Point position, World world) {
        super(position,world);
        this.force = 9;
        this.initiative = 5;
    }
    public Wolf(Point position, int force, int intiative, int age, World world) {
        super(position, force,intiative,age, world);
    }

    @Override
    public char getSign() {
        return 'W';
    }

    @Override
    public String getName() {
        return "Wolf";
    }

    @Override
    public Color getColor() {
        return new Color(0x616161);
    }

    @Override
    public boolean isSameSpecies(Organism other) {
        return other instanceof Wolf;
    }

    @Override
    public void createOrganism(Point position, World world) {
        this.getWorld().addOrganism(new Wolf(position,world));
    }
}
