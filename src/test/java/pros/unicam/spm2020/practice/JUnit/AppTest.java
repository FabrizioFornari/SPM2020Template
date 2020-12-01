package pros.unicam.spm2020.practice.JUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    @Tag("UnitTest")
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    @Test
    @Disabled
    public void shouldAnswerWithTrue2()
    {
        assertTrue( false );
    }
}
