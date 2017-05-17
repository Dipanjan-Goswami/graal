/*
 * Copyright (c) 2016, Oracle and/or its affiliates.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided
 * with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.oracle.truffle.llvm.nodes.vars;

import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameUtil;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.llvm.runtime.LLVMIVarBit;
import com.oracle.truffle.llvm.runtime.floating.LLVM80BitFloat;
import com.oracle.truffle.llvm.runtime.nodes.api.LLVMExpressionNode;

public abstract class LLVMReadNode extends LLVMExpressionNode {

    @NodeField(name = "slot", type = FrameSlot.class)
    public abstract static class LLVMI1ReadNode extends LLVMExpressionNode {

        protected abstract FrameSlot getSlot();

        @Specialization
        protected boolean readI1(VirtualFrame frame) {
            return FrameUtil.getBooleanSafe(frame, getSlot());
        }
    }

    @NodeField(name = "slot", type = FrameSlot.class)
    public abstract static class LLVMI8ReadNode extends LLVMExpressionNode {

        protected abstract FrameSlot getSlot();

        @Specialization
        protected byte readI8(VirtualFrame frame) {
            return FrameUtil.getByteSafe(frame, getSlot());
        }
    }

    @NodeField(name = "slot", type = FrameSlot.class)
    public abstract static class LLVMI16ReadNode extends LLVMExpressionNode {

        protected abstract FrameSlot getSlot();

        @Specialization
        protected short readI16(VirtualFrame frame) {
            return (short) FrameUtil.getIntSafe(frame, getSlot());
        }
    }

    @NodeField(name = "slot", type = FrameSlot.class)
    public abstract static class LLVMI32ReadNode extends LLVMExpressionNode {

        protected abstract FrameSlot getSlot();

        @Specialization
        protected int readI32(VirtualFrame frame) {
            return FrameUtil.getIntSafe(frame, getSlot());
        }
    }

    @NodeField(name = "slot", type = FrameSlot.class)
    public abstract static class LLVMI64ReadNode extends LLVMExpressionNode {

        protected abstract FrameSlot getSlot();

        @Specialization
        protected long readI64(VirtualFrame frame) {
            return FrameUtil.getLongSafe(frame, getSlot());
        }
    }

    @NodeField(name = "slot", type = FrameSlot.class)
    public abstract static class LLVMIReadVarBitNode extends LLVMExpressionNode {

        protected abstract FrameSlot getSlot();

        @Specialization
        protected LLVMIVarBit readVarBit(VirtualFrame frame) {
            return (LLVMIVarBit) FrameUtil.getObjectSafe(frame, getSlot());
        }
    }

    @NodeField(name = "slot", type = FrameSlot.class)
    public abstract static class LLVMFloatReadNode extends LLVMExpressionNode {

        protected abstract FrameSlot getSlot();

        @Specialization
        protected float readDouble(VirtualFrame frame) {
            return FrameUtil.getFloatSafe(frame, getSlot());
        }
    }

    @NodeField(name = "slot", type = FrameSlot.class)
    public abstract static class LLVMDoubleReadNode extends LLVMExpressionNode {

        protected abstract FrameSlot getSlot();

        @Specialization
        protected double readDouble(VirtualFrame frame) {
            return FrameUtil.getDoubleSafe(frame, getSlot());
        }
    }

    @NodeField(name = "slot", type = FrameSlot.class)
    public abstract static class LLVM80BitFloatReadNode extends LLVMExpressionNode {

        protected abstract FrameSlot getSlot();

        @Specialization
        protected LLVM80BitFloat read80BitFloat(VirtualFrame frame) {
            return (LLVM80BitFloat) FrameUtil.getObjectSafe(frame, getSlot());
        }
    }

    @NodeField(name = "slot", type = FrameSlot.class)
    public abstract static class LLVMAddressReadNode extends LLVMExpressionNode {

        protected abstract FrameSlot getSlot();

        @Specialization
        protected Object readObject(VirtualFrame frame) {
            return FrameUtil.getObjectSafe(frame, getSlot());
        }
    }

    @NodeField(name = "slot", type = FrameSlot.class)
    public abstract static class LLVMFunctionReadNode extends LLVMExpressionNode {

        protected abstract FrameSlot getSlot();

        @Specialization
        protected Object readI32(VirtualFrame frame) {
            return FrameUtil.getObjectSafe(frame, getSlot());
        }
    }

}
