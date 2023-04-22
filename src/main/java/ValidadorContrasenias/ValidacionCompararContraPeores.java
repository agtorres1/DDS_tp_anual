package ValidadorContrasenias;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ValidacionCompararContraPeores implements Validable{
    public ValidacionCompararContraPeores() {
    }

    @Override
    public boolean esValida(String clave) {
        Path path = Paths.get("src/main/java/ValidadorContrasenias/10000-most-common-passwords.txt");

        File archivo = new File(path.toAbsolutePath().toString());
        Scanner lector = null;
        try {
            lector = new Scanner(archivo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(lector.hasNextLine()){

            if(lector.nextLine().equals(clave)){
                return false;
            }
        }
        return true;
    }
}
