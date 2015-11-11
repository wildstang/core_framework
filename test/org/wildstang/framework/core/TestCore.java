package org.wildstang.framework.core;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.powermock.reflect.Whitebox;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.core.Core;
import org.wildstang.framework.core.Inputs;
import org.wildstang.framework.core.Outputs;
import org.wildstang.framework.io.InputManager;
import org.wildstang.framework.io.OutputManager;
import org.wildstang.framework.logger.StateTracker;
import org.wildstang.framework.subsystems.SubsystemManager;

public class TestCore extends BaseTest
{
   Core core;
   
   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(false);

      core = new Core(NullInputFactory.class, NullOutputFactory.class);
      
      Whitebox.setInternalState(Core.class, getMockLogger());
   }

   @After
   public void tearDown() throws Exception
   {
   }

   @Test
   public void testCore()
   {
      resetAll();
      configureLogging();
      replayAll();
      
      core = new Core(NullInputFactory.class, NullOutputFactory.class);
      assertNotNull(core);
   }

   @Test(expected=NullPointerException.class)
   public void testCoreNullInputFactory()
   {
      resetAll();
      configureLogging();
      replayAll();
      
      core = new Core(null, NullOutputFactory.class);
      assertNotNull(core);
   }

   @Test(expected=NullPointerException.class)
   public void testCoreNullOutputFactory()
   {
      resetAll();
      configureLogging();
      replayAll();
      
      core = new Core(NullInputFactory.class, null);
      assertNotNull(core);
   }

   @Test
   public void testInit()
   {
      resetAll();
      configureLogging();
      replayAll();

      core = new Core(NullInputFactory.class, NullOutputFactory.class);
      
      assertNotNull(Core.getConfigManager());
      assertNotNull(Core.getInputManager());
      assertNotNull(Core.getOutputManager());
      assertNotNull(Core.getStateTracker());
      assertNotNull(Core.getSubsystemManager());
   }

   @Test
   public void testCreateOutputsValid()
   {
      resetAll();
      configureLogging();
      replayAll();

      core.createOutputs(DummyOutputs.values());
      assertEquals(DummyOutputs.values().length, Core.getOutputManager().size());
   }

   @Test
   public void testCreateOutputsValidNoTracking()
   {
      resetAll();
      configureLogging();
      replayAll();

      Core.getStateTracker().stopTrackingState();
      core.createOutputs(DummyOutputs.values());
      assertEquals(DummyOutputs.values().length, Core.getOutputManager().size());
   }

   @Test(expected=NullPointerException.class)
   public void testCreateOutputsNull()
   {
      resetAll();
      configureLogging();
      replayAll();

      core.createOutputs(null);
   }

   @Test
   public void testCreateOutputsEmpty()
   {
      resetAll();
      configureLogging();
      replayAll();

      core.createOutputs(new Outputs[]{});
      assertEquals(0, Core.getOutputManager().size());
   }

   @Test
   public void testCreateInputsValid()
   {
      resetAll();
      configureLogging();
      replayAll();

      core.createInputs(DummyInputs.values());
      assertEquals(DummyInputs.values().length, Core.getInputManager().size());
   }

   @Test
   public void testCreateInputsValidNoTracking()
   {
      resetAll();
      configureLogging();
      replayAll();

      Core.getStateTracker().stopTrackingState();
      core.createInputs(DummyInputs.values());
      assertEquals(DummyInputs.values().length, Core.getInputManager().size());
   }

   @Test(expected=NullPointerException.class)
   public void testCreateInputsNull()
   {
      resetAll();
      configureLogging();
      replayAll();

      core.createInputs(null);
   }

   @Test
   public void testCreateInputsEmpty()
   {
      resetAll();
      configureLogging();
      replayAll();

      core.createInputs(new Inputs[]{});
      assertEquals(0, Core.getInputManager().size());
   }

   @Test
   public void testCreateFactory()
   {
      resetAll();
      configureLogging();
      replayAll();

      Object o = core.createObject(String.class);
      assertTrue(o instanceof String);
   }

   @Test(expected=NullPointerException.class)
   public void testCreateFactoryNull()
   {
      resetAll();
      configureLogging();
      replayAll();

      Object o = core.createObject(null);
      assertTrue(o instanceof String);
   }

   @Test
   public void testExecuteUpdate()
   {
      // Create mocks for the static obejcts involved - we want to test behaviour, not state
      Whitebox.setInternalState(Core.class, createNiceMock(InputManager.class));
      Whitebox.setInternalState(Core.class, createNiceMock(OutputManager.class));
      Whitebox.setInternalState(Core.class, createNiceMock(SubsystemManager.class));
      Whitebox.setInternalState(Core.class, createNiceMock(StateTracker.class));

      resetAll();
      Core.getStateTracker().beginCycle((Date)anyObject());
      expectLastCall().once();
      Core.getInputManager().update();
      expectLastCall().once();
      Core.getSubsystemManager().update();
      expectLastCall().once();
      Core.getOutputManager().update();
      expectLastCall().once();
      Core.getStateTracker().endCycle();
      expectLastCall().once();
      
      replayAll();
      
      core.executeUpdate();
      
      verifyAll();
   }

}
