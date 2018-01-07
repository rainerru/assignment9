package rainer_sieberer;

import java.io.File;

public interface CSVInterpreter
{

	public Matrix getInputs ( File csvTrainingData ) throws Exception;
	
	public Matrix getTargets ( File csvTrainingData ) throws Exception;

	public Matrix convertToTranposedMatrix ( String csvString ) throws Exception;

}