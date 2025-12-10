import java.util.Random;

public class Project {
    public static void main(String[] args) {
        final int BILLION = 1_000_000_000;
        int[] sizes = { 1000, 10000, 50000, 100000 };

        String[] datasetNames = {
                "Random Data",
                "Sorted Data",
                "Reverse-Sorted Data",
                "Few Unique Values"
        };

        System.out.println("SORTING ALGORITHMS BENCHMARK");
        System.out.println("====================================\n");

        for (int size : sizes) {

            System.out.println("====================================");
            System.out.println(" DATASET SIZE: " + size);
            System.out.println("====================================\n");

            for (int type = 0; type < 4; type++) {

                System.out.println("Dataset Type: " + datasetNames[type]);
                System.out.println("------------------------------------");

                int[] original = generateDataset(type, size);

                benchmarkAlgorithms(original, BILLION);

                System.out.println();
            }
            System.out.println();
        }
    }

    // ====== BENCHMARKING ======
    static void benchmarkAlgorithms(int[] original, final int BILLION) {

        int[] a1 = original.clone();
        int[] a2 = original.clone();
        int[] a3 = original.clone();
        int[] a4 = original.clone();
        
        System.out.printf("%-25s %-15s%n", "Algorithm", "Time (s)");
        System.out.println("-------------------------------------------");

        // Insertion Sort
        long start = System.nanoTime();
        insertionSort(a1);
        long end = System.nanoTime();
        System.out.printf("%-25s %.6f%n", "Insertion Sort", (end - start) / (double) BILLION);

        // Merge Sort
        start = System.nanoTime();
        mergeSort(a2);
        end = System.nanoTime();
        System.out.printf("%-25s %.6f%n", "Merge Sort", (end - start) / (double) BILLION);

        // Heap Sort
        start = System.nanoTime();
        heapSort(a3);
        end = System.nanoTime();
        System.out.printf("%-25s %.6f%n", "Heap Sort", (end - start) / (double) BILLION);

        // QuickSort
        start = System.nanoTime();
        quickSort(a4, 0, a4.length - 1);
        end = System.nanoTime();
        System.out.printf("%-25s %.6f%n", "QuickSort", (end - start) / (double) BILLION);

    }
    // ====== BENCHMARKING ======

    // ====== GENERATE FUNCTIONS ======
    static int[] generateDataset(int type, int size) {
        if (type == 0) {
            return generateRandom(size);
        }
        if (type == 1) {
            return generateSorted(size);
        }
        if (type == 2) {
            return generateReverse(size);
        }
        return generateFewUnique(size);
    }

    static int[] generateRandom(int size) {
        Random r = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = r.nextInt(1_000_000);
        }
        return arr;
    }

    static int[] generateSorted(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }

    static int[] generateReverse(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        return arr;
    }

    static int[] generateFewUnique(int size) {
        Random r = new Random();
        int[] arr = new int[size];
        int[] values = { 1, 2, 3, 4, 5 };
        for (int i = 0; i < size; i++) {
            arr[i] = values[r.nextInt(5)];
        }
        return arr;
    }
    // ====== GENERATE FUNCTIONS ======

    // ====== SORTING ALGORITHMS ======
    // Insertion Sort
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
    // ==== Insertion Sort ====

    // Merge Sort
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
    // ==== Merge Sort ====

    // QuickSort
    static void quickSort(int[] array, int start, int end) {
        if (start >= end)
            return;
        boolean allEqual = true;
        for (int i = start + 1; i <= end; i++) {
            if (array[i] != array[start]) {
                allEqual = false;
                break;
            }
        }
        if (allEqual)
            return;
        int pivot = normalPartition(array, start, end);
        quickSort(array, start, pivot - 1);
        quickSort(array, pivot + 1, end);
    }

    static int normalPartition(int[] array, int start, int end) {
  
        int randomPivot = start + (int) (Math.random() * (end - start + 1));
        int tempPivot = array[randomPivot];
        array[randomPivot] = array[end];
        array[end] = tempPivot;
        int pivot = array[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = temp;
        return i + 1;
    }
    // ==== QuickSort ====

    // Heap Sort
    public static void heapSort(int[] arr) {
        int n = arr.length;

  
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

      
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest])
            largest = left;

        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }
    // ==== Heap Sort ====

    // ====== SORTING ALGORITHMS ======
}
