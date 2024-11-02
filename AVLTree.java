/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 10/5/2024

Author: Dylan Theis
Date: Fall 2024
Class: CSC420
Project: ProcessScheduler
Description: ProcessInfo class for the ProcessScheduler. Class holding the process's information.
*/

import java.util.LinkedList;
import java.util.Queue;

// AVLTree class
public class AVLTree {
    // Tree has root variable
    private AVLNode root;

    // Insert method
    public AVLNode insert(AVLNode node, ProcessInfo process) {
        // If node is null create node with process
        if (node == null) {
            return new AVLNode(process);
        }

        // If process priority is less than root node
        if (process.getProcessPriority() < node.process.getProcessPriority()) {
            // Insert in left subtree
            node.left = insert(node.left, process);
            // If process priority is greater than root node priority
        } else if (process.getProcessPriority() > node.process.getProcessPriority()) {
            // Insert process in right subtree
            node.right = insert(node.right, process);
            // Else return node
        } else {
            return node;
        }

        // Update node height and get balance
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        // Left left
        // If balance > 1 and process is less than left priority perform right rotate
        if (balance > 1 && process.getProcessPriority() < node.left.process.getProcessPriority()) {
            return rightRotate(node);
        }

        // Right right
        // If balance < -1 and process is greater than right priority perform left rotate
        if (balance < -1 && process.getProcessPriority() > node.right.process.getProcessPriority()) {
            return leftRotate(node);
        }

        // Left right
        // if balance > 1 and priority greater than left priority
        if (balance > 1 && process.getProcessPriority() > node.left.process.getProcessPriority()) {
            // Perform left rotate on left node and assign it left node
            node.left = leftRotate(node.left);
            // Then perform right rotate on node
            return rightRotate(node);
        }

        // Right left
        // If balance < -1 and priority less than right priority
        if (balance < -1 && process.getProcessPriority() < node.right.process.getProcessPriority()) {
            // Perform right rotate on right node and assign node as right node
            node.right = rightRotate(node.right);
            // Then perform left rotate on node
            return leftRotate(node);
        }

        return node;
    }

    // Right rotate
    private AVLNode rightRotate(AVLNode y) {
        // Assign left node and right node
        AVLNode x = y.left;
        AVLNode x2 = x.right;

        // Then swap the nodes
        x.right = y;
        y.left = x2;

        // Increase the nodes height
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Left rotate
    private AVLNode leftRotate(AVLNode x) {
        // Assign left node and right node
        AVLNode y = x.right;
        AVLNode x2 = y.left;

        // Swap the nodes
        y.left = x;
        x.right = x2;

        // Adjust the heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Height method
    private int height(AVLNode node) {
        // If node is null return height zero
        if (node == null) {
            return 0;
        }
        // Return node height
        return node.height;
    }

    // Get balance of tree
    private int getBalance(AVLNode node) {
        // If node is null return 0
        if (node == null) {
            return 0;
        }
        // Else balance factor is left height - right height
        return height(node.left) - height(node.right);
    }

    // Level order traversal method
    public void levelOrderTraversal(AVLNode root) {
        // If root is null return empty
        if (root == null) {
            return;
        }

        // Create queue using linked list
        Queue<AVLNode> queue = new LinkedList<>();
        // Add root the queue
        queue.add(root);

        // Start at level 0(root node)
        int level = 0;
        // Until queue is empty
        while (!queue.isEmpty()) {
            // Print nodes on level
            System.out.println("Level " + level + " >");
            int nodeCount = queue.size();
            // While there is a node on the level
            while (nodeCount > 0) {
                // Get current node(process) from the queue
                AVLNode currentNode = queue.poll();
                ProcessInfo process = currentNode.process;
                // Print Process info
                System.out.println(String.format("Process Name: %-7s Process Id: %-5d Process Priority: %-5d " +
                        "Process Remaining Runtime: %-5d", process.getProcessName(), process.getProcessId(),
                        process.getProcessPriority(), process.getRemainingTime()));

                // Add node on the left
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                // Add node on the right
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
                // Remove process from nodeCount
                nodeCount--;
            }
            // Increase level
            level++;
        }
    }

    // Get root node
    public AVLNode getRoot() {
        return root;
    }

    // set root node
    public void setRoot(AVLNode root) {
        this.root = root;
    }
}

