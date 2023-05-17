import java.util.Map;

/**
 * Represents a negation expression.
 */
public class Neg extends UnaryExpression implements Expression {
    /**
     * Constructs a new Neg with the given expression.
     * @param expression An Expression representing the expression to negate
     */
    public Neg(Expression expression) {
        super(expression);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return -super.getOperand().evaluate(assignment);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(super.getOperand().assign(var, expression));
    }

    @Override
    public String toString() {
        return "(-" + super.getOperand().toString() + ")";
    }

    @Override
    public Expression differentiate(String var) {
        return new Neg(super.getOperand().differentiate(var));
    }

    @Override
    public Expression simplifyAssumingThereAreVariables() {
        return new Neg(this.getOperand().simplify());
    }
}
