package view;


// Cite: Java examples on Oracle site , http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/layout/GridLayoutDemoProject/src/layout/GridLayoutDemo.java

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
    Label playerLabel;
    Label statusLabel;
    Label scoreLabel;


    public BoardView(String name, GameController controller) {
        super(name);
        startX = -1;
        startY = -1;
        endX = -1;
        endY = -1;
        this.controller = controller;
        playerLabel = new Label(getPlayerLabel() + " Player's turn");
        statusLabel = new Label("New game");
        scoreLabel = new Label("White 0 to Black 0");
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
        controls.setLayout(new GridLayout(2, 2));

        Dimension buttonSize = new Dimension(275, 175);
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

                NormalChessBoard board = controller.getBoard();
                Piece piece = board.getPiece(x, y);
                if (piece != null) {
                    image = getImage(piece);

                    button.setIcon(new ImageIcon(image));
                }

            }
        }

        controls.add(playerLabel);
        controls.add(statusLabel);
        controls.add(scoreLabel);


        JButton forfeitButton = new JButton("Forfeit");
        forfeitButton.addActionListener(forfeit());
        controls.add(forfeitButton);

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(restart());
        controls.add(restartButton);


        pane.add(squaresPanel, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    }

    private ActionListener restart() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Restart");
                controller.restart();
            }

        };
    }

    private ActionListener forfeit() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Forfeit");
                controller.forfeit();
            }
        };
    }

    private String getPlayerLabel() {
        boolean color = controller.getCurrentPlayer().getColor();
        if (color == PlayerColors.BLACK) {
            return "Black";
        } else {
            return "White";
        }
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
                    buttons[i][j].setBackground(Color.YELLOW);

                } else {
                    endX = i;
                    endY = j;
                    String status = controller.sendMove(startX, startY, endX, endY, controller.getCurrentPlayer());
                    statusLabel.setText(status);
                    if ((startX %2 ==0 && startY%2 == 0) || (startX %2 ==1 && startY%2 == 1)){
                        buttons[startX][startY].setBackground(Color.WHITE);
                    }
                    else{
                        buttons[startX][startY].setBackground(Color.BLACK);
                    }
                    startX = -1;
                    startY = -1;
                    endX = -1;
                    endY = -1;


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

    public void setTurnText(boolean color) {

        playerLabel.setText(getPlayerLabel() + " Player's turn");

    }

    public void setScoreLabel(int blackScore, int whiteScore) {
        scoreLabel.setText("White " + whiteScore + " to Black" + blackScore);
    }
}