
## ALGOBENGORO

**Sorty**
- Bubble
- Insertion & Insertion + binary
- Quick

**Searche**
- Linear
- Binary

**-----------------------------------------------------------------------------------------------------------------------------**

### Bubble Sort

Simplest sorting algorithm that works by repeatedly swapping the adjacent elements if they are in wrong order

**Example**

>First Pass:  
( 5 1 4 2 8 ) –> ( 1 5 4 2 8 ), Swap since 5 > 1  
( 1 5 4 2 8 ) –>  ( 1 4 5 2 8 ), Swap since 5 > 4  
( 1 4 5 2 8 ) –>  ( 1 4 2 5 8 ), Swap since 5 > 2  
( 1 4 2 5 8 ) –> ( 1 4 2 5 8 ), No swap since 5 < 8  

>Second Pass:  
( 1 4 2 5 8 ) –> ( 1 4 2 5 8 )  
( 1 4 2 5 8 ) –> ( 1 2 4 5 8 ), Swap since 4 > 2  
( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )  
( 1 2 4 5 8 ) –>  ( 1 2 4 5 8 ) 

**JS**  
```javascript
let bubbleSort = (inputArr) => {
    let len = inputArr.length;
    for (let i = 0; i < len; i++) {
        for (let j = 0; j < len; j++) {
            if (inputArr[j] > inputArr[j + 1]) {
                let tmp = inputArr[j];
                inputArr[j] = inputArr[j + 1];
                inputArr[j + 1] = tmp;
            }
        }
    }
    return inputArr;
};
```
**JAVA**  
```java
void bubbleSort(int arr[]) 
    { 
        int n = arr.length; 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (arr[j] > arr[j+1]) 
                { 
                    // swap arr[j+1] and arr[j] 
                    int temp = arr[j]; 
                    arr[j] = arr[j+1]; 
                    arr[j+1] = temp; 
                } 
    } 
```


### Insertion Sort

Insertion sort is a simple sorting algorithm that works similar to the way you sort playing cards in your hands. The array is virtually split into a sorted and an unsorted part. Values from the unsorted part are picked and placed at the correct position in the sorted part.


**Example**

To sort an array of size n in ascending order:  
1: Iterate from arr[1] to arr[n] over the array.  
2: Compare the current element (key) to its predecessor.  
3: If the key element is smaller than its predecessor, compare it to the elements before. Move the greater elements one position up to make space for the swapped element.


>**12**, 11, 13, 5, 6  
Let us loop for i = 1 (second element of the array) to 4 (last element of the array)  
i = 1; Since 11 is smaller than 12, move 12 and insert 11 before 12  
**11**, **12**, 13, 5, 6  
i = 2; 13 will remain at its position as all elements in A[0..I-1] are smaller than 13  
**11**, **12**, **13**, 5, 6  
i = 3; 5 will move to the beginning and all other elements from 11 to 13 will move one position   ahead of their current position.  
**5**, **11**, **12**, **13**, 6   
i = 4; 6 will move to position after 5, and elements from 11 to 13 will move one position ahead of their current position.  
**5, 6, 11, 12, 13** 


**JS**  
```javascript
function insertionSort(inputArr) {
    let n = inputArr.length;
        for (let i = 1; i < n; i++) {
            // Choosing the first element in our unsorted subarray
            let current = inputArr[i];
            // The last element of our sorted subarray
            let j = i-1; 
            while ((j > -1) && (current < inputArr[j])) {
                inputArr[j+1] = inputArr[j];
                j--;
            }
            inputArr[j+1] = current;
        }
    return inputArr;
}
```
**JAVA**  
```java
/*Function to sort array using insertion sort*/
    void sort(int arr[]) 
    { 
        int n = arr.length; 
        for (int i = 1; i < n; ++i) { 
            int key = arr[i]; 
            int j = i - 1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j >= 0 && arr[j] > key) { 
                arr[j + 1] = arr[j]; 
                j = j - 1; 
            } 
            arr[j + 1] = key; 
        } 
    } 
```


