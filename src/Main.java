
import java.io.*;

public class Main {

    public static void main(String[] args) {

        //gets the path for the txt file
        File studentFile = new File("students.txt");

        //outputs the location of the file in which it gets the path studen.txt is in
        System.out.println("File location: "
                + studentFile.getAbsolutePath());

        //what the output is if the text file is nowhere to be found
        if (!studentFile.exists()) {
            System.out.println("ERROR: students.txt not found!");
            return;
        }

        // random access is ran under try in which if it cant seem to organize the file into something similar to an array
        //its put into a read and write mode in which it can both read and write to the file, in which it can make the file instead
        try (RandomAccessFile file =
                     new RandomAccessFile(studentFile, "rw")) {

            //looks for how long the file is as random access cataloged everything into the variable file
            System.out.println("File size: "
                    + file.length() + " bytes");

            //declares string line, and that if the boolean found ever comes up it would equal false
            String line;
            boolean found = false;

            //while the condition is still being met
            while (true) {

                // with modifier long it gestfilepointer in which returns what the space between two points in the file is
                long position = file.getFilePointer();

                //reads the whole line of text
                line = file.readLine();

                //if the line has no information on it, it is considered null and the code stops
                if (line == null) {
                    break;
                }

                //the line taken at the runtime by readline is displayed
                System.out.println("Reading: " + line);

                // it splits the string into parts using the comma as a way to know how to seperate it
                String[] data = line.split(",");

                //if data in the data variable longer than 3 bytes it continues
                if (data.length < 3) {
                    continue;
                }

                //variable StudentID is delclares as the value of data[0] but what the data in that index is
                //the trim removes any spaces
                String studentID = data[0].trim();

               // if the data in data[0]=1002 it updates the string
                //with 1002 and alice brown 3.9
                if (studentID.equals("1002")) {

                    String updated =
                            "1002,Alice Brown,3.9";

                    // Verify same byte length
                    //if it isnt equal an error message is displayed and the code stops
                    if (updated.length() != line.length()) {
                        System.out.println(
                                "ERROR: Record length mismatch!");
                        break;
                    }

                    // configures the point on which filepointer starts at
                    file.seek(position);

                    // makes the string in variable updated into bytes
                    file.writeBytes(updated);


                    System.out.println("Record updated!");
                //the final out put of the code-
                   // File location: C:\Users\juare\IdeaProjects\lab 7\students.txt
                   // File size: 61 bytes
                    //the final output is Reading: 1001,John Smith,3.5
                    //Reading: 1002,Alice Brown,3.9
                    //Record updated!
                    found = true;
                    break;
                    //makes it so the boolean for found is true
                }
            }

            //if it isn't the same boolean as found it prints the line
            if (!found) {
                System.out.println(
                        "Student ID 1002 was not updated.");
            }

             //what runs if the error ioexception happens
            // it prints out what the error is and some details about it
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
