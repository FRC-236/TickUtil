/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticks.util2013.util;

import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

/**
 * An improved version of the wpi pid controller that is more efficient and runs
 * in the updater thread.
 *
 * @author Jared
 */
public class TickPID implements LiveWindowSendable, NamedSendable, Updatable {

    TickPIDGains gains;
    NumberSource source;
    NumberOutput output;
    String name;
    double goal;
    double errorSum;
    double lastError;
    double lastDeltaError;
    double lastSource, lastOut;
    double minIError = 10.0;
    double onTargetError = 2.0, onTargetDeltaError = 0.05;

    public TickPID(String name, TickPIDGains gains, NumberSource source, NumberOutput output) {
        this.name = name;
        this.gains = gains;
        this.source = source;
        this.output = output;


    }

    

    public void setGains(TickPIDGains gains) {
        this.gains = gains;
    }

    public void setErrorLimits(double onTargetError, double onTargetDeltaError) {
        this.onTargetError = onTargetError;
        this.onTargetDeltaError = onTargetDeltaError;
    }

    public void update() {
        lastSource = source.getNumber();

        double out = 0;
        double error = goal - lastSource;
        double p = gains.getkP() * error;
        if (Math.abs(error) < minIError) {
            errorSum += error;
        }
        double i = gains.getkI() * errorSum;
        double dError = error - lastError;
        double d = gains.getkD() * dError;
        lastError = error;
        double ff = gains.getkF() * goal;
        out = p + i + d + ff;
        output.setNumbers(out);
        lastOut = out;
        lastDeltaError = dError;
    }

    public void setGoal(double goal) {
        errorSum = 0;
        this.goal = goal;
        output.setNumbers(0);
    }

    public double getGoal() {
        return this.goal;
    }
    private ITableListener listener = new ITableListener() {
        public void valueChanged(ITable table, String key, Object value, boolean isNew) {
            if (key.equals("p") || key.equals("i") || key.equals("d") || key.equals("f")) {
                if (gains.getkP() != table.getNumber("p", 0.0) || gains.getkI() != table.getNumber("i", 0.0)
                        || gains.getkD() != table.getNumber("d", 0.0)) {
                    System.out.println("got gains!");
                    gains.setkP(table.getNumber("p", 0.0));
                    gains.setkI(table.getNumber("i", 0.0));
                    gains.setkD(table.getNumber("d", 0.0));
                }
            } else if (key.equals("setpoing")) {
                if (goal != ((Double) value).doubleValue()) {
                    setGoal(((Double) value).doubleValue());
                }
            }
        }
    };
    private ITable table;

    public void updateTable() {
    }

    public void startLiveWindowMode() {
    }

    public void stopLiveWindowMode() {
    }

    public void initTable(ITable table) {
        if (this.table != null) {
            this.table.removeTableListener(listener);
        }
        this.table = table;
        if (table != null) {
            table.putNumber("p", gains.getkP());
            table.putNumber("i", gains.getkI());
            table.putNumber("d", gains.getkD());
            table.putNumber("f", gains.getkF());
            table.putNumber("goal", goal);
            table.putNumber("source", source.getNumber());
            table.addTableListener(listener, true);
        }
    }

    public ITable getTable() {
        return table;
    }

    public String getSmartDashboardType() {
        return "PIDController";
    }

    public String getName() {
        return name;
    }

    public boolean onTarget() {
        boolean done = (Math.abs(goal - lastSource) < onTargetError)
                && (Math.abs(lastDeltaError) < onTargetDeltaError);

        return done;
    }
}
