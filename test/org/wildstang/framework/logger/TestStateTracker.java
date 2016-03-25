package org.wildstang.framework.logger;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.logger.StateTracker;


public class TestStateTracker extends BaseTest
{

   private static final String NAME = "IMU.x";
   private static final String PARENT = "IMU";
   private static final double VALUE = 0.55;

   private static final String IO_NAME = "Test Name";
   private static final String TYPE = "Analog";
   private static final String DIRECTION = "Input";
   private static final String PORT = "BB_10";
   
   private StateTracker stateTracker;
   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(false);
      
      stateTracker = new StateTracker();

      Whitebox.setInternalState(StateTracker.class, getMockLogger());

   }

   @After
   public void tearDown() throws Exception
   {
      stateTracker.reset();
   }

   @Test
   public void testInit()
   {
      resetAll();
      configureLogging();
      replayAll();
      
      stateTracker = new StateTracker();
      stateTracker.init();
      
      assertTrue(true);
   }

   // Begin cycle
   // 1. Test start of new cycle
   // 2. Test within a cycle
   // 3. Test After end cycle (begin, end, begin)
   // 4. Test after shutdown
   @Test
   public void testBeginCycleStart()
   {
      resetAll();
      configureLogging();
      replayAll();
      assertNull(stateTracker.getCurrentState());
      stateTracker.beginCycle(new Date());
      assertNotNull(stateTracker.getCurrentState());
   }

   @Test(expected=IllegalStateException.class)
   public void testBeginCycleWithinCycle()
   {
      resetAll();
      configureLogging();
      replayAll();
      assertNull(stateTracker.getCurrentState());
      stateTracker.beginCycle(new Date());
      assertNotNull(stateTracker.getCurrentState());
      stateTracker.beginCycle(new Date());

   }

   @Test
   public void testBeginCycleNewCycle()
   {
      resetAll();
      configureLogging();
      replayAll();
      assertNull(stateTracker.getCurrentState());

      stateTracker.beginCycle(new Date());
      assertNotNull(stateTracker.getCurrentState());
      
      stateTracker.endCycle();
      assertNull(stateTracker.getCurrentState());
      
      stateTracker.beginCycle(new Date());
      assertNotNull(stateTracker.getCurrentState());
   }

   @Test
   public void testBeginCycleNotTrackingState()
   {
      resetAll();
      configureLogging();
      replayAll();
      assertNull(stateTracker.getCurrentState());
      stateTracker.stopTrackingState();
      
      stateTracker.beginCycle(new Date());
      assertNull(stateTracker.getCurrentState());
   }

//-----------------------------------
   
   // TODO: Add tests with invalid parameters
   // 1. Add state within cycle
   // 2. Add state before cycle
   // 3. Add state after cycle

   @Test
   public void testAddStateValid()
   {
      resetAll();
      configureLogging();
      replayAll();
      assertNull(stateTracker.getCurrentState());

      stateTracker.beginCycle(new Date());
      assertNotNull(stateTracker.getCurrentState());
      
      stateTracker.addState(NAME, PARENT, VALUE);
      
      assertEquals(1, stateTracker.getCurrentState().getStateList().size());
      assertEquals(0, stateTracker.getStateList().size());

      stateTracker.endCycle();
      assertNull(stateTracker.getCurrentState());
      assertEquals(1, stateTracker.getStateList().size());
   }

   @Test(expected=IllegalStateException.class)
   public void testAddStateBeforeCycle()
   {
      resetAll();
      configureLogging();
      replayAll();
      assertNull(stateTracker.getCurrentState());

      stateTracker.addState(NAME, PARENT, VALUE);
   }

   @Test(expected=IllegalStateException.class)
   public void testAddStateAfterCycle()
   {
      resetAll();
      configureLogging();
      replayAll();
      assertNull(stateTracker.getCurrentState());

      stateTracker.beginCycle(new Date());
      assertNotNull(stateTracker.getCurrentState());
      
      stateTracker.endCycle();

      stateTracker.addState(NAME, PARENT, VALUE);
   }

   @Test
   public void testAddStateNotTrackingState()
   {
      resetAll();
      configureLogging();
      replayAll();
      assertNull(stateTracker.getCurrentState());
      stateTracker.stopTrackingState();
      
      stateTracker.beginCycle(new Date());
      assertNull(stateTracker.getCurrentState());

      stateTracker.addState(NAME, PARENT, VALUE);
      assertEquals(0, stateTracker.getStateList().size());
   }

//-----------------------------------

   // End Cycle
   // 1. Test end of cycle (begin, end)
   // 2. Test subsequent cycles (begin, end, begin, end)
   // 3. Test After end cycle (begin, end, end)
   // 4. Test while not logging state
   @Test
   public void testEndCycleNormal()
   {
      resetAll();
      configureLogging();
      replayAll();
      assertNull(stateTracker.getCurrentState());

      stateTracker.beginCycle(new Date());
      assertNotNull(stateTracker.getCurrentState());
      
      stateTracker.endCycle();
      assertNull(stateTracker.getCurrentState());
   }

   @Test
   public void testEndCycleRepeated()
   {
      resetAll();
      configureLogging();
      replayAll();
      assertNull(stateTracker.getCurrentState());

      stateTracker.beginCycle(new Date());
      assertNotNull(stateTracker.getCurrentState());
      
      stateTracker.endCycle();
      assertNull(stateTracker.getCurrentState());

      stateTracker.beginCycle(new Date());
      assertNotNull(stateTracker.getCurrentState());
      
      stateTracker.endCycle();
      assertNull(stateTracker.getCurrentState());
   }

   @Test(expected=IllegalStateException.class)
   public void testEndCycleEndAfterEnd()
   {
      resetAll();
      configureLogging();
      replayAll();
      assertNull(stateTracker.getCurrentState());

      stateTracker.beginCycle(new Date());
      assertNotNull(stateTracker.getCurrentState());
      
      stateTracker.endCycle();
      assertNull(stateTracker.getCurrentState());

      stateTracker.endCycle();

   }

   @Test
   public void testEndCycleNottrackingState()
   {
      resetAll();
      configureLogging();
      replayAll();
      assertNull(stateTracker.getCurrentState());
      stateTracker.stopTrackingState();
      
      stateTracker.beginCycle(new Date());
      assertNull(stateTracker.getCurrentState());
      
      stateTracker.endCycle();
      assertNull(stateTracker.getCurrentState());
   }

//-----------------------------------

   @Test
   public void testAddIOInfo()
   {
      resetAll();
      configureLogging();
      replayAll();

      stateTracker.addIOInfo(IO_NAME, TYPE, DIRECTION, PORT);
      assertEquals(1, stateTracker.getIoSet().getInfoList().size());
   }

   @Test
   public void testGetIoSet()
   {
      assertNotNull(stateTracker.getIoSet());
   }

   @Test
   public void testIsTrackingState()
   {
      stateTracker.startTrackingState();
      
      assertTrue(stateTracker.isTrackingState());
   }

   @Test
   public void testGetStateList()
   {
      assertNotNull(stateTracker.getStateList());
   }

}
