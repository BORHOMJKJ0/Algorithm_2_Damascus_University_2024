import java.util.*;
public class Node {
    protected String value ;
    protected ArrayList <Node> children ;
    protected String parent ;
    public Node (String value ){
        this.value = value ;
        this.children = new ArrayList<>();

    }
}
