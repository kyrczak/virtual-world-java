package pl.student;

import java.util.ArrayList;

public class World {
    private int height;
    private int width;
    private ArrayList<Organism> organismsArrayList;
    private ArrayList<String> journal;

    public World(int height, int width) {
        this.height = height;
        this.width = width;
        this.organismsArrayList = new ArrayList<>();
        this.journal = new ArrayList<>();
    }
}
