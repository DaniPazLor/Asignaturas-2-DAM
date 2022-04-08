/*
 * @(#)ViewParticipantInfo.java	1.2 01/03/13
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

import jmapps.ui.*;


public class ViewParticipantInfo extends JMPanel {

    private SessionManager          mngrSession;
    private Participant             participant;
    private ViewSourceDescription   panelSrcDescr = null;

    public ViewParticipantInfo ( SessionManager mngrSession, Participant participant ) {
	    super ();

        this.mngrSession = mngrSession;
        this.participant = participant;
        try {
            init();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init () throws Exception {
        this.setLayout ( new BorderLayout() );

        panelSrcDescr = new ViewSourceDescription ( participant.getSourceDescription() );
        this.add ( panelSrcDescr, BorderLayout.NORTH );
    }

    public void updateFields ( Report report ) {
        if ( report.getParticipant() != this.participant )
            return;

        panelSrcDescr.updateFields ( report.getSourceDescription() );
    }

}


