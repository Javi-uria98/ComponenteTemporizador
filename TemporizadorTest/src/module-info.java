module TemporizadorTest {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires ComponenteTemporizador;
    exports com.javier.test;
    exports com.javier.test.fxml;
    opens com.javier.test.fxml;
}