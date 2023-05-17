import java.util.List;
import java.util.Map;

/**
 * Represents a cosine expression.
 */
public class Cos extends UnaryExpression implements Expression {
    /**
     * Constructs a new Cos with the provided expression.
     * @param e An Expression representing the expression to cosine
     */
    public Cos(Expression e) {
        super(e);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.cos(super.getE().evaluate(assignment));
    }

    @Override
    public double evaluate() throws Exception {
        return Math.cos(super.getE().evaluate());
    }

    @Override
    public List<String> getVariables() {
        return super.getE().getVariables();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(super.getE().assign(var, expression));
    }

    @Override
    public String toString() {
        return "cos(" + super.getE().toString() + ")";
    }
}
