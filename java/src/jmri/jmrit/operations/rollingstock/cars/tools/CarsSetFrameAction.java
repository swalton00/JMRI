package jmri.jmrit.operations.rollingstock.cars.tools;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTable;

/**
 * Swing action to create and register a CarsSetFrame object.
 *
 * @author Bob Jacobsen Copyright (C) 2001
 * @author Daniel Boudreau Copyright (C) 2010
 */
public class CarsSetFrameAction extends AbstractAction {

    JTable _carsTable;

    public CarsSetFrameAction(JTable carsTable) {
        super(Bundle.getMessage("TitleSetCars"));
        _carsTable = carsTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // create a car table frame
        CarsSetFrame csf = new CarsSetFrame();
        csf.initComponents(_carsTable);
    }
}


