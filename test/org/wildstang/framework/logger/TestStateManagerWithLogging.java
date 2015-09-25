package org.wildstang.framework.logger;

import org.junit.Before;

public class TestStateManagerWithLogging extends TestStateTracker
{

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(true);
   }}
