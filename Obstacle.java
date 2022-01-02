
/**
 * Write a description of class Obstacle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;
public class Obstacle
{
    public Vector2d position;

    public Obstacle(double x, double y)
    {   
        position = new Vector2d(x,y);

    }
    public void update(){
        StdDraw.setPenColor(new Color(55,160,75));
        StdDraw.filledCircle(position.getX(),position.getY(),4);
    }
    
}
