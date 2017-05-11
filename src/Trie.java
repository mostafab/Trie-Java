import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mostafa on 5/10/2017.
 */
public class Trie {

    private TrieNode head;

    public Trie() {
        head = new TrieNode(null, false);
    }

    public Trie(Collection<String> words) {
        words.forEach(this::add);
    }

    public void add(String word) {
        String copy = word.toLowerCase().trim();
        TrieNodeIndexPair match = findEndOfMatch(copy);
        if (match.index < copy.length()) {
            match.node.children.add(createNodeChain(copy, match.index));
        } else {
            match.node.isEndOfWord = true;
        }
    }

    public List<String> getPrefixMatches(String prefix) {
        List<String> words = new ArrayList<>();
        String copy = prefix.toLowerCase().trim();
        TrieNodeIndexPair match = findEndOfMatch(copy);
        if (match.index < copy.length()) {
            return words;
        }
        getAllWordsRecursive(match.node, copy, words);
        return words;
    }

    public boolean contains(String word) {
        TrieNodeIndexPair match = findEndOfMatch(word);
        if (match.index == word.length()) {
            return match.node.isEndOfWord;
        }
        return false;
    }

    private void getAllWordsRecursive(TrieNode node, String currentWord, List<String> words) {
        if (node.isEndOfWord) {
            words.add(currentWord);
        }
        for (TrieNode child : node.children) {
            String str = Character.toString(child.letter);
            getAllWordsRecursive(child, currentWord + str, words);
        }
    }

    private TrieNode createNodeChain(String word, int index) {
        TrieNode node = new TrieNode(word.charAt(index), index == word.length() - 1);
        if (index < word.length() - 1) {
            node.children.add(createNodeChain(word, index + 1));
        }
        return node;
    }

    private TrieNodeIndexPair findEndOfMatch(String word) {
        TrieNode current = head;
        int index = 0;
        while (index < word.length() && current.findChildNodeByLetter(word.charAt(index)) != null) {
            current = current.findChildNodeByLetter(word.charAt(index));
            index++;
        }
        return new TrieNodeIndexPair(current, index);
    }

    private class TrieNode {

        private Character letter;
        private List<TrieNode> children;
        private boolean isEndOfWord;

        private TrieNode(Character letter, boolean isEndOfWord) {
            this.letter = letter;
            this.isEndOfWord = isEndOfWord;
            children = new ArrayList<>();
        }

        private TrieNode(Character letter) {
            this.letter = letter;
            this.isEndOfWord = false;
            children = new ArrayList<>();
        }

        public TrieNode findChildNodeByLetter(Character letter) {
            for (TrieNode node : this.children) {
                if (node.letter.equals(letter)) {
                    return node;
                }
            }
            return null;
        }

        public String toString() {
            return Character.toString(letter);
        }
    }

    private class TrieNodeIndexPair {

        private TrieNode node;
        private int index;

        private TrieNodeIndexPair(TrieNode node, int index) {
            this.node = node;
            this.index = index;
        }

        @Override
        public String toString() {
            return "(" + node.toString() + ", " + index + ")";
        }
    }
}
