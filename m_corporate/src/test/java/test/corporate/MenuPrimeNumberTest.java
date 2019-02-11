package test.corporate;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MenuPrimeNumberTest {

    private List<Integer> holder = new ArrayList<>();

    private List<Integer> rootPrimeList(int c) {
        List<Integer> result = new ArrayList<>();

        for(int i = 1; i <= c; i++) {
            if(isPrime(i))
                result.add(i);
        }
        return result;
    }

    private boolean isPrime(int c) {
        if(c < 2)
            return false;

        for(int i = 0; i < holder.size() && c > holder.get(i); i++) {
            if(c % holder.get(i) == 0) {
                return false;
            }
        }

        holder.add(c);
        return true;
    }

    @Test
    public void printPrimeNumbers() {
        int i = 500;
        long r = 1;
        List<Integer> result = rootPrimeList(i);
        for(int x = 0; x < result.size(); x++) {
            r *= result.get(x);
        }
        System.out.println(result.size() + "{}" + r);
    }
}
