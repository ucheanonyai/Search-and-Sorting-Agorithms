/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eceproject1;

/**
 *
 * @author ucheanonyai
 */
import com.sun.org.apache.xpath.internal.FoundIndex;
import java.io.*;
import java.util.*; 

class Dictionary{

    /////////////////////////////////////
    ///////////// Private Variables//////
    /////////////////////////////////////
    
    // private variables related to the dictionary
    private String name; // name of the dictionary
    private int nbwords=0;  // number of words
    private String[] words; // array of words
    private int maxsize; // max fixed size of the array
    private boolean sorted=false; // flag for sorted/unsorted
    private String[]Arrayz; //array to help in swaping words
    private String addedfile;
    
    // private variables related to the search for the dictionary
    private int lowIndex;
    private int middleIndex;
    private int searchIndex=0;
    private int searchStepCounter=0;
    private int i=0;
    private String information; //info method message

    /////////////////////////////////////
    ///////////// Constructors///////////
    /////////////////////////////////////
  
    public Dictionary(int maxsize) {
	this.maxsize=maxsize;
	words=new String[maxsize];
    }   


    ////////////////////////////////////////////////////////////
    ///////////// Methods //////////////////////////////////////
    ////////////////////////////////////////////////////////////
  
    public void loadDictionary(String name){
        this.name=name;
    


        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader("/Applications/Engineering/project1-f17/"+this.name);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            

            while((line = bufferedReader.readLine()) != null && i<maxsize) {
                
                words[i]=line;
                //System.out.println(line);
                i++;
            }   
            nbwords=i;

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                this.name + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '"  + name + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        
        
    }
    
   public String info(){
       if (sorted == true){
       information="The dictionary "+ name +" is sorted and is of size: "+nbwords;
       }
       else{
       information="The dictionary "+ name +" is not sorted and is of size: "+nbwords;  
       }
       return information;
   }
   
   public void sortBubble(){
      
       for(int i = nbwords - 1; i > 1; i--){
           
            //loop to check if values is greater
            for(int j= 0;j < i; j++){
                
                //compare array value
                int results=words[j].compareTo(words[j+1]);
                
                if(results > 0){
                  
                    //swap values function
                    swapValues(j, j+1);
                    
                }
                
            }
        }
       for(int i=0;i<nbwords;i++){
           System.out.println(words[i]);
       }
       sorted=true;
   }
   
   public void swapValues(int indexOne, int indexTwo){
         
        //creat a temp int
        String temp = words[indexOne];
        
        //store index one
        words[indexOne] = words[indexTwo];
        
        //store value for index two
        words[indexTwo] = temp;
        
    }
   
   public void sortSelection(){
       //loop through array
        for(int i =1; i < nbwords; i++){
            
            //set j
            int j = i;
            
            //insert value
            String toInsert = words[i];
            
            //check
            while((j>0) && (words[j-1].compareTo(toInsert)>0)){
                
                //set j -1
                words[j] = words[j-1];
                
                //decrement j
                j--;
                
            }
            
            //set the array
            words[j] = toInsert;
     
        }
       for(int i=0;i<nbwords;i++){
           System.out.println(words[i]);
       }
        
       sorted=true;
       
   }
   
   public void sortInsertion(){
          for(int i =1; i < nbwords; i++){
            
            //set j
            int j = i;
            
            //insert value
            String toInsert = words[i];
            
            //check
            while((j>0) && (words[j-1].compareTo(toInsert) > 0)){
                
                //set j -1
                words[j] = words[j-1];
                
                //decrement j
                j--;
                
            }
            
            //set the array
            words[j] = toInsert;
     
        }
        for(int i=0;i<nbwords;i++){
           System.out.println(words[i]);
       }
        sorted=true;
       }
   
   public void sortEnhancedInsertion(){
   
        for (int i=0;i<words.length;++i){
            String temp=words[i];
            int left=0;
            int right=i;
            while (left<right){
                int middle=(left+right)/2;
                if (temp.compareTo(words[middle])>0)
                    left=right+1;
                else
                    right=middle;
            }
            for (int j=i;j>left;--j){
                swapValues(j-1,j);
            }
        }
    
         sorted=true;
   }
   
   public void saveDictionary(String extension){
      
       try{
           PrintWriter out=new PrintWriter("/Applications/Engineering/project1-f17/"+name+extension);
          
           for(int i=0;i<words.length;i++){
           out.println(words[i]);
           }
           out.close();
       }
       catch(IOException e){
           e.printStackTrace();
       }
   }
   
   public String getName(){
       return name;
   } 
   
   public void setSorted(boolean sorted){
       this.sorted=sorted;
       System.out.println(this.sorted);
   }
   
   public boolean isSorted(){
       return sorted;
   }
   
   public boolean linearSearch(String random){
       //set boolean
        boolean valueInArray = false;
        
        //set string
        String indexWithValue = "";
        
        System.out.println("Value was found in the following ");
        
        //loop array
        for(int i = 0; i < nbwords; i++){
            
            if(words[i].equals(random) ){
                
                valueInArray = true;
                
                System.out.println(i + " ");
                
                indexWithValue = i + " ";
            }
        }
        
        
        return valueInArray;
    }
   
   public boolean binarySearch(String random){
       //set lowindex
       lowIndex = 0;
       
       boolean found=false;
       //set highIndex
       int highIndex = nbwords - 1;
       
       //loop through array
       while(lowIndex <= highIndex){
           
           //set middle Index
           middleIndex = (highIndex + lowIndex) / 2;
           
           //check for match
           if(words[middleIndex].compareTo(random)<0) lowIndex = middleIndex + 1;
           
           else if(words[middleIndex].compareTo(random)>0) highIndex = middleIndex - 1;
           
           //find exact match
           else{
               
               //print 
               System.out.println("\n Found a match for " + random + " at index " + middleIndex);
               
               //match low and high
               lowIndex = highIndex + 1;
               found=true;
           }
           
   }
       return found;
   }
   
   public String getRandomWord(){
       Random rand=new Random();
       int random=rand.nextInt(maxsize);
       return words[random];
   }
   
   public int getStep(){
       int steps=(int) (Math.log(nbwords) / Math.log(2));
      return  steps;
   }
   
   public int getSize(){
       return nbwords;
   }
   
   public boolean isFull(){
       boolean full=false;
       
      if(nbwords>=maxsize){
          full=true;
       }
       return full;
   }
   
   public String deleteLast(){
       String deletelaString=words[nbwords-1];
       words[nbwords-1]=null;
       nbwords--;
       return deletelaString;
   }
   
   public boolean isEmpty(){
       boolean empty=false;
       if(words==null){
          empty= true;
       }
       return empty;
   }
   
   
   
   public void insert(String deleteLast){
      
       //System.out.println(nbwords);
       if(isEmpty()==true){
           words[getSize()]=deleteLast;
           nbwords++;
       }
       
       
       
       else if (sorted==false){
           words[nbwords]=deleteLast;
           nbwords++;
       }
       else {
           if(binarySearch(deleteLast)==false)
               for(i=nbwords-1;i>=middleIndex;i--){
                   words[i+1]=words[i];
               }
           words[middleIndex]=deleteLast;
           nbwords++;
      
   }
   }
 //Dictionary App 4-------------------
   
   public int maxSizeWord(){
       int maxSize=0;
       for(int i=0;i<nbwords;i++){
           String word=words[i];
           if(word.length()>maxSize){
               maxSize=word.length();
           }
       }
       return maxSize;
   }
   
   public String getWords(int i){
       
       return words[i];
   }
   
   
   public int getnbwords(){
       return nbwords;
   }
   
}


