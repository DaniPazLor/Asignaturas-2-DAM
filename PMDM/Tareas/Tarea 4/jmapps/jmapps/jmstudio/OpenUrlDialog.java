/*
 * @(#)OpenUrlDialog.java	1.2 01/03/13
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

import com.sun.media.util.JMFI18N;

import jmapps.ui.*;


public class OpenUrlDialog extends JMDialog {

    public static final String     LABEL = JMFI18N.getResource ( "jmstudio.openurl.label" );

    private TextField   fieldUrl;
    private String      nameUrlDefault = null;


    public OpenUrlDialog ( Frame frame, String nameUrlDefault ) {
        super ( frame, JMFI18N.getResource("jmstudio.openurl.title"), true );

        this.nameUrlDefault = nameUrlDefault;
        try {
            init();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUrl () {
        String  nameUrl;

        nameUrl = fieldUrl.getText ();
        return ( nameUrl );
    }


    protected void init () throws Exception {
        JMPanel     panel;
        JMPanel     panelButtons;
        Label       label;

	    setLayout ( new BorderLayout() );

        panel = new JMPanel ( new BorderLayout() );
        panel.setEmptyBorder ( 6, 6, 6, 6 );
        this.add ( panel, BorderLayout.CENTER );

        label = new Label ( LABEL );
        panel.add ( label, BorderLayout.WEST );

        if ( nameUrlDefault == null )
            nameUrlDefault = "";
	    fieldUrl = new TextField ( nameUrlDefault, 30 );
        fieldUrl.addActionListener ( this );
        panel.add ( fieldUrl, BorderLayout.CENTER );

        panel = new JMPanel ( new FlowLayout(FlowLayout.CENTER) );
        this.add ( panel, BorderLayout.SOUTH );
        panelButtons = createButtonPanel ( new String[] { ACTION_OPEN, ACTION_CANCEL } );
        panel.add ( panelButtons );

        this.pack ();
        this.setResizable ( false );
    }

    public void actionPerformed ( ActionEvent event ) {
        String      strAction;
        Object      objSource;

        strAction = event.getActionCommand ();
        objSource = event.getSource ();
        if ( strAction.equals(ACTION_OPEN) ) {
            this.setAction ( strAction );
            this.dispose ();
        }
        else if ( strAction.equals(ACTION_CANCEL) ) {
            this.setAction ( strAction );
            this.dispose ();
        }
        else if ( objSource == fieldUrl ) {
            this.setAction ( ACTION_OPEN );
            this.dispose ();
        }
    }


}


