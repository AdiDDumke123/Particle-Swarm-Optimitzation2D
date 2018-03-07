package PSO;

import java.util.Scanner;

public class Algorithm {

    int particles, epochs;

    public void menu () {
        Swarm swarm;
        swarm = new Swarm(particles, epochs);
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
