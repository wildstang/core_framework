package org.wildstang.framework;


import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.easymock.EasyMockSupport;
import org.junit.Before;

public class BaseTest extends EasyMockSupport
{
   private boolean m_loggingOn;
   Logger mockLogger;


   @Before
   public void setUp() throws Exception
   {
      mockLogger = createNiceMock(Logger.class);
   }
   
   protected boolean getLoggingState()
   {
      return m_loggingOn;
   }
   
   protected void setLoggingState(boolean loggingOn)
   {
      m_loggingOn = loggingOn;
   }
   
   protected void configureLogging()
   {
      expect(mockLogger.isLoggable((Level)anyObject())).andReturn(getLoggingState()).anyTimes();

   }
   
   public Logger getMockLogger()
   {
      return mockLogger;
   }

   
}
