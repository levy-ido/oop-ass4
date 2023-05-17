import java.util.Map;

/**
 * Represents a power expression.
 */
public class Pow extends BinaryExpression implements Expression {
    /**
     * Constructs a new Pow with the given base and exponent.
     * @param base An Expression representing the new Pow's base
     * @param exponent An Expression representing the new Pow's exponent
     */
    public Pow(Expression base, Expression exponent) {
        super(base, exponent);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double baseEvaluation = super.getFirst().evaluate(assignment);
        double exponentEvaluation = super.getSecond().evaluate(assignment);
        return Math.pow(baseEvaluation, exponentEvaluation);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression assignedBase = super.getFirst().assign(var, expression);
        Expression assignedExponent = super.getSecond().assign(var, expression);
        return new Pow(assignedBase, assignedExponent);
    }
    @Override
    public String toString() {
        return "(" + super.getFirst().toString() + "^" + super.getSecond().toString() + ")";
    }

    @Override
    public Expression differentiate(String var) {
        Expression base = super.getFirst();
        Expression exponent = super.getSecond();
        Expression baseDividedByExponent = new Div(exponent, base);
        Expression augend = Differentiation.fDerivativeMultipliedByG(base, baseDividedByExponent, var);
        Expression naturalLogOfBase = Log.naturalLogOf(base);
        Expression addend = Differentiation.fDerivativeMultipliedByG(exponent, naturalLogOfBase, var);
        Expression multiplicand = new Plus(augend, addend);
        return new Mult(this, multiplicand);
    }

    @Override
    public Expression simplifyAssumingThereAreVariables() {
        Expression simplifiedBase = super.getFirst().simplify();
        Expression simplifiedExponent = super.getSecond().simplify();
        return new Pow(simplifiedBase, simplifiedExponent);
    }
}
