package randomized;

public class Node {

	private Node parent;
	private Node left;
	private Node right;
	private int priority;
	private int key;
	
	private Node(int p, int k) {
		priority = p;
		key = k;
	}
	
	private Node(int p, int k, Node l, Node r) {
		priority = p;
		key = k;
		left = l;
		right = r;
	}
	
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
}
