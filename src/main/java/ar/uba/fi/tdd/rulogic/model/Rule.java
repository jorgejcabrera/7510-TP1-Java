package ar.uba.fi.tdd.rulogic.model;

import java.util.List;

public class Rule {

    String ruleName;
    List<String> params;
    List<String> facts;

    public List<String> getParams() { return this.params; }
    public String getRuleName () { return this.ruleName; }
    public List<String> getFacts () { return this.facts; }

    public Rule(String rule) {
        DBParser parser = DBParser.getInstance();
        this.ruleName = parser.getRuleName(rule);
        String allFacts = parser.factsAsString(rule);
        this.facts = parser.factsAsList(allFacts);
        this.params = parser.paramsAsList(rule);
    }

    public List<String> evaluate (List<String> params) {
        String description = this.toString();
        int index = 0;
        for (String param : this.params) {
            description = description.replace(param,params.get(index));
            index++;
        }
        String factsFromRule = DBParser.getInstance().factsAsString(description);
        return DBParser.getInstance().factsAsList(factsFromRule);
    }

    public String toString() {
        return  this.ruleName + "(" +
                this.params.toString().replace("[","").replace("]","") + ") :- " +
                this.facts.toString().replace("[","").replace("]","");
    }
}
