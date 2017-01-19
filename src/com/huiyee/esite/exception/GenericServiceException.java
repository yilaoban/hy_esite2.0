package com.huiyee.esite.exception;

/**
 * @author Yufeng Li
 * @version $Id
 *  
 */
public class GenericServiceException extends RuntimeException {
    private static final long serialVersionUID = 1995032920020121L;
    public GenericServiceException(String arg0) {
        super(arg0);

    }

    public GenericServiceException(String arg0, Throwable arg1) {
        super(arg0, arg1);

    }
}