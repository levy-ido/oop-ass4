import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a variable.
 */
public class Var implements Expression {
    private final String name;

    /**
     * Constructs a new Var with the given name.
     * @param name A String representing the new Var name
     */
    public Var(String name) {
        this.name = name;
    }
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(this.name)) {
            return assignment.get(this.name);
        }
        throw new Exception("The variable " + name + " is not assigned.");
    }

    @Override
    public double evaluate() throws Exception {
        return this.evaluate(Map.of());
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
    public Expression simplify() {
        return this;
    }
}
