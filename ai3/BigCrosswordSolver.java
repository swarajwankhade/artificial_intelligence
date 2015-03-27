//package assign3;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BigCrosswordSolver {

    // INSTANCE VARIABLES:
	 CrosswordWord[] words3 ;
	 CrosswordWord[] words4 ;
	 CrosswordWord[] words5 ;
	 CrosswordWord[] words7 ;
    // The current state of the crossword puzzle
    private char[][] puzzle;
    
    // A collection of CrosswordSpace objects
    private CrosswordSpace[] slots;
    
    
    private CrosswordWord[] words;
    
    
    private int[][] letterUsage;
    
    
    private int numBacktracks;
    
    
    
    public static final char BLANK = ' ';
    public static final char FILLED = '#';
    
    
   
    public BigCrosswordSolver ( char[][] puzzle, CrosswordSpace[] slots, CrosswordWord[] words3, CrosswordWord[] words4, CrosswordWord[] words5, CrosswordWord[] words7 ) {
        this.puzzle = puzzle;
        this.slots = slots;
        this.words3 = words3; 
        this.words4 = words4;
        this.words5 = words5;
        this.words7 = words7; 
        
    }
    
    
    private void reinitialize() {
        letterUsage = new int[puzzle.length][puzzle[0].length]; 
        numBacktracks = 0;     
    }
    
    
    public void solve() {
        reinitialize();
        
        CrosswordWord word = new CrosswordWord("fun");
        putWordInSlot (word, slots[0]);
        
        if ( fillPuzzle(1) ) {
            System.out.println("Solution found!");
            //System.out.println("Backtracks: " + numBacktracks);
        }
        else {
            System.out.println("No solution found!");
        }
    }
    
    
    private boolean fillPuzzle ( int slot ) {
    
       
    
        if ( slot == slots.length ) {
            printPuzzle();
            return true;
        }
             
        
        
        if (slots[slot].getLength() == 3)
        {
        	return yyy(words3, slot);
        }
        if (slots[slot].getLength() == 4)
        {
        	return yyy(words4, slot);
        }
        if (slots[slot].getLength() == 5)
        {
        	return yyy(words5, slot);
        }
        if (slots[slot].getLength() == 7)
        {
        	return yyy(words7, slot);
        }
        
       
        
        numBacktracks++;
        return false;
    }

    boolean yyy(CrosswordWord[] wordsList,int slot){
    	
    	for ( CrosswordWord word : wordsList ) {
            
            
            
            if ( wordFitsInSlot(word, slots[slot]) ) {
                putWordInSlot(word, slots[slot]);
                
                if ( fillPuzzle(slot + 1) ) {
                    return true;
                }
                
                removeWordFromSlot(word, slots[slot]);
            }
        }
    	return false;
    }
    
    
    
   
    private boolean wordFitsInSlot ( CrosswordWord w, CrosswordSpace slot ) {
    
        
        
        if ( w.getWord().length() != slot.getLength() || w.isUsed() ) {
            return false;
        }
        
        
        
        Point position = new Point(slot.getStart());
        
        for ( int i = 0; i < slot.getLength(); i++ ) {
            
            if ( puzzle[position.x][position.y] != BLANK &&
                 puzzle[position.x][position.y] != w.getWord().charAt(i) ) {
                return false;    
            }
            
           
            
            position.x += slot.getDirection().x;
            position.y += slot.getDirection().y;
        }
        
        
        
        return true;
    }
    
    
    private void putWordInSlot ( CrosswordWord w, CrosswordSpace slot ) {
        Point position = new Point(slot.getStart());
             System.out.println(slot + " - " + w.getWord());
        for ( int i = 0; i < slot.getLength(); i++ ) {         
        
            
            
            puzzle[position.x][position.y] = w.getWord().charAt(i);
            
            
            
            letterUsage[position.x][position.y]++;
            
           
            
            position.x += slot.getDirection().x;
            position.y += slot.getDirection().y;
        }
        
       
        
        w.setUsed(true);
    }
    
    
    private void removeWordFromSlot ( CrosswordWord w, CrosswordSpace slot ) {
        Point position = new Point(slot.getStart());
        
        for ( int i = 0; i < slot.getLength(); i++ ) { 
        
           
            
            letterUsage[position.x][position.y]--;
                       
            
            
            if ( letterUsage[position.x][position.y] == 0 ) {                                      
                puzzle[position.x][position.y] = BLANK;
            }
            
           
            
            position.x += slot.getDirection().x;
            position.y += slot.getDirection().y;
        }
        
        
            
        w.setUsed(false);
    }
    
    
    public void printPuzzle() {
        printPuzzleBorder();
        
        for ( int row = 0; row < puzzle.length; row++ ) {
            System.out.print("|");
            for ( int col = 0; col < puzzle[row].length; col++ ) {
                System.out.print(puzzle[row][col] + "|");
            }
            System.out.println();
        }
              
        printPuzzleBorder();
        
        System.out.println();
    }
    
    
    private void printPuzzleBorder() {
        for ( int i = 0; i < puzzle[0].length * 2 + 1; i++ ) {
            System.out.print("-");
        }
        System.out.println();    
    }
    
   
    public static void main ( String[] args ) throws IOException {
    
        // The crossword puzzle itself before any words have been
        // put into it:
		FileInputStream fstream = new FileInputStream("C:\\Users\\swankhad\\Desktop\\ai3\\Words.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		List<String> arrayList3 = new ArrayList<String>();
		List<String> arrayList4 = new ArrayList<String>();
		List<String> arrayList5 = new ArrayList<String>();
		List<String> arrayList7 = new ArrayList<String>();
		String strLine;
		
		while ((strLine = br.readLine()) != null)   
		{
			//arrayList.add(strLine);
			//System.out.println (strLine);
			//System.out.println("Inside");
			// Print the content on the console
			//System.out.println (strLine);
			

			if(strLine.length() == 3)
			{
				arrayList3.add(strLine);
				continue;
			}
			if(strLine.length() == 4)
			{
				arrayList4.add(strLine);
				continue;
			}
			if(strLine.length() == 5)
			{
				arrayList5.add(strLine);
				continue;
			}
			if(strLine.length() == 7)
			{
				arrayList7.add(strLine);
				continue;
			}
			
		}
		
br.close();
        
        char[][] smallPuzzle = {
           { FILLED, FILLED, FILLED, FILLED, FILLED, FILLED, BLANK , FILLED, FILLED, FILLED, FILLED, FILLED, FILLED },
           { FILLED, FILLED, FILLED, FILLED, FILLED, 'f', 'u', 'n', FILLED, FILLED, FILLED, FILLED, FILLED },
           { FILLED, FILLED, FILLED, FILLED, BLANK, BLANK, BLANK, BLANK, BLANK, FILLED, FILLED, FILLED, FILLED },
           { FILLED, FILLED, FILLED, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, FILLED, FILLED, FILLED },
           { FILLED, FILLED, BLANK, BLANK, BLANK, BLANK, FILLED, BLANK, BLANK, BLANK, BLANK, FILLED, FILLED },
           { FILLED, BLANK, BLANK, BLANK, BLANK, FILLED, FILLED, FILLED, BLANK, BLANK, BLANK, BLANK, FILLED },
		   { BLANK, BLANK, BLANK, BLANK, FILLED, FILLED, FILLED, FILLED, FILLED, BLANK, BLANK, BLANK, BLANK },
           { FILLED, BLANK, BLANK, BLANK, BLANK, FILLED, FILLED, FILLED, BLANK, BLANK, BLANK, BLANK, FILLED },
		   { FILLED, FILLED, BLANK, BLANK, BLANK, BLANK, FILLED, BLANK, BLANK, BLANK, BLANK, FILLED, FILLED },
		   { FILLED, FILLED, FILLED, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, FILLED, FILLED, FILLED },
		   { FILLED, FILLED, FILLED, FILLED, BLANK, BLANK, BLANK, BLANK, BLANK, FILLED, FILLED, FILLED, FILLED },
		   { FILLED, FILLED, FILLED, FILLED, FILLED, BLANK, BLANK, BLANK, FILLED, FILLED, FILLED, FILLED, FILLED },
		   { FILLED, FILLED, FILLED, FILLED, FILLED, FILLED, BLANK , FILLED, FILLED, FILLED, FILLED, FILLED, FILLED }
        };
        
        
        // (0, 1) --> Accross
		// (1, 0) --> Down
        CrosswordSpace[] slots = {
        	new CrosswordSpace(new Point(1, 5), new Point(0, 1), 3),
        	new CrosswordSpace(new Point(3, 3), new Point(0, 1), 7),
        	new CrosswordSpace(new Point(2, 4), new Point(0, 1), 5),
            new CrosswordSpace(new Point(0, 6), new Point(1, 0), 4),
            new CrosswordSpace(new Point(1, 5), new Point(1, 0), 4),
            new CrosswordSpace(new Point(1, 7), new Point(1, 0), 4),
            new CrosswordSpace(new Point(2, 4), new Point(1, 0), 4),
            new CrosswordSpace(new Point(2, 8), new Point(1, 0), 4),
            new CrosswordSpace(new Point(3, 3), new Point(1, 0), 7),
            new CrosswordSpace(new Point(3, 9), new Point(1, 0), 7),
            new CrosswordSpace(new Point(4, 2), new Point(0, 1), 4),
			new CrosswordSpace(new Point(4, 7), new Point(0, 1), 4),
			new CrosswordSpace(new Point(4, 2), new Point(1, 0), 5),
			new CrosswordSpace(new Point(4, 10), new Point(1, 0), 5),
			new CrosswordSpace(new Point(5, 1), new Point(0, 1), 4),
			new CrosswordSpace(new Point(5, 8), new Point(0, 1), 4),
			new CrosswordSpace(new Point(5, 1), new Point(1, 0), 3),
			new CrosswordSpace(new Point(5, 11), new Point(1, 0), 3),
			new CrosswordSpace(new Point(6, 0), new Point(0, 1), 4),
			new CrosswordSpace(new Point(6, 9), new Point(0, 1), 4),
			new CrosswordSpace(new Point(7, 1), new Point(0, 1), 4),
			new CrosswordSpace(new Point(7, 8), new Point(0, 1), 4),
			new CrosswordSpace(new Point(9, 3), new Point(0, 1), 7),
			new CrosswordSpace(new Point(7, 4), new Point(1, 0), 4),
			new CrosswordSpace(new Point(7, 8), new Point(1, 0), 4),
			new CrosswordSpace(new Point(10, 4), new Point(0, 1), 5),
			new CrosswordSpace(new Point(8, 2), new Point(0, 1), 4),
			new CrosswordSpace(new Point(8, 7), new Point(0, 1), 4),
			new CrosswordSpace(new Point(8, 5), new Point(1, 0), 4),
			new CrosswordSpace(new Point(8, 7), new Point(1, 0), 4),
			new CrosswordSpace(new Point(11, 5), new Point(0, 1), 3),
			new CrosswordSpace(new Point(9, 6), new Point(1, 0), 4)
			};
        String str1;
       
        
			
			//CrosswordWord[] words =  new CrosswordWord[arrayList.size()];
         CrosswordWord[] words3  =  new CrosswordWord[arrayList3.size()];
   	  CrosswordWord[] words4 =  new CrosswordWord[arrayList4.size()]; 
   	  CrosswordWord[] words5 =  new CrosswordWord[arrayList5.size()]; 
   	  CrosswordWord[] words7 =  new CrosswordWord[arrayList7.size()]; 
			
			for (int k=0 ; k<arrayList3.size();k++)
			{
				str1=arrayList3.get(k);
				words3[k] = new CrosswordWord(str1);
								
						
			}
			
			for (int k=0 ; k<arrayList4.size();k++)
			{
				str1=arrayList4.get(k);
				words4[k] = new CrosswordWord(str1);
								
					
			}
			
			for (int k=0 ; k<arrayList5.size();k++)
			{
				str1=arrayList5 .get(k);
				words5[k] = new CrosswordWord(str1);
								
						
			}								
			
			for (int k=0 ; k<arrayList7.size();k++)
			{
				str1=arrayList7.get(k);
				words7[k] = new CrosswordWord(str1);
				
			}
			
        
 
        
        
        
        BigCrosswordSolver s = new BigCrosswordSolver(smallPuzzle, slots, words3,words4,words5,words7);
        
       
        
        System.out.println("The puzzle looks like:");
        s.printPuzzle();
        
        s.solve();
    }
}

// HELPER CLASSES:


class CrosswordSpace {
    private Point start;
    private Point direction;
    private int length;
        
    public CrosswordSpace ( Point start, Point direction, int length ) {
        this.start = start;
        this.direction = direction;
        this.length = length;
    }
    
    public Point getStart() {
        return start;
    }
    
    public Point getDirection() {
        return direction;
    }
    
    public int getLength() {
        return length;
    }
    
    @Override
    public String toString(){
    	
    	String x = "start: " + start.toString() + ", direction: " + direction.toString() + ", length: " + length;
    	return x;
    }
}


class CrosswordWord {
    private String word;
    private boolean used;
        
    public CrosswordWord ( String word ) {
        this.word = word;
        used = false;
    }
    
    public String getWord() {
        return word;
    }
    
    public boolean isUsed() {
        return used;
    }
    
    public void setUsed ( boolean isUsed ) {
        used = isUsed;
    }
}
