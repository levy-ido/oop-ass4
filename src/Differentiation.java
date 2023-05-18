/**
 * A class containing auxiliary methods for differentiation.
 */
public class Differentiation {
    /**
     * Returns the derivative of f relative to var multiplied by g.
     * @param f An Expression representing the expression to differentiate
     * @param g An Expression representing the new Mult multiplicand
     * @param var A String representing a variable name
     * @return An Expression representing the derivative of f relative to var multiplied by g
     */
    public static Expression derivativeOfFMultipliedByG(Expression f, Expression g, String var) {
        Expression fDerivative = f.differentiate(var);
        return new Mult(fDerivative, g);
    }
}