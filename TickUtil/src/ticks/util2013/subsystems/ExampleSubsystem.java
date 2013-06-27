
package ticks.util2013.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import ticks.util2013.util.NumberOutput;
import ticks.util2013.util.NumberSource;
import ticks.util2013.util.TickPID;
import ticks.util2013.util.TickPIDGains;
import ticks.util2013.util.TickUpdater;

/**
 *
 */
public class ExampleSubsystem extends Subsystem implements NumberOutput, NumberSource{
    //example implementation of Tickpid
    
    
    private TickPID pidController;
    
    public ExampleSubsystem(){
        pidController = new TickPID("example", new TickPIDGains(1, 0.1, 0.05, 0), this, this);
        TickUpdater.getInstance().add(pidController);
        SmartDashboard.putData(pidController);
        pidController.setGoal(2);
    }
    

    

    public void setNumbers(double number) {
        System.out.println(number + " this is the output");
    }

    public double getNumber() {
        return 4; //random number (:
    }


public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}