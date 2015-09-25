package org.wildstang.framework.io;

public interface IInputManager
{

   public void init();
   /**
    * Updates all inputs registered with the manager.
    */
   public abstract void update();

   public abstract void addInput(Input p_input);

   public abstract void removeInput(Input p_input);

   public abstract Input getInput(String p_name);

   public abstract int size();

   public abstract void removeAll();

}