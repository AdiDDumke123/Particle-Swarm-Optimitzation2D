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
        gc.fillRect(normalizeValue(x,-2,2,100,500),normalizeValue(y,-2,2,100,500),1,1);
    }

    public void doClearCanvas(){
        Platform.runLater(new clearCanvasTask(this));
    }

    public void clearCanvas(){
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        drawBananaFunction();
        gc.setFill(Color.YELLOW);
        gc.fillOval(normalizeValue(1,-3,3,100,500),normalizeValue(1,-3,3,100,500),10,10);

    }

    public void doDrawParticle(double x, double y){
        Platform.runLater(new drawParticleTask(this, x, y));
    }

    public void drawParticle(double x, double y){
        gc.setFill(Color.RED);
        gc.fillRect(normalizeValue(x,-2,2,100,500),normalizeValue(y,-2,2,100,500),3,3);
    }
    private void drawBananaFunction() {
        for(double i=-3;i<3;i=i+0.01){
            for(double u=-3;u<3;u=u+0.01){
                double result = banana.rosenbrock(u,i);
                if(result<3000&&result>500){
                    gc.setFill(Color.color(1,0.2,0.7,0.6));
                    gc.fillRect(normalizeValue(u,-2,2,100,500),normalizeValue(i,-2,2,100,500),1,1);
                }
                if(result<500&&result>200){
                    gc.setFill(Color.color(0.7,result*0.0005,result*0.0005,0.7));
                    gc.fillRect(normalizeValue(u,-2,2,100,500),normalizeValue(i,-2,2,100,500),1,1);
                }
                else if(result<200&result>50){
                    gc.setFill(Color.color(result*0.005,1,result*0.005,0.8));
                    gc.fillRect(normalizeValue(u,-2,2,100,500),normalizeValue(i,-2,2,100,500),1,1);

                }
                else if(result<50&result>10){
                    gc.setFill(Color.color(result*0.005,result*0.005,1,0.9));
                    gc.fillRect(normalizeValue(u,-2,2,100,500),normalizeValue(i,-2,2,100,500),1,1);
                }
                else if(result<10&result>0){
                    gc.setFill(Color.color(result*0.005,0.5,0.5,1));
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

