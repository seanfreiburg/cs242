package view;

import model.board.Board;
import model.board.NormalChessBoard;
import model.piece.Piece;
import model.player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

// Cite: http://stackoverflow.com/questions/15870608/creating-a-draw-rectangle-filled-with-black-color-function-in-java-for-a-grid
// http://stackoverflow.com/questions/299495/java-swing-how-to-add-an-image-to-a-jpanel

public class BoardView extends JFrame {

    public static final int SQUARE_SPACING = 75;

    public BoardView() {
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
        //@todo for debugging, remove
        Board board = new NormalChessBoard();
        //
        fillBoardWithPieces(g, board);
    }

    public void fillBoardWithPieces(Graphics g, Board board) {
        int width = board.getWidth();
        int height = board.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Piece currentPiece = board.getPiece(x, y);
                setPiece(g, currentPiece, x, y);
            }
        }
    }

    private void setPiece(Graphics g, Piece piece, int x, int y) {
        if (piece == null) {
            return;
        }
        String color;
        if (piece.getColor() == Player.BLACK) {
            color = "black";
        } else {
            color = "white";
        }


        String className = piece.getClass().getSimpleName();
        String imageName = "";
        imageName = color + className;
        BufferedImage image;

        try {
            image = ImageIO.read(new File("src/assets/images/" +imageName + ".png"));

        } catch (IOException ex) {
            System.exit(1);
            return; // java complains without this
        }

        g.drawImage(image, x*BoardView.SQUARE_SPACING, y*BoardView.SQUARE_SPACING, null);
    }

    private void invertSquareColor(Graphics g) {
        if (g.getColor() == Color.BLACK) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.BLACK);
        }
    }

}