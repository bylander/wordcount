/**
 * TreeNode2 along with Tree2 is intended to be an object-oriented
 * conversion of the tnode struct and functions in K&R pp. 140-142. 
 */
public class TreeNode2 {
	/** the text */
	private String word;

	/** the number of occurences */
	private int count; 

	/** the left child */
	private TreeNode2 left;

	/** the right child */
	private TreeNode2 right;

	/**
	 * Creates a new TreeNode2 to store a word.
	 * 
	 * @param word the String to be stored in the node
	 */
	public TreeNode2(String word) {
		this.word = word;
		this.count = 1;
		this.left = this.right = null;
	}

	/**
	 * addtree adds (or modifies) a node for a word w into the
	 * tree/subtree headed by this node.  If the word is already in
	 * this tree, only the count is incremented; no new node is created.
	 * <p>
	 * This needs to take care of null differently because methods
	 * can't be called on null.
	 * 
	 * @param w the word to be added into the tree
	 */
	public void addTree(String w) {
		if (w.equals(word)) {
			count++;
		} else if (w.compareTo(word) < 0) {
			if (left == null) {
				left = new TreeNode2(w);
			} else {
				left.addTree(w);
			} 
		} else {
			if (right == null) {
				right = new TreeNode2(w);
			} else {
				right.addTree(w);
			}
		}
	}

	/**
	 * treePrint prints the tree/subtree headed by this node in-order
	 * on standard output.
	 * <p>
	 * This needs to take care of null differently because methods
	 * can't be called on null.
	 */
	public void treePrint() {
		if (left != null) {
			left.treePrint();
		}
		System.out.printf("%4d %s\n", count, word);
		if (right != null) {
			right.treePrint();
		}
	}
}
