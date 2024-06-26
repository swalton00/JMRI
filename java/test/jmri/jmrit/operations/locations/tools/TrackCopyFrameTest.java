package jmri.jmrit.operations.locations.tools;

import java.awt.GraphicsEnvironment;
import java.text.MessageFormat;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.jupiter.api.Test;

import jmri.jmrit.operations.OperationsTestCase;
import jmri.jmrit.operations.locations.Location;
import jmri.jmrit.operations.locations.Track;
import jmri.util.JUnitOperationsUtil;
import jmri.util.JUnitUtil;
import jmri.util.swing.JemmyUtil;

/**
 *
 * @author Paul Bender Copyright (C) 2017
 */
public class TrackCopyFrameTest extends OperationsTestCase {

    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        TrackCopyFrame t = new TrackCopyFrame(null, null);
        Assert.assertNotNull("exists",t);
        JUnitUtil.dispose(t);
    }
    
    @Test
    public void testButtons() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        
        Location acton = JUnitOperationsUtil.createOneNormalLocation("Acton");
        TrackCopyFrame tcf = new TrackCopyFrame(null, acton);
        Assert.assertNotNull("exists",tcf);
        tcf.setVisible(true);
        
        JemmyUtil.enterClickAndLeaveThreadSafe(tcf.copyButton);
        
        // error dialog window show appear
        JemmyUtil.pressDialogButton(tcf, MessageFormat.format(Bundle
                .getMessage("CanNotTrack"), new Object[]{Bundle.getMessage("ButtonCopy")}), Bundle.getMessage("ButtonOK"));
        JemmyUtil.waitFor(tcf);
        
        // enter a name for the new track
        tcf.trackNameTextField.setText("Test track name");
        
        JemmyUtil.enterClickAndLeaveThreadSafe(tcf.copyButton);
        
        // error dialog window show appear
        JemmyUtil.pressDialogButton(tcf, Bundle.getMessage("SelectTrackToCopy"), Bundle.getMessage("ButtonOK"));
        JemmyUtil.waitFor(tcf);
        
        // select a track to copy
        tcf.locationBox.setSelectedIndex(1);
        tcf.trackBox.setSelectedIndex(1);
        JemmyUtil.enterClickAndLeave(tcf.moveRollingStockCheckBox);
        JemmyUtil.enterClickAndLeave(tcf.deleteTrackCheckBox);
        
        JemmyUtil.enterClickAndLeave(tcf.copyButton);
        Assert.assertNotNull(acton.getTrackByName("Test track name", Track.INTERCHANGE));

        JUnitUtil.dispose(tcf);
    }
    
    @Test
    public void testButtonsDestination() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        
        // create 2 locations, copy track from Acton to Boston
        JUnitOperationsUtil.createOneNormalLocation("Acton");
        Location boston = JUnitOperationsUtil.createOneNormalLocation("Boston");
        
        TrackCopyFrame tcf = new TrackCopyFrame(null, null);
        Assert.assertNotNull("exists",tcf);
        tcf.setVisible(true);
        
        // confirm that copy button is disabled
        Assert.assertFalse(tcf.copyButton.isEnabled());
        
        // select destination
        tcf.destinationBox.setSelectedIndex(2);
        
        // confirm that copy button is enabled
        Assert.assertTrue(tcf.copyButton.isEnabled());
        
        JemmyUtil.enterClickAndLeaveThreadSafe(tcf.copyButton);
        
        // error dialog window show appear
        JemmyUtil.pressDialogButton(tcf, MessageFormat.format(Bundle
                .getMessage("CanNotTrack"), new Object[]{Bundle.getMessage("ButtonCopy")}), Bundle.getMessage("ButtonOK"));
        JemmyUtil.waitFor(tcf);
        
        // enter a name for the new track
        tcf.trackNameTextField.setText("Test track name");
        
        JemmyUtil.enterClickAndLeaveThreadSafe(tcf.copyButton);
        
        // error dialog window show appear
        JemmyUtil.pressDialogButton(tcf, Bundle.getMessage("SelectTrackToCopy"), Bundle.getMessage("ButtonOK"));
        JemmyUtil.waitFor(tcf);
        
        // select a track to copy
        tcf.locationBox.setSelectedIndex(1);
        tcf.trackBox.setSelectedIndex(1);
        JemmyUtil.enterClickAndLeave(tcf.moveRollingStockCheckBox);
        JemmyUtil.enterClickAndLeave(tcf.deleteTrackCheckBox);
        
        JemmyUtil.enterClickAndLeave(tcf.copyButton);
        Assert.assertNotNull(boston.getTrackByName("Test track name", Track.INTERCHANGE));

        JUnitUtil.dispose(tcf);
    }
    
    @Test
    public void testCopyNameTooLong() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        
        Location acton = JUnitOperationsUtil.createOneNormalLocation("Acton");
        TrackCopyFrame tcf = new TrackCopyFrame(null, acton);
        Assert.assertNotNull("exists",tcf);
        tcf.setVisible(true);
        
        JemmyUtil.enterClickAndLeaveThreadSafe(tcf.copyButton);
        // error dialog window show appear
        JemmyUtil.pressDialogButton(tcf, MessageFormat.format(Bundle
                .getMessage("CanNotTrack"), new Object[]{Bundle.getMessage("ButtonCopy")}), Bundle.getMessage("ButtonOK"));
        JemmyUtil.waitFor(tcf);
        
        // enter a name for the new track to fail longer than 25 characters
        tcf.trackNameTextField.setText("Very Long Test Track Name X");
        
        JemmyUtil.enterClickAndLeaveThreadSafe(tcf.copyButton);
        // error dialog window show appear
        JemmyUtil.pressDialogButton(tcf, MessageFormat.format(Bundle
                .getMessage("CanNotTrack"), new Object[]{Bundle.getMessage("ButtonCopy")}), Bundle.getMessage("ButtonOK"));
        JemmyUtil.waitFor(tcf);
        
        Assert.assertNull(acton.getTrackByName("Very Long Test Track Name X", null));
        JUnitUtil.dispose(tcf);
    }
    
    @Test
    public void testCopyNameAlreadyExists() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        
        Location acton = JUnitOperationsUtil.createOneNormalLocation("Acton");
        TrackCopyFrame tcf = new TrackCopyFrame(null, acton);
        Assert.assertNotNull("exists",tcf);
        tcf.setVisible(true);
        
        JemmyUtil.enterClickAndLeaveThreadSafe(tcf.copyButton);
        // error dialog window show appear
        JemmyUtil.pressDialogButton(tcf, MessageFormat.format(Bundle
                .getMessage("CanNotTrack"), new Object[]{Bundle.getMessage("ButtonCopy")}), Bundle.getMessage("ButtonOK"));
        JemmyUtil.waitFor(tcf);
        
        // enter the same name
        tcf.trackNameTextField.setText(acton.getTracksList().get(2).getName());
        
        JemmyUtil.enterClickAndLeaveThreadSafe(tcf.copyButton);
        // error dialog window show appear
        JemmyUtil.pressDialogButton(tcf, MessageFormat.format(Bundle
                .getMessage("CanNotTrack"), new Object[]{Bundle.getMessage("ButtonCopy")}), Bundle.getMessage("ButtonOK"));
        JemmyUtil.waitFor(tcf);
        JUnitUtil.dispose(tcf);
    }
    
    @Test
    public void testSaveButton() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        
        Location acton = JUnitOperationsUtil.createOneNormalLocation("Acton");
        TrackCopyFrame tcf = new TrackCopyFrame(null, acton);
        Assert.assertNotNull("exists",tcf);
        tcf.setVisible(true);
        
        JemmyUtil.enterClickAndLeave(tcf.saveButton);
        JUnitUtil.dispose(tcf);
    }

    // private final static Logger log = LoggerFactory.getLogger(TrackCopyFrameTest.class);

}
