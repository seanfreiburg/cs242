package view;

import javax.swing.*;

// Cite: http://stackoverflow.com/questions/15870608/creating-a-draw-rectangle-filled-with-black-color-function-in-java-for-a-grid
// http://stackoverflow.com/questions/299495/java-swing-how-to-add-an-image-to-a-jpanel

/*
    Test Plan:

        Look at the board, if it looks correct, it's good

        Check on pieces move
        Check starting game
 */


public class BoardViewOld extends JFrame {

    // how far apart im putting the squares, I should probably make this a class variable at some point
    public static final int SQUARE_SPACING = 75;

    public BoardViewOld() {
        super("Chess Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Squares squares = new Squares();



        getContentPane().add(squares);
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                squares.addSquare(x * SQUARE_SPACING, y * SQUARE_SPACING, SQUARE_SPACING, SQUARE_SPACING);
            }
        }
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new BoardViewOld();
    }

}

