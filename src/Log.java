import java.util.Map;

/**
 * Represents a logarithmic expression.
 */
public class Log extends BinaryExpression implements Expression {
    /**
     * Constructs a new Log with the given base and anti-logarithm.
     * @param base An Expression representing the new Log base
     * @param antiLog An Expression representing the new Log anti-logarithm
     */
    public Log(Expression base, Expression antiLog) {
        super(base, antiLog);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double baseEvaluation = super.getFirst().evaluate(assignment);
        double antiLogEvaluation = super.getSecond().evaluate(assignment);
        return Math.log(antiLogEvaluation) / Math.log(baseEvaluation);
    }

    @Override
    public double evaluate() throws Exception {
        return this.evaluate(Map.of());
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression assignedBase = super.getFirst().assign(var, expression);
        Expression assignedAntiLog = super.getSecond().assign(var, expression);
        return new Log(assignedBase, assignedAntiLog);
    }

    @Override
    public Expression differentiate(String var) {
        Expression antiLog = super.getSecond();
        Expression oneDividedByAntiLog = new Div(new Num(1.0), antiLog);
        Expression naturalLogOfBase = Log.naturalLogOf(super.getFirst());
        Expression antiLogDerivative = antiLog.differentiate(var);
        return new Mult(new Mult(oneDividedByAntiLog, naturalLogOfBase), antiLogDerivative);
    }

    @Override
    public String toString() {
        return "log(" + super.getFirst().toString() + ", " + super.getSecond().toString() + ")";
    }

    /**
     * Returns the natural logarithm of the given expression.
     * @param expression An Expression
     * @return An Expression representing the natural logarithm of the given expression
     */
    public static Log naturalLogOf(Expression expression) {
        return new Log(new Var("e"), expression);
    }

    @Override
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception exception) {
            Expression simplifiedBase = super.getFirst().simplify();
            Expression simplifiedAntiLog = super.getSecond().simplify();
            if (simplifiedBase.toString().equals(simplifiedAntiLog.toString())) {
                return new Num(1.0);
            }
            return new Log(simplifiedBase, simplifiedAntiLog);
        }
    }
}
