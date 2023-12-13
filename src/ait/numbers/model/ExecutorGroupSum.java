package ait.numbers.model;

import java.util.Arrays;
import java.util.concurrent.*;

public class ExecutorGroupSum extends GroupSum {
    public ExecutorGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        int totalSum = 0;

        ExecutorService executorService = Executors.newFixedThreadPool(numberGroups.length);


        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);

        for (int[] group : numberGroups) {

            completionService.submit(() -> Arrays.stream(group).sum());
        }

        try {

            for (int i = 0; i < numberGroups.length; i++) {
                Future<Integer> result = completionService.take();
                totalSum += result.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {

            executorService.shutdown();
        }


        return totalSum;
    }
}
