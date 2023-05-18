import java.util.Map;

/**
 * Represents an addition expression.
 */
public class Plus extends BinaryExpression implements Expression {
    /**
     * Constructs a new Plus with the given augend and addend.
     * @param augend An Expression representing the new Plus augend
     * @param addend An Expression representing the new Plus addend
     */
    public Plus(Expression augend, Expression addend) {
        super(augend, addend);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double augendEvaluation = super.getFirst().evaluate(assignment);
        double addendEvaluation = super.getSecond().evaluate(assignment);
        return augendEvaluation + addendEvaluation;
    }

    @Override
    public double evaluate() throws Exception {
        return this.evaluate(Map.of());
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression assignedAugend = super.getFirst().assign(var, expression);
        Expression assignedAddend = super.getSecond().assign(var, expression);
        return new Plus(assignedAugend, assignedAddend);
    }

    @Override
    public String toString() {
        return "(" + super.getFirst().toString() + " + " + super.getSecond().toString() + ")";
    }

    @Override
    public Expression differentiate(String var) {
        Expression augendDerivative = super.getFirst().differentiate(var);
        Expression addendDerivative = super.getSecond().differentiate(var);
        return new Plus(augendDerivative, addendDerivative);
    }

    @Override
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception exception) {
            Expression simplifiedAugend = super.getFirst().simplify();
            Expression simplifiedAddend = super.getSecond().simplify();
            if (simplifiedAugend.toString().equals("0.0")) {
                return simplifiedAddend;
            }
            if (simplifiedAddend.toString().equals("0.0")) {
                return simplifiedAugend;
            }
            return new Plus(simplifiedAugend, simplifiedAddend);
        }
    }
}
