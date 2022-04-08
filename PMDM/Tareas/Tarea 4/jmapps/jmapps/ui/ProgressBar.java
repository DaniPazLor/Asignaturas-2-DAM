/*
 * @(#)ProgressBar.java	1.2 01/03/13
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

import java.awt.*;


public class ProgressBar extends Canvas {

    private int    nMinPos;
    private int    nMaxPos;
    private int    nCurPos;

    public ProgressBar ( int nMin, int nMax ) {
        super ();

        nMinPos = nMin;
        nMaxPos = nMax;
        if ( nMaxPos <= nMinPos )
            nMaxPos = nMinPos + 1;
        nCurPos = nMinPos;
        this.setBackground ( Color.lightGray );
    }

    public int getMinPos () {
        return ( nMinPos );
    }

    public int getMaxPos () {
        return ( nMaxPos );
    }

    public int getCurPos () {
        return ( nCurPos );
    }

    public void setCurPos ( int nPos ) {
        nCurPos = nPos;
        if ( nCurPos > nMaxPos )
            nCurPos = nMaxPos;
        if ( nCurPos < nMinPos )
            nCurPos = nMinPos;
        repaint ();
    }

    public int getCurPercent () {
        return ( 100 * (nCurPos - nMinPos) / (nMaxPos - nMinPos) );
    }


    public void paint ( Graphics graphics ) {
        Rectangle    rect;

        rect = this.getBounds ();
        rect.x = 0;
        rect.y = 0;

        graphics.setColor ( Color.darkGray );
        graphics.drawLine ( rect.x, rect.y, rect.x, rect.y + rect.height - 2 );
        graphics.drawLine ( rect.x, rect.y, rect.x + rect.width - 2, rect.y );

        graphics.setColor ( Color.white );
        graphics.drawLine ( rect.x + rect.width - 1, rect.y + rect.height - 1, rect.x + rect.width - 1, rect.y + 1 );
        graphics.drawLine ( rect.x + rect.width - 1, rect.y + rect.height - 1, rect.x + 1, rect.y + rect.height - 1 );

        rect.x++;
        rect.y++;
        rect.width -= 2;
        rect.height -= 2;

        graphics.setColor ( Color.blue );
        rect.width = rect.width * (nCurPos - nMinPos) / (nMaxPos - nMinPos);
        graphics.fillRect ( rect.x, rect.y, rect.width, rect.height );
    }

    public Dimension getPreferredSize () {
        Dimension    dim;

        dim = new Dimension ( 128, 22 );
        return ( dim );
    }

}


