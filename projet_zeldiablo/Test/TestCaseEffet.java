import gameLaby.laby.LabyJeu;
import gameLaby.laby.Labyrinthe;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCaseEffet {



    @Test
    public void test_sansCaseEffet() throws IOException {
        //preparation des données
        LabyJeu JL = new LabyJeu();

        assertEquals( 0 ,JL.getLaby().caseEffets.size());
    }

    @Test
    public void test_avecCaseEffet() throws IOException {
        //preparation des données
        LabyJeu JL = new LabyJeu("labySimple/laby1P.txt");

        assertEquals(2,JL.getLaby().caseEffets.size());
    }
}
