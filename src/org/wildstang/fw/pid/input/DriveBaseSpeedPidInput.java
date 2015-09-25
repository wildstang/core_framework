package org.wildstang.fw.pid.input;

import org.wildstang.framework.core.Core;
import org.wildstang.yearly.subsystems.DriveBase;
import org.wildstang.yearly.subsystems.WSSubsystems;

/**
 *
 * @author Nathan
 */
public class DriveBaseSpeedPidInput implements IPidInput
{

   public DriveBaseSpeedPidInput()
   {
      // Nothing to do here
   }

   @Override
   public double pidRead()
   {
      double /* left_encoder_value, */right_encoder_value, final_encoder_value;
      // left_encoder_value = ((DriveBase)
      // SubsystemContainer.getInstance().getSubsystem(SubsystemContainer.DRIVE_BASE)).getLeftDistance();
      double currentVelocity = ((DriveBase) Core.getSubsystemManager().getSubsystem(WSSubsystems.DRIVE_BASE.getName())).getVelocity();
      return currentVelocity;
   }
}
