package examples_ch12;

class SortBaseTeacher {

    protected Teacher[] values;
    public SortBaseTeacher(Teacher[] x) { values = x; }

    public boolean isAscendingSorted() {
        boolean s = true;
        for (int i = 0; i < values.length - 1; i++) {
            if (values[i].compareTo(values[i + 1]) > 0) {
                s = false;
                break;
            }
        }
        return s;
    }

    public void swap(int i, int j) {
        Teacher temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }

    public void sort() {
    }

    public void print() {
        for (int i = 0; i < values.length; i++) {
            values[i].print();
        }
        System.out.println();
    }
}
