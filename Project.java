public class Project {
    public static void main(String[] args) {
        int[] array = { 4, 1, 6, 9, 2, 3, 7, 8, 5 };
        // INSERTION SORT
        insertionSort(array);
        System.out.println("Insertion Sort: ");
        for (int i : array) {
            System.out.print(i + " ");
        }
        // ==== INSERTION SORT ====

        // MERGE SORT
        mergeSort(array);
        System.out.println("\nMerge Sort: ");
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

    static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }

    static void mergeSort(int[] array) {
        int length = array.length;
        if (length <= 1) {
            return;
        }

        int middle = length / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[length - middle];

        int i = 0;
        int j = 0;
        for (; i < length; i++) {
            if (i < middle) {
                leftArray[i] = array[i];
            } else {
                rightArray[j] = array[i];
                j++;
            }
        }
        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(leftArray, rightArray, array);
    }

    static void merge(int[] leftArray, int[] rightArray, int[] array) {
        int leftSize = array.length / 2;
        int rightSize = array.length - leftSize;
        int i = 0, l = 0, r = 0;

        while (l < leftSize && r < rightSize) {
            if (leftArray[l] < rightArray[r]) {
                array[i] = leftArray[l];
                i++;
                l++;
            } else {
                array[i] = rightArray[r];
                i++;
                r++;
            }
        }
        while (l < leftSize) {
            array[i] = leftArray[l];
            i++;
            l++;
        }
        while (r < rightSize) {
            array[i] = rightArray[r];
            i++;
            r++;
        }
    }
}
