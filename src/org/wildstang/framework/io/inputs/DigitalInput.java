package org.wildstang.framework.io.inputs;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DigitalInput extends AbstractInput
{

   private static Logger s_log = Logger.getLogger(DigitalInput.class.getName());
   private static final String s_className = "DigitalInput";

   private boolean m_currentValue = false;
   private int m_cyclesOnCurrentValue = 0;
   private boolean m_debounced = false;
   private boolean m_lastValue;
   private int DEBOUNCE_CYCLES;

   public DigitalInput(String p_name)
   {
      super(p_name);
   }

   public DigitalInput(String p_name, boolean p_debounced, int p_debounceCycles)
   {
      super(p_name);
      m_debounced = p_debounced;
      DEBOUNCE_CYCLES = p_debounceCycles;
   }

   public DigitalInput(String p_name, boolean p_default)
   {
      super(p_name);
      setCurrentValue(p_default);
   }

   @Override
   public void readDataFromInput()
   {
      if (s_log.isLoggable(Level.FINER)) s_log.entering(s_className, "readDataFromInput");

      boolean newValue = readRawValue();

      // Only update if the value has changed
      if (s_log.isLoggable(Level.FINEST))
      {
         s_log.finest("Current value = " + m_currentValue + " : New value = " + newValue);
      }

      if (m_debounced)
      {
         if (newValue != m_lastValue)
         {
            // The value has changed - reset the counter
            m_cyclesOnCurrentValue = 0;
            m_lastValue = newValue;
         }
         else
         {
            // Otherwise, the value has held for longer - increment counter
            m_cyclesOnCurrentValue++;
         }
         
         // If the value has held long enough, set the new value
         if (m_cyclesOnCurrentValue >= DEBOUNCE_CYCLES)
         {
            setCurrentValue(newValue);
            setValueChanged(true);
         }
      }
      else
      {
         if (newValue != m_currentValue)
         {
            setCurrentValue(newValue);
            setValueChanged(true);
         }
      }
      
      if (s_log.isLoggable(Level.FINER)) s_log.exiting(s_className, "readDataFromInput");
   }

   protected void setCurrentValue(boolean p_value)
   {
      m_currentValue = p_value;
   }

   protected abstract boolean readRawValue();
   
   public boolean getValue()
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
