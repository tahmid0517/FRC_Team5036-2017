package org.usfirst.frc.team5036.util;

public class PID {
	double kP,kI,kD;
	double currentError,lastError,accumulatedError,integralThreshold;
	public PID(double kP,double kI,double kD,double error,double integralThreshold){
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
		currentError = error;
		lastError = error;
		accumulatedError = 0;
		this.integralThreshold = integralThreshold;
	}
	public double getOutput(double error){
		calculate(error);
		return getP(error) - getD(error) + getI(error);
	}
	public void calculate(double error){
		lastError = currentError;
		currentError = error;
		if(error<=integralThreshold){
			accumulatedError+=error;
		}
	}
	public double getP(double error){
		return kP * error;
	}
	public double getI(double error){
		return kI * accumulatedError;
	}
	public double getD(double error){
		return (lastError - currentError) * kD;
	}
	public double getError(){
		return currentError;
	}
}
