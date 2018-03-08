package sample;

/**
 * is called to change gameover to true in the UI-/main-thread and refresh the canvas as soon as there is a
 * brute force solution.
 */
public class drawStartParticlesTask implements Runnable {

    private Controller controller;
    private double x, y;

    public drawStartParticlesTask(Controller controller, double x, double y){
        this.x = x;
        this.y = y;
        this.controller = controller;
    }

    @Override
    public void run(){
        controller.drawStartParticles(x,y);
    }
}
