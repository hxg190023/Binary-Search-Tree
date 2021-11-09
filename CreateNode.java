/*
Name & NET-ID:      Harsh Gopalan - HXG190023
Class & Section:    CS 2336. 005
Assignment:         Assignment 5 - Binary Search Tree Cross-Reference
Start / End Date:   10/25/2021 - 11/3/2021
Purpose:            Writing this program for this class to complete Assignment 5.

General Description of Program:

    This program reads the contents of a file and displays the contents as it gets read into a binary search tree.
    The binary search tree consists of nodes, that each represent the word, the word's line number, and the count of
    the word (which will be used to check if that word already exists in the tree).
    The binary search tree will be sorted alphabetically using the inorder traversal method.
    The program will then display the sorted tree and below will display the total number of unique words,
    followed by the total number of total words, followed by the total number of lines in the data file.
    The program will then exit after the final output statement has been printed.

Notes for the TA/Grader:
  - The following program resembles my Asg4, as this is continuation of that program with some changes in the data
    structure of the program. As per the instructions, we "may use some of the code you wrote for the assignment."
  - As per the instructions, and after talking to the instructor/professor, the datafile titled, "Asg4Data2.txt",
    contains numbers. Since this goes against the instructions saying that "test data will not contain numbers",
    the professor has instructed students to either consider numbers as a word or we can ignore them. Both are
    acceptable.
  - As per the instructions, since there is no specific requirement that we cannot use static methods, this program
    does contain static methods.
  - As per the instructions, this program will not keep running after one set of output is printed, as the instructions
    state that "Your program will exit after printing the output".
  - After talking to the instructor/professor, the exception word i.e. when printed in the tree, can be counted
    as one word or as two separate words. Since this is not specified in the program, and is different from cases such
    as words that have a hyphen in the middle, this can be included in the unique word count and total word count as a
    single word or as two separate words.
        - Specifically, in this program, this exception causes the letters i and e to be included in the word count as
          two separate words. This should be accepted as an acceptable output.
        - This exception happens when running data file, Asg4Data2.txt.
        - Actual Output:
            e 2 320, 331
            i 11 101, 104, 122, 303, 320, 325, 326, 331, 365, 442, 445
        - So, this exception causes i and e to be printed as separate words from lines 320 and 331
 */

package CS2336Asg5_HXG190023;

public class CreateNode
{
    // The left and right child of the tree is crated, respectively
    CreateNode left;
    CreateNode right;

    // Will store the word count as an integer
    int wordCount;

    // Will store the word and its line number as a string
    String currentWord, lineNumber;

    // The argument constructor that takes in the word, the line number and the word count.
    // Then, it creates the node.
    CreateNode(String word, String lineNumber, int wordCount)
    {
        // line number is stored as new line number
        this.lineNumber = lineNumber;

        // Word is converted to lower case as the case does not matter in this program
        this.currentWord = word.toLowerCase();

        // word count is stored as new word count
        this.wordCount = wordCount;

    } // end of  CS2336Asg5_HXG190023.CreateNode(String word, String lineNumber, int wordCount)

} // end of class CS2336Asg5_HXG190023.CreateNode