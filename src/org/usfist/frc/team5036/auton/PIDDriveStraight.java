package org.usfist.frc.team5036.auton;

import org.usfirst.frc.team5036.robot.Robot;
import org.usfirst.frc.team5036.robot.RobotOutput;
import org.usfirst.frc.team5036.robot.Sensors;
import org.usfirst.frc.team5036.subsystems.Drivetrain;
import org.usfirst.frc.team5036.teleop.OperatorInterface;
import org.usfirst.frc.team5036.util.DevilAutonTask;
import org.usfirst.frc.team5036.util.PID;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class PIDDriveStraight{
	public static final double kP = 0.00228740157186985;
	public static final double kI = 0;
	public static final double kD = 0;
	public static void driveStraight(double length,double tolerance){
		Sensors.getInstance().resetEncoders();
		SmartDashboard.putString("PID:","ON");
		double error = length - Sensors.getInstance().getEncoderAverage();
		Sensors.getInstance().resetGyro();
		//while(/*!OperatorInterface.getInstance().driverController.getRawButton()*/){
		while(DriverStation.getInstance().isAutonomous() && Math.abs(error)>tolerance){
			double output = getP(error);
			Drivetrain.getInstance().arcadeDrive(-(output),-PIDRotate.kP*Sensors.getInstance().getGyro());
			error = length - Sensors.getInstance().getEncoderAverage();
			SmartDashboard.putNumber("Gyro",Sensors.getInstance().getGyro());
			SmartDashboard.putNumber("ERROR:",error);
			SmartDashboard.putNumber("P",getP(error));
			SmartDashboard.putNumber("Length",Sensors.getInstance().getEncoderAverage());
			Timer.delay(0.002);
		}
		SmartDashboard.putString("PID:","OFF");
		Drivetrain.getInstance().arcadeDrive(0,0);
		//SmartDashboard.putString("PID P:",String.valueOf((OperatorInterface.getInstance().getPIDAxis() + 1)/10000));
	}
	public static double getP(double error){
		return error * kP;
	}
	public static double getPConstant(){
		//return 0.0015 + (OperatorInterface.getInstance().getPIDAxis()/100);
		return 0;
	}
}
