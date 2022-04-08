/*
 * @(#)JavaSoundAuto.java	1.2 01/03/13
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

import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import java.util.Vector;
import java.util.Enumeration;
import com.sun.media.protocol.javasound.JavaSoundSourceStream;

public class JavaSoundAuto {
    private static final String detectClass = "JavaSoundDetector";
    CaptureDeviceInfo [] devices = null;

    public static void main(String[] args) {
       new JavaSoundAuto();
       System.exit(0);
    }

    public JavaSoundAuto() {
	boolean supported = false;
	// instance JavaSoundDetector to check is javasound's capture is availabe
	try {
	    Class cls = Class.forName(detectClass);
	    JavaSoundDetector detect = (JavaSoundDetector)cls.newInstance();
	    supported = detect.isSupported();
	} catch (Throwable t) {
	    supported = false;
	    // t.printStackTrace();
	}
	
	System.out.println("JavaSound Capture Supported = " + supported);

	if (supported) {
	    // It's there, start to register JavaSound with CaptureDeviceManager
	    Vector devices = (Vector) CaptureDeviceManager.getDeviceList(null).clone();
	    
	    // remove the old javasound capturers
	    String name;
	    Enumeration enum = devices.elements();
	    while (enum.hasMoreElements()) {
		CaptureDeviceInfo cdi = (CaptureDeviceInfo) enum.nextElement();	
		name = cdi.getName();
		if (name.startsWith("JavaSound"))
		    CaptureDeviceManager.removeDevice(cdi);
	    }
	    
	    // collect javasound capture device info from JavaSoundSourceStream
	    // and register them with CaptureDeviceManager
	    CaptureDeviceInfo[] cdi =  com.sun.media.protocol.javasound.JavaSoundSourceStream.listCaptureDeviceInfo();
	    if ( cdi != null ){
		for (int i = 0; i < cdi.length; i++)
		    CaptureDeviceManager.addDevice(cdi[i]);
		try {
		    CaptureDeviceManager.commit();
		    System.out.println("JavaSoundAuto: Committed ok");
		} catch (java.io.IOException ioe) {
		    System.err.println("JavaSoundAuto: error committing cdm");
		}
	    }
		
	}
    }
}
	
	

    
