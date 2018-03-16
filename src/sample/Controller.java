package sample;

import PSO.Algorithm;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import java.net.URL;


import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    Canvas canvas;
    private GraphicsContext gc;
    private PSO.Function banana = new PSO.Function();

    private Algorithm algorithm;
    private Thread thread;

    @FXML
    private Slider swarmslider;

    @FXML
    private Slider epochslider;

    @FXML
    private Button button;

    public void setButtonDisabled(boolean value){
        button.setDisable(value);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         gc = canvas.getGraphicsContext2D();
         algorithm = new Algorithm(this);


    }
    @FXML
    public void draw() {
        thread = new Thread(algorithm);
        button.setDisable(true);
        thread.setDaemon(true);
        thread.start();

    }
    @FXML
    public void setSwarmsize() {
        epochslider.setValue(0);
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
        gc.fillRect(normalizeValue(x,Configuration.instance.minimum,Configuration.instance.maximium
                ,Configuration.instance.drawminimum,Configuration.instance.drawMaximum)
                ,normalizeValue(y,Configuration.instance.minimum,Configuration.instance.maximium,
                        Configuration.instance.drawminimum,Configuration.instance.drawMaximum),
                Configuration.instance.sizeOfStartParticle,Configuration.instance.sizeOfStartParticle);
    }

    public void doClearCanvas(){
        Platform.runLater(new clearCanvasTask(this));
    }

    public void clearCanvas(){
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        drawBananaFunction();
        gc.setFill(Color.YELLOW);
        gc.fillOval(normalizeValue(Configuration.instance.low-0.09,Configuration.instance.minimum, Configuration.instance.maximium
                ,Configuration.instance.drawminimum,Configuration.instance.drawMaximum)
                ,normalizeValue(Configuration.instance.low-0.09,Configuration.instance.minimum,Configuration.instance.maximium
                        ,Configuration.instance.drawminimum,Configuration.instance.drawMaximum)
                ,Configuration.instance.sizeOfOval,Configuration.instance.sizeOfOval);

    }

    public void doDrawParticle(double x, double y){
        Platform.runLater(new drawParticleTask(this, x, y));
    }

    public void drawParticle(double x, double y){
        gc.setFill(Color.RED);
        gc.fillRect(normalizeValue(x,Configuration.instance.minimum,Configuration.instance.maximium,Configuration.instance.drawminimum,Configuration.instance.drawMaximum)
                ,normalizeValue(y,Configuration.instance.minimum,Configuration.instance.maximium
                        ,Configuration.instance.drawminimum,Configuration.instance.drawMaximum),Configuration.instance.sizeOfParticle,Configuration.instance.sizeOfParticle);
    }
    private void drawBananaFunction() {
        for(double i=Configuration.instance.minimum;i<Configuration.instance.maximium;i=i+Configuration.instance.resolution){
            for(double u=Configuration.instance.minimum;u<Configuration.instance.maximium;u=u+Configuration.instance.resolution){
                double result = banana.rosenbrock(u,i);
                if(result<4000&&result>800){
                    gc.setFill(Color.color(0,0,0,result*0.00025));
                }
                else if(result<800&&result>200){
                    gc.setFill(Color.color(1,0.25,0,result*0.00125));
                }
                else if(result<200&&result>100){
                   gc.setFill(Color.color(0,1,0.25,result*0.005));
                }
                else if(result<100&&result>=0){
                    gc.setFill(Color.color(0,0.1,0.5,result*0.01));
                }
                gc.fillRect(normalizeValue(u,Configuration.instance.minimum,Configuration.instance.maximium,Configuration.instance.drawminimum,Configuration.instance.drawMaximum)
                        ,normalizeValue(i,Configuration.instance.minimum,Configuration.instance.maximium,Configuration.instance.drawminimum,Configuration.instance.drawMaximum),1,1);

            }
        }

    }

   private static double normalizeValue(double value, double min, double max, double newMin, double newMax) {

       return (value - min) * (newMax - newMin) / (max - min) + newMin;

   }
}

