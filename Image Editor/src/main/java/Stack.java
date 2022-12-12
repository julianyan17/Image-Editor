import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Julian Wai San Yan 
 * Date: 5/1/19
 * File: Stack.java 
 */

/** 
 * This file includes the Stack class that is a type of data structure. The
 * stack is implemented through an array. There are methods to add and remove
 * objects from the Stack.
 */

/**
 * This class contains methods to edit a Stack object. The Stack object contains 
 * an array that can shrink and increase in size. There is also methods to add
 * and remove multiple objects at the same time.
 */

class Stack<E> {

    float loadFactor;
    float shrinkFactor;
    int top; // Index of the top element
    E arr[];
    int capacity;
    
    private static final int TWO = 2;
    
    /**
     * Creates a Stack Object
     *
     * @param capacity capacity of the Stack
     * @return         Stack object
     */

    Stack(int capacity) {
        this.capacity = capacity;
        this.top = 0;
        this.arr = (E[]) new Object[capacity];
    }

    /**
     * Creates a Stack Object
     *
     * @param capacity   capacity of the Stack
     * @param loadFactor decides when to grow
     * @return           Stack object
     */
    
    Stack(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.top = 0;
        this.arr = (E[]) new Object[capacity];        
    }

    /**
     * Creates a Stack Object
     *
     * @param capacity     capacity of the Stack
     * @param loadFactor   decides when to grow
     * @param shrinkFactor decides when to shrink
     * @return             Stack object
     */

    Stack(int capacity, float loadFactor, float shrinkFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.shrinkFactor = shrinkFactor; 
        this.top = 0;
        this.arr = (E[]) new Object[capacity];        
    }

    /**
     * Whether or not the stack is empty
     *
     * @param none
     * @return     whether or not the stack is empty
     */

    boolean isEmpty() {
        // if array length is 0, return true
        if(top == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Getter for capacity of the stack
     *
     * @param none
     * @return     the capacity of the stack
     */

    int getCapacity() {
        return this.capacity;
    }

    /**
     * Adds an element into the Stack            
     *
     * @param x element to add into Stack
     * @return  none
     */

    void push(E x) throws StackOverflowError, NullPointerException {
        // throw StackOverflowError if Stack is full
        if(isFull() == true) {
            throw new StackOverflowError();
        }

        // throw NullPointerException if element passed in is null
        if(x == null) {
            throw new NullPointerException();
        }

        // if loadFactor is greater than zero and loadFactor has been reached
        if(loadFactor > 0 
                && (float)top / (float)capacity >= loadFactor) {
            // double the capacity
            this.capacity = capacity * TWO;
            // create a new array and fill in the array
            E[] tempArr = (E[]) new Object[capacity];
            for(int i = 0; i < arr.length; i++) {
                tempArr[i] = arr[i];
            }
            arr = tempArr;
        }
        arr[top] = x;
        top++;
    }

    /**
     * Removes an element from the Stack
     *
     * @param none
     * @return     the element that was removed
     */

    E pop() throws EmptyStackException {
        // throw EmptyStackException if Stack is empty
        if(isEmpty() == true) {
            throw new EmptyStackException();
        }

        // if shrinkFactor is greater than 0 and shrinkFactor has been reached
        if(shrinkFactor > 0 
                && (float)top / (float)capacity <= shrinkFactor) {
            // divide the capacity in half
            this.capacity = capacity / TWO;
            // create a new array and fill in the array
            E[] tempArr = (E[]) new Object[capacity];
            for(int i = 0; i < tempArr.length; i++) {
                tempArr[i] = arr[i];
            }
            arr = tempArr;
        }
        top--;
        // store the element to be removed
        E removedElement = arr[top];
        return removedElement;
    }

    /** 
     * Returns element at the top of the Stack without removing it 
     *
     * @param none
     * @return     element at the top of the Stack
     */

    E peek() throws EmptyStackException{
        // throw EmptyStackException if Stack is empty
        if(isEmpty() == true) {
            throw new EmptyStackException();
        }
        E topElement = arr[top - 1];
        return topElement;
    }

    /**
     * Empty the stack
     *
     * @param none
     * @return     none
     */

    void clear() {
        this.top = 0;
    }

    /**
     * Whether or not the stack is full
     *
     * @param none
     * @return     whether or not the stack is full
     */

    boolean isFull() {
        // if array length is equal to the capacity, return true
        if(top == this.capacity) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns the current size of the stack
     *
     * @param none
     * @return     current size of the stack
     */

    int currentSize() {
        return this.top;
    }

    /**
     * Removes multiple elements from the Stack
     *
     * @param k the number of elements to remove
     * @return  the elements that was removed
     */

    E[] multiPop(int k) throws EmptyStackException{
        // throw EmptyStackException if Stack is empty
        if(isEmpty() == true) {
            throw new EmptyStackException();
        }
        
        // if k is greater than currentSize, pop the entire array
        if(k > currentSize()) {
            k = currentSize();
        }

        E[] arrReturn = (E[]) new Object[k];
        for(int i = 0; i < k; i++) {
            arrReturn[i] = this.pop();
        }
        return arrReturn;
    }

    /**
     * Adds multiple elements into the Stack
     *
     * @param arr elements to add into Stack
     * @return    none
     */

    void multiPush(E[] arr) throws StackOverflowError, NullPointerException{
        // throw StackOverflowError if Stack is full
        if(isFull() == true) {
            throw new StackOverflowError();
        }

        // throw NullPointerException if element passed in is null
        if(arr == null) {
            throw new NullPointerException();
        }

        for(int i = 0; i < arr.length; i++) {
            this.push(arr[i]);
        }
    }

    /**
     * Reverse the elements in the Stack
     *
     * @param none 
     * @return     none
     */

    void reverse() throws EmptyStackException{
        // throw EmptyStackException if Stack is empty
        if(isEmpty() == true) {
            throw new EmptyStackException();
        }

        // reverse the elements starting from top in the Stack
        for(int i = 0; i < top / TWO; i++) {
            E temp = arr[i];
            arr[i] = arr[top - i - 1];
            arr[top - i - 1] = temp;
        }
    }

    /**
     * Add an element to the top of the Stack only if the current element at the
     * top is not the same as the element to be added
     *
     * @param x element to add to Stack
     * @return  if element was added to Stack
     */

    boolean pushUnique(E x) throws StackOverflowError, NullPointerException{
        // throw StackOverflowError if Stack is full
        if(isFull() == true) {
            throw new StackOverflowError();
        }

        // throw NullPointerException if element passed in is null
        if(arr == null) {
            throw new NullPointerException();
        }
        
        // if Stack is empty, push element
        if(isEmpty() == true) {
            push(x);
            return true;
        }

        // if top element is the same as the element to be added, return false
        if(this.peek().equals(x)) {
            return false;
        }

        push(x);
        return true;
    }

    /** 
     * Returns the distance of the element from the top of the Stack
     *
     * @param x element to search for 
     * @return  distance of the element from the top of the Stack, -1 if it
     *          doesn't exist
     */

    int search(E x) throws EmptyStackException{
        // throw EmptyStackException if Stack is empty
        if(isEmpty() == true) {
            throw new EmptyStackException();
        }

        // searches for the element in the Stack
        for(int i = top - 1; i >= 0; i--) {
            if(arr[i].equals(x)) {
                return top - i;
            }
         }
         return -1;
    }
}
