package PSO;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.Scanner;

public class Algorithm {
    @FXML
    Canvas canvas;
    GraphicsContext gc;

    public Algorithm(Canvas canvas, GraphicsContext gc){
        this.canvas = canvas;
        this.gc = gc;
    }

    int particles, epochs;

    public void menu () {
        Swarm swarm;
        swarm = new Swarm(particles, epochs,canvas,gc);
        swarm.run();
    }

    public void setParticles(int particles){
        this.particles = particles;
    }
    public void setEpochs(int epochs){
        this.epochs = epochs;
    }

    /*private int getUserInt () {
        int input;
        while (true) {
            Scanner sc = new Scanner(System.in);

            if (sc.hasNextInt()) {
                input = sc.nextInt();
                System.out.print(input);

                if (input <= 0) {
                    System.out.println("Number must be positive.");
                } else {
                    break;
                }

            } else {
                System.out.println("Invalid input.");
            }
        }
        return input;
    }*/
}
