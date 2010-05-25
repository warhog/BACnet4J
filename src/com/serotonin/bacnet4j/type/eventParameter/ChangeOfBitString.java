/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * Copyright (C) 2006-2009 Serotonin Software Technologies Inc. http://serotoninsoftware.com
 * @author Matthew Lohbihler
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 */
package com.serotonin.bacnet4j.type.eventParameter;

import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.type.constructed.SequenceOf;
import com.serotonin.bacnet4j.type.primitive.BitString;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.serotonin.util.queue.ByteQueue;

public class ChangeOfBitString extends EventParameter {
    public static final byte TYPE_ID = 0;

    private final UnsignedInteger timeDelay;
    private final BitString bitMask;
    private final SequenceOf<BitString> listOfBitstringValues;

    public ChangeOfBitString(UnsignedInteger timeDelay, BitString bitMask, SequenceOf<BitString> listOfBitstringValues) {
        this.timeDelay = timeDelay;
        this.bitMask = bitMask;
        this.listOfBitstringValues = listOfBitstringValues;
    }

    public ChangeOfBitString(ByteQueue queue) throws BACnetException {
        timeDelay = read(queue, UnsignedInteger.class, 0);
        bitMask = read(queue, BitString.class, 1);
        listOfBitstringValues = readSequenceOf(queue, BitString.class, 2);
    }

    @Override
    protected void writeImpl(ByteQueue queue) {
        write(queue, timeDelay, 0);
        write(queue, bitMask, 1);
        write(queue, listOfBitstringValues, 2);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((bitMask == null) ? 0 : bitMask.hashCode());
        result = PRIME * result + ((listOfBitstringValues == null) ? 0 : listOfBitstringValues.hashCode());
        result = PRIME * result + ((timeDelay == null) ? 0 : timeDelay.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ChangeOfBitString other = (ChangeOfBitString) obj;
        if (bitMask == null) {
            if (other.bitMask != null)
                return false;
        }
        else if (!bitMask.equals(other.bitMask))
            return false;
        if (listOfBitstringValues == null) {
            if (other.listOfBitstringValues != null)
                return false;
        }
        else if (!listOfBitstringValues.equals(other.listOfBitstringValues))
            return false;
        if (timeDelay == null) {
            if (other.timeDelay != null)
                return false;
        }
        else if (!timeDelay.equals(other.timeDelay))
            return false;
        return true;
    }
}
