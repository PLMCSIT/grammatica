/*
 * Element.java
 *
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the BSD license.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * LICENSE.txt file for more details.
 *
 * Copyright (c) 2003-2015 Per Cederberg. All rights reserved.
 */

package net.percederberg.grammatica.parser.re;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import net.percederberg.grammatica.parser.ReaderBuffer;

/**
 * A regular expression element. This is the common base class for all
 * regular expression elements, i.e. the parts of the regular
 * expression.
 *
 * @author   Per Cederberg
 * @version  1.5
 */
abstract class Element implements Cloneable {

    /**
     * Creates a copy of this element. The copy will be an instance
     * of the same class matching the same strings. Copies of elements
     * are necessary to allow elements to cache intermediate results
     * while matching strings without interfering with other threads.
     *
     * @return a copy of this element
     */
    public abstract Object clone();

    /**
     * Returns the length of a matching string starting at the
     * specified position. The number of matches to skip can also be
     * specified, but numbers higher than zero (0) cause a failed
     * match for any element that doesn't attempt to combine other
     * elements.
     *
     * @param m              the matcher being used
     * @param buffer         the input character buffer to match
     * @param start          the starting position
     * @param skip           the number of matches to skip
     *
     * @return the length of the longest matching string, or
     *         -1 if no match was found
     *
     * @throws IOException if an I/O error occurred
     */
    public abstract int match(Matcher m,
                              ReaderBuffer buffer,
                              int start,
                              int skip)
        throws IOException;

    /**
     * Prints this element to the specified output stream.
     *
     * @param output         the output stream to use
     * @param indent         the current indentation
     */
    public final void printTo(PrintStream output, String indent) {
        PrintWriter  writer = new PrintWriter(output);

        printTo(writer, indent);
        writer.flush();
    }

    /**
     * Prints this element to the specified output stream.
     *
     * @param output         the output stream to use
     * @param indent         the current indentation
     */
    public abstract void printTo(PrintWriter output, String indent);

}
