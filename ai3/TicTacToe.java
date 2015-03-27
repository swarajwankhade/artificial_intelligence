import java.io.*;
public class TicTacToe{
    public static void main(String args[])throws IOException{
        BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
        char[][] u=new char[3][3];
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                u[i][j]='-';
            }
        }
        System.out.println("                    TIC TAC TOE");
        System.out.println("You play as 'X' while the computer plays as 'O'.");
        System.out.println("****************************************************");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.print("Choose a difficulty : ");
        int c=3;
        try{
            c=Integer.parseInt(b.readLine());
        }
        catch(Exception e){
            System.out.println("Invalid choice. Choosing Hard by default");
            System.out.print("Press Enter to continue ");
            String trash=b.readLine();
        }
        if (c!=1&&c!=2&&c!=3){
            System.out.println("Invalid choice. Choosing Hard by default");
            System.out.print("Press Enter to continue ");
            String trash=b.readLine();
            c=3;
        }
        int loop=0;
        int move=0;
        int game=0;
        int win=0;
        while(true){
            game++;
            if (game%2==0){
                    int[] g=compute(c,u);
                    u[g[0]][g[1]]='O';
            }
            while (loop<1){
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\f");
                display(u);
                System.out.println("\n****************************************************");
                System.out.print("Enter counter position : ");
                String loc=b.readLine();
                if (loc.length()<2){
                    loc+="xxx";
                }
                char xx=loc.charAt(0);
                char yy=loc.charAt(1);
                if (loc.length()!=2||((!(xx>='a'&&xx<='c'))&&(!(xx>='A'&&xx<='C')))||(!(yy>='1'&&yy<='3'))){
                    System.out.println("Wrong input format. Type the letter of the grid location first , followed by it's number. Example : A3");
                    System.out.print("Press Enter to try again");
                    String trash1=b.readLine();
                    continue;
                }
                int x=(int)Character.toUpperCase(xx)-65;
                int y=(int)yy-49;
                if (u[x][y]!='-'){
                    System.out.println("That grid has already been filled. Select another.");
                    System.out.print("Press Enter to try again");
                    String trash2=b.readLine();
                    continue;
                }
                u[x][y]='X';
                if (check(u)>0){
                    break;
                }
                int[] g=compute(c,u);
                u[g[0]][g[1]]='O';
                if (check(u)>0){
                    break;
                }
                
                move++;
            }
            System.out.println("\n****************************************************");
            if (check(u)==1){
                System.out.println("YOU LOSE");
                System.out.println("You have won "+win+" matches out of "+game);
            }
            else if (check(u)==2){
                win++;
                System.out.println("YOU WIN");
                System.out.println("You have won "+win+" matches out of "+game);
            }
            else if (check(u)==3){
                System.out.println("THE MATCH IS DRAWN");
                System.out.println("You have won "+win+" matches out of "+game);
            }
            System.out.println("\n****************************************************");
            System.out.print("Do you want to play again? (y/n) ");
            String resp=b.readLine();
            for (int i=0;i<3;i++){
                for (int j=0;j<3;j++){
                    u[i][j]='-';
                }
            }
            try{
                if (resp.charAt(0)=='n'||resp.charAt(0)=='N'){
                    break;
                }
            }
            catch(Exception ex){
                break;
            }
        }
        System.out.println("\n****************************************************");
        System.out.println("Thank you for playing.");
    }
    public static void display(char[][] u){
        System.out.println("\t   1  2  3");
        System.out.println("\tA  "+u[0][0]+"  "+u[0][1]+"  "+u[0][2]);
        System.out.println("\tB  "+u[1][0]+"  "+u[1][1]+"  "+u[1][2]);
        System.out.println("\tC  "+u[2][0]+"  "+u[2][1]+"  "+u[2][2]);
    }
    public static int check(char[][] u){
        if (u[0][0]=='O'&&u[0][1]=='O'&&u[0][2]=='O'){
            return 1;
        }
        else if (u[0][0]=='X'&&u[0][1]=='X'&&u[0][2]=='X'){
            return 2;
        }
        else if (u[1][0]=='O'&&u[1][1]=='O'&&u[1][2]=='O'){
            return 1;
        }
        else if (u[1][0]=='X'&&u[1][1]=='X'&&u[1][2]=='X'){
            return 2;
        }
        else if (u[2][0]=='O'&&u[2][1]=='O'&&u[2][2]=='O'){
            return 1;
        }
        else if (u[2][0]=='X'&&u[2][1]=='X'&&u[2][2]=='X'){
            return 2;
        }
        else if (u[0][0]=='O'&&u[1][0]=='O'&&u[2][0]=='O'){
            return 1;
        }
        else if (u[0][0]=='X'&&u[1][0]=='X'&&u[2][0]=='X'){
            return 2;
        }
        else if (u[0][1]=='O'&&u[1][1]=='O'&&u[2][1]=='O'){
            return 1;
        }
        else if (u[0][1]=='X'&&u[1][1]=='X'&&u[2][1]=='X'){
            return 2;
        }
        else if (u[0][2]=='O'&&u[1][2]=='O'&&u[2][2]=='O'){
            return 1;
        }
        else if (u[0][2]=='X'&&u[1][2]=='X'&&u[2][2]=='X'){
            return 2;
        }
        else if (u[0][0]=='O'&&u[1][1]=='O'&&u[2][2]=='O'){
            return 1;
        }
        else if (u[0][0]=='X'&&u[1][1]=='X'&&u[2][2]=='X'){
            return 2;
        }
        else if (u[2][0]=='O'&&u[1][1]=='O'&&u[0][2]=='O'){
            return 1;
        }
        else if (u[2][0]=='X'&&u[1][1]=='X'&&u[0][2]=='X'){
            return 2;
        }
        else {
            int count=0;
            for (int i=0;i<3;i++){
                for (int j=0;j<3;j++){
                    if (u[i][j]=='-'){
                        count++;
                    }
                }
            }
            if (count==0){
                return 3;
            }
            else{
                return 0;
            }
        }
    }
    public static int[] compute(int c,char[][] u){
        int ar[]=new int[2];
        if (c==1){
            int x=(int)(Math.random()*3);
            int y=(int)(Math.random()*3);
            if (u[x][y]=='-'){
                ar[0]=x;
                ar[1]=y;
                return ar;
            }
            else{
                return compute(1,u);
            }
        }
        if (c==2){
            return compute(((int)(Math.random()*3)+1),u);
        }
        if (c==3){
            boolean mark=false;
            int x=0,y=0;
            int count=0;
            for (int i=0;i<3;i++){
                for (int j=0;j<3;j++){
                    if (u[i][j]=='-'){
                        count++;
                        u[i][j]='X';
                        if (check(u)==2){
                            mark=true;
                            x=i;
                            y=j;
                        }
                        u[i][j]='-';
                    }
                }
            }
            for (int i=0;i<3;i++){
                for (int j=0;j<3;j++){
                    if (u[i][j]=='-'){
                        u[i][j]='O';
                        if (check(u)==1){
                            mark=true;
                            x=i;
                            y=j;
                        }
                        u[i][j]='-';
                    }
                }
            }
            if ((!mark)&&(predict(u,0,0)>1||predict(u,0,2)>1||predict(u,1,1)>1||predict(u,2,0)>1||predict(u,2,2)>1)){
                for (int i=0;i<3;i++){
                    for (int j=0;j<3;j++){
                        if (u[i][j]=='-'&&((i==0&&j==1)||(i==1&&j==0)||(i==1&&j==2)||(i==2&&j==1))){
                            u[i][j]='O';
                            for (int k=0;k<3;k++){
                                for (int l=0;l<3;l++){
                                    if (u[k][l]=='-'){
                                        u[k][l]='O';
                                        if (check(u)==1){
                                            mark=true;
                                            x=k;
                                            y=l;
                                        }
                                        u[k][l]='-';
                                    }
                                }
                            }
                            u[i][j]='-';
                        }
                    }
                }
            }
            if (!mark){
                if (count==9){
                    int ran=(int)(Math.random()*5);
                    switch(ran){
                        case 0 : x=0;
                                 y=0;
                                 break;
                        case 1 : x=1;
                                 y=1;
                                 break;
                        case 2 : x=2;
                                 y=2;
                                 break;
                        case 3 : x=0;
                                 y=2;
                                 break;
                        case 4 : x=2;
                                 y=0;
                                 break;
                    }
                }
                else if (count==8){
                    if (u[1][1]=='-'){
                        x=1;
                        y=1;
                    }
                    else{
                        int ran=(int)(Math.random()*4);
                        switch(ran){
                            case 0 : x=0;
                                     y=0;
                                     break;
                            case 1 : x=2;
                                     y=0;
                                     break;
                            case 2 : x=2;
                                     y=2;
                                     break;
                            case 3 : x=0;
                                     y=2;
                                     break;
                        }
                    }
                }
                else{
                    if (u[0][0]=='-'&&u[2][2]=='O'){
                        x=0;
                        y=0;
                    }
                    else if (u[2][2]=='-'&&u[0][0]=='O'){
                        x=2;
                        y=2;
                    }
                    else if (u[0][2]=='-'&&u[2][0]=='O'){
                        x=0;
                        y=2;
                    }
                    else if (u[2][0]=='-'&&u[0][2]=='O'){
                        x=2;
                        y=0;
                    }
                    else{
                        if (u[0][0]=='-'){
                            x=0;
                            y=0;
                        }
                        else if (u[2][2]=='-'){
                            x=2;
                            y=2;
                        }
                        else if (u[0][2]=='-'){
                            x=0;
                            y=2;
                        }
                        else if (u[2][0]=='-'){
                            x=2;
                            y=0;
                        }
                        else{
                            for (int i=0;i<3;i++){
                                for (int j=0;j<3;j++){
                                    if (u[i][j]=='-'){
                                        x=i;
                                        y=j;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (u[x][y]=='-'){
                ar[0]=x;
                ar[1]=y;
                return ar;
            }
            else{
                return compute(3,u);
            }
        }
        return ar;
    }
    public static int predict(char[][] u,int x,int y){
        if (u[x][y]=='-'){
            u[x][y]='X';
            int number=0;
            for (int i=0;i<3;i++){
                for (int j=0;j<3;j++){
                    if (u[i][j]=='-'){
                        u[i][j]='X';
                        if (check(u)==2){
                           number++;
                        }
                        u[i][j]='-';
                    }
                }
            }
            u[x][y]='-';
            return number;
        }
        else{
            return 0;
        }
    }
 /**
 *     1  2  3
 *  A  -  -  -
 *  B  -  -  -
 *  C  -  -  -
 * 
 **/
}