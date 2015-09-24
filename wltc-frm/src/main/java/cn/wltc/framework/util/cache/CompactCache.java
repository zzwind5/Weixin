package cn.wltc.framework.util.cache;

public interface CompactCache<K,V> {
	
	public void put(K key, V value);
	
	public V get(K key);

	public void clean();
}
