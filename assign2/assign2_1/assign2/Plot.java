package assign2;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class Plot extends JPanel {
	double[] data = new double[100];
	public Plot(double[] in){
		
		for (int i = 0; i < 100; i++){
			data[i] = in[i];
			
		}
		System.out.println();
}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.draw(new Line2D.Double(35, 15, 35, 600));
		g2.draw(new Line2D.Double(35, 600, 650, 600));
        int j = 0;
		while(j < data.length){
			String run = "" + j;
			g2.drawString(run, (j*6)+35, 610);
			j=j+5;
		}
		g2.drawString("100", 635, 610);
		g2.drawString("run", 50*6, 620);
		 Font font = g2.getFont();
	        FontRenderContext frc = g2.getFontRenderContext();
	        LineMetrics lm = font.getLineMetrics("0", frc);
	        float sh = lm.getAscent() + lm.getDescent();
	        
	        String s = "minimum";
	        float sy = ((600 - 40) - s.length()*sh)/2 + lm.getAscent();
	        for(int i = 0; i < s.length(); i++) {
	            String letter = String.valueOf(s.charAt(i));
	            float sw = (float)font.getStringBounds(letter, frc).getWidth();
	            float sx = (10-sw)/2;
	            g2.drawString(letter, sx, sy);
	            sy += sh;
	        }
	        g2.drawString("5", 20, 25);
	        g2.drawString("10", 20, 45);
	        g2.drawString("15", 20, 65);
	        g2.drawString("20", 20, 85);
	        g2.drawString("25", 20, 105);
	        g2.drawString("30", 20, 125);
	        g2.drawString("35", 20, 145);
	        g2.drawString("40", 20, 165);
	        g2.drawString("45", 20, 185);
	        g2.drawString("50", 15, 205);
	        g2.drawString("55", 15, 225);
	        g2.drawString("60", 15, 245);
	        g2.drawString("65", 15, 265);
	        g2.drawString("70", 15, 285);
	        g2.drawString("75", 15, 305);
	        g2.drawString("80", 15, 325);
	        g2.drawString("85", 15, 345);
	        g2.drawString("90", 15, 365);
	        g2.drawString("95", 15, 385);
	        g2.drawString("100", 15, 405);
	        g2.drawString("105", 15, 425);
	        g2.drawString("110", 15, 445);
	        g2.drawString("115", 15, 465);
	        g2.drawString("120", 15, 485);
	        g2.drawString("125", 15, 505);
	        g2.drawString("130", 15, 525);
	        g2.drawString("135", 15, 545);
	        g2.drawString("140", 15, 565);
	        g2.drawString("145", 15, 585);
	        g2.drawString("150", 15, 605);
	       
		   
        g2.setPaint(Color.red);
        for(int i = 0; i < data.length; i++) {
          
        	double y = data[i]*4.0;
            double x = (6*i)+55;
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
          }
	}
        
}
