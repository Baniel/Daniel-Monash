/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planpath;

/**
 *
 * @author YANDONG ZHANG
 * 
 * the node just has the position X and position Y
 */
public class Node {

    private int x, y;

    public Node(int row, int column) {
        x = row;
        y = column;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int row) {
        x = row;
    }

    public void setY(int column) {
        y = column;
    }

}
