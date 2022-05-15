package pl.student;

import pl.student.interface_elements.Tile;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.util.Date;

public class Game {
    private GUI gui;
    private World world;
    public Game() {
        this.world = new World(50,50);
        this.gui = new GUI(this);
    }
    public Game(World world) {
        this.world = world;
        this.gui = new GUI(this);
        this.drawGameBoard();
    }
    public void saveGame() {
        String save = "";
        save+=(world.getName()+" "+world.getHeight()+" "+world.getWidth()+"\n");
        for(Organism org : world.getOrganismsArrayList()) {
            save+=(org.getName()+" "+org.getPosition().getX()+" "+org.getPosition().getY()+" "+org.getForce()+" "+org.getAge()+"\n");
        }
        System.out.print(save);
        //TODO SaveToFile()
    }
    public void gameNextTurn() {
        this.drawGameBoard();
        this.getWorld().nextTurn();
        this.clearGameBoard();
        this.drawGameBoard();
    }
    public void loadGame() {
        System.out.println("Load game");
    }
    public void drawGameBoard() {
        for(Component tile: this.getGui().getTiles()) {
            if(tile instanceof Tile) {
                Point tileCoords = ((Tile) tile).getPosition();
                Organism org = this.getWorld().getOrganism(tileCoords);
                if(org!=null) {
                    String orgSign = org.getName();
                    ((JButton) tile).setText(orgSign);
                }
            }
        }
    }
    public void clearGameBoard() {
        for(Component tile: this.getGui().getTiles()) {
            if(tile instanceof Tile) {
                Point tileCoords = ((Tile) tile).getPosition();
                Organism org = this.getWorld().getOrganism(tileCoords);
                if(org==null) {
                    ((JButton) tile).setText(" ");
                }
            }
        }
    }
    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public GUI getGui() {
        return gui;
    }
}
