package rainer_sieberer;

public class SigmoidFunction implements ActivationFunction
{

	private static final double E = 2.718281828459045;

	public double use ( double value )
	{
		return 1/(1+Math.pow(E,-value));

		/*double x = value;
		if (x < 0) {
        x = Math.pow(E,x);
        return x / (1 + x);
    }
    else {
        return 1 / (1 + Math.pow(E,-x));
    }*/
	}

}
