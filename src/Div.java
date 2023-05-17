import java.util.Map;

/**
 * Represents a division expression.
 */
public class Div extends BinaryExpression implements Expression {
    /**
     * Constructs a new Div with the given expressions.
     * @param numerator An Expression representing the new Div's numerator
     * @param denominator An Expression representing the new Div's denominator
     */
    public Div(Expression numerator, Expression denominator) {
        super(numerator, denominator);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double numeratorEvaluation = super.getFirst().evaluate(assignment);
        double denominatorEvaluation = super.getSecond().evaluate(assignment);
        if (Double.compare(denominatorEvaluation, 0.0) == 0) {
            throw new ArithmeticException("Division by zero.");
        }
        return numeratorEvaluation / denominatorEvaluation;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression assignedNumerator = super.getFirst().assign(var, expression);
        Expression assignedDenominator = super.getSecond().assign(var, expression);
        return new Div(assignedNumerator, assignedDenominator);
    }

    @Override
    public String toString() {
        return "(" + super.getFirst().toString() + " / " + super.getSecond().toString() + ")";
    }

    @Override
    public Expression differentiate(String var) {
        Expression numerator = super.getFirst();
        Expression denominator = super.getSecond();
        Expression minuend = Differentiation.fDerivativeMultipliedByG(numerator, denominator, var);
        Expression subtrahend = Differentiation.fDerivativeMultipliedByG(denominator, numerator, var);
        Expression derivativeNumerator = new Minus(minuend, subtrahend);
        Expression derivativeDenominator = new Pow(denominator, new Num(2.0));
        return new Div(derivativeNumerator, derivativeDenominator);
    }

    @Override
    public Expression simplifyAssumingThereAreVariables() {
        Expression simplifiedNumerator = super.getFirst().simplify();
        Expression simplifiedDenominator = super.getSecond().simplify();
        String simplifiedDenominatorString = simplifiedDenominator.toString();
        if (simplifiedDenominatorString.equals("1.0")) {
            return simplifiedNumerator;
        }
        if (simplifiedNumerator.toString().equals(simplifiedDenominatorString)) {
            return new Num(1.0);
        }
        return new Div(simplifiedNumerator, simplifiedDenominator);
    }
}
