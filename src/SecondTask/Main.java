package SecondTask;


import FirtsTask.MyTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String outLine = "S";
    private static final StringBuilder wayToTerminalChain = new StringBuilder();
    private static final MyTree tree = new MyTree('S');
    public static void main(String[] args) {
        tree.setCurrentNode(tree);
        tree.setHeadNode(tree);

        change(getRules(), false);
        System.out.println("Terminal chain: " + outLine);
        System.out.println("Rules: " + wayToTerminalChain);
        if(outLine.contains("S")||outLine.contains("A")||outLine.contains("B")){
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }

    public static void changeInner(String change, String changeTo, boolean right) {
        int posOfNotTerminal;
        if (!right)
            posOfNotTerminal = outLine.indexOf(change);
        else
            posOfNotTerminal = outLine.lastIndexOf(change);
        changeTerminal(posOfNotTerminal, changeTo);
        tree.getNeededNode(change.toCharArray()[0], right);
        tree.addChilds(changeTo.toCharArray());
    }


    public static void change(List<Integer> rules, boolean right) {
        for (int rule : rules) {
            switch (rule) {
                case 1 -> {
                    changeInner("S", "aSbS", right);
                    wayToTerminalChain.append("1 ");
                }
                case 2 -> {
                    changeInner("S", "aS", right);
                    wayToTerminalChain.append("2 ");
                }
                case 3 -> {
                    changeInner("S", "A", right);
                    wayToTerminalChain.append("3 ");
                }
                case 4 -> {
                    changeInner("A", "Sa", right);
                    wayToTerminalChain.append("4 ");
                }
                case 5 -> {
                    changeInner("A", "Ba", right);
                    wayToTerminalChain.append("5 ");
                }
                case 6 -> {
                    changeInner("A", "b", right);
                    wayToTerminalChain.append("6 ");
                }
                case 7 -> {
                    changeInner("B", "b", right);
                    wayToTerminalChain.append("7 ");
                }
                case 8 -> {
                    changeInner("B", "aA", right);
                    wayToTerminalChain.append("8 ");
                }
                default -> System.out.println("Incorrect! Try again!");
            }
        }
    }
    public static void changeTerminal(int pos, String changeTo) {
        outLine = outLine.substring(0, pos) + changeTo + outLine.substring(pos + 1);
    }
    public static List<Integer> getRules(){
        Scanner scanner = new Scanner(System.in);
        List<Integer> rules = new ArrayList<>();
        Integer a = 1;
        while(a !=0) {
            System.out.print("Enter num of rule: ");
            a = scanner.nextInt();
            if(a==0){
                return rules;
            }
            rules.add(a);
        }
        return rules;
    }
}