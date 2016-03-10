package randomized;

public class Leaf extends Node {
	public Leaf(InternalNode parent) {
		this.parent = parent;
	}
	@Override
	public boolean isLeaf() {
		return true;
	}
}
