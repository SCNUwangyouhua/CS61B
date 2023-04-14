package deque;

import java.util.Comparator;
public class MaxArrayDeque<Item> extends ArrayDeque<Item>{
    private final Comparator<Item> comparator;

    public MaxArrayDeque(Comparator<Item> c) {
        comparator = c;
    }

    public Item max(Comparator<Item> c) {
        if (isEmpty()) {
            return null;
        }
        int MaxIndex = 0;
        for (int i = 0; i < size(); i++) {
            if (c.compare(get(i), get(MaxIndex)) > 0) {
                MaxIndex = i;
            }
        }
        return get(MaxIndex);
    }

    public Item max() {
        return max(comparator);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof MaxArrayDeque)) {
            return false;
        }
        if (((MaxArrayDeque<Item>) o).max() != max()) {
            return false;
        }
        return super.equals(0);
    }
}
