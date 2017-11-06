package ar.uba.fi.tdd.rulogic.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DBParser {

    private static DBParser instance = null;

    public static DBParser getInstance() {
        if (instance == null) {
            instance = new DBParser();
        }
        return instance;
    }

    public List<String> rawsAsList(String path) throws IOException {
        List<String> raws = new ArrayList<String>();
        List<Object> lines = Files.lines(Paths.get(path)).collect(Collectors.toList());
        for(Object line : lines) {
            raws.add(line.toString());
        }
        return raws;
    }

    public List<String> paramsAsList(String input) {
        List<String> params = new ArrayList<String>();
        Pattern p = Pattern.compile("^[a-z]+\\([^)]+\\)");
        Matcher m = p.matcher(input);
        if (m.find()) {
            String ruleName = m.group();
            p = Pattern.compile("\\([^)]+\\)");
            m = p.matcher(ruleName);
            if (m.find()) {
                String[] paramStr = m.group()
                        .replace("(","")
                        .replace(")","")
                        .split(",");
                for(String param : paramStr) { params.add(param.trim()); }
            }
        }
        return params;
    }

    public List<String> factsAsList(String facts) {
        Pattern p = Pattern.compile("[a-z]+\\([^)]+\\)");
        Matcher m = p.matcher(facts);
        List<String> results = new ArrayList<String>();
        while (m.find()) {
            results.add(m.group());
        }
        return results;
    }

    public String getBaseRule(String input){
        String ruleRegex = "^[a-z]+\\([^)]+\\)";
        return this.applyPattern(input,ruleRegex);
    }

    public String getRuleName(String input) {
        String ruleNameRegex = "^[a-z]+";
        return this.applyPattern(input,ruleNameRegex);
    }

    public String factsAsString(String rule) {
        String factsRegex = "[^:-]+$";
        return this.applyPattern(rule,factsRegex);
    }

    private String applyPattern(String input, String regex) {
        String ruleName = "";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        if (m.find())
            ruleName = m.group().trim();
        return ruleName;
    }
}
