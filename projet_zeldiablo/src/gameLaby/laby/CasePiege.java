package gameLaby.laby;

public class CasePiege extends CaseEffet {
    /**
     * degat du piege
     */
    int degats;
    /**
     * Constructeur de la classe CasePiege qui prend en parametre les coordonnées de la case
     * @param x
     * @param y
     */
    public CasePiege(int x, int y) {
        super(x,y);
        this.degats=25;
    }
    /**
     * Implémentation de la méthode abstract declencher la case piege
     * @param p Perso
     */
    @Override
    public void declencher(Perso p) {
        this.setActiver(true);
        p.perdrePv(this.degats);
        System.out.println("case à effet declancher !\nPv : "+p.getPv());
    }
}

