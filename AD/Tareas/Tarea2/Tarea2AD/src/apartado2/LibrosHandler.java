//SAX: Simple API for XML, es decir, una API para acceder a XML desde Java
package apartado2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Clase que nos sirve de manejador del fichero xml a traves de la interfaz
 * ContentHandler de la clase DefaultHandler
 *
 * @author Daniel Paz Lorenzo
 */
public class LibrosHandler extends DefaultHandler {
    //Vamos a recorrer secuencialmente el XML hasta el final
    //Sax lee directamente desde el fichero
    //Recojo el código que me proporciona mediante Source>Insert Code>Override Method

    /**
     * Método sobreescrito y definido que nos da como resultado los carateres
     * obtenidos dentro de cada etiqueta
     *
     * @param ch
     * @param start
     * @param length
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length); //To change body of generated methods, choose Tools | Templates.
        //Concatena a la cadena resultado cada uno de los caracteres de la cadena detectada
        String cadena = new String(ch, start, length);
        System.out.print(cadena);
    }

    /**
     * Método sobreescrito y definido que hacer cuando se detecta el final del
     * elemento "libro"
     *
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("libro")) {
            System.out.println("--------------------------------\n");
        }
    }

    /**
     * Método sobreescrito y definido
     *
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("libro")) {
            System.out.print("Publicado en: "
                    + attributes.getValue(attributes.getQName(0)));
        } else if (qName.equals("titulo")) {
            System.out.print("El título es: ");
        } else if (qName.equals("apellido")) {
            System.out.print("El apellido del autor es: ");
        } else if (qName.equals("nombre")) {
            System.out.print("El nombre del autor es: ");
        } else if (qName.equals("editorial")) {
            System.out.print("La editorial del libro es: ");
        } else if (qName.equals("precio")) {
            System.out.print("El precio del libro es: ");
        }
    }
}
