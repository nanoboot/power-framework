
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

package org.nanoboot.powerframework.random.choicegenerators;

import org.nanoboot.powerframework.random.RandomException;
import org.nanoboot.powerframework.random.generators.RandomGenerator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * 
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class ProbabilityGeneratorTest {
    private RandomGenerator randomGenerator;

    @Before
    public void setup() {
        randomGenerator = RandomGenerator.getDefaultImplStatic();
    }

    @Test(expected = RandomException.class)
    public void constructor_probabilityIsLessThanMinimum() {
        new ProbabilityGenerator(randomGenerator, -1);
    }
    @Test(expected = RandomException.class)
    public void constructor_probabilityIsMoreThanMinimum() {
        new ProbabilityGenerator(randomGenerator, 101);
    }
    @Test(expected = Test.None.class)
    public void constructor_probabilityIsOK0() {
        new ProbabilityGenerator(randomGenerator, 0);
    }
    @Test(expected = Test.None.class)
    public void constructor_probabilityIsOK1() {
        new ProbabilityGenerator(randomGenerator, 1);
    }
    @Test(expected = Test.None.class)
    public void constructor_probabilityIsOK99() {
        new ProbabilityGenerator(randomGenerator, 99);
    }
    @Test(expected = Test.None.class)
    public void constructor_probabilityIsOK60() {
        new ProbabilityGenerator(randomGenerator, 60);
    }
    @Test(expected = Test.None.class)
    public void constructor_probabilityIsOK100() {
        new ProbabilityGenerator(randomGenerator, 100);
    }

    @Test
    public void generate_alwaysFalse() {
        assertEquals(false, new ProbabilityGenerator(randomGenerator, 0).generate());
    }    @Test
    public void generate_alwaysTrue() {
        assertEquals(true, new ProbabilityGenerator(randomGenerator, 100).generate());
    }
    @Test
    public void generate_NotAlwaysFalse() {
        boolean trueGenerated = false;
        boolean falseGenerated = false;
        for(int i = 0; i<100; i++) {
            boolean result = new ProbabilityGenerator(randomGenerator, 40).generate();
            if(result) {
                trueGenerated = true;
            } else {
                falseGenerated = true;
            }
            if(trueGenerated && falseGenerated) {
                //no need for next loop
                break;
            }
        }
        assertTrue(trueGenerated && falseGenerated);
    }


    @Test
    public void generate_mockedCheck() {
//        //prepare
        class ARandomGeneratorImpl implements RandomGenerator {
            private int[] intArray = new int[]{0, 24, 25, 26, 60, 74, 75, 76, 99, 100};
            private int nextIndex = 0;
            private boolean fromToChecked = false;

            @Override
            public long next() {
                return 0;
            }

            public int nextInt(int from, int to) {
                if (!fromToChecked) {
                    assertEquals(0, from);
                    assertEquals(100, to);
                    fromToChecked = true;
                }
                if (nextIndex >= intArray.length) {
                    fail("next index is " + nextIndex + ", but the intArray has length " + intArray.length);
                }
                int result = intArray[nextIndex];
                ++nextIndex;
                return result;
            }

            @Override
            public RandomGenerator getItself() {
                return this;
            }

            @Override
            public String getName() {
                return "choiceRandomGenerator";
            }
        }
        RandomGenerator randomArg = new ARandomGeneratorImpl();

        ProbabilityGenerator choiceGenerator = new ProbabilityGenerator(randomArg, 40);
//        //execute
//        //assert

        assertTrue(choiceGenerator.generate());
        assertTrue(choiceGenerator.generate());
        assertTrue(choiceGenerator.generate());
        assertTrue(choiceGenerator.generate());
        assertFalse(choiceGenerator.generate());
        assertFalse(choiceGenerator.generate());
        assertFalse(choiceGenerator.generate());
        assertFalse(choiceGenerator.generate());
        assertFalse(choiceGenerator.generate());
        assertFalse(choiceGenerator.generate());
    }
}
