/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.pdfbox.cos;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * This class represents a floating point number in a PDF document.
 *
 * @author Ben Litchfield
 * 
 */
public class COSFloat extends COSNumber
{
    private BigDecimal value;

    /**
     * @param aFloat The primitive float object that this object wraps.
     */
    public COSFloat(float aFloat)
    {
        // use a BigDecimal as intermediate state to avoid
        // a floating point string representation of the float value
        value = new BigDecimal(String.valueOf(aFloat));
    }

    /**
     * @param aFloat The primitive float object that this object wraps.
     * @throws IOException If aFloat is not a float.
     */
    public COSFloat(String aFloat) throws IOException
    {
        try
        {
            value = new BigDecimal(aFloat);
        }
        catch (NumberFormatException e)
        {
            throw new IOException("Expected floating point number actual='" + aFloat + "'", e);
        }
    }

    @Override
    public float floatValue()
    {
        return value.floatValue();
    }

    @Override
    public double doubleValue()
    {
        return value.doubleValue();
    }

    @Override
    public long longValue()
    {
        return value.longValue();
    }

    @Override
    public int intValue()
    {
        return value.intValue();
    }

    @Override
    public boolean equals(Object o)
    {
        return o instanceof COSFloat
                && Float.floatToIntBits(((COSFloat) o).value.floatValue()) == Float
                        .floatToIntBits(value.floatValue());
    }

    @Override
    public int hashCode()
    {
        return value.hashCode();
    }

    @Override
    public String toString()
    {
        return value.stripTrailingZeros().toPlainString();
    }

    @Override
    public void accept(COSVisitor visitor) throws IOException
    {
        visitor.visit(this);
    }
}
