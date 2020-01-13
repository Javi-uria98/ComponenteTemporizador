package com.javier.ut5;

import com.sun.javafx.css.StyleManager;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Temporizador extends Label {

    private int segundos;

    private OnEndCountdown endCountdown;

    public Temporizador() {
        super();
    }

    public Temporizador(String s) {
        super(s);
    }

    public Temporizador(String s, Node node) {
        super(s, node);
    }

    public int getSegundos() {
        return segundos;
    }

    /*public IntegerProperty segundosProperty() {
        return segundos;
    }*/

    public void setSegundos(int segundos) {
        this.segundos = segundos;
        setText(Integer.toString(this.segundos));
    }

    public void addOnEndCountdown(OnEndCountdown endCountdown) {
        this.endCountdown=endCountdown;
    }

    public void iniciar() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (segundos > 0) {
                    segundos--;
                    StyleManager.getInstance().addUserAgentStylesheet(getClass().getResource("resources/verde.css").toExternalForm());
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            setText(Integer.toString(segundos));
                        }
                    });
                    if (segundos == 0) {
                        StyleManager.getInstance().addUserAgentStylesheet(getClass().getResource("resources/rojo.css").toExternalForm());
                        if (endCountdown!=null){
                            endCountdown.ejecuta();
                        }
                    }
                } else {
                    cancel();
                }
            }
        }, 0, 1000);

    }
}
