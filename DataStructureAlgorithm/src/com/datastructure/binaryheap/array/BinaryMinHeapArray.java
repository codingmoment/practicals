package com.datastructure.binaryheap.array;

public class BinaryMinHeapArray {

  private int[] heapArray;
  private int sizeOfHeap;

  public BinaryMinHeapArray(int size) {
    this.heapArray = new int[size];
    this.sizeOfHeap = 0;
  }

  public int[] getHeapArray() {
    return heapArray;
  }

  public void setHeapArray(int[] heapArray) {
    this.heapArray = heapArray;
  }

  public int getSizeOfHeap() {
    return sizeOfHeap;
  }

  public void setSizeOfHeap(int sizeOfHeap) {
    this.sizeOfHeap = sizeOfHeap;
  }

  public int peekOfHeap() throws Exception {
    if (sizeOfHeap == 0) {
      throw new Exception("Heap is empty!");
    }
    return heapArray[1];
  }

  public int sizeOfHea() {
    return sizeOfHeap;
  }

  public void insert(int value) throws Exception {
    if (heapArray == null) {
      throw new Exception("Heap does not exist!");
    }

    heapArray[sizeOfHeap + 1] = value;
    sizeOfHeap++;

    heapifyBottomToTop(sizeOfHeap);
  }

  private void heapifyBottomToTop(int valueIndex) {
    int parentIndex = valueIndex / 2;

    if (parentIndex < 1) {
      // We are at root, no further heapify is required
      return;
    }

    // Compare value of parent and current node
    if (heapArray[parentIndex] > heapArray[valueIndex]) {
      // Swap
      int tempValue = heapArray[parentIndex];
      heapArray[parentIndex] = heapArray[valueIndex];
      heapArray[valueIndex] = tempValue;

      heapifyBottomToTop(parentIndex);
    }
  }

  public int extractMin() throws Exception {
    if (sizeOfHea() == 0) {
      throw new Exception("Heap is empty!");
    }

    int minNumber = heapArray[1];
    heapArray[1] = heapArray[sizeOfHeap];
    sizeOfHeap--;

    heapifyTopToBottom(1);

    return minNumber;
  }

  private void heapifyTopToBottom(int valueIndex) {
    int leftChildIndex = valueIndex * 2;
    int rightChildIndex = valueIndex * 2 + 1;

    if (sizeOfHeap < leftChildIndex) {
      // If there is no left child, this also means that there is also no right child. Nothing to compare.
      return;
    }
    else if (sizeOfHeap < rightChildIndex) {
      // There is left child, but not right child. So compare with only left child and return.
      if (heapArray[valueIndex] > heapArray[leftChildIndex]) {
        int temp = heapArray[valueIndex];
        heapArray[valueIndex] = heapArray[leftChildIndex];
        heapArray[leftChildIndex] = temp;
      }
      return;
    }
    else {
      // There are both left and right child
      int smallestChildIndex = heapArray[leftChildIndex] < heapArray[rightChildIndex] ? leftChildIndex : rightChildIndex;
      if (heapArray[valueIndex] > heapArray[smallestChildIndex]) {
        int temp = heapArray[valueIndex];
        heapArray[valueIndex] = heapArray[smallestChildIndex];
        heapArray[smallestChildIndex] = temp;

        heapifyTopToBottom(smallestChildIndex);
      }
    }
  }
  
  public void print() {
    for(int i = 1; i <= sizeOfHeap; i++) {
      System.out.print(heapArray[i] + " ");
    }
    System.out.println("\n");
  }
}
