/*
 * Copyright (c) 2021, 2021, Oracle and/or its affiliates. All rights reserved.
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

package com.oracle.truffle.espresso.analysis.hierarchy;

import com.oracle.truffle.espresso.impl.ObjectKlass;

/**
 * Computes the classes that are effectively final by keeping track of currently loaded classes. To
 * compute currently leaf classes, it creates {@code leafTypeAssumption} in the {@link ObjectKlass}
 * constructor and invalidates it when a descendant of this class is initialized.
 */
public class DefaultClassHierarchyOracle extends NoOpClassHierarchyOracle implements ClassHierarchyOracle {
    @Override
    public LeafTypeAssumption createAssumptionForClass(ObjectKlass newKlass) {
        if (newKlass.isFinalFlagSet()) {
            return FinalIsAlwaysLeaf;
        }
        if (newKlass.isAbstract() || newKlass.isInterface()) {
            return NotLeaf;
        }
        return new LeafTypeAssumptionImpl(newKlass);
    }

    /**
     * Marks all ancestors of {@code newClass} as non-leaf.
     *
     * @param newClass -- newly initialized class.
     */
    @Override
    public void onClassInit(ObjectKlass newClass) {
        ObjectKlass currentParent = newClass.getSuperKlass();
        while (currentParent.getLeafTypeAssumption().getAssumption().isValid()) {
            currentParent.getLeafTypeAssumption().getAssumption().invalidate();
            currentParent = currentParent.getSuperKlass();
        }
    }
}
