/**
 * Created by Mostafa on 5/11/2017.
 */
public class Main {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("mom");
        trie.add("dad");
        System.out.println(trie.contains("mom"));
    }
}
