import java.util.Map;

/**
 * Represents a logarithmic expression.
 */
public class Log extends BinaryExpression implements Expression {
    /**
     * Constructs a new Log with the given base and anti-logarithm.
     * @param base An Expression representing the new Log's base
     * @param antiLog An Expression representing the new Log's anti-logarithm
     */
    public Log(Expression base, Expression antiLog) {
        super(base, antiLog);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double evaluatedBase = super.getFirst().evaluate(assignment);
        double evaluatedAntiLog = super.getSecond().evaluate(assignment);
        return Math.log(evaluatedAntiLog) / Math.log(evaluatedBase);
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
     * Returns the natural log of the given expression.
     * @param antiLog An Expression representing the anti-logarithm
     * @return An Expression representing the natural log of the given expression
     */
    public static Log naturalLogOf(Expression antiLog) {
        return new Log(new Var("e"), antiLog);
    }

    @Override
    public Expression simplifyAssumingThereAreVariables() {
        Expression simplifiedBase = super.getFirst().simplify();
        Expression simplifiedAntiLog = super.getSecond().simplify();
        if (simplifiedBase.toString().equals(simplifiedAntiLog.toString())) {
            return new Num(1.0);
        }
        return new Log(simplifiedBase, simplifiedAntiLog);
    }
}
