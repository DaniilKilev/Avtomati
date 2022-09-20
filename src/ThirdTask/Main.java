package ThirdTask;


import FirtsTask.MyTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String outLine = "S";
    private static final StringBuilder wayToTerminalChain = new StringBuilder();
    private static  MyTree tree = new MyTree('S');
    private static String cause;

    public static void main(String[] args) {
        System.out.println("\nFor left out");
        outChain(false);
        System.out.println("\nFor right out");
        outChain(true);
    }
    public static void outChain(boolean right){
        boolean canOutChain = change(getRules(), right);
        System.out.println("Can out chain?");
        if (canOutChain) {
            System.out.println("\tYes");
            isTerminal();
        } else {
            System.out.println("\tNo, " + cause);
        }
        System.out.println("Chain: " + outLine);
        outLine = "S";
        tree = new MyTree('S');
    }

    public static void isTerminal(){
        System.out.println("It's terminal word?");
        if(outLine.contains("S")||outLine.contains("A")||outLine.contains("B")){
            System.out.println("\tNo, word is not terminal");
            System.out.println(outLine);
        } else {
            System.out.println("\tYes, it's terminal word");
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

    public static boolean change(List<Integer> rules, boolean right) {
        for (int rule : rules) {
            switch (rule) {
                case 1 -> {
                    if (!outLine.contains("S")) {
                        cause = "Word haven't 'S' symbol";
                        return false;
                    }
                    changeInner("S", "aSbS", right);
                    wayToTerminalChain.append("1 ");
                }
                case 2 -> {
                    if (!outLine.contains("S")) {
                        cause = "Word haven't 'S' symbol";
                        return false;
                    }
                    changeInner("S", "aS", right);
                    wayToTerminalChain.append("2 ");
                }
                case 3 -> {
                    if (!outLine.contains("S")) {
                        cause = "Word haven't 'S' symbol";
                        return false;
                    }
                    changeInner("S", "A", right);
                    wayToTerminalChain.append("3 ");
                }
                case 4 -> {
                    if (!outLine.contains("A")) {
                        cause = "Word haven't 'A' symbol";
                        return false;
                    }
                    changeInner("A", "Sa", right);
                    wayToTerminalChain.append("4 ");
                }
                case 5 -> {
                    if (!outLine.contains("A")) {
                        cause = "Word haven't 'A' symbol";
                        return false;
                    }
                    changeInner("A", "Ba", right);
                    wayToTerminalChain.append("5 ");
                }
                case 6 -> {
                    if (!outLine.contains("A")) {
                        cause = "Word haven't 'A' symbol";
                        return false;
                    }
                    changeInner("A", "b", right);
                    wayToTerminalChain.append("6 ");
                }
                case 7 -> {
                    if (!outLine.contains("B")) {
                        cause = "Word haven't B symbol";
                        return false;
                    }
                    changeInner("B", "b", right);
                    wayToTerminalChain.append("7 ");
                }
                case 8 -> {
                    if (!outLine.contains("B")) {
                        cause = "Word haven't B symbol";
                        return false;
                    }
                    changeInner("B", "aA", right);
                    wayToTerminalChain.append("8 ");
                }
                default -> System.out.println("Incorrect! Try again!");
            }
        }
        return true;
    }

    public static void changeTerminal(int pos, String changeTo) {
        outLine = outLine.substring(0, pos) + changeTo + outLine.substring(pos + 1);// Возвращаем подстроку s, которая начиная с нулевой позиции переданной строки (0) и заканчивается позицией символа (pos), который мы хотим удалить, соединенную с другой подстрокой s, которая начинается со следующей позиции после позиции символа (pos + 1), который мы удаляем, и заканчивается последней позицией переданной строки.
    }

    public static List<Integer> getRules() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> rules = new ArrayList<>();
        System.out.println("Enter number of rules in format: \"(1 2 3 4...)\"");
        String s = scanner.nextLine();
        String[] numbersOfRules = s.split(" ");
        for(String rule: numbersOfRules){
            rules.add(Integer.parseInt(rule));
        }
        return rules;
    }
}