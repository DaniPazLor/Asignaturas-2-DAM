/*
 * @(#)AboutDialog.java	1.6 01/03/13
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

import java.lang.*;
import java.lang.reflect.*;
import java.util.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.media.*;

import com.sun.media.util.JMFI18N;

import jmapps.ui.*;


public class AboutDialog extends JMDialog {

    private Image       imageAbout;
    private Image       imageSunLogo;
    private TextView    textCopyrightEng;
    private TextView    textCopyrightFr;


    public AboutDialog ( Frame frame ) {
	    super ( frame, JMFI18N.getResource("jmstudio.about.title"), true );

        try {
            init();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createDialog ( Frame frame ) {
        AboutDialog    dlg;

        if ( frame == null )
            frame = new Frame ();
        dlg = new AboutDialog ( frame );
        dlg.show ();
    }

    protected void init () throws Exception {
        Panel           panelImage;
        JMPanel         panelAboutText;
        JMPanel         panelLogo;
        Panel           panel;
        Panel           panelButtons;
        Panel           panelCopyright;
        ImageArea       fieldImage;
        Label           label;
        String          strVersion;
        Dimension       dimText;
        int             nTextHeightEng;
        int             nTextHeightFr;
        String          strCopyright;
        Font            font;


        this.addWindowListener ( this );
        this.setLayout ( new BorderLayout() );
        this.setBackground ( new Color(204,204,204) );

        this.imageAbout = ImageArea.loadImage ( "aboutBox.gif", this, true );
        this.imageSunLogo = ImageArea.loadImage ( "SunLogoForGrey.gif", this, true );

        panelImage = new Panel ( new BorderLayout() );
        this.add ( panelImage, BorderLayout.NORTH );
        panelAboutText = new JMPanel ( new BorderLayout(6,6) );
        panelAboutText.setEmptyBorder ( 12, 12, 12, 12 );
        this.add ( panelAboutText, BorderLayout.CENTER );
        panelLogo = new JMPanel ( new BorderLayout() );
        panelLogo.setEmptyBorder ( 12, 12, 12, 12 );
        this.add ( panelLogo, BorderLayout.SOUTH );

        fieldImage = new ImageArea ( imageAbout );
        fieldImage.setInsets ( 0, 0, 0, 0 );
        panelImage.add ( fieldImage, BorderLayout.CENTER );

        strVersion = Manager.getVersion ();
        strVersion = JMFI18N.getResource("jmstudio.about.version") + " " + strVersion;
        label = new Label ( strVersion );
        panelAboutText.add ( label, BorderLayout.NORTH );

        panelCopyright = new Panel ( new GridLayout(0,1,6,6) );
        panelAboutText.add ( panelCopyright, BorderLayout.CENTER );
        font = new Font ( "Dialog", Font.PLAIN, 10 );

        strCopyright = "Copyright 2001 Sun Microsystems, Inc. All rights reserved. Use is subject to license terms. Third-party software, including font technology, is copyrighted and licensed from Sun suppliers. Sun, Sun Microsystems, the Sun Logo, and Java are trademarks or registered trademarks of Sun Microsystems, Inc. in the U.S. and other countries. Federal Acquisitions: Commercial Software -- Government users subject to standard license terms and conditions.";
        textCopyrightEng = new TextView ( strCopyright );
        textCopyrightEng.setFont ( font );
        panelCopyright.add ( textCopyrightEng );

        strCopyright = "Copyright 2001 Sun Microsystems, Inc. Tous droits r�serv�s. Distribu� par des licences qui en restreignent l'u. Le logiciel d�tenu par des tiers, et qui comprend la technologie relative aux polices de caract�res, est prot�g� par un copyright et licenci� par des fournisseurs de Sun. Sun, Sun Microsystems, le logo Sun, et Java sont des marques de fabrique ou des marques d�pos�es de Sun Microsystems, Inc. aux Etats-Unis et dans d'autres pays.";
        textCopyrightFr = new TextView ( strCopyright );
        textCopyrightFr.setFont ( font );
        panelCopyright.add ( textCopyrightFr );

        fieldImage = new ImageArea ( imageSunLogo );
        panelLogo.add ( fieldImage, BorderLayout.WEST );

        panel = new JMPanel ( new BorderLayout(6,6) );
        panelLogo.add ( panel, BorderLayout.EAST );
        panelButtons = createButtonPanel ( new String[] { ACTION_CLOSE } );
        panel.add ( panelButtons, BorderLayout.SOUTH );

        this.pack ();
        
        dimText = textCopyrightEng.getSize();
        textCopyrightEng.setPreferredWidth ( dimText.width );
        dimText = textCopyrightFr.getSize();
        textCopyrightFr.setPreferredWidth ( dimText.width );

        this.setResizable ( false );
        this.pack ();
        setLocationCenter ();
    }

    public void actionPerformed ( ActionEvent event ) {
        String  strAction;

        strAction = event.getActionCommand ();
        if ( strAction.equals(ACTION_CLOSE) ) {
            setAction ( ACTION_CLOSE );
            this.dispose ();
        }
    }

    public void windowClosing ( WindowEvent event ) {
        setAction ( ACTION_CLOSE );
        this.dispose ();
    }

}


