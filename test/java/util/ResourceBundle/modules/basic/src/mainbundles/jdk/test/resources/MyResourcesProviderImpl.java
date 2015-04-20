/*
 * Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package jdk.test.resources;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.spi.ResourceBundleControlProvider;
import java.util.spi.ResourceBundleProvider;

public class MyResourcesProviderImpl extends MyControl implements ResourceBundleControlProvider,
                                                                  MyResourcesProvider {
    // ResourceBundleControlProvider
    @Override
    public ResourceBundle.Control getControl(String baseName) {
        return "jdk.test.resources.MyResources".equals(baseName) ? this : null;
    }

    // MyResourcesProvider
    @Override
    public ResourceBundle getBundle(String baseName, Locale locale) {
        if (isMainLocale(locale)) {
            try {
                ClassLoader loader = MyResourcesProviderImpl.class.getClassLoader();
                return newBundle(baseName, locale, "java.class", loader, false);
            } catch (IllegalAccessException | InstantiationException | IOException e) {
                System.out.println(e);
            }
        }
        return null;
    }

}
