package FirtsTask;

import java.util.ArrayList;
import java.util.Collections;

public class MyTree {
    private final char value;
    private static final StringBuilder builder = new StringBuilder();
    private MyTree currentNode = this;
    private static MyTree headNode;
    private MyTree parentNode;
    private boolean isChecked;
    private final ArrayList<MyTree> childs  = new ArrayList<>();

    public MyTree(char value) {
        this.value = value;
    }
    public void setHeadNode(MyTree myTree){
        headNode = myTree;
    }

    public void setCurrentNode(MyTree currentNode){
        this.currentNode = currentNode;
    }


    public char getValue() {
        return value;
    }

    public void addChilds(char[] valOfChilds){
        for(char val: valOfChilds){
            currentNode.childs.add(new MyTree(val));
        }
    }
    public void getNeededNode(char value, boolean right){
        if(currentNode.childs.isEmpty()){
            return;
        }
        if(right)
            Collections.reverse(currentNode.childs);
        for(MyTree node: currentNode.childs){
            if(!node.isChecked){
                if (node.getValue() == value){
                    node.isChecked = true;
                    node.setParentNode(currentNode);
                    setCurrentNode(node);
                    return;
                }
            }
        }
        if(right)
            Collections.reverse(currentNode.childs);
        currentNode = currentNode.parentNode;
        getNeededNode(value, right);
    }
    public void setParentNode(MyTree parentNode){
        this.parentNode = parentNode;
    }

    public ArrayList<MyTree> getChilds() {
        return childs;
    }

    @Override
    public String toString() {
        return ""+value;
    }
    public static void printTree(MyTree head){
        builder.append(head.getValue()).append("(");
        for(MyTree innerNode: head.getChilds()){
            if(!innerNode.getChilds().isEmpty())
                printTree(innerNode);
            else
                builder.append(innerNode.getValue());
        }
        builder.append(")");
    }

    public StringBuilder getBuilder() {
        return builder;
    }

    public MyTree getHeadNode() {
        return headNode;
    }
}
