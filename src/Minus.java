import java.util.Map;

/**
 * Represents a subtraction expression.
 */
public class Minus extends BinaryExpression implements Expression {
    /**
     * Constructs a new Minus with the given operands.
     * @param minuend An Expression representing the new Minus's minuend
     * @param subtrahend An Expression representing the new Minus's subtrahend
     */
    public Minus(Expression minuend, Expression subtrahend) {
        super(minuend, subtrahend);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double minuendEvaluation = super.getFirst().evaluate(assignment);
        double subtrahendEvaluation = super.getSecond().evaluate(assignment);
        return minuendEvaluation - subtrahendEvaluation;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression assignedMinuend = super.getFirst().assign(var, expression);
        Expression assignedSubtrahend = super.getSecond().assign(var, expression);
        return new Minus(assignedMinuend, assignedSubtrahend);
    }

    @Override
    public String toString() {
        return "(" + super.getFirst().toString() + " - " + super.getSecond().toString() + ")";
    }

    @Override
    public Expression differentiate(String var) {
        Expression minuendDerivative = super.getFirst().differentiate(var);
        Expression subtrahendDerivative = super.getSecond().differentiate(var);
        return new Minus(minuendDerivative, subtrahendDerivative);
    }

    @Override
    public Expression simplifyAssumingThereAreVariables() {
        Expression simplifiedMinuend = super.getFirst().simplify();
        Expression simplifiedSubtrahend = super.getSecond().simplify();
        String simplifiedMinuendString = simplifiedMinuend.toString();
        String simplifiedSubtrahendString = simplifiedSubtrahend.toString();
        if (simplifiedMinuendString.equals("0.0")) {
            return new Neg(simplifiedSubtrahend);
        }
        if (simplifiedSubtrahendString.equals("0.0")) {
            return simplifiedMinuend;
        }
        if (simplifiedMinuendString.equals(simplifiedSubtrahendString)) {
            return new Num(0.0);
        }
        return new Minus(simplifiedMinuend, simplifiedSubtrahend);
    }
}
