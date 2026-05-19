import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


//Gère les fantômes (positions, IA de base, états : normal/apeuré).

public class Ghost {
    private int x, y;
    private int dx = 1, dy = 0; // Direction initiale (droite)
    private final int speed = 2;
    private final Color color;

    public Ghost(int gridX, int gridY, Color color) {
        this.x = gridX * Board.TILE_SIZE;
        this.y = gridY * Board.TILE_SIZE;
        this.color = color;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void move(Board board) {
        // L'IA ne calcule un chemin que lorsqu'elle est centrée sur une case de la grille
        if (x % Board.TILE_SIZE == 0 && y % Board.TILE_SIZE == 0) {
            int currentRow = y / Board.TILE_SIZE;
            int currentCol = x / Board.TILE_SIZE;

            // Définition des 4 directions cardinales {dx, dy}
            int[][] possibilities = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
            List<int[]> validMoves = new ArrayList<>();

            for (int[] p : possibilities) {
                // Évite de faire un demi-tour immédiat s'il y a d'autres chemins (rend le mouvement fluide)
                if (p[0] == -dx && p[1] == -dy) {
                    continue;
                }
                if (board.isSafe(currentRow + p[1], currentCol + p[0])) {
                    validMoves.add(p);
                }
            }

            // Si bloqué ou impasse totale, faire un demi-tour forcé
            if (validMoves.isEmpty()) {
                dx = -dx;
                dy = -dy;
            } else {
                // Choix aléatoire d'une direction valide
                int randomIndex = (int) (Math.random() * validMoves.size());
                dx = validMoves.get(randomIndex)[0];
                dy = validMoves.get(randomIndex)[1];
            }
        }

        x += dx * speed;
        y += dy * speed;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, Board.TILE_SIZE, Board.TILE_SIZE);
    }
}

