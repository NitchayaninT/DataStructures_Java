package examples_ch12;

class QuickSort extends SortBase {

    public QuickSort(int n)     { super(n); }
    public QuickSort(int[] x)   { super(x); }

    public void sort() {
        System.out.println("\n\n=== QUICK SORT ===");
        quicksort(0, values.length - 1, 0);
    }

    // variable depth keeps track of recursive depth
    private void quicksort(int left, int right, int depth) {
        if (left >= right) {
            return;
        }
        int mid = partition(left, right, depth + 1);
        quicksort(left, mid - 1, depth + 1);	// left half
        quicksort(mid + 1, right, depth + 1);	// right half
    }

    private int partition(int left, int right, int depth) {
        System.out.println();
        printDepth(depth);
        System.out.printf("original   = ");
        print(left, right);

        //int randomIndex = randomBetween(left, right);
        //swap(left, randomIndex);
        //System.out.println(); printDepth(depth);
        //System.out.printf("randomized = "); print(left, right);
        int pivot = values[left];
        int L = left, R = right + 1;

        while (L < R) // loop until L and R cross
        {
            while (L < right && values[++L] < pivot);
            while (R > left && values[--R] > pivot);

            if (L < R) {
                swap(L, R);
            }
        }

        // pivot is last & biggest member in left half
        // so, values[left..R] < values[R+1..right]
        swap(left, R);

        System.out.println();
        printDepth(depth);
        System.out.printf("partition  = ");
        print(left, R);
        System.out.printf(" < ");
        print(R + 1, right);
        System.out.println("\n");

        return R;
    }

    private void printDepth(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("     ");
        }
    }

    private int randomBetween(int left, int right) {
        int r;
        do {
            r = (int) (Math.random() * 100);
        } while (r < left || r > right);
        return r;
    }
}

class D12_7_QuickSort {

    public static void main(String[] args) {
        int[] X = {6, 8, 1, 4, 10, 5, 3, 9, 22};
        int[] Y = {1, 3, 3, 5, 6, 8, 9, 10, 22};	// already sorted
        int[] Z = {22, 10, 9, 8, 6, 5, 3, 3, 1};        // reverse order

        QuickSort QS = new QuickSort(X);
        //QuickSort QS = new QuickSort(10);
        System.out.println("Before sorting");
        QS.print();
        QS.sort();
        if (!QS.isAscendingSorted()) {
            System.out.println("Sorting fails !!");
        } else {
            System.out.println("\nAfter sorting");
            QS.print();
        }
    }
}
