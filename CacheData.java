package cyrusLRUCache;

/**
 * Created by ytw on 9/27/16.
 */
public class CacheData {
    private Object data;
    private int count = 0;

    public CacheData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CacheData{" +
                "data=" + data +
                ", count=" + getCount() +
                '}';
    }
}

