package ex5;

import java.util.Random;

public class Test {

    static long tStart;
    static long tEnd;
    static long tDelta;
    static double elapsedSeconds;

    static void gnomeSort(int arr[], int n)
    {
        int index = 0;

        while (index < n) {
            if (index == 0)
                index++;
            if (arr[index] >= arr[index - 1])
                index++;
            else {
                int temp = 0;
                temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
                index--;
            }
        }
        return;
    }

    public static void cycleSort(int arr[], int n)
    {
        // count number of memory writes
        int writes = 0;

        // traverse array elements and put it to on
        // the right place
        for (int cycle_start = 0; cycle_start <= n - 2; cycle_start++) {
            // initialize item as starting point
            int item = arr[cycle_start];

            // Find position where we put the item. We basically
            // count all smaller elements on right side of item.
            int pos = cycle_start;
            for (int i = cycle_start + 1; i < n; i++)
                if (arr[i] < item)
                    pos++;

            // If item is already in correct position
            if (pos == cycle_start)
                continue;

            // ignore all duplicate elements
            while (item == arr[pos])
                pos += 1;

            // put the item to it's right position
            if (pos != cycle_start) {
                int temp = item;
                item = arr[pos];
                arr[pos] = temp;
                writes++;
            }

            // Rotate rest of the cycle
            while (pos != cycle_start) {
                pos = cycle_start;

                // Find position where we put the element
                for (int i = cycle_start + 1; i < n; i++)
                    if (arr[i] < item)
                        pos += 1;

                // ignore all duplicate elements
                while (item == arr[pos])
                    pos += 1;

                // put the item to it's right position
                if (item != arr[pos]) {
                    int temp = item;
                    item = arr[pos];
                    arr[pos] = temp;
                    writes++;
                }
            }
        }
    }

    public static void cocktailSort(int a[])
    {
        boolean swapped = true;
        int start = 0;
        int end = a.length;

        while (swapped == true) {
            // reset the swapped flag on entering the
            // loop, because it might be true from a
            // previous iteration.
            swapped = false;

            // loop from bottom to top same as
            // the bubble quickkSort
            for (int i = start; i < end - 1; ++i) {
                if (a[i] > a[i + 1]) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    swapped = true;
                }
            }

            // if nothing moved, then array is sorted.
            if (swapped == false)
                break;

            // otherwise, reset the swapped flag so that it
            // can be used in the next stage
            swapped = false;

            // move the end point back by one, because
            // item at the end is in its rightful spot
            end = end - 1;

            // from top to bottom, doing the
            // same comparison as in the previous stage
            for (int i = end - 1; i >= start; i--) {
                if (a[i] > a[i + 1]) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    swapped = true;
                }
            }

            // increase the starting point, because
            // the last stage would have moved the next
            // smallest number to its rightful spot.
            start = start + 1;
        }
    }

    public static void bubbleSort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    // swap arr[j+1] and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }

    static int partition(int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }

    public static void quickkSort(int arr[], int low, int high) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively quickkSort elements before
            // partition and after partition
            quickkSort(arr, low, pi - 1);
            quickkSort(arr, pi + 1, high);
        }
    }

        public static void printArray(int a[])
    {
        for (int i :a)
            System.out.print(i + " ");
        System.out.println();
    }

    public static int[] genereatePesymisticArray(int n){
        int[] result = new int[n];
        for(int i=0;i<n;i++){
            result[i]=n-i;
        }
        return result;
    }
    public static int[] genereateOptymisticArray(int n){
        int[] result = new int[n];
        for(int i=0;i<n;i++){
            result[i]=i;
        }
        return result;
    }
    public static int[] genereateRealisticArray(int n){
        int[] result = new int[n];
        Random generator = new Random();
        for(int i=0;i<n;i++){
            result[i]= generator.nextInt();
        }
        return result;
    }


    public static double testQuickSort(int[] array) {
        tStart = System.currentTimeMillis();
        Test.quickkSort(array,0,array.length-1);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        return elapsedSeconds;
    }

    public static double testBubbleSort(int[] array) {
        tStart = System.currentTimeMillis();
        Test.bubbleSort(array);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        return elapsedSeconds;
    }

    public static double testCycleSort(int[] array) {
        tStart = System.currentTimeMillis();
        Test.cycleSort(array,array.length);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        return elapsedSeconds;
    }

    public static double testGnomeSort(int[] array) {
        tStart = System.currentTimeMillis();
        Test.gnomeSort(array,array.length);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        return elapsedSeconds;
    }

    public static double testCoctailSort(int[] array) {
        tStart = System.currentTimeMillis();
        Test.cocktailSort(array);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        elapsedSeconds = tDelta / 1000.0;
        return elapsedSeconds;
    }







}
