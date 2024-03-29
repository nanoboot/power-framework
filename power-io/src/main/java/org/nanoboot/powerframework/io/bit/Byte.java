
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

package org.nanoboot.powerframework.io.bit;

/**
 * 
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */

public class Byte {

    public static byte booleanArrayToByte(boolean... booleanArray) {
        final int TWO = 2;
        byte byteValue = -128;
        int exponent = 0;
        for (int i = booleanArray.length - 1; i >= 0; i--) {
            int booleanAsInt = booleanArray[i] ? 1 : 0;
            byteValue = (byte) (byteValue + (booleanAsInt * Math.pow(TWO, exponent)));
            exponent++;
        }
        return byteValue;
    }

    /**
     * Convert a byte array to a boolean array. Bit 0 is represented with false,
     * Bit 1 is represented with 1
     *
     * @param bytes byte[]
     *
     * @return boolean[]
     */
    private static boolean[] byteArrayToBooleanArray(byte[] bytes) {
        boolean[] bits = new boolean[bytes.length * 8];
        for (int i = 0; i < bytes.length * 8; i++) {
            if(((bytes[i / 8] + 128) & (1 << (7 - (i % 8)))) > 0) {
                bits[i] = true;
            }
        }
        return bits;
    }

    /**
     * Convert a byte array to a boolean array. Bit 0 is represented with false,
     * Bit 1 is represented with 1
     *
     * @param byteValueIn
     *
     * @return boolean[]
     */
    public static boolean[] byteValueToBooleanArray(byte byteValueIn) {
        return byteArrayToBooleanArray(new byte[]{byteValueIn});
    }

}
