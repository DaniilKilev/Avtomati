package ThirdTask;


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

        if(change(getRules(), false)){
            System.out.println("Yes");
        } else {
            System.out.println("No");
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
                    if (!outLine.contains("S"))
                        return false;
                    changeInner("S", "aSbS", right);
                    wayToTerminalChain.append("1 ");
                }
                case 2 -> {
                    if (!outLine.contains("S"))
                        return false;
                    changeInner("S", "aS", right);
                    wayToTerminalChain.append("2 ");
                }
                case 3 -> {
                    if (!outLine.contains("S"))
                        return false;
                    changeInner("S", "A", right);
                    wayToTerminalChain.append("3 ");
                }
                case 4 -> {
                    if (!outLine.contains("A"))
                        return false;
                    changeInner("A", "Sa", right);
                    wayToTerminalChain.append("4 ");
                }
                case 5 -> {
                    if (!outLine.contains("A"))
                        return false;
                    changeInner("A", "Ba", right);
                    wayToTerminalChain.append("5 ");
                }
                case 6 -> {
                    if (!outLine.contains("A"))
                        return false;
                    changeInner("A", "b", right);
                    wayToTerminalChain.append("6 ");
                }
                case 7 -> {
                    if (!outLine.contains("B"))
                        return false;
                    changeInner("B", "b", right);
                    wayToTerminalChain.append("7 ");
                }
                case 8 -> {
                    if (!outLine.contains("B"))
                        return false;
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
        int a = 1;
        while (a != 0) {
            System.out.print("Enter num of rule: ");
            a = scanner.nextInt();
            if (a == 0) {
                return rules;
            }
            rules.add(a);
        }
        return rules;
    }
}