public class Project {
    public static void main(String[] args) {
        int[] array = { 4, 1, 6, 9, 2, 3, 7, 8, 5 };

        insertionSort(array);

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

  
    public static void randomizedQuickSort(int[] arr) {
        randomizedQuickSort(arr, 0, arr.length - 1);
    }

    private static void randomizedQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = randomizedPartition(arr, low, high);

            randomizedQuickSort(arr, low, pivotIndex - 1);
            randomizedQuickSort(arr, pivotIndex + 1, high);
        }
    }

private static int randomizedPartition(int[] arr, int low, int high) {

    int randomIndex = low + (int)(Math.random() * (high - low + 1));


    int temp = arr[randomIndex];
    arr[randomIndex] = arr[high];
    arr[high] = temp;

  
    return partition(arr, low, high);

    
}
private static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = low - 1;

    for (int j = low; j < high; j++) {
        if (arr[j] < pivot) {
            i++;
            int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
        }
    }

    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;

    return i + 1;
}

}