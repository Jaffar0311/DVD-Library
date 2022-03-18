package com.company.dao;

import com.company.dto.DVD;

import java.io.*;
import java.util.*;

public class LibraryDaoImpl implements LibraryDao{



    public static final String DVD_FILE = "dvd.txt";
    public static final String DELIMITER = "    ";


    private Map<String, DVD> dvds = new HashMap<>();

    @Override
    public DVD createDvd( DVD dvd) throws ClassLibraryDaoException{
        loadDVD();
        DVD newDVD = dvds.put(dvd.getTitle(), dvd);
        writeDVD();
        return newDVD;

    }

    @Override
    public List<DVD> getAllDVDs() throws ClassLibraryDaoException {
        loadDVD();
        return new ArrayList<DVD>(dvds.values());
    }

    @Override
    public DVD getDVD(String title) throws ClassLibraryDaoException {
        loadDVD();
        return dvds.get(title);
    }

    @Override
    public DVD removeDVD(String title) throws ClassLibraryDaoException{
        loadDVD();
        DVD removeDVD = dvds.remove(title);
        writeDVD();
        return removeDVD;
    }

    @Override
    public DVD editDVD(DVD dvd) throws ClassLibraryDaoException {

        loadDVD();
        DVD editedDvd = dvds.replace(dvd.getTitle(), dvd);
        writeDVD();
        return editedDvd;


    }

    @Override
    public DVD searchDVDByTitle(String title) throws ClassLibraryDaoException {
        loadDVD();
        return dvds.get(title);
    }

    private DVD unmarshallDVD(String dvdAsText){
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        String title = dvdTokens[0];
        DVD dvdFromFile = new DVD(title);

        dvdFromFile.setReleaseDate(dvdTokens[1]);
        dvdFromFile.setMPAARating(dvdTokens[2]);
        dvdFromFile.setDirector(dvdTokens[3]);
        dvdFromFile.setStudio(dvdTokens[4]);
        dvdFromFile.setUserRating(dvdTokens[5]);

        return dvdFromFile;

    }

    private void loadDVD() throws ClassLibraryDaoException{
        Scanner scanner;

        try{
            scanner = new Scanner(new BufferedReader(new FileReader(DVD_FILE)));
        }
        catch(FileNotFoundException e){
            throw new ClassLibraryDaoException("-_- Could not load roster data into memory.", e);
        }

        String currentLine;
        DVD currentDVD;
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentDVD = unmarshallDVD(currentLine);

            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        scanner.close();
    }

    private String marshallDVD(DVD aDvd){
        String dvdAsText = aDvd.getTitle() + DELIMITER;
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;
        dvdAsText += aDvd.getMPAARating() + DELIMITER;
        dvdAsText += aDvd.getDirector() + DELIMITER;
        dvdAsText += aDvd.getStudio() + DELIMITER;
        dvdAsText += aDvd.getUserRating() + DELIMITER;

        return dvdAsText;

    }

    private void writeDVD() throws ClassLibraryDaoException{
        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(DVD_FILE));
        }
        catch (IOException e){
            throw new ClassLibraryDaoException("Could not save data", e);
        }

        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDs();

        for(DVD currDVD : dvdList){
            dvdAsText = marshallDVD(currDVD);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }


}
