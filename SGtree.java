package sgtree;

import java.util.Scanner;
public class SGtree {
    class node{
     node right,left,parent;
     int value;
     public node(int v){ // constructor
         value= v;
     }
 }
 public node root;
 int n,q;
 public SGtree(){ // constructor
     
     root=null;
     n=0;
 }
 public boolean isEmpty(){ // function to check if tree is empty
     return root==null;
 }
 public void makeEmpty(){ // function to clear tree
     root=null;
     n=0;
 }
 public int size(node r){ //function to count number of nodes recursively
     if(r==null){
         return 0;
     }
     else{
      int i=1;
      i+=size(r.left);
      i+=size(r.right);
      return i;
     }
 }
 public boolean search(node r, int val){ // functions to search for an element
    boolean found = false;
    while((r!=null)&& !found){
        int rval= r.value;
        if(val<rval){
            r=r.left;
        }
        else if(val>rval){
            r=r.right;
        }
        else{
            found = true;
            break;
        }
        found=search(r,val);
    }
    return found;
 }
 public boolean search(int val){
     return search(root,val);
 }
 public int size(){ // function to return current size of tree
     return n;
 }
 public void inorder(node r){ // inorder traversal recursively
     if(r!=null){
        inorder(r.left);
        System.out.print(" "+r.value);
        inorder(r.right);
     }
 }
 public void inorder(){
     inorder(root);
 }
 public void preorder(node r){ //preorder traversal recursively
     if(r!=null){
         System.out.print(" "+r.value);
         preorder(r.left);
         preorder(r.right);
     }
 }
 public void preorder(){
     preorder(root);
 }
 public void postorder(node r){ //postorder traversal recursively
     if(r!=null){
         postorder(r.left);
         postorder(r.right);
         System.out.print(" "+r.value);
     }
 
 }
 public void postorder(){
     postorder(root);
 }
 private static final int log32(int q){ 
     final double log23= 2.4663034623764317;
     return (int)Math.ceil(log23*Math.log(q));
 }
 public int packintoarray(node u, node[] a, int i){ // function to pack into array
     if(u==null){
         return i;
     }
     i=packintoarray(u.left,a,i);
     a[i++]=u;
     return packintoarray(u.right,a,i);
 }
 public node buildbalanced(node[] a, int i , int ns){ //function to build balance tree
     if(ns==0){
         return null;
     }
     int m= ns/2;
     a[i+m].left= buildbalanced(a,i,m);
     if(a[i+m].left!=null){
         a[i+m].left.parent=a[i+m];
     }
     a[i+m].right= buildbalanced(a,i+m+1,ns-m-1);
     if(a[i+m].right!=null){
         a[i+m].right.parent= a[i+m];
     }
      return a[i+m] ;      
 }
 
 public int addwithdepth(node u){ // function to add with depth
     node w= root;
     if(w==null){
         root= u;
         n++;
         q++;
         
     }
     boolean done = false;
     int d= 0;
     do{
         if(u.value<w.value){
             if(w.left==null){
                 w.left=u;
                 u.parent=w;
                 done= true;
             }
             else{
                 w=w.left;
             }
         }
         else if(u.value>w.value){
             if(w.right == null){
                 w.right= u;
                 u.parent= w;
                 done = true;
            }
            
                 w=w.right;
             
         }
         else{
             return -1;
         }
        d++;
       }while(!done);
        n++;
        q++;
     
      return d;
 }
  public void rebuild(node u){ //function to rebuild tree
      int ns= size(u);
      node p= u.parent;
      node[] a = new node[ns];
      packintoarray(u,a,0);
      if(p==null){
          root=buildbalanced(a,0,ns);
          root.parent= null;
      }
      else if(p.right==u){
          p.right=buildbalanced(a,0,ns);
          p.right.parent= p;
      }
      else{
          p.left= buildbalanced(a,0,ns);
          p.left.parent= p;
      }
  }
  public boolean add(int x){ // function to insert in tree
      node u = new node(x);
      int d= addwithdepth(u);
      if(d>log32(q)){
          node w= u.parent;
          while(3*size(w)<= 2*size(w.parent)){
              w= w.parent;
          }
          rebuild(w.parent);
      }
      return d>=0;
  } 
  
    public static void main(String[] args) {
       Scanner scan = new Scanner (System.in);
       SGtree sgt = new SGtree();
       System.out.print("\n Scape Goat tree test\n");
       char ch;
       do{
           System.out.println("\n Scape Goat tree operations\n");
           System.out.println("1. insert");
           System.out.println("2. count nodes");
           System.out.println("3. search");
           System.out.println("4. check empty");
           System.out.println("5. make empty");
           int choice= scan.nextInt();
           switch(choice){
               case 1:
                    System.out.println("Enter integer element to insert");
                    int x= scan.nextInt();
                    sgt.add(x);
                    break;
               case 2:
                   System.out.println("Nodes= "+sgt.size());
                   break;
               case 3:
                   System.out.println("\n Enter integer element to search: ");
                   System.out.println("Search result: "+sgt.search(scan.nextInt()));
                   break;
               case 4:
                   System.out.println("Empty status: "+sgt.isEmpty());
                   break;
               case 5:
                   System.out.println("\n tree cleared\n ");
                   sgt.makeEmpty();
                   break;
               default:
                   System.out.println("\n wrong entry \n ");
                   break;
           }
           /* Display Tree */
           System.out.println("\n Post order: ");
           sgt.postorder();
           System.out.println("\n Pre order: ");
           sgt.preorder();
           System.out.println("\n In order: ");
           sgt.inorder();
           
           System.out.println("\n Do you want to continue (Type y or n) \n");
           ch= scan.next().charAt(0);
       }while(ch=='y' || ch== 'Y');
       
    }
    
}
