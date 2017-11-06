package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
public class ParserTest {

    @Test
    public void testParserDeberiaObtenerTodasLasLineasDeArchivo() throws IOException {
        List<String> raws = DBParser.getInstance().rawsAsList("../7510-TP1-Java/src/main/resources/rules.db");
        Assert.assertTrue(raws.contains("varon(juan)."));
        Assert.assertTrue(raws.contains("varon(alejandro)."));
    }

    @Test
    public void testParserDeberiaObtenerTodasFactsComoUnString() {
        String raws = DBParser.getInstance().factsAsString("hija(X, Y) :- mujer(X), padre(Y, X).");
        Assert.assertTrue(raws.equals("mujer(X), padre(Y, X)."));
    }

    @Test
    public void testParserDeberiaDevolverVacioParaInputInvalido() {
        String raws = DBParser.getInstance().factsAsString("hija(X, Y) :-");
        Assert.assertTrue(raws.isEmpty());
    }

    @Test
    public void testParserDeberiaDevolverElNombreDeLaRegla() {
        String ruleName = DBParser.getInstance().getRuleName("hija(X, Y) :-");
        Assert.assertTrue(ruleName.equals("hija"));
    }

    @Test
    public void testParserDeberiaDevolverVacioParaReglaInvalida() {
        String ruleName = DBParser.getInstance().getRuleName(":- mujer(X), padre(Y, X)");
        Assert.assertTrue(ruleName.isEmpty());
    }

    @Test
    public void testParserDeberiaDevolverUnaListaDeHechos() {
        List<String> facts = DBParser.getInstance().factsAsList("mujer(X), padre(Y, X)");
        Assert.assertTrue(facts.contains("mujer(X)"));
        Assert.assertTrue(facts.contains("padre(Y, X)"));
        Assert.assertTrue(facts.size() == 2);
    }

    @Test
    public void testParserDeberiaDevolverLosParametrosDeUnaConsulta() {
        List<String> params = DBParser.getInstance().paramsAsList("padre(Y, X)");
        Assert.assertTrue(params.contains("X"));
        Assert.assertTrue(params.contains("Y"));
        Assert.assertTrue(params.size() == 2);
    }

    @Test
    public void testParserDeberiaDevolverParametrosVaciosParaConsultaInvalida() {
        List<String> params = DBParser.getInstance().paramsAsList("padre()");
        Assert.assertTrue(params.size() == 0);
    }
}
