package uz.friday.forkjoinframework;

import java.util.concurrent.RecursiveTask;

public class Counter extends RecursiveTask<Integer> {

    private String[] names;
    private int from;
    private int to;

    private int limit = 500;

    public Counter(String[] names, int from, int to) {
        this.names = names;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if(to-from < limit){
            int count = 0;
            for (int i = from; i < to; i++) {
                if (names[i].startsWith("1")) {
                    System.out.println(names[i]);
                    count++;
                }
            }
            return count;
        } else {
            int middle = from + (to-from)/2;
            Counter first = new Counter(names, from, middle);
            Counter second = new Counter(names, middle, to);
            invokeAll(first, second);
            return first.join()+second.join();
        }
    }
}
