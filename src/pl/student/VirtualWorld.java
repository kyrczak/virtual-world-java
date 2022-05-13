package pl.student;

import pl.student.*;
import pl.student.animals.*;
import pl.student.plants.*;

public class VirtualWorld {

    public static void main(String[] args) {
        World world = new World(10,10);
        world.addOrganism(new Wolf(new Point(4,4),world));
        world.addOrganism(new Wolf(new Point(5,5),world));
        world.addOrganism(new Wolf(new Point(1,9),world));
        world.addOrganism(new Sheep(new Point(9,7), world));
        world.addOrganism(new Sheep(new Point(7,8), world));
        world.addOrganism(new Hogweed(new Point(0,9),world));
        world.addOrganism(new Dandelion(new Point(2,2),world));
        //GUI game = new GUI();
        while(true) {
            world.nextTurn();
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
