package examples_ch12;

class SelectionSort extends SortBase {

    public SelectionSort(int n)     { super(n); }
    public SelectionSort(int[] x)   { super(x); }

    public void sort() {
        System.out.println("\n\n=== SELECTION SORT ===");
        selectionsort();
        //recursivesort(0);
    }

    private void selectionsort() {
        // sorted is the last position in sorted sequence
        for (int sorted = 0; sorted < values.length; sorted++) {
            System.out.printf("\n Outer ");
            print(0, sorted - 1);
            System.out.printf("  | ");
            print(sorted, values.length - 1);

            int min = sorted;
            for (int i = sorted + 1; i < values.length; i++) {
                if (values[i] < values[min]) {
                    min = i;
                }

                System.out.printf("\n Inner ");
                print(0, sorted - 1, min);
                System.out.printf("  | ");
                print(sorted, values.length - 1, min);
            }
            System.out.println();

            // append this min to the sorted sequence
            swap(sorted, min);
        }
    }

    public void print(int first, int last, int min) {
        for (int i = first; i <= last; i++) {
            if (i == min) {
                System.out.printf("%3d*", values[i]);
            } else {
                System.out.printf("%3d ", values[i]);
            }
        }
    }

    // tail recursion -- not a good programming practice
    public void recursivesort(int sorted) {
        if (sorted == values.length - 1) {
            System.out.println("\nRecursive version");
            print();
            return;
        }
        int min = sorted;
        for (int i = sorted + 1; i < values.length; i++) {
            if (values[i] < values[min]) {
                min = i;
            }
        }
        swap(sorted, min);
        recursivesort(sorted + 1);
    }
}

class D12_2_SelectionSort {

    public static void main(String[] args) {
        int[] X = {6, 8, 1, 4, 10, 5, 3, 9, 22};
        int[] Y = {1, 3, 3, 5, 6, 8, 9, 10, 22};	// already sorted
        int[] Z = {22, 10, 9, 8, 6, 5, 3, 3, 1};        // reverse order

        SelectionSort SS = new SelectionSort(X);
        //SelectionSort SS = new SelectionSort(10);
        System.out.println("Before sorting");
        SS.print();
        SS.sort();
        if (!SS.isAscendingSorted()) {
            System.out.println("Sorting fails !!");
        }
    }
}
