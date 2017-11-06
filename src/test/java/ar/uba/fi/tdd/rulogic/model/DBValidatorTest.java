package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DBValidatorTest {

    @Test
    public void testValidatorDeberiaReconocerQueryValida() {
        DBValidator validator = new DBValidator();
        Assert.assertTrue(validator.isValidRule("hija(X, Y) :- mujer(X), padre(Y, X)."));
    }

    @Test
    public void testValidatorDeberiaReconocerQueriesInvalidas() {
        DBValidator validator = new DBValidator();
        Assert.assertFalse(validator.isValidRule("hija(X, Y) :- mujer(X "));
        Assert.assertFalse(validator.isValidRule("hija(X, Y) : mujer(X), padre(Y, X)."));
        Assert.assertFalse(validator.isValidRule("hija(X, Y) : mujer(X), padre(Y, X"));
        Assert.assertFalse(validator.isValidRule("hija(X, Y :- mujer(X), padre(Y, X)."));
    }

    @Test
    public void testValidatorDeberiaReconocerUnInputInvalido() {
        DBValidator validator = new DBValidator();
        Assert.assertFalse(validator.isValidInput("hija(X, Y"));
    }

    @Test
    public void testValidatorDeberiaReconocerUnInputValido() {
        DBValidator validator = new DBValidator();
        Assert.assertTrue(validator.isValidInput("hija(X, Y)"));
    }

    @Test
    public void testValidatorDeberiaReconocerUnaQueryValida() {
        DBValidator validator = new DBValidator();
        Assert.assertTrue(validator.isValidQuery("padre(juan,pepe)"));
    }

    @Test
    public void testValidatorDeberiaReconocerUnaQueryInvalida() {
        DBValidator validator = new DBValidator();
        Assert.assertFalse(validator.isValidQuery("padrejuan,pepe)"));
    }
}
