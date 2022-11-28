package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;
import moteurJeu.MoteurJeu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static gameLaby.laby.Labyrinthe.*;

public class LabyJeu implements Jeu {

    private boolean finiPerso;
    private boolean finiMonstre;
    /**
     * labyrinthe
     */
    private Labyrinthe labyrinthe;
    /**
     * liste des actions du monstre
     */
    private List<String> actionMonstre;
    /**
     * random
     */
    private Random rand;

    /**
     * Constructeur de la classe LabyJeu
     * @param nomdufichier Creation du laby à partir d'un fhichier
     * @throw IOException fichier introuvable ou pas le bon fichier
     */
    public LabyJeu(String nomdufichier) throws IOException {
        this.labyrinthe=new Labyrinthe(nomdufichier);
        initActionMonstre();
    }
    /**
     * Constructeur vide de LabyJeu
     *  * @throw IOException fichier introuvable ou pas le bon fichier
     */

    public LabyJeu() throws IOException {
        this.labyrinthe=new Labyrinthe("labySimple/laby1.txt");
        initActionMonstre();
    }
    /**
     * méthode qui peut à jour le déplacement du personnage et monstre dans le labyrinthe
     * @param secondes
     * @param clavier
     */
    @Override
    public void update(double secondes, Clavier clavier) {
        finiPerso = labyrinthe.pj.getPv() == 0;
        finiMonstre = labyrinthe.monstre.size() == 0;
        // deplacement personnage
        if (clavier.haut)
            this.labyrinthe.deplacerPerso(HAUT, labyrinthe.pj);
        else if (clavier.bas)
            this.labyrinthe.deplacerPerso(BAS, labyrinthe.pj);
        if (clavier.droite)
            this.labyrinthe.deplacerPerso(DROITE, labyrinthe.pj);
        else if (clavier.gauche)
            this.labyrinthe.deplacerPerso(GAUCHE, labyrinthe.pj);
        //attaque du perso
        if (clavier.att) {
            this.labyrinthe.gestionAttPerso();
        }

        // deplacement monstre
        for (int i = 0; i < labyrinthe.monstre.size(); i++) {
            if (!(labyrinthe.monstre.get(i) == null)) {
                if (rand.nextInt((int) MoteurJeu.getFps()) == 1) {

                    this.labyrinthe.deplacerPerso((String) actionMonstre.get(rand.nextInt(4)), labyrinthe.monstre.get(i));
                }
            }
        }
    }

    @Override
    public void init() {
    }
    /**
     * Initiaton des actions du monstre que l'on place dans une ArrayList<String>
     */
    private void initActionMonstre(){
        rand = new Random();
        actionMonstre = new ArrayList<String>();
        actionMonstre.add(HAUT);
        actionMonstre.add(BAS);
        actionMonstre.add(DROITE);
        actionMonstre.add(GAUCHE);
    }
    /**
     * Méthode qui renvoie false qd le jeu n'est aps terminé et true sinon
     * @return boolean
     */
    @Override
    public boolean etreFini() {
        return finiPerso || finiMonstre;
    }

    /**
     * Méthode de getter qui renvoie le labyrinthe
     * @return labytinthe
     */
    public Labyrinthe getLaby() {
        return labyrinthe;
    }
}

