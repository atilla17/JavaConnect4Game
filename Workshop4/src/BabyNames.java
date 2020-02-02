import java.io.Console;
import java.io.IOException;
import java.util.Scanner;
import  java.io.FileInputStream;

public class BabyNames {

    public FileInputStream inputStream = null;
    public Scanner sc = null;
    boolean keepDataInMemory = false;
    boolean dataLoadedInMemory = false;

    String dataPath ="data\\babynamesranking2001to2010\\babynamesranking";




    private int readInt(String errorMsg)
    {
        try {
            Scanner in = new Scanner(System.in);
            int year = in.nextInt();
            return  year;
        }
        catch (Exception e)
        {
            System.out.println(errorMsg);
            return readInt(errorMsg);
        }
    }
    private String readGender()
    {
        try {
            Scanner in = new Scanner(System.in);
            String gender = "";
            while (gender.compareTo("f") != 0 && gender.compareTo("m") != 0) {
                gender = in.next().toLowerCase().trim();
                if(gender.compareTo("f") != 0 && gender.compareTo("m") != 0)
                {
                    System.out.println("invalid entry, valid entrys are as follows m for male f for female" + " input: " + gender);
                }
            }
            return  gender;

        }
        catch (Exception e)
        {
            System.out.println("error " +e);
            return readGender();
        }
    }


    private void mainMenue()
    {
        Scanner in =  new Scanner(System.in);
       String command = in.next();
    }
    private void parseCommand()
    {

    }


    public void readNameSpecific()
    {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter year: ");
        int year = readInt("Please enter year number only");
        System.out.print("Enter gender m/f");
        String gender = readGender();
        System.out.print("Enter name: ");
        String name = in.next();

        try {
            readNameFromFile(dataPath, name, year, gender);
        }
        catch (IOException e)
        {
            System.out.println("No file for the selected year");

        }

        System.out.println("Search for another name y/n ?");

        String input = in.next();

        if(input.toLowerCase().compareTo("y") == 0 )
        {
            readNameSpecific();
        }
        else
        {
            System.out.println("exiting");
        }






    }


    //Reads a single file and searches for a specific entry
    public void readNameFromFile(String path, String name, int year, String gender) throws IOException
    {
        try {
            boolean foundName = false;
            inputStream = new FileInputStream(path + year +".txt");
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                //String line = sc.nextLine();
                int rank = sc.nextInt();
                String boysName = sc.next();
                int boysNumber = sc.nextInt();
                String girlsName = sc.next();
                int girlsNumber = sc.nextInt();


                //Logic block
                if (gender.compareTo("m") == 0  && boysName.toLowerCase().trim().compareTo(name.toLowerCase().trim()) == 0)
                {
                    System.out.println(boysName + " is ranked #" + rank +" of boy names ranked in " + year);
                    foundName = true;
                }
                else if (gender.compareTo("f") == 0  && girlsName.toLowerCase().trim().compareTo(name.toLowerCase().trim()) == 0){
                    System.out.println(girlsName + " is ranked #" + rank +" of girl names ranked in " + year);
                    foundName = true;
                }
            }
            if(foundName == false) {
                System.out.println("No ranking information for the name " + name);
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
    }





}
