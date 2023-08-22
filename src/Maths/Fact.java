package Maths;

import A2.Components;
import A2.GraphM;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Fact {
    public static int factorial(int num) {
        Components c1 = new Components();
        GraphM g1 = new GraphM();
        if (num < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        } else if (num == 0 || num == 1) {
            return 1;
        } else if (c1.isPrime(num)) {
            int result = 1;
            for (int i = 2; i <= num; i++) {
                result *= i;
            }
            return result;
        } else {
            List<Integer> factors = g1.primeFactors(num);
            Set<Integer> set = new LinkedHashSet<>(factors);
            int result = 1;
            for (int factor : set) {
                int count = Collections.frequency(factors, factor);
                int factorResult = 1;
                for (int i = 1; i <= count; i++) {
                    factorResult *= i;
                }
                result *= factorResult;
            }
            return result;
        }
    }
}
