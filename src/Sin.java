import java.util.List;
import java.util.Map;

/**
 * Represents a sine expression.
 */
public class Sin extends UnaryExpression implements Expression {

    /**
     * Constructs a new Sin with the given expression.
     * @param e An Expression representing the expression to sine
     */
    public Sin(Expression e) {
        super(e);
    }
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.sin(super.getE().evaluate(assignment));
    }

    @Override
    public double evaluate() throws Exception {
        return Math.sin(super.getE().evaluate());
    }

    @Override
    public List<String> getVariables() {
        return super.getE().getVariables();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(super.getE().assign(var, expression));
    }

    @Override
    public String toString() {
        return "sin(" + super.getE().toString() + ")";
    }
}
