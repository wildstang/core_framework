package org.wildstang.framework.subsystems;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.core.Core;
import org.wildstang.framework.core.NullInputFactory;
import org.wildstang.framework.core.NullOutputFactory;
import org.wildstang.framework.subsystems.Subsystem;
import org.wildstang.framework.subsystems.SubsystemManager;


public class TestSubsystemManager extends BaseTest
{

   Subsystem mockSubsystem;
   SubsystemManager manager;

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      mockSubsystem = createNiceMock(Subsystem.class);
      manager = new SubsystemManager();
      manager.init();
      
      Whitebox.setInternalState(SubsystemManager.class, getMockLogger());
   }

   @After
   public void tearDown() throws Exception
   {
      manager.removeAll();
   }

   @Test
   public void testGetInstance()
   {
      resetAll();
      configureLogging();
      replayAll();
      assertNotNull(manager);
      
   }

   @Test
   public void testUpdate()
   {
      // TESTS
      // 1. Call update, validate update was called once on the Subsystem
      // 3. Call update with multiple Subsystems added to manager

      // Setup - add the Input
      resetAll();
      configureLogging();
      expect(mockSubsystem.getName()).andReturn("TestSubsystem").anyTimes();
      
      replayAll();
      manager.addSubsystem(mockSubsystem);

      // validate number of subsystems
      assertEquals(1, manager.size());

      resetAll();
      
      // 1. Call update, validate update was called once on the Subsystem
      configureLogging();
      expect(mockSubsystem.getName()).andReturn("TestSubsystem").anyTimes();
      mockSubsystem.update();
      expectLastCall().once();
      replayAll();
      
      manager.update();
      verifyAll();

      
//-----------------------------------------

      // 3. Call update with multiple Inputs added to manager
      resetAll();
      Subsystem mockSubsystem2 = createNiceMock(Subsystem.class);
      manager.addSubsystem(mockSubsystem2);
      
      configureLogging();
      expect(mockSubsystem2.getName()).andReturn("TestInput2").anyTimes();
      mockSubsystem.update();
      expectLastCall().times(1);
      mockSubsystem2.update();
      expectLastCall().times(1);
      replayAll();

      assertEquals(2, manager.size());
      
      manager.update();
      verifyAll();

   }

   @Test
   public void testAddSubsystem()
   {
      // TESTS
      // 1. Call add with null input
      // 2. Call add with valid subsystem
      // 3. Call add with input that already exists in manager

      // 1. Call add with null input
      resetAll();
      configureLogging();
      replayAll();
      boolean thrown = false;
      try
      {
         manager.addSubsystem(null);
      }
      catch (NullPointerException npe)
      {
         thrown = true;
      }
      assertTrue(thrown);
      assertEquals(0, manager.size());

      
//-----------------------------------------
      
      // 2. Call add with valid subsystem
      resetAll();
      configureLogging();
      replayAll();

      manager.addSubsystem(mockSubsystem);
      
      assertEquals(1, manager.size());


//-----------------------------------------
      // 3. Call add with input that already exists in manager
      resetAll();
      configureLogging();
      replayAll();

      manager.addSubsystem(mockSubsystem);
      
      assertEquals(1, manager.size());
   }

   @Test
   public void testRemoveSubsystem()
   {
      // TESTS
      // 1. Call remove with null input
      // 2. Call remove with valid subsystem
      // 3. Call remove with input that doesn't exist in manager

      // 1. Call remove with null input
      resetAll();
      configureLogging();
      replayAll();
      boolean thrown = false;
      try
      {
         manager.removeSubsystem(null);
      }
      catch (NullPointerException npe)
      {
         thrown = true;
      }
      assertTrue(thrown);
      assertEquals(0, manager.size());

      
//-----------------------------------------
      
      // 2. Call remove with valid subsystem
      
      resetAll();
      configureLogging();
      replayAll();

      Subsystem mockSubsystem2 = createNiceMock(Subsystem.class);
      manager.addSubsystem(mockSubsystem);
      manager.addSubsystem(mockSubsystem2);
      
      assertEquals(2, manager.size());

      
      manager.removeSubsystem(mockSubsystem2);
      assertEquals(1, manager.size());

      
//-----------------------------------------
      
      // 3. Call remove with input that doesn't exist in manager
      resetAll();
      configureLogging();
      replayAll();

      assertEquals(1, manager.size());

      
      manager.removeSubsystem(mockSubsystem2);
      assertEquals(1, manager.size());
   }

   @Test
   public void testRemoveAll()
   {
      resetAll();
      configureLogging();
      replayAll();

      Subsystem mockSubsystem2 = createNiceMock(Subsystem.class);
      manager.addSubsystem(mockSubsystem);
      manager.addSubsystem(mockSubsystem2);
      
      assertEquals(2, manager.size());

      
      manager.removeAll();
      assertEquals(0, manager.size());
   }

   @Test(expected=NullPointerException.class)
   public void testGetSubsystemNull()
   {
      Subsystem mockSub = createMock(Subsystem.class);
      resetAll();
      configureLogging();
      expect(mockSub.getName()).andReturn("TestSubsystem").anyTimes();
      replayAll();
      
      manager.addSubsystem(mockSub);
      
      manager.getSubsystem(null);
      
   }

   @Test
   public void testGetSubsystemEmpty()
   {
      Subsystem mockSub = createMock(Subsystem.class);
      resetAll();
      configureLogging();
      expect(mockSub.getName()).andReturn("TestSubsystem").anyTimes();
      replayAll();
      
      manager.addSubsystem(mockSub);
      
      Subsystem result = manager.getSubsystem("");
      
      assertNull(result);
      verifyAll();

   }

   @Test
   public void testGetSubsystem()
   {
      Subsystem mockSub = createMock(Subsystem.class);
      resetAll();
      configureLogging();
      expect(mockSub.getName()).andReturn("TestSubsystem").anyTimes();
      replayAll();
      
      manager.addSubsystem(mockSub);
      
      Subsystem result = manager.getSubsystem("TestSubsystem");
      
      assertEquals(result, mockSub);
      verifyAll();

   }

   @Test
   public void testGetSubsystems()
   {
      Subsystem mockSub = createMock(Subsystem.class);
      resetAll();
      configureLogging();
      expect(mockSub.getName()).andReturn("TestSubsystem").anyTimes();
      replayAll();
      
      manager.addSubsystem(mockSub);
      
      List<Subsystem> list = manager.getSubsystems();
      
      assertEquals(manager.size(), list.size());
      verifyAll();

   }

   @Test
   public void testSelfTestAll()
   {
      Subsystem mockSub = createMock(Subsystem.class);
      resetAll();
      configureLogging();
      expect(mockSub.getName()).andReturn("TestSubsystem").anyTimes();
      mockSub.selfTest();
      expectLastCall().once();
      replayAll();

      Core core = new Core(NullInputFactory.class, NullOutputFactory.class);
      
      manager.addSubsystem(mockSub);
      
      manager.selfTestAll();
      
      verifyAll();

   }

}
