package org.wildstang.framework.io;

import org.junit.Before;

public class TestOutputManagerWithLogging extends TestOutputManager
{

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(true);
   }
}
