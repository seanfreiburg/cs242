package view;


// Cite: Java examples on Oracle site

import controller.GameController;
import model.PlayerColors;
import model.board.NormalChessBoard;
import model.piece.Pawn;
import model.piece.Piece;
import model.player.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BoardView extends JFrame {

    GridLayout boardLayout = new GridLayout(8, 8);
    SquareButton[][] buttons = new SquareButton[8][8];
    int startX, startY, endX, endY;
    GameController controller;

    public BoardView(String name, GameController controller) {
        super(name);
        startX = -1;
        startY = -1;
        endX = -1;
        endY = -1;
        this.controller = controller;

        setResizable(false);

        this.createAndShowGUI();
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    public void createAndShowGUI() {
        //Create and set up the window.
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        this.addComponentsToPane(this.getContentPane());
        //Display the window.
        this.pack();
        this.setVisible(true);
        //debugging @todo remove this
        //setPieces(new NormalChessBoard());
    }



    public void addComponentsToPane(final Container pane) {
        JPanel squaresPanel = new JPanel();
        squaresPanel.setLayout(boardLayout);
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(2, 3));

        Dimension buttonSize = new Dimension(250, 150);
        squaresPanel.setPreferredSize(new Dimension((int) (buttonSize.getWidth() * 2.5),
                (int) (buttonSize.getHeight() * 3.5)));

        int i = 0;
        Color color = Color.WHITE;
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                SquareButton button = new SquareButton(x, y);
                button.setBackground(color);
                squaresPanel.add(button);
                color = invertSquareColor(color);
                i++;
                if (i % 8 == 0) {
                    color = invertSquareColor(color);
                }
                buttons[x][y] = button;
                buttons[x][y].addActionListener(e(x, y));
                Image image = null;

                //major hack haha
                NormalChessBoard board = new NormalChessBoard();
                Piece piece = board.getPiece(x, y);
                if (piece != null) {
                    image = getImage(piece);

                    button.setIcon(new ImageIcon(image));
                }

            }
        }

        pane.add(squaresPanel, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    }

    private Color invertSquareColor(Color color) {
        if (color.equals(Color.BLACK)) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    private ActionListener e(final int i, final int j) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (startX == -1 && startY == -1) {
                    startX = i;
                    startY = j;
                    System.out.println(startX + " "+ startY+ " "+ endX+ " "+ endY);

                } else {
                    endX = i;
                    endY = j;
                    String status = controller.sendMove(startX,startY,endX,endY, controller.getCurrentPlayer());
                    startX =-1;
                    startY = -1;
                    endX = -1;
                    endY = -1;
                    System.out.println(startX + " "+ startY+ " "+ endX+ " "+ endY);
                    System.out.println(status);

                }
            }
        };
    }

    public void setPiece(int x, int y, Piece piece) {
        BufferedImage image = getImage(piece);
        if (image == null) {
            buttons[x][y].setIcon(null);
        } else {
            buttons[x][y].setIcon(new ImageIcon(image));
        }
    }

    public BufferedImage getImage(Piece piece) {
        if (piece == null) {
            return null;
        }

        String color;
        if (piece.getColor() == PlayerColors.BLACK) {
            color = "black";
        } else {
            color = "white";
        }


        String className = piece.getClass().getSimpleName();
        String imageName = "";
        imageName = color + className;
        BufferedImage image;

        try {
            image = ImageIO.read(new File("assets/images/" + imageName + ".png"));

        } catch (IOException ex) {
            image = null;
            System.exit(1);
        }
        return image;
    }

}