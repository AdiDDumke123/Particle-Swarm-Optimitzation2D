package sample;

/**
 * is called to change gameover to true in the UI-/main-thread and refresh the canvas as soon as there is a
 * brute force solution.
 */
public class clearCanvasTask implements Runnable {

    private Controller controller;

    public clearCanvasTask(Controller controller){
        this.controller = controller;
    }

    @Override
    public void run(){
        controller.clearCanvas();
    }
}
