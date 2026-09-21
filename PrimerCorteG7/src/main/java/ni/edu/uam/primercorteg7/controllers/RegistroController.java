package ni.edu.uam.estudiantes.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ni.edu.uam.estudiantes.models.Estudiante;

import java.io.IOException;
import java.time.LocalDate;

public class RegistroController {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombres;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtCarrera;

    @FXML
    private DatePicker dpFechaNacimiento;

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

    private final ObservableList<Estudiante> listaEstudiantes =
            FXCollections.observableArrayList();

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

        tvEstudiantes.setItems(listaEstudiantes);
    }

    @FXML
    private void guardarEstudiante(ActionEvent event) {

        String codigo = txtCodigo.getText().trim();
        String nombres = txtNombres.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String carrera = txtCarrera.getText().trim();
        LocalDate fechaNacimiento = dpFechaNacimiento.getValue();

        if (codigo.isEmpty()
                || nombres.isEmpty()
                || apellidos.isEmpty()
                || carrera.isEmpty()
                || fechaNacimiento == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos incompletos",
                    "Debe completar todos los campos."
            );

            return;
        }

        Estudiante estudiante = new Estudiante(
                codigo,
                nombres,
                apellidos,
                carrera,
                fechaNacimiento
        );

        listaEstudiantes.add(estudiante);

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Registro exitoso",
                "El estudiante fue registrado correctamente."
        );

        limpiar();
    }

    @FXML
    private void limpiarCampos(ActionEvent event) {
        limpiar();
    }

    private void limpiar() {

        txtCodigo.clear();
        txtNombres.clear();
        txtApellidos.clear();
        txtCarrera.clear();
        dpFechaNacimiento.setValue(null);

        txtCodigo.requestFocus();
    }

    @FXML
    private void regresar(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/ni/edu/uam/estudiantes/principal-view.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    "No se pudo regresar a la ventana principal."
            );

            e.printStackTrace();
        }
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