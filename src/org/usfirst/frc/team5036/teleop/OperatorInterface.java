package org.usfirst.frc.team5036.teleop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OperatorInterface {
	public static final int DRIVER_CONTROLLER_PORT = 0;
	public static final int OPERATOR_CONTROLLER_PORT = 1;
	//public static final int PID_TUNER = 2;
	public Joystick driverController,operatorController;
	public static OperatorInterface instance;
	public OperatorInterface(){
		driverController = new Joystick(DRIVER_CONTROLLER_PORT);
		operatorController = new Joystick(OPERATOR_CONTROLLER_PORT);
		//pidTuner = new Joystick(PID_TUNER);
	}
	public static OperatorInterface getInstance(){
		if(instance==null){
			instance = new OperatorInterface();
		}
		return instance;
	}
	public double getDriveForward(){
		return (driverController.getRawAxis(1));
	}
	public double getDriveRotate(){
		return (driverController.getRawAxis(2));
	}
	public boolean getQuickTurn()
	{
		return driverController.getRawButton(6);
	}
	public boolean getHoldHeadingButton(){
		return driverController.getRawButton(12);
	}
	public double getCubic(double x){
		return (0.8*x*x*x) + (0.2*x*x);
	}
	public boolean getFlashButon(){
		return operatorController.getRawButton(8);
	}
	public double getClimbAxis(){
		return operatorController.getRawAxis(3);
	}
	public boolean getClimbFullPower(){
		return operatorController.getRawButton(6);
	}
	public boolean getManualFlapControlButton(){
		return operatorController.getRawButton(7);
	}
	public boolean getAutoFlapControlButton(){
		return operatorController.getRawButton(7);
	}
	public boolean getFlapsPullIn(){
		return operatorController.getRawButton(5);
	}
}
