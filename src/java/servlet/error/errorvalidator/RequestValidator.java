/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.error.errorvalidator;

import javax.servlet.ServletRequest;
import servlet.error.PresentableError;

/**
 *
 * @author dangminhtien
 */
public interface RequestValidator {
    public boolean init (ServletRequest req);
    public PresentableError getError ();
    public boolean hasError();
}
