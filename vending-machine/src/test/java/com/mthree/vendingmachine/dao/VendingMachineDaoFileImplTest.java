
package com.mthree.vendingmachine.dao;

import com.mthree.vendingmachine.dto.Snack;
import java.math.BigDecimal;
import java.util.HashMap;

import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineDaoFileImplTest {
    VendingMachineDao dao;
    
    public VendingMachineDaoFileImplTest() {
        dao = new VendingMachineDaoFileImpl();
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of insertMoneyToMachine method, of class VendingMachineDaoFileImpl.
     */
    @Test
    public void testInsertMoneyToMachine() {
        System.out.println("insertMoneyToMachine");
        BigDecimal insertedMoney = new BigDecimal("1.25");
        
        try {
            dao.insertMoneyToMachine(insertedMoney);
        } catch (Exception e) {
            fail("Test failed with Exception " + e);
        }
        return;
    }


    /**
     * Test of addSnack method, of class VendingMachineDaoFileImpl.
     */
    @Test
    public void testAddSnack() {
        System.out.println("addSnack");
        String snackTitle = "NewSnack";
        Snack snack = new Snack("NewSnack", "1.00", 0);
        Snack expResult = null;
        Snack result = dao.addSnack(snackTitle, snack);
        assertEquals(expResult, result);
    }


    /**
     * Test of loadSnacksFromFile method, of class VendingMachineDaoFileImpl.
     */
    @Test
    public void testLoadSnacksFromFile() {
        System.out.println("loadSnacksFromFile");
        boolean expResult = true;
        boolean result = dao.loadSnacksFromFile();
        assertEquals(expResult, result);
    }

    /**
     * Test of writeSnacksToFile method, of class VendingMachineDaoFileImpl.
     */
    @Test
    public void testWriteSnacksToFile() {
        System.out.println("writeSnacksToFile");
        
        Map<String, Snack> snacks = new HashMap<>();
        snacks.put("Snickers", new Snack("Snickers", "1.00", 4));
        snacks.put("Coke", new Snack("Coke", ".70", 6));
        snacks.put("Lays", new Snack("Lays", "2.00", 1));
        snacks.put("Twix", new Snack("Twix", "1.20", 2));
        dao.addSnack("Snickers", snacks.get("Snickers"));
        dao.addSnack("Coke", snacks.get("Coke"));
        dao.addSnack("Lays", snacks.get("Lays"));
        dao.addSnack("Twix", snacks.get("Twix"));
        
        boolean expResult = true;
        boolean result = dao.writeSnacksToFile();
        assertEquals(expResult, result);
    }

    /**
     * Test of getChange method, of class VendingMachineDaoFileImpl.
     */
    @Test
    public void testGetChange() {
        return;
    }

    /**
     * Test of getChangeInCoins method, of class VendingMachineDaoFileImpl.
     */
    @Test
    public void testGetChangeInCoins() {
        return;
    }
    
}
