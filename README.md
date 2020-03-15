Project 1
Java Infix Expression Evaluator

Taylor Artunian
CSC413-05
Spring 2020

Overview
Introduction
Infix algebraic notation is the most commonly used mathematical notation in the world. When writing an expression in infix notation, mathematical operators are placed between operands, such as in 1+2, x^2 and so on. The goal of this project was to create a set of Java classes which could parse and evaluate infix algebraic expressions with constant integer terms, inputted as a string of characters. Additionally, both a command-line program and calculator user interface are provided to test the package’s functionality.

Summary
To implement an algorithm that converts a string into a symbolic representation of an algebraic expression, several classes of tokens were created. Here, tokens refers to either integer numbers or the mathematical operators “+-*/^()” found in the input string. Each token in the string is converted to either an Operand or an Operator object and placed on one of two corresponding stacks in the order in which it appears. As each token is processed, order of operations rules are applied to simplify the expression when possible. Once the whole string has been processed, one final pass through the remaining elements on the stacks is done to complete the calculation.

Development
Development was done with the IntelliJ IDEA Community edition IDE and built for JDK 13.0.2.

Features
Extensible abstract Operator class with utility methods for token verification	
Operator subclasses for most common operators “+-*/^()”	
Command-line program and calculator UI for testing	
 
Instructions
The following commands are used for compiling and executing the project. Replace {output_directory} with the relative path to the directory for compiled .class files. Omitting this output directory will result in compiled classes being placed in the current working directory. Likewise, when executing the program, omitting the -cp flag will look for the compiled file in the current directory.

Compilation
javac *.java -d {output_directory}

Execution
java -cp {output_directory} EvaluatorTester “{expression_1}” “{expression_2}” …
java -cp {output_directory} EvaluatorUI
Note: When running EvaluatorTester from the command-line, expressions should be enclosed in quotes as certain characters are otherwise removed by the operating system.

Assumptions
Input
•	Expressions may contain the characters 0-9 and +-*/^() and spaces (spaces will be ignored).
•	Any number of expressions may be used as command-line arguments with EvaluatorTester and should be separated by a space.
•	Command-line arguments should be enclosed in quotes.
Expressions
•	Expressions should not contain implicit multiplication of parentheses e.g. (1+2)(3+4).

 
Implementation Discussion
Class Diagrams and Implemenation Decisions
Model Package
 
FIGURE 1 - MODEL PACKAGE
Coding the project began with creating the model to represent the logic of an expression evaluator. The two types of symbols in a mathematical expression are operators and operands. Operators refer to the symbols +, -, *, /, ^, ( and ) while operands are integer numbers made of the digits 0-9. Implementing the Operand class was straightforward because it mainly needed to store an integer value. An accessor was added for this field in addition to a static helper method isOperand, which attempts to parse the String parameter to an Integer. If it succeeds, it returns true and false otherwise.
The Operator class was slightly more complex because each type of operator introduces different functionality. Like the Operand class, it has a static helper method isOperator which returns whether the static HashMap, operators, contains the parameter in its keys. This HashMap maps the character representation of the Operator to an instance of its corresponding Operator subclass. Each of the seven characters listed above have their own subclass of Operator: AdditionOperator, SubtractionOperator, MultiplicationOperator, DivisionOperator, ExponentialOperator, OpenParenthesisOperator and CloseParenthesisOperator. Additionally, there is one more class, InitOperator, which is used to indicate the beginning of the expression in our algorithm. 
Evaluator Algorithm
This package is driven by the Evaluator class which has one public instance method, evaluate. Before this method is called, the Evaluator class initializes two stacks, operatorStack and operandStack, to hold the object representations of the expression. After initializing the stacks, an instance of the InitOperator class is pushed onto the operatorStack. Once the Evaluator is initialized, it is ready to evaluate expressions.
The evaluate method begins the process of converting the string representation of the expression into something more meaningful. It takes in a single String argument, expression, and splits it into tokens, using the characters in the DELIMITERS field to separate them (these are just the standard math operators and spaces). It is important to note that the last parameter in the StringTokenizer tells it whether to return each delimiter as a token which in our case it will. From here, each token in the string is processed by the handleToken method and is transformed into its corresponding object type: Operator or Operand. This is determined by the isOperator and isOperand static methods of each respective class. In the case that the token represents an Operator, the correct subclass is determined by the getByToken static method which returns a reusable instance of the subclass from the operators HashMap. From here, Operators are pushed onto the operatorStack and Operands are pushed onto the operandStack. Before each Operator is pushed onto the stack, its priority field is compared to that of previous Operators and executes those of greater priority first.
Once every token has been processed and some operations executed, one last pass through the stacks is carried out by the complete method which returns the result.
UI Package
 
FIGURE 2 - UI PACKAGE
To implement user interaction with the calculator, I started by creating an abstract CalculatorButton class which has a single abstract method, performAction and which takes in a TextField argument. This was done to allow each button to modify the main TextField of EvaulatorUI when it is clicked while minimizing its dependencies. Clicks on the buttons are handled by the EventManager class which also holds a reference to the main TextField. This way, the EventManager can pass this reference to the button and call the button’s performAction method, allowing it to update the TextField.
Subclasses of CalculatorButton were also created for the different types of buttons. SymbolButton is used for operators and digits while the other buttons ( =, CE, C ) have their own subclasses.
 
FIGURE 3 - DEFAULT PACKAGE
The above Figure 3 - Default Package shows the default package which contains the UI and Model packages, as well as the Evaluator, EvaluatorUI and EvaluatorTester classes. 
Code Organization
I chose to organize the project into two packages, Model and UI, because the components placed inside each of those packages serve similar purposes. This was also done because the contents of the Model package could be reused in another project and are not dependent on anything outside of it.
Future Considerations
	While I am happy with the result of this project, several features could be added to improve the functionality and reliability of the calculator. Some of the things I would add in the future include:
•	Create a Regular Expression to match only proper mathematical expressions in the Evaluator class.
•	Implement support for Double typed operands. 
•	Add support for implicit multiplication of parenthesis.
•	Handle input on a click by click basis the way that real calculators do.
o	Display an intermediate result.
o	Show a history of inputs.
•	Convert the project to an MVC or MVVM pattern. (Possible implementation included as Figure 4 - MVVM Implementation in the Appendix)
Conclusions
	I enjoyed this project very much as it helped me to get reacquainted with the Java language as well as design patterns for object-oriented programming and UI development. It was also a very thorough exercise in understanding the algorithm that calculators use for solving problems. 
This project also showed me the value of designing a program in UML before implementing it in code. Diagramming the solution beforehand allowed me to try new ideas and scrap them on the fly which is harder to do when attempting a code-first method. Diagramming also shows the interrelation of modules and big picture that is not obvious when working strictly with code. In addition, creating the UI and working with UML diagrams reminded me that the separation of business-logic from the UI has been a common pitfall for me in the past.
While the UI solution which was provided to us works (EventManager implementation), if the program were any larger it might not be the right solution. Trying to improve this solution led me to rediscover the MVC and MVVM patterns. The ViewModel concept seemed somewhat robust for an application that has only one display field, but it was helpful to learn some of the Java libraries/classes (java.lang.Runnable, java.util.function.* and java.beans.PropertyChangeSupport) which helped with implementing the Command and Property Binding patterns.
Overall this was a great project and learning experience and I look forward to future projects because of the concepts I have learned. 
Appendix
 
FIGURE 4 - MVVM IMPLEMENTATION
=======
# CSC413 Lab 1
## Infix Expression Evaluator and UI
