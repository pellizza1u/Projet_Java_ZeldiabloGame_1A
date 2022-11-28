package gameLaby.laby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * classe labyrinthe. represente un labyrinthe avec
 * <ul> des murs </ul>
 * <ul> un personnage (x,y) </ul>
 */
public class Labyrinthe {

    int hauteur;
    int largeur;

    /**
     * Constantes char
     */
    public static final char MUR = 'X';
    public static final char PJ = 'P';
    public static final char VIDE = '.';
    public static final char MONSTRE = 'M';
    public static final char PIEGE = 'T';
    public static final char HEAL = 'H';

    /**
     * constantes actions possibles
     */
    public static final String HAUT = "Haut";
    public static final String BAS = "Bas";
    public static final String GAUCHE = "Gauche";
    public static final String DROITE = "Droite";

    /**
     * attribut du personnage
     */
    public Perso pj;

    /**
     * attribut Monstre
     */
    public List<Monstre> monstre;

    /**
     * attribut piege
     */
    public List<CaseEffet> caseEffets;


    /**
     * les murs du labyrinthe
     */
    public boolean[][] murs;


    /**
     * retourne la case suivante selon une action
     *
     * @param x      case depart
     * @param y      case depart
     * @param action action effectuee
     * @return case suivante
     */
    static int[] getSuivant(int x, int y, String action) {
        switch (action) {
            case HAUT:
                // on monte une ligne
                y--;
                break;
            case BAS:
                // on descend une ligne
                y++;
                break;
            case DROITE:
                // on augmente colonne
                x++;
                break;
            case GAUCHE:
                // on augmente colonne
                x--;
                break;
            default:
                throw new Error("action inconnue");
        }
        int[] res = {x, y};
        return res;
    }

    /**
     * charge le labyrinthe
     *
     * @param nom nom du fichier de labyrinthe
     * @return labyrinthe cree
     * @throws IOException probleme a la lecture / ouverture
     */
    public Labyrinthe(String nom) throws IOException {
        // ouvrir fichier
        FileReader fichier = new FileReader(nom);
        BufferedReader bfRead = new BufferedReader(fichier);


        // lecture nblignes
        this.hauteur = Integer.parseInt(bfRead.readLine());
        // lecture nbcolonnes
        this.largeur = Integer.parseInt(bfRead.readLine());

        // creation labyrinthe vide
        this.murs = new boolean[this.largeur][this.hauteur];
        this.pj = null;
        this.monstre=new ArrayList<Monstre>();
        caseEffets = new ArrayList<CaseEffet>();

        // lecture des cases
        String ligne = bfRead.readLine();

        // stocke les indices courants
        int numeroLigne = 0;

        // parcours le fichier
        while (ligne != null) {

            // parcours de la ligne
            for (int colonne = 0; colonne < ligne.length(); colonne++) {
                char c = ligne.charAt(colonne);
                switch (c) {
                    case MUR:
                        this.murs[colonne][numeroLigne] = true;
                        break;
                    case VIDE:
                        this.murs[colonne][numeroLigne] = false;
                        break;
                    case PJ:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.pj = new Joueur(colonne, numeroLigne);
                        break;
                    case MONSTRE:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute MONSTRE
                        this.monstre.add(new Monstre(colonne, numeroLigne));
                        break;
                    case PIEGE:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute piege
                        this.caseEffets.add(new CasePiege(colonne, numeroLigne));
                        break;
                    case HEAL:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute soins
                        this.caseEffets.add(new CaseHeal(colonne, numeroLigne));
                        break;

                    default:
                        throw new Error("caractere inconnu " + c);
                }
            }

            // lecture
            ligne = bfRead.readLine();
            numeroLigne++;
        }

        // ferme fichier
        bfRead.close();
    }

    /**
     * Méthode qui permet de gerer si un perso tombe sur une CaseEffet
     * @param perso
     * @param suivante case suivante
     */

    private void gestionCasesEffet(Perso perso, int[]suivante){
        CasePiege piege = null;
        CaseHeal heal = null;
        for (int j = 0; j < caseEffets.size(); j++) {
            if (caseEffets.get(j) instanceof CasePiege) piege = (CasePiege) caseEffets.get(j);
            else heal = (CaseHeal) caseEffets.get(j);
            //si c'est un piege
            if (piege != null) {
                if ((piege.getX() == suivante[0]) && (piege.getY() == suivante[1]) && !piege.isActiver()) {
                    piege.declencher(perso);
                    if (perso.getPv() == 0 && perso instanceof Monstre) monstre.remove(perso);
                }
            }
            //si c'est un soins
            if (heal != null) {
                if ((heal.getX() == suivante[0]) && (heal.getY() == suivante[1]) && !heal.isActiver()) {
                    heal.declencher(perso);
                }
            }
        }
    }

