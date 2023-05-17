import java.util.List;
import java.util.Map;

/**
 * Represents a number.
 */
public class Num implements Expression {
    private final double val;

    /**
     * Constructs a new Num with the given value.
     * @param val A double representing the value to construct the new Num with
     */
    public Num(double val) {
        this.val = val;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.val;
    }

    @Override
    public double evaluate() throws Exception {
        return this.val;
    }

    @Override
    public List<String> getVariables() {
        return null;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    @Override
    public String toString() {
        return Double.toString(this.val);
    }
}
