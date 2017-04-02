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

import java.util.*;

public class WordSearch {
	
	ArrayList<String> wordList = new ArrayList<String>();
	char [][] grid;
	Solution sol = new Solution();
	
/**
 * Create empty grid
 */	
	void gridInitialise(){
		int x, y;
		int longest = getMaxLength(wordList);
		int listSize = wordList.size();
		
		x = y = longest + listSize;
		
		grid = new char [x][y];
	}

/**
 * Find the longest word in the input file
 */
	private int getMaxLength(ArrayList<String> words){
		int max = 0;
		for(String word : words){
			if(max < word.length()){
				max = word.length();
			}
		}
		return max;
	}

/**
 * Create random letter
 */
	char pickLetter(){
		int letter = ((int)(Math.random()*26))+97;
		return (char)letter;
	}
	
/**
 * Select random word direction as 1 of 4 options
 * 1 = Horizontal and Forwards
 * 2 = Horizontal and Backwards
 * 3 = Vertical and Forwards
 * 4 = Vertical and Backwards
 */
	int getDirection(){
		int direction = ((int)(Math.random()*4))+1;
		return direction;
	}

/**
 * Choose random start position for Horizontal words
 */
	int[] pickStartH(int wordLength){
		int[] position = {0,0};
		boolean found = false;
		
		while(!found){
			position[0] = (int)(Math.random() * (grid.length-1));
			position[1] = (int)(Math.random() * ((grid[0].length-1)- wordLength));
			
			for (int x = position[0], y = position[1]; y < position[1] + wordLength; y++){
				if(grid[x][y] != 0){
					found = false;
					break;
				}
				found = true;
			}
		}
		return position;
	}

/**
 * Choose random start position for Vertical words
 */
	int[] pickStartV(int wordLength){
		int[] position = {0,0};
		boolean found = false;
		
		while(!found){
			position[0] = (int)(Math.random() * ((grid[0].length-1)-wordLength));
			position[1] = (int)(Math.random() * (grid.length-1));
						
			for (int x = position[0], y = position[1]; x < position[0] + wordLength; x++){
				if(grid[x][y] != 0){
					found = false;
					break;
				}
				found = true;
			}
		}
		return position;
	}

/**
 * Reverse a word
 */
	String reverseWord(String word){
		StringBuilder newWord = new StringBuilder(word);
		newWord.reverse();
		word = newWord.toString();
		return word;
	}
	
/**
 * Add Horizontal word
 */
	void addHorizontalWord (String word, int positionX, int positionY){
		
		for(int i = 0; i < word.length(); i++){
			grid[positionX][positionY] = word.charAt(i);
			positionY++;
		}
	}
	
/**
 * Add Vertical word
 */
	void addVerticalWord (String word, int positionX, int positionY){
		
		for(int i = 0; i < word.length(); i++){
			grid[positionX][positionY] = word.charAt(i);
			positionX++;
		}
	}
	
/**
 * Add words randomly to the grid
 */
	void addWord(String word){
		int direction = getDirection();
		int position[];
		
		switch(direction){
			case(1):position = pickStartH(word.length());
					addHorizontalWord(word, position[0], position[1]);
					sol.addToStack(word, position[0]+1, position[1]+1);
					break;
			case(2):position = pickStartH(word.length());
					addHorizontalWord(reverseWord(word), position[0], position[1]);
					sol.addToStack(word, position[0]+1, position[1]+1);
					break;
			case(3):position = pickStartV(word.length());
					addVerticalWord(word, position[0], position[1]);
					sol.addToStack(word, position[0]+1, position[1]+1);
					break;
			case(4):position = pickStartV(word.length());
					addVerticalWord(reverseWord(word), position[0], position[1]);
					sol.addToStack(word, position[0]+1, position[1]+1);
					break;
		}
	}

/**
 * Fill empty grid spaces
 */
	void fillRandom(){
		for(int i = 0; i < grid.length; i++){
			for (int j = 0; j < grid[i].length; j++){
				if(grid[i][j] == 0){
					grid[i][j] = pickLetter();
				}
					
			}
		}
	}

/**
 * Fill complete grid
 */
	void fillGrid(){
		gridInitialise();
		
		Collections.sort(wordList, new StringLengthComparator());
		
		for(String word: wordList){
			addWord(word);
		}
		
		fillRandom();
	}		
}