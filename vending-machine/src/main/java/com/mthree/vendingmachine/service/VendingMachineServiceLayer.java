
package com.mthree.vendingmachine.service;

import com.mthree.vendingmachine.dao.VendingMachinePersistenceException;
import com.mthree.vendingmachine.dto.Snack;
import java.util.List;


public interface VendingMachineServiceLayer {
    void createSnack(Snack snack) throws
            VendingMachineDuplicateIdException,
            VendingMachineDataValidationException,
            VendingMachinePersistenceException;
 
    List<Snack> getAllSnacks() throws
            VendingMachinePersistenceException;
 
    Snack getSnack(String snackTitle) throws
            VendingMachinePersistenceException;
 
    Snack removeSnack(String snackTitle) throws 
            VendingMachineNoItemInventoryException, 
            VendingMachineInsufficientFundsException,
            VendingMachinePersistenceException;
    
    boolean insertMoneyToMachine(String insertedMoney) throws
            VendingMachineInvalidValueException,
            VendingMachinePersistenceException;
    
    String getMoneyInMachine();
    
    String getChange() throws VendingMachinePersistenceException;
}
