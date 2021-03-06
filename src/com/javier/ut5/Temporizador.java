package com.javier.ut5;

import com.sun.javafx.css.StyleManager;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Temporizador extends Label {

    private IntegerProperty segundos = new SimpleIntegerProperty(0);
    private StringProperty  textoFinal = new SimpleStringProperty("");
    private ObjectProperty<Paint> colorEncendido= new SimpleObjectProperty<>(Color.GREEN);
    private ObjectProperty<Paint> colorFin= new SimpleObjectProperty<>(Color.RED);
    private ArrayList<OnEndCountdown> endCountdown;

    public Temporizador() {
        endCountdown=new ArrayList<OnEndCountdown>();
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
        //etText(Integer.toString(segundos));
    }

    public String getTextoFinal() {
        return textoFinal.get();
    }

    public StringProperty textoFinalProperty() {
        return textoFinal;
    }

    public void setTextoFinal(String textoFinal) {
        this.textoFinal.set(textoFinal);
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
        this.endCountdown.add(endCountdown);
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
                                setText(textoFinal.get());
                                setStyle("-fx-text-fill:#"+colorToString(colorFin.get()));
                                for (OnEndCountdown o: endCountdown){
                                    o.ejecuta();
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
