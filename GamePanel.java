
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;


//Le cœur du jeu (JPanel). 
// C'est ici que se trouvent la boucle de jeu, 
// le dessin des graphismes et la gestion des touches.


public class GamePanel extends JPanel implements ActionListener{
    private Board board;
    private Pacman pacman;
    private Ghost binkyy;
    private Timer timer;
    private int score=0;
    private boolean gameOver=false;

    public GamePanel(){
        board= new Board();
        pacman= new Pacman(1, 1);
        binkyy= new Ghost(8, 3, Color.RED);

        int panelWidth= board.getCol() * Board.TILE_SIZE;
        int panelHeight= (board.getRows() * Board.TILE_SIZE)+40;
        
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        // ajout de l'écouteur clavier
        this.addKeyListener(new GameInputHandler());

        // boucle de jeu
        timer = new Timer(16, this);
        timer.start();
    }
    @Override 
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        // c'est ici qu'on va dessiner le labyrinthe, pacman et les ghosts
        draw(g);
    }
    private void draw(Graphics g){
        // labyrinthe
        board.draw(g);
        // pacman et ghost
        pacman.draw(g);
        binkyy.draw(g);
        // dessin de score et fin
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("SCORE: "+ score ,15, board.getRows()* Board.TILE_SIZE +25);

        if (gameOver){
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("GAME OVER !",(board.getCol()* Board.TILE_SIZE)/4,(board.getRows()* Board.TILE_SIZE)/2);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(! gameOver){
            pacman.move(board);//mettre à jour les positions 
            binkyy.move(board);
        }
        
        // vérifier les collisions
        checkEaten();
        checkGhostcollisions();
        // redessiner l'écran
        repaint(); 
    }
    private void checkEaten(){
        if (pacman.getX()% Board.TILE_SIZE==0 && pacman.getY()% Board.TILE_SIZE==0){
            int i= pacman.getY() / Board.TILE_SIZE;
            int j= pacman.getX()/ Board.TILE_SIZE;

        if (board.getCell(i, j)==2){// oac gomme
            board.setCell(i,j,0);
            score+=10;
        }else if (board.getCell(i,j)==3){ // super pac gomme
            board.setCell(i, j, 0);
            score +=50;
        }
        }
    }
    private void checkGhostcollisions (){
        int distY=Math.abs(pacman.getY() - binkyy.getY());
        int distX=Math.abs(pacman.getX() - binkyy.getX());
        if(distX < Board.TILE_SIZE -10 && distY< Board.TILE_SIZE -10){
            gameOver=true;
            timer.stop();
        }
    }
    private class GameInputHandler extends KeyAdapter {
        @Override
        public void keyPressed (KeyEvent e){
            int key= e.getKeyCode();
            if (key== KeyEvent.VK_UP || key== KeyEvent.VK_Z){
                pacman.setDirection(0,-1 , board);
            }else if (key== KeyEvent.VK_DOWN || key== KeyEvent.VK_S){
                pacman.setDirection(0, 1, board);
            }else if(key==KeyEvent.VK_LEFT || key==KeyEvent.VK_Q){
                pacman.setDirection(-1, 0, board);
            }else if( key == KeyEvent.VK_RIGHT || key== KeyEvent.VK_D){
                pacman.setDirection(1, 0, board);
            }
        }
    }
}