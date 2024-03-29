
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

package org.nanoboot.powerframework.json;

import java.util.*;

/**
 * 
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */

class CommasFinder {

    private static boolean nestingWillBeIncreased(char charToCheck) {
        return (charToCheck == JsonConstants.OBJECTSTART) || (charToCheck == JsonConstants.ARRAYSTART);
    }

    private static boolean nestingWillBeDecreased(char charToCheck) {
        return (charToCheck == JsonConstants.OBJECTEND) || (charToCheck == JsonConstants.ARRAYEND);
    }

    static ArrayList<Integer> getListOfIndexesOfCommas(final String string) {
        CommasFinder commasFinder = new CommasFinder(string);
        return commasFinder.listOfCommas;
    }

    private final ArrayList<Integer> listOfCommas = new ArrayList<>();
    private int nesting = 0;
    private char currentChar;
    private int charIndex;
    private final String string;

    private CommasFinder(final String string) {
        this.string = string;
        //System.out.println(string);
        for (charIndex = 0; charIndex < string.length(); charIndex++) {
            updateCurrentChar();
            //System.out.println(string.charAt(charIndex));
            skipStringIfNecessarily();

            if ((currentChar == JsonConstants.COMMA) && (nesting == 0)) {
                //System.out.println("přidání");
                listOfCommas.add(charIndex);
            }
            if (nestingWillBeIncreased(currentChar)) {
                nesting++;
            }
            if (nestingWillBeDecreased(currentChar)) {
                nesting--;
            }

        }
    }

    private void skipStringIfNecessarily() {

        if ((currentChar == JsonConstants.APOSTROPHE) && (nesting == 0)) {
            //System.out.println("skok");
            charIndex++;//NOSONAR
            updateCurrentChar();
            while (JsonConstants.APOSTROPHE != currentChar) {
                charIndex++;//NOSONAR
                updateCurrentChar();
            }
        }
    }

    private void updateCurrentChar() {
        currentChar = string.charAt(charIndex);
    }

}
