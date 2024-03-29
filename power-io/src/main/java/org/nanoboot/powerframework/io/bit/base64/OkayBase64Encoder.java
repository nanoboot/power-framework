
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

package org.nanoboot.powerframework.io.bit.base64;

import org.nanoboot.powerframework.collections.PowerQueue;
import org.nanoboot.powerframework.io.bit.Byte;

/**
 * 
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
class OkayBase64Encoder {

    /**
     *
     * @param byteArrayIn
     *
     * @return
     */
    public static String encode(byte[] byteArrayIn) {
        StringBuilder stringBuilder = new StringBuilder();
        Base64Padding base64Padding = Base64Padding.getBase64Padding(byteArrayIn);

        iterateBytes(byteArrayIn, stringBuilder);
        stringBuilder.append(base64Padding.getPadding());
        return stringBuilder.toString();
    }

    private static void iterateBytes(byte[] byteArrayIn,
            StringBuilder stringBuilder) {
        PowerQueue<Boolean> bitQueue = new PowerQueue<>();

        for (int i = 0; i < byteArrayIn.length; i++) {
            processByteToQueue(byteArrayIn[i], bitQueue);
            feedQueue(bitQueue, stringBuilder);

        }
        if(bitQueue.size() > 0) {
            while (bitQueue.size() < 6) {
                bitQueue.add(false);
            }
            feedQueue(bitQueue, stringBuilder);
        }
    }

    private static void processByteToQueue(byte byteValue,
            PowerQueue<Boolean> bitQueue) {
        boolean[] booleanArray = Byte.byteValueToBooleanArray(byteValue);
        for (boolean element : booleanArray) {
            //System.out.print(element ? "1" : "0");
            bitQueue.add(element);
        }
    }

    private static void feedQueue(PowerQueue<Boolean> bitQueue,
                                  StringBuilder stringBuilder) {
        while (bitQueue.size() >= 6) {

            byte byteValue = Byte.booleanArrayToByte(bitQueue.poll(), bitQueue.poll(), bitQueue.poll(), bitQueue.poll(), bitQueue.poll(), bitQueue.poll());
            stringBuilder.append(Base64Convertor.byteToChar(byteValue));

        }
    }

    private OkayBase64Encoder() {
    }
}
