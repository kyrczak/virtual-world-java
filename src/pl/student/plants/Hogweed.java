package pl.student.plants;

import com.sun.org.apache.xpath.internal.operations.Or;
import pl.student.*;
import pl.student.Point;

import java.awt.*;

public class Hogweed extends Plant {
    public Hogweed(Point position, World world) {
        super(position,world);
        this.force = 10;
    }
    public Hogweed(Point position, int force, int age, World world) {
        super(position, force, age, world);
    }

    @Override
    public char getSign() {
        return 'h';
    }

    @Override
    public String getName() {
        return "Hogweed";
    }

    @Override
    public Color getColor() {
        return new Color(0xff00ff);
    }

    @Override
    public void createOrganism(Point position, World world) {
        this.getWorld().addOrganism(new Hogweed(position,world));
    }

    @Override
    public void action() {
        this.killAround();
        this.grow();
        this.setAge(this.getAge()+1);
    }

    @Override
    public boolean collision(Organism attacker) {
        attacker.setAlive(false);
        return false;
    }
    public void killAround() {
        Point[] positionsAround = {
            new Point(this.getPosition().getX(),this.getPosition().getY()+1),
            new Point(this.getPosition().getX(),this.getPosition().getY()-1),
            new Point(this.getPosition().getX()+1,this.getPosition().getY()),
            new Point(this.getPosition().getX()-1,this.getPosition().getY())
        };
        for(Point position : positionsAround) {
            if(this.getWorld().getOrganism(position)!=null && this.isInBounds(position)) {
                if(this.isAnimal(this.getWorld().getOrganism(position))) {
                    this.getWorld().getOrganism(position).setAlive(false);
                }
            }
        }
    }
    public boolean isAnimal(Organism org) {
        return org instanceof Animal;
    }
}
