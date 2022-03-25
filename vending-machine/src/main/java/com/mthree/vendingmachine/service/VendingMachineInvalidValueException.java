
package com.mthree.vendingmachine.service;


public class VendingMachineInvalidValueException extends Exception {
    public VendingMachineInvalidValueException(String message) {
        super(message);
    }

    public VendingMachineInvalidValueException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
