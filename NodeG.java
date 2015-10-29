/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planpath;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author YANDONG ZHANG
 *  The NodeG include the position X and position Y and the G cost.
 */
public class NodeG {
    private int x,y,g;
    public NodeG(int x,int y,int g)
    {
        this.x = x;
        this.y = y;
        this.g = g;
    }
    
    public void setG(int g)
    {
        this.g = g;
    }
    
    public int getG(int r, int c)
    {
        if (r == x && c == y) 
            return g;
        else return -1;
    }
    
    public int getG ()
    {
        return g;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
     public int getX()
     {
         return x;
     }
    
     public void setY(int y)
     {
         this.y = y;
     }
     
     
     public int getY()
     {
         return y;
     }
     
      public String getCoordinate()
    {
        return getX() + "," + getY();
    }
    
    
}
