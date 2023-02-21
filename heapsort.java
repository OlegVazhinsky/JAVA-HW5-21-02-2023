import java.util.Arrays;
import java.util.Random;

// Реализовать алгоритм пирамидальной сортировки (HeapSort)

public class heapsort{
    public static void main(String[] args){
        int[] array = getRandomArrayList(20, 10);
        showArray(array, "Исходный массив:");
        heapSort(array);
        showArray(array, "После пирамидальной сортировки:");
        // averageTimeExecution(1_000, 1_000_000);
        // Для 1000000 элементов в массиве и 1000 повторений среднее время сортировки составило 163 мс.
    }

    public static void averageTimeExecution(int numberOfExecutions, int numberOfElements){
        int averageTimeExecution = 0;
        for (int i = 0; i < numberOfExecutions; i++){
            int[] array = getRandomArrayList(numberOfElements, 10);
            long startTime = System.nanoTime();
            heapSort(array);
            long endTime = System.nanoTime();
            int duration = (int) (endTime - startTime) / 1_000_000;
            averageTimeExecution = averageTimeExecution + duration;
        }
        System.out.println("Для " + numberOfElements + " элементов в массиве и " + numberOfExecutions + " повторений среднее время сортировки составило " + averageTimeExecution / numberOfExecutions + " мс.");
    }

    public static void swapElements(int[] array, int i, int j){
        int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
    }

    // метод формирования дерева потомков
    public static void isDescendantGrater(int[] array, int start, int stop){
        // найти индексы потомков родителя
        int parent = start;
        while((parent * 2 + 1) <= stop){
            int descendant = 2 * parent + 1;
            //System.out.println("parent = " + parent + " descendant = " + descendant + " start = " + start + " stop = " + stop);
            if (descendant + 1 <= stop && array[descendant + 1] > array[descendant]){
                descendant = descendant + 1;
            }
            if (array[parent] < array[descendant]){
                swapElements(array, parent, descendant);
                parent = descendant;
            }
            else{
                return;
            }
        }
    }

    // метод реализующий сортировку
    public static void heapSort(int[] array){
        int size = array.length;
        // выстроить кучу
        for (int i = (size - 2) / 2; i >= 0; i--){
            isDescendantGrater(array, i, size - 1);
        }
        // поменять элементы, перестроить кучу
        for(int i = size - 1; i >= 1; i--){
            swapElements(array, i, 0);
            isDescendantGrater(array, 0, i - 1);
        }
    }

    // метод вывода массива с сообщнием
    public static void showArray(int[] array, String message){
        System.out.println(message);
        System.out.println(Arrays.toString(array));
    }
    
    // метод генерации случайного массива
    public static int[] getRandomArrayList(int arraySize, int maxRandomValue){
        int[] array = new int[arraySize];
        Random element = new Random();
        for (int i = 0; i < arraySize; i++){
            array[i] = element.nextInt(maxRandomValue);
        }
        return array;
    }
}