/*
 * @(#)Export.java	1.2 01/03/13
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

import java.util.*;
import java.awt.*;
import java.awt.event.*;

import com.sun.media.util.JMFI18N;

import jmapps.export.*;
import jmapps.ui.*;


public class Export extends JMFrame {

    private ExportWizard    dlgExport = null;
    private Vector          vectorWindows = new Vector ();

    public Export ( String strSourceUrl ) {
        super ( JMFI18N.getResource("jmstudio.export.title") );

        dlgExport = new ExportWizard ( this, strSourceUrl, null );
        dlgExport.addWindowListener ( this );
        dlgExport.setVisible ( true );
    }

    public static void main ( String [] args ) {
        Export          frameExport;
        String          strSourceUrl;

        if ( args.length < 1 )
            strSourceUrl = "";
        else
            strSourceUrl = args[0];
        frameExport = new Export ( strSourceUrl );
    }

    public void windowClosed ( WindowEvent event ) {
        int       i;
        int       nCount;
        Object    objWindow;
        Window    window;

        window = event.getWindow ();
        if ( window == dlgExport ) {
            vectorWindows = dlgExport.getWindowsLeft ();
            nCount = vectorWindows.size ();
            for ( i = 0;  i < nCount;  i++ ) {
                objWindow = vectorWindows.elementAt ( i );
                if ( !(objWindow instanceof Window) )
                    continue;
                if ( !((Window)objWindow).isShowing() )
                    continue;
                ((Window)objWindow).addWindowListener ( this );
            }
        }
        else if ( vectorWindows.contains(window) ) {
                vectorWindows.removeElement ( window );
        }

        if ( vectorWindows.isEmpty() )
            System.exit ( 0 );
    }

}


