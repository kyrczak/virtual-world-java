package pl.student.plants;

import pl.student.*;

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
    public void createOrganism(Point position, World world) {
        this.getWorld().addOrganism(new Grass(position,world));
    }
}
