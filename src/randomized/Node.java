package randomized;

public abstract class Node {

	InternalNode parent;

	public InternalNode getParent() {
		return parent;
	}
	
	public abstract boolean isLeaf();
}
