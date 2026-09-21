module ni.edu.uam.primercorteg7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.primercorteg7 to javafx.fxml;
    exports ni.edu.uam.primercorteg7;
}