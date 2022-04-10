/*
 * @(#)ViewReportBlock.java	1.2 01/03/13
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
import com.sun.media.rtp.util.Signed;

import jmapps.ui.*;


public class ViewReportBlock extends JMPanel {

    private Feedback    feedback;

    private Label   fieldDlsr;
    private Label   fieldFractionLost;
    private Label   fieldLostPackets;
    private Label   fieldJitter;
    private Label   fieldLsr;
    private Label   fieldSsrc;
    private Label   fieldXtndSeqNum;

    private static final String LABEL_DLSR          = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.feedback.dlsr" );
    private static final String LABEL_FRACTION_LOST = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.feedback.fractionlost" );
    private static final String LABEL_LOST_PACKETS  = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.feedback.lostpackets" );
    private static final String LABEL_JITTER        = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.feedback.jitter" );
    private static final String LABEL_LSR           = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.feedback.lsr" );
    private static final String LABEL_SSRC          = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.feedback.ssrc" );
    private static final String LABEL_XTNDSEQNUM    = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.feedback.xtndseqnum" );


    public ViewReportBlock ( Feedback feedback ) {
	    super ();

        this.feedback = feedback;
        try {
            init();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init () throws Exception {
        JMPanel     panelContent;
        JMPanel     panelLabels;
        JMPanel     panelData;
        Label       label;


        this.setLayout ( new BorderLayout(6,6) );

        panelContent = new JMPanel ( new BorderLayout(6,6) );
        this.add ( panelContent, BorderLayout.NORTH );
        panelLabels = new JMPanel ( new GridLayout(0,1,0,0) );
        panelContent.add ( panelLabels, BorderLayout.WEST );
        panelData = new JMPanel ( new GridLayout(0,1,0,0) );
        panelContent.add ( panelData, BorderLayout.CENTER );

        label = new Label ( LABEL_DLSR );
        panelLabels.add ( label );
        fieldDlsr = new Label ();
        panelData.add ( fieldDlsr );

        label = new Label ( LABEL_FRACTION_LOST );
        panelLabels.add ( label );
        fieldFractionLost = new Label ();
        panelData.add ( fieldFractionLost );

        label = new Label ( LABEL_LOST_PACKETS );
        panelLabels.add ( label );
        fieldLostPackets = new Label ();
        panelData.add ( fieldLostPackets );

        label = new Label ( LABEL_JITTER );
        panelLabels.add ( label );
        fieldJitter = new Label ();
        panelData.add ( fieldJitter );

        label = new Label ( LABEL_LSR );
        panelLabels.add ( label );
        fieldLsr = new Label ();
        panelData.add ( fieldLsr );

        label = new Label ( LABEL_SSRC );
        panelLabels.add ( label );
        fieldSsrc = new Label ();
        panelData.add ( fieldSsrc );

        label = new Label ( LABEL_XTNDSEQNUM );
        panelLabels.add ( label );
        fieldXtndSeqNum = new Label ();
        panelData.add ( fieldXtndSeqNum );

        updateFields ( feedback );
    }

    public void updateFields ( Feedback feedback ) {
        if ( feedback == null )
            return;
        this.feedback = feedback;

        fieldDlsr.setText ( "" + (feedback.getDLSR()/65536.0) );
        fieldFractionLost.setText ( "" + (feedback.getFractionLost()/256.0) );
        fieldLostPackets.setText ( "" + feedback.getNumLost() );
        fieldJitter.setText ( "" + feedback.getJitter() );
        fieldLsr.setText ( "" + feedback.getLSR() );
        fieldSsrc.setText ( "" + Signed.UnsignedInt((int)feedback.getSSRC()) );
        fieldXtndSeqNum.setText ( "" + feedback.getXtndSeqNum() );
    }

}


