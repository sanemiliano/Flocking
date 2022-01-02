
/**
 * Write a description of class Enviroment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
public class Enviroment
{
    private static Enviroment instance = null;
    public static ArrayList<Agent> agents = new ArrayList<Agent>();
    public static ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
    public static boolean separation = true;
    public static boolean alignment = true;
    public static boolean cohesion = true;
    public static double separationW = 1;
    public static double alignmentW = 1;
    public static double cohesionW = 1;
    private Enviroment(int initialAgents,int initialObstacles)
    {
        // initialise instance variables
        double x,y;
        Random r = new Random();
        for(int i = 0; i < initialAgents; i++){
            x = -75 + r.nextDouble() * 150;
            y = -75 + r.nextDouble() * 150;
            agents.add(new Agent(x,y));
        }
        x = -98;
        double yst = 98;
        for(int i = 0; i < initialObstacles-1; i++){
            obstacles.add(new Obstacle(x,yst));
            obstacles.add(new Obstacle(x,-yst));
            obstacles.add(new Obstacle(yst,x));
            obstacles.add(new Obstacle(-yst,x));
            x += 4;
        }
    }
    
    public static Enviroment getInstance(int initialAgents,int initialObstacles){
        if(instance == null){
            instance = new Enviroment(initialAgents,initialObstacles);
        }
        return instance;
    }
    
    public static Enviroment getInstance(){
        return instance;
    }
    
    public void update(){
        updateAgents();
        //System.out.println("\n\n\n\n");
        updateObstacles();
        updateConstants();
    }
    
    public void updateAgents(){
        for(int i = 0; i < agents.size(); i++){
            agents.get(i).sense();
        }
        for(int i = 0; i < agents.size(); i++){
            agents.get(i).update();
        }
        
    }
    
    public void updateObstacles(){
        for(int i =0; i < obstacles.size(); i++){
            obstacles.get(i).update();
        }
    }
    public void updateConstants(){
        String sep = "SeparationStatus: "+separation + " SeparationWeight: "+round(separationW,2);
        String ali = " AlignmentStatus: "+alignment + " AligmentWeight: " + round(alignmentW,2);
        String co = " CohesionStatus: "+cohesion+" CohesionWeight: "+round(cohesionW,2);
        StdDraw.setPenColor(new Color(255,255,255));
        StdDraw.text(-25,97, sep + ali + co);
    }
    public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();
    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
}
}
