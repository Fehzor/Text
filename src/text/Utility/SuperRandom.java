/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Utility;

import java.util.Random;

/**
 * Returns skewed random numbers, randomly changing the skew.
 * @author FF6EB4
 */
public class SuperRandom {
    public static Random oRan = new Random();
    
    
    public static int nextIntGaussian(int max){
        double d = oRan.nextGaussian();
        
        d = d / 3;
        if(d > 1) d = 1;
        if(d < -1) d = -1;
        
        d = d / 2;
        d = d + .5;
        
        return (int)(d * (double)max);
    }
    
    //Next int but it's the inverse of a bell curve
    public static int nextIntReverseGaussian(int max){
        double d = oRan.nextGaussian();
        
        d = d / 3;
        if(d > 1) d = 1;
        if(d < -1) d = -1;
        
        if(d < 0){
            d = -1 - d;
        } else {
            d = 1 - d;
        }
        
        d = d / 2;
        d = d + .5;
        
        return (int)(d * (double)max);
    }
    
    
    /**
     * Jump is
     * @param base
     * The base random
     * @param jump
     * Chance, from 1 to 99, of more being added
     * BE CAREFUL OF HIGHER VALUES
     * @return 
     * A random number
     */
    public static int nextIntJump(int base, int jump){
        int a = oRan.nextInt(base);
        
        while(oRan.nextInt(100) < jump){
            a+=oRan.nextInt(oRan.nextInt(base)+1);
        }
        
        return a;
    }
    
    public static double[] skew = skew(10);
    
    public static int nextIntSkewed(int i){
        if(skew.length < i || oRan.nextInt(100) <= 5){
            skew = skew(i);
        }
        
        int ret = oRan.nextInt(i);
        double check = Math.abs(oRan.nextGaussian());
        
        while(skew[ret] < check){
            ret = oRan.nextInt(i);
            check = Math.abs(oRan.nextGaussian()) / 3;
        }
        
        return ret;
    }
    
    private static double[] skew(int i){
        double[] ret = new double[i];
        
        int middle = oRan.nextInt(i);
        
        for(int j = 0; j < i; ++j){
            int distance = Math.abs(middle - j);
            
            ret[j] = 1 / Math.sqrt(distance);
        }
        
        return ret;
    }
}
