package pl.student;

import java.util.ArrayList;
import java.util.Random;

public abstract class Organism {
    protected Point position;
    protected int force;
    protected int initiative;
    protected int age;
    protected boolean isAlive = true;
    protected World world;

    public Organism(Point position, int force, int initiative,int age, World world) {
        this.position = position;
        this.force = force;
        this.initiative = initiative;
        this.world = world;
        this.age = age;
    }
    public Organism(Point position, World world) {
        this.position = position;
        this.world = world;
        this.age = 0;
    }
    public abstract void action();
    public abstract boolean collision(Organism attacker);
    public abstract boolean isSameSpecies(Organism other);
    public abstract void createOrganism(Point position, World world);

    public Point proposedPosition(int direction) {
        Point[] steps = {
                new Point(0,-1),
                new Point(0,1),
                new Point(1,0),
                new Point(-1,0)
        };
        return new Point(this.getPosition().getX()+steps[direction].getX(),this.getPosition().getY()+steps[direction].getY());
    }

    public void draw() {
        this.getWorld().plane[this.getPosition().getY()][this.getPosition().getX()] = this.getSign();
    }

    public int getForce() {
        return force;
    }

    public int getInitiative() {
        return initiative;
    }

    public Point getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    public boolean getAlive() {
        return isAlive;
    }

    public World getWorld() {
        return world;
    }

    public String getName() {
        return "Organism";
    }

    public char getSign() {
        return '?';
    }

    public void reproduce() {
        ArrayList<Point> emptyPositions = freePositions();
        Point chosenPosition = new Point();
        if(chooseFreePosition(chosenPosition,emptyPositions)) {
            this.createOrganism(chosenPosition,this.getWorld());
        }
    }

    public boolean chooseFreePosition(Point position,ArrayList<Point> pointArrayList) {
        if(!pointArrayList.isEmpty()) {
            int randomNumber = this.getWorld().getRand().nextInt(pointArrayList.size());
            position.setX(pointArrayList.get(randomNumber).getX());
            position.setY(pointArrayList.get(randomNumber).getY());
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isInBounds(Point proposed) {
        return (proposed.getX() >= 0 && proposed.getY() >=0 && proposed.getX() < this.getWorld().getWidth() && proposed.getY() < this.getWorld().getHeight() );
    }

    public ArrayList<Point> freePositions() {
        ArrayList<Point> freePositionsArray = new ArrayList<>();
        Point[] steps = {
                new Point(this.getPosition().getX(),this.getPosition().getY()-1),
                new Point(this.getPosition().getX(),this.getPosition().getY()+1),
                new Point(this.getPosition().getX()+1,this.getPosition().getY()),
                new Point(this.getPosition().getX()-1,this.getPosition().getY())
        };

        for(Point step : steps) {
            if(this.getWorld().getOrganism(step) == null && this.isInBounds(step)) {
                freePositionsArray.add(step);
            }
        }

        return freePositionsArray;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
