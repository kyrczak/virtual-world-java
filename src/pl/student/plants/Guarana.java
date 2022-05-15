package pl.student.plants;

import pl.student.Organism;
import pl.student.Plant;
import pl.student.Point;
import pl.student.World;

import java.awt.*;

public class Guarana extends Plant {
    private int fruitForce = 3;
    public Guarana(Point position, World world) {
        super(position,world);
        this.force = 0;
    }
    public Guarana(Point position, int force, int age, World world) {
        super(position, force, age, world);
    }

    @Override
    public char getSign() {
        return '@';
    }

    @Override
    public String getName() {
        return "Guarana";
    }

    @Override
    public Color getColor() {
        return new Color(0x651F45);
    }

    @Override
    public void createOrganism(Point position, World world) {
        this.getWorld().addOrganism(new Guarana(position,world));
    }

    @Override
    public boolean collision(Organism attacker) {
        attacker.setForce(attacker.getForce()+this.fruitForce);
        this.getWorld().addActivity("Organism "+ attacker.getName() + " ate guarana");
        return this.fight(attacker);
    }
}
