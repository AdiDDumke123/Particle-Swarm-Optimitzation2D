package PSO;

import sample.Controller;

public class Algorithm implements Runnable{

    private Controller controller;

    public Algorithm(Controller controller){
        this.controller = controller;

    }

    private int particles, epochs;

    @Override
    public void run (){
        Swarm swarm;
        swarm = new Swarm(controller, particles, epochs);
        swarm.run();
    }

    public void setParticles(int particles){
        this.particles = particles;
    }
    public void setEpochs(int epochs){
        this.epochs = epochs;
    }
}
