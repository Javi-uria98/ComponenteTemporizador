package com.javier.test.fxml;

import com.javier.ut5.Temporizador;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    private Temporizador temporizador;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        temporizador.setFont(new Font(42));
        temporizador.iniciar();
    }


}
