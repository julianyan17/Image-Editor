Author: Julian Yan  
Date: 5/1/2019  
Title: Image Editor  
Summary: This program allows the editing of a grayscale image by implementing a stack.

Contents: 
* src
  * main
    * ImageEditor.java
      * This file includes the ImageEditor class that will allow the editing of a grayscale image.
        * ImageEditor(int[][] mat)
        * void delete(int i, int j)
        * void blur(int i, int j, float blurFactor)
        * void sharpen(int i, int j, float sharpenFactor)
        * boolean undo()
        * boolean redo()
        * int[][] getImage()
        * int[][] deepCopy()
    * Stack.java
      * This file includes the Stack class that is implemented through an array. 
        * Stack(int capacity)
        * Stack(int capacity, float loadFactor)
        * Stack(int capacity, float loadFactor, float shrinkFactor)
        * boolean isEmpty()
        * int getCapacity()
        * void push(E x)
        * E pop()
        * E peek()
        * void clear()
        * boolean isFull()
        * int currentSize()
        * E[] multiPop(int k)
        * void multiPush(E[] arr)
        * void reverse()
        * boolean pushUnique(E x)
        * int search(E x)
  * test
    * TestImageEditor.java
    * TestMyBasic.java
    * TestMyResize.java
