/*
 * @(#)ViewSourceDescription.java	1.2 01/03/13
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

package jmapps.rtp;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.media.rtp.*;
import javax.media.rtp.rtcp.*;

import com.sun.media.util.JMFI18N;

import jmapps.ui.*;


public class ViewSourceDescription extends JMPanel {

    private Vector  vectorData = null;

    private JMPanel     panelLabels = null;
    private JMPanel     panelData = null;
    private Label       fieldCname;
    private Label       fieldName;
    private Label       fieldEmail;
    private Label       fieldPhone;
    private Label       fieldLocation;
    private Label       fieldTool;
    private Label       fieldNote;
    private Label       fieldPrivate;

    private static final String LABEL_CNAME     = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.srcdescr.cname" );
    private static final String LABEL_NAME      = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.srcdescr.name" );
    private static final String LABEL_EMAIL     = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.srcdescr.email" );
    private static final String LABEL_PHONE     = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.srcdescr.phone" );
    private static final String LABEL_LOCATION  = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.srcdescr.location" );
    private static final String LABEL_TOOL      = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.srcdescr.tool" );
    private static final String LABEL_NOTE      = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.srcdescr.note" );
    private static final String LABEL_PRIVATE   = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.srcdescr.private" );
    private static final String LABEL_NONE      = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.srcdescr.none" );


    public ViewSourceDescription ( Vector vectorData ) {
	    super ();

        this.vectorData = vectorData;
        try {
            init();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init () throws Exception {
        Label       label;

        this.setLayout ( new BorderLayout(6,6) );

        panelLabels = new JMPanel ( new GridLayout(0,1,0,0) );
        this.add ( panelLabels, BorderLayout.WEST );
        panelData = new JMPanel ( new GridLayout(0,1,0,0) );
        this.add ( panelData, BorderLayout.CENTER );

        label = new Label ( LABEL_CNAME );
        panelLabels.add ( label );
        fieldCname = new Label ( LABEL_NONE );
        panelData.add ( fieldCname );

        label = new Label ( LABEL_NAME );
        panelLabels.add ( label );
        fieldName = new Label ( LABEL_NONE );
        panelData.add ( fieldName );

        label = new Label ( LABEL_EMAIL );
        panelLabels.add ( label );
        fieldEmail = new Label ( LABEL_NONE );
        panelData.add ( fieldEmail );

        label = new Label ( LABEL_PHONE );
        panelLabels.add ( label );
        fieldPhone = new Label ( LABEL_NONE );
        panelData.add ( fieldPhone );

        label = new Label ( LABEL_LOCATION );
        panelLabels.add ( label );
        fieldLocation = new Label ( LABEL_NONE );
        panelData.add ( fieldLocation );

        label = new Label ( LABEL_TOOL );
        panelLabels.add ( label );
        fieldTool = new Label ( LABEL_NONE );
        panelData.add ( fieldTool );

        label = new Label ( LABEL_NOTE );
        panelLabels.add ( label );
        fieldNote = new Label ( LABEL_NONE );
        panelData.add ( fieldNote );

        label = new Label ( LABEL_PRIVATE );
        panelLabels.add ( label );
        fieldPrivate = new Label ( LABEL_NONE );
        panelData.add ( fieldPrivate );

        updateFields ( vectorData );
    }

    public void updateFields ( Vector vectorData ) {
        int                 i;
        int                 nCount;
        SourceDescription   srcDescr;

        if ( vectorData == null )
            return;

        this.vectorData = vectorData;

        nCount = vectorData.size ();
        for ( i = 0;  i < nCount;  i++ ) {
            srcDescr = (SourceDescription) vectorData.elementAt ( i );
            switch ( srcDescr.getType() ) {
                case SourceDescription.SOURCE_DESC_CNAME:
                    fieldCname.setText ( srcDescr.getDescription() );
                    break;
                case SourceDescription.SOURCE_DESC_NAME:
                    fieldName.setText ( srcDescr.getDescription() );
                    break;
                case SourceDescription.SOURCE_DESC_EMAIL:
                    fieldEmail.setText ( srcDescr.getDescription() );
                    break;
                case SourceDescription.SOURCE_DESC_PHONE:
                    fieldPhone.setText ( srcDescr.getDescription() );
                    break;
                case SourceDescription.SOURCE_DESC_LOC:
                    fieldLocation.setText ( srcDescr.getDescription() );
                    break;
                case SourceDescription.SOURCE_DESC_TOOL:
                    fieldTool.setText ( srcDescr.getDescription() );
                    break;
                case SourceDescription.SOURCE_DESC_NOTE:
                    fieldNote.setText ( srcDescr.getDescription() );
                    break;
                case SourceDescription.SOURCE_DESC_PRIV:
                    fieldPrivate.setText ( srcDescr.getDescription() );
                    break;
                default:
                    break;
            }
        }
    }

}


