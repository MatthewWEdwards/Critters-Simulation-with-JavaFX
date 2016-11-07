
package assignment5;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.stage.Screen;

public abstract class Painter {
	public static void drawCircle(int cornerX, 
								  int cornerY, 
								  int critterWidth, 
								  int critterHeight, 
								  Color c,
								  GraphicsContext displayGraphics){
		displayGraphics.strokeArc(cornerX, cornerY, critterWidth, critterHeight, 0, 360, ArcType.ROUND);
		displayGraphics.setFill(c);
		displayGraphics.fillArc(cornerX, cornerY, critterWidth, critterHeight, 0, 360, ArcType.ROUND);
		
	}
	public static void drawStar(int cornerX, 
			 					int cornerY, 
			 					int critterWidth, 
			 					int critterHeight, 
			 					Color c,
			 					GraphicsContext displayGraphics){
		displayGraphics.strokeLine(cornerX+(critterWidth/2), cornerY, cornerX+(3*critterWidth/5), cornerY + (2*critterHeight/5));
		displayGraphics.strokeLine(cornerX+(3*critterWidth/5), cornerY + (2*critterHeight/5), cornerX+critterWidth, cornerY + (2*critterHeight/5));
		displayGraphics.strokeLine(cornerX+critterWidth, cornerY + (2*critterHeight/5),cornerX+(7*critterWidth/10), cornerY + (3*critterHeight/5));
		displayGraphics.strokeLine(cornerX+(7*critterWidth/10), cornerY + (3*critterHeight/5) ,cornerX+(4*critterWidth/5), cornerY + critterHeight);
		displayGraphics.strokeLine(cornerX+(4*critterWidth/5), cornerY + critterWidth ,cornerX+critterWidth/2, cornerY + (4*critterWidth/5));
		displayGraphics.strokeLine(cornerX+critterWidth/2, cornerY + (4*critterWidth/5),cornerX+(critterWidth/5), cornerY + critterWidth);
		displayGraphics.strokeLine(cornerX+(critterWidth/5), cornerY + critterWidth, cornerX+(3*critterWidth/10), cornerY + (3*critterHeight/5));
		displayGraphics.strokeLine(cornerX+(3*critterWidth/10), cornerY + (3*critterHeight/5), cornerX, cornerY + (2*critterHeight/5));
		displayGraphics.strokeLine(cornerX, cornerY + (2*critterHeight/5),  cornerX+(2*critterWidth/5), cornerY + (2*critterHeight/5));
		displayGraphics.strokeLine(cornerX+(2*critterWidth/5), cornerY + (2*critterHeight/5), cornerX+(critterWidth/2), cornerY);
		displayGraphics.beginPath();
		displayGraphics.moveTo(cornerX+(critterWidth/2), cornerY);
		displayGraphics.lineTo(cornerX+(3*critterWidth/5), cornerY + (2*critterHeight/5));
		displayGraphics.lineTo(cornerX+critterWidth, cornerY + (2*critterHeight/5));
		displayGraphics.lineTo(cornerX+(7*critterWidth/10), cornerY + (3*critterHeight/5));
		displayGraphics.lineTo(cornerX+(4*critterWidth/5), cornerY + critterHeight);
		displayGraphics.lineTo(cornerX+critterWidth/2, cornerY + (4*critterWidth/5));
		displayGraphics.lineTo(cornerX+(critterWidth/5), cornerY + critterWidth);
		displayGraphics.lineTo(cornerX+(3*critterWidth/10), cornerY + (3*critterHeight/5));
		displayGraphics.lineTo( cornerX, cornerY + (2*critterHeight/5));
		displayGraphics.lineTo(cornerX+(2*critterWidth/5), cornerY + (2*critterHeight/5));
		displayGraphics.lineTo(cornerX+(critterWidth/2), cornerY);
		displayGraphics.closePath();
		displayGraphics.setFill(c);
		displayGraphics.fill();
	}
	
	public static void drawDiamond(int cornerX, 
								   int cornerY, 
								   int critterWidth, 
								   int critterHeight, 
								   Color c,
								   GraphicsContext displayGraphics){
		displayGraphics.strokeLine(cornerX, cornerY + critterHeight/2, cornerX + critterWidth/2, cornerY);
		displayGraphics.strokeLine(cornerX, cornerY + critterHeight/2, cornerX + critterWidth/2, cornerY + critterHeight);
		displayGraphics.strokeLine(cornerX + critterWidth/2, cornerY, cornerX + critterWidth, cornerY + critterHeight/2);
		displayGraphics.strokeLine(cornerX + critterWidth/2, cornerY + critterHeight, cornerX + critterWidth, cornerY + critterHeight/2);
		displayGraphics.beginPath();
		displayGraphics.moveTo(cornerX, cornerY + critterHeight/2);
		displayGraphics.lineTo(cornerX + critterWidth/2, cornerY);
		displayGraphics.lineTo(cornerX + critterWidth, cornerY + critterHeight/2);
		displayGraphics.lineTo(cornerX + critterWidth/2, cornerY + critterHeight);
		displayGraphics.lineTo(cornerX, cornerY + critterHeight/2);
		displayGraphics.closePath();
		displayGraphics.setFill(c);
		displayGraphics.fill();
	}
	
	public static void drawTriangle(int cornerX, 
									int cornerY, 
									int critterWidth, 
									int critterHeight, 
									Color c,
									GraphicsContext displayGraphics){
		displayGraphics.strokeLine(cornerX, cornerY+critterHeight, cornerX+(critterWidth/2), cornerY);
		displayGraphics.strokeLine(cornerX+(critterWidth/2), cornerY, cornerX+critterWidth, cornerY+critterHeight);
		displayGraphics.strokeLine(cornerX+critterWidth, cornerY+critterHeight, cornerX, cornerY+critterHeight);
		displayGraphics.beginPath();
		displayGraphics.moveTo(cornerX, cornerY+critterHeight);
		displayGraphics.lineTo(cornerX+(critterWidth/2), cornerY);
		displayGraphics.lineTo(cornerX+critterWidth, cornerY+critterHeight);
		displayGraphics.lineTo(cornerX, cornerY+critterHeight);
		displayGraphics.closePath();
		displayGraphics.setFill(c);
		displayGraphics.fill();
		
	}
	
	public static void drawRectangle(int cornerX, 
									 int cornerY, 
									 int critterWidth, 
									 int critterHeight, 
									 Color c,
									 GraphicsContext displayGraphics){
		displayGraphics.fillRect(cornerX, cornerY, critterWidth, critterHeight);
		displayGraphics.setFill(c);
		displayGraphics.fillRect(cornerX + 1, cornerY + 1, critterWidth-2, critterHeight-2);
	}
}