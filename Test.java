

public class Test {
    public static void main(String[] args) {
        AVL avl = new AVL();
        avl.insert(30);
        avl.insert(40);
        avl.insert(10);
        avl.insert(5);
        avl.insert(20);

        String de = avl.binaryTreeDeserialize(avl.root);
        System.out.println(de);
        avl.delete(40);
         
        de = avl.binaryTreeDeserialize(avl.root);
        System.out.println(de);

    }
}