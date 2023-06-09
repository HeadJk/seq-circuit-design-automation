package state_table_solver.booleanLogic;

import org.junit.Test;

import static org.junit.Assert.*;

public class BitTest {
    @Test
    public void equalityTest() {
        Bit b1 = new BitVar("A", Bit.HIGH);
        Bit b2 = new BitVar("B", BitValue.LOW);
        Bit b3 = new BitVar("C", BitValue.UNKNOWN);
        Bit b4 = new BitVar("A", BitValue.LOW);
        Bit b5 = new BitConst(BitValue.UNKNOWN);
        Bit b6 = new BitConst(Bit.HIGH);

        assertFalse(b1.equals(b2));
        assertFalse(b1.equals(b3));
        assertFalse(b1.equals(b4));
        assertFalse(b1.equals(b5));
        assertFalse(b1.equals(b6));
        
        assertFalse(b4.equals(b1));
        assertFalse(b4.equals(b2));
        assertFalse(b4.equals(b3));
        assertFalse(b4.equals(b5));
        assertFalse(b4.equals(b6));

        Bit b7 = new BitVar("A", Bit.HIGH);

        assertTrue(b1.equals(b7));
        assertTrue(b1.equalsValue(b6));
        assertTrue(b1.equalsId(b4));

        assertFalse(b5.equals(b6));
        b5.setValue(Bit.HIGH);
        assertTrue(b5.equals(b6));

        assertFalse(b1.equalsValue(b2));
        b2.negate();
        assertTrue(b1.equalsValue(b2));

        assertFalse(b1.equals(b4));
        b4.negate();
        assertTrue(b1.equals(b4));
    }

    @Test
    public void toStringTest() {
        Bit b1 = new BitVar("A", BitValue.UNKNOWN);
        Bit b2 = new BitConst(BitValue.UNKNOWN);

        assertEquals(b1.toString(), "A");
        b1.setValue(Bit.HIGH);
        assertEquals(b1.toString(), "A");
        b1.negate();
        assertEquals(b1.toString(), "A'");

        assertEquals(b2.toString(), "-");
        b2.setValue(Bit.HIGH);
        assertEquals(b2.toString(), "1");
        b2.negate();
        assertEquals(b2.toString(), "0");
    }
}
