
package serverHTTP_Multihilo;

/**
 *
 * @author IMCG
 */
//Mensajes que intercambia el Servidor con el Cliente según protocolo HTTP
public class Mensajes {
 public static final String lineaInicial_OK = "HTTP/3.0 200 OK";
  public static final String lineaInicial_NotFound =
          "HTTP/3.0 404 Not Found";
//  public static final String lineaInicial_BadRequest =
//          "HTTP/3.0 400 Bad Request";
 
}
