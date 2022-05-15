package pl.student.animals;

import pl.student.*;
import pl.student.Point;

import java.awt.*;
import java.util.Random;

public class Antelope extends Animal {
    public Antelope(Point position, World world) {
        super(position,world);
        this.force = 4;
        this.initiative = 4;
    }
    public Antelope(Point position, int force, int intiative, int age, World world) {
        super(position, force,intiative,age, world);
    }

    @Override
    public char getSign() {
        return 'A';
    }

    @Override
    public String getName() {
        return "Antelope";
    }

    @Override
    public Color getColor() {
        return new Color(0x963700);
    }

    @Override
    public boolean isSameSpecies(Organism other) {
        return other instanceof Antelope;
    }

    @Override
    public void createOrganism(Point position, World world) {
        this.getWorld().addOrganism(new Antelope(position,world));
    }

    @Override
    public void move() {
        int direction = this.getWorld().getRand().nextInt(8);
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

    @Override
    public Point proposedPosition(int direction) {
        Point[] steps = {
                new Point(0,-1),
                new Point(0,1),
                new Point(1,0),
                new Point(-1,0),
                new Point(0,2),
                new Point(0,-2),
                new Point(2,0),
                new Point(-2,0)
        };
        return new Point(this.getPosition().getX()+steps[direction].getX(),this.getPosition().getY()+steps[direction].getY());
    }

    @Override
    public boolean collision(Organism attacker) {
        boolean attack = this.getWorld().getRand().nextBoolean();
        if(attack) {
            return this.fight(attacker);
        }
        else {
            this.getWorld().addActivity("Antelope escaped the fight");
            return false;
        }
    }
}
