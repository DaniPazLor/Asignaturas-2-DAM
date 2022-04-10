/*
 * @(#)JMFInit.java	1.11 01/03/13
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

import com.sun.media.util.Registry;
import java.awt.*;
import java.io.*;
import java.util.Vector;
import javax.media.*;
import javax.media.format.AudioFormat;
import com.sun.media.ExclusiveUse;

public class JMFInit extends Frame implements Runnable {

    private String tempDir = "/tmp";
    private TextArea textBox;
    private boolean done = false;
    private String policyFile;
    private static String SIGNATURE = "JMFPolicySection";
    private static String SIGNATURE2 = "JMFPolicyCacheDir";
    private String userHome;
    
    public JMFInit(String [] args) {
	super("Initializing JMF...");

	createGUI();

	updateTemp(args);

	if (args.length > 1) {
	    policyFile = args[1];
	    installPolicyFile();
	}

	if (args.length > 2) {
	    boolean allow = false;
	    if (args[2].equals("1"))
		allow = true;
	    Registry.set("secure.allowCaptureFromApplets", new Boolean(allow));
	    allow = false;
	    if (args.length > 3) {
		if (args[3].equals("1"))
		    allow = true;
		Registry.set("secure.allowSaveFileFromApplets", new Boolean(allow));
	    }
	    try {
		Registry.commit();
	    } catch (Exception e) {
	    }
	}

	Thread detectThread = new Thread(this);
	detectThread.run();

	int slept = 0;
	while (!done && slept < 60 * 1000 * 2) {
	    try {
		Thread.currentThread().sleep(500);
	    } catch (InterruptedException ie) {
	    }
	    slept += 500;
	}
	
	if (!done) {
	    message("Aborting detection!");
	}
	
	try {
	    Thread.currentThread().sleep(2000);
	} catch (InterruptedException ie) {
	}
    }

    public void run() {
	detectDirectAudio();
	detectS8DirectAudio();
	detectCaptureDevices();
	done = true;
    }
    
    private void updateTemp(String [] args) {
	if (args.length > 0) {
	    tempDir = args[0];
	    message("Setting cache directory to " + tempDir);
	    Registry r = new Registry();
	    try {
		r.set("secure.cacheDir", tempDir);
		r.commit();
		message("Updated registry");
	    } catch (Exception e) {
		message("Couldn't update registry!");
	    }
	}
    }

    private void installPolicyFile() {
	String javaVersion = System.getProperty("java.version");
	if (javaVersion.compareTo("1.2") < 0)
	    return;
	userHome = System.getProperty("user.home");
	if (userHome == null) {
	    message("Error installing policy file!");
	    return;
	}
	String policyDest = userHome + File.separator + ".java.policy";

	// Update the original jmf policy file
	File fileIn = new File(policyFile);
	message("Updating policy file");
	updateJMFPolicy(fileIn);
	
	File fileOut = new File(policyDest);
	if (fileOut.exists()) {
	    if (!foundJMFPolicy(fileOut)) {
		message("Appending to existing policy file");
		appendJMFPolicy(fileOut, policyFile);
	    } else {
		message("Updating existing policy file");
		updateJMFPolicy(fileOut);
	    }
	} else {
	    message("Creating new policy file");
	    copyJMFPolicy(policyFile, policyDest);
	}   
    }

    private boolean foundJMFPolicy(File file) {
	try {
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    String line;
	    while ((line = br.readLine()) != null) {
		if (line.indexOf(SIGNATURE) >= 0) {
		    br.close();
		    return true;
		}
	    }
	    br.close();
	} catch (Exception e) {
	    System.err.println(e);
	}
	return false;
    }

    private void appendJMFPolicy(File file, String policySrc) {
	try {
	    RandomAccessFile raf = new RandomAccessFile(file, "rw");
	    raf.skipBytes((int) raf.length());
	    BufferedReader br = new BufferedReader(new FileReader(policySrc));
	    String line;
	    while ((line = br.readLine()) != null) {
		raf.writeChars(line + "\n");
	    }
	    raf.close();
	    br.close();
	} catch (Exception ex) {
	}
    }

    private void copyJMFPolicy(String policyFile, String policyDest) {
	try {
	    BufferedReader br = new BufferedReader(new FileReader(policyFile));
	    BufferedWriter bw = new BufferedWriter(new FileWriter(policyDest));
	    String line;
	    while ((line = br.readLine()) != null) {
		bw.write(line);
		bw.newLine();
	    }
	    bw.close();
	    br.close();
	} catch (Exception e) {
	}
    }
    
    private void updateJMFPolicy(File file) {
	String tempFile = tempDir + File.separator + "XYZ143.TMP";
	String slashedTempDir = "";
	String doublePath = (File.separator.equals("/"))? "/" : "\\\\";
	char [] tempChars = tempDir.toCharArray();
	for (int i = 0; i < tempChars.length; i++) {
	    if (tempChars[i] == File.separatorChar)
		slashedTempDir += doublePath;
	    else
		slashedTempDir += tempChars[i];
	}

	try {
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
	    String line;
	    while ((line = br.readLine()) != null) {
		bw.write(line);
		bw.newLine();
		if (line.indexOf(SIGNATURE2) >= 0) {
		    // Throw away old two lines
		    br.readLine();
		    br.readLine();
		    // Update the temp directory
		    String writePerm = "\tpermission java.io.FilePermission \"" +
			slashedTempDir + doublePath + "*\", \"write\";";
		    String deletePerm = "\tpermission java.io.FilePermission \"" +
			slashedTempDir + doublePath + "*\", \"delete\";";
		    bw.write(writePerm);
		    bw.newLine();
		    bw.write(deletePerm);
		    bw.newLine();
		}
	    }
	    bw.flush();
	    bw.close();
	    br.close();
	    // Rename the temp file to replace the original
	    copyJMFPolicy(tempFile, file.getPath());
	} catch (Exception e) {
	    message("Error in updating policy file");
	}
    }
    
    private void detectCaptureDevices() {
	// check if JavaSound capture is available
	message("Looking for Audio capturer");
	Class dsauto = null;
	try { 
	    dsauto = Class.forName("DirectSoundAuto");
	    dsauto.newInstance();
	    message("Finished detecting DirectSound capturer");
	} catch (ThreadDeath td) {
	    throw td;
	} catch (Throwable t ) {
	}

	Class jsauto = null;
	try { 
	    jsauto = Class.forName("JavaSoundAuto");
	    jsauto.newInstance();
	    message("Finished detecting javasound capturer");
	} catch (ThreadDeath td) {
	    throw td;
	} catch (Throwable t ) {
	    message("JavaSound capturer detection failed!");
	}

	// Check if VFWAuto or SunVideoAuto is available
	message("Looking for video capture devices");
	Class auto = null;
        Class autoPlus = null;
	try {
	    auto = Class.forName("VFWAuto");
	} catch (Exception e) {
	}
	if (auto == null) {
	    try {
		auto = Class.forName("SunVideoAuto");
	    } catch (Exception ee) {
	    }
	    try {
		autoPlus = Class.forName("SunVideoPlusAuto");
	    } catch (Exception ee) {
	    }
	}
	try {
	    Object instance = auto.newInstance();
            if (autoPlus != null) {
                Object instancePlus = autoPlus.newInstance();
            }
	    message("Finished detecting video capture devices");
	} catch (ThreadDeath td) {
	    throw td;
	} catch (Throwable t) {
	    message("Capture device detection failed!");
	}
    }

    private void detectDirectAudio() {
	Class cls;
	int plType = PlugInManager.RENDERER;
	String dar = "com.sun.media.renderer.audio.DirectAudioRenderer";
	try {
	    // Check if this is the Windows Performance Pack - hack
	    cls = Class.forName("VFWAuto");
	    // Check if DS capture is supported, otherwise fail DS renderer
	    // since NT doesn't have capture
	    cls = Class.forName("com.sun.media.protocol.dsound.DSound");
	    // Find the renderer class and instantiate it.
	    cls = Class.forName(dar);
	    
	    Renderer rend = (Renderer) cls.newInstance();
	    try {
		// Set the format and open the device
		AudioFormat af = new AudioFormat(AudioFormat.LINEAR,
						 44100, 16, 2);
		rend.setInputFormat(af);
		rend.open();
		Format [] inputFormats = rend.getSupportedInputFormats();
		// Register the device
		PlugInManager.addPlugIn(dar, inputFormats, new Format[0],
					plType);
		// Move it to the top of the list
		Vector rendList =
		    PlugInManager.getPlugInList(null, null, plType);
		int listSize = rendList.size();
		if (rendList.elementAt(listSize - 1).equals(dar)) {
		    rendList.removeElementAt(listSize - 1);
		    rendList.insertElementAt(dar, 0);
		    PlugInManager.setPlugInList(rendList, plType);
		    PlugInManager.commit();
		    //System.err.println("registered");
		}
		rend.close();
	    } catch (Throwable t) {
		//System.err.println("Error " + t);
	    }
	} catch (Throwable tt) {
	}
    }

    private void detectS8DirectAudio() {
	Class cls;
	int plType = PlugInManager.RENDERER;
	String dar = "com.sun.media.renderer.audio.DirectAudioRenderer";
	try {
	    // Check if this is the solaris Performance Pack - hack
	    cls = Class.forName("SunVideoAuto");

	    // Find the renderer class and instantiate it.
	    cls = Class.forName(dar);
	    
	    Renderer rend = (Renderer) cls.newInstance();

	    if ( rend instanceof ExclusiveUse &&
                 !((ExclusiveUse)rend).isExclusive()) {
		// sol8+, DAR supports mixing
		Vector rendList = PlugInManager.getPlugInList(null, null, plType);
		int listSize = rendList.size();
		boolean found = false;
		String rname = null;

		for ( int i = 0; i < listSize; i++) {
		    rname = (String)(rendList.elementAt(i));
		    if ( rname.equals(dar) ) { // DAR is in the registry
			found = true;
			rendList.removeElementAt(i);
			break;
		    }
		}
		
		if ( found ) {
		    rendList.insertElementAt(dar, 0);
		    PlugInManager.setPlugInList(rendList, plType);
		    PlugInManager.commit();
		}
	    }
	} catch (Throwable tt) {
	}
    }

    private void message(String mesg) {
	textBox.append(mesg + "\n");
    }

    private void createGUI() {
	textBox = new TextArea(5, 50);
	add("Center", textBox);
	textBox.setEditable(false);
	addNotify();
	setVisible(true);
	pack();
    }
    
    public static void main(String [] args) {
	new JMFInit(args);
	System.exit(0);
    }
}
