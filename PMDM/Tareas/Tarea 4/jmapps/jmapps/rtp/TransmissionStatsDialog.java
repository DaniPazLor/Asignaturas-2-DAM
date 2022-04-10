/*
 * @(#)TransmissionStatsDialog.java	1.2 01/03/13
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

import com.sun.media.util.JMFI18N;
import com.sun.media.ui.TabControl;

import jmapps.ui.*;


public class TransmissionStatsDialog extends JMDialog {

    private Vector          vectorMngrSessions;
    private Vector          vectorStreamLabels;
    private UpdateThread    threadUpdate = null;

    private Label   fieldTotalRtpPacketsSent [];
    private Label   fieldTotalBytesSent [];
    private Label   fieldRtcpPacketsSent [];
    private Label   fieldLocalCollisions [];
    private Label   fieldRemoteCollisions [];
    private Label   fieldFailedTransmissions [];

    private static final String     LABEL_TOTAL_RTP_PACKETS_SENT    = JMFI18N.getResource ( "jmstudio.transmitstats.totalrtppacketssent" );
    private static final String     LABEL_TOTAL_BYTES_SENT          = JMFI18N.getResource ( "jmstudio.transmitstats.totalbytessent" );
    private static final String     LABEL_RTCP_PACKETS_SENT         = JMFI18N.getResource ( "jmstudio.transmitstats.rtcppacketssent" );
    private static final String     LABEL_LOCAL_COLLISIONS          = JMFI18N.getResource ( "jmstudio.transmitstats.localcollisions" );
    private static final String     LABEL_REMOTE_COLLISIONS         = JMFI18N.getResource ( "jmstudio.transmitstats.remotecollisions" );
    private static final String     LABEL_FAILED_TRANSMISSIONS      = JMFI18N.getResource ( "jmstudio.transmitstats.failedtransmissions" );


    public TransmissionStatsDialog ( Frame frame, Vector vectorMngrSessions, Vector vectorStreamLabels ) {
	    super ( frame, JMFI18N.getResource("jmstudio.transmitstats.title"), false );

        this.vectorStreamLabels = vectorStreamLabels;
        this.vectorMngrSessions = vectorMngrSessions;
        try {
            init();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void init () throws Exception {
        int         i;
        int         nCount;
        JMPanel     panel;
        JMPanel     panelContent;
        JMPanel     panelButtons;
        TabControl  tabControl;


        this.setLayout ( new BorderLayout(6,6) );
        panelContent = new JMPanel ( new BorderLayout(6,6) );
        panelContent.setEmptyBorder ( 6, 6, 6, 6 );
        this.add ( panelContent, BorderLayout.CENTER );

        tabControl = new TabControl ( TabControl.ALIGN_TOP );
        panelContent.add ( tabControl, BorderLayout.CENTER );

        nCount = Math.min ( vectorMngrSessions.size(), vectorStreamLabels.size() );
        fieldTotalRtpPacketsSent = new Label [nCount];
        fieldTotalBytesSent = new Label [nCount];
        fieldRtcpPacketsSent = new Label [nCount];
        fieldLocalCollisions = new Label [nCount];
        fieldRemoteCollisions = new Label [nCount];
        fieldFailedTransmissions = new Label [nCount];

        for ( i = 0;  i < nCount;  i++ ) {
            panel = createStreamPanel ( i );
            tabControl.addPage ( panel, vectorStreamLabels.elementAt(i).toString() );
        }

        panel = new JMPanel ( new BorderLayout(6,6) );
        panelContent.add ( panel, BorderLayout.SOUTH );
        panelButtons = createButtonPanel ( new String[] { ACTION_CLOSE } );
        panel.add ( panelButtons, BorderLayout.EAST );

        this.pack ();
        this.setResizable ( false );
        updateFields ();
    }

    public void addNotify () {
        super.addNotify ();

        if ( threadUpdate == null )
            threadUpdate = new UpdateThread ();
        threadUpdate.start ();
    }

    public void removeNotify () {
        if ( threadUpdate != null ) {
            threadUpdate.terminate ();
            threadUpdate = null;
        }

        super.removeNotify ();
    }

    private JMPanel createStreamPanel ( int nIndex ) {
        JMPanel     panelStream;
        JMPanel     panel;
        JMPanel     panelLabels;
        JMPanel     panelData;
        Label       label;

        panelStream = new JMPanel ( new BorderLayout(6,6) );
        panel = new JMPanel ( new BorderLayout(6,6) );
        panelStream.add ( panel, BorderLayout.NORTH );

        panelLabels = new JMPanel ( new GridLayout(0,1,6,6) );
        panel.add ( panelLabels, BorderLayout.WEST );
        panelData = new JMPanel ( new GridLayout(0,1,6,6) );
        panel.add ( panelData, BorderLayout.CENTER );

        label = new Label ( LABEL_TOTAL_RTP_PACKETS_SENT );
        panelLabels.add ( label );
        fieldTotalRtpPacketsSent[nIndex] = new Label ( "000000000000" );
        panelData.add ( fieldTotalRtpPacketsSent[nIndex] );

        label = new Label ( LABEL_TOTAL_BYTES_SENT );
        panelLabels.add ( label );
        fieldTotalBytesSent[nIndex] = new Label ();
        panelData.add ( fieldTotalBytesSent[nIndex] );

        label = new Label ( LABEL_RTCP_PACKETS_SENT );
        panelLabels.add ( label );
        fieldRtcpPacketsSent[nIndex] = new Label ();
        panelData.add ( fieldRtcpPacketsSent[nIndex] );

        label = new Label ( LABEL_LOCAL_COLLISIONS );
        panelLabels.add ( label );
        fieldLocalCollisions[nIndex] = new Label ();
        panelData.add ( fieldLocalCollisions[nIndex] );

        label = new Label ( LABEL_REMOTE_COLLISIONS );
        panelLabels.add ( label );
        fieldRemoteCollisions[nIndex] = new Label ();
        panelData.add ( fieldRemoteCollisions[nIndex] );

        label = new Label ( LABEL_FAILED_TRANSMISSIONS );
        panelLabels.add ( label );
        fieldFailedTransmissions[nIndex] = new Label ();
        panelData.add ( fieldFailedTransmissions[nIndex] );

        return ( panelStream );
    }

    public void actionPerformed ( ActionEvent event ) {
        String  strAction;

        strAction = event.getActionCommand ();
        if ( strAction.equals(ACTION_CLOSE) ) {
            setAction ( ACTION_CLOSE );
            this.setVisible ( false );
        }
    }

    public void windowClosing ( WindowEvent event ) {
        setAction ( ACTION_CLOSE );
        this.setVisible ( false );
    }

    private void updateFields () {
        int                         i;
        int                         nCount;
        Object                      objMngr;
        SessionManager              mngrSession;
        GlobalTransmissionStats     stats;

        nCount = Math.min ( vectorMngrSessions.size(), vectorStreamLabels.size() );
        for ( i = 0;  i < nCount;  i++ ) {
            objMngr = vectorMngrSessions.elementAt ( i );
            if ( !(objMngr instanceof SessionManager) )
                continue;
            mngrSession = (SessionManager)objMngr;
            stats = mngrSession.getGlobalTransmissionStats ();

            fieldTotalRtpPacketsSent[i].setText ( "" + stats.getRTPSent() );
            fieldTotalBytesSent[i].setText ( "" + stats.getBytesSent() );
            fieldRtcpPacketsSent[i].setText ( "" + stats.getRTCPSent() );
            fieldLocalCollisions[i].setText ( "" + stats.getLocalColls() );
            fieldRemoteCollisions[i].setText ( "" + stats.getRemoteColls() );
            fieldFailedTransmissions[i].setText ( "" + stats.getTransmitFailed() );
        }
    }


    private class UpdateThread extends Thread {

        private boolean     boolTerminate = false;

        public UpdateThread () {
        }

        public void terminate () {
            boolTerminate = true;
        }


        public void run () {
            while ( boolTerminate == false ) {
                try {
                    sleep ( 1000 );
                    updateFields ();
                }
                catch ( Exception exception ) {
                }
            }
        }
    }


}


