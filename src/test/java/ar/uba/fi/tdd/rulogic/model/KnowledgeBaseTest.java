package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.Arrays;
import java.util.List;

public class KnowledgeBaseTest {

	private List<String> raws;

	@Before
	public void setUp() throws Exception {
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
	}

	@Test
	public void testInterpreterDeberiaResolverConsultaExistente() {
		Interpreter interpreter = new Interpreter(this.raws);
		Assert.assertTrue(interpreter.answer("varon(pepe)"));
	}

	@Test
	public void testInterpreterDeberiaResolverConsultaConEspacios() {
		Interpreter interpreter = new Interpreter(this.raws);
		Assert.assertTrue(interpreter.answer("varon(  pepe)"));
	}

	@Test
	public void testInterpreterDeberiaResolverConsultaNoExistente() {
		Interpreter interpreter = new Interpreter(this.raws);
		Assert.assertFalse(interpreter.answer("varon(jorge)"));
	}

}
