package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DBClient implements Database {

    private List<String> facts;
    private List<Rule> rules;
    private DBValidator validator;

    public List<Rule> getRules () { return this.rules;}
    public List<String> getFacts () { return this.facts;}
    public DBClient(List<String> raws) {
        this.validator = new DBValidator();
        this.facts = new ArrayList<String>();
        this.rules = new ArrayList<Rule>();
        this.load(raws);
    }

    public void load(List<String> raws) {
        for (Iterator<String> i = raws.iterator(); i.hasNext();) {
            String raw = i.next();
            raw = raw.replace(".","")
                    .replaceAll(" ","")
                    .trim();
            if (validator.isValidRule(raw)) {
                Rule newRule = new Rule(raw);
                this.rules.add(newRule);
            } else if (validator.isValidFact(raw)) {
                this.facts.add(raw);
            }
        }
    }

    public boolean executeQuery(String query) {
        query = query.replaceAll(" ","");
        if (validator.isValidQuery(query)) {
            Rule rule = findRule(query);
            if (rule != null) {
                List<String> queryParams = DBParser.getInstance().paramsAsList(query);
                List<String> facts = rule.evaluate(queryParams);
                for (String fact : facts) {
                    if (!isFact(fact))
                        return false;
                }
                return true;
            } else if (isFact(query)) {
                return true;
            }
        }
        return false;
    }

    private boolean isFact(String query) {
        return this.facts.contains(query);
    }

    private Rule findRule(String query) {
        final String ruleName = DBParser.getInstance().getRuleName(query);
        for (Rule rule : this.rules) {
            if (rule.getRuleName().equals(ruleName))
                return rule;
        }
        return null;
    }
}
