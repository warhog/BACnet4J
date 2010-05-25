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
import com.serotonin.bacnet4j.type.constructed.DeviceObjectPropertyReference;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.serotonin.util.queue.ByteQueue;

public class CommandFailure extends EventParameter {
    public static final byte TYPE_ID = 3;

    private final UnsignedInteger timeDelay;
    private final DeviceObjectPropertyReference feedbackPropertyReference;

    public CommandFailure(UnsignedInteger timeDelay, DeviceObjectPropertyReference feedbackPropertyReference) {
        this.timeDelay = timeDelay;
        this.feedbackPropertyReference = feedbackPropertyReference;
    }

    @Override
    protected void writeImpl(ByteQueue queue) {
        write(queue, timeDelay, 0);
        write(queue, feedbackPropertyReference, 1);
    }

    public CommandFailure(ByteQueue queue) throws BACnetException {
        timeDelay = read(queue, UnsignedInteger.class, 0);
        feedbackPropertyReference = read(queue, DeviceObjectPropertyReference.class, 1);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((feedbackPropertyReference == null) ? 0 : feedbackPropertyReference.hashCode());
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
        final CommandFailure other = (CommandFailure) obj;
        if (feedbackPropertyReference == null) {
            if (other.feedbackPropertyReference != null)
                return false;
        }
        else if (!feedbackPropertyReference.equals(other.feedbackPropertyReference))
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
