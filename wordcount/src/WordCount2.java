/**
 * WordCount2 along with Tree2, TreeNode2, and Getch2 is intended to 
 * be a object-oriented conversion of the
 * K&R word counting program (main routine on p. 140).
 * <p>
 * This conversion implements the tnode struct as the Tree2 and
 * TreeNode2 classes.  It also implements the getch and ungetch
 * functions as the Getch2 class.  This is intended to handle
 * chars completely correctly.
 * 
 * @author Tom Bylander
 * @version 20130619
 */

public class WordCount2 {
	/**
	 * The main method reads every word from standard input,
	 * stores it in a binary tree (which remembers how many
	 * times each word appears), and prints the words and their
	 * counts back out in lexiographic order.
	 */
	public static void main(String[] args) {
		Tree2 tree = new Tree2();
		StringBuilder word = new StringBuilder();
		Getch2 in = new Getch2();

		while (getWord(word, in) != Getch2.EOF) {
			if (Character.isLetter(word.charAt(0))) {
				tree.addTree(word.toString());
			}
		}
		tree.treePrint();
	}

	/**
	 * getWord stores the next word or non-space character from the
	 * Getch2 object into its StringBuilder parameter.  It returns
	 * the int representation of the first character or Getch2.EOF.
	 * A word is interpreted as any string of letters delimited by 
	 * nonletters.
	 *
	 * @param word the buffer for the word
	 * @return the first character of the word or Getch2.EOF.
	 */
	public static int getWord(StringBuilder word, Getch2 in) {
		word.delete(0, word.length());  /* make sure buffer starts out empty */
		int c = in.getch();
		while (c != Getch2.EOF && Character.isWhitespace((char) c)) {
			c = in.getch();
		}

		if (c != Getch2.EOF) {
			word.append((char) c);
		}
		if (! Character.isLetter(c)) {
			return c;
		}
		c = in.getch();
		while (Character.isLetter(c)) {
			word.append((char) c);
			c = in.getch();
		}
		in.ungetch(c);
		return word.charAt(0);
	}
}
