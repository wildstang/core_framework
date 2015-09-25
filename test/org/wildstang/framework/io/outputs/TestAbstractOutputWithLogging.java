package org.wildstang.framework.io.outputs;

import org.junit.Before;

public class TestAbstractOutputWithLogging extends TestAbstractOutput
{

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(true);
   }
}
