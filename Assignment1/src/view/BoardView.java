package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

// Cite: http://stackoverflow.com/questions/15870608/creating-a-draw-rectangle-filled-with-black-color-function-in-java-for-a-grid

public class BoardView extends JFrame {
    public BoardView() {
        super("Game Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Squares squares = new Squares();
        getContentPane().add(squares);
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                squares.addSquare(x * 75, y * 75, 75, 75);
            }
        }
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new BoardView();
    }

}

class Squares extends JPanel {
    private static final int PREF_W = 700;
    private static final int PREF_H = PREF_W;
    private List<Rectangle> squares = new ArrayList<Rectangle>();

    public void addSquare(int x, int y, int width, int height) {
        Rectangle rect = new Rectangle(x, y, width, height);
        squares.add(rect);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.BLACK);
        int i = 0;
        for (Rectangle rect : squares) {
            invertSquareColor(g);
            g2.fillRect(rect.x, rect.y, rect.width, rect.height);
            g2.draw(rect);
            i++;
            if (i % 8 == 0) {
                invertSquareColor(g);
            }
        }
    }

    private void invertSquareColor(Graphics g) {
        if (g.getColor() == Color.BLACK) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.BLACK);
        }
    }

}