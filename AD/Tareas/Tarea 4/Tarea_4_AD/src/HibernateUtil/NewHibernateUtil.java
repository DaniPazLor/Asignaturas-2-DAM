package HibernateUtil;

import org.hibernate.cfg.*;
import org.hibernate.SessionFactory;

/**
 *La clase configura un objeto de la clase session que podrá ser instanciado
 *
 * @author Daniel Paz Lorenzo
 */
public class NewHibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            // Crea una sessionFactory a partir del fichero hibernate.cfg.xml 
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Si no conecta lanza excepción 
            System.err.println("No se ha podido crear la sesión" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
}
