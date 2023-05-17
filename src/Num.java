import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a number.
 */
public class Num implements Expression {
    private final double value;

    /**
     * Constructs a new Num with the given value.
     * @param value A double representing the value to construct the new Num with
     */
    public Num(double value) {
        this.value = value;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.evaluate();
    }

    @Override
    public double evaluate() throws Exception {
        return this.value;
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    @Override
    public String toString() {
        return Double.toString(this.value);
    }

    @Override
    public Expression differentiate(String var) {
        return new Num(0.0);
    }

    @Override
    public Expression simplifyAssumingThereAreVariables() {
        return this.simplify();
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
