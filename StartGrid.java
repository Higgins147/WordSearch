/**
 * This is a WordSearch puzzle. The program will take a txt file of words
 * and produce a grid with all words contained inside. Methods below will
 * manipulate the words so they are placed into the grid forwards, backwards
 * horizontal and vertical. Remaining places in the grid are filled with random
 * letters
 * 
 * @author Tracy Walsh
 * @author John Coleman
 * @version 21
 * @date 2016-03-23
 * @link http://youtu.be/6TaoJHPIKxY?hd=1
 * 
 **/

package WordSearchPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.io.File;

public class StartGrid {

		
	public static void main(String[] args) {
		

		WordSearch wSearch = new WordSearch();
		
		String fileIn = JOptionPane.showInputDialog(null,"Enter Input File: ","Create your own word search",JOptionPane.INFORMATION_MESSAGE);
	    String gridOut = JOptionPane.showInputDialog(null,"Enter Grid Output File: ","Create your own word search",JOptionPane.INFORMATION_MESSAGE);	 
	    String solutionOut = JOptionPane.showInputDialog(null,"Enter Solution Output File: ","Create your own word search",JOptionPane.INFORMATION_MESSAGE);

				
		try {
			inputWords(wSearch, fileIn);    // Input list of words from text file
						
			wSearch.fillGrid();   // Initialise and fill the grid with words and random letters
			
			outputGrid(wSearch, wSearch.wordList.size(), gridOut);    // Output wordsearch grid to file
		
			outputSolution(wSearch, solutionOut);					 // Output solution to file
			
			JOptionPane.showMessageDialog(null,"Your WordSearch has been successfully created\n","Create your own word search",JOptionPane.INFORMATION_MESSAGE);
		
			Desktop.getDesktop().open(new File(gridOut));
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Error: " + e.getMessage(),"Create your own word search",JOptionPane.INFORMATION_MESSAGE);
		}
		finally {
			System.out.println("done");
		}		
	}
	
/**
 * Input a list of words from a selected text file	
 */
	
	static void inputWords(WordSearch wSearch, String inFile) throws IOException {
		Scanner input = new Scanner(new BufferedReader(new FileReader(inFile)));

		while (input.hasNext()) {
			wSearch.wordList.add(input.next().toLowerCase());
		}		

		input.close();
	}
	

/**
 * Output wordsearch solution to selected file.
 */		
	static void outputSolution(WordSearch grid, String outFile) throws IOException {		
		
		BufferedWriter output = new BufferedWriter(new FileWriter(outFile));
				
		while(!grid.sol.isEmptyStack()){
			Solution s1 = grid.sol.removeFromStack();
			output.write(s1.word + " is at position [" + s1.posX + "," + s1.posY+"]\r\n");
		}	
		output.close();
	}	
	
/**
 * Output wordsearch grid to selected file.
 */
	static void outputGrid(WordSearch wSearch, int numOfWords, String outFile) throws IOException {
		
		BufferedWriter output = new BufferedWriter(new FileWriter(outFile));
				
		output.write("Can you find the " + numOfWords + " words hidden in the grid below?");
		output.write("\r\n");
		output.write("\r\n");

		for (int i = 0; i < wSearch.grid.length; i++) {
			for (int j = 0; j < wSearch.grid[i].length; j++){
				output.write(wSearch.grid[i][j] + "  ");
			}
			output.write("\r\n");
		}
		
		
		output.write("\r\n");
		
		Collections.sort(wSearch.wordList);
		
		output.write("Hidden Words: \r\n\r\n");
		
		for (String word :wSearch.wordList) {
				output.write(word + "   ");					
		}
		
		output.close();

	}	
}