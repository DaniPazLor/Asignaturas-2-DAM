/*
 * @(#)JMFrame.java	1.6 01/03/13
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

package jmapps.ui;

import java.util.*;
import java.awt.*;
import java.awt.event.*;


public class JMFrame extends Frame implements WindowListener {

    protected Frame         frameOwner = null;
    private boolean         boolFirstTimeVisible = true;
    private Vector          vectorChildFrames = new Vector ();


    public JMFrame () {
        this ( null, "JMFrame" );
    }

    public JMFrame ( String strTitle ) {
        this ( null, strTitle );
    }

    public JMFrame ( Frame frameOwner ) {
        this ( frameOwner, "JMFrame" );
    }

    public JMFrame ( Frame frameOwner, String strTitle ) {
        super ( strTitle );
        this.frameOwner = frameOwner;
        initFrame ();
    }


    protected void initFrame () {
        this.addWindowListener ( this );
    }

    public void addNotify () {
        super.addNotify ();
        autoPosition ();
    }

    public void setVisible ( boolean boolVisible ) {
        if ( boolVisible == true  &&  !this.isVisible()  &&  boolFirstTimeVisible == true ) {
            boolFirstTimeVisible = false;
            autoPosition ();
            if ( frameOwner != null  &&  frameOwner instanceof JMFrame ) {
                ((JMFrame)frameOwner).vectorChildFrames.addElement ( this );
            }
        }
        super.setVisible ( boolVisible );
    }

    public void autoPosition () {
        autoPosition ( this, frameOwner );
    }

    public static void autoPosition ( Frame frame, Frame frameOwner ) {
        Dimension       dim;
        Dimension       dimOwner;
        Dimension       dimFrame;
        Dimension       dimScreen;
        Point           point;
        Insets          insets;
        Frame           framePrevSibling = null;

        if ( frame == null  ||  frameOwner == null  ||  !frameOwner.isShowing() )
            return;

        if ( frameOwner instanceof JMFrame  &&  ((JMFrame)frameOwner).vectorChildFrames.size() > 0 ) {
            framePrevSibling = (Frame) ((JMFrame)frameOwner).vectorChildFrames.lastElement();
        }

        if ( framePrevSibling != null  &&  framePrevSibling.isShowing() ) {
            point = framePrevSibling.getLocationOnScreen();
            point.x += 20;
            point.y += 20;
        }
        else {
            point = frameOwner.getLocationOnScreen ();
            dimOwner = frameOwner.getSize ();
            point.y += dimOwner.height;
        }

        dim = frame.getPreferredSize ();
        dimFrame = frame.getSize ();
        dimFrame.width = Math.max ( dim.width, dimFrame.width );
        dimFrame.height = Math.max ( dim.height, dimFrame.height );
        dimScreen = Toolkit.getDefaultToolkit().getScreenSize();

        if ( point.x + dimFrame.width > dimScreen.width )
            point.x = dimScreen.width - dimFrame.width;
        if ( point.y + dimFrame.height > dimScreen.height )
            point.y = dimScreen.height - dimFrame.height;
        frame.setLocation ( point );
    }

    public void windowOpened ( WindowEvent event ) {
    }

    public void windowClosing ( WindowEvent event ) {
        this.dispose ();
    }

    public void windowClosed ( WindowEvent event ) {
        if ( frameOwner != null  &&  frameOwner instanceof JMFrame
                    &&  ((JMFrame)frameOwner).vectorChildFrames.contains(this) ) {
            ((JMFrame)frameOwner).vectorChildFrames.removeElement ( this );
        }
    }

    public void windowIconified ( WindowEvent event ) {
    }

    public void windowDeiconified ( WindowEvent event ) {
    }

    public void windowActivated ( WindowEvent event ) {
    }

    public void windowDeactivated ( WindowEvent event ) {
    }

}


