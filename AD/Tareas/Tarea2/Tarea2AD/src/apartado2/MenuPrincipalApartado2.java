package apartado2;

import java.io.*;
import java.util.Scanner;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Clase que contiene el menú de selección del modo de visualización del fichero
 * y los 2 métodos para hacerlo dependiendo de la opción que elija el usuario
 *
 * @author Daniel Paz Lorenzo
 */
public class MenuPrincipalApartado2 {

    /**
     * Método main que muestra por pantalla el menú al usuario, recoge la opción
     * seleccionada y realiza la opción pertinente dependiendo de la misma
     *
     * @param args
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //Declaración de variables y objetos locales
        Scanner teclado = new Scanner(System.in);
        boolean val = true;
        //Muestra el menú por pantalla y evalúa la selección incluido si es erronea
        do {
            System.out.println("MENÚ DE SELECCIÓN PARA MOSTRAR LAS ETIQUETAS DEL FICHERO ´LIBROS.XML´");
            System.out.println("---------------------------------------------------------------------");
            System.out.println("1.-Visualizar el fichero con la API SAX");
            System.out.println("2.-Visualizar el fichero con la API DOM");
            System.out.println("3.-Salir");
            System.out.printf("Elija opción: ");

            String opcion = teclado.nextLine();
            switch (Integer.parseInt(opcion)) {
                case 1:
                    visualizarConSax();
                    break;
                case 2:
                    visualizarConDom();
                    break;
                case 3:
                    val = false;
                    break;
                default:
                    System.out.println("Introduzca una opción del 1-3");
                    System.err.println("Opción incorrecta");
            }
        } while (val);

    }

    /**
     * Método para visualizar el contenido del fichero xml haciendo uso de la
     * clase SAX contenida en la API de java
     *
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static void visualizarConSax() throws ParserConfigurationException, SAXException, IOException {
        //Construimos el objeto SAXParser a través de la SAXParserFactory
        SAXParserFactory saxparserfactory = SAXParserFactory.newInstance();
        //Parser: analizador léxico-sintáctico
        SAXParser saxparser = saxparserfactory.newSAXParser();
        //Declaramos el objeto File del fichero
        File file = new File("ficheros\\libros.xml");
        LibrosHandler handler = new LibrosHandler();

        saxparser.parse(file, handler);
    }

    /**
     * Método para visualizar el contenido del fichero xml haciendo uso de la
     * clase DOM de la API de java
     *
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static void visualizarConDom() throws ParserConfigurationException, SAXException, IOException {
        //Instanciamos el fichero xml con la clase file
        File ficheroLibros = new File("ficheros\\libros.xml");
        //Parseamos a un documento ficheroLibros
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document documento = db.parse(ficheroLibros);

        documento.getDocumentElement().normalize();
        //Almacenamos la lista de la etiqueta libro
        NodeList listaNodoLibro = documento.getElementsByTagName("libro");
        //Recorremos todos los nodos del documento y los vamos mostrando
        for (int i = 0; i < listaNodoLibro.getLength(); i++) {

            Node nodoLibro = listaNodoLibro.item(i);

            if (nodoLibro.getNodeType() == Node.ELEMENT_NODE) {
                Element elementoLibro = (Element) nodoLibro;

                System.out.println("\nTitulo del libro: "
                        + elementoLibro.getElementsByTagName("titulo").item(0).getTextContent());
                System.out.println("\tAño de publicación: " + elementoLibro.getAttribute("año"));

                System.out.println("\tAutor o autores: ");
                NodeList listaNodoAutor = elementoLibro.getElementsByTagName("autor");
                for (int j = 0; j < listaNodoAutor.getLength(); j++) {
                    Node nodoAutor = listaNodoAutor.item(j);

                    if (nodoAutor.getNodeType() == Node.ELEMENT_NODE) {

                        Element elementosAutor = (Element) nodoAutor;
                        System.out.println("\t\tapellidos: " + elementosAutor.getElementsByTagName("apellido").item(0).getTextContent());
                        System.out.println("\t\tnombre: " + elementosAutor.getElementsByTagName("nombre").item(0).getTextContent());
                        System.out.println("");
                    }
                }
                System.out.println("\tEditorial: "
                        + elementoLibro.getElementsByTagName("editorial").item(0).getTextContent());
                System.out.println("\tPrecio: "
                        + elementoLibro.getElementsByTagName("precio").item(0).getTextContent());
            }
            System.out.println("---------------------------------------------------");
        }

    }

}
