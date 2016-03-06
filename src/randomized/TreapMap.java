package randomized;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

public class TreapMap<K, V> extends AbstractMap<K, V> implements SortedMap<K, V>, Cloneable, Serializable {
	private static final long serialVersionUID = -7242391404737896904L;

	private Comparator<? super K> comparator;

	private Random random;

	private Entry<K, V> root;

	private int size = 0;

	public TreapMap() {
		this.comparator = null;
		this.random = new Random();
	}

	public TreapMap(Comparator<? super K> comparator) {
		this.comparator = comparator;
		this.random = new Random();
	}

	public TreapMap(Random random) {
		this.comparator = null;
		this.random = random;
	}

	public TreapMap(Map<? extends K, ? extends V> m) {
		this.comparator = null;
		this.random = new Random();
		putAll(m);
	}

	public TreapMap(Comparator<? super K> comparator, Random random, Map<? extends K, ? extends V> m) {
		this.comparator = comparator;
		this.random = random;
		putAll(m);
	}

	/** Insert */
	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		return super.put(key, value);
	}

	/** Delete */
	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return super.remove(key);
	}

	/** Find */
	@Override
	public V get(Object key) {
		// TODO Auto-generated method stub
		return super.get(key);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Comparator<? super K> comparator() {
		return comparator;
	}

	@Override
	public K firstKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedMap<K, V> headMap(K arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K lastKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedMap<K, V> subMap(K arg0, K arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedMap<K, V> tailMap(K arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	final static class Entry<K, V> implements Map.Entry<K, V> {

		K key;
		/*final*/ int priority;
		V value;
		Entry<K, V> left;
		Entry<K, V> right;
		Entry<K, V> parent;

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}

		@Override
		public String toString() {
			return "("+key+","+priority+")="+value;
		}

	}

}
