/**
 * WordCount1 is intended to be a mostly straightforward translation
 * of the K&R word counting program (main routine on p. 140).
 * <p>
 * This translation implements the tnode struct as a separate class
 * named TreeNode1 with public fields word, count, left, and right.
 * StringBuilder is used for character buffers.  The mismatch between
 * bytes and INICODE chars is not fully handled, as each byte that is
 * input is individually translated to a char.
 * 
 * @author Tom Bylander
 * @version 20130619
 */

public class WordCount1 {
	/**
	 * EOF is a class constant.  It is -1 because that is what
	 * is returned for end of file by System.in.read().
	 */
	public static final int EOF = -1;

	/**
	 * The main method reads every word from standard input,
	 * stores it in a binary tree (which remembers how many
	 * times it appears), and prints the words and their
	 * counts back out in lexiographic order.
	 * <p>
	 * Corresponds to K&R, p. 140.
	 */
	public static void main(String[] args) {
		TreeNode1 root = null;
		StringBuilder word = new StringBuilder();

		while (getWord(word) != EOF) {
			if (Character.isLetter(word.charAt(0))) {
				root = addTree(root, word.toString());
			}
		}
		treePrint(root);
	}

	/**
	 * The global buf variable is a translation of char buf[BUFSIZE];.
	 * This is used by getch and ungetch.
	 * <p> 
	 * A translation of int bufp = 0; is not needed because a
	 * StringBuilder object knows how big it is.
	 * A StringBuilder object starts with no characters in it.
	 * <p>
	 * Corresponds to K&R, p. 79.
	 */
	private static StringBuilder buf = new StringBuilder(); 

	/**
	 * getch returns a character removed from buf, or, if buf is empty, 
	 * getch returns a character from standard input (or EOF).
	 * <p>
	 * Corresponds to K&R, p. 79.
	 * 
	 * @return EOF or an int representation of the character.
	 */
	public static int getch() {
		int len = buf.length();
		if (len > 0) {                     // if the buffer has chars
			char c = buf.charAt(len - 1);  //   get the last char
			buf.deleteCharAt(len - 1);      //   remove the last char
			return c;                      //   return that char
		} else {
			try {
				return System.in.read();
			} catch (java.io.IOException e) {
				throw new RuntimeException("read failed: " + e);
			}
		}
	}

	/**
	 * ungetch appends a character to buf.
	 * <p>
	 * Corresponds to K&R, p. 79
	 * 
	 * @param c an int representation of the character
	 */
	public static void ungetch(int c) {
		if (c != EOF) {             // if it's not the end of file
			buf.append((char) c);   //   insert the char into the buffer
		}
	}

	/**
	 * getWord stores the next word or nonspace character from
	 * standard input into its StringBuilder parameter.  It returns
	 * the int representation of the first character or EOF.  A word
	 * is interpreted as any string of letters delimited by nonletters.
	 * <p>
	 * Corresponds to K&R, p. 136.
	 *
	 * @param word the buffer for the word
	 * @return the first character of the word or EOF.
	 */
	public static int getWord(StringBuilder word) {
		word.delete(0, word.length());  /* make sure buffer starts out empty */
		int c = getch();
		while (c != EOF && Character.isWhitespace((char) c)) {
			c = getch();
		}

		if (c != EOF) {
			word.append((char) c);
		}
		if (! Character.isLetter(c)) {
			return c;
		}
		c = getch();
		while (Character.isLetter(c)) {
			word.append((char) c);
			c = getch();
		}
		ungetch(c);
		return word.charAt(0);
	}

	/**
	 * addtree adds (or modifies) a node for a word w into the tree or
	 * subtree with root p.  If the word is already in the tree, only the 
	 * count is incremented; no new node is created.
	 * <p>
	 * Corresponds to K&R, p. 141. 
	 * 
	 * @param p the root of the subtree, possibly null
	 * @param w the word to be added into the tree
	 * @return the TreeNode1 p, or, if p is null, a new TreeNode1 object.
	 *         The latter is needed to fill in a null field of the parent.
	 */
	public static TreeNode1 addTree(TreeNode1 p, String w) {
		if (p == null) {          /* a new word has arrived */
			p = new TreeNode1();  /* make a new node */
			p.word = w;           /* no need to copy the String */
			p.count = 1;
			p.left = p.right = null;
		} else if (w.equals(p.word)) {
			p.count++;
		} else if (w.compareTo(p.word) < 0) {
			p.left = addTree(p.left, w);
		} else {
			p.right = addTree(p.right, w);
		}
		return p;
	}

	/**
	 * treePrint prints the tree p in-order on standard output.
	 * <p>
	 * Corresponds to K&R, p. 142.
	 *
	 * @param p the TreeNode1 root of the tree or subtree.
	 */
	public static void treePrint(TreeNode1 p) {
		if (p != null) {
			treePrint(p.left);
			System.out.printf("%4d %s\n", p.count, p.word);
			treePrint(p.right);
		}
	}
}
