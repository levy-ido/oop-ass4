/**
 * A class containing auxiliary methods for differentiation.
 */
public class Differentiation {
    /**
     * Returns f'g.
     * @param f An Expression representing the expression to differentiate
     * @param g An Expression representing the multiplicand
     * @param var A String representing the variable to differentiate relative to
     * @return An Expression representing f'g
     */
    public static Expression fDerivativeMultipliedByG(Expression f, Expression g, String var) {
        Expression fDerivative = f.differentiate(var);
        return new Mult(fDerivative, g);
    }
}