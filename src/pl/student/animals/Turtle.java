package pl.student.animals;

import pl.student.*;

import java.util.Random;

public class Turtle extends Animal {
    public Turtle(Point position, World world) {
        super(position,world);
        this.force = 2;
        this.initiative = 1;
    }
    public Turtle(Point position, int force, int intiative, int age, World world) {
        super(position, force,intiative,age, world);
    }

    @Override
    public char getSign() {
        return 'T';
    }

    @Override
    public String getName() {
        return "Turtle";
    }

    @Override
    public boolean isSameSpecies(Organism other) {
        return other instanceof Turtle;
    }

    @Override
    public void createOrganism(Point position, World world) {
        this.getWorld().addOrganism(new Turtle(position,world));
    }

    @Override
    public void move() {
        int direction = this.getWorld().getRand().nextInt(16);
        if(direction<4) {
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
                    if(org.collision(this)) {
                        this.getWorld().addActivity("Organism "+org.getName()+" was killed by " + this.getName());
                        this.setPosition(proposedPosition);
                    }
                }
            }
        }
    }

    @Override
    public boolean collision(Organism attacker) {
        if(attacker.getForce() < 5) {
            this.getWorld().addActivity("Turtle blocked attack from " + attacker.getName());
            return false;
        }
        else {
            return this.fight(attacker);
        }
    }
}
