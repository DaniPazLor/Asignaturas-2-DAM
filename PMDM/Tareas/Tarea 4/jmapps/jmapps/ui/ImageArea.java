/*
 * @(#)ImageArea.java	1.3 01/03/13
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

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;

public class ImageArea extends Canvas {

    private Image    image = null;
    private Insets   insetsBorder = new Insets (6,6,6,6);

    public ImageArea ( Image image ) {
	this.image = image;
    }

    public void setImage ( Image image ) {
	this.image = image;
    }

    public void setInsets ( int nLeft, int nTop, int nRight, int nBottom ) {
        insetsBorder.left = nLeft;
        insetsBorder.top = nTop;
        insetsBorder.right = nRight;
        insetsBorder.bottom = nBottom;
    }

    public void paint ( Graphics g ) {
        Dimension       dim;

        dim = this.getSize ();
        dim.width -= insetsBorder.left + insetsBorder.right;
        dim.height -= insetsBorder.top + insetsBorder.bottom;
        g.drawImage ( image, insetsBorder.left, insetsBorder.top, dim.width, dim.height, this );
    }

    public Dimension getPreferredSize () {
        Dimension      dim;

        if ( image == null )
            dim = new Dimension ( 0, 0 );
        else
            dim = new Dimension ( image.getWidth(this), image.getHeight(this) );
        dim.width += insetsBorder.left + insetsBorder.right;
        dim.height += insetsBorder.top + insetsBorder.bottom;
        return ( dim );
    }

    public static Image loadImage ( String strFileName ) {
        Image               image = null;
        URL                 url;
        Toolkit             toolkit;
        Class               classObject;
        Object              objImageProducer;
        ImageProducer       imageProducer;
        BufferedInputStream streamImage;
        byte                arrImageBytes [];

        toolkit = Toolkit.getDefaultToolkit ();

        if ( strFileName == null )
            return ( null );
        if ( !strFileName.startsWith("/jmapps/images/") )
            strFileName = "/jmapps/images/" + strFileName;

        try {
            classObject = Class.forName ( "jmapps.ui.ImageArea" );
            url = classObject.getResource ( strFileName );
            if ( url != null ) {
                objImageProducer = url.getContent ();
                if ( objImageProducer instanceof ImageProducer ) {
                    imageProducer = (ImageProducer) objImageProducer;
                    image = toolkit.createImage ( imageProducer );
                }
                else if ( objImageProducer instanceof BufferedInputStream ) {
                    streamImage = (BufferedInputStream) objImageProducer;
                    arrImageBytes = new byte [streamImage.available()];
                    streamImage.read ( arrImageBytes );
                    image = toolkit.createImage ( arrImageBytes );
                }
            }
        }
        catch ( Exception exception ) {
//            exception.printStackTrace ();
        }

        return ( image );
    }

    public static Image loadImage ( String strFileName, Component component, boolean boolWait ) {
        Image          image;
        MediaTracker   trackerMedia;

        image = loadImage ( strFileName );

        if ( image != null  &&  boolWait == true ) {
            trackerMedia = new MediaTracker ( component );
            trackerMedia.addImage ( image, 1001 );
            try {
                trackerMedia.waitForID ( 1001 );
            }
            catch ( Exception exception ) {
//                exception.printStackTrace ();
            }
        }
        return ( image );
    }

}


