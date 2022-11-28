package gameLaby.laby;

public class Joueur extends Perso{


    /**
     * dégats du joueue
     */
    private int degats = 35;
    /**
     * constructeur la classe Joueur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Joueur(int dx, int dy) {
        super(dx, dy);
    }

    /**
     * méthode qui permet au joueur d'attaquer un Monstre
     * @param p Perso qui correspond au montre
     */

    public void attaquer(Perso p) {
        if (!(p instanceof Joueur)) {
            this.getAttaque();
            p.perdrePv(degats);
        }
    }
}
