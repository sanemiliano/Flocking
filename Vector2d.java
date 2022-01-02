
/**
 * Write a description of class Vector here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vector2d
{

    double x,y;
    public Vector2d(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public void add(Vector2d vec){
        x += vec.getX();
        y += vec.getY();
    }
    
    public void substract(Vector2d vec){
        x -= vec.getX();
        y -= vec.getY();
    }
    
    public void multiply(double scalar){
        x *= scalar;
        y *= scalar;
    }
    
    public void divide(double scalar){
        x /= scalar;
        y /= scalar;
    }
    
    public double getSize(){
        return Math.sqrt((x*x)+(y*y));
    }
    
    public void normalize(){
        double size =  getSize();
        divide(size);
    }
    
    public double getDistance(Vector2d aux){
        double xDif = (x-aux.getX());
        double yDif = (y-aux.getY());
        return Math.sqrt((xDif*xDif)+(yDif*yDif));
    }
    
    public double getRotationAngle(){
        Vector2d aux = new Vector2d(x,y);
        aux.normalize();
        double degrees = Math.toDegrees(Math.acos(aux.getX()));;
        if(aux.getY() < 0)
            degrees = 360 - degrees;
        return degrees;
    }
    
    public void limit(double limit){
        double magnitude = getSize();
        if(magnitude > limit){
            normalize();
            multiply(limit);
        }
    }
    
    @Override
    public String toString(){
        return ("Vector: x: "+x+" y: "+y + " size: "+getSize());
    }
}
