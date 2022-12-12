import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.*;
import java.util.Random;


@FixMethodOrder(MethodSorters.JVM)
public class TestImageEditor {

    static int[][] mat = new int[2][2];

    ImageEditor obj;


    @Before
    public void populate(){
        obj = new ImageEditor(mat);
        Random r = new Random(1234);
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                mat[i][j] = r.nextInt(255);
            }
        }
    }

    @Test
    public void testDelete() {
        int temp = mat[0][0];
        obj.delete(0,0);
        Assert.assertEquals(0, mat[0][0]);
    }
    
    @Test (expected = IndexOutOfBoundsException.class) 
    public void testDeleteOutOfBoundsI() {
        obj.delete(3,0);
    }

    @Test (expected = IndexOutOfBoundsException.class) 
    public void testDeleteOutOfBoundsJ() {
        obj.delete(0,3);
    }

    @Test (expected = IndexOutOfBoundsException.class) 
    public void testDeleteOutOfBoundsINegative() {
        obj.delete(-1,0);
    }
    
    @Test (expected = IndexOutOfBoundsException.class) 
    public void testDeleteOutOfBoundsJNegative() {
        obj.delete(0,-1);
    }

    @Test 
    public void testBlur() { 
        int temp = mat[0][0];
        temp = (int)(temp * 0.7f);
        obj.blur(0,0,0.7f);
        Assert.assertEquals(temp, obj.mat[0][0]);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testBlurOutOfBoundsI() {
       obj.blur(3,0,0.5f);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testBlurOutOfBoundsJ() {
       obj.blur(0,3,0.5f);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testBlurOutOfBoundsINegative() {
       obj.blur(-1,0,0.5f);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testBlurOutOfBoundsJNegative() {
       obj.blur(0,-1,0.5f);
    }

    @Test (expected = IllegalArgumentException.class) 
    public void testBlurIllegalArgumentTooBig() {
        obj.blur(0,0,2f);
    }

    @Test (expected = IllegalArgumentException.class) 
    public void testBlurIllegalArgumentTooSmall() {
        obj.blur(0,0,-0.5f);
    }

    @Test 
    public void testSharpen() { 
        obj.sharpen(0,0,1.5f);
        Assert.assertEquals(237, obj.mat[0][0]);
    }

    @Test
    public void testSharpenOverflow() {
        System.out.println(Arrays.deepToString(obj.mat));
        obj.sharpen(0,0,5.0f);
        System.out.println(Arrays.deepToString(obj.mat));
        Assert.assertEquals(255, obj.mat[0][0]);
    }

    
    @Test (expected = IndexOutOfBoundsException.class)
    public void testSharpenOutOfBoundsI() {
       obj.sharpen(3,0,0.5f);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSharpenOutOfBoundsJ() {
       obj.sharpen(0,3,0.5f);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSharpenOutOfBoundsINegative() {
       obj.sharpen(-1,0,0.5f);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSharpenOutOfBoundsJNegative() {
       obj.sharpen(0,-1,0.5f);
    }

    @Test (expected = IllegalArgumentException.class) 
    public void testSharpenIllegalArgumentTooSmall() {
        obj.sharpen(0,0,0.5f);
    }

    @Test
    public void testRedo() {
        Assert.assertFalse(obj.redo());
    }
    
    @Test 
    public void testUndo() { 
        int temp = mat[0][0];
        obj.blur(0,0,0.7f);
        obj.undo();
        Assert.assertEquals(temp, obj.mat[0][0]);
    }

    @Test 
    public void testUndoTwice() { 
        int temp = mat[0][0];
        obj.blur(0,0,0.7f);
        obj.sharpen(0,0,1.5f);
        obj.undo();
        obj.undo();
        Assert.assertEquals(temp, obj.mat[0][0]);
    }

    @Test
    public void testUndoFalse() {
        Assert.assertFalse(obj.undo());
    }

    @Test 
    public void testUndoTwiceRedoTwice() { 
        int temp = mat[0][0];
        temp = (int)(temp * 0.7f);
        temp = (int)(temp * 1.5f);
       
        obj.blur(0,0,0.7f);
        obj.sharpen(0,0,1.5f);
        obj.undo();
        obj.undo();
        obj.redo();
        obj.redo();
        Assert.assertEquals(temp, obj.mat[0][0]);
    }

    @Test 
    public void testUndoTwiceRedoTwice2() {        
        obj.delete(0,0);
        obj.undo();
        obj.redo();
        obj.undo();
        obj.redo();
        Assert.assertEquals(0, obj.mat[0][0]);
    }

    @Test 
    public void testUndoRedo() { 
        int temp = mat[0][0];
        obj.sharpen(0,0,1.5f);
        obj.undo();
        obj.redo();
        Assert.assertEquals(237, obj.mat[0][0]);
    }



}
