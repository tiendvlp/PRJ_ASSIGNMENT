/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.common.error;

/**
 *
 * @author dangminhtien
 */
public class LoginError implements PresentableError {
    private String incorrectMessage;

    public void setIncorrect() {
        this.incorrectMessage = "Incorrect user id";
    }

    public String getIncorrectMessage() {
        return incorrectMessage;
    }
    
}
