package com.steel.nettyaction.nio;


import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.IntBuffer;

/**
 * @author steelqin
 * @date 18/4/19
 */
public class BufferTest {

    IntBuffer b1;
    IntBuffer b2;

    @Before
    public void prepare() {
        b1 = IntBuffer.allocate(11);
        b1.put(1);
        b2 = IntBuffer.allocate(10);
        b2.put(1);
    }

    @Test
    public void equals() {
        b1.flip();
        b2.flip();
        Assert.assertTrue(b1.equals(b2));
    }

    @Test
    public void compareTo() {
        b1.flip();
        b2.flip();
        Assert.assertEquals(0, b1.compareTo(b2));
    }

    @Test
    public void test() {
        String log = JSON.toJSONString(null);
        Assert.assertEquals("null", log);
    }
}
