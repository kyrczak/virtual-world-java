package pl.student.plants;

import pl.student.*;
import pl.student.Point;

import java.awt.*;
import java.util.Random;

public class Dandelion extends Plant {
    public Dandelion(Point position, World world) {
        super(position,world);
        this.force = 0;
    }
    public Dandelion(Point position, int force, int age, World world) {
        super(position, force, age, world);
    }

    @Override
    public char getSign() {
        return 'd';
    }

    @Override
    public String getName() {
        return "Dandelion";
    }

    @Override
    public Color getColor() {
        return new Color(0xfff203);
    }

    @Override
    public void createOrganism(Point position, World world) {
        this.getWorld().addOrganism(new Dandelion(position,world));
    }

    @Override
    public void grow() {
        int chance;
        for(int i = 0; i<3; i++) {
            chance = this.getWorld().getRand().nextInt(this.growthRate);
            if(chance == 0) {
                this.reproduce();
            }
        }
    }
}
