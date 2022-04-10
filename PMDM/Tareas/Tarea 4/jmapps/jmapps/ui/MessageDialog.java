/*
 * @(#)MessageDialog.java	1.6 01/03/13
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

import java.lang.*;
import java.util.*;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import com.sun.media.util.JMFI18N;


public class MessageDialog extends JMDialog  {

    public static String    titleDefault = "Message";

    protected String       strMessage;
    protected Image        image = null;

    private Button         buttonOK;
    private Button         buttonCancel;
    private boolean        needCancel;
    private boolean        YesNoDiag = false;
    private TextView       fieldMessage;


    public MessageDialog ( Frame frame, String strTitle, String strMessage,
			   Image image, boolean boolModal, boolean needCancel ) {
	this (frame, strTitle, strMessage, image, boolModal, needCancel, false);
    }

    public MessageDialog ( Frame frame, String strTitle, String strMessage,
			   Image image, boolean boolModal, boolean needCancel, boolean YesNoDiag ) {
        super ( frame, strTitle, boolModal );

        this.strMessage = strMessage;
        this.image = image;
        this.needCancel = needCancel;
	this.YesNoDiag = YesNoDiag;
        try {
            init();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MessageDialog ( Frame frame, String strTitle, String strMessage,
			   Image image, boolean boolModal ) {
        this ( frame, strTitle, strMessage, image, boolModal, false );
    }

    public static String createErrorDialog ( Frame frame, String strTitle, String strMessage ) {
        MessageDialog       dlg;
        Image               image;
        String              strAction;
        boolean             boolCenter = false;

        if ( frame == null ) {
            frame = new Frame ();
            boolCenter = true;
        }

        image = ImageArea.loadImage ( "iconError.gif" );
        dlg = new MessageDialog ( frame, strTitle + " "
                                + JMFI18N.getResource("jmstudio.messagedlg.error"),
                                strMessage, image, true );
        if ( boolCenter )
            dlg.setLocationCenter ();
        dlg.show ();
        strAction = dlg.getAction ();
        return ( strAction );
    }

    public static String createYesNoDialog ( Frame frame, String strTitle, String strMessage ) {
        MessageDialog       dlg;
        Image               image;
        String              strAction;
        boolean             boolCenter = false;

        if ( frame == null ) {
            frame = new Frame ();
            boolCenter = true;
        }

        image = ImageArea.loadImage ( "iconQuery.gif" );
        dlg = new MessageDialog ( frame, strTitle, strMessage, image, true,false, true);

        if ( boolCenter )
            dlg.setLocationCenter ();
        dlg.show ();
        strAction = dlg.getAction ();
        return ( strAction );
    }


    public static String createErrorDialog ( Frame frame, String strMessage ) {
        String  strAction;

        strAction = createErrorDialog ( frame, titleDefault, strMessage );
        return ( strAction );
    }

    public static String createErrorDialog ( Frame frame, Exception exception ) {
        String           strAction;

        strAction = createErrorDialog ( frame, null, exception );
        return ( strAction );
    }

    public static String createErrorDialog ( Frame frame, String strText, Exception exception ) {
        String           strMessage;
        String           strAction;

        strMessage = exception.getMessage ();
        if ( strMessage == null  ||  strMessage.trim().length() < 1 ) {
            strMessage = exception.getClass().getName();
        }
        strMessage = JMFI18N.getResource("jmstudio.messagedlg.exception") + ". "
                                    + "\n" + strMessage;
        if ( strText != null )
            strMessage = strText + "\n" + strMessage;
        strAction = createErrorDialog ( frame, strMessage );
        return ( strAction );
    }

    public static String createInfoDialog ( Frame frame, String strMessage ) {
        String  strAction;

        strAction = createInfoDialog ( frame, titleDefault, strMessage );
        return ( strAction );
    }

    public static String createInfoDialog ( Frame frame, String strTitle, String strMessage ) {
        MessageDialog   dlg;
        Image           image;
        String          strAction;
        boolean         boolCenter = false;

        if ( frame == null ) {
            frame = new Frame ();
            boolCenter = true;
        }

        image = ImageArea.loadImage ( "iconInfo.gif" );
        dlg = new MessageDialog ( frame, strTitle, strMessage, image, true );
        if ( boolCenter )
            dlg.setLocationCenter ();
        dlg.show ();
        strAction = dlg.getAction ();
        return ( strAction );
    }

    public static String createWarningDialog ( Frame frame, String strMessage ) {
        MessageDialog   dlg;
        Image           image;
        String          strAction;
        boolean         boolCenter = false;

        if ( frame == null ) {
            frame = new Frame ();
            boolCenter = true;
        }

        image = ImageArea.loadImage ( "iconWarning.gif" );
        dlg = new MessageDialog ( frame, titleDefault + " "
                                + JMFI18N.getResource("jmstudio.messagedlg.warning"),
                                strMessage, image, true );
        if ( boolCenter )
            dlg.setLocationCenter ();
        dlg.show ();
        strAction = dlg.getAction ();
        return ( strAction );
    }

    public static String createOKCancelDialog ( Frame frame, String strMessage ) {
        MessageDialog   dlg;
        Image		    image;
        String          strAction;
        boolean         boolCenter = false;

        if ( frame == null ) {
            frame = new Frame ();
            boolCenter = true;
        }

        image = ImageArea.loadImage ( "iconQuery.gif" );
        dlg = new MessageDialog ( frame, titleDefault + " "
                                + JMFI18N.getResource("jmstudio.messagedlg.query"),
                                strMessage, image, true, true );
        if ( boolCenter )
            dlg.setLocationCenter ();
        dlg.show ();
        strAction = dlg.getAction ();
        return ( strAction );
    }

    public static void createErrorDialogModeless ( Frame frame, String strMessage ) {
        MessageDialog    dlg;
        Image            image;
        boolean         boolCenter = false;

        if ( frame == null ) {
            frame = new Frame ();
            boolCenter = true;
        }

        image = ImageArea.loadImage ( "iconError.gif" );
        dlg = new MessageDialog ( frame, titleDefault + " "
                                + JMFI18N.getResource("jmstudio.messagedlg.error"),
                                strMessage, image, false );
        if ( boolCenter )
            dlg.setLocationCenter ();
        dlg.show ();
    }

    public static void createErrorDialogModeless ( Frame frame, String strText, Exception exception ) {
        String           strMessage;

        strMessage = exception.getMessage ();
        if ( strMessage == null  ||  strMessage.trim().length() < 1 ) {
            strMessage = exception.getClass().getName();
        }
        strMessage = JMFI18N.getResource("jmstudio.messagedlg.exception") + ". "
                                + "\n" + strMessage;
        if ( strText != null )
            strMessage = strText + "\n" + strMessage;
        createErrorDialogModeless ( frame, strMessage );
    }

    public TextView getTextView() {
	return fieldMessage;
    }


    protected void init () throws Exception {
        JMPanel         panel;
        JMPanel         panelImage;
        JMPanel         panelButtons;
        Dimension       dimDialog;
        Dimension       dimScreen;
        // TextView        fieldMessage;
        ImageArea       fieldImage;
        MediaTracker    trackerMedia;


        this.setLayout ( new BorderLayout(6,6) );
        panel = new JMPanel ( new BorderLayout(6,6) );
        panel.setEmptyBorder ( 12, 6, 12, 6 );
        this.add ( panel, BorderLayout.CENTER );


        if (strMessage != null){
            fieldMessage = new TextView ( strMessage );
            panel.add ( fieldMessage, BorderLayout.CENTER );
            fieldMessage.setPreferredWidth ( 360 );
        }
        panelImage = new JMPanel ( new FlowLayout(FlowLayout.CENTER) );
        panel.add ( panelImage, BorderLayout.WEST );
        if ( image != null ) {
            trackerMedia = new MediaTracker ( this );
            trackerMedia.addImage ( image, 1001 );
            try {
                trackerMedia.waitForID ( 1001 );
            }
            catch ( Exception exception ) {
//                exception.printStackTrace ();
            }

//            Toolkit.getDefaultToolkit().prepareImage ( image, -1, -1, this );
            fieldImage = new ImageArea ( image );
            panelImage.add ( fieldImage );
        }

        panel = new JMPanel ( new FlowLayout(FlowLayout.CENTER) );
        this.add ( panel, BorderLayout.SOUTH );

	if ( YesNoDiag ) {
            panelButtons = createButtonPanel ( new String[] { ACTION_YES, ACTION_NO } );
	} else if ( needCancel ) {
            panelButtons = createButtonPanel ( new String[] { ACTION_OK, ACTION_CANCEL } ); 
	} else {
            panelButtons = createButtonPanel ( new String[] { ACTION_OK } );
	}
        panel.add ( panelButtons );

        this.pack ();
        this.setResizable ( false );
    }

    public void actionPerformed ( ActionEvent event ) {
        String          strAction;

        strAction = event.getActionCommand ();
        if ( strAction.equals(ACTION_OK) ) {
            this.setAction ( strAction );
            this.dispose ();
        } else if ( strAction.equals(ACTION_CANCEL) ) {
            this.setAction ( strAction );
            this.dispose ();
        } else if ( strAction.equals(ACTION_YES)) {
	    this.setAction(strAction);
	    this.dispose();
	} else if ( strAction.equals(ACTION_NO)) {
	    this.setAction(strAction);
	    this.dispose();
	}
    }

}


