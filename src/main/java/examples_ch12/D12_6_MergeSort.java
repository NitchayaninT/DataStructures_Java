package examples_ch12;

class MergeSort extends SortBase {

    public MergeSort(int n)     { super(n); }
    public MergeSort(int[] x)   { super(x); }

    public void sort() {
        System.out.println("\n\n=== MERGE SORT ===");
        mergesort(0, values.length - 1, 0);
    }

    // variable depth keeps track of recursive depth
    private void mergesort(int left, int right, int depth) {
        if (left >= right) {
            return;                                     // stop recursive call
        }
        int mid = (left + right) / 2;
        mergesort(left, mid, depth + 1);		// left half
        mergesort(mid + 1, right, depth + 1);		// right half
        merge(left, mid, mid + 1, right, depth + 1);
    }

    private void merge(int leftFirst, int leftLast,
            int rightFirst, int rightLast, int depth) {
        printDepth(depth);
        System.out.printf("left  = ");
        print(leftFirst, leftLast);
        System.out.println();
        printDepth(depth);
        System.out.printf("right = ");
        print(rightFirst, rightLast);

        int[] temp = new int[values.length];
        int i = leftFirst, backupFirst = leftFirst;

        // compare left and right halves
        while (leftFirst <= leftLast && rightFirst <= rightLast) {
            if (values[leftFirst] < values[rightFirst]) {
                temp[i++] = values[leftFirst++];
            } else {
                temp[i++] = values[rightFirst++];
            }
        }

        // copy remaining data in left half
        while (leftFirst <= leftLast) {
            temp[i++] = values[leftFirst++];
        }

        // copy remaining data in right half
        while (rightFirst <= rightLast) {
            temp[i++] = values[rightFirst++];
        }

        // copy data from temp back to values
        for (int j = backupFirst; j <= rightLast; j++) {
            values[j] = temp[j];
        }

        System.out.println();
        printDepth(depth);
        System.out.printf("merge = ");
        print(backupFirst, rightLast);
        System.out.println("\n");
    }

    private void printDepth(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("     ");
        }
    }
}

class D12_6_MergeSort {

    public static void main(String[] args) {
        int[] X = {6, 8, 1, 4, 10, 5, 3, 9, 22};
        int[] Y = {1, 3, 3, 5, 6, 8, 9, 10, 22};	// already sorted
        int[] Z = {22, 10, 9, 8, 6, 5, 3, 3, 1};        // reverse order

        MergeSort MS = new MergeSort(X);
        //MergeSort MS = new MergeSort(10);
        System.out.println("Before sorting");
        MS.print();
        MS.sort();
        if (!MS.isAscendingSorted()) {
            System.out.println("Sorting fails !!");
        }
    }
}
