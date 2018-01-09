package rainer_sieberer;

public class SigmoidFunction implements ActivationFunction
{

	private static final float E = 2.718281828459045f;

	public float use ( float value )
	{
		return 1/(1+(float)Math.pow(E,-value));

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
