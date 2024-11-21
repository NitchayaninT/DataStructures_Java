package examples_ch12;

class HeapSort extends SortBase {

    public HeapSort(int n)      { super(n); }
    public HeapSort(int[] x)    { super(x); }

    public void sort() {
        System.out.println("\n\n=== HEAP SORT ===");
        heapsort();
    }

    private void heapsort() {
        int size = values.length;
        // convert linear array to max-heap
        for (int i = size / 2; i >= 0; i--) {
            maxheapify(i, size);
        }
        System.out.print("Max-Heap   : ");
        print();
        System.out.println();

        // remove max & place it at the end
        for (int i = values.length - 1; i > 0; i--) {
            swap(0, i);
            maxheapify(0, i);
            System.out.print("Delete max : ");
            print();
        }
    }

    private void maxheapify(int root, int size) {
        int max = root;
        int left = (2 * root) + 1;	  // root's index starts at 0
        int right = left + 1;

        if (left < size && values[left] > values[root]) {
            max = left;
        }
        if (right < size && values[right] > values[max]) {
            max = right;
        }
        if (max != root) {
            swap(max, root);
            maxheapify(max, size);
        }
    }
}

class D12_5_HeapSort {

    public static void main(String[] args) {
        int[] X = {6, 8, 1, 4, 10, 5, 3, 9, 22};
        int[] Y = {1, 3, 3, 5, 6, 8, 9, 10, 22};	// already sorted
        int[] Z = {22, 10, 9, 8, 6, 5, 3, 3, 1};        // reverse order

        HeapSort HS = new HeapSort(X);
        //HeapSort HS = new HeapSort(10);
        System.out.println("Before sorting");
        HS.print();
        HS.sort();
        if (!HS.isAscendingSorted()) {
            System.out.println("Sorting fails !!");
        }
    }
}
