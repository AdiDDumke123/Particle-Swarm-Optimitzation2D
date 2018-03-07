package sample;

import PSO.Algorithm;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    Algorithm algorithm;
    @FXML
    private Slider swarmslider;

    @FXML
    private Slider epochslider;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

         algorithm = new Algorithm();
    }
    @FXML
    public void draw() {
        algorithm.menu();
    }
    @FXML
    public void setSwarmsize() {
        algorithm.setParticles((int)swarmslider.getValue());
        System.out.println(swarmslider.getValue());
    }
    @FXML
    public void setEpochs() {
        algorithm.setEpochs((int)epochslider.getValue());
        System.out.println(epochslider.getValue());
    }

}

