package rainer_sieberer;

public class SigmoidFunction implements ActivationFunction
{

	private static final double E = 3.14159265358979323846;

	public double use ( double value )
	{
		return 1/(1+Math.pow(E,-value));
	}

}