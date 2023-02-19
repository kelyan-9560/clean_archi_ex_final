package domain.services;

import domain.exception.ParserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserValidatorTest {

    ParserValidator parserValidator = new ParserValidator();
    @Test
    void commandHeaderIsValid() throws ParserException {
        assertTrue(parserValidator.commandHeaderIsValid("agenda"));
        assertThrows(ParserException.class, () -> parserValidator.commandHeaderIsValid("commande pas bonne"));
    }

    @Test
    void instructionIsValid() {
        assertThrows(ParserException.class, () -> parserValidator.instructionIsValid("instruction pas bonne"));
    }

    @Test
    void statusIsValid() {
        assertThrows(ParserException.class, () -> parserValidator.statusIsValid("instruction pas bonne"));
    }

    @Test
    void argumentIsValid() {
        // doit etre '-'
        assertThrows(ParserException.class, () -> parserValidator.argumentIsValid('a'));
    }
}