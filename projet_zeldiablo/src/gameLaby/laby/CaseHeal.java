package gameLaby.laby;

public class CaseHeal extends CaseEffet {
    /**
     * soins du heal
     */
    int heal;

    /**
     * Constructeur de la classe CaseHeal qui prend en parametre les coordonnées de la case
     * @param x
     * @param y
     */
    public CaseHeal(int x, int y) {
        super(x,y);
        this.heal =50;
    }
    /**
     * Implémentation de la méthode abstract declencher la case de heal
     * @param p Perso
     */
    @Override
    public void declencher(Perso p) {
        this.setActiver(true);
        p.gagnerPv(this.heal);
        System.out.println("case à effet declancher !\nPv : "+p.getPv());
    }
}