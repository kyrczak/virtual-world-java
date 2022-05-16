package pl.student;
import pl.student.interface_elements.GameBoard;
import pl.student.interface_elements.JournalPanel;
import pl.student.interface_elements.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI extends JFrame implements KeyListener {
    private Menu menu;
    private GameBoard board;
    private Game game;
    private JournalPanel journalPanel;

    private int width = 900;
    private int height = 1000;
    public GUI(Game game) {
        this.game = game;
        this.setSize(width,height);
        this.setTitle("Virtual World");
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
        gbl.columnWeights = new double[]{1};
        gbl.rowWeights = new double[] {1,2,1};
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.menu = new Menu(game);
        this.board = new GameBoard(game);
        this.journalPanel = new JournalPanel(game);
        this.board.setPreferredSize(new Dimension(600,600));

        c.gridx=0;
        c.gridy=0;
        this.add(menu,c);
        c.gridx=0;
        c.gridy=1;
        c.fill=GridBagConstraints.NONE;
        this.add(board,c);
        c.gridx=0;
        c.gridy=2;
        c.fill=GridBagConstraints.BOTH;
        this.add(journalPanel,c);

        //TODO Journal
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(true);
        this.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.getGame().getWorld().setKey(Keys.UP);
                this.getGame().gameNextTurn();
                break;
            case KeyEvent.VK_DOWN:
                this.getGame().getWorld().setKey(Keys.DOWN);
                this.getGame().gameNextTurn();
                break;
            case KeyEvent.VK_LEFT:
                this.getGame().getWorld().setKey(Keys.LEFT);
                this.getGame().gameNextTurn();
                break;
            case KeyEvent.VK_RIGHT:
                this.getGame().getWorld().setKey(Keys.RIGHT);
                this.getGame().gameNextTurn();
                break;
            case KeyEvent.VK_F:
                this.getGame().getWorld().setKey(Keys.KEY_F);
                this.getGame().gameNextTurn();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public JournalPanel getJournalPanel() {
        return journalPanel;
    }

    public Component[] getTiles() {
        return this.board.getComponents();
    }

    public Game getGame() {
        return game;
    }
}
