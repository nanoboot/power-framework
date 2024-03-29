
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

package org.nanoboot.powerframework.collections.arrays;

import java.util.Iterator;

/**
 * There will be the choice to select the starting index:0 will be default
 * (maybe), but 1 or something else will be possible.
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class IntArray implements Iterable<Integer> {

    private Array array;

    public IntArray(int... lengthIn) {
        array = new Array<Object>(ArrayType.INT, lengthIn);
    }

    public static IntArray ofValues(int... values) {
        IntArray intArray = new IntArray(values.length);
        intArray.array = new Array(values);
        return intArray;
    }

    public int get(int... index) {
        return array.getInt(index);
    }

    public void set(int value,
                    int... index) {
        array.setInt(value, index);
    }

    /**
     * @param dimension 1 or 2 or 3 or 4
     * @return
     */
    public int getLength(int dimension) {
        return this.array.getLength(dimension);
    }

    @java.lang.Override
    public Iterator<Integer> iterator() {
        if (array.getDimensions() != 1) {
            throw new ArrayException("For iterating is only supported for one-dimensional arrays.");
        }
        return new IntArrayIterator(array);
    }

    class IntArrayIterator implements Iterator<Integer> {

        private Array array;
        private int index = 0;

        public IntArrayIterator(Array arrayIn) {
            this.array = arrayIn;
        }

        @Override
        public boolean hasNext() {
            return (index + 1) <= array.getLength();
        }

        @Override
        public Integer next() {
            throwExceptionIfNoNextElement();
            return array.getInt(++index);
        }

        private void throwExceptionIfNoNextElement() throws ArrayException {
            if (!hasNext()) {
                throw new ArrayException("There is no next element.");
            }
        }
    }

}
