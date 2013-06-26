/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lifestyle.DAO;

import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Leon
 */
public class Test {

    public static void main(String args[]) {
        DefaultMutableTreeNode rootNode =
                new DefaultMutableTreeNode();
        rootNode.setUserObject("ROOT");
        DefaultMutableTreeNode apolloNode =
                new DefaultMutableTreeNode("Apollo");
        apolloNode.setUserObject("Apollo");
        rootNode.add(apolloNode);

        DefaultMutableTreeNode skylabNode =
                new DefaultMutableTreeNode("Skylab");
        skylabNode.setUserObject("Skylab");
        rootNode.add(skylabNode);

        DefaultMutableTreeNode thirdNode =
                new DefaultMutableTreeNode("thirdNode");
        thirdNode.setUserObject("thirdNode");
        skylabNode.add(thirdNode);
        Enumeration e = rootNode.children();
        while (e.hasMoreElements()) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
            System.out.println(node);
            if (node.isLeaf()) {
                System.out.println("is leaf");
            } else {
                System.out.println("not leaf");
            }
        }
//        System.out.println(rootNode.getNextNode());
//        System.out.println();
    }
}
