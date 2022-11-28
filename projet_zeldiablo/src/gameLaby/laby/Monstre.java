package gameLaby.laby;

public class Monstre extends Perso{

    /**
     * attaque d'un personnage
     */
    private int degats = 30;

    /**
     * constructeur de la classe Monstre
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Monstre(int dx, int dy) {
       super(dx, dy);
    }

    /**
     * méthode qui permet au monstre d'attaquer un Joueur
     * @param p Perso qui correspond au joueur
     */

    public void attaquer(Perso p){
        if(!(p instanceof Monstre)){
            this.getAttaque();
            p.perdrePv(degats);
        }

    }


}
