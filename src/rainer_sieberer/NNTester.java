package rainer_sieberer;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class NNTester
{

	public static void main ( String[] args )
	{
	
		Matrix a = new Matrix(1,2);
		Matrix b = new Matrix(2,3);
		a.setValue(0,0,0);
		a.setValue(0,1,0);
		b.setValue(0,0,0.01);
		b.setValue(0,1,0.01);
		b.setValue(0,2,0.01);
		b.setValue(1,0,0.01);
		b.setValue(1,1,0.01);
		b.setValue(1,2,0.01);
		
		/*CSVInterpreter csvInterpreter0 = new CSVDigitInterpreter();
		try{
		Matrix wih = new Matrix(200,784);
		Matrix inputs = csvInterpreter0.getInputs( new File("./mnist_train_100.csv")  );
		Matrix hiddenInputs = wih.matrixProduct( inputs );
		hiddenInputs.printToFile("./1hiddenInputs.txt");
		} catch ( Exception e) {}
		*/
		
		try{
		Matrix c = a.matrixProduct(b);
		c.print();
		} catch ( Exception e) {}
		
		NeuralNetwork nn = new NeuralNetwork();
		System.out.println("Neural Network created!");
		CSVInterpreter csvInterpreter = new CSVDigitInterpreter();
		
		try{
		if ( nn.init( new File("./mnist_train_100.csv")) )
		{
			System.out.println("Initialization succesful!");
			if ( nn.train() )
			{
				System.out.println("Training succesful!");
				
				BufferedReader br = new BufferedReader( new FileReader( new File("./mnist_test_10.csv")) );
				String line = br.readLine();
				line = line.substring(2);
				
				System.out.println("Testing: should output a 7");
				System.out.println( nn.recognize(line) );
				
				/*line = br.readLine();
				line = line.substring(2);
				
				System.out.println("Testing: should output a 2");
				System.out.println( nn.recognize(line) );
				
				line = br.readLine();
				line = line.substring(2);
				
				System.out.println("Testing: should output a 1");
				System.out.println( nn.recognize(line) );
				
				line = br.readLine();
				line = line.substring(2);
				
				System.out.println("Testing: should output a 0");
				System.out.println( nn.recognize(line) );*/
				
				}
				
				
				
		}
		} catch ( Exception e ) {
			System.out.println("exception occurrred");
			e.printStackTrace();
		}
		
	}

}