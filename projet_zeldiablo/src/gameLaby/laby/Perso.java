package gameLaby.laby;

/**
 * gere un personnage situe en x,y
 */
public abstract class Perso {

    private String dernierMouvement;

    /**
     * vie du Perso
     */
    int pv;

    /**
     * position du personnage
     */
    int x, y;


    /**
     * dégats d'un personnage
     */
    int degats;

    /**
     * constructeur de la classe Perso
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Perso(int dx, int dy) {
        this.x = dx;
        this.y = dy;
        this.pv=100;
        this.dernierMouvement="Haut";
    }

    /**
     * permet de savoir si le personnage est en x,y
     *
     * @param dx position testee
     * @param dy position testee
     * @return true si le personnage est bien en (dx,dy)
     */
    public boolean etrePresent(int dx, int dy) {

        return (this.x == dx && this.y == dy);
    }

    // ############################################
    // GETTER
    // ############################################

    /**
     * guetter de X
     * @return position x du personnage
     */
    public int getX() {
        // getter
        return this.x;
    }

    /**
     * getter de Y
     * @return position y du personnage
     */
    public int getY() {
        //getter
        return this.y;
    }

    /**
     * Methode qui permet de récupérer les PV
     * @return int
     */

    public int getPv() {
        return pv;
    }

    /**
     *Methode qui fait perdre des pv
     *@return int
     */

    /**
     *Methode qui fait gagner des pv
     */
    public void perdrePv(int pvperdu){
        this.pv=this.pv-pvperdu;
        if(this.pv<0)this.pv=0;
    }
    /**
     *Methode qui fait gagner des pv
     */
    public void gagnerPv(int pvGagner){
        this.pv=this.pv+pvGagner;
        if(this.pv>100)this.pv=100;
    }
    /**
     * méthode qui permet au monstre d'attaquer un Joueur ou l'inverse
     * @param p Perso qui correspond au joueur
     */
    public abstract void attaquer(Perso p);

    /**
     * méthode qui permet de recevoir l'impact des dégats causés pour connaître le nombre de dégats
     * @return int
     */

    public int getAttaque() {

        return this.degats;
    }

    public String getDernierMouvement() {
        return dernierMouvement;
    }

    public void setDernierMouvement(String dernierMouvement) {
        this.dernierMouvement = dernierMouvement;
    }
}