
import java.awt.Point;

public class CrosswordSolver {

    
    private char[][] puzzle;
    
    
    private CrosswordSpace[] slots;
    
    
    private CrosswordWord[] words;
    
    
    private int[][] letterUsage;
    
   
    private int numBacktracks;
    
    
    
    public static final char BLANK = ' ';
    public static final char FILLED = '#';
    
    
    
    public CrosswordSolver ( char[][] puzzle, CrosswordSpace[] slots,
                             CrosswordWord[] words ) {
        this.puzzle = puzzle;
        this.slots = slots;
        this.words = words;                  
    }
    
   
    private void reinitialize() {
        letterUsage = new int[puzzle.length][puzzle[0].length]; 
        numBacktracks = 0;     
    }
    
    
    public void solve() {
        reinitialize();
        
        if ( fillPuzzle(0) ) {
            System.out.println("Solution found!");
            System.out.println("Backtracks: " + numBacktracks);
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
             
       
           
        for ( CrosswordWord word : words ) {
        
            
            if ( wordFitsInSlot(word, slots[slot]) ) {
                putWordInSlot(word, slots[slot]);
                
                if ( fillPuzzle(slot + 1) ) {
                    return true;
                }
                
                removeWordFromSlot(word, slots[slot]);
            }
        }
        
       
        
        numBacktracks++;
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
    
   
    public static void main ( String[] args ) {
    
        
        
        char[][] smallPuzzle = {
           { BLANK, BLANK, BLANK, BLANK, BLANK },
           { FILLED, FILLED, BLANK, FILLED, BLANK },
           { FILLED, BLANK, BLANK, BLANK, BLANK },
           { BLANK, FILLED, BLANK, BLANK, BLANK },
           { BLANK, BLANK, BLANK, BLANK, BLANK },
           { BLANK, FILLED, FILLED, BLANK, FILLED }
           
        };
        
        
        
        CrosswordSpace[] slots = {
            new CrosswordSpace(new Point(0, 0), new Point(0, 1), 5),
            new CrosswordSpace(new Point(0, 2), new Point(1, 0), 5),
            new CrosswordSpace(new Point(0, 4), new Point(1, 0), 5),
            new CrosswordSpace(new Point(2, 1), new Point(0, 1), 4),
            new CrosswordSpace(new Point(2, 3), new Point(1, 0), 4),
            new CrosswordSpace(new Point(3, 0), new Point(1, 0), 3),
            new CrosswordSpace(new Point(3, 2), new Point(0, 1), 3),
            new CrosswordSpace(new Point(4, 0), new Point(0, 1), 5)
        };
        
        
        
        CrosswordWord[] words = {
            new CrosswordWord("AFT"),
            new CrosswordWord("ALE"),
            new CrosswordWord("EEL"),
            new CrosswordWord("HEEL"),
            new CrosswordWord("HIKE"),
            new CrosswordWord("HOSES"),
            new CrosswordWord("KEEL"),
            new CrosswordWord("KNOT"),
            new CrosswordWord("LASER"),
            new CrosswordWord("LEE"),
            new CrosswordWord("LINE"),
            new CrosswordWord("SAILS"),
            new CrosswordWord("SHEET"),
            new CrosswordWord("STEER"),
            new CrosswordWord("TIE")
        };
        
       
        
        CrosswordSolver s = new CrosswordSolver(smallPuzzle, slots, words);
        
        
        
        System.out.println("The puzzle looks like:");
        s.printPuzzle();
        
       
        
        s.solve();
    }
}


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
