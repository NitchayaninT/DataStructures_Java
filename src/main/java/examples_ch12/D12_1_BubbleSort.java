package examples_ch12;

class BubbleSort extends SortBase {

    public BubbleSort(int n)    { super(n); }
    public BubbleSort(int[] x)  { super(x); }

    public void sort() {
        System.out.println("\n\n=== BUBBLE SORT ===");
        bubblesort();
    }

    private void bubblesort() {
        for (int i = 0; i < values.length; i++) {
            System.out.printf("\n Outer ");
            print();

            for (int k = 0; k < values.length - i - 1; k++) {
                if (values[k] > values[k + 1]) {
                    swap(k, k + 1);
                }

                System.out.printf(" Inner ");
                print();
            }
            System.out.println();

            // at the end of each inner loop, the current max
            // bubbles to one side (sorted sequence)
        }
    }
}

class D12_1_BubbleSort {

    public static void main(String[] args) {
        int[] X = {6, 8, 1, 4, 10, 5, 3, 9, 22};
        int[] Y = {1, 3, 3, 5, 6, 8, 9, 10, 22};	// already sorted
        int[] Z = {22, 10, 9, 8, 6, 5, 3, 3, 1};        // reverse order

        BubbleSort BS = new BubbleSort(X);
        //BubbleSort BS = new BubbleSort(10);
        System.out.println("Before sorting");
        BS.print();
        BS.sort();
        if (!BS.isAscendingSorted()) {
            System.out.println("Sorting fails !!");
        }
    }
}
