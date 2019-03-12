import org.junit.Test;

import java.math.BigInteger;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class HasherTest {
@Test
    public void testPaddingTrueIfLengthEquals(){
   /* Hasher hasher = new Hasher("10110");
    String result = hasher.addPadding().getMessage();
    assertEquals("Result", 262144,result.length() );*/
}
@Test
    public void circularLeftShift(){
    String result = MISTY.cyclicLeftShift(new BigInteger("10101010001",2),11,3).toString(2);
    assertEquals("1010001101",result);
}
}
