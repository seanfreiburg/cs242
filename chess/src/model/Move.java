package model;


public class Move {

    int startX;
    int startY;
    int endX;
    int endY;

    /**
     * Default constructor that sets up a Move
     *
     * @param source_x
     * @param source_y
     * @param destination_x
     * @param destination_y
     */
    public Move(int source_x, int source_y, int destination_x, int destination_y) {
        startX = source_x;
        startY = source_y;
        endX = destination_x;
        endY = destination_y;

    }

    public int getStartX() {
        return this.startX;
    }

    public void setStartX(int start_x) {
        this.startX = start_x;
    }

    public int getStartY() {
        return this.startY;
    }

    public void setStartY(int start_y) {
        this.startY = start_y;
    }

    public int getEndX() {
        return this.endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return this.endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }


}
