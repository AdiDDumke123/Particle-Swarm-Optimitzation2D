package Drawer;


import PSO.Particle;
import javafx.scene.paint.Color;
import java.awt.*;

public interface IDrawer {
    void drawParticle(Particle particle, Color color,int size);
    void drawField();
    void clearField(Particle[] startParticles);
}
