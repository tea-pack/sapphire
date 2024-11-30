package com.github.tea_pack.sapphire.utility;

public class CMPair<K extends Comparable<K>, V> extends Pair<K, V> implements Comparable<CMPair<K, V>> {

	public CMPair(K key, V value) {
		super(key, value);
	}

	@Override
	public int compareTo(CMPair<K, V> o) {
		return key.compareTo(o.key);
	}
}
