package pl.student.plants;

import pl.student.Organism;
import pl.student.Plant;
import pl.student.Point;
import pl.student.World;

public class Belladonna extends Plant {
    public Belladonna(Point position, World world) {
        super(position,world);
        this.force = 99;
    }
    public Belladonna(Point position, int force, int age, World world) {
        super(position, force, age, world);
    }

    @Override
    public char getSign() {
        return 'b';
    }

    @Override
    public String getName() {
        return "Belladonna";
    }

    @Override
    public void createOrganism(Point position, World world) {
        this.getWorld().addOrganism(new Belladonna(position,world));
    }

    @Override
    public boolean collision(Organism attacker) {
        attacker.setAlive(false);
        this.getWorld().addActivity("Belladonna killed "+attacker.getName());
        return this.fight(attacker);
    }
}
