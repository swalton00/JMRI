package jmri.jmrit.operations.locations.tools;

import java.awt.GraphicsEnvironment;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.jupiter.api.Test;

import jmri.InstanceManager;
import jmri.jmrit.operations.OperationsTestCase;
import jmri.jmrit.operations.locations.Location;
import jmri.jmrit.operations.locations.LocationManager;
import jmri.util.JUnitOperationsUtil;
import jmri.util.JUnitUtil;
import jmri.util.swing.JemmyUtil;

/**
 *
 * @author Paul Bender Copyright (C) 2017
 */
public class LocationsByCarTypeFrameTest extends OperationsTestCase {
    
    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        LocationsByCarTypeFrame t = new LocationsByCarTypeFrame();
        Assert.assertNotNull("exists",t);
        t.initComponents();
        Assert.assertTrue("frame visible", t.isVisible());
        
        JUnitUtil.dispose(t);
    }
    
    @Test
    public void testFrameButtons() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        
        JUnitOperationsUtil.initOperationsData();
        LocationManager lmanager = InstanceManager.getDefault(LocationManager.class);
        Location loc = lmanager.getLocationByName("North Industries");
        Assert.assertNotNull("exists", loc);
        
        LocationsByCarTypeFrame lctf = new LocationsByCarTypeFrame();
        Assert.assertNotNull("exists",lctf);
        lctf.initComponents(loc, "Boxcar");
        Assert.assertTrue("frame visible", lctf.isVisible());
        
        Assert.assertTrue("accepts", loc.acceptsTypeName("Boxcar"));
        Assert.assertTrue("accepts", loc.acceptsTypeName("Flat"));
        
        JemmyUtil.enterClickAndLeave(lctf.clearButton);
        JemmyUtil.enterClickAndLeave(lctf.saveButton); 
        Assert.assertFalse("accepts", loc.acceptsTypeName("Boxcar"));
        Assert.assertTrue("accepts", loc.acceptsTypeName("Flat"));
        
        JemmyUtil.enterClickAndLeave(lctf.setButton);
        JemmyUtil.enterClickAndLeave(lctf.saveButton);
        Assert.assertTrue("accepts", loc.acceptsTypeName("Boxcar"));
        Assert.assertTrue("accepts", loc.acceptsTypeName("Flat"));
        
        JUnitUtil.dispose(lctf);

    }
    
    @Test
    public void testFrameCopyCheckBox() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        
        JUnitOperationsUtil.initOperationsData();
        LocationManager lmanager = InstanceManager.getDefault(LocationManager.class);
        Location loc = lmanager.getLocationByName("North Industries");
        Assert.assertNotNull("exists", loc);
        
        LocationsByCarTypeFrame lctf = new LocationsByCarTypeFrame();
        Assert.assertNotNull("exists",lctf);
        lctf.initComponents(loc);
        Assert.assertTrue("frame visible", lctf.isVisible());
        
        Assert.assertTrue("accepts", loc.acceptsTypeName("Boxcar"));
        Assert.assertTrue("accepts", loc.acceptsTypeName("Flat"));
        
        // Boxcar is the 1st car type in the selection box
        JemmyUtil.enterClickAndLeave(lctf.clearButton);
        JemmyUtil.enterClickAndLeave(lctf.saveButton); 
        Assert.assertFalse("accepts", loc.acceptsTypeName("Boxcar"));
        Assert.assertTrue("accepts", loc.acceptsTypeName("Flat"));
        
        JemmyUtil.enterClickAndLeave(lctf.setButton);
        JemmyUtil.enterClickAndLeave(lctf.saveButton);
        Assert.assertTrue("accepts 2", loc.acceptsTypeName("Boxcar"));
        Assert.assertTrue("accepts 2", loc.acceptsTypeName("Flat"));
        
        lctf.typeComboBox.setSelectedItem("Flat");
        JemmyUtil.enterClickAndLeave(lctf.clearButton);
        JemmyUtil.enterClickAndLeave(lctf.saveButton); 
        lctf.copyComboBox.setSelectedItem("Flat");
        JemmyUtil.enterClickAndLeave(lctf.copyCheckBox);
        lctf.typeComboBox.setSelectedItem("Boxcar");
        
        JemmyUtil.enterClickAndLeaveThreadSafe(lctf.saveButton); 
        // the save should have opened a dialog window
        JemmyUtil.pressDialogButton(lctf, Bundle.getMessage("CopyCarTypeTitle"), Bundle.getMessage("ButtonYes"));
        JemmyUtil.waitFor(lctf);
        
        Assert.assertFalse("accepts 3", loc.acceptsTypeName("Boxcar"));
        Assert.assertFalse("accepts 3", loc.acceptsTypeName("Flat"));
        
        JUnitUtil.dispose(lctf);

    }

    // private final static Logger log = LoggerFactory.getLogger(LocationsByCarTypeFrameTest.class);

}
