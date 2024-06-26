package jmri.jmrix.loconet.logixng.swing;

import jmri.jmrix.loconet.logixng.ActionUpdateSlots;
import jmri.util.JUnitUtil;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 * Tests for the ExpressionSlotUsageSwing class
 *
 * @author Daniel Bergqvist Copyright 2020
 */
public class ActionUpdateSlotsSwingTest {
    
    @Test
    public void testCtor() {
        ActionUpdateSlotsSwing e = new ActionUpdateSlotsSwing();
        Assert.assertNotNull(e);
    }
    
    @Test
    public void testName() {
        // Test that swing class has same name as parent class
        ActionUpdateSlots e = new ActionUpdateSlots("IQDA1", null, null);
        ActionUpdateSlotsSwing es = new ActionUpdateSlotsSwing();
        Assert.assertEquals(e.getShortDescription(), es.toString());
    }
    
    // The minimal setup for log4J
    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
        JUnitUtil.resetInstanceManager();
        JUnitUtil.resetProfileManager();
        JUnitUtil.initConfigureManager();
        JUnitUtil.initInternalSensorManager();
        JUnitUtil.initInternalTurnoutManager();
        JUnitUtil.initLogixNGManager();
        
        // Temporary let the error messages from this test be shown to the user
//        JUnitAppender.end();
    }
    
    @AfterEach
    public void tearDown() {
        JUnitUtil.deregisterBlockManagerShutdownTask();
        JUnitUtil.tearDown();
    }
    
    
//    private final static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ExpressionSlotUsageSwingTest.class);

}
