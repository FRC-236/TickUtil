/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticks.util2013.util;

/**
 *
 * @author Jared
 */
public class TickBangBang implements Updatable{
    NumberSource source;
    NumberOutput output;
    double highValue, lowValue, goal;
    
    public TickBangBang(NumberOutput output, NumberSource source){
        this.source = source;
        this.output = output;
    }

    public void setHighValue(double highValue) {
        this.highValue = highValue;
    }

    public void setLowValue(double lowValue) {
        this.lowValue = lowValue;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }
    

    public void update() {
        if(source.getNumber()>goal){
            output.setNumbers(lowValue);
        }else{
            output.setNumbers(highValue);
        }
    }
    
    
}
