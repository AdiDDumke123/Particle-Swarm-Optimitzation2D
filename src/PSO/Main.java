package PSO;

import java.util.Scanner;

public class Main {

    public static void main (String[] args) {
        menu(true);
    }

    private static void menu (boolean flag) {
        Swarm swarm;
        int particles, epochs;
        double inertia, cognitive, social;

        particles = getUserInt("Particles: ");
        epochs = getUserInt("Epochs:    ");

        if (flag) {
            inertia = getUserDouble("Inertia:   ");
            cognitive = getUserDouble("Cognitive: ");
            social = getUserDouble("Social:    ");
            swarm = new Swarm(particles, epochs, inertia, cognitive, social);
        } else {
            swarm = new Swarm(particles, epochs);

        }

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

    private static double getUserDouble (String msg) {
        double input;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print(msg);

            if (sc.hasNextDouble()) {
                input = sc.nextDouble();

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
