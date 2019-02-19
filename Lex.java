
/*
 * Author: Surya Keswani 
 * Contact: suryakeswani@gmail.com
 * School: University of California, Santa Cruz
 * Date: August 2018
 */
import java.io.*;
import java.util.Scanner;

public class Lex {

	public static void main(String[] args) throws IOException {
		Scanner in; // for in file to get the number of lines
		Scanner in2; // for in file to add lines to token string array
		PrintWriter out = null; // for out file
		String[] token; // tokens are each file line in an index of the array
		int lineNumber = 0; // counter to keep track of the number of lines in the in file
		List sorted = new List(); // list that indirectly sorts the token array

		if (args.length != 2) { // if 2 arguments are not passed then an error is thrown
			System.err.println("Lex Error: FileIO infile outfile");
			System.exit(1);
		}

		in = new Scanner(new File(args[0])); // in file scanner for line numbers
		in2 = new Scanner(new File(args[0])); // in2 is to add the input file contexts line by line to token array
		out = new PrintWriter(new FileWriter(args[1])); // out file scanner

		while (in2.hasNextLine()) { // find the number of lines in the file
			lineNumber++;
			in2.nextLine();
		}

		token = new String[lineNumber]; // make a string array with the line numbers in the file
		for (int i = 0; i < lineNumber; i++) {
			token[i] = in.nextLine();
		}

		sorted.append(0);
		for (int i = 1; i < token.length; i++) {
			sorted.moveFront();
			boolean done = false;
			if (token[i].compareTo(token[sorted.back()]) > 0) {
				sorted.append(i);
			} else if (token[i].compareTo(token[sorted.front()]) < 0) {
				sorted.prepend(i);
			} else {
				while (done == false) {
					if (token[i].compareTo(token[sorted.get()]) <= 0) {
						sorted.insertBefore(i);
						done = true;
					} else {
						sorted.moveNext();
					}
				}
			}
		}

		sorted.moveFront(); // move the cursor to the front of the list
		for (int i = 0; i < sorted.length(); i++) { // iterate through the list
			out.println(token[sorted.get()]); // print out each element of the list to the output file
			sorted.moveNext(); // move the cursor over
		}

		in.close();
		in2.close();
		out.close();
	}
}