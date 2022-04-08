/*
 * @(#)ViewSenderReport.java	1.2 01/03/13
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
import javax.media.rtp.event.*;

import com.sun.media.util.JMFI18N;
import com.sun.media.rtp.util.Signed;

import jmapps.ui.*;


public class ViewSenderReport extends JMPanel {

    private SessionManager          mngrSession;
    private SenderReport            reportSender;

    private ViewSourceDescription   panelSrcDescr = null;
    private JMPanel                 panelLabels = null;
    private JMPanel                 panelData = null;
    private Label                   fieldSSRC;
    private Label                   fieldNtpTimestampLsw;
    private Label                   fieldNtpTimestampMsw;
    private Label                   fieldRtpTimestamp;
    private Label                   fieldBytes;
    private Label                   fieldPackets;

    private static final String LABEL_SSRC              = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.report.ssrc" );
    private static final String LABEL_NTP_TIMESTAMP_LSW = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.report.ntptimestamplsw" );
    private static final String LABEL_NTP_TIMESTAMP_MSW = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.report.ntptimestampmsw" );
    private static final String LABEL_RTP_TIMESTAMP     = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.report.rtptimestamp" );
    private static final String LABEL_BYTES             = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.report.bytecount" );
    private static final String LABEL_PACKETS           = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.report.packetcount" );


    public ViewSenderReport ( SessionManager mngrSession, SenderReport reportSender ) {
	    super ();

        this.mngrSession = mngrSession;
        this.reportSender = reportSender;
        try {
            init();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init () throws Exception {
        JMPanel     panel;
        Label       label;

        this.setLayout ( new BorderLayout(6,6) );
        panel = new JMPanel ( new BorderLayout(6,6) );
        this.add ( panel, BorderLayout.NORTH );

        panelLabels = new JMPanel ( new GridLayout(0,1,0,0) );
        panel.add ( panelLabels, BorderLayout.WEST );
        panelData = new JMPanel ( new GridLayout(0,1,0,0) );
        panel.add ( panelData, BorderLayout.CENTER );

        label = new Label ( LABEL_SSRC );
        panelLabels.add ( label );
        fieldSSRC = new Label ();
        panelData.add ( fieldSSRC );

        label = new Label ( LABEL_NTP_TIMESTAMP_LSW );
        panelLabels.add ( label );
        fieldNtpTimestampLsw = new Label ();
        panelData.add ( fieldNtpTimestampLsw );

        label = new Label ( LABEL_NTP_TIMESTAMP_MSW );
        panelLabels.add ( label );
        fieldNtpTimestampMsw = new Label ();
        panelData.add ( fieldNtpTimestampMsw );

        label = new Label ( LABEL_RTP_TIMESTAMP );
        panelLabels.add ( label );
        fieldRtpTimestamp = new Label ();
        panelData.add ( fieldRtpTimestamp );

        label = new Label ( LABEL_BYTES );
        panelLabels.add ( label );
        fieldBytes = new Label ();
        panelData.add ( fieldBytes );

        label = new Label ( LABEL_PACKETS );
        panelLabels.add ( label );
        fieldPackets = new Label ();
        panelData.add ( fieldPackets );

        panel = new JMPanel ( new BorderLayout(6,6) );
        this.add ( panel, BorderLayout.CENTER );
        panelSrcDescr = new ViewSourceDescription ( reportSender.getSourceDescription() );
        panel.add ( panelSrcDescr, BorderLayout.NORTH );

        updateFields ( reportSender );
    }

    public void updateFields ( SenderReport reportSender ) {
        long    lValue;

        if ( reportSender == null )
            return;

        this.reportSender = reportSender;

        lValue = reportSender.getSSRC ();
        fieldSSRC.setText ( longToString(lValue) );

        lValue = reportSender.getNTPTimeStampLSW ();
        fieldNtpTimestampLsw.setText ( longToString(lValue) );

        lValue = reportSender.getNTPTimeStampMSW ();
        fieldNtpTimestampMsw.setText ( longToString(lValue) );

        lValue = reportSender.getRTPTimeStamp ();
        fieldRtpTimestamp.setText ( longToString(lValue) );

        lValue = reportSender.getSenderByteCount ();
        fieldBytes.setText ( longToString(lValue) );

        lValue = reportSender.getSenderPacketCount ();
        fieldPackets.setText ( longToString(lValue) );
    }

    private String longToString ( long lValue ) {
        if ( lValue < 0 )
            lValue = Signed.UnsignedInt((int)lValue);
        return ( "" + lValue );
    }

}


