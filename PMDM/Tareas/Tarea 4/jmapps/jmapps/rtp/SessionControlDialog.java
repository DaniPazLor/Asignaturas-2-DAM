/*
 * @(#)SessionControlDialog.java	1.3 01/03/13
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
import javax.media.control.*;

import com.sun.media.util.JMFI18N;
import com.sun.media.ui.TabControl;

import jmapps.ui.*;


public class SessionControlDialog extends JMDialog {

    private SessionManager      mngrSession;


    public SessionControlDialog ( Frame frame, SessionManager mngrSession ) {
	    super ( frame, JMFI18N.getResource("jmstudio.rtpsessionctrl.title"), false );

        this.mngrSession = mngrSession;
        try {
            init();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init () throws Exception {
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

        panel = createOverallStatsPanel ();
        tabControl.addPage ( panel, JMFI18N.getResource("jmstudio.rtpsessionctrl.panel.overall") );
        panel = createParticipantsPanel ();
        tabControl.addPage ( panel, JMFI18N.getResource("jmstudio.rtpsessionctrl.panel.participants") );
        panel = createBufferControlPanel ();
        tabControl.addPage ( panel, JMFI18N.getResource("jmstudio.rtpsessionctrl.panel.bufferctrl") );

        panel = new JMPanel ( new BorderLayout(6,6) );
        panelContent.add ( panel, BorderLayout.SOUTH );
        panelButtons = createButtonPanel ( new String[] { ACTION_CLOSE } );
        panel.add ( panelButtons, BorderLayout.EAST );

        this.pack ();
        this.setResizable ( false );
    }

    private JMPanel createOverallStatsPanel () {
        JMPanel     panelStats;

        panelStats = new PanelOverallRtpStats ( mngrSession );
        return ( panelStats );
    }

    private JMPanel createParticipantsPanel () {
        JMPanel     panelParticipants;

        panelParticipants = new PanelParticipants ( mngrSession );
        return ( panelParticipants );
    }

    private JMPanel createBufferControlPanel () {
        JMPanel         panelBufferControl;
        BufferControl   bufferControl;
        Component       component = null;

        panelBufferControl = new JMPanel ( new FlowLayout() );

        bufferControl = (BufferControl) mngrSession.getControl ( "javax.media.control.BufferControl" );
        if ( bufferControl != null )
            component = bufferControl.getControlComponent ();

        if ( component != null )
            panelBufferControl.add ( component );

        return ( panelBufferControl );
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

}


