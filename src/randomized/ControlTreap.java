package randomized;

public class ControlTreap {

	public static void main(String[] args) {

	}

	/**
	 * 
	 * @return a new empty Treap
	 */
	public static Treap makeSet() {
		return new Treap(null);
	}
	
	/**
	 * insert Node n at the right place into t. Adjust Treap afterwards
	 * @param n node to be added, having a unique key and priority
	 * @param t the Treap to insert in
	 */
	public static void insert(Node n, Treap t) {
		boolean done = false;
		Node current = t.getRoot();
		Node nodeToInsert = n;
		while(!done && current != null) {
			// replace current with nodeToInsert and insert current into one of the subtrees
			if(nodeToInsert.getPriority() > current.getPriority()) {
				//place nodeToInsert here
				nodeToInsert.setParent(current.getParent());
				//check whether nodeToInsert is the left or the right child of your parent
				if(nodeToInsert.getParent().getKey() > nodeToInsert.getKey()) {
					//nodeToInsert is the left child
					nodeToInsert.getParent().setLeft(nodeToInsert);
				} else {
					//nodeToInsert is the right child
					nodeToInsert.getParent().setRight(nodeToInsert);
				}
				nodeToInsert.setLeft(current.getLeft());
				nodeToInsert.setRight(current.getRight());
				current.setParent(null);
				current.setLeft(null);
				current.setRight(null);
				
				//insert current Node into left or right subtree
				if(nodeToInsert.getKey() > current.getKey()) {
					//insert into left subtree 
					if(nodeToInsert.getLeft() == null) {
						// the left subtree is empty
						nodeToInsert.setLeft(current);
						current.setParent(nodeToInsert);
						done = true;
					} else {
						// the left subtree is not empty
						Node temp = current;
						current = nodeToInsert.getLeft();
						nodeToInsert = temp;
					}
				} else {
					//insert into right subtree
					if(nodeToInsert.getRight() == null) {
						// the right subtree is empty
						nodeToInsert.setRight(current);
						current.setParent(nodeToInsert);
						done = true;
					} else {
						// the left subtree is not empty
						Node temp = current;
						current = nodeToInsert.getRight();
						nodeToInsert = temp;
					}
				}
			// insert nodeToInsert into one of the subtrees 
			} else {
				// insert nodeToInsert into left subtree
				if(n.getKey() < current.getKey()) {
					// insert nodeToinsert
					if(current.getLeft() == null) {
						current.setLeft(nodeToInsert);
						nodeToInsert.setParent(current);
						done = true;
					
					// insert nodeToInsert into the left subtree
					} else {
						current = current.getLeft();
					}
					
				// insert nodeToInsert into right subtree
				} else { 
					//insert nodeToinsert
					if(current.getRight() == null) {
						current.setRight(nodeToInsert);
						nodeToInsert.setParent(current);
						done = true;
						
					// insertNodeToInsert into the right subtree
					} else {
						current = current.getRight();
					}
					
				}
			}
		}
	}
	
	/**
	 * Delete the node with key k and adjust the tree afterwards. If no such Node exist, no Node will be removed
	 * @param k key value of the node to delete
	 * @param t the Treap
	 */
	public static void delete(int k, Treap t) {
		//TODO
	}
	
	/**
	 * find and return the Node with key k. If there exists no Node with key k, return null
	 * @param k key value to find
	 * @param t Treap to be searched
	 * @return
	 */
	public static Node find(int k, Treap t) {
		//TODO
		return null;
	}

	/**
	 * create and return a new Treap with Node n as root and l and r as children
	 * @param l Treap where all keys are smaller than k
	 * @param n Node with key k
	 * @param r Treap where all keys are larger than k
	 * @return
	 */
	public static Treap join(Treap l, Node n, Treap r) {
		//TODO
		Treap t = new Treap(n);
		n.setLeft(l.getRoot());
		l.getRoot().setParent(n);
		n.setRight(r.getRoot());
		r.getRoot().setParent(n);
		return t;
	}
	
	/**
	 * create and return a new Treap containing all the nodes of l and r
	 * @param l left Treap (with small keys)
	 * @param r right Treap (with large keys)
	 * @return the resulting Treap
	 */
	public static Treap paste(Treap l, Treap r) {
		//TODO
		return null;
	}

	/**
	 * split Treap t into two Treaps, one with keys smaller than k and one with keys larger than k
	 * @param k the key value to split on
	 * @param t the originial Treap
	 * @return an array containing the two resulting Treaps
	 */
	public static Treap[] split(int k, Treap t) {
		//TODO
		return null;
	}
		
}
