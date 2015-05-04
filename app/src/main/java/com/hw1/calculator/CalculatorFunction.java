package com.hw1.calculator;

/**
 * Created by Mohammad on 4/27/2015.
 */
public class CalculatorFunction {

    private double mOperand;
    private double mWaitingOperand;
    private String mWaitingOperator;
    private double mCalculatorMemory;

    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String MULTIPLY = "X";
    public static final String DIVIDE = "/";

    public static final String CLEAR = "Del";
    public static final String SQUAREROOT = "√";
    public static final String SQUARED = "x²";
    public static final String INVERT = "1/x";
    public static final String TOGGLESIGN = "+/-";
    public static final String SIN = "sin";
    public static final String COS = "cos";
    public static final String TAN = "tan";

    public static final String FACTORIAL = "!";

    public CalculatorFunction() {
        mOperand = 0;
        mWaitingOperand = 0;
        mWaitingOperator = "";
        mCalculatorMemory = 0;
    }

    public void setOperand(double operand) {
        mOperand = operand;
    }

    public double getResult() {
        return mOperand;
    }

    public void setMemory(double calculatorMemory) {
        mCalculatorMemory = calculatorMemory;
    }

    public double getMemory() {
        return mCalculatorMemory;
    }

    public String toString() {
        return Double.toString(mOperand);
    }

    protected double performOperation(String operator) {

        System.out.println(operator);

        switch (operator) {
            case CLEAR:
                mOperand = 0;
                mWaitingOperator = "";
                mWaitingOperand = 0;
                mCalculatorMemory = 0;
                break;
            case SQUARED:
                mOperand = mOperand * mOperand;
                break;
            case SQUAREROOT:
                System.out.println(Math.sqrt(mOperand));
                mOperand = Math.sqrt(mOperand);
                break;
            case INVERT:
                if (mOperand != 0) {
                    mOperand = 1 / mOperand;
                }
                break;
            case SIN:
                System.out.println(Math.sin(Math.toRadians(mOperand)));
                mOperand = Math.sin(Math.toRadians(mOperand));
                break;
            case COS:
                mOperand = Math.cos(Math.toRadians(mOperand));
                System.out.println(Math.cos(Math.toRadians(mOperand)));
                break;
            case TAN:
                mOperand = Math.tan(Math.toRadians(mOperand));
                break;
            case FACTORIAL:
                mOperand = factorial(mOperand);
                break;
            default:
                performWaitingOperation();
                mWaitingOperator = operator;
                mWaitingOperand = mOperand;
                break;
        }

        return mOperand;
    }

    protected void performWaitingOperation() {

        switch (mWaitingOperator){
            case ADD:
                mOperand = mWaitingOperand + mOperand;
                break;
            case  SUBTRACT:
                mOperand = mWaitingOperand - mOperand;
                break;
            case MULTIPLY:
                mOperand = mWaitingOperand * mOperand;
                break;
            case DIVIDE:
                if (mOperand != 0) {
                    mOperand = mWaitingOperand / mOperand;
                    System.out.println(mOperand);
                }
                break;


        }// End of switch
    }

    protected double factorial(double n)
    {
        if(n<=1)
            return n;
        else
            return n*factorial(n-1);
    }

}