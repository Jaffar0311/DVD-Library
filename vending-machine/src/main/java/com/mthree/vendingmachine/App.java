package com.mthree.vendingmachine;

import com.mthree.vendingmachine.controller.VendingMachineController;
import com.mthree.vendingmachine.dao.VendingMachineAuditDao;
import com.mthree.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.mthree.vendingmachine.dao.VendingMachineDao;
import com.mthree.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.mthree.vendingmachine.dao.VendingMachinePersistenceException;
import com.mthree.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.mthree.vendingmachine.service.VendingMachineInvalidValueException;
import com.mthree.vendingmachine.service.VendingMachineNoItemInventoryException;
import com.mthree.vendingmachine.service.VendingMachineServiceLayer;
import com.mthree.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.mthree.vendingmachine.ui.UserIO;
import com.mthree.vendingmachine.ui.UserIOConsoleImpl;
import com.mthree.vendingmachine.ui.VendingMachineView;





public class App {
    public static void main(String[] args) throws 
            VendingMachinePersistenceException, 
            VendingMachineInvalidValueException, 
            VendingMachineNoItemInventoryException, 
            VendingMachineInsufficientFundsException {
        
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
        myDao.loadSnacksFromFile();
        VendingMachineController controller = new VendingMachineController(myService, myView);
        controller.run();
        myDao.writeSnacksToFile();
        
    }
}
