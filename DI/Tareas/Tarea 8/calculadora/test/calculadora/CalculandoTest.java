/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import junit.framework.TestCase;

/**
 *
 * @author DANILOR
 */
public class CalculandoTest extends TestCase {
    
    public CalculandoTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of add method, of class Calculando.
     */
    public void testAdd() {
        System.out.println("add");
        double number1 = 4.0;
        double number2 = 3.0;
        Calculando instance = new Calculando();
        double expResult = 7.0;
        double result = instance.add(number1, number2);
        assertEquals(expResult, result, 7.0);

    }

    /**
     * Test of subtract method, of class Calculando.
     */
    public void testSubtract() {
        System.out.println("subtract");
        double number1 = 4.0;
        double number2 = 3.0;
        Calculando instance = new Calculando();
        double expResult = 1.0;
        double result = instance.subtract(number1, number2);
        assertEquals(expResult, result, 1.0);

    }

    /**
     * Test of multiply method, of class Calculando.
     */
    public void testMultiply() {
        System.out.println("multiply");
        double number1 = 4.0;
        double number2 = 3.0;
        Calculando instance = new Calculando();
        double expResult = 12.0;
        double result = instance.multiply(number1, number2);
        assertEquals(expResult, result, 12.0);

    }

    /**
     * Test of divide method, of class Calculando.
     */
    public void testDivide() {
        System.out.println("divide");
        double number1 = 9.0;
        double number2 = 3.0;
        Calculando instance = new Calculando();
        double expResult = 3.0;
        double result = instance.divide(number1, number2);
        assertEquals(expResult, result, 3.0);

    }
    
}
