package assignment9_int;

import java.io.File;

/** Interface for assignment 9 */
public interface Assignment9 {
   
   /** loads the .csv file with the training data or throws an Exception if anything goes wrong; returns true iff the initialization completed successfully. */
   public boolean init(File csvTrainingData) throws Exception;
   
   /** trains the net; returns true iff the training phase completed successfully. */
   public boolean train() throws Exception;
   
   /** tries to recognize the digit represented by csvString; returns the digit */
   public int recognize(String csvString) throws Exception;
}