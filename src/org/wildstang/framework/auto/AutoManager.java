package org.wildstang.framework.auto;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.wildstang.framework.auto.program.Sleeper;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Nathan
 */
public class AutoManager
{

   private List<AutoProgram> programs = new ArrayList<AutoProgram>();
   private AutoProgram runningProgram;
   private boolean programFinished, programRunning;
   private static AutoManager instance = null;
   private AutoStartPositionEnum currentPosition;
   private SendableChooser chooser;
   private SendableChooser lockinChooser;
   private SendableChooser allianceChooser;
   private static Logger s_log = Logger.getLogger(AutoManager.class.getName());

   private AutoManager()
   {
      currentPosition = AutoStartPositionEnum.UNKNOWN;
      chooser = new SendableChooser();
      lockinChooser = new SendableChooser();
      lockinChooser.addDefault("Unlocked", false);
      lockinChooser.addObject("Locked", true);

      allianceChooser.addDefault("Red", true);
      allianceChooser.addObject("Blue", false);
      
      definePrograms();

      SmartDashboard.putData("Select Autonomous Program", chooser);
      SmartDashboard.putData("Lock in auto program", lockinChooser);
      SmartDashboard.putData("Alliance Color", allianceChooser);
      clear();
   }

   public void update()
   {
      if (programFinished)
      {
         runningProgram.cleanup();
         programFinished = false;
         startSleeper();
      }
      runningProgram.update();
      if (runningProgram.isFinished())
      {
         programFinished = true;
      }
   }

   public void startCurrentProgram()
   {
      if ((Boolean) lockinChooser.getSelected())
      {
         runningProgram = (AutoProgram) chooser.getSelected();
      }
      else
      {
         runningProgram = programs.get(0);
      }
//      runningProgram = programs.get(1);
      s_log.logp(Level.ALL, "Auton", "Running Autonomous Program", runningProgram.toString());
      runningProgram.initialize();
      SmartDashboard.putString("Running Autonomous Program", runningProgram.toString());
   }

   public void startSleeper()
   {
      runningProgram = programs.get(0);
      runningProgram.initialize();
   }

   public void clear()
   {
      programFinished = false;
      programRunning = false;
      if (runningProgram != null)
      {
         runningProgram.cleanup();
      }
      runningProgram = (AutoProgram) programs.get(0);
      SmartDashboard.putString("Running Autonomous Program", "No Program Running");
      SmartDashboard.putString("Current Start Position", currentPosition.toString());
   }

   public AutoProgram getRunningProgram()
   {
      if (programRunning)
      {
         return runningProgram;
      }
      else
      {
         return (AutoProgram) null;
      }
   }

   public String getRunningProgramName()
   {
      return runningProgram.toString();
   }

   /*
    * public String getSelectedProgramName() { return
    * programs.get(currentProgram).toString(); }
    * 
    * public String getLockedProgramName() { return
    * programs.get(lockedProgram).toString(); }
    */

   public AutoStartPositionEnum getStartPosition()
   {
      return currentPosition;
   }

   public static AutoManager getInstance()
   {
      if (AutoManager.instance == null)
      {
         AutoManager.instance = new AutoManager();
      }
      return AutoManager.instance;
   }

   
   public String getAllianceColor() {
      return (String) allianceChooser.getSelected();
   }
   /*
    * public void setProgram(int index) { if (index >= programs.size() || index
    * < 0) { index = 0; } currentProgram = index; lockedProgram =
    * currentProgram; }
    */

   public void setPosition(int index)
   {
      if (index >= AutoStartPositionEnum.POSITION_COUNT)
      {
         index = 0;
      }
      currentPosition = AutoStartPositionEnum.getEnumFromValue(index);
   }

   private void definePrograms()
   {
      addProgram(new Sleeper()); // Always leave Sleeper as
                                 // 0. Other parts of the
                                 // code assume 0 is Sleeper.

   }

   public void addProgram(AutoProgram program)
   {
      programs.add(program);
      chooser.addObject(program.toString(), program);
   }
}
