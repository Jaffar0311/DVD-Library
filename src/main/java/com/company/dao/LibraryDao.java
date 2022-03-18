package com.company.dao;

import com.company.dto.DVD;

import java.util.List;

public interface LibraryDao {

    DVD createDvd(DVD dvd) throws ClassLibraryDaoException;

    List<DVD> getAllDVDs() throws ClassLibraryDaoException;

    DVD getDVD(String title) throws ClassLibraryDaoException;

    DVD removeDVD(String title) throws ClassLibraryDaoException;

    DVD editDVD( DVD dvd) throws ClassLibraryDaoException;

    DVD searchDVDByTitle(String title) throws ClassLibraryDaoException;



}
