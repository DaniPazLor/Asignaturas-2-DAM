/*
 * @(#)CDMPanel.java	1.5 01/03/13
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

package jmapps.registry;

import java.awt.*;
import java.awt.event.*;
import javax.media.CaptureDeviceManager;
import java.util.Vector;
import java.util.Enumeration;
import com.sun.media.util.Registry;
import javax.media.format.*;
import java.io.IOException;
import javax.media.*;
import javax.media.renderer.*;

import com.sun.media.util.JMFI18N;
import com.sun.media.util.DynamicPlugIn;

import jmapps.ui.*;


public class CDMPanel extends JMPanel implements VectorEditor, ActionListener {

    Panel               panelLeft;
    Button              buttonDetect;
    VectorPanel         panelDevices = null;
    Vector              deviceVector;
    Vector              deviceArray;
    Vector              devices;
    TextArea            textArea;
    int                 type;


    public CDMPanel() {
        super();

        Panel   panel;
        Label   label;

        setLayout ( new GridLayout(1,2,6,6) );
        panelLeft = new Panel ( new BorderLayout(6,6) );
        this.add ( panelLeft );
        panel = new Panel ( new FlowLayout(FlowLayout.LEFT) );
        panelLeft.add ( panel, BorderLayout.SOUTH );
        buttonDetect = new Button ( JMFI18N.getResource("jmfregistry.capture.detect") );
        buttonDetect.addActionListener ( this );
        panel.add ( buttonDetect );

        createPanelDevices ();

        panel = new Panel ( new BorderLayout() );
        this.add ( panel );

        label = new Label ( JMFI18N.getResource("jmfregistry.capture.details.label") );
        panel.add ( label, BorderLayout.NORTH );
        textArea = new TextArea ();
        textArea.setEditable ( false );
        panel.add ( textArea, BorderLayout.CENTER );
    }


    public Vector stringArrayToVector(String [] array) {
        Vector v = new Vector();
        if (array != null)
            for (int i = 0; i < array.length; i++)
                v.addElement(array[i]);
        return v;
    }

    public String [] vectorToStringArray(Vector v) {
        String [] sa = new String[v.size()];
        Enumeration e = v.elements();
        int i = 0;

        while (e.hasMoreElements())
            sa[i++] = (String) e.nextElement();
        return sa;
    }

    public Vector getList(int code) {
        devices = CaptureDeviceManager.getDeviceList(null);
	devices = (Vector) devices.clone();
        deviceVector = new Vector(10);
        Enumeration enum = devices.elements();

        while (enum.hasMoreElements()) {
            CaptureDeviceInfo cdi = (CaptureDeviceInfo) enum.nextElement();
            deviceVector.addElement(cdi.getName());
        }
        return deviceVector;
    }

    public void setList(int code, Vector v) {
	deviceVector = v;
    }

    public void commit(int code) {
	int i;
	for (i = 0; i < devices.size(); i++)
	    CaptureDeviceManager.removeDevice((CaptureDeviceInfo) devices.elementAt(i));
	for (i = 0; i < deviceVector.size(); i++) {
	    String name = (String) deviceVector.elementAt(i);
	    for (int j = 0; j < devices.size(); j++) {
		if (((CaptureDeviceInfo)devices.elementAt(j)).getName().equals(name)) {
		    CaptureDeviceManager.addDevice((CaptureDeviceInfo)devices.elementAt(j));
		    break;
		}
	    }
	}
        try {
            CaptureDeviceManager.commit();
        }
        catch (IOException ioe) {
            System.err.println("CaptureDeviceManager.commit() - " + ioe);
        }
    }

    public boolean addToList(int code, String value) {
        // Register the device
        try {
            String className = value;
            Class pic = Class.forName(className);
            Object instance = pic.newInstance();
	    
            if (instance instanceof CaptureDeviceInfo) {
                CaptureDeviceInfo cdi = (CaptureDeviceInfo) instance;
                if (cdi.getName() != null  ||  cdi.getLocator() != null) {
                    if (CaptureDeviceManager.addDevice(cdi)) {
                        return true;
                    }
                }
            }
        }
        catch (Exception e) {
            System.err.println(e);
        }
        catch (Error er) {
            System.err.println(er);
        }
	
        return false;
    }

    public void selectedIndex(int code, int index) {
        String                  text = "";
        String                  name = null;
        CaptureDeviceInfo       cdi = null;

        if ( index >= 0 )
            name = (String) deviceVector.elementAt ( index );
        if ( name != null )
            cdi = CaptureDeviceManager.getDevice ( name );
        if (cdi != null) {
            text += JMFI18N.getResource("jmfregistry.details.name") + " = "
                        + cdi.getName() + "\n\n";
            text += JMFI18N.getResource("jmfregistry.details.locator") + " = "
                        + cdi.getLocator() + "\n\n";
            text += JMFI18N.getResource("jmfregistry.details.outformats")
                        + "---->\n\n" + printFormats(cdi.getFormats()) + "\n\n";
        }
      	textArea.setText(text);
    }

    public void actionPerformed ( ActionEvent event ) {
        String  strAction;

        strAction = event.getActionCommand ();
        if ( strAction.equals(buttonDetect.getActionCommand()) )
            detectDevices ();
    }

    private String printFormats(Object fa) {
        if (! (fa instanceof Format[])) {
            return "null";
        }
        else {
            Format [] formats = (Format []) fa;
            String text = "";
            for (int i = 0; i < formats.length; i++) {
                text += i + ". " + formats[i].getClass().getName() + "\n  " + formats[i] + "\n";
            }
            return text;
        }
    }

    private void createPanelDevices () {
        if ( panelDevices != null )
            panelLeft.remove ( panelDevices );
        panelDevices = new VectorPanel ( JMFI18N.getResource("jmfregistry.capture.vector.label"), this, 0 );
        panelLeft.add ( panelDevices, BorderLayout.CENTER );
        panelLeft.validate();
    }

    private void detectDevices () {
        // Check if VFWAuto or SunVideoAuto is available
        Class       directAudio = null;
        Class       autoAudio = null;
        Class       autoVideo = null;
        Class       autoVideoPlus = null;
        Object      instanceAudio;
        Object      instanceVideo;
        Object      instanceVideoPlus;
        PopupWait   popupWait;

        try {
            directAudio = Class.forName("DirectSoundAuto");
        }
        catch (Exception e) {
        }

        try {
            autoAudio = Class.forName("JavaSoundAuto");
        }
        catch (Exception e) {
        }

        try {
            autoVideo = Class.forName("VFWAuto");
        }
        catch (Exception e) {
        }

        if (autoVideo == null) {
            try {
                autoVideo = Class.forName("SunVideoAuto");
            }
            catch (Exception ee) {
            }
            try {
                autoVideoPlus = Class.forName("SunVideoPlusAuto");
            }
            catch (Exception ee) {
            }
        }
	if (autoVideo == null) {
	    try {
		autoVideo = Class.forName("V4LAuto");
	    } catch (Exception eee) {
	    }
	}

        if ( directAudio == null && autoAudio == null  &&
	     autoVideo == null  &&  autoVideoPlus == null )
            return;

        popupWait = new PopupWait ( getFrame(),
                        JMFI18N.getResource("jmstudio.mssg.capturequery")
                        + "\n" + JMFI18N.getResource("jmfregistry.appname") + " "
                        + JMFI18N.getResource("jmstudio.mssg.lookingcapture") );
        popupWait.setVisible ( true );

        try {
            if ( directAudio != null )
                instanceAudio = directAudio.newInstance ();
            if ( autoAudio != null )
                instanceAudio = autoAudio.newInstance ();
            if ( autoVideo != null )
                instanceVideo = autoVideo.newInstance ();
            if (autoVideoPlus != null)
                instanceVideoPlus = autoVideoPlus.newInstance ();
        }
        catch ( ThreadDeath td ) {
            popupWait.dispose();
            throw td;
        }
        catch (Throwable t) {
            MessageDialog.createErrorDialog ( new Frame(), JMFI18N.getResource("jmstudio.error.capturequery") );
        }

        popupWait.dispose();
        createPanelDevices ();
    }

}


