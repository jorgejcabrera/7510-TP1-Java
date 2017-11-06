package ar.uba.fi.tdd.rulogic.model;

import java.util.List;

public class Interpreter {
    private DBClient database;

    public Interpreter(List<String> raws){
        this.database = new DBClient(raws);
    }

    public boolean answer(String query) {
        return this.database.executeQuery(query);
    }
}
