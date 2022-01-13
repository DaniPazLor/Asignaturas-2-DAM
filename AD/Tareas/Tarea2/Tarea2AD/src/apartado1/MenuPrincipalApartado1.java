package apartado1;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Clase principal del apartado 1 que contiene los métodos main con el menú que
 * se muestra por pantalla y el método para generar el fichero xml de empleados
 *
 * @author Daniel Paz Lorenzo
 */
public class MenuPrincipalApartado1 {

    /**
     * Método main que muestra el menú para de la gestión de empleados
     *
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws javax.xml.transform.TransformerConfigurationException
     */
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException {
        //Declaración de variables y objetos locales
        Scanner teclado = new Scanner(System.in);
        String opcion = null;
        boolean val;
        FicheroIO ioFichero = new FicheroIO();
        MenuPrincipalApartado1 mpa1 = new MenuPrincipalApartado1();

        //Muestra el menú por pantalla y evalúa si la opción introducida es correcta
        do {
            val = true;
            try {
                System.out.println("-----------------------------------------");
                System.out.println("Menú de gestión del archivo EMPLEADOS.DAT");
                System.out.println("-----------------------------------------");
                System.out.println("1.-Introducir datos de empleado");
                System.out.println("2.-Mostrar datos de todos los empleados");
                System.out.println("3.-Generar fichero XML de empleados");
                System.out.println("4.-Salir");
                System.out.printf("Elegir opción: ");

                opcion = teclado.nextLine();

                switch (Integer.parseInt(opcion)) {
                    case 1:
                        ioFichero.introducirDatos();
                        break;
                    case 2:
                        ioFichero.mostrarFichero();
                        break;
                    case 3:
                        mpa1.crearFicheroEmpleadosXml(ioFichero.leerFichero());
                        break;
                    case 4:
                        val = false;
                        break;
                    default:
                        System.out.println("Introduzca una opción del 1-4");
                        System.err.println("Opción incorrecta");
                }
                //Recoge la excepción si el dato introducido no es correcto
            } catch (Exception e) {
                System.err.println("Error al introducir el dato");
            }
        } while (val);
    }

    /**
     * Método que genera el fichero empleados.xml pasando como parámetro el
     * arrayList de Empleados extraido del fichero EMPLEADOS.DAT
     *
     * @param listaEmpleados array de objetos Empleados
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws TransformerConfigurationException
     */
    public void crearFicheroEmpleadosXml(ArrayList<Empleados> listaEmpleados) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException {
        //Instanciamos un nuevo documento
        DocumentBuilderFactory docBuilFac = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder db = docBuilFac.newDocumentBuilder();
        Document doc = db.newDocument();

        //Creamos el elemento Raíz y lo añadimos al documento
        Element elementoRaiz = doc.createElement("empleados");
        doc.appendChild(elementoRaiz);

        //Vamos generando el resto de etiquetas con elementos y atributos
        //en base al array de empleados
        for (Empleados empleado : listaEmpleados) {
            Element eEmpleado = doc.createElement("empleado");
            elementoRaiz.appendChild(eEmpleado);

            Attr atrCodigo = doc.createAttribute("código");
            atrCodigo.setValue(Integer.toString(empleado.getCodigo()));
            eEmpleado.setAttributeNode(atrCodigo);

            Element eNombre = doc.createElement("nombre");
            eNombre.appendChild(doc.createTextNode(empleado.getNombre()));
            eEmpleado.appendChild(eNombre);

            Element eDireccion = doc.createElement("direccion");
            eDireccion.appendChild(doc.createTextNode(empleado.getDireccion()));
            eEmpleado.appendChild(eDireccion);

            Element eSalario = doc.createElement("salario");
            eSalario.appendChild(doc.createTextNode(String.valueOf(empleado.getSalario())));
            eEmpleado.appendChild(eSalario);

            Element eComision = doc.createElement("comision");
            eComision.appendChild(doc.createTextNode(String.valueOf(empleado.getComision())));
            eEmpleado.appendChild(eComision);

        }
        //Transformamos el documento en fichero xml
        TransformerFactory transFact = TransformerFactory.newInstance();
        Transformer transformer = transFact.newTransformer();
        //Damos las propiedades al documento y asi lo identamos 
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        //Creamos el documento que sirve como fuente y el fichero donde queremos grabarlo
        DOMSource documentoFuente = new DOMSource(doc);
        StreamResult ficheroResultado = new StreamResult("ficheros\\empleados.xml");
        try {
            transformer.transform(documentoFuente, ficheroResultado);
            System.out.println("Se ha creado el fichero empleado.xml");
        } catch (TransformerException ex) {
            Logger.getLogger(FicheroIO.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("No se ha podido crear el fichero xml");
        }
    }

}
