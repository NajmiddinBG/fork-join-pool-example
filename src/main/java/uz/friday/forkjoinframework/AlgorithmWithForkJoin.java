package uz.friday.forkjoinframework;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class AlgorithmWithForkJoin {
    public static void main(String[] args) {
        int size = 10_000_000;

        String[] names = new String[size];
        for (int i = 0; i < size; i++) {
            names[i] = getSaltString();
        }
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Counter counter = new Counter(names, 0, size);
        forkJoinPool.invoke(counter);
        Integer join = counter.join();
        System.out.println(join);
    }

    //Generating random string
    public static String getSaltString() {
        String bigLettersAndNumbers = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) {
            int index = (int) (rnd.nextFloat() * bigLettersAndNumbers.length());
            salt.append(bigLettersAndNumbers.charAt(index));
        }
        return salt.toString();

    }
}
