package cyrusLRUCache;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by ytw on 9/27/16.
 */
public class Cache {
    private int size;
    Map cacheMap;
    public Cache(int size){
        this.size = size;
        cacheMap = new HashMap(size);
    }
    public CacheData getCacheData(String key){
        //change the access count
        CacheData data = (CacheData)cacheMap.get(key);
        data.setCount(data.getCount()+1);
        cacheMap.replace(key,data);
        return data;
    }
    public void putCacheData(String key, Object value){
        cacheMap.put(key, new CacheData(value));
        checkSize();
    }
    public void checkSize(){
        //check if data structure exceed the size of limit
        //drop the least used cache data until within boundary
        if (cacheMap.size() > size) {
            Set<String> keys = cacheMap.keySet();
            CacheData first =  (CacheData) cacheMap.get(keys.iterator().next()); //lease used data
            CacheData lud =  first; //lease used data
            for (String key : keys){
                if (((CacheData)cacheMap.get(key)).getCount() < lud.getCount()) {
                    lud = (CacheData) cacheMap.get(key);
                }
            }
        }
    }

    //finite size
    //get unary function, returns the value of the key
    //put binary function, takes the value in to the cache under the key
    //least use element will be dropped first


    public static void main(String[] args){
        int size = 100;
        Cache cache = new Cache(size);
        for (int i = 0; i < size; i++){
            String data = "hello"+size;
            cache.putCacheData(data,new CacheData(data));

        }
        System.out.println(cache);

    }

    @Override
    public String toString() {
        return "cyrusLRUCache.Cache{" +
                "size=" + size +
                ", cacheMap=" + cacheMap +
                '}';
    }
}
