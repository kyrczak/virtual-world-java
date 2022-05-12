package pl.student;

import java.util.ArrayList;
import java.util.Random;

public abstract class Organism {
    protected Point position;
    protected int force;
    protected int initiative;
    protected int age = 0;
    protected boolean isAlive = true;
    protected char sign;
    protected String name;
    protected World world;

    public Organism(Point position, int force, int initiative, char sign, String name, World world) {
        this.position = position;
        this.force = force;
        this.initiative = initiative;
        this.sign = sign;
        this.name = name;
        this.world = world;
    }
    public abstract void action();
    public abstract boolean collision();
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

    public void reproduce() {
        ArrayList<Point> emptyPositions = freePositions();
        Point chosenPosition = new Point();
        if(chooseFreePosition(chosenPosition,emptyPositions)) {
            this.createOrganism(chosenPosition,this.getWorld());
        }
    }

    public boolean chooseFreePosition(Point position,ArrayList<Point> pointArrayList) {
        if(!pointArrayList.isEmpty()) {
            Random rand = new Random();
            int randomNumber = rand.nextInt(pointArrayList.size());
            position.setX(pointArrayList.get(randomNumber).getX());
            position.setY(pointArrayList.get(randomNumber).getY());
            return true;
        }
        else {
            return false;
        }
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
            if(this.getWorld().getOrganism(step) == false && this.isInBounds(step)) {
                freePositionsArray.add(step);
            }
        }

        return freePositionsArray;
    }
}
