package pl.student;

import pl.student.animals.*;
import pl.student.plants.*;
import pl.student.interface_elements.Tile;

import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Game {
    private GUI gui;
    private World world;

    public Game() {
        this.world = new World(25,25);
        this.world.randNewOrganisms();
        this.gui = new GUI(this);
        this.drawGameBoard();
    }

    public Game(World world) {
        this.world = world;
        this.world.addAwaitingOrganisms();
        this.gui = new GUI(this);
        this.drawGameBoard();
    }

    public void saveGame() {
        JFrame input = new JFrame();
        JTextField textField = new JTextField(30);
        input.add(textField);
        JButton saveButton = new JButton("Save");
        input.setLayout(new FlowLayout(FlowLayout.CENTER));
        input.add(saveButton);
        input.setSize(500,100);
        input.setVisible(true);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                String save = "";
                save+=(world.getName()+" "+world.getHeight()+" "+world.getWidth()+" "+world.getOrganismsArrayList().size()+"\n");
                for(Organism org : world.getOrganismsArrayList()) {
                    if(org instanceof Human) {
                        save+=(org.getName()+" "+org.getPosition().getX()+" "+org.getPosition().getY()+" "+org.getForce()+" "+org.getInitiative()+" "+org.getAge()+" "+((Human) org).getAbilityCooldown()+" "+((Human) org).getAbilityTime())+" "+((Human) org).getIsAbilityActive()+"\n";
                    }
                    else {
                        save+=(org.getName()+" "+org.getPosition().getX()+" "+org.getPosition().getY()+" "+org.getForce()+" "+org.getInitiative()+" "+org.getAge()+"\n");
                    }
                }
                System.out.print(save);
                try {
                    String path = ".\\src\\pl\\student\\saves\\"+text+".txt";
                    File saveFile = new File(path);
                    saveFile.createNewFile();
                    BufferedWriter writer = new BufferedWriter(new FileWriter(path));
                    writer.write(save);
                    writer.close();
                }
                catch (IOException o) {
                    o.printStackTrace();
                }
                input.setVisible(false);
                input.dispose();
            }
        });
    }

    public void gameNextTurn() {
        this.getWorld().nextTurn();
        this.clearGameBoard();
        this.drawGameBoard();
    }

    public void loadGame() {
        JFileChooser chooseFileToLoad = new JFileChooser();
        int response = chooseFileToLoad.showOpenDialog(null);
        if(response == JFileChooser.APPROVE_OPTION) {
            File loadFile = new File(chooseFileToLoad.getSelectedFile().getAbsolutePath());
            try {
                BufferedReader reader = new BufferedReader(new FileReader(loadFile));
                String line = reader.readLine();
                String[] elements = line.split(" ");
                int height = Integer.parseInt(elements[1]);
                int width = Integer.parseInt(elements[2]);
                int size = Integer.parseInt(elements[3]);
                World newWorld = new World(height,width);
                for(int i = 0; i<size; i++) {
                    line = reader.readLine();
                    elements = line.split(" ");
                    switch(elements[0]) {
                        case "Human":
                            newWorld.addOrganism(new Human(new Point(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//point
                                    newWorld,
                                    Integer.parseInt(elements[3]), //force
                                    Integer.parseInt(elements[4]), //initiative
                                    Integer.parseInt(elements[5]), //age
                                    Integer.parseInt(elements[6]), //abilityCooldown
                                    Integer.parseInt(elements[6]), //abilityTime
                                    Boolean.parseBoolean(elements[7]))); //isAbilityActive
                            break;
                        case "Fox":
                            newWorld.addOrganism(new Fox(new Point(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),
                                    Integer.parseInt(elements[3]),
                                    Integer.parseInt(elements[4]),
                                    Integer.parseInt(elements[5]),
                                    newWorld));
                            break;
                        case "Antelope":
                            newWorld.addOrganism(new Antelope(new Point(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),
                                    Integer.parseInt(elements[3]),
                                    Integer.parseInt(elements[4]),
                                    Integer.parseInt(elements[5]),
                                    newWorld));
                            break;
                        case "Sheep":
                            newWorld.addOrganism(new Sheep(new Point(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),
                                    Integer.parseInt(elements[3]),
                                    Integer.parseInt(elements[4]),
                                    Integer.parseInt(elements[5]),
                                    newWorld));
                            break;
                        case "Wolf":
                            newWorld.addOrganism(new Wolf(new Point(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),
                                    Integer.parseInt(elements[3]),
                                    Integer.parseInt(elements[4]),
                                    Integer.parseInt(elements[5]),
                                    newWorld));
                            break;
                        case "Turtle":
                            newWorld.addOrganism(new Turtle(new Point(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),
                                    Integer.parseInt(elements[3]),
                                    Integer.parseInt(elements[4]),
                                    Integer.parseInt(elements[5]),
                                    newWorld));
                            break;
                        case "Belladonna":
                            newWorld.addOrganism(new Belladonna(new Point(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),
                                    Integer.parseInt(elements[3]),
                                    Integer.parseInt(elements[5]),
                                    newWorld));
                            break;
                        case "Dandelion":
                            newWorld.addOrganism(new Dandelion(new Point(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),
                                    Integer.parseInt(elements[3]),
                                    Integer.parseInt(elements[5]),
                                    newWorld));
                            break;
                        case "Grass":
                            newWorld.addOrganism(new Grass(new Point(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),
                                    Integer.parseInt(elements[3]),
                                    Integer.parseInt(elements[5]),
                                    newWorld));
                            break;
                        case "Guarana":
                            newWorld.addOrganism(new Guarana(new Point(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),
                                    Integer.parseInt(elements[3]),
                                    Integer.parseInt(elements[5]),
                                    newWorld));
                            break;
                        case "Hogweed":
                            newWorld.addOrganism(new Hogweed(new Point(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),
                                    Integer.parseInt(elements[3]),
                                    Integer.parseInt(elements[5]),
                                    newWorld));
                            break;
                    }
                }
                newWorld.addAwaitingOrganisms();
                this.setWorld(newWorld);
                this.clearGameBoard();
                this.drawGameBoard();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Load game");
    }

    public void drawGameBoard() {
        for(Component tile: this.getGui().getTiles()) {
            if(tile instanceof Tile) {
                Point tileCoords = ((Tile) tile).getPosition();
                Organism org = this.getWorld().getOrganism(tileCoords);
                if(org!=null) {
                    Color orgColor = org.getColor();
                    tile.setBackground(orgColor);
                }
            }
        }
        this.getGui().getJournalPanel().setJournalActivities();
        this.getWorld().getJournal().clear();
    }

    public void clearGameBoard() {
        for(Component tile: this.getGui().getTiles()) {
            if(tile instanceof Tile) {
                Point tileCoords = ((Tile) tile).getPosition();
                Organism org = this.getWorld().getOrganism(tileCoords);
                if(org==null) {
                    tile.setBackground(new Color(0xffffff));
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
