import java.util.Map;

/**
 * Represents a multiplication expression.
 */
public class Mult extends BinaryExpression implements Expression {
    /**
     * Constructs a new Mult with the given multiplier and multiplicand.
     * @param multiplier An Expression representing the new Mult multiplier
     * @param multiplicand An Expression representing the new Mult multiplicand
     */
    public Mult(Expression multiplier, Expression multiplicand) {
        super(multiplier, multiplicand);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double multiplierEvaluation = super.getFirst().evaluate(assignment);
        double multiplicandEvaluation = super.getSecond().evaluate(assignment);
        return multiplierEvaluation * multiplicandEvaluation;
    }

    @Override
    public double evaluate() throws Exception {
        return this.evaluate(Map.of());
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression assignedMultiplier = super.getFirst().assign(var, expression);
        Expression assignedMultiplicand = super.getSecond().assign(var, expression);
        return new Mult(assignedMultiplier, assignedMultiplicand);
    }

    @Override
    public String toString() {
        return "(" + super.getFirst().toString() + " * " + super.getSecond().toString() + ")";
    }

    @Override
    public Expression differentiate(String var) {
        Expression multiplier = super.getFirst();
        Expression multiplicand = super.getSecond();
        Expression augend = Differentiation.derivativeOfFMultipliedByG(multiplier, multiplicand, var);
        Expression addend = Differentiation.derivativeOfFMultipliedByG(multiplicand, multiplier, var);
        return new Plus(augend, addend);
    }

    @Override
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception exception) {
            Expression simplifiedMultiplier = super.getFirst().simplify();
            Expression simplifiedMultiplicand = super.getSecond().simplify();
            String simplifiedMultiplierString = simplifiedMultiplier.toString();
            String simplifiedMultiplicandString = simplifiedMultiplicand.toString();
            if (simplifiedMultiplierString.equals("0.0") || simplifiedMultiplicandString.equals("0.0")) {
                return new Num(0.0);
            }
            if (simplifiedMultiplierString.equals("1.0")) {
                return simplifiedMultiplicand;
            }
            if (simplifiedMultiplicandString.equals("1.0")) {
                return simplifiedMultiplier;
            }
            return new Mult(simplifiedMultiplier, simplifiedMultiplicand);
        }
    }
}
