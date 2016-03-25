package org.wildstang.framework.config;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.config.ConfigListener;
import org.wildstang.framework.config.ConfigManager;

public class TestConfigManager extends BaseTest
{
   ConfigManager manager;
   ConfigListener mockConfigListener;

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(false);
      manager = new ConfigManager();
      manager.init();
      
      mockConfigListener = createMock(ConfigListener.class);
      
      Whitebox.setInternalState(ConfigManager.class, getMockLogger());
   }

   @After
   public void tearDown() throws Exception
   {
   }

   @Test
   public void testConfigManager()
   {
      manager = new ConfigManager();
      assertNotNull(manager);
      assertNull(manager.getConfig());
   }

   @Test
   public void testInit()
   {
      manager = new ConfigManager();
      assertNotNull(manager);
      assertNull(manager.getConfig());
      
      manager.init();
      assertNotNull(manager.getConfig());
   }

   @Test
   public void testLoadConfig()
   {
      BufferedReader reader = new BufferedReader(new StringReader(""));

      resetAll();
      configureLogging();
      replayAll();
      
      manager.loadConfig(reader);
   }

   @Test
   public void testAddConfigListener()
   {
      resetAll();
      configureLogging();
      
      replayAll();
      
      manager.addConfigListener(mockConfigListener);
      assertEquals(1, manager.getConfigListeners().size());
      verifyAll();
   }

   @Test(expected=NullPointerException.class)
   public void testAddConfigListenerNull()
   {
      resetAll();
      configureLogging();
      
      replayAll();
      
      manager.addConfigListener(null);

      assertEquals(1, manager.getConfigListeners().size());
      verifyAll();
   }

   @Test
   public void testAddConfigListenerExisting()
   {
      resetAll();
      configureLogging();
      
      replayAll();
      
      manager.addConfigListener(mockConfigListener);
      
      // Add a listener that already exists
      manager.addConfigListener(mockConfigListener);

      assertEquals(1, manager.getConfigListeners().size());
      verifyAll();
   }

   @Test
   public void testRemoveConfigListenerValid()
   {
      resetAll();
      configureLogging();
      
      replayAll();

      // Set up the test - add a listener
      manager.addConfigListener(mockConfigListener);
      assertEquals(1, manager.getConfigListeners().size());

      // Remove the listener
      manager.removeConfigListener(mockConfigListener);
      assertEquals(0, manager.getConfigListeners().size());
      
      verifyAll();
   }

   @Test(expected=NullPointerException.class)
   public void testRemoveConfigListenerNull()
   {
      resetAll();
      configureLogging();
      
      replayAll();

      // Set up the test - add a listener
      manager.addConfigListener(mockConfigListener);
      assertEquals(1, manager.getConfigListeners().size());

      // Remove the listener
      manager.removeConfigListener(null);
      assertEquals(0, manager.getConfigListeners().size());
      
      verifyAll();
   }

   @Test
   public void testRemoveConfigListenerInvalid()
   {
      ConfigListener mock2 = createMock(ConfigListener.class);
      resetAll();
      configureLogging();
      
      replayAll();

      // Set up the test - add a listener
      manager.addConfigListener(mockConfigListener);
      assertEquals(1, manager.getConfigListeners().size());

      // Remove the listener
      manager.removeConfigListener(mock2);
      assertEquals(1, manager.getConfigListeners().size());
      
      verifyAll();
   }

   @Test
   public void testGetConfigListeners()
   {
      resetAll();
      configureLogging();
      
      replayAll();

      // Set up the test - add a listener
      manager.addConfigListener(mockConfigListener);
      List<ConfigListener> listeners = manager.getConfigListeners();
      assertNotNull(listeners);
      assertEquals(1, listeners.size());
   }

   @Test
   public void testGetConfigListenersEmpty()
   {
      resetAll();
      configureLogging();
      
      replayAll();

      assertNotNull(manager.getConfigListeners());
      assertEquals(0, manager.getConfigListeners().size());
   }

   @Test
   public void testGetConfig()
   {
      manager = new ConfigManager();
      assertNotNull(manager);
      assertNull(manager.getConfig());
      
      manager.init();
      assertNotNull(manager.getConfig());
   }
   
   
   @Test
   public void testNotifyListeners()
   {
      ConfigListener mock2 = createMock(ConfigListener.class);
      resetAll();
      configureLogging();

      mockConfigListener.notifyConfigChange((Config)anyObject());
      expectLastCall().times(1);
      mock2.notifyConfigChange((Config)anyObject());
      expectLastCall().times(1);
      replayAll();

      // Set up the test - add a listener
      manager.addConfigListener(mockConfigListener);
      manager.addConfigListener(mock2);
      assertEquals(2, manager.getConfigListeners().size());
      
      manager.notifyListeners();
      
      
      verifyAll();
   }

}
