
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

package org.nanoboot.powerframework.core.version;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * 
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class VersionTypeTest {

    @Test
    public void of() {
        assertEquals(VersionType.SNAPSHOT, VersionType.of("SNAPSHOT"));
        assertEquals(VersionType.ALPHA, VersionType.of("a"));
        assertEquals(VersionType.BETA, VersionType.of("b"));
        assertEquals(VersionType.RELEASE_CANDIDATE, VersionType.of("rc"));
        assertEquals(VersionType.STANDARD, VersionType.of("standard"));
    }
    @Test(expected = UnsupportedOperationException.class)
    public void of2() {
        VersionType.of("nonsense text");
    }

    @Test
    public void isSnapshot() {
        assertTrue(VersionType.SNAPSHOT.isSnapshot());
        assertFalse(VersionType.ALPHA.isSnapshot());
        assertFalse(VersionType.BETA.isSnapshot());
        assertFalse(VersionType.RELEASE_CANDIDATE.isSnapshot());
        assertFalse(VersionType.STANDARD.isSnapshot());
    }

    @Test
    public void isAlpha() {
        assertFalse(VersionType.SNAPSHOT.isAlpha());
        assertTrue(VersionType.ALPHA.isAlpha());
        assertFalse(VersionType.BETA.isAlpha());
        assertFalse(VersionType.RELEASE_CANDIDATE.isAlpha());
        assertFalse(VersionType.STANDARD.isAlpha());
    }

    @Test
    public void isBeta() {
        assertFalse(VersionType.SNAPSHOT.isBeta());
        assertFalse(VersionType.ALPHA.isBeta());
        assertTrue(VersionType.BETA.isBeta());
        assertFalse(VersionType.RELEASE_CANDIDATE.isBeta());
        assertFalse(VersionType.STANDARD.isBeta());
    }

    @Test
    public void isReleaseCandidate() {
        assertFalse(VersionType.SNAPSHOT.isReleaseCandidate());
        assertFalse(VersionType.ALPHA.isReleaseCandidate());
        assertFalse(VersionType.BETA.isReleaseCandidate());
        assertTrue(VersionType.RELEASE_CANDIDATE.isReleaseCandidate());
        assertFalse(VersionType.STANDARD.isReleaseCandidate());
    }

    @Test
    public void isStandard() {
        assertFalse(VersionType.SNAPSHOT.isStandard());
        assertFalse(VersionType.ALPHA.isStandard());
        assertFalse(VersionType.BETA.isStandard());
        assertFalse(VersionType.RELEASE_CANDIDATE.isStandard());
        assertTrue(VersionType.STANDARD.isStandard());
    }
}
