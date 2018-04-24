package Model;

import java.util.*;

public class RandomList<Type> implements Iterable<Type>{
    //private Type[] arrayList;
    List<Type> list = new ArrayList<Type>();
    Random random = new Random();

    public RandomList(Type[] newArray) {
        //this.arrayList = newArray;
        Collections.addAll(list,newArray);
    }

    public RandomList(RandomList<Type> tetrominoTypes) {
        this.list=tetrominoTypes.list;
        this.random=tetrominoTypes.random;
    }

    public Type pop() {
        if (list.size()==0)
            return null;
        Type res = list.get(random.nextInt(list.size()));
        list.remove(res);
        return res;
    }

    public void add(Type type) {
        list.add(type);
    }


    public Iterator<Type> iterator() {
        final List<Type> lista = new ArrayList<Type>(list);
        final Random random = new Random();
        return new Iterator<Type>() {

            public boolean hasNext() {
                return !lista.isEmpty();
            }

            public Type next() {
                Type res = lista.get(random.nextInt(lista.size()));
                lista.remove(res);
                return res;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }
}
