package ni.edu.uam.estudiantes.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ni.edu.uam.primercorte7.models.Estudiante;

public class EstudianteDao {

    private static final ObservableList<Estudiante> estudiantes =
            FXCollections.observableArrayList();

    public void agregar(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public ObservableList<Estudiante> obtenerEstudiantes() {
        return estudiantes;
    }

    public Estudiante buscarPorCodigo(String codigo) {

        for (Estudiante estudiante : estudiantes) {

            if (estudiante.getCodigo().equalsIgnoreCase(codigo)) {
                return estudiante;
            }
        }

        return null;
    }

    public boolean existeCodigo(String codigo) {
        return buscarPorCodigo(codigo) != null;
    }

    public boolean eliminar(String codigo) {

        Estudiante estudiante = buscarPorCodigo(codigo);

        if (estudiante != null) {
            estudiantes.remove(estudiante);
            return true;
        }

        return false;
    }

    public boolean actualizar(Estudiante estudianteActualizado) {

        for (int i = 0; i < estudiantes.size(); i++) {

            Estudiante estudiante = estudiantes.get(i);

            if (estudiante.getCodigo()
                    .equalsIgnoreCase(estudianteActualizado.getCodigo())) {

                estudiantes.set(i, estudianteActualizado);
                return true;
            }
        }

        return false;
    }
}