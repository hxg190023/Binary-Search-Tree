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

public class BinarySearchTree
{
    // The root of the tree is created and is initialized to null
    CreateNode root = null;

    // The unique word count is created as a static int variable initialized to 0,
    // to make sure that the changes in the respective methods update throughout the program,
    // and can be accessed by any method.
    static int uniqueWordCount = 0;

    /*
    This method takes the current word and its line number from the file, and the
    number of its occurrences which is 1.
    Then, this method will check if the word exists in the tree, and if it does, then it is not created as a node.
    If the word does not, then it traverses through the tree and uses the compareTo method to see exactly where the
    word is to be placed.
     */
    public void insertNode(String word, String lineNumber, int numOfWordOccur)
    {

        // Parent and point_to_next are created from CS2336Asg5_HXG190023.CreateNode class
        CreateNode parent;
        CreateNode point_to_next;

        // Current is set as the root to check to see if a word already exists
        CreateNode current = root;

        // The while loop checks to see if the word that is going to be inserted into the tree,
        // already exists.
        while (current != null)
        {
            // If the word already exists, then the numOfWordOccur is incremented, and it breaks.
            if (current.currentWord.equalsIgnoreCase(word))
            {
                numOfWordOccur++;
                break;
            }
            // If not it checks down the left side of the tree
            else if (word.compareToIgnoreCase(current.currentWord) < 0)
            {
                current = current.left;
            }
            // If not it checks down the right side of the tree
            else if (word.compareToIgnoreCase(current.currentWord) > 0)
            {
                current = current.right;
            }
        }

        // If the word already exists in the tree, then a new node is not created.
        // The word's word count is incremented and the line number is appended on the existing word
        if (numOfWordOccur > 1)
        {
            // Word count is incremented
            current.wordCount++;

            // The line number is added by appending the string, only if that line number does not
            // already exist.
            if (!current.lineNumber.contains(lineNumber))
            {
                current.lineNumber = current.lineNumber + ", " + lineNumber;
            }
        }
        // If the word is not already in the binary search tree
        else
        {
            // The new node is created with the following parameters
            parent = new CreateNode(word.toLowerCase(), lineNumber, numOfWordOccur);

            // The left and right child of the parent are set to null
            parent.left = null;
            parent.right = null;

            // The current word is set to lower case
            parent.currentWord = word.toLowerCase();

            // If the root of the tree is empty,
            // the parent becomes the root
            if (root == null)
            {
                root = parent;

                // The unique word count is incremented
                uniqueWordCount++;
            }
            // If the root of the tree is not empty
            else
            {
                // Becomes root, and will be used to check where to place the next word in the tree
                point_to_next = root;

                // This while loop will check and run until the current is null, and then will check to see
                // if the word becomes the right or left child of the parent
                while (point_to_next != null)
                {
                    // If the word is greater than the current word, then it becomes the right child, and its own
                    // parent.
                    if (word.compareToIgnoreCase(point_to_next.currentWord) > 0)
                    {
                        // If null, then it becomes the parent
                        if (point_to_next.right == null)
                        {
                            // Becomes the parent, and the unique word count is incremented by 1
                            point_to_next.right = parent;
                            uniqueWordCount++;

                            // Will break
                            break;
                        }
                        // Will point to the next right sub tree
                        else
                        {
                            point_to_next = point_to_next.right;
                        }
                    }
                    // If the word is less than the current word, then it becomes the left child, and its own
                    // parent.
                    else if (word.compareToIgnoreCase(point_to_next.currentWord) < 0)
                    {
                        if (point_to_next.left == null)
                        {
                            // Becomes the parent, and the unique word count is incremented by 1
                            point_to_next.left = parent;
                            uniqueWordCount++;

                            // Will break
                            break;
                        }
                        // Will point to the next left sub tree
                        else
                        {
                            point_to_next = point_to_next.left;
                        }
                    }
                }
            }
        }

    } // end of insertNode(String word, String lineNumber, int numOfWordOccur)

    /*
    This method will be called by the main class and it will call
    the inorderTraversal method to print the contents of the tree using inorder
    traversal, by sending the root of the tree to this method.
     */
    public void traverseTree()
    {
        // Send the root to the inorderTraversal method
        inorderTraversal(root);

    } // end of traverseTree()

    /*
    This method takes the root of the tree as a parameter, and uses the inorder traversal
    to print the words in alphabetical to the console.
     */
    public void inorderTraversal(CreateNode point_to_next)
    {
        // If the root is null, the program will return
        if (point_to_next == null)
        {
            return;
        }

        // Does inorder traversal on the left children of the tree
        inorderTraversal(point_to_next.left);

        // Prints the actual output to the console, for each word
        System.out.printf("%-15s %-10s %n", point_to_next.currentWord, point_to_next.wordCount
                + " " + point_to_next.lineNumber);

        // Does inorder traversal on the right children of the tree
        inorderTraversal(point_to_next.right);

    } // end of inorderTraversal(CS2336Asg5_HXG190023.CreateNode ptr)

    /*
    This method will be called by the main class and it will print the
    unique word count to the console.
     */
    public void displayUniqueWordCount()
    {
        System.out.println("\nNumber of Unique Words: " + uniqueWordCount);

    } // end of displayUniqueWordCount()

} // end of class CS2336Asg5_HXG190023.BinarySearchTree
