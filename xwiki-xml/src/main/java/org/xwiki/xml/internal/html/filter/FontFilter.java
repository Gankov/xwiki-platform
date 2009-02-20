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
package org.xwiki.xml.internal.html.filter;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xwiki.xml.html.filter.AbstractHTMLFilter;

/**
 * Replaces invalid &lt;font&gt; tags with equivalent &lt;span&gt; tags using inline css rules.
 * 
 * @version $Id$
 * @since 1.8RC2
 */
public class FontFilter extends AbstractHTMLFilter
{
    /**
     * {@inheritDoc}
     */
    public void filter(Document document)
    {
        List<Element> fontTags = filterDescendants(document.getDocumentElement(), new String[] {FONT});
        for (Element fontTag : fontTags) {
            Element span = document.createElement(SPAN);
            moveChildren(fontTag, span);
            StringBuffer buffer = new StringBuffer();
            if (fontTag.hasAttribute(ATTRIBUTE_FONTCOLOR)) {
                buffer.append(String.format("color:%s;", fontTag.getAttribute(ATTRIBUTE_FONTCOLOR)));
            }
            if (fontTag.hasAttribute(ATTRIBUTE_FONTFACE)) {
                buffer.append(String.format("font-family=%s;", fontTag.getAttribute(ATTRIBUTE_FONTFACE)));
            }
            if (fontTag.hasAttribute(ATTRIBUTE_FONTSIZE)) {
                buffer.append(String.format("font-size=%spt;", fontTag.getAttribute(ATTRIBUTE_FONTSIZE)));
            }
            if (fontTag.hasAttribute(ATTRIBUTE_STYLE)) {
                buffer.append(fontTag.getAttribute(ATTRIBUTE_STYLE));
            }
            if (!buffer.toString().trim().equals("")) {
                span.setAttribute(ATTRIBUTE_STYLE, buffer.toString());
            }
            fontTag.getParentNode().insertBefore(span, fontTag);
            fontTag.getParentNode().removeChild(fontTag);
        }
    }
}
