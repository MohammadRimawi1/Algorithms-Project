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
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = temp;
        }
    }
}