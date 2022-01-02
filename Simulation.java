/**
 * The simulation takes place here.
 * 
 * @author Emiliano 
 * @version 18/06/2019
 */
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class Simulation
{
  int xSize = 1000;
  int ySize = 700;
  Color blue = new Color(0,160,245);
  Color orange = new Color(255,69,0);
  boolean separation = true;
  boolean alignment = true;
  boolean cohesion = true;
  int consta = 1;
  int initialAgents = 30;
  int initialObstacles = 50;
  String selected = "separation";
  Enviroment env = Enviroment.getInstance(initialAgents,initialObstacles);
  
  public Simulation()
  {
      
  }

  public void simulate()
  {  
    StdDraw.setCanvasSize(xSize,ySize);
    StdDraw.setXscale(-100,100);
    StdDraw.setYscale(-100,100);
    StdDraw.enableDoubleBuffering();
    Font font2 = new Font("Arial", Font.BOLD, 11);
    StdDraw.setFont(font2);
    StdDraw.setPenColor(blue);
    StdDraw.filledSquare(0,0,200);
    
    while(true){
        runSimulation();
    }
  }
  
  public void runSimulation(){
      
      StdDraw.clear();
      StdDraw.setPenColor(blue);
      StdDraw.filledSquare(0,0,200);
      env.update();
      StdDraw.setPenColor(new Color(255,255,255));
      StdDraw.text(70,97,"CurrentlySelected: "+selected);
      StdDraw.show();
      StdDraw.pause(50);
      updateKeystrokes();
      
  }
  
  public void updateKeystrokes(){
      if(StdDraw.hasNextKeyTyped()){
          char typed = StdDraw.nextKeyTyped();
          switch(typed){
              case '1':
                separation = !separation;
                //System.out.println("Separation: "+separation);
                break;
              case '2':
                alignment = !alignment;
                //System.out.println("Alignment: "+alignment);
                break;
              case '3':
                cohesion = !cohesion;
                //System.out.println("Cohesion: "+cohesion);
                break;
              case '4':
                env.agents.add(new Agent(StdDraw.mouseX(),StdDraw.mouseY()));
                break;
              case '5':
                env.obstacles.add(new Obstacle(StdDraw.mouseX(),StdDraw.mouseY()));
                break;
              case 'q':
                consta = 1;
                selected = "separation";
                break;
              case 'w':
                consta = 2;
                selected = "alignment";
                break;
              case 'e':
                consta = 3;
                selected = "cohesion";
                break;
              case 'r':
                consta = 4;
                selected = "MaxSpeed";
                break;
              case 't':
                changeConst(-.1);
                break;
              case 'y':
                changeConst(.1);
                break;
                
          } 
          
      }
      
      env.separation = separation;
      env.alignment = alignment;
      env.cohesion = cohesion;
  }
   public void changeConst(double delta){
       switch(consta){
           case 1:
            env.separationW += delta;
            break;
           case 2:
           env.alignmentW += delta;
            break;
           case 3:
           env.cohesionW += delta;
            break;
           case 4:
           Agent.maxspeed += delta;
        }
    }

}
