package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Particle Swarm Optimization 2D");
        primaryStage.setScene(new Scene(root, 700, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Controller controller = new Controller();
        launch(args);
        /*
        // 800 x 800 px
        double[][] function = new double[800][800];
        double smallest = 14.0;
        double biggest = 0.0;
        for(int x = 0;x < 800; x++){
            for(int y = 0;y < 800; y++){
                function[x][y] = PSO.Function.rosenbrock(x,y);
                if(function[x][y] > biggest) biggest = function[x][y];
                if(function[x][y] < smallest) smallest = function[x][y];
            }
        }
        for(int x = 0;x < 800; x++) {
            for (int y = 0; y < 800; y++) {
                Color temp = ColorHelper.getColor(function[x][y], smallest, biggest);
                controller.drawGraph(x,y,temp);
            }
        }
        System.out.println("Ende");
        */
    }
}
