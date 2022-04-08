/*
 * @(#)CaptureControlsDialog.java	1.2 01/03/13
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

package jmapps.jmstudio;

import java.awt.*;
import java.awt.event.*;

import javax.media.*;
import javax.media.format.*;
import javax.media.protocol.*;

import com.sun.media.util.JMFI18N;

import jmapps.ui.*;


public class CaptureControlsDialog extends JMDialog {

    private DataSource      dataSource;
    private int             nControlCount = 0;


    public CaptureControlsDialog ( Frame parentFrame, DataSource dataSource ) {
        super ( parentFrame, JMFI18N.getResource("jmstudio.capturecontrols.title"), false );

        this.dataSource = dataSource;
        try {
            init();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isEmpty () {
        return ( nControlCount < 1 );
    }

    private void init () throws Exception {
        int             i;
        int             nCount = 0;
        Object          arrControls [] = null;
        Component       componentControl;
        JMPanel         panelContent;
        Panel           panel;
        Panel           panelNext;
        Panel           panelButtons;


        this.setLayout ( new BorderLayout() );

        panelContent = new JMPanel ( new BorderLayout() );
        panelContent.setEmptyBorder ( 6, 6, 6, 6 );
        this.add ( panelContent, BorderLayout.CENTER );

        if ( dataSource != null )
            arrControls = dataSource.getControls ();
        if ( arrControls != null )
            nCount = arrControls.length;
        panel = panelContent;
        nControlCount = 0;
        for ( i = 0;  i < nCount;  i++ ) {
            if ( arrControls[i] == null )
                continue;
            if ( !(arrControls[i] instanceof Control) )
                continue;
            componentControl = ((Control)arrControls[i]).getControlComponent ();
            if ( componentControl == null )
                continue;
            nControlCount++;
            panelNext = new Panel ( new BorderLayout(6,6) );
            panelNext.add ( componentControl, BorderLayout.NORTH );
            panel.add ( panelNext, BorderLayout.CENTER );
            panel = panelNext;
        }

        panel = new JMPanel ( new FlowLayout(FlowLayout.CENTER) );
        panelContent.add ( panel, BorderLayout.SOUTH );
        panelButtons = createButtonPanel ( new String[] { ACTION_CLOSE } );
        panel.add ( panelButtons );

        this.pack ();
        this.setResizable ( false );
    }

    public void actionPerformed ( ActionEvent event ) {
        String               strCmd;


        strCmd = event.getActionCommand ();
        if ( strCmd.equals(ACTION_CLOSE) ) {
            setAction ( ACTION_CLOSE );
            setVisible ( false );
        }
    }

    public void windowClosing ( WindowEvent event ) {
        setAction ( ACTION_CLOSE );
        setVisible ( false );
    }


}


