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

import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Function;

public class Controller implements Initializable {
    @FXML
    Canvas canvas;
    private GraphicsContext gc;
    private PSO.Function banana = new PSO.Function();

    private Algorithm algorithm;

    @FXML
    private Slider swarmslider;

    @FXML
    private Slider epochslider;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         gc = canvas.getGraphicsContext2D();
         algorithm = new Algorithm(this);
    }
    @FXML
    public void draw() {
        //gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
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
        gc.fillRect(scaleValue(x),scaleValue(y),1,1);
    }

    public void doClearCanvas(){
        Platform.runLater(new clearCanvasTask(this));
    }

    public void clearCanvas(){
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setFill(Color.BLUE);
        gc.fillOval(scaleValue(1)-7,scaleValue(1)-7,15,15);
        drawBananaFunction();
    }

    public void doDrawParticle(double x, double y){
        Platform.runLater(new drawParticleTask(this, x, y));
    }

    public void drawParticle(double x, double y){
        gc.setFill(Color.RED);
        gc.fillRect(scaleValue(x),scaleValue(y),3,3);
    }
    private void drawBananaFunction() {
            gc.setFill(Color.BLUE);
        for(int i=-100;i<100;i++){
            for(int u=-100;u<100;u++){
                double result = banana.rosenbrock(u,i);
                if(result<5000&&result>500){
                    gc.setFill(Color.RED);
                    gc.fillRect(scaleBanana(u), scaleBanana(i), 1, 1);
                }
                else if(result<500&result>100){
                    gc.setFill(Color.YELLOW);
                    gc.fillRect(scaleBanana(u), scaleBanana(i), 1, 1);
                }
                else if(result<100&result>0){
                    gc.setFill(Color.BLUE);
                    gc.fillRect(scaleBanana(u), scaleBanana(i), 1, 1);
                }

            }
        }

    }
    private double scaleBanana(int value){
        return (Configuration.instance.bananagradient*value)+Configuration.instance.intersection;
    }
    private double scaleColor(double value){
        return value*0.0005;
    }

    private double scaleValue(double value){
        //Eingabe zwischen -2 und 2
        //Ausgabe zwischen 100 und 500
        //Skalierfunktion y=100*value+300
       // double a = (Configuration.instance.gradient*value)+Configuration.instance.intersection;
        return (Configuration.instance.gradient*value)+Configuration.instance.intersection;
    }
}

