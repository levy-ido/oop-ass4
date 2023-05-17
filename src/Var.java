import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a variable.
 */
public class Var implements Expression {
    private final String name;

    /**
     * Constructs a new Var with the given name.
     * @param name A String representing the new variable's name
     */
    public Var(String name) {
        this.name = name;
    }
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (!assignment.containsKey(this.name)) {
            throw new Exception("The variable " + name + " is not assigned.");
        }
        return assignment.get(this.name);
    }

    @Override
    public double evaluate() throws Exception {
        return this.evaluate(new HashMap<>());
    }

    @Override
    public List<String> getVariables() {
        List<String> variableList = new ArrayList<>();
        variableList.add(this.name);
        return variableList;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.name.equals(var)) {
            return expression;
        }
        return this;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public Expression differentiate(String var) {
        if (this.name.equals(var)) {
            return new Num(1.0);
        }
        return new Num(0.0);
    }

    @Override
    public Expression simplifyAssumingThereAreVariables() {
        return this;
    }

    @Override
    public Expression simplify() {
        return this.simplifyAssumingThereAreVariables();
    }
}
