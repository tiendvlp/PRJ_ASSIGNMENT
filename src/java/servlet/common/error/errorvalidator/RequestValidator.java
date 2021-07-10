/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.common.error.errorvalidator;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import servlet.common.error.PresentableError;

/**
 *
 * @author dangminhtien
 */
public interface RequestValidator {
    public boolean init (HttpServletRequest req);
    public PresentableError getError ();
    public boolean hasError();
}
