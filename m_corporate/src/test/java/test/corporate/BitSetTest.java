package test.corporate;

import org.junit.Test;

import java.util.BitSet;

import static org.junit.Assert.*;

public class BitSetTest {

    @Test
    public void byteArray2BitSet() {
        byte[] bytes = new byte[1];
        bytes[0] = 4;
        BitSet bitSet = BitSet.valueOf(bytes);
        assertFalse(bitSet.get(0));
        assertFalse(bitSet.get(1));
        assertTrue(bitSet.get(2));
        assertFalse(bitSet.get(3));
        assertFalse(bitSet.get(9));
        assertFalse(bitSet.get(100));
    }

    @Test
    public void bitSet2ByteArray() {
        BitSet bitSet = new BitSet();
        bitSet.set(0);
        assertFalse(bitSet.isEmpty());
        assertEquals(1, bitSet.toByteArray().length);
        bitSet.set(7);
        assertEquals(1, bitSet.toByteArray().length);
        bitSet.set(8);
        assertEquals(2, bitSet.toByteArray().length);
        bitSet.set(8, false);
        assertEquals(1, bitSet.toByteArray().length);
        bitSet.set(7, false);
        bitSet.set(0, false);
        assertNotNull(bitSet.toByteArray());
        assertEquals(0, bitSet.toByteArray().length);
    }
}
