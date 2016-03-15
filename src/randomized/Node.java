package randomized;

public abstract class Node {

	InternalNode parent;
	boolean rightChild;

	public InternalNode getParent() {
		return parent;
	}

	public boolean isRightChild() {
		return rightChild;
	}

	public abstract boolean isLeaf();
}
