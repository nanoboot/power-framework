
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
/**
 * 
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */

public class ChoiceGenerator<T> {
    /**
     * Random for this choice generator.
     */
    private final RandomGenerator random;
    /**
     * Choice entry array, which will be used to generate next ChoiceEntry.
     */
    private final ChoiceEntry<T>[] choiceEntries;
    /**
     * Internal frequency sum int array:
     * contains sum of frequencies from frequency of choiceEntries[0] up to the index.
     */
    private final int[] frequencySum;

    /**
     * Constructor.
     * @param randomArg CSRandomGenerator instance for randomness for this choice generator
     * @param choiceEntriesArg array of ChoiceEntry instances
     */
    public ChoiceGenerator(final RandomGenerator randomArg, final ChoiceEntry<T>... choiceEntriesArg) {
        if (choiceEntriesArg.length == 0) {
            throw new RandomException("choiceEntries.length == 0");
        }
        this.random = randomArg;
        this.choiceEntries = choiceEntriesArg;
        frequencySum = new int[choiceEntriesArg.length];
        for (int i = 0; i < choiceEntriesArg.length; i++) {
            int previousFrequencySum = i == 0 ? 0 : frequencySum[i - 1];
            ChoiceEntry<T> choiceEntry = choiceEntriesArg[i];
            frequencySum[i] = previousFrequencySum + choiceEntry.getFrequency();
        }
    }

    /**
     * Returns random choice entry using the frequency values of the choice entries.
     *
     * @return a ChoiceEntry instance- one of the instances in the array passed to the constructor.
     * Which instance of ChoiceEntry will be selected depends on the random selected number and
     * the frequency values in the ChoiceEntry array.
     */
    public ChoiceEntry<T> generate() {
        if (this.choiceEntries.length == 1) {
            return choiceEntries[0];
        }
        int min = 0;
        int frequencySumLastIndex = frequencySum.length - 1;
        int max = frequencySum[frequencySumLastIndex];
        int randomNumber = random.nextInt(min, max);
        ChoiceEntry<T> result = null;
        for (int i = 0; i < frequencySum.length; i++) {
            int frequencySumItem = frequencySum[i];
            if (randomNumber <= frequencySumItem) {
                result = choiceEntries[i];
                break;
            }
        }
        if (result == null) {
            throw new RandomException("ChoiceEntry not found.");
        }
        return result;

    }
}
