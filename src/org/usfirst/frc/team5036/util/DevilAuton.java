package org.usfirst.frc.team5036.util;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class DevilAuton{
	public static final long autonPeriod = 15000;
	public boolean isAuton;
	Timer timer;
	ArrayList<DevilAutonTask> taskList;
	ArrayList<Double> waitTimes;
	int taskIndex;
	public abstract void execute();
	public abstract void whenCompleted();
	public DevilAuton(){
		timer = new Timer();
		taskList = new ArrayList<DevilAutonTask>();
		waitTimes = new ArrayList<Double>();
	}
	public void start(){
		execute();
		isAuton = true;
		taskIndex = 0;
		timer.scheduleAtFixedRate(new ExecuteTask(),0L,1L);
		timer.schedule(new EndTask(),autonPeriod);
	}
	private class ExecuteTask extends TimerTask{
		@Override
		public void run(){
			System.out.println(taskIndex);
			if(taskIndex>=taskList.size()){
				this.cancel();
				timer.schedule(new WhenCompleted(),0L,1L);
				return;
			}
			DevilAutonTask task = taskList.get(taskIndex);
			if(!task.started){
				task.init();
				task.started = true;
				SmartDashboard.putNumber("Task: ", taskIndex);
			}
			if(task.isFinished()){
				taskIndex++;
				task.whenFinished();
				task.completed = true;
			}
			else{
				task.execute();
				edu.wpi.first.wpilibj.Timer.delay(waitTimes.get(taskIndex));
			}
		}
	}
	private class EndTask extends TimerTask{
		@Override
		public void run(){
			isAuton = false;
			timer.cancel();
			timer.purge();
		}
	}
	private class WhenCompleted extends TimerTask{
		@Override
		public void run(){
			whenCompleted();
		}
	}
	public void addTask(double waitTime,DevilAutonTask task){
		taskList.add(task);
		waitTimes.add(waitTime);
	}
}
