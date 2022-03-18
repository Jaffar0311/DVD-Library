package com.company;

import com.company.controller.LibraryController;
import com.company.dao.LibraryDao;
import com.company.dao.LibraryDaoImpl;
import com.company.ui.LibraryView;
import com.company.ui.UserIO;
import com.company.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        LibraryView myView = new LibraryView(myIo);
        LibraryDao myDao = new LibraryDaoImpl();
        LibraryController controller =
                new LibraryController(myDao, myView);

        controller.run();
    }
}
