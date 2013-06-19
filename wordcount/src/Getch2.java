import java.io.*;  /* for Reader and InputStreamReader */

/** 
 * Getch2 is intended to be an object-oriented translation of
 * the getch and ungetch functions in K&R p. 79.
 * <p>
 * This should be easily generalizable to getting and ungetting
 * characters from any file or input stream.
 */
public class Getch2 {
	/**
	 * EOF is a class constant.  It is -1 because that is what
	 * is returned for end of file by in.read().
	 */
	public static final int EOF = -1;

	/* want a Reader object instead of an InputStream
	 * to handle UNICODE characters correctly.
	 */
	private Reader in;

	/* buffer for ungetting characters */
	private StringBuilder buf;

	/**
	 * This constructor creates a Getch2 object that will read
	 * from standard input.
	 */
	public Getch2() {
		in = new InputStreamReader(System.in);
		buf = new StringBuilder();
	}

	/**
	 * getch returns the next character from the input (or EOF).
	 * 
	 * @return EOF or an int representation of the character.
	 */
	public int getch() {
		int len = buf.length();
		if (len > 0) {
			char c = buf.charAt(len - 1);
			buf.deleteCharAt(len - 1);
			return c;
		} else {
			try {
				return in.read();
			} catch (java.io.IOException e) {
				/* probably not the best way to handle this */
				throw new RuntimeException("read failed: " + e);
			}
		}
	}

	/**
	 * ungetch ungets a character so that getch can return it later.
	 * 
	 * @param c an int representation of the character
	 */
	public void ungetch(int c) {
		if (c != EOF)
			buf.append((char) c);
	}
}
