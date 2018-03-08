package Drawer;


import PSO.Particle;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Drawer implements IDrawer{
    @FXML
    Canvas canvas;
    GraphicsContext gc;

    public Drawer(Canvas canvas, GraphicsContext gc){
        this.canvas = canvas;
        this.gc = gc;
    }
    public void updateGraphicsContext(){
        //canvas = canvas.getGraphicsContext2D().beginPath();


    }
    @Override
    public void drawParticle(Particle particle, Color color,int size) {
        gc.setFill(color);
        double x = particle.getPosition().getX();
        double y = particle.getPosition().getY();
        gc.fillRect(scaleValue(x),scaleValue(y),size,size);
    }
    public void clearField(Particle[] startParticles){
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        for(Particle particle : startParticles){
            drawParticle(particle,Color.BLACK,5);
        }
    }

    private double scaleValue(double value){
        //Eingabe zwischen -100 und 101
        //Ausgabe zwischen 100 und 500
        //Skalierfunktion y=1,990049*value+300,0049
        return (Configuration.instance.gradient*value)+Configuration.instance.intersection;
    }

    @Override
    public void drawField() {

    }
}
