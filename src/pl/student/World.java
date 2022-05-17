package pl.student;

import pl.student.animals.*;
import pl.student.plants.*;

import java.util.ArrayList;
import java.util.Random;

public class World {
    private int height;
    private int width;
    private int turn = 0;
    private boolean isHumanAlive;
    private Keys key;
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
        this.key = Keys.DEFAULT;
        this.plane = new char[height][width];
        this.organismsArrayList = new ArrayList<>();
        this.organismsToAdd = new ArrayList<>();
        this.organismsToRemove = new ArrayList<>();
        this.journal = new ArrayList<>();
        this.rand = new Random();
        this.isHumanAlive = false;
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

    public Keys getKey() {
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

    public void addAwaitingOrganisms() {
        if(!this.organismsToAdd.isEmpty()) {
            for (Organism org : this.organismsToAdd) {
                this.organismsArrayList.add(org);
            }
            this.organismsToAdd.clear();
        }
        this.getOrganismsArrayList().sort(new OrganismComparator());
    }

    public void sortOrganism() {
        for(Organism org : this.getOrganismsArrayList()) {
            if(!org.getAlive()) {
                if(org instanceof Human) {
                    this.setHumanAlive(false);
                }
                this.organismsToRemove.add(org);
            }
        }
        if(!this.organismsToRemove.isEmpty()) {
            for (Organism org : this.organismsToRemove) {
                this.organismsArrayList.remove(org);
            }
            this.organismsToRemove.clear();
        }
        this.addAwaitingOrganisms();
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
        this.showOrganismsList();
        this.drawBoard();
        for(String activity : this.getJournal()) {
            System.out.println(activity);
        }
        this.getJournal().clear();
        System.out.println("\n");
    }

    public void nextTurn() {
        for(Organism org : this.getOrganismsArrayList()) {
            if (org.getAlive()) {
                org.action();
            }
        }
        //this.drawGame();
        this.sortOrganism();
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

    public void setKey(Keys key) {
        this.key = key;
    }

    public void randNewOrganisms() {
        int amountOfOrganisms = (this.getWidth() + this.getHeight());
        int x = this.getRand().nextInt(this.getWidth());
        int y = this.getRand().nextInt(this.getHeight());
        this.addOrganism(new Human(new Point(x,y),this));
        for(int i=0; i<amountOfOrganisms; i++) {
            x = this.getRand().nextInt(this.getWidth());
            y = this.getRand().nextInt(this.getHeight());
            if(this.getOrganism(new Point(x,y))==null) {
                int whichOrganism = this.getRand().nextInt(10);
                switch(whichOrganism) {
                    case 0: this.addOrganism(new Sheep(new Point(x,y),this));
                    case 1: this.addOrganism(new Wolf(new Point(x,y),this));
                    case 2: this.addOrganism(new Fox(new Point(x,y),this));
                    case 3: this.addOrganism(new Antelope(new Point(x,y),this));
                    case 4: this.addOrganism(new Turtle(new Point(x,y),this));
                    case 5: this.addOrganism(new Grass(new Point(x,y),this));
                    case 6: this.addOrganism(new Dandelion(new Point(x,y),this));
                    case 7: this.addOrganism(new Guarana(new Point(x,y),this));
                    case 8: this.addOrganism(new Hogweed(new Point(x,y),this));
                    case 9: this.addOrganism(new Belladonna(new Point(x,y),this));
                }
            }
        }
        this.sortOrganism();
    }

    public void setHumanAlive(boolean humanAlive) {
        isHumanAlive = humanAlive;
    }
    public boolean getHumanAlive() {
        return this.isHumanAlive;
    }
}
