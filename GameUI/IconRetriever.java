package GameUI;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;

import Game.Piece;

public class IconRetriever {
	
	public static String generateURI(String location) {
		return "ShogiPieces"+"/"+location; 
	}
	
	public static BufferedImage getIcon(Piece piece, int w, int h) {
		boolean rotate = piece.getDirection().equals("down") ? true : false; 
		//king
		//golden general
		//silver general 
		//knight
		//lance
		//bishop
		//pawn
		
		//set up the conditions
		
		if (piece.getId().equals("King")) {
			return IconRetriever.getImage(h, w, rotate, generateURI("shogiking-piece.gif")); 
		}
		else if(piece.getId().equals("Lance")) {
			if(piece.getisPromoted()) {
				return IconRetriever.getImage(h, w, rotate, generateURI("plance-piece.gif")); 
			}
			return IconRetriever.getImage(h, w, rotate, generateURI("shogilance-piece.gif")); 
		}
		else if(piece.getId().equals("Knight")) {
			if(piece.getisPromoted()) {
				return IconRetriever.getImage(h, w, rotate, generateURI("phorse-piece.gif")); 
			}
			return IconRetriever.getImage(h, w, rotate, generateURI("shogihorse-piece.gif")); 
		}
		else if(piece.getId().equals("Silver General")) {
			if (piece.getisPromoted()) {
			return IconRetriever.getImage(h, w, rotate, generateURI("psilver-piece.gif")); 
			}
			return IconRetriever.getImage(h, w, rotate, generateURI("silver-piece.gif")); 
			
		}
		else if(piece.getId().equals("Gold General")) {
			return IconRetriever.getImage(h, w, rotate, generateURI("gold-piece.gif")); 
			
		}
		else if(piece.getId().equals("Bishop")) {
			if (piece.getisPromoted()) {
			return IconRetriever.getImage(h, w, rotate, generateURI("pbishop-piece.gif")); 
			}
			return IconRetriever.getImage(h, w, rotate, generateURI("shogibishop-piece.gif")); 
			
		}
		else if(piece.getId().equals("Pawn")) {
			if (piece.getisPromoted()) {
			return IconRetriever.getImage(h, w, rotate, generateURI("ppawn-piece.gif")); 
			}
			return IconRetriever.getImage(h, w, rotate, generateURI("shogipawn-piece.gif")); 
			
		}
		else if(piece.getId().equals("Rook")) {
			if (piece.getisPromoted()) {
			return IconRetriever.getImage(h, w, rotate, generateURI("prook-piece.gif")); 
			}
			return IconRetriever.getImage(h, w, rotate, generateURI("shogirook-piece.gif")); 
			
		}
		
		
		return null; 
	}
	
	
	
	public static BufferedImage getImage(int h, int w, boolean rotate, String location) {
		try {
			BufferedImage image = ImageIO.read(new File(location));
			
			return IconRetriever.getScaledImage(image, w, h, rotate); 
			
		} catch (IOException e) {
			System.out.println(e.getMessage() + ": " + location);
		} 
		return null; 
	}
	
	private static BufferedImage getScaledImage(BufferedImage src, int w, int h, boolean rotate){
	    int finalw = w;
	    int finalh = h;
	    double factor = 1.0d;
	    if(src.getWidth() > src.getHeight()){
	        factor = ((double)src.getHeight()/(double)src.getWidth());
	        finalh = (int)(finalw * factor);                
	    }else{
	        factor = ((double)src.getWidth()/(double)src.getHeight());
	        finalw = (int)(finalh * factor);
	    }   
	    
	    BufferedImage resizedImg = new BufferedImage(finalw, finalh, BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = resizedImg.createGraphics();
	    if (rotate) {
	    	AffineTransform at = new AffineTransform();

            // 4. translate it to the center of the component
            at.translate(w/1.35, h/1.3);

            // 3. do the actual rotation
            at.rotate(Math.PI);

            // 2. just a scale because this image is big
            at.scale(0.5, 0.5);

            // 1. translate the object so that you rotate it around the 
            //    center (easier :))
            at.translate(-w/2, -h/2);

            // draw the image
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(src, at, null);
            g2.dispose();
            return resizedImg; 

		}
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(src, 0, 0, finalw, finalh, null);
	    g2.dispose();
	    return resizedImg;
	}
	
}
