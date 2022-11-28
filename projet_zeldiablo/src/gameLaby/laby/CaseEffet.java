package gameLaby.laby;

public abstract class CaseEffet {

    private boolean activer;

    /**
     * position du personnage
     */
    private int x, y;

    /**
     * Constructeur de la classe CaseEffet
     * @param dx position x
     * @param dy position y
     */

    public CaseEffet(int dx, int dy) {
        this.x = dx;
        this.y = dy;
        this.activer = false;
    }

    /**
     * Méthode abstraite déclencher les cases à effet
     * @param p Personnage
     */

    public abstract void declencher(Perso p);

    /**
     * Méthode qui met le boolean visible à true
     * @param visible Boolean
     */

    public void setActiver(boolean visible) {
        this.activer = visible;
    }

    /**
     * Méthode qui retourne le boolean activer
     */

    public boolean isActiver() {
        return activer;
    }

    /**
     * Getter de Y
     * @return y int
     */
    public int getY() {
        return y;
    }

    /**
     * Getter de X
     * @return x int
     */
    public int getX() {
        return x;
    }
}
