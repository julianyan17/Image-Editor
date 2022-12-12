import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.EmptyStackException;
import java.util.*;


@FixMethodOrder(MethodSorters.JVM)
public class TestMyBasic {

    static Stack<Integer> obj = null;

    @Before
    public void testPush() {
        obj = new Stack(10);
        for(int i=0;i<6;i++)
            obj.push(new Integer(i));
    }

    @Test
    public void testPop() {        
        Assert.assertEquals(obj.pop(), new Integer(5));
    }

    @Test
    public void testIsEmptyTrue() { 
        obj = new Stack(0);
        Assert.assertTrue(obj.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        Assert.assertFalse(obj.isEmpty());
    }

    @Test 
    public void testGetCapacity() {
        Assert.assertEquals(10, obj.getCapacity());
    }

    @Test (expected = StackOverflowError.class) 
    public void testPushFull() {
        obj = new Stack(2);
        obj.push(new Integer(0));
        obj.push(new Integer(1));

        obj.push(new Integer(2));
    }

    @Test (expected = NullPointerException.class) 
    public void testPushNull() {
        obj.push(null);
    }

    @Test 
    public void testPushLoadFactorEqual() {
        obj = new Stack(10,0.5f);
        for(int i = 0; i < 5; i++) {
            obj.push(new Integer(i));
        }

        obj.push(new Integer(6));
        Assert.assertEquals(20, obj.getCapacity());
    }

    @Test
    public void testPushLoadFactor() {
        obj = new Stack(10,0.5f);
        for(int i=0;i<6;i++)
            obj.push(new Integer(i));
        Assert.assertEquals(20, obj.getCapacity());
    }

    @Test 
    public void testPushCheck() { 
        obj = new Stack(10,0.5f);
        for(int i=0;i<4;i++)
            obj.push(new Integer(i));

        obj.push(new Integer(5));
        Assert.assertEquals(10, obj.getCapacity());
    }

    @Test
    public void testPopShrinkFactor() {  
        obj = new Stack(6,0, 0.5f);
        for(int i=0;i<2;i++)
            obj.push(new Integer(i));

        Assert.assertEquals(obj.pop(), new Integer(1));
        Assert.assertEquals(3, obj.getCapacity());
        Assert.assertEquals(1, obj.top);
    }

    @Test
    public void testPopShrinkFactorEqual() {  
        obj = new Stack(6,0, 0.5f);
        for(int i=0;i<2;i++)
            obj.push(new Integer(i));

        Assert.assertEquals(obj.pop(), new Integer(1));
        Assert.assertEquals(3, obj.getCapacity());
    }

    @Test 
    public void popTester() { 
        System.out.println(Arrays.toString(obj.arr));
        System.out.println(obj.top);
        obj.pop();
        System.out.println(Arrays.toString(obj.arr));
        System.out.println(obj.top);
        obj.push(6);
        System.out.println(Arrays.toString(obj.arr));
    }

    @Test
    public void testPopPush() {  
        obj = new Stack(6,0, 0.5f);
        for(int i=0;i<2;i++)
            obj.push(new Integer(i));
        //System.out.println(Arrays.toString(obj.arr));
        Assert.assertEquals(obj.pop(), new Integer(1));
        //System.out.println(Arrays.toString(obj.arr));
        obj.push(3);
        //System.out.println(Arrays.toString(obj.arr));        
        Assert.assertEquals(2, obj.currentSize());
        Assert.assertEquals(3, obj.getCapacity());
    }

    @Test
    public void testPopOddShrinkFactor() {  
        obj = new Stack(7,0, 0.5f);
        for(int i=0;i<3;i++)
            obj.push(new Integer(i));

        Assert.assertEquals(obj.pop(), new Integer(2));
        Assert.assertEquals(3, obj.getCapacity());
    }

    @Test (expected = EmptyStackException.class)
    public void testPeekEmpty() { 
        obj = new Stack(2);
        obj.peek();
    }

    @Test
    public void testPeek() {
        Assert.assertEquals(obj.peek(), new Integer(5));
        Assert.assertEquals(obj.pop(), new Integer(5));
    }

    @Test
    public void testClear() {
        obj.clear();
        Assert.assertEquals(10, obj.getCapacity());
        Assert.assertEquals(0,obj.top);
    }

    @Test
    public void testIsFullFalse() {
        Assert.assertFalse(obj.isFull());
    }

    @Test
    public void testIsFullTrue() {
        obj = new Stack(6);
        for(int i=0;i<6;i++)
            obj.push(new Integer(i));

        Assert.assertTrue(obj.isFull());
    }

    @Test
    public void testCurrentSize() {
        Assert.assertEquals(6, obj.currentSize());
    }

    @Test
    public void testMultiPop() {
        obj.multiPop(3);
        Assert.assertEquals(obj.peek(), new Integer(2));
    }

    @Test 
    public void testMultiPopBig() {
        obj = new Stack(10);
        for(int i=0;i<6;i++)
            obj.push(new Integer(i));

        obj.multiPop(7);
        Assert.assertTrue(obj.isEmpty());
    }

    @Test 
    public void testMultiPopExact() {
        obj = new Stack(10);
        for(int i=0;i<6;i++)
            obj.push(new Integer(i));

        obj.multiPop(6);
        Assert.assertTrue(obj.isEmpty());
    }

    @Test
    public void testMultiPush() {
        Integer[] arr = new Integer[]{11,22,33};
        obj.multiPush(arr);
        Assert.assertEquals(obj.pop(), new Integer(33));
    }

    @Test (expected = NullPointerException.class)
    public void testMultiPushNullArray() {
        Integer[] arr = null;
        obj.multiPush(arr);
    }

    @Test (expected = NullPointerException.class)
    public void testMultiPushNull() {
        Integer[] arr = new Integer[]{11,22,null};
        obj.multiPush(arr);
    }

    @Test
    public void testReverse() {
        obj.reverse();
        Assert.assertEquals(obj.peek(), new Integer(0));
    }
    
    @Test (expected = EmptyStackException.class)
    public void testReverseEmpty() { 
        obj = new Stack(2);
        obj.reverse();
    }

    @Test
    public void testPushUniqueTrue() {
        Assert.assertTrue(obj.pushUnique(new Integer(6)));
        Assert.assertEquals(obj.peek(), new Integer(6)); 
    }

    @Test
    public void testPushUniqueOne() {
        obj = new Stack(2);
        Assert.assertTrue(obj.pushUnique(new Integer(6)));
        Assert.assertEquals(obj.peek(), new Integer(6)); 
    }

    @Test
    public void testPushUniqueFalse() {
        Assert.assertFalse(obj.pushUnique(5));
        Assert.assertEquals(obj.peek(), new Integer(5));        
    }
   
    @Test
    public void testSearchExist() {
        Assert.assertEquals(obj.search(new Integer(0)), 6);
    }
}
