package academy.pocu.comp3500.lab6.leagueofpocu;

import java.util.ArrayList;

public class League {
    private BinarySearchTree binarySearchTree;

    public League() {
        this.binarySearchTree = null;
    }

    public League(Player[] players, boolean isSorted) {
        this.binarySearchTree = null;
    }

    public Player findMatchOrNull(final Player player) {
        if (this.binarySearchTree == null) {
            return null;
        }

        Node playerNode = this.binarySearchTree.searchById(player.getId());
        Player parentPlayer = playerNode.getParent() != null ? playerNode.getParent().getPlayer() : null;
        Player leftPlayer = playerNode.getLeftChild() != null ? playerNode.getLeftChild().getPlayer() : null;
        Player rightPlayer = playerNode.getRightChild() != null ? playerNode.getRightChild().getPlayer() : null;

        // 등급이 같은 선수
        if (parentPlayer != null && parentPlayer.getRating() == playerNode.getPlayer().getRating()) {
            return parentPlayer;
        }
        if (rightPlayer != null && rightPlayer.getRating() == playerNode.getPlayer().getRating()) {
            return rightPlayer;
        }

        // 가장 등급이 가까운 선수
        ArrayList<Player> candidates = new ArrayList<Player>();
        if (parentPlayer != null) {
            candidates.add(parentPlayer);
        }
        if (leftPlayer != null) {
            candidates.add(leftPlayer);
        }
        if (rightPlayer != null) {
            candidates.add(rightPlayer);
        }

        if (candidates.size() == 0) {
            return null;
        } else if (candidates.size() == 1) {
            return candidates.get(0);
        } else {
            return candidates.get(candidates.size() - 1);
        }
    }

    public Player[] getTop(final int count) {
        if (this.binarySearchTree == null) {
            return null;
        }

        ArrayList<Player> list = this.binarySearchTree.toArrayByRataingAsc();

        int from = 0;
        int to = count > list.size() ? list.size() - 1 : count - 1;

        return (Player[]) list.subList(from, to).toArray();
    }

    public Player[] getBottom(final int count) {
        if (this.binarySearchTree == null) {
            return null;
        }

        ArrayList<Player> list = this.binarySearchTree.toArrayByRataingDesc();

        int from = 0;
        int to = count > list.size() ? list.size() - 1 : count - 1;

        return (Player[]) list.subList(from, to).toArray();
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
}