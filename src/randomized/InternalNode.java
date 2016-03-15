package randomized;

public class InternalNode extends Node {

	Node left;
	Node right;
	long priority;
	long key;
	
	InternalNode(long p, long k) {
		priority = p;
		key = k;
	}
	
	InternalNode(long p, long k, InternalNode par) {
		priority = p;
		key = k;
		left = new Leaf(this);
		right = new Leaf(this);
		right.rightChild = true;
		parent = par;
	}
	
	public Node getLeft() {
		return left;
	}
	public Node getRight() {
		return right;
	}
	public long getPriority() {
		return priority;
	}
	public long getKey() {
		return key;
	}
	@Override
	public boolean isLeaf() {
		return false;
	}
}
