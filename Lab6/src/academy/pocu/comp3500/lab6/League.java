package academy.pocu.comp3500.lab6;

import academy.pocu.comp3500.lab6.leagueofpocu.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class League {
    private BinarySearchTree binarySearchTree;

    public League() {
        this.binarySearchTree = null;
    }

    public League(Player[] players, boolean isSorted) {
        if (!isSorted) {
            Sort.quickSort(players);
        }
        List<Node> nodes = new ArrayList<Node>();
        Player previous = players.length > 0 ? players[0] : null;
        if (previous != null) {
            nodes.add(new Node(previous));
        }
        Player current;
        int i = 1;
        while (i < players.length) {
            current = players[i];
            if (current.getId() != previous.getId() && current.getRating() == previous.getRating()) {
                nodes.get(nodes.size() - 1).getPlayers().put(current.getId(), current);
            } else {
                nodes.add(new Node(current));
            }
            previous = current;
            i++;
        }

        Node root = this.sortedArrayToBinarySearchTree(nodes);
        this.binarySearchTree = new BinarySearchTree(root);
    }

    public Player findMatchOrNull(final Player player) {
        if (this.binarySearchTree == null) {
            return null;
        }

        Node playerNode = this.binarySearchTree.searchById(player.getId());
        if (playerNode != null) {
            for (Entry<Integer, Player> entrySet : playerNode.getPlayers().entrySet()) {
                if (entrySet.getKey() != player.getId()) {
                    return entrySet.getValue();
                }
            }
        }

        Node parent = playerNode.getParentOrNull();
        Node left = playerNode.getLeftChildOrNull();
        Node right = playerNode.getRightChildOrNull();

        // 가장 등급이 가까운 선수
        ArrayList<Node> candidates = new ArrayList<Node>();
        if (parent != null) {
            candidates.add(parent);
        }
        if (left != null) {
            candidates.add(left);
        }
        if (right != null) {
            candidates.add(right);
        }

        if (candidates.size() != 0) {
            int index = 0;
            int minDiff = Math.abs(candidates.get(0).getRating() - player.getRating());
            for (int i = 1; i < candidates.size(); i++) {
                int candiateDiff = Math.abs(candidates.get(i).getRating() - player.getRating());
                if (candiateDiff < minDiff) {
                    index = i;
                    minDiff = candiateDiff;
                } else if (candiateDiff == minDiff && candidates.get(i).getRating() > player.getRating()) {
                    index = i;
                    minDiff = candiateDiff;
                }
            }

            for (Entry<Integer, Player> entrySet : candidates.get(index).getPlayers().entrySet()) {
                if (entrySet.getKey() != player.getId()) {
                    return entrySet.getValue();
                }
            }
        }

        return null;
    }

    public Player[] getTop(final int count) {
        if (this.binarySearchTree == null) {
            return new Player[0];
        }

        List<Node> list = this.binarySearchTree.toArrayByRataingDesc();

        ArrayList<Player> players = new ArrayList<Player>();
        int i = 0;
        Node temp = list.get(0);
        while (temp != null && i < count) {
            for (Entry<Integer, Player> entrySet : temp.getPlayers().entrySet()) {
                if (i < count) {
                    players.add(entrySet.getValue());
                    i++;
                } else {
                    break;
                }
            }
            temp = list.get(i);
        }
        Player[] p = new Player[players.size()];

        return players.toArray(p);
    }

    public Player[] getBottom(final int count) {
        if (this.binarySearchTree == null) {
            return new Player[0];
        }

        List<Node> list = this.binarySearchTree.toArrayByRataingAsc();

        ArrayList<Player> players = new ArrayList<Player>();
        int i = 0;
        Node temp = list.get(0);
        while (temp != null && i < count) {
            for (Entry<Integer, Player> entrySet : temp.getPlayers().entrySet()) {
                if (i < count) {
                    players.add(entrySet.getValue());
                    i++;
                } else {
                    break;
                }
            }
            temp = list.get(i);
        }
        Player[] p = new Player[players.size()];

        return players.toArray(p);
    }

    public boolean join(final Player player) {
        Node node = new Node(player);

        if (this.binarySearchTree == null) {
            this.binarySearchTree = new BinarySearchTree(node);
            return true;
        }

        if (this.binarySearchTree.searchById(player.getId()) != null) {
            return false;
        }

        this.binarySearchTree.insert(new Node(player));
        return true;
    }

    public boolean leave(final Player player) {
        if (this.binarySearchTree == null) {
            return false;
        }

        Node deleteTarget = this.binarySearchTree.searchById(player.getId());
        if (deleteTarget == null) {
            return false;
        }
        this.binarySearchTree.delete(deleteTarget);
        return true;
    }

    private Node sortedArrayToBinarySearchTree(List<Node> nodes) {
        if (nodes == null || nodes.size() == 0) {
            return null;
        }

        int midIndex = nodes.size() / 2;
        Node newNode = nodes.get(midIndex);

        List<Node> leftList = nodes.subList(0, midIndex);
        Node leftNode = sortedArrayToBinarySearchTree(leftList);
        if (leftNode != null) {
            newNode.setLeftChild(leftNode);
            leftNode.setParent(newNode);
        }

        List<Node> rightList = nodes.subList(midIndex + 1, nodes.size());
        Node rightNode = sortedArrayToBinarySearchTree(rightList);
        if (rightNode != null) {
            newNode.setRightChild(rightNode);
            rightNode.setParent(newNode);
        }

        return newNode;
    }

}