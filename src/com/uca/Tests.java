package com.uca;

import org.junit.jupiter.api.Test;
import java.util.concurrent.Callable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Tests {
	
	@Test
	public void testConverter(){
		assertThat(RomanConverter.getRomanFromNumber(4), equalTo("IV")); //Success
        assertThat(RomanConverter.getNumberFromRoman("ML"), equalTo(1050)); //Success
        assertThat(RomanConverter.getNumberFromRoman("IX"), equalTo(9)); //Success
	}

    @Test
    public void negativeNumber() {
        assertThat(exceptionOf(() -> RomanConverter.getRomanFromNumber(-2)), instanceOf(IllegalArgumentException.class));
    }

    @Test
    public void outOfRange() {
        assertThat(exceptionOf(() -> RomanConverter.getRomanFromNumber(4000)), instanceOf(IllegalArgumentException.class));
    }

    @Test
    public void charRepetition() {
        assertThat(exceptionOf(() -> RomanConverter.getNumberFromRoman("IIII")), instanceOf(IllegalArgumentException.class));
    }

    @Test
    public void comparison() {
        RomanNumber rn = new RomanNumber("XX");
        assertThat(rn.compareTo(20), equalTo(0));
    }

    //Help you to handle exception. :-)
    public static Throwable exceptionOf(Callable<?> callable) {
        try {
            callable.call();
            return null;
        } catch (Throwable t) {
            return t;
        }
    }
}
