package leetcode.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * PathSum
 */
public class PathSum {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {

        if (root == null) {
            return false;
        }
        return sumPaths(root, sum, 0);

    }

    public boolean sumPaths(TreeNode root, int sum, int currentSum) {
        currentSum += root.val;
        if (root.left == null && root.right == null) {
            if (currentSum == sum) {
                return true;
            } else {
                return false;
            }
        }

        boolean solutionLeft = false;
        boolean solutionRight = false;
        if (root.left != null) {
            solutionLeft |= sumPaths(root.left, sum, currentSum);
        }
        if (root.right != null) {
            solutionRight |= sumPaths(root.right, sum, currentSum);
        }

        return solutionLeft | solutionRight;
    }

    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = pathSum.new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = pathSum.new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = pathSum.new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    static PathSum pathSum = new PathSum();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);
            line = in.readLine();
            int sum = Integer.parseInt(line);

            boolean ret = new PathSum().hasPathSum(root, sum);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }
}