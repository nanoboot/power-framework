
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

package org.nanoboot.powerframework.utils;

/**
 *
 * @author robertvokac
 */

import java.util.HashMap;
import java.util.Map;
/**
 * 
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class StringTemplate {
    public static final String VAR_START = "{{{{";
    public static final String VAR_END = "}}}}";
    private final String template;

    public StringTemplate(String template) {
        this.template = template;
    }
    public String apply(Map<String, String> map) {
        String result = template;
        for(String key:map.keySet()) {
            String var = VAR_START + key + VAR_END;
            String value = map.get(key);
            result = result.replace(var, value);
        }
        if(result.contains(VAR_START)) {
            throw new UtilsException("Template \"" + result + "\"still contains \"" + VAR_START + "\".");
        }

        if(result.contains(VAR_END)) {
            throw new UtilsException("Template \"" + result + "\"still contains \"" + VAR_END + "\".");
        }
        return result;
    }
    public Map<String, String> createEmptyMap() {
        return new HashMap<>();
    }
}

 
