module ni.edu.uam.primercorte7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens ni.edu.uam.primercorte7 to javafx.fxml;
    opens ni.edu.uam.primercorte7.controller to java.fxml;
    exports ni.edu.uam.primercorte7;
}