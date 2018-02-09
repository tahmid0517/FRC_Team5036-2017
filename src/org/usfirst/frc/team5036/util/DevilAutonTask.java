package org.usfirst.frc.team5036.util;
import java.util.TimerTask;
public abstract class DevilAutonTask extends TimerTask  {
	public abstract void init();
	public abstract void execute();
	public abstract void whenFinished();
	public abstract boolean isFinished();
	public boolean started = false;
	public boolean completed = false;
	public void run(){
		if(!isFinished()){
			execute();
		}
	}
}
