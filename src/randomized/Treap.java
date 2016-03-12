package randomized;

import java.util.Random;

public class Treap {

	private Node root;
	private Random random;

	public Treap() {
		root = new Leaf(null);
		random = new Random();
	}

	public boolean insert(long key) {
		Node position = find(key);
		if (position.isLeaf()) {
			InternalNode parent = position.parent;
			InternalNode inserted = new InternalNode(random.nextLong(), key, parent);
			if (parent.left == position)
				parent.left = inserted;
			else if (parent.right == position)
				parent.right = inserted;
			while (parent.priority < inserted.priority) {
				// TODO: rotateLeftOrRight(parent, inserted);
			}
			return true;
		} else
			return false;
	}

	public boolean delete(long key) {
		Node position = find(key);
		if (position.isLeaf())
			return false;
		else {
			InternalNode deleted = (InternalNode)position;
			//TODO
			return true;
		}
	}

	public Node find(long key) {
		Node position = root;
		while (! position.isLeaf()) {
			InternalNode internalPosition = (InternalNode)position;
			if (key < internalPosition.key)
				position = internalPosition.left;
			else if (key > internalPosition.key)
				position = internalPosition.right;
			else
				return position;
		}
		return position;
	}

	public Node getRoot() {
		return root;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	private void rotateLeft(InternalNode node) {
		// assert(node.left.parent == node);
		InternalNode child = (InternalNode)node.left;
		child.parent = node.parent;
		if (root == node)
			root = child;
		else {
			InternalNode parent = node.parent;
			if (parent.left == node)
				parent.left = child;
			else if (parent.right == node)
				parent.right = child;
		}
		node.parent = child;
		node.left = child.right;
		child.right.parent = node;
		child.right = node;
		// assert(child.right.parent == child);
	}

	private void rotateRight(InternalNode node) {
		// assert(node.right.parent == node);
		InternalNode child = (InternalNode)node.right;
		child.parent = node.parent;
		if (root == node)
			root = child;
		else {
			InternalNode parent = node.parent;
			if (parent.left == node)
				parent.left = child;
			else if (parent.right == node)
				parent.right = child;
		}
		node.parent = child;
		node.right = child.left;
		child.left.parent = node;
		child.left = node;
		// assert(child.left.parent == child);
	}
}
