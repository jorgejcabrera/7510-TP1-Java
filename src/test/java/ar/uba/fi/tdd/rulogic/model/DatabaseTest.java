package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
public class DatabaseTest {

    private List<String> raws;
    private DBClient dbClient;

    @Before
    public void setUp() {
        this.raws = Arrays.asList("varon(juan).",
            "varon(pepe).",
            "varon(hector).",
            "varon(roberto).",
            "varon(alejandro).",
            "mujer(maria) .",
            "mujer(cecilia).",
            "padre(juan, pepe).",
            "padre(juan, pepa).",
            "padre(hector, maria).",
            "padre(roberto, alejandro).",
            "padre(roberto, cecilia).",
            "hijo(X, Y) :- varon(X), padre(Y, X).",
            "hija(X, Y) :- mujer(X), padre(Y, X).",
            "hermano(nicolas, roberto).",
            "hermano(roberto, nicolas).",
            "varon ( nicolas ) .",
            "tio(X, Y, Z):- varon(X),	hermano(X, Z),padre(Z, Y).",
            "tia(X, Y, Z):- mujer(X),	hermano(X, Z),padre(Z, Y).)");
        this.dbClient = new DBClient(this.raws);
    }

    @Test
    public void testSeDeberianCargarTodasLasRules() {
        Assert.assertEquals(this.dbClient.getRules().size(),4);
    }

    @Test
    public void testSeDeberianCargarTodasLasRowsValidas () {
        Assert.assertEquals(this.dbClient.getFacts().size()+this.dbClient.getRules().size(),this.raws.size());
    }

    @Test
    public void testSeDeberianCargarTodasLasFacts() {
        Assert.assertEquals(this.dbClient.getFacts().size(),15);
    }

    @Test
    public void testDeberiaContenerFactEspecifica() {
        Assert.assertTrue(this.dbClient.getFacts().get(0).toString().equals("varon(juan)"));
    }

    @Test
    public void testDeberiaContenerRuleEspecifica() {
        Assert.assertTrue(this.dbClient.getRules().get(0).toString().equals("hijo(X, Y) :- varon(X), padre(Y,X)"));
    }

    @Test
    public void testDeberiaRetornarFalseParaQueryInvalida() {
        Assert.assertFalse(this.dbClient.executeQuery("malaquery"));
    }

    @Test
    public void testDeberiaRetornarTrueSiExisteFact() {
        Assert.assertTrue(this.dbClient.executeQuery("varon(juan)"));
    }

    @Test
    public void testDeberiaRetornarTrueSiExisteUnaReglaRelacionada() {
        Assert.assertTrue(this.dbClient.executeQuery("hijo(pepe,juan)"));
    }

    @Test
    public void testDeberiaRetornarTrueSiExisteUnaReglaRelacionadaCompuesta() {
        Assert.assertTrue(this.dbClient.executeQuery("tio(nicolas,alejandro,roberto)"));
    }

    @Test
    public void testDeberiaRetornarTrueSiExisteUnaReglaRelacionadaParaQueryConEspacios() {
        Assert.assertTrue(this.dbClient.executeQuery("tio(nicolas,   alejandro,     roberto)"));
    }
}
