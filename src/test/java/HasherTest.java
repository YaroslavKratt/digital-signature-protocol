import org.junit.Test;

import java.math.BigInteger;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class HasherTest {
@Test
    public void testPaddingTrueIfLengthEquals(){
    Hasher hasher = new Hasher(new BigInteger("10110",2));
    String result = hasher.addPadding().getMessage().toString(2);
    assertEquals("Result", 262144,result.length() );
}
@Test
    public void circularLeftShift(){
    String result = MISTY.cyclicLeftShift(new BigInteger("10101010001",2),11,3).toString(2);
    assertEquals("1010001101",result);
}

@Test
    public void whenSplitedCorrectly() {
    Hasher start = new Hasher(new BigInteger("10110",2));
   Hasher temp  = start.addPadding();
   String str="";
    BigInteger result =new BigInteger(String.join("",temp.splitOnStringsWithLength(64)),2);
    assertEquals(temp.getMessage(),result);


}
}
