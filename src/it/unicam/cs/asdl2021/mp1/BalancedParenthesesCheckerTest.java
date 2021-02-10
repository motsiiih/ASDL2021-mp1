package it.unicam.cs.asdl2021.mp1;

import it.unicam.cs.asdl2021.mp1.BalancedParenthesesChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalancedParenthesesCheckerTest {

    @Test
    void check() {
        BalancedParenthesesChecker checker = new BalancedParenthesesChecker();
        assertTrue(checker.check("{[()]}"));
        assertTrue(checker.check("{[(   )]}"));
        assertTrue(checker.check("{[( )]}"));
        assertTrue(checker.check("{[{()()}]}"));
        assertTrue(checker.check(""));
        assertFalse(checker.check("({)"));
        assertThrows(IllegalArgumentException.class, () -> {
            checker.check("({p)");
        });
        assertFalse(checker.check("({{])"));
        assertFalse(checker.check(")"));
        assertFalse(checker.check("({)"));
    }
}