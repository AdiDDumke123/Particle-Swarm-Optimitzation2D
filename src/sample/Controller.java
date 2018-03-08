package sample;

import PSO.Algorithm;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    Canvas canvas;
    private GraphicsContext gc;

    private Algorithm algorithm;

    @FXML
    private Slider swarmslider;

    @FXML
    private Slider epochslider;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(0.5));
         gc = canvas.getGraphicsContext2D();
         algorithm = new Algorithm(this);
    }
    @FXML
    public void draw() {
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        Thread thread = new Thread(algorithm);
        thread.setDaemon(true);
        thread.start();
    }
    @FXML
    public void setSwarmsize() {
        algorithm.setParticles((int)swarmslider.getValue());
        System.out.println(swarmslider.getValue());
    }
    @FXML
    public void setEpochs() {
        algorithm.setEpochs((int)epochslider.getValue());
        System.out.println(epochslider.getValue());
    }

    public void doDrawStartParticles(double x, double y){
        Platform.runLater(new drawStartParticlesTask(this, x, y));
    }

    public void drawStartParticles(double x, double y){
        gc.setFill(Color.BLACK);
        gc.fillRect(scaleValue(x),scaleValue(y),5,5);
    }

    public void doClearCanvas(){
        Platform.runLater(new clearCanvasTask(this));
    }

    public void clearCanvas(){
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setFill(Color.BLUE);
        gc.fillOval(scaleValue(1)-7,scaleValue(1)-7,15,15);
    }

    public void doDrawParticle(double x, double y){
        Platform.runLater(new drawParticleTask(this, x, y));
    }

    public void drawParticle(double x, double y){
        gc.setFill(Color.RED);
        gc.fillRect(scaleValue(x),scaleValue(y),3,3);
    }

    private double scaleValue(double value){
        //Eingabe zwischen -100 und 101
        //Ausgabe zwischen 100 und 500
        //Skalierfunktion y=1,990049*value+300,0049
        return (Configuration.instance.gradient*value)+Configuration.instance.intersection;
    }
}

