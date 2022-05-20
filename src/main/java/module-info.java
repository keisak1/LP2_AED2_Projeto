module com.projeto {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires algs4.master.fc511547db;

    opens com.projeto to javafx.fxml;
    exports com.projeto;
}