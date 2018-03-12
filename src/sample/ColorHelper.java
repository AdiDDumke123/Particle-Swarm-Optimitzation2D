package sample;

import javafx.scene.paint.Color;

/**
 * Color gradient map from blue to red in 1200 steps.
 * Returns a Color for a double value.
 *
 */
public class ColorHelper {

    public static Color getColor(double value, double min, double max) {
        double gray = normalizeValue(value, min, max, 0., 1.);

        gray = clamp(gray, 0, 1);

        return Color.RED.interpolate(Color.YELLOW, gray);
    }

    public static double normalizeValue(double value, double min, double max, double newMin, double newMax) {

        return (value - min) * (newMax - newMin) / (max - min) + newMin;

    }

    private static double clamp(double value, double min, double max) {

        if (Double.compare(value, min) < 0)
            return min;

        if (Double.compare(value, max) > 0)
            return max;

        return value;
    }
}