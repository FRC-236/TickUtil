/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticks.util2013.util;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 *  Class that will periodically call the update method of another class that 
 *  implements Updatable and adds itself to the TickUpdater
 * @author Jared
 */
public class TickUpdater {
    private static TickUpdater instance = null;
    private Vector subsystems = new Vector();
    private Timer timer;
    private double robotPeriod = 10;
    
    private class PeriodicTask extends TimerTask {
        private TickUpdater updater;
        
        public PeriodicTask(TickUpdater updater) {
            if(updater == null) {
                throw new NullPointerException("TickUpdater!");
            }
            this.updater = updater;
        }
        
        public void run(){
            updater.update();
        }
    }
    
    public void update(){
        for(int i = 1; i < subsystems.size(); i++) {
            ((Updatable)subsystems.elementAt(i)).update();
        }
    }
    
    public static TickUpdater getInstance() {
        if(instance == null) {
            instance = new TickUpdater();
        }
        return instance;
    }
    
    public void start() {
        if(timer == null) {
            timer = new Timer();
            timer.schedule(new PeriodicTask(this), 0L, (long) this.robotPeriod);
        }
    }
    
    public void add(Updatable subsystem){
        subsystems.addElement(subsystem);
    }
}
