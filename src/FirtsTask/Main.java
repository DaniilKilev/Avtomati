package FirtsTask;

import java.util.Scanner;

public class Main {
    private static String outLine = "S";
    private static final StringBuilder wayToTerminalChain = new StringBuilder();
    private static final MyTree tree = new MyTree('S');
    static boolean A = false;
    static boolean B = false;
    static boolean S = true;

    public static void main(String[] args) {
        System.out.println("""
                RULES:
                1. S->aSbS                           5. A->Ba\s
                2. S->aS                             6. A->b
                3. S->A                              7. B->b
                4. A->Sa                             8. B->aA
                """);
        tree.setCurrentNode(tree);
        tree.setHeadNode(tree);
        change(true);
        MyTree.printTree(tree.getHeadNode());
        System.out.println("\n" +
                "Linear bracket form: "+tree.getBuilder());
        System.out.println("Way to get terminal chain: " + wayToTerminalChain);
        System.out.println("Terminal chain: " + outLine);
        System.out.println("1 2 3 5 8 4 3 6 2 3 5 8 6");
    }

    public static void changeInner(String change, String changeTo, boolean right) {
        int posOfNotTerminal;
        if(!right)
            posOfNotTerminal = outLine.indexOf(change);
        else
            posOfNotTerminal = outLine.lastIndexOf(change);
        changeTerminal(posOfNotTerminal, changeTo);
        S = outLine.indexOf('S') != -1;
        A = outLine.indexOf('A') != -1;
        B = outLine.indexOf('B') != -1;
        System.out.println(outLine);
        System.out.print("You can use this rules: ");
        if (S)
            System.out.print("1 2 3 ");
        if (A)
            System.out.print("4 5 6 ");
        if (B)
            System.out.print("7 8 ");
        System.out.print("\n");
        tree.getNeededNode(change.toCharArray()[0], right);
        tree.addChilds(changeTo.toCharArray());
    }
    public static void change(boolean right) {
        Scanner scn = new Scanner(System.in);
        int numberOfRule;
        while ((A || B || S)) {
            System.out.print("Enter num of rule: ");
            numberOfRule = scn.nextInt();
            switch (numberOfRule) {
                case 1 -> {
                    changeInner("S", "aSbS",right);
                    wayToTerminalChain.append("1 ");
                }
                case 2 -> {
                    changeInner("S", "aS",right);
                    wayToTerminalChain.append("2 ");
                }
                case 3 -> {
                    changeInner("S", "A",right);
                    wayToTerminalChain.append("3 ");
                }
                case 4 -> {
                    changeInner("A", "Sa",right);
                    wayToTerminalChain.append("4 ");
                }
                case 5 -> {
                    changeInner("A", "Ba",right);
                    wayToTerminalChain.append("5 ");
                }
                case 6 -> {
                    changeInner("A", "b",right);
                    wayToTerminalChain.append("6 ");
                }
                case 7 -> {
                    changeInner("B", "b",right);
                    wayToTerminalChain.append("7 ");
                }
                case 8 -> {
                    changeInner("B", "aA",right);
                    wayToTerminalChain.append("8 ");
                }
                default -> System.out.println("Incorrect! Try again!");
            }
        }
    }

    public static void changeTerminal(int pos, String changeTo) {
        outLine = outLine.substring(0, pos) + changeTo + outLine.substring(pos + 1);// Возвращаем подстроку s, которая начиная с нулевой позиции переданной строки (0) и заканчивается позицией символа (pos), который мы хотим удалить, соединенную с другой подстрокой s, которая начинается со следующей позиции после позиции символа (pos + 1), который мы удаляем, и заканчивается последней позицией переданной строки.
    }
}