/*
 * @(#)ViewParticipantList.java	1.5 01/03/13
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

import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import javax.media.rtp.*;

import com.sun.media.util.JMFI18N;

import jmapps.ui.*;


public class ViewParticipantList extends JMPanel {

    public static final String  PARTICIPANTS    = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.partcipants" );
    public static final String  NONE            = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.partcipants.none" );
    public static final String  REMOTE          = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.partcipants.remote" );
    public static final String  LOCAL           = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.partcipants.local" );
    public static final String  PASSIVE         = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.partcipants.passive" );
    public static final String  ACTIVE          = JMFI18N.getResource ( "jmstudio.rtpsessionctrl.partcipants.active" );

    private SessionManager  mngrSession;
    private List            listParticipantsRemote;
    private List            listParticipantsLocal;
    private List            listParticipantsActive;
    private List            listParticipantsPassive;


    public ViewParticipantList ( SessionManager mngrSession ) {
	    super ();

        this.mngrSession = mngrSession;
        try {
            init();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init () throws Exception {
        JMPanel panel;
        Label   label;

        this.setLayout ( new GridLayout(0,1,6,6) );

        panel = new JMPanel ( new BorderLayout() );
        this.add ( panel );
        label = new Label ( REMOTE + ":" );
        panel.add ( label, BorderLayout.NORTH );
        listParticipantsRemote = new List ();
        panel.add ( listParticipantsRemote, BorderLayout.CENTER );

        panel = new JMPanel ( new BorderLayout() );
        this.add ( panel );
        label = new Label ( LOCAL + ":" );
        panel.add ( label, BorderLayout.NORTH );
        listParticipantsLocal = new List ();
        panel.add ( listParticipantsLocal, BorderLayout.CENTER );

        panel = new JMPanel ( new BorderLayout() );
        this.add ( panel );
        label = new Label ( ACTIVE + ":" );
        panel.add ( label, BorderLayout.NORTH );
        listParticipantsActive = new List ();
        panel.add ( listParticipantsActive, BorderLayout.CENTER );

        panel = new JMPanel ( new BorderLayout() );
        this.add ( panel );
        label = new Label ( PASSIVE + ":" );
        panel.add ( label, BorderLayout.NORTH );
        listParticipantsPassive = new List ();
        panel.add ( listParticipantsPassive, BorderLayout.CENTER );

        updateFields ();
    }


    boolean compare(Vector list1, Vector list2) {
	if (list1.size() != list2.size())
	    return false;
	for (int i = 0; i < list1.size(); i++) {
	    if (!list2.contains(list1.elementAt(i)))
		return false;
	}
	return true;
    }


    private Vector lastRemoteList = new Vector();
    private Vector lastLocalList = new Vector();
    private Vector lastActiveList = new Vector();
    private Vector lastPassiveList = new Vector();
    long lastCheckTime = -1;

    public void updateFields () {
        int     i;
        int     nCount;
        Vector  vectorParticipants;
        Object  objParticipant;

	long now = System.currentTimeMillis();

	// Check at only every 3 seconds interval.
	if (now - lastCheckTime < 3000L)
	    return;

	lastCheckTime = now;

	// list the remote users.
        vectorParticipants = getParticipants ( mngrSession, REMOTE );
	if (!compare(lastRemoteList, vectorParticipants)) {
	    if (listParticipantsRemote.getItemCount() > 0)
                listParticipantsRemote.removeAll ();
            nCount = vectorParticipants.size ();
            for ( i = 0;  i < nCount;  i++ ) {
                objParticipant = vectorParticipants.elementAt ( i );
                if ( objParticipant != null  &&  objParticipant instanceof Participant )
                    listParticipantsRemote.add ( ((Participant)objParticipant).getCNAME() );
            }

            if ( nCount < 1 )
                listParticipantsRemote.add ( NONE );

	    lastRemoteList.removeAllElements();
	    for (int j = 0; j < vectorParticipants.size(); j++)
	        lastRemoteList.addElement(vectorParticipants.elementAt(j));	
	}

	// List the local users.
        vectorParticipants = getParticipants ( mngrSession, LOCAL );
	if (!compare(lastLocalList, vectorParticipants)) {
	    if (listParticipantsLocal.getItemCount() > 0)
                listParticipantsLocal.removeAll ();
            nCount = vectorParticipants.size ();
            for ( i = 0;  i < nCount;  i++ ) {
                objParticipant = vectorParticipants.elementAt ( i );
                if ( objParticipant != null  &&  objParticipant instanceof Participant )
                    listParticipantsLocal.add ( ((Participant)objParticipant).getCNAME() );
            }
            if ( nCount < 1 )
                listParticipantsLocal.add ( NONE );

	    lastLocalList.removeAllElements();
	    for (int j = 0; j < vectorParticipants.size(); j++)
	        lastLocalList.addElement(vectorParticipants.elementAt(j));	
	}

	// List the active users.
        vectorParticipants = getParticipants ( mngrSession, ACTIVE );
	if (!compare(lastActiveList, vectorParticipants)) {
	    if (listParticipantsActive.getItemCount() > 0)
                listParticipantsActive.removeAll ();
            nCount = vectorParticipants.size ();
            for ( i = 0;  i < nCount;  i++ ) {
                objParticipant = vectorParticipants.elementAt ( i );
                if ( objParticipant != null  &&  objParticipant instanceof Participant )
                    listParticipantsActive.add ( ((Participant)objParticipant).getCNAME() );
            }
            if ( nCount < 1 )
                listParticipantsActive.add ( NONE );

	    lastActiveList.removeAllElements();
	    for (int j = 0; j < vectorParticipants.size(); j++)
	        lastActiveList.addElement(vectorParticipants.elementAt(j));	
        }

	// List the passive users.
        vectorParticipants = getParticipants ( mngrSession, PASSIVE );
	if (!compare(lastPassiveList, vectorParticipants)) {
	    if (listParticipantsPassive.getItemCount() > 0)
                listParticipantsPassive.removeAll ();
            nCount = vectorParticipants.size ();
            for ( i = 0;  i < nCount;  i++ ) {
                objParticipant = vectorParticipants.elementAt ( i );
                if ( objParticipant != null  &&  objParticipant instanceof Participant )
                    listParticipantsPassive.add ( ((Participant)objParticipant).getCNAME() );
            }
            if ( nCount < 1 )
                listParticipantsPassive.add ( NONE );

	    lastPassiveList.removeAllElements();
	    for (int j = 0; j < vectorParticipants.size(); j++)
	        lastPassiveList.addElement(vectorParticipants.elementAt(j));	
        }
    }

    public static Vector getParticipants ( SessionManager mngrSession, String strListType ) {
        Vector  vectorParticipants = null;

        if ( strListType.equals(REMOTE) ) {
            vectorParticipants = mngrSession.getRemoteParticipants ();
        }
        else if ( strListType.equals(LOCAL) ) {
            vectorParticipants = new Vector ();
            vectorParticipants.addElement ( mngrSession.getLocalParticipant() );
        }
        else if ( strListType.equals(PASSIVE) ) {
            vectorParticipants = mngrSession.getPassiveParticipants ();
        }
        else if ( strListType.equals(ACTIVE) ) {
            vectorParticipants = mngrSession.getActiveParticipants ();
        }

        return ( vectorParticipants );
    }

}


