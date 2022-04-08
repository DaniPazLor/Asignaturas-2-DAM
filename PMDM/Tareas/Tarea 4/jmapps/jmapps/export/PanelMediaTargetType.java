/*
 * @(#)PanelMediaTargetType.java	1.2 01/03/13
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

import com.sun.media.util.JMFI18N;

import jmapps.ui.*;


public class PanelMediaTargetType extends JMPanel {

    public static final String		TYPE_FILE = JMFI18N.getResource("jmstudio.export.type.file");
    public static final String		TYPE_NETWORK = JMFI18N.getResource("jmstudio.export.type.network");
    public static final String		TYPE_SCREEN = JMFI18N.getResource("jmstudio.export.type.screen");
    public static final String		TYPE_OTHER = JMFI18N.getResource("jmstudio.export.type.other");

    private CheckboxGroup	groupTargetType;
    private Checkbox		radioFile;
    private Checkbox		radioNetwork;
    private Checkbox		radioScreen;


    public PanelMediaTargetType () {
    	super ();

    	try {
    	    init ();
    	}
    	catch ( Exception exception ) {
    	    exception.printStackTrace ();
    	}
    }

    public String getType () {
    	String		strType;

    	if ( radioFile.getState() )
    	    strType = TYPE_FILE;
    	else if ( radioNetwork.getState() )
    	    strType = TYPE_NETWORK;
    	else if ( radioScreen.getState() )
    	    strType = TYPE_SCREEN;
    	else
    	    strType = TYPE_OTHER;

    	return ( strType );
    }

    private void init () throws Exception {
    	Panel	panel;
    	Panel	panelDescription;
    	Panel	panelChoice;
        Panel   panelTemp;
    	Label	label;


    	this.setLayout ( new BorderLayout(12,12) );

    	panelDescription = new Panel ( new GridLayout(0,1) );
    	this.add ( panelDescription, BorderLayout.NORTH );

    	panelDescription.add ( new Label(JMFI18N.getResource("jmstudio.export.type.label1")) );
    	panelDescription.add ( new Label(JMFI18N.getResource("jmstudio.export.type.label2")) );

    	panel = new Panel ( new BorderLayout(6,6) );
    	this.add ( panel, BorderLayout.CENTER );

    	panelTemp = new Panel ( new BorderLayout(6,6) );
    	panel.add ( panelTemp, BorderLayout.NORTH );

    	panelChoice = new Panel ( new GridLayout(0,1,6,6) );
    	panelTemp.add ( panelChoice, BorderLayout.WEST );

    	groupTargetType = new CheckboxGroup ();

    	panelTemp = new Panel ( new BorderLayout(6,6) );
    	panelChoice.add ( panelTemp );
    	radioFile = new Checkbox ( TYPE_FILE, groupTargetType, true );
    	panelTemp.add ( radioFile, BorderLayout.WEST );

    	panelTemp = new Panel ( new BorderLayout(6,6) );
    	panelChoice.add ( panelTemp );
    	radioNetwork = new Checkbox ( TYPE_NETWORK, groupTargetType, false );
    	panelTemp.add ( radioNetwork, BorderLayout.WEST );

    	panelTemp = new Panel ( new BorderLayout(6,6) );
    	panelChoice.add ( panelTemp );
    	radioScreen = new Checkbox ( TYPE_SCREEN, groupTargetType, false );
    	panelTemp.add ( radioScreen, BorderLayout.WEST );

    }

}


