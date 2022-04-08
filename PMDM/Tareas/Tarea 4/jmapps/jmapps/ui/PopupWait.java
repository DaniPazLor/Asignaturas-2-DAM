/*
 * @(#)PopupWait.java	1.3 01/03/13
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

import java.awt.*;
import java.awt.event.*;


public class PopupWait extends Window {

    private JMPanel         panelOuter;
    private JMPanel         panelInner;
    private JMPanel         panelImage;
    private ImageArea       fieldImage;
    private TextView        fieldText;

    private Frame           frameOwner;
    private String          strText;
    private UpdateThread    threadUpdate = new UpdateThread ();
    private Cursor          cursorOld;
    private Cursor          cursorWait = new Cursor ( Cursor.WAIT_CURSOR );
    private Cursor          cursorOldOwner;
    private Cursor          cursorWaitOwner = new Cursor ( Cursor.WAIT_CURSOR );


    public PopupWait ( Frame frameOwner, String strText ) {
        super ( frameOwner );

        this.frameOwner = frameOwner;
        this.strText = strText;
        try {
            init();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init () throws Exception {
        Image       image;
        Dimension   dimPopup;
        Dimension   dimScreen;
        Point       point;

        this.setLayout ( new BorderLayout(6,6) );

        panelOuter = new JMPanel ( new BorderLayout(6,6) );
        panelOuter.setBackground ( Color.lightGray );
        panelOuter.setRaisedBorder ();
        this.add ( panelOuter, BorderLayout.CENTER );

        panelInner = new JMPanel ( new BorderLayout(6,6) );
        panelInner.setEmptyBorder ( 6, 6, 6, 6 );
        panelOuter.add ( panelInner, BorderLayout.CENTER );

        panelImage = new JMPanel ( new FlowLayout() );
        panelInner.add ( panelImage, BorderLayout.WEST );

        image = ImageArea.loadImage ( "logo.gif", this, true );
        fieldImage = new ImageArea ( image );
        fieldImage.setInsets ( 0, 0, 0, 0 );
        panelImage.add ( fieldImage );

        fieldText = new TextView ( strText );
        fieldText.setPreferredWidth ( 256 );
        panelInner.add ( fieldText, BorderLayout.CENTER );

        pack ();
        dimPopup = this.getSize ();
        dimScreen = this.getToolkit().getScreenSize();
        point = new Point ( (dimScreen.width - dimPopup.width) / 2, (dimScreen.height - dimPopup.height) / 2 );
        this.setLocation ( point );
    }

    public void setVisible ( boolean boolVisible ) {
        super.setVisible ( boolVisible );

        if ( boolVisible == true ) {
            cursorOld = this.getCursor ();
            this.setCursor ( cursorWait );

            cursorOldOwner = frameOwner.getCursor ();
            frameOwner.setCursor ( cursorWaitOwner );

            threadUpdate.start ();
        }
        else {
            threadUpdate.terminateThread ();

            this.setCursor ( cursorOld );
            frameOwner.setCursor ( cursorOldOwner );
        }
    }

    private class UpdateThread extends Thread {
        private boolean     boolTerminate = false;

        public UpdateThread () {
            super ();
        }

        public void terminateThread () {
            boolTerminate = true;
        }

        public void run() {
            boolTerminate = false;
            while ( boolTerminate == false ) {
                try {
                    sleep ( 1000 );
                }
                catch ( Exception exception ) {
                }
//                updateComponent ( PopupWait.this );
                updateComponent ( PopupWait.this.panelOuter );
                updateComponent ( PopupWait.this.panelInner );
                updateComponent ( PopupWait.this.panelImage );
                updateComponent ( PopupWait.this.fieldImage );
                updateComponent ( PopupWait.this.fieldText );
            }
        }

        private void updateComponent ( Component component ) {
            Graphics    graphics;

            if ( component == null )
                return;
            if ( !component.isShowing() )
                return;
            graphics = component.getGraphics ();
            if ( graphics == null )
                return;

            component.update ( graphics );
        }

    }

}


