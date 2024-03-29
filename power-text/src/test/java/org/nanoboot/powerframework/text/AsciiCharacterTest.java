
///////////////////////////////////////////////////////////////////////////////////////////////
// power-framework: Java library with many purposes of usage.
// Copyright (C) 2016-2022 the original author or authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation;
// version 2.1 of the License only.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
///////////////////////////////////////////////////////////////////////////////////////////////

package org.nanoboot.powerframework.text;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * 
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */

public class AsciiCharacterTest {

    @Test
    public void toChar() {
        for (AsciiCharacter e : AsciiCharacter.values()) {
            char ch = e.toChar();
            AsciiCharacter ac = AsciiCharacter.ofCharacter(ch);
            assertEquals(ch, ac.toChar());
        }
    }

    @Test
    public void ofCharacter() {
        assertEquals(AsciiCharacter.CARET, AsciiCharacter.ofCharacter('^'));
        assertEquals(null, AsciiCharacter.ofCharacter('‣'));
    }

    @Test
    public void getAsciiPosition() {
        int position = 100;
        char ch = (char) position;
        assertEquals(position, AsciiCharacter.ofCharacter(ch).getAsciiPosition());
    }

    @Test
    public void getCharacter() {
        char ch = '3';
        AsciiCharacter ac = AsciiCharacter.ofCharacter(ch);
        assertEquals(ch, ac.toChar());
    }

    @Test
    public void getDescription() {
        for (AsciiCharacter e : AsciiCharacter.values()) {
            assertNotNull(e.getDescription());
            assertFalse(e.getDescription().isEmpty());
        }
    }

    @Test
    public void getCharacterType() {
        assertEquals(CharacterType.NUMBER, AsciiCharacter.ofCharacter('5').getCharacterType());
        assertEquals(CharacterType.LOWER_LETTER, AsciiCharacter.ofCharacter('g').getCharacterType());
        assertEquals(CharacterType.UPPER_LETTER, AsciiCharacter.ofCharacter('M').getCharacterType());
        assertEquals(CharacterType.OTHER_NOT_PRINTABLE, AsciiCharacter.ofCharacter(' ').getCharacterType());
        assertEquals(CharacterType.OTHER_PRINTABLE, AsciiCharacter.ofCharacter('*').getCharacterType());
    }

    @Test
    public void getOppositeCharacter() {
        for (AsciiCharacter ac : AsciiCharacter.values()) {
            if (ac.getOppositeCharacter() != null) {
                Character character = ac.getCharacter();
                Character oppositeCharacter = ac.getOppositeCharacter();
                Character expected = character;
                Character returned = AsciiCharacter.ofCharacter(oppositeCharacter).getOppositeCharacter();
                assertEquals(expected, returned);
            }
        }
        assertEquals(AsciiCharacter.ROUND_BRACKET_START.getCharacter(),
                AsciiCharacter.ROUND_BRACKET_END.getOppositeCharacter());
    }

    @Test
    public void asString() {
        String expected = "3";
        AsciiCharacter ac = AsciiCharacter.ofCharacter('3');
        assertEquals(expected, ac.asString());
    }

}
