class Node {
    int data;
    int height; 
    Node left;
    Node right;
    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 0;
    }
    
}

public class AVL {

    Node root;
    public AVL() {
        this.root = null;
    }

    public Node insert(int data) {
        this.root = insertUtil(this.root,data);
        return root;
    }
     
    public Node insertUtil(Node root, int data) {
        if(root == null) {
            Node temp = new Node(data);
            temp.height = 1 ; 
            return temp ;

        }
        if(root.data > data) {
            root.left = insertUtil(root.left,data);
        }
        else {
            root.right = insertUtil(root.right,data);
        }
        root.height = height(root) ;
        int balance = getBalance(root);

        if(balance == 2 && getBalance(root.left) == 1) {
            return LL(root);
        }
        else if(balance == 2 && getBalance(root.left) == -1) {
            return LR(root);
        }
        else if(balance == -2 && getBalance(root.right) == -1) {
            return RR(root);
        }
        else if(balance == -2 && getBalance(root.right) == 1) {
            return RL(root);
        }


        return root;
    }
    public int height(Node root) {
        if(root == null) {
            return 0;
        }

        int hl = 0, hr = 0 ;
        if(root.left != null) {
            hl = root.left.height;
        }
        if(root.right != null) {
            hr = root.right.height;
        }

        return Math.max(hl, hr) + 1; 
    }
    public int getBalance(Node root) {
        if(root == null) {
            return 0;
        }
        int hl = 0, hr = 0 ;
        if(root.left != null) {
            hl = root.left.height;
        }
        if(root.right != null) {
            hr = root.right.height;
        }

        return hl-hr ;

    }
    public Node LL(Node root) {
        Node rl = root.left;
        Node rlr = rl.right;
        
        rl.right = root;
        root.left = rlr;

        root.height = height(root);
        rl.height = height(rl);

        if(root == this.root) {
            this.root = rl;
        }

        return rl;
    }
    public Node LR(Node root) {
        Node rl = root.left;
        Node rlr = rl.right;

        rl.right = rlr.left;
        root.left = rlr.right;

        rlr.left = rl ;
        rlr.right = root;

        root.height = height(root);
        rl.height = height(rl);
        rlr.height = height(rlr);

        if(root == this.root) {
            this.root = rlr;
        }
        return rlr;

    }
    public Node RL(Node root) {

        Node rr = root.right;
        Node rrl = rr.left; 

        root.right = rrl.left;
        rr.left = rrl.right; 

        rrl.left = root;
        rrl.right = rr;

        root.height = height(root);
        rr.height = height(rr);
        rrl.height = height(rrl);

        if(root == this.root) {
            this.root = rrl;
        }

        return rrl;
    }
    public Node RR(Node root) {
        Node rr = root.right;
        Node rrl = rr.left; 
        
        root.right = rrl;
        rr.left = root;

        rr.height = height(rr) ; 
        root.height = height(root) ;

        if(root == this.root) {
            this.root = rr;
        }
        return rr;
    }

    public String binaryTreeDeserialize(Node root){
        if(root == null) {
            return "null ";
        }
        String str = "";
        str += root.data + " ";
        str += binaryTreeDeserialize(root.left);
        str += binaryTreeDeserialize(root.right);
        return str;
    }
}

class Test {
    public static void main(String[] args) {
        AVL avl = new AVL();
        avl.insert(50);
        avl.insert(70);
        avl.insert(60);
         
        String de = avl.binaryTreeDeserialize(avl.root) ;
        System.out.println(de);

        
    }
}