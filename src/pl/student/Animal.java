package pl.student;

import java.util.Random;

public class Animal extends Organism{
    private int reproductionChance = 6;
    public Animal(Point position, int force, int initiative,int age, World world) {
        super(position, force, initiative,age, world);
    }
    public Animal(Point position, World world) {
        super(position,world);
    }
    @Override
    public boolean isSameSpecies(Organism other) {
        return false;
    }

    @Override
    public void action() {
        this.move();
        this.setAge(this.getAge()+1);
    }

    @Override
    public boolean collision(Organism attacker) {
        return this.fight(attacker);
    }

    @Override
    public void createOrganism(Point position, World world) {

    }

    public void move() {
        int direction = this.getWorld().getRand().nextInt(4);
        Point proposedPosition = this.proposedPosition(direction);
        if(this.isInBounds(proposedPosition)) {
            Organism org = this.getWorld().getOrganism(proposedPosition);
            if (org == null) {
                this.setPosition(proposedPosition);
            }
            else if(this.isSameSpecies(org)) {
                if(this.getWorld().getRand().nextInt(this.reproductionChance)==0) {
                    this.reproduce();
                }
            }
            else {
                if(org.collision(this)) {
                    this.getWorld().addActivity("Organism "+org.getName()+" was killed by " + this.getName());
                    this.setPosition(proposedPosition);
                }
            }
        }
    }

    public boolean fight(Organism attacker) {
        if(this.getForce()<attacker.getForce()) {
            this.setAlive(false);
            return true;
        }
        else if(this.getForce() == attacker.getForce()) {
            this.setAlive(false);
            return true;
        }
        else {
            attacker.setAlive(false);
            return false;
        }
    }


}
