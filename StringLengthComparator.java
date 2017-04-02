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

import java.util.Comparator;

/**
 * Comparing the length of words in the array
 * Adapted from www.caveofprogramming.com
 **/
	class StringLengthComparator implements Comparator<String> {

	    @Override
	    public int compare(String s1, String s2) {

	        int len1 = s1.length();
	        int len2 = s2.length();
	        
	        if(len1 > len2) {
	            return -1;
	        }
	        else if(len1 < len2) {
	            return 1;
	        }
	        
	        return 0;
	    }
	}
