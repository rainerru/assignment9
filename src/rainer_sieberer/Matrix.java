package rainer_sieberer;

import java.io.PrintWriter;
import java.util.Random;

public class Matrix
{

	protected double[][] data;
	protected int xDim;
	protected int yDim;
	
	public Matrix ( int xDim, int yDim )
	{
		this.xDim = xDim;
		this.yDim = yDim;
		this.data = new double[xDim][yDim];
	}
	
	public int getXDim () { return this.xDim; }
	
	public int getYDim () { return this.yDim; }
	
	public double getValue ( int i, int j ) { return this.data[i][j]; }
	
	public void setValue ( int i, int j, double value ) { this.data[i][j] = value; }
	
	public void addValue ( int i, int j, double value ) { this.data[i][j] += value; }

	public void randomize ()
	{
		Random gaussMaker = new Random();
		double upperBound = Math.pow(this.xDim,-0.5);
		for ( int i = 0; i < this.xDim; i++ )
			for ( int j = 0; j < this.yDim; j++ )
				this.data[i][j] = gaussMaker.nextGaussian()*upperBound;
	}
	
	public Matrix useActivationFunction ( ActivationFunction activate )
	{
		Matrix result = new Matrix(xDim,yDim);
		for ( int i = 0; i < xDim; i++ )
			for ( int j = 0; j < yDim; j++ )
				result.setValue(i,j, activate.use( this.data[i][j] ) );
		return result;
	}
	
	public int getIndexWithMax () // only checks 1 dim
	{
		int index = 0;
		double maxValue = this.data[0][0];
		for ( int i = 1; i < xDim; i++ ) // xDim or yDim?
		{
			if ( maxValue < this.data[i][0] )
			{
				maxValue = this.data[i][0];
				index = i;
			}
		}
		return index;
	}

	public Matrix matrixProduct ( Matrix B ) throws Exception
	{
		int zDim = B.getYDim();
		if ( this.yDim != B.getXDim() )
			throw new Exception("invalid matrix dimensions"); // which one?
		Matrix result = new Matrix( this.xDim,zDim );
		for ( int i = 0; i < this.xDim; i++ )
			for ( int j = 0; j < zDim; j++ )
				for ( int k = 0; k < this.yDim; k++ )
				{
					result.data[i][j] += this.data[i][k] * B.data[k][j];
					//if ( this.data[i][k] < 0.0 )
						// System.out.print("alert" + i + "," + k + " ");
				}
		return result;
	}
	
	public Matrix subtract ( Matrix B ) throws Exception
	{
		if ( this.xDim != B.getXDim() || this.yDim != B.getYDim() )
			throw new Exception("invalid matrix dimensions"); // which one?
		Matrix result = new Matrix( this.xDim, this.yDim );
		for ( int i = 0; i < this.xDim; i++ )
			for ( int j = 0; j < this.yDim; j++ )
				result.data[i][j] = this.data[i][j] - B.data[i][j];
				//result.setValue(i,j, this.data[i][j] - B.getValue(i,j) );
		return result;
	}
	
	public Matrix add ( Matrix B ) throws Exception
	{
		if ( this.xDim != B.getXDim() || this.yDim != B.getYDim() )
			throw new Exception("invalid matrix dimensions"); // which one?
		Matrix result = new Matrix( this.xDim, this.yDim );
		for ( int i = 0; i < xDim; i++ )
			for ( int j = 0; j < yDim; j++ )
				result.setValue(i,j, this.data[i][j] + B.getValue(i,j) );
		return result;
	}

	public void weightUpdate( Matrix A, Matrix B, Matrix C, double learningRate ) throws Exception
	{
		/*if ( this.xDim != A.getXDim() || this.xDim != B.getXDim() || this.yDim != C.getXDim() ||
			 this.yDim != C.getXDim() || A.getYDim() != B.getYDim() || A.getYDim != C.getYDim() ) // and this!
			throw new Exception("invalid matrix dimensions"); // which one? IllegalDimensionException?*/
		Matrix leftMatrix = new Matrix( A.getXDim(), A.getYDim() );
		for ( int i = 0; i < A.getXDim(); i++ )
			for ( int j = 0; j < A.getYDim(); j++ )
				leftMatrix.data[i][j] = learningRate * A.data[i][j] * B.data[i][j] * ( 1.0 - B.data[i][j] );

		Matrix rightMatrix = C.getTransposed();
		Matrix prod = leftMatrix.matrixProduct( rightMatrix );
		
		leftMatrix.printToFile("./leftMatrix.txt");
		rightMatrix.printToFile("./rightMatrix.txt");
		prod.printToFile("./prod.txt");
		
		//prod.printToFile("./prod.txt");
		for ( int i = 0; i < this.xDim; i++ )
			for ( int j = 0; j < this.yDim; j++ )
				this.data[i][j] += prod.data[i][j];
				
/*				self.who += self.lr * numpy.dot((output_errors * final_outputs * (1.0 - final_outputs)), numpy.transpose(hidden_outputs))\n",
    "        \n",
    "        # update the weights for the links between the input and hidden layers\n",
    "        self.wih += self.lr * numpy.dot((hidden_errors * hidden_outputs * (1.0 - hidden_outputs)), numpy.transpose(inputs))\n", */
	}
	
	public Matrix getTransposed ()
	{
		Matrix result = new Matrix( this.yDim, this.xDim );
		for ( int i = 0; i < this.xDim; i++ )
			for ( int j = 0; j < this.yDim; j++ )
				result.setValue(j,i, this.data[i][j] );
		return result;
	}
	
	public void print ()
	{
		for ( int i = 0; i < this.xDim; i++ )
		{
			for ( int j = 0; j < this.yDim; j++ )
			{
				System.out.print(this.data[i][j] + ", ");
			}
			System.out.println();
		}
	}
	
	public void printToFile ( String name )
	{
		try
		{
			PrintWriter writer = new PrintWriter( name, "UTF-8");
			writer.println("begin");
			for ( int i = 0; i < this.xDim; i++ )
			{
				for ( int j = 0; j < this.yDim; j++ )
				{
					writer.print(this.getValue(i,j) + ", ");
				}
				writer.println();
			}
			writer.println("end");
			writer.close();
		} catch ( Exception e ) { System.out.println("error while printing to file");}
	}
	
}
