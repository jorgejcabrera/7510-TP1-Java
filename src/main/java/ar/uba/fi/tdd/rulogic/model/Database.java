package ar.uba.fi.tdd.rulogic.model;

import java.util.List;

public interface Database {

    void load(List<String> raws);

    boolean executeQuery(String query);
}
