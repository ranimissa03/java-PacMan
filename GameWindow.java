//Gère la fenêtre principale (JFrame).
import javax.swing.JFrame;
public class GameWindow extends JFrame {
    public GameWindow(){
        this.setTitle("Pac-Man Java");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        // ajout du panneau du jeu
        GamePanel gamePanel= new GamePanel();
        this.add(gamePanel);
        this.pack();
        this.setLocationRelativeTo(null);//centre la fenêtre
        this.setVisible(true);

    }
    public static void main(String[]args){
        new GameWindow();
    }
}