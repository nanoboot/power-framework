
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

package org.nanoboot.powerframework.time.moment;

import org.nanoboot.powerframework.core.PowerException;
import org.nanoboot.powerframework.time.utils.TimeException;
import org.nanoboot.powerframework.time.utils.TimeUnitsValidator;
import lombok.Getter;

/**
 * Represents Time without date and time zone information.
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public final class LocalTime  {

    private static final String COLON = ":";
    public static final int LOCAL_TIME_AS_STRING_LENGTH = 12;

    @Getter
    private final int hour;
    @Getter
    private final int minute;
    @Getter
    private final int second;
    @Getter
    private final int millisecond;

    /**
     * Constructor.
     * Millisecond is automatically set to 0.
     *
     * @param hour hour value
     * @param minute minute value
     * @param second second value
     *
     * @throws PowerException if parameters are invalid.
     */
    public LocalTime(int hour,
            int minute,
            int second) {
        this(hour, minute, second, 0);
    }
    /**
     * Constructor
     *
     * @param hour hour value
     * @param minute minute value
     * @param second second value
     * @param millisecond millisecond value
     *
     * @throws PowerException if parameters are invalid.
     */
    public LocalTime(int hour,
            int minute,
            int second,
            int millisecond) {
        TimeUnitsValidator.validate(hour, minute, second, millisecond);
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.millisecond = millisecond;
    }

    /**
     *
     * @param s with format HH:mm:ss:SSS
     */
    public LocalTime(String s) {
        if (s == null) {
            throw new TimeException("Local time as string is null.");
        }
        if (s.length() != LOCAL_TIME_AS_STRING_LENGTH) {
            throw new TimeException("Local time length is " + s.length() + ", but length 12 is expected.");
        }
        int hour = Integer.parseInt(s.substring(0, 2));
        int minute = Integer.parseInt(s.substring(3, 5));
        int second = Integer.parseInt(s.substring(6, 8));
        int millisecond = Integer.parseInt(s.substring(9, 12));
        TimeUnitsValidator.validate(hour, minute, second, millisecond);
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.millisecond = millisecond;
    }

    /*
     * Converts this time to format HH:MM:SS::MIS
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if(hour < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(this.getHour());
        stringBuilder.append(COLON);
        if(minute < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(this.getMinute());
        stringBuilder.append(COLON);
        if(second < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(this.getSecond());
        stringBuilder.append(COLON);
        if(millisecond < 10) {
            stringBuilder.append("00");
        } else if(millisecond < 100) {
            stringBuilder.append("0");
        }
        stringBuilder.append(this.getMillisecond());
        return stringBuilder.toString();
    }


}
