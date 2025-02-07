import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.Stack;

import javax.print.DocFlavor.STRING;

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

     public static ArrayList<Integer> NodetoRootPath(Node node, int data){

        if(node.data== data){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(data);
            return list;
        }

        for(Node child :node.children){
            ArrayList<Integer> ctnode = NodetoRootPath(child, data);
            if(ctnode.size()>0){
                ctnode.add(node.data);
                return ctnode;

            }

        }
        return new ArrayList<>();
     }

      public static int LowestCommonAncesstor(Node node, int d1, int d2){

        ArrayList<Integer>  p1 = NodetoRootPath(node, d1) ;
        ArrayList <Integer> p2 = NodetoRootPath(node, d2);
        int i = p1.size()-1;
        int j = p2.size()-1;
        while (i>=0 && j>= 0 && p1.get(i)==p2.get(j)) {
            i--;
            j--;

            
        }
        i++;
        j++;

        return p1.get(i);

      }

      public static int DistanceBtwNodes(Node node,int  d1, int d2 ){

        ArrayList <Integer> p1 = NodetoRootPath(node,d1);
        ArrayList<Integer> p2 =  NodetoRootPath(node, d2);
        int i = p1.size() -1;
         int j = p2.size()-1;
        while (i>=0 && j>=0 && p1.get(i)== p2.get(j)) {
            i--;
            j--;

            
        }
        i++;
        j++;

        return i+j;
      }

      public static boolean isSimilar(Node root1, Node root2) {
        // Base case: if both roots are null, they are similar
        if (root1 == null && root2 == null) {
            return true;
        }
        // If one is null and the other is not, they are not similar
        if (root1 == null || root2 == null) {
            return false;
        }
        // If the number of children is different, they are not similar
        if (root1.children.size() != root2.children.size()) {
            return false;
        }
        // Recursively check each pair of children
        for (int i = 0; i < root1.children.size(); i++) {
            Node c1 = root1.children.get(i);
            Node c2 = root2.children.get(i);
            if (!isSimilar(c1, c2)) {
                return false;
            }
        }
        // If all checks pass, the trees are similar
        return true;
    }


    public static boolean areMirrorShape(Node root1, Node root2){

        if(root1 == null && root2==null){
            return true;
        }

        if(root1.children.size()!= root2.children.size()){
            return false;
        }
        for(int i =0;i<root1.children.size();i++){
            int j = root1.children.size()-1-i;
            Node lofr1= root1.children.get(i);
            Node rofr2 = root2.children.get(j);
            if(!areMirrorShape(lofr1, rofr2)){
                return false;
            }

        }


        return true;
    }

    public static boolean isSymmetric(Node root){
        return areMirrorShape(root, root);
    }


    static int min ;
     static int max;
      static int height;
      static int size;

      public static void Multisolver(Node node , int depth){

        size ++;
        max = Math.max(max,node.data );
        min = Math.min(min, node.data);
        height = Math.max(height, depth);


        for(Node child:node.children){
            Multisolver(child, depth+1);
        }
      }


      static  Node predecessor;
       static Node  sucesstor;
       static int state;
        

       public static void preAndSuccess(Node node , int data){
        if(state ==0 ){
            if(node.data== data){
                state =1;
            }
            else{
                predecessor = node;
            }
        
        }
         else if(state ==1){
            sucesstor = node;
            state= 2;

        }


        for(Node child: node.children){
            preAndSuccess(child, data);
        }


       }

       static int ceil= Integer.MAX_VALUE;
       static int floor= Integer.MIN_VALUE;
       public static void ceilAndFloor(Node node, int data){
        if(node.data< data){
            if(node.data> floor){
                floor = node.data;

            }
        }
        else if(node.data > data){
            if(node.data<ceil){
                ceil = node.data;

            }
        }

        for(Node child:node.children){
            ceilAndFloor(child, data);
        }
       }

    static int msn =0;
    static int ms =Integer.MIN_VALUE;
    public static int  MaxsumSubtree(Node node){

        int sum =0;
        for(Node child:node.children){
            int childsum = MaxsumSubtree(child);
            sum += childsum;
        }
        sum += node.data;

        if(sum> ms){
            ms = sum;
            msn =node.data;
        }


        return sum;
    }
    static int dia =0;
     static int CalHeightAndGiveDia(Node node){
        int dh = -1;
        int sdh =-1;
        for(Node child: node.children){
            int ch = CalHeightAndGiveDia(child);

            if(ch>dh){
                dh = ch;

            }else if(ch>sdh){
                sdh = ch;

            }
        }
    int factor = dh +sdh+2;
    if(factor> dia){
        dia = factor;

      } 
      dh++;
        return dh;
     }


     public static class PPair{
        Node node;
        int state;
        PPair(Node node, int state){
            this.node = node;
             this.state =state;
        }
     }


     public static String iterativePrePost(Node node ){
        String pre ="";
        String post= "";

        Stack<PPair> st = new Stack<>();
        st.push(new PPair(node,-1 ));
        while (st.size()>0) {

            PPair top = st.peek();

            if(top.state==-1){
                pre += top.node.data +" ";
                top.state ++;


            }else if(top.state == top.node.children.size()){
                post += top.node.data + " ";
                st.pop();


            }
            else
            {
                PPair t = new PPair(top.node.children.get(top.state), -1);
                top.state++;
                st.push(t);

            }
            
        }

        return "preorder is " + pre + "  "+ "postOrder is"+ post ;

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
        System.out.println(LowestCommonAncesstor(root,40, 90));
        System.out.println(DistanceBtwNodes(root, 70,110 ));
        System.out.println(isSymmetric(root));
        size =0;
        min= Integer.MAX_VALUE;
         max =Integer.MIN_VALUE;
         height = 0;
         Multisolver(root, 0);

         System.out.println( max + ""+ min + "" + size + " "+ height);

         predecessor =null; 
         sucesstor =null ;
          state =0;
        preAndSuccess(root, 100);
        System.out.println(predecessor.data);
        // System.out.println(sucesstor.data);
        MaxsumSubtree(root);
        System.out.println(ms + "@" + ms);

        System.out.println("dia maerter is ");
        CalHeightAndGiveDia(root);
        System.out.println(dia);
        System.out.println( 
            iterativePrePost(root));

        




    }
    
}
