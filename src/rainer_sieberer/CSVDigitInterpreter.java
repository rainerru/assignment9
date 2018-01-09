package rainer_sieberer;

import java.io.File;
import java.util.LinkedList;
//import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class CSVDigitInterpreter implements CSVInterpreter
{

	/*public Matrix getInputs ( File csvTrainingData ) throws Exception
	{
		LinkedList<LinkedList<Integer>> inputs = new LinkedList<LinkedList<Integer>>();
		
		Scanner scanner = new Scanner( csvTrainingData );
		while ( scanner.hasNextInt() )
		{
			System.out.println("scanned something!");
			scanner.nextInt(); // skip the first value, which is the target, not part of the input
			LinkedList<Integer> thisInput = new LinkedList<Integer>();
			inputs.add( thisInput );
			for ( int i = 0; i < 28; i++ )
				thisInput.add( scanner.nextInt() );
			scanner.nextLine();
		}
		
		System.out.println(inputs.size());
		System.out.println(inputs.element().size());
		
		Matrix result = new Matrix( inputs.element().size(), inputs.size() );
		for ( int j = 0; j < inputs.size(); j++ )
		{
			LinkedList<Integer> thisInput = inputs.getFirst();
			for ( int i = 0; i < inputs.element().size(); i++ )
				result.setValue(i,j, thisInput.getFirst() );
		}
		return result;
	}*/
	
	public Matrix getInputs ( File csvTrainingData ) throws Exception
	{
		LinkedList<String> lines = new LinkedList<String>();
		
		BufferedReader br = new BufferedReader( new FileReader ( csvTrainingData ) );
		while ( br.ready() )
			lines.add( br.readLine() );
		br.close();
		
		int size = lines.size();
		
		Matrix result = new Matrix( 784, size );
		for ( int j = 0; j < size; j++ )
		{
			String[] parts = lines.remove().split(",");
			for ( int i = 0; i < 784; i++ )
				result.setValue(i,j, Float.parseFloat(parts[i+1])/255*0.99f+0.01f ); // or Double.parseDouble ???
		}
		return result;
	}
	
	/*public Matrix getTargets ( File csvTrainingData ) throws Exception
	{
		LinkedList<Integer> targets = new LinkedList<Integer>();
		
		Scanner scanner = new Scanner( csvTrainingData );
		while ( scanner.hasNextInt() )
		{
			targets.add( scanner.nextInt() );
			scanner.nextLine();
		}
		
		Matrix result = new Matrix( targets.size(), 1 );
		for ( int i = 0; i < targets.size(); i++ )
			result.setValue(i,1, targets.getFirst() );
		return result;
	}*/
	
	public Matrix getTargets ( File csvTrainingData ) throws Exception
	{	
		LinkedList<String> lines = new LinkedList<String>();
		
		BufferedReader br = new BufferedReader( new FileReader ( csvTrainingData ) );
		while ( br.ready() )
			lines.add( br.readLine() );
		br.close();
		
		int size = lines.size();
		
		Matrix result = new Matrix( 10,size );
		for ( int i = 0; i < size; i++ )
		{
			for ( int j = 0; j < 10; j++ )
				result.setValue(j, i, 0.01f );
			String[] parts = lines.remove().split(",");
			result.setValue(Integer.parseInt(parts[0]),i ,  0.99f );
		}
		return result;
	}

	/* public Matrix convertToTranposedMatrix ( String csvString ) throws Exception
	{
		LinkedList<Integer> input = new LinkedList<Integer>();
		
		Scanner scanner = new Scanner( csvString );
		while ( scanner.hasNextInt() )
			input.add( scanner.nextInt() );
		
		Matrix result = new Matrix( input.size(), 1 );
		for ( int i = 0; i < input.size(); i++ )
		{
			result.setValue(i,1, input.getFirst() );
		}
		return result;
	} */
	
	public Matrix convertToTranposedMatrix ( String csvString ) throws Exception
	{
		String[] parts = csvString.split(",");
		Matrix result = new Matrix( 784, 1 );
		for ( int i = 0; i < 784; i++ )
			result.setValue(i,0, Float.parseFloat(parts[i])/255*0.99f+0.01f );
		return result;
	}

}
