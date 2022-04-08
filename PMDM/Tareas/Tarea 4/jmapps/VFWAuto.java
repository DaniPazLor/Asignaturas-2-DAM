/*
 * @(#)VFWAuto.java	1.2 01/03/13
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

import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.media.*;
import javax.media.Format;
import javax.media.format.VideoFormat;

import com.sun.media.vfw.*;
import com.sun.media.util.WindowUtil;


public class VFWAuto {

    public VFWAuto() {
        Vector devices = (Vector) CaptureDeviceManager.getDeviceList(null).clone();
        Enumeration enum = devices.elements();

        while (enum.hasMoreElements()) {
            CaptureDeviceInfo cdi = (CaptureDeviceInfo) enum.nextElement();
            String name = cdi.getName();
            if (name.startsWith("vfw:"))
                CaptureDeviceManager.removeDevice(cdi);
        }
	
        int nDevices = 0;
        for (int i = 0; i < 10; i++) {
            String name = VFWCapture.capGetDriverDescriptionName(i);
            if (name != null && name.length() > 1) {
                System.err.println("Found device " + name);
                System.err.println("Querying device. Please wait...");
                com.sun.media.protocol.vfw.VFWSourceStream.autoDetect(i);
                nDevices++;
            }
        }
    }

    public static void main(String [] args) {
        VFWAuto a = new VFWAuto();
        System.exit(0);
    }
}

