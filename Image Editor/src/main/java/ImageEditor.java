import java.util.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Julian Wai San Yan
 * Date: 5/1/19
 * File: ImageEditor.java 
 */

/**
 * This file includes the ImageEditor class that will allow the editing of a
 * grayscale image. Edits include deleting, sharpening and blurring a pixel of
 * an image. It is also possible to undo and redo edits made on a pixel.
 */

/**
 * This class allows the editing of a grayscale image. Included in this class
 * are methods that can delete, sharpen and blur a pixel in an image. The edits
 * made on the pixel can also be undo-ed and redo-ed. These functions are made 
 * possible through the implementation of a Stack object.
 */

public class ImageEditor {

    int[][] mat;

    // stack to store all edits made
    private Stack<int[][]> editStack;

    // stack to store all undos made
    private Stack<int[][]> redoStack;

    private static final int GRAYSCALE_FACTOR = 255;
    private static final int CAPACITY = 10;
    private static final float LOADFACTOR = 0.5f;
    private static final float SHRINKFACTOR = 0.5f;


    /**
     * Create an ImageEditor object
     *
     * @param mat the pixels of the image in a 2d array
     * @return    ImageEditor object
     */

    public ImageEditor(int[][] mat) {
        this.mat = mat;
        this.editStack = new Stack(CAPACITY,LOADFACTOR,SHRINKFACTOR);
        this.redoStack = new Stack(CAPACITY,LOADFACTOR,SHRINKFACTOR);
    }

    /**
     * Delete a pixel from the image by setting it to 0
     *
     * @param i the row of the pixel
     * @param j the column of the pixel
     * @return  void
     */

    void delete(int i, int j) throws IndexOutOfBoundsException{

        // throw an exception if the coordinate of the pixel doesn't exist
        if(i >= this.mat.length || j >= this.mat[0].length) {
            throw new IndexOutOfBoundsException();
        }

        // throw an exception if the coordinate of the pixel is negative
        if(i < 0 || j < 0) {
            throw new IndexOutOfBoundsException();
        }
        
        // add new image to editStack
        this.editStack.push(this.deepCopy());

        // delete the pixel
        this.mat[i][j] = 0;

        // clear the redoStack
        this.redoStack.clear();
    }

    /**
     * Blur a pixel from the image
     *
     * @param i          the row of the pixel
     * @param j          the column of the pixel
     * @param blurFactor the factor to blur the pixel by
     * @return  void
     */

    void blur(int i, int j, float blurFactor) throws IndexOutOfBoundsException,
            IllegalArgumentException{

        // throw an exception if the coordinate of the pixel doesn't exist
        if(i >= mat.length || j >= mat[0].length) {
            throw new IndexOutOfBoundsException();
        }

        // throw an exception if the coordinate of the pixel is negative
        if(i < 0 || j < 0) {
            throw new IndexOutOfBoundsException();
        }
        
        // throw an exception if blurFactor is less than 0 or greater than 1
        if(blurFactor < 0 || blurFactor > 1) {
            throw new IllegalArgumentException();
        }

        // add new image to editStack
        this.editStack.push(this.deepCopy());

        // blur the pixel
        int newValue = (int)(this.mat[i][j] * blurFactor);
        this.mat[i][j] = newValue; 
    
        // clear the redoStack
        this.redoStack.clear();
    }

    void sharpen(int i, int j, float sharpenFactor) throws
        IndexOutOfBoundsException, IllegalArgumentException{

        // throw an exception if the coordinate of the pixel doesn't exist
        if(i >= mat.length || j >= mat[0].length) {
            throw new IndexOutOfBoundsException();
        }

        // throw an exception if the coordinate of the pixel is negative
        if(i < 0 || j < 0) {
            throw new IndexOutOfBoundsException();
        }

        // throw an exception if sharpenFactor is less than 1
        if(sharpenFactor < 1) {
            throw new IllegalArgumentException();
        }

        // add new image to editStack
        this.editStack.push(this.deepCopy());
        
        // sharpen the pixel
        int newValue = (int)(mat[i][j] * sharpenFactor);
        
        // if newValue is greater than 255
        if(newValue > GRAYSCALE_FACTOR) {
            newValue = GRAYSCALE_FACTOR;
        }

        this.mat[i][j] = newValue;
        

        // clear the redoStack
        this.redoStack.clear();
    }
    
    /**
     * Undo an edit
     * 
     * @param  none
     * @return     none
     */

    boolean undo() {
        // if editStack is empty, return false
        if(this.editStack.isEmpty() == true) {
            return false;
        }

        // add current image to redoStack
        this.redoStack.push(this.deepCopy());

        // set current mat to the previous edit and remove from editStack
        this.mat = this.editStack.pop();
        return true;
    }
    
    /**
     * Redo an edit
     * @param  none
     * @return     none
     */

    boolean redo() {
        // if redoStack is empty, return false
        if(this.redoStack.isEmpty() == true) {
            return false;
        }
        
        // add current image to editStack 
        this.editStack.push(this.deepCopy());

        // set current mat to the edit that was made before undo and remove
        // from editStack
        this.mat = this.redoStack.pop();
        return true; 
    }

    /**
     * Getter for the image
     *
     * @param none 
     * @return    the image that was made
     */

    int[][] getImage() {
        return this.mat;
    }

    /**
     * Deep copy of the image
     *
     * @param none 
     * @return    the deep copy of the image
     */

    private int[][] deepCopy() {
        
        // make a deep copy of the image
        int[][] copyMat = new int[mat.length][mat[0].length];
        for(int k = 0; k < mat.length; k++) {
            for(int l = 0; l < mat[0].length; l++) {
                copyMat[k][l] = mat[k][l];
            }
        }
        return copyMat; 
    }

}
