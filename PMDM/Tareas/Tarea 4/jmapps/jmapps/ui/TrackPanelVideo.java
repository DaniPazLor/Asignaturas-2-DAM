/*
 * @(#)TrackPanelVideo.java	1.3 01/03/13
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

package jmapps.ui;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.media.*;
import javax.media.control.*;
//import javax.media.Format;
import javax.media.format.*;
//import javax.media.protocol.*;
//import javax.media.datasink.*;

import com.sun.media.ui.VideoFormatChooser;


public class TrackPanelVideo extends TrackPanel implements ActionListener {

    private VideoFormat         formatOld;
    private String              strContentType = null;
    private VideoFormatChooser  chooserVideoFormat;


    public TrackPanelVideo ( TrackControl trackControl, ActionListener listenerEnableTrack ) {
        super ( trackControl, listenerEnableTrack );

        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setContentType( String strContentType ) {
        int            i;
        int            nSize;
        VideoFormat    formatVideo;

        arrSupportedFormats = trackControl.getSupportedFormats();
        this.strContentType = strContentType;
        nSize = arrSupportedFormats.length;
        vectorContSuppFormats = new Vector ();

        // Add the supported formats to the vector
        for ( i = 0;  i < nSize;  i++ ) {
            if ( !(arrSupportedFormats[i] instanceof VideoFormat) )
                continue;
            formatVideo = (VideoFormat) arrSupportedFormats[i];
            // assume that processor reports only valid formats
            vectorContSuppFormats.addElement( formatVideo );
        }

        chooserVideoFormat.setSupportedFormats ( vectorContSuppFormats );
        chooserVideoFormat.setCurrentFormat ( formatOld );
    }

    public boolean isTrackEnabled () {
        boolean     boolEnabled;
        boolEnabled = chooserVideoFormat.isTrackEnabled ();
        return ( boolEnabled );
    }

    public Format getFormat () {
        Format         format;
        format = chooserVideoFormat.getFormat ();
        return ( format );
    }

    public void setDefaults ( boolean boolTrackEnable, Format format ) {
        chooserVideoFormat.setTrackEnabled ( boolTrackEnable );
        if ( format instanceof VideoFormat ) {
            formatOld = (VideoFormat) format;
            chooserVideoFormat.setCurrentFormat ( formatOld );
        }
    }

    private void init() throws Exception {
        this.setLayout ( new BorderLayout() );
        formatOld = (VideoFormat) trackControl.getFormat ();
        chooserVideoFormat = new VideoFormatChooser ( arrSupportedFormats, formatOld, true, this );
        this.add ( chooserVideoFormat, BorderLayout.NORTH );
    }

    public void actionPerformed( ActionEvent event ) {
        String        strCmd;
        ActionEvent   eventNew;

        strCmd = event.getActionCommand ();
        if ( strCmd.equals(VideoFormatChooser.ACTION_TRACK_ENABLED) ) {
            eventNew = new ActionEvent ( this, ActionEvent.ACTION_PERFORMED,
					 event.getActionCommand() );
            listenerEnableTrack.actionPerformed ( eventNew );
        } else if ( strCmd.equals(VideoFormatChooser.ACTION_TRACK_DISABLED) ) {
            eventNew = new ActionEvent ( this, ActionEvent.ACTION_PERFORMED,
					 event.getActionCommand() );
            listenerEnableTrack.actionPerformed ( eventNew );
        }
    }


}


