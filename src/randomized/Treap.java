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
			if (root == position) {
				root = inserted;
				return true;
			}
			inserted.rightChild = position.rightChild;
			if (inserted.rightChild)
				parent.right = inserted;
			else
				parent.left = inserted;
			while (root != inserted && parent.priority < inserted.priority) {
				if (inserted.rightChild)
					rotateRight(parent);
				else
					rotateLeft(parent);
				parent = inserted.parent;
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
			while (! deleted.left.isLeaf() || ! deleted.right.isLeaf()) {
				if (deleted.left.isLeaf())
					rotateRight(deleted);
				else if (deleted.right.isLeaf())
					rotateLeft(deleted);
				else if (((InternalNode)deleted.left).priority < ((InternalNode)deleted.right).priority)
					rotateRight(deleted);
				else
					rotateLeft(deleted);
			}
			InternalNode parent = deleted.parent;
			if (deleted.rightChild) {
				parent.right = new Leaf(parent);
				parent.right.rightChild = true;
			} else
				parent.left = new Leaf(parent);
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
		child.rightChild = node.rightChild;
		if (root == node)
			root = child;
		else {
			InternalNode parent = node.parent;
			if (node.rightChild)
				parent.right = child;
			else
				parent.left = child;
		}
		node.parent = child;
		node.rightChild = true;
		node.left = child.right;
		child.right.parent = node;
		child.right.rightChild = false;
		child.right = node;
		// assert(child.right.parent == child);
	}

	private void rotateRight(InternalNode node) {
		// assert(node.right.parent == node);
		InternalNode child = (InternalNode)node.right;
		child.parent = node.parent;
		child.rightChild = node.rightChild;
		if (root == node)
			root = child;
		else {
			InternalNode parent = node.parent;
			if (node.rightChild)
				parent.right = child;
			else
				parent.left = child;
		}
		node.parent = child;
		node.rightChild = false;
		node.right = child.left;
		child.left.parent = node;
		child.left.rightChild = true;
		child.left = node;
		// assert(child.left.parent == child);
	}
}
