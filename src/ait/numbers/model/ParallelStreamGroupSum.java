package ait.numbers.model;

import java.util.Arrays;

public class ParallelStreamGroupSum extends GroupSum{
    public ParallelStreamGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {

        // TODO Advanced Homework: reduce sum numbers of numberGroups, use Arrays.stream(...).parallel()
        return Arrays.stream(numberGroups)
                .parallel()
                .mapToInt(a -> Arrays.stream(a).sum())
                .sum();

    }
}
