package ValidadorContrasenias;

public class ValidacionPorLongitud implements Validable{

    public ValidacionPorLongitud(){
    }

    @Override
    public boolean esValida(String clave) {
        return clave.length() > 8;
    }
}
