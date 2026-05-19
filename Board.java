import java.awt.Color;
import java.awt.Graphics;

//( une matrice) : Représente la carte du jeu (murs, pac-gommes, super pac-gommes).

public class Board {
    public static final int TILE_SIZE= 40;
    //chaque case= 40*40 pixels
    // la matrice:
    private final int [][]maze={
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},// 1:MUR
        {1, 2, 2, 2, 2, 1, 2, 2, 2, 1},//PAC_GOMME
        {1, 3, 1, 1, 2, 1, 2, 1, 2, 1},//SUPERPAC_GOMME
        {1, 2, 2, 2, 2, 2, 2, 2, 3, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    }; 
    public int getRows(){ 
        return maze.length;
    }
    public int getCol(){
        return maze[0].length;
    }
    public int getCell(int r, int c){
        if (r>= 0 && r< getRows() && c>= 0 && c< getCol())
            return maze[r][c];
        return 1;
    }
    public void  setCell (int r, int c, int value ){
        if (r>= 0 && r< getRows() && c>= 0 && c< getCol())
            maze[r][c]= value;
    }
    // verifier qu'une case n'est pas un mur
    public boolean isSafe(int row, int col){
        return getCell(row, col) != 1;
    }
    public void draw (Graphics g){
        for (int i=0; i< getRows(); i++){
            for(int j=0; j< getCol(); j++){
                int cell= getCell(i,j);
                int x= j* TILE_SIZE;
                int y= i* TILE_SIZE;

                if (cell==1){// si mur 
                    g.setColor(Color.BLUE);
                    g.fillRect(x,y,TILE_SIZE, TILE_SIZE);
                }else if (cell==2){//si pac_gomme
                    g.setColor(Color.WHITE);
                    g.fillOval(x+18,y+18,5,5);
                }else if (cell==3){// si super pac_gomme
                    g.setColor(Color.PINK);
                    g.fillOval(x+14, y+14, 12,12);
                }

            }
        }
    }
}
