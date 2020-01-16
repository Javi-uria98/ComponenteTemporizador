package com.javier.ut5;

import com.sun.javafx.css.StyleManager;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Temporizador extends Label {

    private IntegerProperty segundos = new SimpleIntegerProperty(0);
    private ObjectProperty<Paint> colorEncendido= new SimpleObjectProperty<>(Color.GREEN);
    private ObjectProperty<Paint> colorFin= new SimpleObjectProperty<>(Color.RED);

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
        return segundos.get();
    }

    public IntegerProperty segundosProperty() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos.set(segundos);
        setText(Integer.toString(segundos));
    }

    public Paint getColorEncendido() {
        return colorEncendido.get();
    }

    public ObjectProperty<Paint> colorEncendidoProperty() {
        return colorEncendido;
    }

    public void setColorEncendido(Paint colorEncendido) {
        this.colorEncendido.set(colorEncendido);
    }

    public Paint getColorFin() {
        return colorFin.get();
    }

    public ObjectProperty<Paint> colorFinProperty() {
        return colorFin;
    }

    public void setColorFin(Paint colorFin) {
        this.colorFin.set(colorFin);
    }

    private String colorToString(Paint color)
    {
        return color.toString().substring(2);
    }

    public void addOnEndCountdown(OnEndCountdown endCountdown) {
        this.endCountdown=endCountdown;
    }

    public void iniciar() {
        setStyle("-fx-text-fill:#"+colorToString(colorEncendido.get()));
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (segundos.get() > 0) {
                    segundos.set(segundos.get()-1);

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            setText(Integer.toString(segundos.get()));
                            if (segundos.get() == 0) {
                                setStyle("-fx-text-fill:#"+colorToString(colorFin.get()));
                                if (endCountdown!=null){
                                    endCountdown.ejecuta();
                                }
                            }
                        }
                    });

                } else {
                    timer.cancel();
                    timer.purge();
                }
            }
        }, 1000, 1000);

    }
}
