package ar.uba.fi.tdd.rulogic.model;

import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;

public class RuleTest {


    @Test
    public void testDeberiaInizialitarFactsOk() {
        Rule rule = new Rule("hijo(X, Y) :- varon(X), padre(Y, X)");
        Assert.assertFalse(rule.getFacts().isEmpty());
        Assert.assertTrue(rule.getFacts().contains("varon(X)"));
        Assert.assertTrue(rule.getFacts().contains("padre(Y, X)"));
    }

    @Test
    public void testDeberiaInizialitarParamsOk() {
        Rule rule = new Rule("hijo(X, Y) :- varon(X), padre(Y, X)");
        Assert.assertFalse(rule.getParams().isEmpty());
        Assert.assertTrue(rule.getParams().contains("X"));
        Assert.assertTrue(rule.getParams().contains("Y"));
    }

    @Test
    public void testDeberiaDevolverToString() {
        Rule rule = new Rule("hijo(X, Y) :- varon(X), padre(Y, X)");
        Assert.assertEquals(rule.toString(),"hijo(X, Y) :- varon(X), padre(Y, X)");
    }

    @Test
    public void testEvaluateRuleDeberiaDevolverLosFactsAEvaluar() {
        Rule rule = new Rule("hijo(X, Y) :- varon(X), padre(Y, X)");
        List<String> params = new ArrayList<String>();
        params.add("pepe");
        params.add("juan");
        Assert.assertTrue(rule.evaluate(params).contains("varon(pepe)"));
        Assert.assertTrue(rule.evaluate(params).contains("padre(juan, pepe)"));
    }

    @Test
    public void testEvaluateRuleDeberiaDevolverTodosLosFactsAEvaluar() {
        Rule rule = new Rule("tio(X, Y, Z):- varon(X),hermano(X, Z),padre(Z, Y)");
        List<String> params = new ArrayList<String>();
        params.add("nicolas");
        params.add("alejandro");
        params.add("roberto");
        Assert.assertTrue(rule.evaluate(params).contains("varon(nicolas)"));
        Assert.assertTrue(rule.evaluate(params).contains("hermano(nicolas, roberto)"));
        Assert.assertTrue(rule.evaluate(params).contains("padre(roberto, alejandro)"));
    }


}
