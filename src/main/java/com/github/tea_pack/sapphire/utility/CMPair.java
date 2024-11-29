package com.github.tea_pack.sapphire.utility;

public class CMPair<K extends Comparable<K>, V> implements Comparable<CMPair<K, V>> {
	public K key;
	public V value;

	public CMPair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public int compareTo(CMPair<K, V> o) {
		return key.compareTo(o.key);
	}
}
