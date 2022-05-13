package pl.student;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;

public class World {
    private int height;
    private int width;
    private int turn = 0;
    private int key;
    private ArrayList<Organism> organismsArrayList;
    private ArrayList<String> journal;
    //TODO Array of organism or some other 2D data structure

    public World(int height, int width) {
        this.height = height;
        this.width = width;
        this.organismsArrayList = new ArrayList<>();
        this.journal = new ArrayList<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTurn() {
        return turn;
    }

    public int getKey() {
        return key;
    }

    public ArrayList<Organism> getOrganismsArrayList() {
        return organismsArrayList;
    }

    public ArrayList<String> getJournal() {
        return journal;
    }

    public void increaseTurn() {
        this.turn++;
    }

    public void setOrganismsArrayList(ArrayList<Organism> organismsArrayList) {
        this.organismsArrayList = organismsArrayList;
    }

    public void addOrganism(Organism org) {
        this.organismsArrayList.add(org);
    }

    public void addActivity(String activity) {
        this.journal.add(activity);
    }

    public void sortOrganism() {
        for(Organism org : this.getOrganismsArrayList()) {
            if(!org.getAlive()) {
                this.getOrganismsArrayList().remove(org);
            }
        }
        this.getOrganismsArrayList().sort(new OrganismComparator());
    }
    public Organism getOrganism(Point position) {
        for(Organism org : this.getOrganismsArrayList()) {
            if(org.getPosition() == position) {
                return org;
            }
        }
        return null;
    }
    public void showOrganismsList() {
        for(Organism org : this.getOrganismsArrayList()) {
            System.out.println(org.getName() + ": Initiative - "+org.getInitiative()+" Force - "+org.getForce()+" Age - "+org.getAge());
        }
    }
}
