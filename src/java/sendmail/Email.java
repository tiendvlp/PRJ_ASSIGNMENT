/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendmail;

/**
 *
 * @author dangminhtien
 */
public interface Email {
    public String getHtml ();
    public String getSubject ();
    public String getReceiverEmail();
    public String getMessage();
}
