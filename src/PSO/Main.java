package PSO;

import java.util.Scanner;

public class Main {

    public static void main (String[] args) {
        menu();
    }

    private static void menu () {
        Swarm swarm;
        int particles, epochs;

        particles = getUserInt("Particles: ");
        epochs = getUserInt("Epochs:    ");

        swarm = new Swarm(particles, epochs);
        swarm.run();
    }

    private static int getUserInt (String msg) {
        int input;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print(msg);

            if (sc.hasNextInt()) {
                input = sc.nextInt();

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
    }
}
