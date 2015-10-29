package planpath;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author YANDONG ZHANG
 * The Node include the X position and Y position and G cost,h cost,and f cost for the heuristic function.
 */
public class NodeF {
 private int x,y,g;
 private double f,h;
    
    
    public NodeF(int x,int y,double f)
    {
        this.x = x;
        this.y = y;
        this.f = f;
                
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
    
      public void setG(int g)
    {
        this.g = g;
    }
    
    
    public int getG ()
    {
        return g;
    }
    
    public void setH(double h)
    {
        this.h = h;
    }
    
    public double getH()
    {
        return h;
    }
    
    public void setF(int g,double h)
    {
        this.f = g + h;
    }
    
    public double getF()
    {
        return f;
    }
    
  
    
    
    
    
    
}
