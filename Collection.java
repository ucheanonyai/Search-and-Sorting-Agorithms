/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eceproject1;

import java.io.*;
import java.util.*; 

class Collection{

    /////////////////////////////////////
    ///////////// Private Variables//////
    /////////////////////////////////////
    private Dictionary[] collection;
    private Dictionary dict2;
    private int add;
    private int NoOfDictionaries;
    private int dictsize;
    private int max;
    private String[]dictionaryarray;

    /////////////////////////////////////
    ///////////// Constructor///////////
   public Collection(Dictionary dict2){
       
       this.dict2=dict2;
       
       dictsize=dict2.getSize();
       
       max=dict2.maxSizeWord();
       
       collection=new Dictionary[max];
       
       for(int i=0;i<max;i++){
           collection[i]=new Dictionary(300000);
       }
       
       for(int i=dict2.getnbwords()-1;i>=0;i--){
        collection[dict2.getWords(i).length()-1].insert(dict2.deleteLast());
       }
       
       
       
      
       
      
   }
   
   public void info(){
   System.out.println("The collection named '" + dict2.getName() + "' contains " + collection.length);
   System.out.println("dict --> size");
       System.out.println(dict2.getnbwords());
   for (int i = 0; i < collection.length; i++) {
      System.out.println((i+1) + " --> " + collection[i].getnbwords());
       }
    }
      
      
 public void saveCollection() {
   for (int i = 0; i < collection.length; i++) {
      collection[i].saveDictionary(collection[i].getName() + (i+1));
   } 
 }
 
 public boolean search(String a) {
     boolean found=false;
   if(collection!=null){
   found =  collection[a.length()-1].linearSearch(a);
   //add = dict3.getStep();
   }
   else{
       found=false;
   }
   return found;
  
 }
 
 public int getStep() {
      return add;
   }
   
 public void crackLock(char arr[][], int letters, int a){
    
     String random;
     int numOfWords=0;
    
    Random rand = new Random();
    // formulla to find c
    double c =5* (Math.pow(a,letters));
    for(int i =0; i<c;i++){
    random = "";
    for(int k=0; k<letters;k++)
    {
    random+=arr[k][rand.nextInt(a)];
    }
    if(collection[letters-1].binarySearch(random) == true)
    {
    numOfWords++;
    System.out.print(random);
    }
    }
    System.out.println("Number of words found" + numOfWords);
}
   
}

