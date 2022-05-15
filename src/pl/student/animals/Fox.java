package pl.student.animals;

import pl.student.*;
import pl.student.Point;

import java.awt.*;
import java.util.Random;

public class Fox extends Animal {
    public Fox(Point position, World world) {
        super(position,world);
        this.force = 3;
        this.initiative = 7;
    }
    public Fox(Point position, int force, int initiative, int age, World world) {
        super(position, force,initiative,age, world);
    }

    @Override
    public char getSign() {
        return 'F';
    }

    @Override
    public String getName() {
        return "Fox";
    }

    @Override
    public Color getColor() {
        return new Color(0xDC2A00);
    }
    @Override
    public boolean isSameSpecies(Organism other) {
        return other instanceof Fox;
    }

    @Override
    public void createOrganism(Point position, World world) {
        this.getWorld().addOrganism(new Fox(position,world));
    }

    @Override
    public void move() {
        int direction = this.getWorld().getRand().nextInt(4);
        Point proposedPosition = this.proposedPosition(direction);
        if(this.isInBounds(proposedPosition)) {
            Organism org = this.getWorld().getOrganism(proposedPosition);
            if (org == null) {
                this.setPosition(proposedPosition);
            }
            else if(this.isSameSpecies(org)) {
                this.reproduce();
            }
            else {
                if(org.getForce() < this.getForce()) {
                    if (org.collision(this)) {
                        this.getWorld().addActivity("Organism " + org.getName() + " was killed by " + this.getName());
                        this.setPosition(proposedPosition);
                    }
                }
            }
        }
    }
}
