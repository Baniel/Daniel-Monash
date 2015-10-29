package planpath;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class Heuristic {
    
    public int xG;
    public int yG;
    public int h;
    
    
    public void setxG(int xG)
    {
        this.xG = xG;
    }
    
    
    public int getxG()
    {
        return xG;
    }
    
    public void setyG(int yG)
    {
        this.yG = yG;
    }
    
    public int getyG()
    {
        return yG;
    }
    
    /*
    **It's mainbody about the heuristic function
    */
    public double heuristic(int x,int y,int xG,int yG)
    
    
    {
        double h = 0;
        double xDifference = x-xG;
        double yDifference = y-yG;
        
        double xSquare = xDifference * xDifference;
        double ySquare = yDifference * yDifference;

        double result = (Math.sqrt(xSquare + ySquare))/2;
        h = result;
        
        return h;
        
      }
    
    
}
