/**
 * Tree2 along with TreeNode2 is intended to be an object-oriented
 * conversion of the tnode struct and functions in K&R pp. 140-142. 
 */

public class Tree2 {
	/** The root of the tree. */
	private TreeNode2 root;

	/**
	 * Creates an empty tree
	 */
	public Tree2() {
		root = null;
	}

	/**
	 * addtree adds (or modifies) a node for a word w into this
	 * tree.  If the word is already in this tree, 
	 * only the count is incremented; no new node is created.
	 * 
	 * @param w the word to be added into the tree
	 */
	public void addTree(String w) {
		if (root == null) {
			root = new TreeNode2(w);
		} else {
			root.addTree(w);
		}
	}

	/**
	 * treePrint prints this tree in-order on standard output.
	 */
	public void treePrint() {
		if (root != null) {
			root.treePrint();
		}
	}
}
