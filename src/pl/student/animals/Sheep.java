package pl.student.animals;

import pl.student.Animal;
import pl.student.Organism;
import pl.student.Point;
import pl.student.World;

public class Sheep extends Animal {
    public Sheep(Point position, World world) {
        super(position,world);
        this.force = 4;
        this.initiative = 4;
    }
    public Sheep(Point position, int force, int intiative, int age, World world) {
        super(position, force,intiative,age, world);
    }

    @Override
    public char getSign() {
        return 'S';
    }

    @Override
    public String getName() {
        return "Sheep";
    }

    @Override
    public boolean isSameSpecies(Organism other) {
        return other instanceof Sheep;
    }

    @Override
    public void createOrganism(Point position, World world) {
        this.getWorld().addOrganism(new Sheep(position,world));
    }
}
