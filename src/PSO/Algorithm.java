package PSO;

import sample.Controller;
import javafx.scene.control.Button;
import java.awt.*;

public class Algorithm implements Runnable{

    private Controller controller;
    private Button button;

    public Algorithm(Controller controller){
        this.controller = controller;

    }

    private int particles, epochs;

    @Override
    public void run (){
        Swarm swarm;
        swarm = new Swarm(controller, particles, epochs);
        swarm.run();
        controller.setButtonDisabled(false);

    }

    public void setParticles(int particles){
        this.particles = particles;
    }
    public void setEpochs(int epochs){
        this.epochs = epochs;
    }
}
