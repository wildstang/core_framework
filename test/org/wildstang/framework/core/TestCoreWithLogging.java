package org.wildstang.framework.core;

import org.junit.Before;

public class TestCoreWithLogging extends TestCore
{

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(true);
   }
}
