/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticks.util2013.util;

/**
 *
 * @author Jared
 */
public class TickPIDGains {

        double kP, kI, kD, kF;

        public TickPIDGains(double kP, double kI, double kD, double kF) {
            this.kD = kD;
            this.kF = kF;
            this.kI = kI;
            this.kP = kP;
        }

        public double getkD() {
            return kD;
        }

        public double getkF() {
            return kF;
        }

        public double getkI() {
            return kI;
        }

        public double getkP() {
            return kP;
        }

        public void setkD(double kD) {
            this.kD = kD;
        }

        public void setkF(double kF) {
            this.kF = kF;
        }

        public void setkI(double kI) {
            this.kI = kI;
        }

        public void setkP(double kP) {
            this.kP = kP;
        }
    }