package ait.numbers.model;

import java.util.Arrays;

public class ThreadGroupSum extends GroupSum {
    public ThreadGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        int totalSum = 0;

        int[] threadSums = new int[numberGroups.length];
        Thread[] threads = new Thread[numberGroups.length];


        for (int i = 0; i < numberGroups.length; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                threadSums[index] = Arrays.stream(numberGroups[index]).sum();
            });
            threads[i].start();
        }


        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        for (int threadSum : threadSums) {
            totalSum += threadSum;
        }

        return totalSum;
    }
}
