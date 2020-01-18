package com.javier.test;

import com.javier.ut5.OnEndCountdown;
import com.javier.ut5.Temporizador;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PantallaPrueba extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox=new VBox();
        Temporizador temporizador=new Temporizador();
        temporizador.setFont(new Font (42));
        temporizador.setSegundos(10);
        temporizador.setTextoFinal("ACABó");
        temporizador.setColorEncendido(Color.YELLOW);
        temporizador.setColorFin(Color.BLUE);

        temporizador.addOnEndCountdown(new OnEndCountdown() {
            @Override
            public void ejecuta() {
                System.out.println("Fin cuenta atrás");
            }
        });

        vBox.getChildren().add(temporizador);
        Scene scene=new Scene(vBox);
        stage.setScene(scene);
        stage.show();
        temporizador.iniciar();
    }

    public static void main (String[] args){
        launch(args);
    }
}
