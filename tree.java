import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.Stack;

public class tree {

    private static class  Node{
        int data;
         ArrayList<Node> children = new ArrayList<>();

    }



    public static void display(Node root){
        String str = root.data + "-->" ;
        for(Node child: root.children){
            str =str+child.data +",";

        }
        str = str+";";
        System.out.println(str);

        for(Node child :root.children){
            display(child);
        }
     }

     public static int size(Node node){
        int s= 0;
         for(Node child : node.children){
            int childsize = size(child);
            s= s+ childsize;
         }
         s = s+1;
         return s;
     }

     public static int MaxElement(Node node){

        int max = node.data;
        for(Node child: node.children){
            int cmax = MaxElement(child);
            if(cmax>max){
                max= cmax;
            }

        }

        return max;

     }

     public static int heightInTermsOfEdges(Node node){
        int h = -1;
         for( Node child : node.children ){
            int ch = heightInTermsOfEdges(child);
            if ( ch > h ){
                h = ch;
            }
         }
         h += 1;



        return h ;
     }

     public static int hieghtInTermsOfNode(Node node){
         int h =0;
          for (Node child : node.children){
            int ch = hieghtInTermsOfNode(child);
          }
          h+= 1;
        return h;
     }
     public static void Traversal(Node root ){


        System.out.println( " node pre "+  root.data);
        for(Node child : root.children){
            System.out.println("edge pre " + root.data + "--" + child.data);

            Traversal(child);
            System.out.println("edge post" + root.data + "--" + child.data);

        }
        System.out.println("node post " + root.data);

     }

     public static void levelOrderTraversal(Node root ){

        Queue<Node> q = new ArrayDeque<>();

        q.add(root);

        while (q.size()>0) {
            Node t = q.remove();
            System.out.print(t.data + " ");
            for(Node child : t.children){
                q.add(child);
            }
            
        }
        System.out.println(";");

     }

     public static void levelOrderLineWise(Node root){
        Queue<Node> mainq = new ArrayDeque<>();
         Queue <Node> helperQ = new ArrayDeque<>();
         mainq.add(root);

         while (mainq.size()>0) {

        Node t = mainq.remove();
        System.out.println(t.data);
        for(Node child :t.children){
            helperQ.add(child);

        }
        if(mainq.size()==0){
            mainq = helperQ;
            helperQ = new ArrayDeque<>();

            System.out.println();
        }
            
         }
        
     }

     public static void LevelOrderLineWiseZigZag(Node root){

        Stack<Node> mainStack = new Stack<>();
         Stack <Node> helperStack = new Stack<>();
         mainStack.push(root);
         while (mainStack.size()>0) {
            int level =1;


            Node t = mainStack.pop();
            System.out.println(t.data);
            if(level%2==1){
                for(int i =0;i<t.children.size();i++){
                    helperStack.push(t.children.get(i));


                }

            }else if(level %2 ==0){
                for(int i = t.children.size()-1;i>=0; i--){
                    helperStack.push(t.children.get(i));
                }


            }

            if(mainStack.size()==0){
                mainStack= helperStack;
                helperStack = new Stack<>();
                System.out.println();
            }

             
         }
     }

     public static void LevelorderLineWise2(Node root){
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        while (q.size()>0) {
            Node t  = q.remove();
            System.out.println(t.data);

            for(Node child :t.children){
                if(child !=null){
                    q.add(child);

                }else if(child ==null){
                    if(q.size()>0){
                        q.add(null);
                        System.out.println();
                    }
                }
            }
            
        }

     }

     public static void LevelorderLineWise3(Node root){
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        while (q.size()>0) {

            int childInCurrLevel =q.size();
            for(int i =0;i<childInCurrLevel;i++){
                Node t = q.remove();
                System.out.println(t.data);
                for(Node child :t.children){
                    q.add(child);

                }
            }
            System.out.println();
            
        }

     }

     public static class Pair {
         private Node node;
         private int level;
         public  Pair(Node node, int level){
            this.node = node;
            this.level = level;
         }
         public Pair(){

         }
     }
     public  static void LevelorderLineWise4(Node root){
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(root, 1));
        int level =1;

        while (q.size()>0) {
            
            Pair p = q.remove();
            if((p.level > level)){
                level =p.level;
                System.out.println();
                
            }else{
                System.out.println(p.node.data+" " + " "+ p.level);
                for(Node child : p.node.children){
                    q.add(new Pair(child, p.level+1));

                }
            }

            
        }
     }

     public static void MirrorImage(Node root){
        for(Node child : root.children){
            MirrorImage(child);
        }
        Collections.reverse(root.children);

     }
     public static void removeLeafs(Node root){
        for(int  i= root.children.size()-1;i>=0;i--){

            Node child = root.children.get(i);
            if(child.children.size()==0){
                root.children.remove(child);
            }
        }

        for(Node child:root.children){
            removeLeafs(child);
        }
     }

     public static void LineariseGenricTree(Node node){

        for(Node child : node.children){
            LineariseGenricTree(child);
        }
         
        while (node.children.size()>1) {
            Node ln = node.children.remove(node.children.size()-1);
            Node sln = node.children.get(node.children.size()-1);
            Node tailofsln = getTail(sln);
            tailofsln.children.add(ln);
            
        }


         
     }
     private static  Node getTail(Node node){
        while (node.children.size()==1) {
            node = node.children.get(0);
            
        }




        return node;

     }


     public static Node LineariseGenricTree2(Node node){

        if(node.children.size()==0){
            return node ;

        }
        Node lkt = LineariseGenricTree2(node.children.get(node.children.size()-1));

        while (node.children.size()>1) {
            Node last = node.children.remove(node.children.size()-1);
            Node sl = node.children.get(node.children.size()-1);
            Node slKitail= LineariseGenricTree2(sl);
            slKitail.children.add(last);

            
        }

        return lkt;
     }

     public static boolean FindInGenricTree(Node node, int data){
        if(node.data == data){
            return true;
        }
        for(Node child:node.children){
             boolean getvalue = FindInGenricTree(child, data);
             if(getvalue){
                return true;
             }
        }

    return false;
     }



    public static void main(String[] args) {
        int arr []={10,20,50,-1,60,-1,-1,30, 70,-1,80,110,-1,120,-1,-1,90,-1,-1,40,400,-1,-1,-1};
        Stack<Node> st= new Stack<>();
        Node root = null;

         for(int i =0;i<arr.length;i++)
         {

            if(arr[i]==-1){
                st.pop();
            }else{
                Node t = new Node();
                t.data = arr[i];
                if(st.size()>0){
                    st.peek().children.add(t);
                 
                }else{
                    root = t;
                }
                st.push(t);



            }
            
         }

         display(root);
         System.out.println(MaxElement(root));
         Traversal(root);

         levelOrderTraversal(root);
         levelOrderLineWise(root);
         LevelOrderLineWiseZigZag(root);

         System.out.println("level LINe wise");
         LevelorderLineWise2(root);
         LevelorderLineWise3(root);

         System.out.println("level4");
         LevelorderLineWise4(root);



    }
    
}
