# A9_starter

Assignment 9: Digit Recognition (machine learning)
--------------------------------------------------

This assignment is worth 2 regular assignments. It consists of 2 sub-tasks with different deadlines, but it is an all-or-nothing assignment. 

The assignment is about porting and reengineering a program for classifying hand-written digits using a machine learning approach. The original program is available at [1] (as a python notebook). It uses the MNIST database [2] containing 60,000 samples. A CSV-representation of the data is available at [3].

**Part A) Background** 

Get familiar with the python program for recognizing hand-written digits (discussed on Jan. 9/10).
Be sure to understand the theoretical background (ANN, backpropagation, ...) as well the python code.
You should be able to present the python program and answer questions.

*One way to run the python program on Windows/Mac (in a VM):*

0) get and install Docker [4]: https://www.docker.com/docker-windows or https://www.docker.com/docker-mac

1) download the material from [1] to a new directory (e.g., C:\DOCKER\sweng-ws17)

2) install the anaconda environment (includes python, required libs) (see [5] for details)
(run from the shell; don't use the git-bash):

`docker pull continuumio/anaconda`

3) run the docker container (see [4,5] for details). For me the follwoing worked:

`docker run -i -v "C:\DOCKER\sweng-ws17":/home -t -p 8888:8888 continuumio/anaconda /bin/bash -c "/opt/conda/bin/conda install jupyter -y --quiet && mkdir /opt/notebooks && /opt/conda/bin/jupyter notebook --notebook-dir=/home --ip='*' --port=8888 --no-browser --allow-root"`

this will start the container with a directory mapping from the local-directory to the vm.
After a while, it will prompt an url, e.g. `http://localhost:8888/?token=314xxx.....`

4) open the url in a browser and click on a `.ipynb`-file.

5) to run the notebook click Cell->Run All; Note: you have to adjust the names of the .csv file (in the code or the file names themselves; e.g. `mnist_train_100.csv` vs. `mnist_train.csv`)

**Part B) Implementation** 

Port this functionality to Java (discussed on Jan. 16/17)

Your program shall be in Java and must not require any library that is not part of the JDK.

As part of your reengineering effort, make sure that your approach follows a well-structured OO design.
Avoid code duplication where ever possible.
Follow the Open-Closed-Principle. Just like for Assignment 8, separate problem-specific aspects (digit recognition) from problem-independent (ANN) code.

Your code shall further allow the activation function (e.g., sigmoid) to be easily changed. Make it configurable (see dependency injection).

Implement the following interface and make sure that the implementing class works with the default constructor:

```java
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
```

The format of the csvString in the `recognize` method is the same as for the training data (without the label). Thus it is a string of 28x28=784 values separated by commas and it represents a single digit.

Note: Since your program will be based on someone else's code, give credit where credit is due (mention the original author and put the link to his site in your java doc).

[1] https://github.com/makeyourownneuralnetwork/makeyourownneuralnetwork 

[2] http://yann.lecun.com/exdb/mnist/ 

[3] https://pjreddie.com/projects/mnist-in-csv/ 

[4] https://www.docker.com/

[5] https://hub.docker.com/r/continuumio/anaconda/
