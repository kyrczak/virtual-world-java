package pl.student;

import java.util.Random;

public class Plant extends Organism{
    protected int growthRate = 50;
    public Plant(Point position, int force,int age, World world) {
        super(position, force, 0,age, world);
    }
    public Plant(Point position, World world) {
        super(position,world);
    }

    @Override
    public void action() {
        this.grow();
        this.setAge(this.getAge()+1);
    }

    @Override
    public boolean collision(Organism attacker) {
        return this.fight(attacker);
    }

    @Override
    public boolean isSameSpecies(Organism other) {
        return false;
    }

    @Override
    public void createOrganism(Point position, World world) {

    }
    public void grow() {
        Random rand = new Random();
        int chance = rand.nextInt(growthRate);
        if(chance == 0) {
            this.reproduce();
        }
    }
    public boolean fight(Organism attacker) {
        this.setAlive(false);
        return true;
    }
}
