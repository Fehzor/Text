/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.Environment;

import java.io.Serializable;
import java.util.Random;
import text.Images.BackGround;

/**
 *
 * @author FF6EB4
 */
public class Environment implements Serializable{
    public static Random oRan = new Random();
    
    //For all 4 double values-
    // -1 = extremely low; 0 = extremely average; 1 = extremely high
    public double waterLevel; 
    public double sunlight;
    public double humidity;
    public double temperature;
    public Soil soil;
    
    public int type;
    public static final int TYPE_NONE = -1;
    public static final int TYPE_NORMAL = 0; // Average; uses nextGaussian();
    public static final int TYPE_ERRATIC = 1; //Extreme but weird; opposes nextGaussian()
    public static final int TYPE_RANDOM = 2; //Random; uses nextDouble();
    
    public Environment(double wl, double sun, double hm, double tem){
        this.waterLevel = wl;
        this.sunlight = sun;
        this.humidity = hm;
        this.temperature = tem;
        soil = new Soil();
        if(waterLevel > .3){
            soil.water = 100;
        }
    }
    
    public Environment(double wl, double sun, double hm, double tem, Soil s){
        this.waterLevel = wl;
        this.sunlight = sun;
        this.humidity = hm;
        this.temperature = tem;
        this.soil = s;
        if(waterLevel > .3){
            soil.water = 100;
        }
    }
    
    public Environment(){
        this(oRan.nextInt(2));
    }
    
    public Environment clone(){
        return new Environment(this.waterLevel,this.sunlight,this.humidity,this.temperature,new Soil(this.soil));
    }
    
    //Generate the environment based on type.
    public Environment(int type){
        if(type == TYPE_NONE){ //Used for say, cities etc.
            this.waterLevel = 0;
            this.sunlight = 0;
            this.humidity = 0;
            this.temperature = 0;
            
            soil = new Soil();
        }
        if(type == TYPE_ERRATIC){
            this.waterLevel = reverseGaussian()*.35;
            this.sunlight = reverseGaussian()*.35;
            this.humidity = reverseGaussian()*.35;
            this.temperature = reverseGaussian()*.35;
            
            int soildiversity =(int)( (reverseGaussian() + 1) * 5.0 );
            soil = new Soil(soildiversity);
        }
        if(type == TYPE_NORMAL){
            this.waterLevel = oRan.nextGaussian()*.35;
            this.sunlight = oRan.nextGaussian()*.35;
            this.humidity = oRan.nextGaussian()*.35;
            this.temperature = oRan.nextGaussian()*.35;
            
            int soildiversity =(int)( (oRan.nextGaussian()+1) * 5.0 );
            soil = new Soil(soildiversity);
        }
        if(type == TYPE_RANDOM){
            this.waterLevel = (oRan.nextDouble() * 2 ) - 1;
            this.sunlight = (oRan.nextDouble() * 2 ) - 1;
            this.humidity = (oRan.nextDouble() * 2 ) - 1;
            this.temperature = (oRan.nextDouble() * 2 ) - 1;
            
            int soildiversity =(int)( oRan.nextInt(10) );
            soil = new Soil(soildiversity);
        }
        
        if(waterLevel > .3){
            soil.water = 100;
        }
    }
    
    private double reverseGaussian(){
        double val = oRan.nextGaussian();
        if(val > .5){
            val -= .5;
        } else {
            val += .5;
        }
        return val;
    }
    
    //Gets the soil's background. Can be overridden later for other places.
    public BackGround makeBack(){
        return soil.makeBack();
    }
    
    //How compatible is this environment with another? Used for plants etc.
    //0 = perfect match
    public double compatible(Environment E){
        double a,b,c,d;
        
        a = Math.abs(E.waterLevel - this.waterLevel);
        b = Math.abs(E.sunlight - this.sunlight);
        c = Math.abs(E.humidity - this.humidity);
        d = Math.abs(E.temperature - this.temperature);
        
        double ans = (a+b+c+d) / 4.0;
        
        return ans;
    }
}
