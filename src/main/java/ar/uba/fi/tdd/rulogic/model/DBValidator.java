package ar.uba.fi.tdd.rulogic.model;

import java.util.Iterator;
import java.util.List;

public class DBValidator {

    public DBValidator(){
    }

    public boolean isValidRule(String rule) {
        boolean isRule = rule.matches(".+:-.+");
        if (isRule) {
            DBParser parser = DBParser.getInstance();
            String baseRule = parser.getBaseRule(rule);
            if (!this.isValidInput(baseRule)) return false;
            String factsFromRule = parser.factsAsString(rule);
            if (!factsFromRule.isEmpty()) {
                List<String> facts = parser.factsAsList(factsFromRule);
                for (Iterator<String> i = facts.iterator(); i.hasNext();) {
                    String fact = i.next();
                    if (!isValidFact(fact))
                        return false;
                }
                return !facts.isEmpty();
            }
        }
        return false;
    }

    public boolean isValidQuery(String query) { return this.isValidInput(query);}
    public boolean isValidFact(String fact) { return this.isValidInput(fact);}
    public boolean isValidInput (String input) { return input.matches("^[a-z]+\\([^()]+\\)");}
}
