package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import java.util.ArrayList;

public class AsIntStream implements IntStream {

    private ArrayList<Integer> lst;

    private AsIntStream() {
        this.lst = new ArrayList<Integer>();
    }

    private AsIntStream(ArrayList<Integer> mylst){
        this.lst = mylst;
    }


    public static IntStream of(int... values) {
        AsIntStream intstream = new AsIntStream();
        for (int i = 0; i < values.length; i++) {
            intstream.lst.add(values[i]);
        }
        return intstream;
    }


    @Override
    public Double average() {
        double res;
        if (lst.isEmpty()) {
            throw new IllegalArgumentException();
        }
        else {
           res = (double)sum()/count();
        }
        return res;

    }

    @Override
    public Integer max() {
        if (lst.isEmpty()) {
            throw new IllegalArgumentException();
        }
        else {
            int maxim = Integer.MIN_VALUE;
            for(int i: lst){
                if (i > maxim){
                    maxim = i;
                }
            }
            return maxim;
        }

    }

    @Override
    public Integer min() {
        if (lst.isEmpty()) {
            throw new IllegalArgumentException();
        }

        else {
            int minim = Integer.MAX_VALUE;
            for(int i: lst){
                if (i < minim){
                    minim = i;
                }
            }
            return minim;
        }
    }

    @Override
    public long count() {
        if (lst.isEmpty()){
            throw new IllegalArgumentException();
        }
        return lst.size();
    }

    @Override
    public Integer sum() {
        if (lst.isEmpty()){
            throw new IllegalArgumentException();
        }
        int sum = 0;
        for (int i : lst) {
            sum += i;
        }
        return sum;

    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        AsIntStream stream2 = new AsIntStream();
        for (int i : lst) {
            if (predicate.test(i)){
                stream2.lst.add(i);
            }
        }
        return stream2;
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int i : lst) {
            action.accept(i);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        AsIntStream lst2 = new AsIntStream();
        for (int i : lst) {
            lst2.lst.add(mapper.apply(i));
        }
        return lst2;

    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        ArrayList<IntStream> lst2 = new ArrayList<>();
        for (Integer i : lst) {
            lst2.add(func.applyAsIntStream(i));
        }

        ArrayList<Integer> strFmap = new ArrayList<>();
        for (IntStream i : lst2) {
            for (int j : i.toArray()){
                strFmap.add(j);
            }
        }
        return new AsIntStream(strFmap);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int result = 0;
        for (int i : lst) {
            result += op.apply(identity, i);
        }
        return result;
    }

    @Override
    public int[] toArray() {
        int lg = lst.size();
        int[] res = new int[lg];
        int ind = 0;
        for (int i: lst){
            res[ind] = i;
            ind++;
        }
        return res;
    }

}
