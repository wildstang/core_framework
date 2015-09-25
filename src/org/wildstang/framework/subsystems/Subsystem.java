package org.wildstang.framework.subsystems;

import org.wildstang.framework.io.InputListener;

public interface Subsystem extends InputListener {

   /**
    * Initialise the subsystem.  Performs any required setup work.
    */
	public void init();
	
	/**
	 * Performs a self test of the subsystem.
	 */
	public void selfTest();
	
	/**
	 * Called to cause the subsystem to update its state and set new values on outputs.
	 * 
	 * Sending values to the hardware outputs is done outside of this method by the framework.
	 */
	public void update();
	
	/**
	 * Returns the name of the subsystem.
	 * 
	 * @return the name of the subsystem
	 */
	public String getName();
	
}
