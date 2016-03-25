package org.wildstang.framework.io;

import org.junit.Before;

public class TestInputManagerWithLogging extends TestInputManager
{

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(true);
   }
}
