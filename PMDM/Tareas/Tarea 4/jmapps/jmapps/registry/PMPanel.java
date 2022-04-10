/*
 * @(#)PMPanel.java	1.2 01/03/13
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

package jmapps.registry;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.media.PackageManager;

import com.sun.media.util.JMFI18N;

import jmapps.ui.*;


public class PMPanel extends JMPanel implements VectorEditor {

    private final static int PROTOCOL = 1;
    private final static int CONTENT = 2;

    VectorPanel panelProtocol;
    VectorPanel panelContent;

    Vector protocolValues = null;
    Vector contentValues  = null;


    public PMPanel() {
        super();

        setLayout ( new GridLayout(1,0,12,12) );

        panelProtocol = new VectorPanel ( JMFI18N.getResource("jmfregistry.protocolprefix.vector.label"),
                                                (VectorEditor)this, PROTOCOL );
        add ( panelProtocol );
        
        panelContent = new VectorPanel ( JMFI18N.getResource("jmfregistry.contentprefix.vector.label"),
                                                (VectorEditor)this, CONTENT );
        add ( panelContent );
    }

    /*************************************************************************
     * VectorEditor implementation
     *************************************************************************/

    public Vector getList(int code) {
        if (code == PROTOCOL) {
            protocolValues = (Vector) PackageManager.getProtocolPrefixList().clone();
            return protocolValues;
        }
        else {
            contentValues = (Vector) PackageManager.getContentPrefixList().clone();
            return contentValues;
        }
    }

    public void setList(int code, Vector list) {
        if (code == PROTOCOL)
            protocolValues = list;
        else
            contentValues = list;
    }

    public void commit(int code) {
        if (code == PROTOCOL) {
            PackageManager.setProtocolPrefixList(protocolValues);
            PackageManager.commitProtocolPrefixList();
        }
        else {
            PackageManager.setContentPrefixList(contentValues);
            PackageManager.commitContentPrefixList();
        }
    }

    public void selectedIndex(int code, int index) {
	// Nothing to to
    }

    public boolean addToList(int code, String value) {
        if (code == PROTOCOL) {
            if (protocolValues.indexOf(value) < 0)
                return true;
            else
                return false;
        }
        else {
            if (contentValues.indexOf(value) < 0)
                return true;
            else
                return false;
        }
    }
    
}


