package org.wildstang.framework.io.inputs;

import org.junit.Before;

public class TestAbstractInputWithLogging extends TestAbstractInput
{

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(true);
   }
}
