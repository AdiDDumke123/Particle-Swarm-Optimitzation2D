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
        gc.setFill(Color.YELLOW);
        gc.fillOval(scaleValue(1)-2,scaleValue(1)-2,10,10);
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
        for(double i=-2;i<2;i=i+0.01){
            for(double u=-2;u<2;u=u+0.01){
                double result = banana.rosenbrock(u,i);
                if(result<500&&result>200){
                    gc.setFill(Color.RED);
                    gc.fillRect(normalizeValue(u,-2,2,100,500),normalizeValue(i,-2,2,100,500),1,1);
                }
                else if(result<200&result>100){
                    gc.setFill(Color.YELLOW);
                    gc.fillRect(normalizeValue(u,-2,2,100,500),normalizeValue(i,-2,2,100,500),1,1);

                }
                else if(result<100&result>0){
                    gc.setFill(Color.BLUE);
                    gc.fillRect(normalizeValue(u,-2,2,100,500),normalizeValue(i,-2,2,100,500),1,1);
                }

            }
        }

    }

   private static double normalizeValue(double value, double min, double max, double newMin, double newMax) {

       return (value - min) * (newMax - newMin) / (max - min) + newMin;

   }

    private double scaleValue(double value){
        //Eingabe zwischen -100 und 100
        //Ausgabe zwischen 100 und 500
        //Skalierfunktion y=100*value+300
       // double a = (Configuration.instance.gradient*value)+Configuration.instance.intersection;
        return (Configuration.instance.gradient*value)+Configuration.instance.intersection;
    }
}

