package pl.student;

import pl.student.*;
import pl.student.animals.*;

public class VirtualWorld {

    public static void main(String[] args) {
        World world = new World(20,20);
        world.addOrganism(new Wolf(new Point(10,10),world));
        world.addOrganism(new Wolf(new Point(12,15),world));
        world.addOrganism(new Turtle(new Point(2,5),world));
        world.addOrganism(new Sheep(new Point(10,18),world));
        world.sortOrganism();
        world.showOrganismsList();
        GUI game = new GUI();
    }
}
