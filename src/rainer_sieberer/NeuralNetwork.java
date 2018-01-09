package rainer_sieberer;

import assignment9_int.Assignment9;

import java.io.File;

/**
 *
 * Source: Tariq Rashid, 2016, license: GPLv2
 *
 */
public class NeuralNetwork implements Assignment9
{

	protected int iNodes, hNodes, oNodes;
	protected Matrix wih, who;
	protected float learningRate;
	protected ActivationFunction activate;
	protected CSVInterpreter csvInterpreter;
	protected int numberOfTrainingRuns;
	
	protected boolean initialized;
	
	protected Matrix inputs, targets;

	public NeuralNetwork ()
	{
		this.initialized = false;
		this.iNodes = 784;
		this.hNodes = 200;
		this.oNodes = 10;
		this.learningRate = 0.1f;
		this.wih = new Matrix(hNodes,iNodes);
		this.who = new Matrix(oNodes,hNodes);
		wih.randomize();
		who.randomize();
		this.setActivationFunction( new SigmoidFunction() );
		csvInterpreter = new CSVDigitInterpreter();
		this.numberOfTrainingRuns = 10;
	}

	public void setActivationFunction ( ActivationFunction newActivationFunction )
	{
		this.activate = newActivationFunction;
	}

	public boolean init( File csvTrainingData ) throws Exception
	{
		this.inputs = csvInterpreter.getInputs( csvTrainingData );
		this.targets = csvInterpreter.getTargets( csvTrainingData );
		
		this.initialized = true;
		return true; // how and when return false?
	}

	public boolean train() throws Exception
	{
		if ( !this.initialized )
			throw new Exception();
		
		this.who.printToFile("./0who.txt");
		this.wih.printToFile("./0wih.txt");
		// Matrix hiddenInputs, hiddenOutputs, finalInputs, finalOutputs, outputErrors, whoTransposed, hiddenErrors;
		Matrix hiddenInputs = new Matrix(0,0), hiddenOutputs = new Matrix(0,0), finalInputs = new Matrix(0,0), finalOutputs = new Matrix(0,0), outputErrors = new Matrix(0,0), whoTransposed = new Matrix(0,0), hiddenErrors = new Matrix(0,0);
		hiddenInputs.printToFile("./1hiddenInputsBEFOREACTIVATION.txt");
		
		for ( int rep = 0; rep < this.numberOfTrainingRuns; rep++ )
		{
			hiddenInputs = this.wih.matrixProduct( this.inputs );
			hiddenOutputs = hiddenInputs.useActivationFunction( this.activate );
			
			finalInputs = this.who.matrixProduct( hiddenOutputs );
			finalOutputs = finalInputs.useActivationFunction( this.activate );
			
			outputErrors = targets.subtract( finalOutputs );
			whoTransposed = this.who.getTransposed();
			hiddenErrors = whoTransposed.matrixProduct( outputErrors );
			
			this.who.weightUpdate( outputErrors, finalOutputs, hiddenOutputs, learningRate );
			this.wih.weightUpdate( hiddenErrors, hiddenOutputs, inputs, learningRate );
		}
		inputs.printToFile("./0inputs.txt");
		targets.printToFile("./0targets.txt");
		hiddenInputs.printToFile("./1hiddenInputs.txt");
		hiddenOutputs.printToFile("./2hiddenOutputs.txt");
		finalInputs.printToFile("./3finalInputs.txt");
		finalOutputs.printToFile("./4finalOutputs.txt");
		outputErrors.printToFile("./5outputErrors.txt");
		whoTransposed.printToFile("./6whoTransposed.txt");
		hiddenErrors.printToFile("./7hiddenErrors.txt");
		who.printToFile("./8who.txt");
		wih.printToFile("./9wih.txt");
		
		return true;
	}

	public int recognize( String csvString ) throws Exception
	{
		if ( !this.initialized )
			throw new Exception();
		
		Matrix newInputs = csvInterpreter.convertToTranposedMatrix( csvString );
		Matrix hiddenInputs = this.wih.matrixProduct( newInputs );
		Matrix hiddenOutputs = hiddenInputs.useActivationFunction( this.activate );
		Matrix finalInputs = this.who.matrixProduct( hiddenOutputs );
		Matrix finalOutputs = finalInputs.useActivationFunction( this.activate );
		
		newInputs.printToFile("./TEST1newInputs.txt");
		hiddenInputs.printToFile("./TEST2hiddenInputs.txt");
		hiddenOutputs.printToFile("./TEST3hiddenOutputs.txt");
		finalInputs.printToFile("./TEST4finalInputs.txt");
		finalOutputs.printToFile("./TEST4finalOutputs.txt");
		finalOutputs.print();
		
		return finalOutputs.getIndexWithMax();
	}

}
