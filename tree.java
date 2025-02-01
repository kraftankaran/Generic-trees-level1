import java.util.ArrayList;
import java.util.Stack;

import org.w3c.dom.Node;

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

    }
    
}
