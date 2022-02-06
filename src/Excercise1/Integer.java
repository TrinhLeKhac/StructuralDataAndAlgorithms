package Excercise1;

public final class Integer implements Comparable<Integer> {
    private int value;
    public int compareTo(Integer anotherInteger) {
        int thisVal = this.value;
        int anotherVal = anotherInteger.value;
        return (thisVal < anotherVal ? -1:(thisVal == anotherVal ? 0:1));
    }
}
