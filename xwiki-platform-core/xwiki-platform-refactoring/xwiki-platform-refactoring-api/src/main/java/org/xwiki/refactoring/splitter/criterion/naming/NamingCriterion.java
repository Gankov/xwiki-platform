/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.refactoring.splitter.criterion.naming;

import org.xwiki.model.reference.DocumentReference;
import org.xwiki.rendering.block.XDOM;
import org.xwiki.stability.Unstable;

/**
 * Interface for defining various criteria for naming new documents generated while splitting.
 * 
 * @version $Id$
 * @since 1.9M1
 */
public interface NamingCriterion
{
    /**
     * Marker used to separate page indexes.
     */
    String INDEX_SEPERATOR = "-";

    /**
     * Generates a suitable name for the new document represented by 'newDoc' which is a result of the splitting
     * operation.
     * 
     * @param newDoc the {@link XDOM} representing the newly split document
     * @return the name generated for the new document
     * @deprecated since 14.10.2, 15.0RC1 use {@link #getDocumentReference(XDOM)} instead
     */
    @Deprecated
    default String getDocumentName(XDOM newDoc)
    {
        return getDocumentReference(newDoc).toString();
    }

    /**
     * Generates a suitable reference for the new document represented by 'newDoc' which is a result of the splitting
     * operation.
     * 
     * @param newDoc the {@link XDOM} representing the newly split document
     * @return the reference generated for the new document
     * @since 14.10.2
     * @since 15.0RC1
     */
    @Unstable
    DocumentReference getDocumentReference(XDOM newDoc);
}
