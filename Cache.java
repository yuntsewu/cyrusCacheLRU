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
        cacheMap = new HashMap();
    }
    public CacheData getCacheData(String key){
        //change the access count
        CacheData data = (CacheData)cacheMap.get(key);
        data.setCount(data.getCount()+1);
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
            String ludKey = keys.iterator().next();
            for (String key : keys){
                CacheData data = (CacheData)cacheMap.get(key);
                CacheData compare = (CacheData)cacheMap.get(ludKey);
                if (data.getCount() < compare.getCount()){
                    ludKey = key;
                }
            }
            //System.out.println("data: " + cacheMap.get(ludKey)+ "  with key of: "+ludKey+" is dropped");
            cacheMap.remove(ludKey);

        }
    }

    @Override
    public String toString() {
        return "size: " + cacheMap.size() + ",data: " + cacheMap;
    }
    //finite size
    //get unary function, returns the value of the key
    //put binary function, takes the value in to the cache under the key
    //least use element will be dropped first


    public static void main(String[] args){
        int size = 100;
        Cache cache = new Cache(size);
        for (int i = 0; i < 100; i++){
            String data = "hello"+i;
            cache.putCacheData(String.valueOf(i),data);

        }
        System.out.println(cache); // initiated the cache and cache have full of data
        //test cases
        System.out.println(cache.getCacheData("1"));//count increase by 1
        System.out.println(cache.getCacheData("1"));//count increase by 1
        System.out.println(cache.getCacheData("1"));//count increase by 1
        System.out.println(cache.getCacheData("1"));//count increase by 1
        System.out.println(cache.getCacheData("1"));//count increase by 1
        System.out.println(cache); // size should be 100
        cache.putCacheData("a","fdaf");// size should be 100
        cache.putCacheData("b","fdaf");// size should be 100
        cache.putCacheData("c","fdaf");// size should be 100
        cache.putCacheData("d","fdaf");// size should be 100
        System.out.println(cache.getCacheData("a"));//access to data
        System.out.println(cache.getCacheData("b"));//access to data
        System.out.println(cache.getCacheData("c"));//access to data
        System.out.println(cache.getCacheData("d"));//access to data




    }


}
