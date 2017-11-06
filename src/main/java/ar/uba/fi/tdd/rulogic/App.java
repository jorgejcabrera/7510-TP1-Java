package ar.uba.fi.tdd.rulogic;

import ar.uba.fi.tdd.rulogic.model.Interpreter;
import ar.uba.fi.tdd.rulogic.model.DBParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

public class App {
	public static void main(String[] args) throws IOException {
		List<String> raws = new ArrayList<String>();
		try {
			raws = DBParser.getInstance().rawsAsList(args[0]);
		} catch (NoSuchFileException e) {
			System.out.println("The directory doesn't exist.");
			return;
		} catch (Exception e) {
			System.out.println("Ups! it was an error.");
			return;
		}
		Interpreter interpreter = new Interpreter(raws);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("I shall answer all your questions!");
		String query = buffer.readLine();

		while (!query.equals("q")) {
			System.out.println("Result: " + interpreter.answer(query));
			query = buffer.readLine();
		}
		System.out.println("See you soon!");

	}
}
