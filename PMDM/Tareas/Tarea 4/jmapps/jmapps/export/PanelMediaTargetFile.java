/*
 * @(#)PanelMediaTargetFile.java	1.2 01/03/13
 *
 * Copyright (c) 1999-2001 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

package jmapps.export;

import java.awt.*;
import java.awt.event.*;

import com.sun.media.util.JMFI18N;

import jmapps.ui.*;


public class PanelMediaTargetFile extends JMPanel implements ActionListener {

    private TextField   textFile;
    private Button      buttonBrowse;

    public PanelMediaTargetFile () {
    	super ();

    	try {
    	    init ();
    	}
    	catch ( Exception exception ) {
    	    exception.printStackTrace ();
    	}
    }

    public String getFileName () {
    	String			strFile;

    	strFile = textFile.getText ();
        return ( strFile );
    }

    private void init () throws Exception {
    	Panel	panel;
    	Panel	panelDescription;
    	Panel	panelEntry;
    	Label	label;


    	this.setLayout ( new BorderLayout(12,12) );

    	panelDescription = new Panel ( new GridLayout(0,1) );
    	this.add ( panelDescription, BorderLayout.NORTH );
    	panelDescription.add ( new Label(JMFI18N.getResource("jmstudio.export.targetfile.label1")) );
    	panelDescription.add ( new Label(JMFI18N.getResource("jmstudio.export.targetfile.label2")) );

    	panel = new Panel ( new BorderLayout(6,6) );
    	this.add ( panel, BorderLayout.CENTER );

    	panelEntry = new Panel ( new BorderLayout(6,6) );
    	panel.add ( panelEntry, BorderLayout.NORTH );

    	textFile = new TextField ();
    	panelEntry.add ( textFile, BorderLayout.CENTER );

    	buttonBrowse = new Button ( JMFI18N.getResource("jmstudio.export.targetfile.browse") );
    	buttonBrowse.addActionListener ( this );
    	panelEntry.add ( buttonBrowse, BorderLayout.EAST );
    }

    public void actionPerformed ( ActionEvent event ) {
    	Object		objSource;

    	objSource = event.getSource ();
    	if ( objSource == buttonBrowse ) {
    	    browseFile ();
    	}
    }

    private void browseFile () {
    	FileDialog		dlgFile;
    	String			strFile;
    	String			strDir;

    	strFile = textFile.getText ();
    	dlgFile = new FileDialog ( getFrame(),
                        JMFI18N.getResource("jmstudio.export.targetfile.filedialog"),
                        FileDialog.SAVE );
    	dlgFile.setFile ( strFile );
    	dlgFile.show ();

    	strFile = dlgFile.getFile ();
    	strDir = dlgFile.getDirectory ();
    	if ( strFile != null  &&  strFile.length() > 0 ) {
    	    strFile = strDir + strFile;
    	    textFile.setText ( strFile );
    	}
    }


}


