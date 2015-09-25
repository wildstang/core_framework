package org.wildstang.framework.subsystems;

import org.junit.Before;

public class TestSubsystemManagerWithLogging extends TestSubsystemManager
{

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(true);
   }
}
