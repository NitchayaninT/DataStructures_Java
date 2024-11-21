package examples_ch12;

// Utility methods for sorting
class SortBase {

    protected int[] values;

    public SortBase(int n) {
        values = new int[n];
        for (int i = 0; i < values.length; i++) {
            values[i] = (int) (Math.random() * 1234) % 100;
        }
    }

    public SortBase(int[] x) {
        values = new int[x.length];
        for (int i = 0; i < values.length; i++) {
            values[i] = x[i];
        }
    }

    public boolean isAscendingSorted() {
        boolean s = true;
        for (int i = 0; i < values.length - 1; i++) {
            if (values[i] > values[i + 1]) {
                s = false;
                break;
            }
        }
        return s;
    }

    public void swap(int i, int j) {
        int temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }

    public void sort() {
    }

    public void print() {
        for (int i = 0; i < values.length; i++) {
            System.out.printf("%3d ", values[i]);
            if (i != 0 && i % 10 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public void print(int first, int last) {
        for (int i = first; i <= last; i++) {
            System.out.printf("%3d ", values[i]);
        }
    }

    public void printjump(int first, int jump) {
        for (int i = first; i < values.length; i = i + jump) {
            System.out.printf("%3d ", values[i]);
        }
        System.out.println();
    }
}