    /**
     * Méthode qui dis si un monstre a était rentré
     * @return boolean
     */

    private boolean monstreSurChemin(Perso perso, int[] suivante){
        boolean monstreSurChemin = false;
        int i = 0;
        while(i < monstre.size() && !monstreSurChemin) {
            if ((monstre.get(i).x == suivante[0]) && (monstre.get(i).y == suivante[1])) {
                monstreSurChemin = true;
            }
            i++;
        }
        return monstreSurChemin;
    }
    /**
     * Méthode qui dis si un joueur a était rentré
     * @return boolean
     */
    private boolean joueurSurChemin(Perso perso, int[] suivante){
        boolean joueurSurChemin = false;
        if ((pj.x == suivante[0]) || !(pj.y == suivante[1])) {
            if ((pj.x == suivante[0]) && (pj.y == suivante[1])) {
                joueurSurChemin = true;
                perso.attaquer(pj);
            }
        }
        return joueurSurChemin;
    }/**
     * Méthode qui dis si le deplacement d'un perso est possible
     * @return boolean
     */

    private boolean deplacementPossible(Perso perso, int[]suivante){
        boolean deplacementPossible = false;
        // si c'est pas un mur
        if (!this.murs[suivante[0]][suivante[1]] ) {

            if (perso instanceof Monstre) {
                if (!monstreSurChemin(perso, suivante) && !joueurSurChemin(perso, suivante)){
                    deplacementPossible = true;
                }
            }else{
                if (!monstreSurChemin(perso, suivante)){
                    deplacementPossible = true;
                }
            }
        }
        return deplacementPossible;
    }
    /**
     * deplace le personnage en fonction de l'action.
     * gere la collision avec les murs
     *
     * @param action une des actions possibles
     */
    public void deplacerPerso(String action, Perso perso) {
        //actualise le dernier mouvement du joueur
        this.pj.setDernierMouvement(action);
        // case courante
        int[] courante = {perso.x, perso.y};


        // calcule case suivante
        int[] suivante = getSuivant(courante[0], courante[1], action);

        if (deplacementPossible(perso, suivante)){
            perso.x = suivante[0];
            perso.y = suivante[1];
        }
        gestionCasesEffet(perso, suivante);
    }

    private Monstre trouverMonstre(int[] coordonnees){
        boolean trouve =false;
        Monstre m = null;
        Monstre monstreCourant;
        int i =0;
        while(i<this.monstre.size() && !trouve){
            monstreCourant = this.monstre.get(i);
            int[] coordonneesActuelles = {monstreCourant.getX(), monstreCourant.getY()};
            if (coordonneesActuelles[0]==coordonnees[0] && coordonneesActuelles[1]==coordonnees[1]) {
                trouve = true;
                m=monstreCourant;
            }
            i++;
        }
        return m;
    }


    public void gestionAttPerso(){
        int[] suivante = getSuivant(this.pj.getX(),this.pj.getY(), this.pj.getDernierMouvement());
        if (monstreSurChemin(this.pj, suivante)){
            Monstre m = trouverMonstre(suivante);
            this.pj.attaquer(m);
            System.out.println("att contre un monstre");
            if (m.getPv() == 0)
                monstre.remove(m);
        }

    }


    /**
     * jamais fini
     *
     * @return fin du jeu
     */
    public boolean etreFini() {
        return false;
    }

    // ##################################
    // GETTER
    // ##################################

    /**
     * return taille selon Y
     *
     * @return
     */
    public int getLengthY() {
        return murs[0].length;
    }

    /**
     * return taille selon X
     *
     * @return
     */
    public int getLength() {
        return murs.length;
    }

    /**
     * méthode qui permet de récupérer un mur à partir de ses coordonnées
     * @param x
     * @param y
     * @return boolean
     */
    public boolean getMur(int x, int y) {
        // utilise le tableau de boolean
        return this.murs[x][y];
    }

    public int getHauteur() {
        return hauteur;
    }
    /**
     * méthode de guetter qui permet de récupérer la hauteur
     * @return int
     */

    public int getLargeur() {
        return largeur;
    }
    /**
     * méthode de guetter qui permet de récupérer la hauteur
     * @return int
     */
}
