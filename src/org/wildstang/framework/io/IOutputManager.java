package org.wildstang.framework.io;

public interface IOutputManager
{

   public void init();
   /**
    * Updates all outputs registered with the manager.
    */
   public abstract void update();

   public abstract void addOutput(Output p_output);

   public abstract void removeOutput(Output p_output);

   public abstract Output getOutput(String p_name);

   public abstract int size();

   public abstract void removeAll();

}