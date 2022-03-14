package tareaExists;

import java.util.Scanner;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.exist.*;



public class Main {
    

		
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		XQConnection conexion=conexion();
		
		if(conexion!=null){
			int id=-1;
			int numComensales=-1;
			
			System.out.print("Introduce el ID de menu: ");
			try{
				id=sc.nextInt();
			}catch(Exception e){
				System.err.println("Cadena inválida");
				System.exit(0);
			}
			
			System.out.print("Introduce el número de comensales: ");
			try{
				numComensales=sc.nextInt();
			}catch(Exception e){
				System.err.println("Cadena inválida");
				System.exit(0);
			}
	sc.close();
			
			if(numComensales<1 | (id<1 | id>8)){
				System.err.println("Alguno de los datos introducidos no es correcto");
				System.exit(0);
			}else{
				
				//Lista con letras y números
				String consulta="for $lista in distinct-values(/menus/menu[@id="+id+"]/plato/ingrediente/@nombre/string()) "+ 
                "let $cant:= sum(/menus/menu[@id="+id+"]/plato/ingrediente[@nombre= $lista]/cantidad/number()) *"+numComensales+" "
                +"return ($lista,$cant)";
				
				//Lista en formato XML
				
				/*String consulta="for $lista in distinct-values(/menus/menu[@id="+id+"]/plato/ingrediente/@nombre)" +
				" let $cant:= sum(/menus/menu[@id="+id+"]/plato/ingrediente[@nombre= $lista]/cantidad) *"+numComensales+
				" return (<ingrediente>{$lista}<cantidad>{$cant}</cantidad></ingrediente>)";*/
				
				try {
					XQExpression cons= conexion.createExpression();
					XQResultSequence resultado=cons.executeQuery(consulta);
				
					
					    
					 
					System.out.println("\n\n*****LISTA DE LA COMPRA******\n\n");
					//System.out.println(resultado.getSequenceAsString(null)); **Muestra todo seguido**
					
					while(resultado.next()){
						//Mostramos individual con salto de línea
					  System.out.println(resultado.getItemAsString(null)+"\n");
					}
					   
					
				} catch (XQException e) {
					
					e.printStackTrace();
				
			}
			
			}
		}else{
			System.err.println("Parece que hay un problema de conexión");
		}

	}
    
	
	
	
	private static XQConnection conexion (){
		
    XQConnection conexion=null;
        try {
		
		    //Los datos provienen de Exist,nuestro recurso de datos será este entonces.
		    //Establecemos los parámetros para acceder a Exist 
             XQDataSource recurso= new ExistXQDataSource();
		    recurso.setProperty("serverName", "localhost");
		    recurso.setProperty("port", "8083");
		
		    //Configurados puerto y servidor , creamos conexión.
		    conexion=recurso.getConnection();
	       } catch (XQException e) {
		   e.printStackTrace();
	       }
     return conexion;
	}
	
}
