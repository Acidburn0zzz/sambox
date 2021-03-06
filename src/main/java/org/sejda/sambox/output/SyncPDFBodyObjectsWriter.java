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
package org.sejda.sambox.output;

import static org.sejda.util.RequireUtils.requireNotNullArg;

import java.io.IOException;

import org.sejda.sambox.cos.IndirectCOSObjectReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of a PDFBodyObjectsWriter that synchronously writes {@link IndirectCOSObjectReference}
 * 
 * @author Andrea Vacondio
 */
class SyncPDFBodyObjectsWriter implements PDFBodyObjectsWriter
{
    private static final Logger LOG = LoggerFactory.getLogger(SyncPDFBodyObjectsWriter.class);

    private IndirectObjectsWriter writer;

    SyncPDFBodyObjectsWriter(IndirectObjectsWriter writer)
    {
        requireNotNullArg(writer, "Cannot write to a null writer");
        this.writer = writer;
    }

    @Override
    public void writeObject(IndirectCOSObjectReference ref) throws IOException
    {
        writer.writeObjectIfNotWritten(ref);

    }

    @Override
    public void onWriteCompletion()
    {
        LOG.debug("Written document body");
    }

    @Override
    public void close()
    {
        // nothing
    }
}
