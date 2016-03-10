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
			InternalNode inserted = new InternalNode(random.nextLong(), key, position.parent);
			//TODO
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
	
}
