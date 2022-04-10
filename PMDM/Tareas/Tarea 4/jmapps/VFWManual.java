/*
 * @(#)VFWManual.java	1.7 01/03/13
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

import javax.media.*;
import javax.media.Format;
import javax.media.format.VideoFormat;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import com.sun.media.vfw.*;
import com.sun.media.util.WindowUtil;

public class VFWManual extends Frame {

    CaptureDeviceInfo [] devices = null;
    int currentID = -1;
    
    public VFWManual() {
	super("Register VFW Caputure Devices");

	setLayout(new FlowLayout());
	int nDevices = 0;

	removeCurrentDevices();
	for (int i = 0; i < 10; i++) {
	    String name = VFWCapture.capGetDriverDescriptionName(i);
	    if (name != null && name.length() > 1) {
		Button device = new Button(name);
		add(device);
		device.addActionListener( new DBListener(i) );
		nDevices++;
	    }
	}

	devices = new CaptureDeviceInfo[nDevices];
	
	pack();
	addWindowListener( new WindowAdapter() {
	    public void windowClosing(WindowEvent we) {
		if (currentID != -1) {
		    System.err.println("Need to close other window first");
		    return;
		}
		dispose();
		System.exit(0);
	    }
	} );
    }

    private void removeCurrentDevices() {
        Vector deviceList = (Vector) CaptureDeviceManager.getDeviceList(null).clone();
	Enumeration enum = deviceList.elements();
	while (enum.hasMoreElements()) {
	    CaptureDeviceInfo cdi = (CaptureDeviceInfo) enum.nextElement();
	    String name = cdi.getName();
	    if (name.startsWith("vfw:"))
		CaptureDeviceManager.removeDevice(cdi);
	}
    }
	
    public void createDialog(int id) {
	if (currentID == id)
	    return;
	currentID = id;
	Thread t = new Thread( new Runnable() {
	    public void run() {
		FormatsDialog fd = new FormatsDialog(currentID);
		fd.setVisible(true);
		CaptureDeviceInfo cdi = fd.getDeviceInfo();
		if (cdi != null) {
		    System.err.println("CaptureDeviceInfo = " + cdi.getName() + " " +
				       cdi.getLocator());
		}
		
		currentID = -1;
	    }
	} );
	t.start();
	// Create a CaptureDeviceInfo....
    }

    class FormatsDialog extends Frame {

	int id;
	Button bVideoFormat, bDone, bCancel;
	Vector formats = new Vector();
	Frame capFrame;
	int capHandle;
	Integer lock = new Integer(1);
	boolean finished = false;
	boolean needVideoFormatDialog = false;
	
	public FormatsDialog(int id) {
	    super(VFWCapture.capGetDriverDescriptionName(id));
	    this.id = id;
	    setLayout(new FlowLayout());
	    bVideoFormat = new Button("VideoFormat Dialog");
	    bDone = new Button("Done");
	    bCancel = new Button("Cancel");
	    capFrame = new Frame("CaptureWindow");
	    capFrame.setBounds(0, 0, 380, 280);
	    capFrame.addNotify();
	    capFrame.setVisible(false);
	    capHandle = VFWCapture.capCreateCaptureWindow("Test",
							  WindowUtil.getWindowHandle(capFrame),
							  0, 0, 320, 240,
							  VFWCapture.getNextID());
	    if (capHandle == 0) {
		capFrame.dispose();
		throw new Error("Could not create capture window");
	    }
	    
	    VFWCapture.capDriverConnect(capHandle, id);
	    
	    add(bVideoFormat);
	    add(bDone);
	    add(bCancel);
	    pack();

	    addWindowListener( new WindowAdapter() {
		public void windowClosing(WindowEvent we) {
		    synchronized (lock) {
			finished = true;
			lock.notify();
		    }
		}
	    } );

	    bDone.addActionListener( new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
		    synchronized (lock) {
			finished = true;
			lock.notify();
		    }
		}
	    } );

	    bCancel.addActionListener( new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
		    synchronized (lock) {
			finished = true;
			lock.notify();
		    }
		}
	    } );

	    bVideoFormat.addActionListener( new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
		    needVideoFormatDialog = true;
		    synchronized (lock) {
			lock.notify();
		    }
		}
	    } );
	}

	public void mydispose() {
	    VFWCapture.capDriverDisconnect(capHandle);
	    capFrame.dispose();
	    dispose();
	    System.err.println("Disconnected driver");
	}
	
	public void doVideoFormatDialog() {
	    // TODO: get the current format ...    
	    VFWCapture.capDlgVideoFormat(capHandle);
	    BitMapInfo bmi = new BitMapInfo();
	    VFWCapture.capGetVideoFormat(capHandle, bmi);
	    VideoFormat vf = bmi.createVideoFormat(byte[].class);
	    System.err.println("Format = " + vf);

	    Enumeration enum = formats.elements();
	    while (enum.hasMoreElements()) {
		Format f = (Format) enum.nextElement();
		if (f.equals(vf))
		    return;
	    }
	    formats.addElement(vf);	    
	}


	public CaptureDeviceInfo getDeviceInfo() {
	    try {
		while (!finished) {
		    synchronized (lock) {
			lock.wait();
		    }
		    
		    if (needVideoFormatDialog) {
			needVideoFormatDialog = false;
			doVideoFormatDialog();
		    }
		}
	    } catch (InterruptedException ie) {
		mydispose();
		return null;
	    }
	    String name = "vfw:" + VFWCapture.capDriverGetName(capHandle) + ":"
		+ id;
	    String locator = "vfw://" + id;
	    mydispose();
	    
	    if (formats == null || formats.size() < 1)
		return null;
	    else {

		Format [] farray = new Format[formats.size()];
		Enumeration enum = formats.elements();
		int i = 0;
		while (enum.hasMoreElements()) {
		    Format f = (Format) enum.nextElement();
		    farray[i++] = f;
		}
		CaptureDeviceInfo cdi = new CaptureDeviceInfo(name, new MediaLocator(locator),
							      farray);
		CaptureDeviceManager.addDevice(cdi);
		try {
		    CaptureDeviceManager.commit();
		    System.err.println("VFWManual: Committed ok");
		} catch (java.io.IOException ioe) {
		    System.err.println("VFWManual: error committing cdm");
		}
		return cdi;
	    }
	}
    }

    class DBListener implements ActionListener {
	
	int id;

	public DBListener(int id) {
	    this.id = id;
	}

	public void actionPerformed(ActionEvent ae) {
	    createDialog(id);
	}
    }

    public static void main(String [] args) {
	VFWManual m = new VFWManual();
	m.setVisible(true);
    }
}