### QuickSort

https://www.youtube.com/watch?v=Hoixgm4-P4M  


![Image](https://www.guru99.com/images/1/011019_1052_QuickSortAl2.png)  
**JS**  
```javascript
var items = [5,3,7,6,2,9];
function swap(items, leftIndex, rightIndex){
    var temp = items[leftIndex];
    items[leftIndex] = items[rightIndex];
    items[rightIndex] = temp;
}
function partition(items, left, right) {
    var pivot   = items[Math.floor((right + left) / 2)], //middle element
        i = left, //left pointer
        j = right; //right pointer
    while (i <= j) {
        while (items[i] < pivot) {
            i++;
        }
        while (items[j] > pivot) {
            j--;
        }
        if (i <= j) {
            swap(items, i, j); //sawpping two elements
            i++;
            j--;
        }
    }
    return i;
}

function quickSort(items, left, right) {
    var index;
    if (items.length > 1) {
        index = partition(items, left, right); //index returned from partition
        if (left < index - 1) { //more elements on the left side of the pivot
            quickSort(items, left, index - 1);
        }
        if (index < right) { //more elements on the right side of the pivot
            quickSort(items, index, right);
        }
    }
    return items;
}
// first call to quick sort
var sortedArray = quickSort(items, 0, items.length - 1);
console.log(sortedArray); //prints [2,3,5,6,7,9]
```
**JAVA**  
```java
 /* This function takes last element as pivot, 
       places the pivot element at its correct 
       position in sorted array, and places all 
       smaller (smaller than pivot) to left of 
       pivot and all greater elements to right 
       of pivot */
    int partition(int arr[], int low, int high) 
    { 
        int pivot = arr[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than the pivot 
            if (arr[j] < pivot) 
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
  
  
    /* The main function that implements QuickSort() 
      arr[] --> Array to be sorted, 
      low  --> Starting index, 
      high  --> Ending index */
    void sort(int arr[], int low, int high) 
    { 
        if (low < high) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(arr, low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
            sort(arr, low, pi-1); 
            sort(arr, pi+1, high); 
        } 
    } 
```


### LINEAR SEARCH 


```java
 int n = arr.length; 
        for (int i = 0; i < n; i++)  
        { 
            if (arr[i] == x) 
                return i; 
        } 
```


### BINARY SEARCH  
**JS**

```javascript
function binarySearch(sortedArray, key){
    let start = 0;
    let end = sortedArray.length - 1;

    while (start <= end) {
        let middle = Math.floor((start + end) / 2);

        if (sortedArray[middle] === key) {
            // found the key
            return middle;
        } else if (sortedArray[middle] < key) {
            // continue searching to the right
            start = middle + 1;
        } else {
            // search searching to the left
            end = middle - 1;
        }
    }
	// key wasn't found
    return -1;
}
```


**JAVA**  
```java
// Java implementation of recursive Binary Search 
class BinarySearch { 
    // Returns index of x if it is present in arr[l.. 
    // r], else return -1 
    int binarySearch(int arr[], int l, int r, int x) 
    { 
        if (r >= l) { 
            int mid = l + (r - l) / 2; 
  
            // If the element is present at the 
            // middle itself 
            if (arr[mid] == x) 
                return mid; 
  
            // If element is smaller than mid, then 
            // it can only be present in left subarray 
            if (arr[mid] > x) 
                return binarySearch(arr, l, mid - 1, x); 
  
            // Else the element can only be present 
            // in right subarray 
            return binarySearch(arr, mid + 1, r, x); 
        } 
  
        // We reach here when element is not present 
        // in array 
        return -1; 
    } 
  
    // Driver method to test above 
    public static void main(String args[]) 
    { 
        BinarySearch ob = new BinarySearch(); 
        int arr[] = { 2, 3, 4, 10, 40 }; 
        int n = arr.length; 
        int x = 10; 
        int result = ob.binarySearch(arr, 0, n - 1, x); 
        if (result == -1) 
            System.out.println("Element not present"); 
        else
            System.out.println("Element found at index " + result); 
    } 
} 
```



**-----------------------------------------------------------------------------------------------------------------------------**

### Programovacie Jazyky 

 A programming language is a formal language comprising a set of instructions that produce various kinds of output. Programming languages are used in computer programming to implement algorithms.

**Compiled** - proccessed by compilers, compiles down to machine code and runs at once  (java)  
**Interpreted** - line by line execute (js)


**-----------------------------------------------------------------------------------------------------------------------------** 

### DÁTOVE ŠTRUKTURY

A data structure is a particular way of organizing data in a computer so that it can be used effectively.

**Array**

An array is a collection of items stored at contiguous memory locations. The idea is to store multiple items of the same type together. 


**Linked List**

A linked list is a linear data structure, in which the elements are not stored at contiguous memory locations. The elements in a linked list are linked using pointers as shown in the below image:

![Image](https://media.geeksforgeeks.org/wp-content/cdn-uploads/gq/2013/03/Linkedlist.png)  

In simple words, a linked list consists of nodes where each node contains a data field and a reference(link) to the next node in the list.  

**STACK**

Stack is a linear data structure which follows a particular order in which the operations are performed. The order may be LIFO(Last In First Out) or FILO(First In Last Out).  

![Image](https://media.geeksforgeeks.org/wp-content/cdn-uploads/gq/2013/03/stack.png)

There are many real-life examples of a stack. Consider an example of plates stacked over one another in the canteen. The plate which is at the top is the first one to be removed, i.e. the plate which has been placed at the bottommost position remains in the stack for the longest period of time. So, it can be simply seen to follow LIFO(Last In First Out)/FILO(First In Last Out) order.

**QUEUE**

A Queue is a linear structure which follows a particular order in which the operations are performed. The order is First In First Out (FIFO). A good example of a queue is any queue of consumers for a resource where the consumer that came first is served first. The difference between stacks and queues is in removing. In a stack we remove the item the most recently added; in a queue, we remove the item the least recently added.A Queue is a linear structure which follows a particular order in which the operations are performed. The order is First In First Out (FIFO). A good example of a queue is any queue of consumers for a resource where the consumer that came first is served first. The difference between stacks and queues is in removing. In a stack we remove the item the most recently added; in a queue, we remove the item the least recently added.


![Image](https://media.geeksforgeeks.org/wp-content/cdn-uploads/gq/2014/02/Queue.png)



**-----------------------------------------------------------------------------------------------------------------------------** 

### OOP  
Velmi pekne vysvetlene tu je :)) - https://www.freecodecamp.org/news/object-oriented-programming-concepts-21bb035f7260/
SPSE - https://www.spse-po.sk/uploads/curricula_materials/attachments/4-oop-teoria-0.pdf



**-----------------------------------------------------------------------------------------------------------------------------** 


### JAVA FX

- maximalne si mozes kuknut stare veci jak sa to robilo lebo teria k tomuto doslova neexistovala pre nas




**-----------------------------------------------------------------------------------------------------------------------------** 


### SPECIAL BEATS

**Threads**

https://www.spse-po.sk/admin/download/signed/50934df8df27458c7520fc037167011022516c80?model=curricula_materials&field=attachments&file=vlakna-thread.ppsx

https://www.spse-po.sk/admin/download/signed/30fe0a84b1777cf368d35fbb8a9bcdc1cad186ce?model=curricula_materials&field=attachments&file=vlakna-thread-2.ppsx

**Eventhandling**
Neviem co tym autor chcel povedat

**Sietove App** 
Sme robili jednu vec (reakcnu dobu) takze asi tolko k tomu. 

**-----------------------------------------------------------------------------------------------------------------------------** 
