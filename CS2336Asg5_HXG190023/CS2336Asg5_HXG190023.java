/*
Name:               Harsh Gopalan
Assignment:         Binary Search Tree Cross-Reference
Language:           Java

General Description of Program:
    This program reads the contents of a file and displays the contents as it gets read into a binary search tree.
    The binary search tree consists of nodes, that each represent the word, the word's line number, and the count of
    the word (which will be used to check if that word already exists in the tree).
    The binary search tree will be sorted alphabetically using the inorder traversal method.
    The program will then display the sorted tree and below will display the total number of unique words,
    followed by the total number of total words, followed by the total number of lines in the data file.
    The program will then exit after the final output statement has been printed.
    
Additional Notes about the Program: 
  - This program will remove numbers and not count them in the binary search tree, the unique word count, and the total word count. 
  - This program will run for the given data files, Asg4Data1.txt, Asg4Data2.txt, Asg4Data3.txt, but the same cannot be said about any data file, 
    as the program was coded based on given conditions, such as how a possible text file would be. 
  - The program also has some given conditions, which include: 
        - The program ignores parantheses and quotations marks.
        - The program considers contractions, such as "don't" as one word, and not as two words. 
        - The program considers plurals and variations of a word as different words. 
        - The program will not include the words, "the", "a", and "an", in the binary search tree, the unique word count, and the total word count. 
        - The program will exit after printing one set of output. 
  - The exception word i.e. when printed in the tree, can be counted as one word or as two separate words. 
    Since this is not specified in the program, and is different from cases such as words that have a hyphen in the middle, this can 
    be included in the unique word count and total word count as a single word or as two separate words.
        - Specifically, in this program, this exception causes the letters i and e to be included in the word count as
          two separate words. This should be accepted as an acceptable output.
        - This exception happens when running data file, Asg4Data2.txt.
        - Actual Output:
            e 2 320, 331
            i 11 101, 104, 122, 303, 320, 325, 326, 331, 365, 442, 445
        - So, this exception causes i and e to be printed as separate words from lines 320 and 331
 */

package CS2336Asg5_HXG190023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CS2336Asg5_HXG190023
{
    /*
    This method takes the file name (String) as a parameter and reads in a valid
    file name/existing file or the sentinel value to exit from the user.
    This method will return an asterisk or a valid file name and the consequent actions
    will be done in main.
    */
    public static String getFileFromUser(String fileName)
    {
        // Scanner to read in the file/
        Scanner scan = new Scanner(System.in);

        // User is asked to enter the file name or the sentinel value.
        System.out.print("Please enter the valid file name or \n" +
                "Please enter the sentinel value * to exit the program: ");

        // File gets read in.
        fileName = scan.nextLine();

        // If the file name is equal to the sentinel value, then it will return the sentinel value to main.
        if (fileName.equals("*"))
        {
            System.out.println("");
            System.out.println("The program will now exit. Thank you!");
            return "*";
        }

        /*
        Will do the input validation here, to make sure that only
        a valid input file is entered.
        */
        while (!new File(fileName).exists())
        {
            System.out.println("");
            System.out.print("Please enter a valid file name or \nPlease " +
                    "enter the sentinel value * to exit the program: ");

            // File gets read in.
            fileName = scan.nextLine();

            // Will check for the sentinel value again.
            // If the file name is equal to the sentinel value, then it will return the sentinel value to main.
            if (fileName.equals("*"))
            {
                System.out.println("");
                System.out.println("The program will now exit. Thank you!");
                return "*";
            }
        }

        // Else will return the valid file name.
        return fileName;

    } // end of String getFileFromUser(String fileName)

    /*
    The main method.
    Prints the opening statement and calls the getFileFromUser method and the accessBinarySearchTree method.
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        // Opening statements are printed to the user.
        System.out.println("Welcome to \"Assignment 5 - Binary Search Tree Cross-Reference!\"");
        System.out.println("");

        // Stores the file name.
        String fileName = "";

        // File name is stored once it is retrieved from user post-input validation.
        fileName = getFileFromUser(fileName);

        // If sentinel value, then exits the program.
        if (fileName == "*")
        {
            return;
        }

        // The file is actually created and opened.
        Scanner file = new Scanner(new File(fileName));

        // Calls this method to access and print the binary search tree along with
        // the final output statements.
        accessBinarySearchTree(file);

        // Closes input file
        file.close();

    } // end of main(String[] args) throws FileNotFoundException

    /*
    This method takes the file (Scanner) as a parameter and reads the file contents line by line.
    As this is being done, the file will print that line and its corresponding line number.
    Next, the current line is removed of all punctuations in a series of statements.
    Then, it will tokenize this string to get each individual word to send to the insertNode method.
    Finally, it will increment the line number and run though this process until the last word has been sent.
     */
    public static void accessBinarySearchTree(Scanner file)
    {
        // Creates an object 'binarySearch' of the CS2336Asg4_HXG190023.BinarySearchTree class.
        BinarySearchTree binarySearch = new BinarySearchTree();

        // Stores a whole line of the file and the initial line number = 1, the number of word occurrences = 1,
        // and the total word count = 0, respectively.
        String nextLine;
        int lineNumber = 1, numOfWordOccur = 1;
        int totalWordCount = 0;

        System.out.println("");

        // Reads through the entire file, line by line, until the file has no more line.
        while (file.hasNextLine())
        {
            // Line gets stores in nextLine, and gets printed with its line number to the console.
            nextLine = file.nextLine();
            System.out.println(lineNumber + ". " + nextLine);
            
            // The current line is removed of the necessary punctuations and number values.
            nextLine = nextLine.replaceAll("[0-9]"," ");
            nextLine = nextLine.replaceAll("-", " ");
            nextLine = nextLine.replaceAll(";", "").replaceAll(":", "")
                    .replaceAll(",", "").replaceAll("!", "")
                    .replaceAll("'", "")
                    .replaceAll("\\p{Punct}", " ");


            // Each line is tokenized to separate each word in the line.
            StringTokenizer word = new StringTokenizer(nextLine);

            // Will store the tokenized word.
            String currentWord;

            // Will run until current line has no more words.
            while (word.hasMoreTokens())
            {
                currentWord = word.nextToken();

                // Ignores the 'ignore' words, by using the continue.
                if (currentWord.equalsIgnoreCase("The")
                        || currentWord.equalsIgnoreCase("An")
                        || currentWord.equalsIgnoreCase("A"))
                {
                    continue;
                }

                // Sends each word to the insertNode method in the current line, until line has no more words.
                binarySearch.insertNode(currentWord, String.valueOf(lineNumber), numOfWordOccur);

                // Increments the total word count by 1.
                totalWordCount++;
            }

            // Increments line number by 1.
            lineNumber++;
        }

        System.out.println("");

        // Calls the traverseTree method to display the binary search tree using the inorder traversal.
        binarySearch.traverseTree();

        // Calls the displayUniqueWordCount method to display the unique word count.
        binarySearch.displayUniqueWordCount();

        // Prints the total number of words and the total number of lines, respectively.
        System.out.println("Number of Total Words: " + totalWordCount);
        System.out.println("Total Number of Lines: " + (lineNumber - 1));

    } // end of accessBinarySearchTree(Scanner file)

} // end of class CS2336Asg5_HXG190023.CS2336Asg5_HXG190023
