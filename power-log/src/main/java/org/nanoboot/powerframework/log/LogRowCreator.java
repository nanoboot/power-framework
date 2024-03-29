
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

package org.nanoboot.powerframework.log;

import org.nanoboot.powerframework.time.utils.LocalSettings;
import org.nanoboot.powerframework.time.moment.TimeZone;
import org.nanoboot.powerframework.time.moment.ZonedDateTime;
/**
 * 
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */

class LogRowCreator {

    private static final String DELIMITER = " ";
    private static final String START = "[";
    private static final String END = "]";
    private static final String STATIC = "----------STATIC";
    private static final String DASH = "-";
    private static final String NOTOKAYOBJECT = "NOT_OKAY_OBJECT";

    static String createRow(long rowNumber,
            Object object,
            Level level,
            String className,
            String message) {

        if(object instanceof String) {
            message = message + DELIMITER + "Warning: The object to log is String.";
        }

        int hashCode = object == null ? 0 : object.hashCode();
        String hashCodeAsHexString = Integer.toHexString(hashCode);

        String hash = hashCodeAsHexString;

        TimeZone timeZone = LocalSettings.getLocalTimeZone();
        String time = ZonedDateTime.getCurrentZonedDateTimeForTimeZone(timeZone).toString();
        return org.nanoboot.powerframework.utils.StringUtils.appendObjects(
                String.format("%08d", rowNumber), DELIMITER,
                START, Thread.currentThread().getName(), END, DELIMITER,
                level, DELIMITER,
                START, time, " ", timeZone.toString(), END, DELIMITER,
                hash, DELIMITER,
                className, DELIMITER,
                message
        );

    }

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private LogRowCreator() {
    }
}
