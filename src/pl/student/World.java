package pl.student;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;
import java.util.Random;

public class World {
    private int height;
    private int width;
    private int turn = 0;
    private int key;
    private Random rand;
    public char[][] plane;
    private ArrayList<Organism> organismsArrayList;
    private ArrayList<Organism> organismsToAdd;
    private ArrayList<Organism> organismsToRemove;
    private ArrayList<String> journal;
    //TODO Array of organism or some other 2D data structure

    public World(int height, int width) {
        this.height = height;
        this.width = width;
        this.plane = new char[height][width];
        this.organismsArrayList = new ArrayList<>();
        this.organismsToAdd = new ArrayList<>();
        this.organismsToRemove = new ArrayList<>();
        this.journal = new ArrayList<>();
        this.rand = new Random();
    }

    public World(int height, int width, ArrayList<Organism> organismsArrayList) {
        this.height = height;
        this.width = width;
        this.plane = new char[height][width];
        this.organismsArrayList = organismsArrayList;
        this.organismsToAdd = new ArrayList<>();
        this.organismsToRemove = new ArrayList<>();
        this.journal = new ArrayList<>();
        this.rand = new Random();
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

    public String getName() {
        return "World";
    }

    public ArrayList<Organism> getOrganismsArrayList() {
        return organismsArrayList;
    }

    public ArrayList<String> getJournal() {
        return journal;
    }

    public Random getRand() {
        return rand;
    }

    public void increaseTurn() {
        this.turn++;
    }

    public void setOrganismsArrayList(ArrayList<Organism> organismsArrayList) {
        this.organismsArrayList = organismsArrayList;
    }

    public void addOrganism(Organism org) {
        for(Organism organismsAdded : this.organismsToAdd) {
            if(organismsAdded.getPosition().getX() == org.getPosition().getX() && organismsAdded.getPosition().getY() == org.getPosition().getY()) {
                return;
            }
        }
        for(Organism organismsAdded : this.getOrganismsArrayList() ) {
            if(organismsAdded.getPosition().getX() == org.getPosition().getX() && organismsAdded.getPosition().getY() == org.getPosition().getY()) {
                return;
            }
        }
        this.organismsToAdd.add(org);
    }

    public void addActivity(String activity) {
        this.journal.add(activity);
    }

    public void sortOrganism() {
        for(Organism org : this.getOrganismsArrayList()) {
            if(!org.getAlive()) {
                this.organismsToRemove.add(org);
            }
        }
        if(!this.organismsToRemove.isEmpty()) {
            for (Organism org : this.organismsToRemove) {
                this.organismsArrayList.remove(org);
            }
            this.organismsToRemove.clear();
        }
        if(!this.organismsToAdd.isEmpty()) {
            for (Organism org : this.organismsToAdd) {
                this.organismsArrayList.add(org);
            }
            this.organismsToAdd.clear();
        }
        this.getOrganismsArrayList().sort(new OrganismComparator());
    }

    public Organism getOrganism(Point position) {
        for(Organism org : this.getOrganismsArrayList()) {
            if(org.getPosition().getX() == position.getX() && org.getPosition().getY() == position.getY() ) {
                return org;
            }
        }
        return null;
    }

    public void showOrganismsList() {
        for(Organism org : this.getOrganismsArrayList()) {
            System.out.println(org.getName() + ": Initiative - "+org.getInitiative()+" Force - "+org.getForce()+" Age - "+org.getAge() + " Position - x = "+org.getPosition().getX()+"  y = "+org.getPosition().getY());
        }
    }

    public void drawGame() {
        this.clearBoard();
        for(Organism org: this.getOrganismsArrayList()) {
            if(org.getAlive()) {
                org.draw();
            }
        }
        //this.showOrganismsList();
        this.drawBoard();
        for(String activity : this.getJournal()) {
            System.out.println(activity);
        }
        this.getJournal().clear();
        System.out.println("\n");
    }

    public void nextTurn() {
        this.sortOrganism();
        for(Organism org : this.getOrganismsArrayList()) {
            if (org.getAlive()) {
                org.action();
            }
        }
        this.drawGame();
        this.increaseTurn();
    }

    public void clearBoard() {
        for(int i = 0; i<height; i++) {
            for(int j = 0; j<width; j++) {
                this.plane[i][j]='_';
            }
        }
    }

    public void drawBoard() {
        for(int i = 0; i<height; i++) {
            for(int j = 0; j<width; j++) {
                System.out.print(this.plane[i][j]);
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }

}
