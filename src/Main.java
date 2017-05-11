/**
 * Created by Mostafa on 5/11/2017.
 */
public class Main {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("cat");
        trie.add("catastrophy");
        trie.add("catastrophic");
        System.out.println(trie.getPrefixMatches("cata"));
    }
}
