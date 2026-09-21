package ni.edu.uam.primercorte7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Consulta_Estudiante {

    @FXML
    private TextField txtBuscarCodigo;

    @FXML
    private TableView<Estudiante> tvEstudiantes;

    @FXML
    private TableColumn<Estudiante, String> colCodigo;

    @FXML
    private TableColumn<Estudiante, String> colNombres;

    @FXML
    private TableColumn<Estudiante, String> colApellidos;

    @FXML
    private TableColumn<Estudiante, String> colCarrera;

    @FXML
    private TableColumn<Estudiante, LocalDate> colFechaNacimiento;

    private final EstudianteDao estudianteDao = new EstudianteDao();

    @FXML
    public void initialize() {

        colCodigo.setCellValueFactory(
                new PropertyValueFactory<>("codigo")
        );

        colNombres.setCellValueFactory(
                new PropertyValueFactory<>("nombres")
        );

        colApellidos.setCellValueFactory(
                new PropertyValueFactory<>("apellidos")
        );

        colCarrera.setCellValueFactory(
                new PropertyValueFactory<>("carrera")
        );

        colFechaNacimiento.setCellValueFactory(
                new PropertyValueFactory<>("fechaNacimiento")
        );

        mostrarTodos();
    }

    @FXML
    private void buscarEstudiante(ActionEvent event) {

        String codigo = txtBuscarCodigo.getText().trim();

        if (codigo.isEmpty()) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campo vacío",
                    "Ingrese el código del estudiante."
            );
            return;
        }

        Estudiante estudiante = estudianteDao.buscarPorCodigo(codigo);

        if (estudiante != null) {

            ObservableList<Estudiante> resultado =
                    FXCollections.observableArrayList();

            resultado.add(estudiante);

            tvEstudiantes.setItems(resultado);

        } else {

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Estudiante no encontrado",
                    "No existe un estudiante con el código ingresado."
            );
        }
    }

    @FXML
    private void mostrarTodos(ActionEvent event) {
        mostrarTodos();
    }

    private void mostrarTodos() {

        tvEstudiantes.setItems(
                estudianteDao.obtenerEstudiantes()
        );
    }

    @FXML
    private void regresar(ActionEvent event) {

        Stage stage = (Stage) tvEstudiantes
                .getScene()
                .getWindow();

        stage.close();
    }

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String titulo,
            String mensaje) {

        Alert alerta = new Alert(tipo);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}
