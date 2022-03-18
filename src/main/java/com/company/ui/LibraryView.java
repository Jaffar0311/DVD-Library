package com.company.ui;

import com.company.dto.DVD;

import java.util.List;

public class LibraryView {

   private UserIO io;

   public LibraryView(UserIO io){
       this.io = io;
   }

        public int printMenuAndGetSelection() {

            io.print("Main Menu");
            io.print("1. List DVD");
            io.print("2. Create DVD");
            io.print("3. Search DVD By Title");
            io.print("4. Edit DVD");
            io.print("5. Remove DVD");
            io.print("6. Display DVD's info");
            io.print("7. Exit");

            return io.readInt("Please select from the about choices.", 1, 7);
        }

    public DVD getNewDVDInfo(){
        String title = io.readString("Please enter the title");
        String releaseDate = io.readString("Please enter the release date");
        String MPAARating = io.readString("Please enter the rating");
        String director = io.readString("Please enter the director");
        String studio = io.readString("Please enter the studio");
        String userRating = io.readString("Please enter the user rating");

        DVD currentDVD = new DVD(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMPAARating(MPAARating);
        currentDVD.setDirector(director);
        currentDVD.setStudio(studio);
        currentDVD.setUserRating(userRating);

        return currentDVD;
    }

    public boolean displayCreateSuccessBanner() {

        int option = io.readInt("DVD successfully created." + "\n1. Add another DVD " +
                                "\n2. Main Menu", 1, 2);
        if(option == 1){
            return true;
        }
        else {
            return false;
        }
    }


    public void displayDVDList(List<DVD> dvdList){
            if(dvdList.size() !=0 ) {
                for (DVD currDVD : dvdList) {
                    String dvdInfo = String.format("#%s : %s %s %s %s %s",
                            currDVD.getTitle(),
                            currDVD.getReleaseDate(),
                            currDVD.getMPAARating(),
                            currDVD.getDirector(),
                            currDVD.getStudio(),
                            currDVD.getUserRating());
                    io.print(dvdInfo);
                }
            }
            else{
                io.print("There are no DVDs");
            }
            io.readString("Please enter to continue");
    }

    public void searchDVDByTitle(DVD dvd) {
        if (dvd != null) {
            int option = io.readInt("DVD Found!" +
                    "\n1. Display Info\n2. Go back to the main menu",1,2);
            if (option == 1) {
                displayDvd(dvd);
            }
        } else {
            io.readString("DVD does not exist, press enter to continue.");
        }
    }


    public String getDvd(){
            return io.readString("Please enter the title");
    }

    public void displayDvd(DVD dvd){
            if(dvd != null){
               io.print(dvd.toString());
            }
            else{
                io.print("No such DVD");
            }
        io.readString("Please hit enter to continue");
    }


    public boolean displayRemoveResult(DVD dvd){
        int option;
        if (dvd != null) {
            option = io.readInt(dvd.getTitle()+" removed from the library " +
                    "\n1.Remove another DVD " +
                    "\n2.Main menu", 1, 2);
        } else {
            option = io.readInt("DVD doesn't exist! " +
                    "\n1.Delete something else? " +
                    "\n2.Main menu", 1, 2);
        }

        if (option == 1) {
            return true;
        } else {
            return false;
        }
    }



    public DVD editDVD(DVD dvd) {
        boolean keepEditing = true;
        do {
            if (dvd != null) {
                int option = io.readInt("What would you like to edit?" +
                        "\n1.Title" +
                        "\n2.Release Date" +
                        "\n3.MPAA Rating" +
                        "\n4.Director" +
                        "\n5.Studio" +
                        "\n6.User Rating",1,6);

                switch (option) {
                    case 1:
                        String newTitle = io.readString("New title: ");
                        dvd.setTitle(newTitle);
                        updateStatement();
                        break;
                    case 2:
                        String newRelease = io.readString("New release date: ");
                        dvd.setReleaseDate(newRelease);
                        updateStatement();
                        break;
                    case 3:
                        String newMPAARating = io.readString("New rating:");
                        dvd.setMPAARating(newMPAARating);
                        updateStatement();
                        break;
                    case 4:
                        String newDirector = io.readString("New director:");
                        dvd.setDirector(newDirector);
                        updateStatement();
                        break;
                    case 5:
                        String newStudio = io.readString("New studio: ");
                        dvd.setStudio(newStudio);
                        updateStatement();
                        break;
                    case 6:
                        String newUserRating = io.readString("New User Rating.");
                        dvd.setUserRating(newUserRating);
                        updateStatement();
                        break;
                }
                int continueEditing = io.readInt("Would you like to continue editing?" +
                        "\n1.Yes" +
                        "\n2.No",1,2);
                if (continueEditing == 2) {
                    keepEditing = false;
                }
            } else {
                io.print("DVD does not exist");
                keepEditing = false;
            }

        } while (keepEditing);
        io.readString("Finished editing, press enter to continue");
        return dvd;
    }
    public void updateStatement(){
        io.print("Updated.");
    }


    public void displayeditDVDBanner () {
        io.print("=== Edit DVD ===");
    }

    public void displayDisplayDVDBanner () {
        io.print("=== Display DVD ===");
    }

    public void displayRemoveDVDBanner () {
        io.print("=== Remove DVD ===");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }

    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }

    public void displayDVDEditedBanner() {
        io.print("=== DVD Edited===");
    }


    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }


    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

}
