import gameLaby.laby.LabyJeu;
import gameLaby.laby.Labyrinthe;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMonstre {



    @Test
    public void test_LabyAvecMonstre() throws IOException {
        //preparation des données
        LabyJeu JL = new LabyJeu("labySimple/laby1M.txt");

        assertEquals(5,JL.getLaby().monstre.get(0).getX());
        assertEquals(2,JL.getLaby().monstre.get(0).getY());

    }
    @Test
    public void test_LabySansMonstre() throws IOException {
        //preparation des données
        LabyJeu JL = new LabyJeu();

        assertEquals(null,JL.getLaby().monstre);

    }

    @Test
    public void test_deplacerPersoSurMonstre() throws IOException {
        //preparation des données
        LabyJeu JL = new LabyJeu("labySimple/laby1M.txt");
        JL.getLaby().deplacerPerso("Haut", JL.getLaby().pj);
        JL.getLaby().deplacerPerso("Haut",JL.getLaby().pj);
        JL.getLaby().deplacerPerso("Haut", JL.getLaby().pj);


        assertEquals(5,JL.getLaby().monstre.get(0).getX());
        assertEquals(2,JL.getLaby().monstre.get(0).getY());

        assertEquals(5,JL.getLaby().pj.getX());
        assertEquals(3,JL.getLaby().pj.getY());

    }
}
