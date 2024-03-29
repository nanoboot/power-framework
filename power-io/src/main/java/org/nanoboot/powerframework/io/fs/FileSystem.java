
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

package org.nanoboot.powerframework.io.fs;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */

public class FileSystem {

    private static final FileSystem defaultFileSystem = null;

    /**
     *
     * @param defaultFileSystemArg default file system
     */
    public static void setDefaultFileSystem(FileSystem defaultFileSystemArg) {
    }

    /**
     *
     * @param aclass Class instance
     *
     * @return path to the class file
     */
    public static String getJarContainingFolder(Class aclass) {
        CodeSource codeSource = aclass.getProtectionDomain().getCodeSource();
        java.io.File jarFile = null;
        if(codeSource.getLocation() != null) {
            try {
                jarFile = new java.io.File(codeSource.getLocation().toURI());
            } catch (URISyntaxException ex) {
                Logger.getLogger(FileSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String path = aclass.getResource(aclass.getSimpleName() + ".class").getPath();
            String jarFilePath = path.substring(path.indexOf(':') + 1, path.indexOf('!'));
            try {
                jarFilePath = URLDecoder.decode(jarFilePath, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(FileSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
            jarFile = new java.io.File(jarFilePath);
        }
        return jarFile.getParentFile().getAbsolutePath();
    }
    private final String location;

    /**
     *
     * @param locationIn path to the root folder of this abstract file system
     */
    public FileSystem(String locationIn) {
        this.location = locationIn;
    }

}
