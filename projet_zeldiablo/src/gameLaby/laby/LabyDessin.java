package gameLaby.laby;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

import static javafx.scene.paint.Color.*;


public class LabyDessin implements DessinJeu {
    /**
     * méthode qui permet de creer l'interface graphique avec un Canvas
     * @param jeu va permettre de mettre à jour le jeu (la vue)
     * @param canvas Canvas qui dessine une interface graphique avec les fonctionnalites
     */
    @Override
    public void dessinerJeu(Jeu jeu, Canvas canvas) {
        LabyJeu labyJeu = (LabyJeu) jeu;
        Labyrinthe labyrinthe = labyJeu.getLaby();
        int height = (int) (canvas.getWidth() / labyrinthe.getLength());
        int width = (int) (canvas.getHeight() / labyrinthe.getLengthY());

        // recupere un pinceau pour dessiner
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        // dessin fond
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        if (!labyJeu.etreFini()) {

            // dessin murs
            dessinerMur(gc, labyrinthe, height, width);

            // dessin cases
            dessinerCases(gc, labyrinthe, height, width);

            // dessin monstres
            dessinerMonstres(gc, labyrinthe, height, width);

            // dessin joueur
            Perso perso = labyrinthe.pj;
            gc.setFill(RED);
            gc.fillOval(perso.getX() * width, perso.getY() * height, width, height);


        }
        else {
            double x = canvas.getWidth()/2;
            double y = canvas.getHeight()/2;
            gc.setFill(RED);
            gc.fillText("Partie Terminée", x, y);
        }
    }

    private void dessinerMonstres(GraphicsContext gc, Labyrinthe labyrinthe, int height, int width) {
        for (int j = 0; j < labyrinthe.monstre.size(); j++) {
            // dessin monstre
            Monstre monstre = labyrinthe.monstre.get(j);
            if (monstre != null) {
                gc.setFill(PURPLE);
                gc.fillOval(monstre.getX() * width, monstre.getY() * height, width, height);
            }
        }
    }

    private void dessinerCases(GraphicsContext gc, Labyrinthe labyrinthe, int height, int width) {
        CaseHeal soins = null;
        CasePiege piege = null;
        for (int i = 0; i < labyrinthe.caseEffets.size(); i++) {
            if (labyrinthe.caseEffets.get(i) instanceof CasePiege)
                piege = (CasePiege) labyrinthe.caseEffets.get(i);
            else soins = (CaseHeal) labyrinthe.caseEffets.get(i);
            if (piege != null) {
                if (piege.isActiver())
                    gc.setFill(CHOCOLATE);
                else
                    gc.setFill(LIGHTGRAY);
                gc.fillRect(piege.getX() * width, piege.getY() * height, width, height);

            }

            if (soins != null) {
                if (soins.isActiver())
                    gc.setFill(LAWNGREEN);
                else
                    gc.setFill(LIGHTGRAY);
                gc.fillRect(soins.getX() * width, soins.getY() * height, width, height);
            }
        }
    }

    private void dessinerMur(GraphicsContext gc, Labyrinthe labyrinthe, int height, int width) {

        for (int i = 0; i < labyrinthe.getLength(); i++) {
            for (int j = 0; j < labyrinthe.getLengthY(); j++) {
                if (labyrinthe.getMur(i, j)) {
                    gc.setFill(BLACK);
                    gc.fillRect(i * width, j * height, width, height);
                }
            }
        }
    }
}
