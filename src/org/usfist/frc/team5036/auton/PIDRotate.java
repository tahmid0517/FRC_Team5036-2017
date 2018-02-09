package org.usfist.frc.team5036.auton;
import org.usfirst.frc.team5036.robot.Sensors;
import org.usfirst.frc.team5036.subsystems.Drivetrain;
import org.usfirst.frc.team5036.teleop.OperatorInterface;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDRotate {
	public static final double kP = 0.04924409580230713;
	public static final double kI = 0;
	public static final double kD = 0.203125;
	static double currentError,lastError;
	public static void rotate(double target,double tolerance){
		Sensors.getInstance().resetGyro();
		double error = target - Sensors.getInstance().getGyro();
		currentError = error;
		lastError = error;
		SmartDashboard.putString("PID","ON");
		SmartDashboard.putNumber("PID P",getPConstant());
		//while(!OperatorInterface.getInstance().driverController.getRawButton(2)){
		while(DriverStation.getInstance().isAutonomous() && Math.abs(error)>tolerance){
			Drivetrain.getInstance().rotate(getP(error) - getD(error));
			error = target - Sensors.getInstance().getGyro();
			lastError = currentError;
			currentError = error;
			SmartDashboard.putNumber("Difference",lastError - currentError);
			SmartDashboard.putNumber("current error",currentError);
			SmartDashboard.putNumber("last Error",lastError);
			SmartDashboard.putNumber("ERROR:",error);
			SmartDashboard.putNumber("D",getD(error));
			SmartDashboard.putNumber("Angle",Sensors.getInstance().getGyro());
			Sensors.getInstance().getGyro();
			Timer.delay(0.02);
		}
		SmartDashboard.putString("PID","OFF");
	}
	public static double getP(double error){
		//return kP * error;
		return getPConstant() * error;
	}
	public static double getD(double error){
		return getDConstant() * (lastError - currentError);
	}
	public static double getPConstant(){
		return kP;
	}
	public static double getDConstant(){
		return kD;
	}
}