package ValidadorContrasenias;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ValidadorDeContrasenias implements Validable{
  private List<Validable> criterios;

  public ValidadorDeContrasenias() {
    this.criterios = new ArrayList<>();
    this.criterios.add(new ValidacionCompararContraPeores());
    this.criterios.add(new ValidacionPorLongitud());
    this.criterios.add(new ValidacionPorComplejidad());
  }

  @Override
  public boolean esValida(String clave){
    return criterios.stream().allMatch(criterio ->{
      return criterio.esValida(clave);
    });
  }

  public void agregarCriterio(Validable criterio){
    criterios.add(criterio);
  }
}

