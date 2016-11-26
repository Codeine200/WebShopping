package com.wizardjava.utils;

/**
 * Created by Codeine on 09.11.2016.
 */
public class PageNavigation {


    /**
     * Return the number of pages for the current source list.
     */
    public static int getPageCount(long countElements, int recordsPerPage) {
        float nrOfPages = (float) countElements / recordsPerPage;
        return (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages);
    }
}
