package com.company.controller;

import com.company.dao.ClassLibraryDaoException;
import com.company.dao.LibraryDao;
import com.company.dao.LibraryDaoImpl;
import com.company.dto.DVD;
import com.company.ui.LibraryView;
import com.company.ui.UserIO;
import com.company.ui.UserIOConsoleImpl;

import java.util.List;

public class LibraryController {

    private LibraryView view;
    private UserIO io = new UserIOConsoleImpl();
    private LibraryDao dao;

    public LibraryController(LibraryDao dao, LibraryView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run(){
        boolean keepGoing = true;
        int menuselection = 0;

        try {
            while (keepGoing) {

                menuselection = getMenuSelection();

                switch (menuselection) {
                    case 1:
                        listDVD();
                        break;
                    case 2:
                        createDVD();
                        break;
                    case 3:
                        searchDVDByTitle();
                        break;
                    case 4:
                        editDVD();
                        break;
                    case 5:
                        removeDVD();
                        break;
                    case 6:
                        displayDVDInfo();
                        break;
                    case 7:
                        keepGoing = false;
                        break;

                    default:
                        unknownCommand();
                }
            }

            exitMessage();
        }
        catch (ClassLibraryDaoException e){
            view.displayErrorMessage(e.getMessage());
        }


    }

    private void displayDVDInfo() throws ClassLibraryDaoException {
        String title = view.getDvd();
        DVD dvd = dao.getDVD(title);
        view.displayDvd(dvd);
    }


    private void searchDVDByTitle() throws ClassLibraryDaoException {
        view.displayDisplayDVDBanner();
        String title = view.getDvd();
        DVD dvd = dao.searchDVDByTitle(title);
        view.searchDVDByTitle(dvd);

    }

    private void removeDVD() throws ClassLibraryDaoException {
        boolean remove = true;
        do {
            view.displayRemoveDVDBanner();
            String title = view.getDvd();
            DVD removeDVD = dao.removeDVD(title);
            remove = view.displayRemoveResult(removeDVD);
        }
        while (remove);

    }

    private void createDVD() throws ClassLibraryDaoException {
        boolean add = true;
        do {
            view.displayCreateDVDBanner();
            DVD newDVD = view.getNewDVDInfo();
            dao.createDvd(newDVD);
            add = view.displayCreateSuccessBanner();
        }
        while(add);
    }

    private void listDVD() throws ClassLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }
    private void editDVD() throws ClassLibraryDaoException{
        view.displayeditDVDBanner();
        String title = view.getDvd();
        DVD dvd = dao.getDVD(title);
        DVD edited = view.editDVD(dvd);
        DVD rem = dao.removeDVD(title);
        dao.createDvd(edited);
        view.displayDVDEditedBanner();

    }
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

}
