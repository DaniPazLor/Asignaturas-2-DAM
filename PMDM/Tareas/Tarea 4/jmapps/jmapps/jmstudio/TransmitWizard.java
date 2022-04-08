/*
 * @(#)TransmitWizard.java	1.5 01/03/13
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

import java.util.*;
import java.awt.*;
import javax.media.*;
import javax.media.protocol.*;
import javax.media.rtp.*;

import com.sun.media.util.JMFI18N;

import jmapps.util.*;
import jmapps.export.*;


public class TransmitWizard extends ExportWizard {

    private Vector      vectorMngrSessions = null;
    private Vector      vectorStreams = null;
    private Vector      vectorStreamLabels = null;
    

    public TransmitWizard ( Frame frame, String strSourceUrl,
			    DataSource dataSource,
			    JMAppsCfg cfgJMApps ) {
    	super ( JMFI18N.getResource("jmstudio.transmit.title"), frame, strSourceUrl, cfgJMApps );

        String    arrAllowContType [];

        strTargetType = PanelMediaTargetType.TYPE_NETWORK;

	panelSource.setDataSource(dataSource);

	arrAllowContType = new String [1];
        arrAllowContType[0] = ContentDescriptor.mimeTypeToPackageName ( ContentDescriptor.RAW_RTP );
        panelTargetFormat.setAllowContentType ( arrAllowContType );

        if ( strSourceUrl != null  &&  strSourceUrl.trim().length() > 0 ) {
            frameOwner.setCursor ( new Cursor(Cursor.WAIT_CURSOR) );
            setNextPage ();
            frameOwner.setCursor ( Cursor.getDefaultCursor() );
        }
    }

    public Processor getProcessor () {
        return ( this.processor );
    }

    public Vector getMngrSessions () {
        return ( vectorMngrSessions );
    }

    public Vector getStreams () {
        return ( vectorStreams );
    }

    public Vector getStreamLabels () {
        return ( vectorStreamLabels );
    }

    protected Panel getFirstPage () {
    	return ( panelSource );
    }

    protected Panel getLastPage () {
    	return ( panelTargetNetwork );
    }

    protected Panel getNextPage ( Panel panelPage ) {
    	Panel	panelPageNext = null;
    	String	strTargetType;

    	if ( panelPage == null ) {
    	    panelPageNext = getFirstPage ();
    	}
    	else if ( panelPage == panelSource ) {
    	    panelPageNext = panelTargetFormat;
    	}
    	else if ( panelPage == panelTargetFormat ) {
            panelPageNext = panelTargetNetwork;
    	}
    	else {
    	    panelPageNext = null;
    	}

    	return ( panelPageNext );
    }

    protected Panel getPrevPage ( Panel panelPage ) {
    	Panel	panelPagePrev = null;

    	if ( panelPage == null )
    	    panelPagePrev = getLastPage ();
    	else if ( panelPage == panelTargetNetwork )
    	    panelPagePrev = panelTargetFormat;
    	else if ( panelPage == panelTargetFormat ) {
    	    panelPagePrev = panelSource;
	    processor.close();
	    processor = null;
	    panelSource.setDataSource(null);
    	 } else
    	    panelPagePrev = null;

    	return ( panelPagePrev );
    }

    protected void createTransmitWindow () {
        vectorMngrSessions = new Vector ();
        vectorStreams = new Vector ();
        vectorStreamLabels = new Vector ();
    }

    protected void addTransmitSessionManager ( SessionManager mngrSession,
                        SendStream streamSend, String strStreamLabel ) {

        if ( vectorMngrSessions != null )
            vectorMngrSessions.addElement ( mngrSession );
        if ( vectorStreams != null )
            vectorStreams.addElement ( streamSend );
        if ( vectorStreamLabels != null )
            vectorStreamLabels.addElement ( strStreamLabel );
    }


}


