package org.wildstang.framework.io.inputs;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DiscreteInput extends AbstractInput
{

   private static Logger s_log = Logger.getLogger(DiscreteInput.class.getName());
   private static final String s_className = "DiscreteInput";

   private int m_currentValue = 0;

   public DiscreteInput(String p_name)
   {
      super(p_name);
   }

   public DiscreteInput(String p_name, int p_default)
   {
      super(p_name);
      setCurrentValue(p_default);
   }

   @Override
   public void readDataFromInput()
   {
      if (s_log.isLoggable(Level.FINER)) s_log.entering(s_className, "readDataFromInput");

      int newValue = readRawValue();

      // Only update if the value has changed
      if (s_log.isLoggable(Level.FINEST))
      {
         s_log.finest("Current value = " + m_currentValue + " : New value = " + newValue);
      }
      if (newValue != m_currentValue)
      {
         setCurrentValue(newValue);
         setValueChanged(true);
      }

      if (s_log.isLoggable(Level.FINER)) s_log.exiting(s_className, "readDataFromInput");
   }

   protected void setCurrentValue(int p_value)
   {
      m_currentValue = p_value;
   }

   protected abstract int readRawValue();
   
   public int getValue()
   {
      return m_currentValue;
   }

   @Override
   protected void logCurrentState()
   {
      if (s_log.isLoggable(Level.FINER)) s_log.entering(s_className, "logCurrentState");

      getStateTracker().addState(getName(), getName(), getValue());
      
      if (s_log.isLoggable(Level.FINER)) s_log.exiting(s_className, "logCurrentState");
   }

}
