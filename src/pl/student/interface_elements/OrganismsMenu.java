package pl.student.interface_elements;

import pl.student.Game;
import pl.student.Point;
import pl.student.animals.*;
import pl.student.plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrganismsMenu extends JFrame implements ActionListener{
    private JComboBox organismsList;
    private JButton addButton;
    private Point position;
    private Game game;
    public OrganismsMenu(Point position, Game game) {
        this.position = position;
        this.game = game;
        this.setLayout(new BorderLayout());
        this.setSize(250,150);
        String[] organisms = {
                "Wolf",
                "Sheep",
                "Fox",
                "Turtle",
                "Antelope",
                "Human",
                "Grass",
                "Dandelion",
                "Guarana",
                "Belladonna",
                "Hogweed"
        };
        this.organismsList = new JComboBox(organisms);
        this.addButton = new JButton("Add");
        this.addButton.setFocusable(false);
        this.addButton.addActionListener(this);
        this.add(this.organismsList,BorderLayout.NORTH);
        this.add(this.addButton,BorderLayout.SOUTH);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            switch (String.valueOf(this.organismsList.getSelectedItem())) {
                case "Wolf":
                    this.game.getWorld().addOrganism(new Wolf(this.position,this.game.getWorld()));
                    break;
                case "Sheep":
                    this.game.getWorld().addOrganism(new Sheep(this.position,this.game.getWorld()));
                    break;
                case "Fox":
                    this.game.getWorld().addOrganism(new Fox(this.position,this.game.getWorld()));
                    break;
                case "Turtle":
                    this.game.getWorld().addOrganism(new Turtle(this.position,this.game.getWorld()));
                    break;
                case "Antelope":
                    this.game.getWorld().addOrganism(new Antelope(this.position,this.game.getWorld()));
                    break;
                case "Human":
                    if(!this.game.getWorld().getHumanAlive()) {
                        this.game.getWorld().addOrganism(new Human(this.position, this.game.getWorld()));
                    }
                    break;
                case "Grass":
                    this.game.getWorld().addOrganism(new Grass(this.position,this.game.getWorld()));
                    break;
                case "Dandelion":
                    this.game.getWorld().addOrganism(new Dandelion(this.position,this.game.getWorld()));
                    break;
                case "Guarana":
                    this.game.getWorld().addOrganism(new Guarana(this.position,this.game.getWorld()));
                    break;
                case "Belladonna":
                    this.game.getWorld().addOrganism(new Belladonna(this.position,this.game.getWorld()));
                    break;
                case "Hogweed":
                    this.game.getWorld().addOrganism(new Hogweed(this.position,this.game.getWorld()));
                    break;
                default:
                    break;
            }
            this.game.getWorld().addAwaitingOrganisms();
            this.game.drawGameBoard();
            this.setVisible(false);
            this.dispose();
    }
}
