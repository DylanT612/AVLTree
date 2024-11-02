/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 10/5/2024

Author: Dylan Theis
Date: Fall 2024
Class: CSC420
Project: ProcessScheduler
Description: AVLNode class for the ProcessScheduler. Class holding the node's information.
*/

// AVLNode Class
public class AVLNode {
    // Variables for process, left, right, and height
    ProcessInfo process;
    AVLNode left, right;
    int height;

    // Constructor
    public AVLNode(ProcessInfo process) {
        this.process = process;
        this.height = 1;
    }
}

