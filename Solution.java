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

import java.util.Stack;

/**
  * Class Solution to store words and their positions within the word search grid in a stack
  */


class Solution {

	public String word;
	public int posX;
	public int posY;
	
	public Stack <Solution> solutionStack = new Stack<Solution>();

	
/**
 * Add solution to solution stack
 */
	void addToStack(String word, int x, int y){
		Solution sol = new Solution();
		sol.word = word;
		sol.posX = x;
		sol.posY = y;
		solutionStack.push(sol);
	}
	
/**
 * Remove solution from stack	
 */
	Solution removeFromStack(){
		Solution s1 = solutionStack.pop();
		return s1;
	}
	
	
/**
 *  Check is stack is empty
 */
	boolean isEmptyStack(){
		if (solutionStack.empty()) return true;
		else return false;
	}

	
@Override                                       
	public String toString() {
		return word + " is at position [" + posX + "," + posY + "]";
	}
	
}

