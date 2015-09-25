package org.wildstang.framework.io.inputs;

import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AnalogInput extends AbstractInput
{

   private static Logger s_log = Logger.getLogger(AnalogInput.class.getName());
   private static final String s_className = "AnalogInput";

   private static final DecimalFormat s_format = new DecimalFormat("#.###");
   
   private double m_currentValue = 0.0d;

   public AnalogInput(String p_name)
   {
      super(p_name);
   }

   public AnalogInput(String p_name, double p_default)
   {
      super(p_name);
      setCurrentValue(p_default);
   }

   @Override
   protected void readDataFromInput()
   {
      if (s_log.isLoggable(Level.FINER)) s_log.entering(s_className, "readDataFromInput");

      double newValue = readRawValue();

      // Only update if the value has changed
      // NOTE: For analog inputs, it is possible to change often due to noise
      // or sensitive sensors. May want to implement a tolerance/sensitivity
      // on value changes
      if (s_log.isLoggable(Level.FINEST))
      {
         s_log.finest("Current value = " + m_currentValue + " : New value = " + newValue);
      }
      if (newValue != m_currentValue)
      {
         setCurrentValue(newValue);
         setValueChanged(true);
      }
      else
      {
         setValueChanged(false);
      }

      if (s_log.isLoggable(Level.FINER)) s_log.exiting(s_className, "readDataFromInput");
   }

   protected void setCurrentValue(double p_value)
   {
      m_currentValue = p_value;
   }

   /**
    * This method reads the raw value from the underlying hardware. This should
    * be implemented by each individual input subclass.
    * 
    * @return
    */
   protected abstract double readRawValue();
   
   public double getValue()
   {
      return m_currentValue;
   }

   @Override
   protected void logCurrentState()
   {
      if (s_log.isLoggable(Level.FINER)) s_log.entering(s_className, "logCurrentState");

      getStateTracker().addState(getName(), getName(), s_format.format(getValue()));
//      StateManager.getInstance().addState(getName(), getParent() == null ? getName() : getParent().getName(), getValue());
      
      if (s_log.isLoggable(Level.FINER)) s_log.exiting(s_className, "logCurrentState");
   }
 

}
