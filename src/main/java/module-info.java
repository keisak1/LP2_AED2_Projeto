module com.example.projeto_lp2_aed2_javafx {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires algs4.master.fc511547db;

    opens com.projeto to javafx.fxml;
    exports com.projeto;
}