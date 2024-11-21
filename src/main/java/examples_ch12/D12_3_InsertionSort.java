package examples_ch12;

class InsertionSort extends SortBase {

    public InsertionSort(int n)     { super(n); }
    public InsertionSort(int[] x)   { super(x); }

    public void sort() {
        System.out.println("\n\n=== INSERTION SORT ===");
        insertionsort();
    }

    private void insertionsort() {
        for (int unsorted = 1; unsorted < values.length; unsorted++) {
            int i = unsorted;
            System.out.printf("\n Outer ");
            print(0, unsorted - 1);
            System.out.printf("  | ");
            print(unsorted, values.length - 1);

            int sorted = i - 1;
            while (sorted >= 0 && values[i] < values[sorted]) {
                swap(i, sorted);
                i = sorted;
                sorted--;

                System.out.printf("\n Inner ");
                print(0, unsorted);
                System.out.printf("  | ");
                print(unsorted + 1, values.length - 1);
            }
            System.out.println();
        }
    }
}

class D12_3_InsertionSort {

    public static void main(String[] args) {
        int[] X = {6, 8, 1, 4, 10, 5, 3, 9, 22};
        int[] Y = {1, 3, 3, 5, 6, 8, 9, 10, 22};	// already sorted
        int[] Z = {22, 10, 9, 8, 6, 5, 3, 3, 1};        // reverse order

        InsertionSort IS = new InsertionSort(X);
        //InsertionSort IS = new InsertionSort(10);
        System.out.println("Before sorting");
        IS.print();
        IS.sort();
        if (!IS.isAscendingSorted()) {
            System.out.println("Sorting fails !!");
        }
    }
}
