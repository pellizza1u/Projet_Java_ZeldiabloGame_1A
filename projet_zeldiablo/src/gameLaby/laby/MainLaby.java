package gameLaby.laby;
import moteurJeu.MoteurJeu;

import java.io.IOException;

public class MainLaby {
    /**
     * Main qui crée des dimensions, les FPS et créent les objets pour les fonctiuonnalités
     */
    public static void main(String[] args) throws IOException {
        int width = 800;
        int height = 600;
        int pFPS = 15;

        // creation des objets
        LabyJeu JL = new LabyJeu("labySimple/LabyDemo.txt");
        LabyDessin LD = new LabyDessin();

        // parametrage du moteur de jeu
        MoteurJeu.setTaille(width,height);
        MoteurJeu.setFPS(pFPS);

        // lancement du jeu
        MoteurJeu.launch(JL, LD);
    }

}
