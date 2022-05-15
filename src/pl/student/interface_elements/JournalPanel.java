package pl.student.interface_elements;

import pl.student.Game;

import javax.swing.*;
import java.awt.*;

public class JournalPanel extends JPanel {
    private Game game;
    private JTextArea journal;
    private JLabel journalTitle;

    public JournalPanel(Game game) {
        this.game = game;
        journalTitle = new JLabel("Journal:");
        journal = new JTextArea();
        journal.setEditable(false);
        journal.setLineWrap(true);
        journal.setFocusable(false);
        JScrollPane scrollPane = new JScrollPane(journal,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setLayout(new BorderLayout());
        this.add(journalTitle,BorderLayout.NORTH);
        this.add(scrollPane,BorderLayout.CENTER);
    }

    public void setJournalActivities() {
        journal.setText("");
        for(String activity : this.game.getWorld().getJournal()) {
            journal.append(activity + "\n");
        }
        this.game.getWorld().getJournal().clear();
    }
}
