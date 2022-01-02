
/**
 * Write a description of class Agent here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;
public class Agent
{  
    // Important vectors 
    Vector2d position;
    Vector2d velocity;
    
    //Figure Configurations
    double r1 = 4;
    double r2 = 2;
    double xs[] = new double[3];
    double ys[] = new double[3];
    Color orange = new Color(255,69,0);
    
    Random r = new Random();
    
    // Friends information and constants
    ArrayList<Agent> friends = new ArrayList<Agent>();
    int counter = 0;
    double friendR = 14;
    public static double maxspeed = 1;
    
    public Agent(double x, double y)
    {
        velocity = new Vector2d((-1 + r.nextDouble()*2),(-1 + r.nextDouble()*2));    
        position = new Vector2d(x,y);
    }
    public void update(){
        //sense();
        decide();
        act();
        updateFigure();
        paint();
    }

    public void sense(){
      getListOfFriends();
        
    }
    public void decide(){
   
    }
    
    public void act(){
        Vector2d avVel = getAverageVelocity();
        avVel.multiply(Enviroment.alignmentW);
        if(Enviroment.alignment)
            velocity.add(avVel);
            
        Vector2d avPos = getAveragePosition();
        avPos.multiply(Enviroment.cohesionW);
         if(Enviroment.cohesion)
            velocity.add(avPos);
            
        Vector2d sep = getSeparation();
        Vector2d sepObs = getSeparationObs();
        sep.multiply(Enviroment.separationW);
        sepObs.multiply(Enviroment.separationW);
        velocity.add(sepObs);
        if(Enviroment.separation)
            velocity.add(sep);
        
        //System.out.println("Velocity "+velocity);
        velocity.limit(maxspeed);
        //System.out.println("Velocity limited "+velocity);
        //System.out.println("Position: "+ position);
        position.add(velocity);
        //System.out.println("New position "+position);
    }
    
    public void getListOfFriends(){
        ArrayList<Agent> agents = Enviroment.agents;
        ArrayList<Agent> close = new ArrayList<Agent>();
        
        for(int i = 0; i < agents.size(); i++){
            if(this != agents.get(i)){
                if(agents.get(i).position.getDistance(position) < friendR){
                    close.add(agents.get(i));
                }
            }
        }
        
        friends = close;
    }
    
    public Vector2d getAverageVelocity(){
        Vector2d averageVelocity = new Vector2d(0,0);

        for(Agent friend : friends){
            averageVelocity.add(friend.velocity);                
        }       
        if(friends.size() > 0){
            averageVelocity.divide(friends.size());    
        }
        averageVelocity.divide(8);
        return averageVelocity;
    }
    
    public Vector2d getAveragePosition(){
        Vector2d averagePosition = new Vector2d(0,0);
        
        for(Agent friend: friends){
            averagePosition.add(friend.position);
        }
        if(friends.size() > 0){
            averagePosition.divide(friends.size());
            averagePosition.substract(position);
            averagePosition.divide(100);
        }

        return averagePosition;
    }
    
    public Vector2d getSeparation(){
        Vector2d separation = new Vector2d(0,0);
        ArrayList<Obstacle> obs = Enviroment.obstacles;
        double distance;
        for(Agent friend : friends){
            Vector2d currentPos = new Vector2d(position.getX(),position.getY());
            currentPos.substract(friend.position);
            distance = currentPos.getSize();
            if(distance < 5){
                currentPos.normalize();
                currentPos.divide(distance);
                separation.add(currentPos);
            }
        }
        for(Obstacle obstacle : obs){
            Vector2d currentPos = new Vector2d(position.getX(),position.getY());
            currentPos.substract(obstacle.position);
            distance = currentPos.getSize();
            if(distance < 10){
                currentPos.normalize();
                currentPos.divide(distance);
                separation.add(currentPos);
            }
        }
        //separation.divide(1000000);
        return separation;
    }
    public Vector2d getSeparationObs(){
        Vector2d separation = new Vector2d(0,0);
        ArrayList<Obstacle> obs = Enviroment.obstacles;
        double distance;

        for(Obstacle obstacle : obs){
            Vector2d currentPos = new Vector2d(position.getX(),position.getY());
            currentPos.substract(obstacle.position);
            distance = currentPos.getSize();
            if(distance < 10){
                currentPos.normalize();
                currentPos.divide(distance);
                separation.add(currentPos);
            }
        }

        return separation;
    }
    
    public void updateFigure(){
        double x = position.getX();
        double y = position.getY();
        double angle = velocity.getRotationAngle();
       
        xs[0] = x + (r1 * Math.cos(Math.toRadians(angle)));      
        xs[1] = x + (r2 * Math.cos(Math.toRadians(angle+150)));
        xs[2] = x + (r2 * Math.cos(Math.toRadians(angle+150+60)));
        
        ys[0] = y+ (r1 * Math.sin(Math.toRadians(angle)));
        ys[1] = y+ (r2 * Math.sin(Math.toRadians(angle+150)));
        ys[2] = y+ (r2 * Math.sin(Math.toRadians(angle+150+60)));             
    }
    
    public void paint(){
        StdDraw.setPenColor(orange);
        //System.out.println("Position paint: " + position+"\n");
        //StdDraw.circle(position.getX(),position.getY(),friendR);
        StdDraw.filledPolygon(xs,ys);
    }

    @Override
    public String toString(){
        return ("Agent direction:" + velocity.toString()+"\n position: "+ position.toString() );
    }
}
