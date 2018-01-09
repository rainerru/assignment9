package rainer_sieberer;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class NNTester
{

	public static void main ( String[] args )
	{
/*
		Matrix A = new Matrix(2,2);
		Matrix B = new Matrix(2,2);
		Matrix C = new Matrix(2,2);
		A.setValue(0,0,1);
		A.setValue(1,0,2);
		A.setValue(0,1,3);
		A.setValue(1,1,4);
		B.setValue(0,0,5);
		B.setValue(1,0,6);
		B.setValue(0,1,7);
		B.setValue(1,1,8);
		C.setValue(0,0,10);
		C.setValue(1,0,100);
		C.setValue(0,1,1000);
		C.setValue(1,1,10000);
	
		Matrix D = new Matrix(2,2);
		D.setValue(0,0,0.1);
		D.setValue(1,0,0.2);
		D.setValue(0,1,0.3);
		D.setValue(1,1,0.4);
try{
		D.weightUpdate(A,B,C,0.1);
} catch ( Exception e ) {
			System.out.println("exception occurrred");
			e.printStackTrace();
		}
		D.print();

		ActivationFunction S = new SigmoidFunction();
		System.out.println("S(-10) = " + S.use(-10));
		System.out.println("S(-1) = " + S.use(-1));
		System.out.println("S(0) = " + S.use(0));
		System.out.println("S(1) = " + S.use(1));
		System.out.println("S(10) = " + S.use(10));
		System.out.println("S(2.7) = " + S.use(2.7));
		
		double E = 3.14159265358979323846;
		System.out.println("e(-10) = " + Math.pow(E,-10));
		System.out.println("e(-1) = " + Math.pow(E,-1));
		System.out.println("e(0) = " + Math.pow(E,0));
		*/


		NeuralNetwork nn = new NeuralNetwork();
		System.out.println("Neural Network created!");
		CSVInterpreter csvInterpreter = new CSVDigitInterpreter();
		
		try{
		if ( nn.init( new File("../data/mnist_train_100.csv")) )
		{
			System.out.println("Initialization succesful!");
			if ( nn.train() )
			{
				System.out.println("Training succesful!");
				
				BufferedReader br = new BufferedReader( new FileReader( new File("../data/mnist_test_10.csv")) );
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
