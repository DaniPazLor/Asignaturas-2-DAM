package examenPSP;

/**
 *
 * @author AnaIglesias
 */
public class Refranes {
    //recurso compartido
    private String [] refranes = new String[6];
    
    public Refranes(){
        //creamos los refranes
        refranes[0]= "Mas vale tarde que nunca";
        refranes[1]= "no por mucho madrugar amanece mas temprano";
        refranes[2]= "mas vale prevenir que lamentar";
        refranes[3]= "mas vale pajaro en mano que ciento volando";
        refranes[4]= "al que madruga dios le ayuda";
        refranes[5]= "se cree el ladron que todos son de su condicion";
    }

    public String[] getRefranes() {
        return refranes;
    }
    
    public String getRefran(int posicion){
        return refranes[posicion];
    }
    
}
