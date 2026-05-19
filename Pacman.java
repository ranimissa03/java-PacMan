import java.awt.Color;
import java.awt.Graphics;


//Gère la position, la direction, la vitesse et le score du joueur.


public class Pacman {
    private int x, y;//position
    private int dx=0, dy=0; // direction actuelle
    private final int speed =2; // vitesse
    
    public Pacman (int gridX, int gridY){
        this.y=gridY * Board.TILE_SIZE;
        this.x= gridX * Board.TILE_SIZE;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void setDirection(int newDx, int newDy, Board board){
        if (x % Board.TILE_SIZE==0 && y % Board.TILE_SIZE==0){
            int targetRow = (y / Board.TILE_SIZE)+ newDy;
            int targetCol=(x / Board.TILE_SIZE)+ newDx;
            if (board.isSafe(targetRow, targetCol)){
                this.dx=newDx;
                this.dy=newDy;
            }
        }
    }

    public void move (Board board){
        if( x % Board.TILE_SIZE==0 && y % Board.TILE_SIZE==0){
            int nextRow=( y/Board.TILE_SIZE)+dy;
            int nextCol=( x/ Board.TILE_SIZE)+ dx;

            if (! board.isSafe(nextRow,nextCol)){
                dx=0;
                dy=0;
            }
        }
        x=x+(dx* speed);
        y+=dy* speed;
    }
    public void draw (Graphics g){
        g.setColor(Color.YELLOW);
        g.fillArc(x,y, Board.TILE_SIZE,Board.TILE_SIZE, 45,270);
    }
}
