package pl.student.animals;

import pl.student.*;
import pl.student.Point;

import java.awt.*;
import java.util.ArrayList;

public class Human extends Animal {
    private boolean isAbilityActive = false;
    private int abilityCooldown = 5;
    private int abilityTime = 5;
    public Human(Point position, World world) {
        super(position,world);
        this.force = 5;
        this.initiative = 4;
        this.world.setHumanAlive(true);
    }
    public Human(Point position, World world,int force, int initiative, int age, int abilityCooldown, int abilityTime, boolean isAbilityActive) {
        super(position,force,initiative,age,world);
        this.abilityCooldown = abilityCooldown;
        this.abilityTime = abilityTime;
        this.isAbilityActive = isAbilityActive;
        this.world.setHumanAlive(true);
    }

    public void setAbilityActive(boolean abilityActive) {
        isAbilityActive = abilityActive;
    }

    public void setAbilityCooldown(int abilityCooldown) {
        this.abilityCooldown = abilityCooldown;
    }

    public void setAbilityTime(int abilityTime) {
        this.abilityTime = abilityTime;
    }

    public int getAbilityCooldown() {
        return abilityCooldown;
    }

    public int getAbilityTime() {
        return abilityTime;
    }

    public boolean getIsAbilityActive() {
        return this.isAbilityActive;
    }
    @Override
    public char getSign() {
        return 'H';
    }

    @Override
    public String getName() {
        return "Human";
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }

    @Override
    public void move() {
        Keys key = this.getWorld().getKey();
        if(key!=Keys.DEFAULT && key!= Keys.KEY_F) {
            Point proposedPosition = this.proposedPosition(key.ordinal());
            if(this.isInBounds(proposedPosition)) {
                Organism org = this.getWorld().getOrganism(proposedPosition);
                if(org==null) {
                    this.setPosition(proposedPosition);
                }
                else if(org.collision(this)){
                    this.getWorld().addActivity("Organism "+org.getName() + " was killed by "+ this.getName());
                    this.setPosition(proposedPosition);
                }
            }
        }
        else if(key == Keys.KEY_F) {
            if(!this.getIsAbilityActive() && this.getAbilityCooldown()==5) {
                this.setAbilityActive(true);
                this.getWorld().addActivity("Human used his special ability");
            }
        }
        this.abilityTimeManagment();

    }

    @Override
    public boolean collision(Organism attacker) {
        if(!this.getIsAbilityActive()) {
            return this.fight(attacker);
        }
        else {
            this.getWorld().addActivity("Alzur's shield deflected the attack from "+attacker.getName());
            ArrayList<Point> emptyPositions = freePositions();
            Point chosenPosition = new Point();
            if(chooseFreePosition(chosenPosition,emptyPositions)) {
                attacker.setPosition(chosenPosition);
            }
            return false;
        }
    }
    public void abilityTimeManagment() {
        if(this.getIsAbilityActive()) {
            this.setAbilityTime(this.getAbilityTime()-1);
            if(this.getAbilityTime() == 0) {
                this.setAbilityTime(5);
                this.setAbilityActive(false);
                this.setAbilityCooldown(this.getAbilityCooldown()-1);
            }
        }
        else if(!this.getIsAbilityActive() && this.getAbilityCooldown()<5) {
            this.setAbilityCooldown(this.getAbilityCooldown()-1);
            if(this.getAbilityCooldown() == 0) {
                this.setAbilityCooldown(5);
            }
        }
    }
}
