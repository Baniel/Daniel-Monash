/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planpath;

/**
 *
 * @author Administrator
 */
public class Cost {
    
    
    
    /*
    **To calucate how much G cost for this position point
    */
    
    public int gCost(String direction, int costG)
    {
        
        if ("RD".equals(direction) || "LD".equals(direction) || "LU".equals(direction) || "RU".equals(direction)) {
            costG = costG + 1;
        }
        if ("R".equals(direction) || "D".equals(direction) || "L".equals(direction) || "U".equals(direction)) {
            costG = costG + 2;

        }
        
        return costG;
        
    }
    
 
    
    
 
}
