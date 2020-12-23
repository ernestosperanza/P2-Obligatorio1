package obligatorio.dominio;
import static obligatorio.interfaz.Prueba.*;

public final class Casilla implements Cloneable{

    private String estado;

    public Casilla(String unColor) {
        setEstado(unColor);
    }

    public void setEstado(String unEstado) {
        this.estado = unEstado;
    }

    public String getEstado() {
        return this.estado;
    }

    public Object clone(){
        Object o = null;
        try{
            o = super.clone();
        }catch (CloneNotSupportedException e){
            System.out.println("No clone");
        }
        return o;
    }
    
    @Override
    public String toString() {
        if (this.estado != null) {
            return this.estado + "X" + reset;
        } else {
            return " ";
        }
    }
    
    
}


