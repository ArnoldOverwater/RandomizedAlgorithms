package randomized;

import java.util.Random;

public class Treap {

	private Node root;
	private Random random;
	private int rotateCount;

	public Treap() {
		root = new Leaf(null);
		random = new Random();
	}

	/**
	 * insert a new Node to the tree with random priority
	 * @param key
	 * @return
	 */
	public boolean insert(long key) {
        return insertNode(key, random.nextLong());
	}

    public boolean insertNode(long key, long random) {
        Node position = find(key);
        if (position.isLeaf()) {
            InternalNode parent = position.parent;
            InternalNode inserted = new InternalNode(random, key, parent);
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
			if (root == deleted) {
				root = new Leaf(null);
				return true;
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

    /**
     * find the Node with key
     * @param key
     * @return the Node with Key 'key'. If no such key exist, return the leaf where the should be located
     */
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

	/**
	 * Create a new treap with this Treap, t and a new node with key
	 * @param key
	 * @param t
	 * @before max key of this Treap < key < min key of t
	 * @return true iff the join was succesful
     */
	public void join(long key, Treap t) {
		InternalNode newRoot = new InternalNode(random.nextLong(), key);
		root.parent = newRoot;
		newRoot.left = root;
		newRoot.right = t.root;
		t.root.parent = newRoot;
		root = newRoot;
        rotateNodeDown( (InternalNode)root);
	}

    /**
     * paste t unto this Treap.
     * @before: all keys of t are larger than the largest key of t
     * @before this and t contain at least one internalNode
     * @param t
     */
    public void paste(Treap t) {
        Node ChildOfNodeWithLargestKey = find(Long.MAX_VALUE);
        InternalNode largestKeyNode = ChildOfNodeWithLargestKey.parent;
        delete(largestKeyNode.key);
        join(largestKeyNode.key, t);
    }

    /**
     * returns an array with two Treaps
     * The first Treap contains all Nodes of the original Treap with value smaller than k
     * The second Treap contains all Nodes of the original Treap with value larger than k
     * @param k
     * @return
     */
    public Treap[] split(long k) {
        delete(k);
        insertNode(k,Long.MAX_VALUE);
        Treap[] treapList = new Treap[2];
        Treap left = new Treap();
        Treap right = new Treap();
        InternalNode currentRoot = (InternalNode)root;
        left.root = currentRoot.left;
        right.root = currentRoot.right;
        left.root.parent = null;
        right.root.parent = null;
        treapList[0] = left;
        treapList[1] = right;
        return treapList;
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

	public void rotateNodeDown(InternalNode node) {
		InternalNode current = node;
		boolean done = false;
		while(!done && (!current.left.isLeaf() || !current.right.isLeaf())) {
			if(current.right.isLeaf()) {			// only a left child
				InternalNode l = (InternalNode) current.left;
				if(l.priority > current.priority) {
					rotateLeft(current);
				} else {
					done = true;
				}
			} else if(current.left.isLeaf()) {		// only a right child
				InternalNode r = (InternalNode) current.right;
				if(r.priority > current.priority) {
					rotateRight(current);
				} else {
					done = true;
				}
			} else {								// left and right child
				InternalNode l = (InternalNode) current.left;
				InternalNode r = (InternalNode) current.right;
				if(l.priority > r.priority && l.priority > current.priority) {
					rotateLeft(current);
				} else if(r.priority > l.priority && r.priority > current.priority) {
					rotateRight(current);
				} else {
					done = true;
				}
			}
		}
	}

	/**
	 * Let node become the right child of its left child
	 * @param node
	 */
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
		rotateCount++;
	}

	/**
	 * Let node become the left child of its right child
	 * @param node
	 */
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
		rotateCount++;
	}

	public int getRotateCountAndReset() {
		int c = rotateCount;
		rotateCount = 0;
		return c;
	}

}
