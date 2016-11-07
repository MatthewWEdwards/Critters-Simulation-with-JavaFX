package assignment5;

import assignment5.Critter;
import assignment5.Critter.CritterShape;

public class Critter2 extends Critter{
		private int dir;
		
		@Override
		/**
		 * Represented in world view by L
		 */
		public String toString() { return "L"; }

		@Override 
		/**
		 * Behavior of Critter2, changes direction each time, does not try to walk
		 */
		public void doTimeStep(){
			int energy = getEnergy();
			dir = (energy*1000%7);
			String look = this.look(dir, true);
			if(look == null)
				run(dir);
		}
		
		
		@Override
		/**
		 * Tries to flee from a fight
		 * @param opponent, name of opponent in fight
		 * @return returns false because does not choose to ever fight
		 */
		public boolean fight(String opponent){
		
			walk(dir); 
			return false;
		}
	
	@Override
	public CritterShape viewShape() { return CritterShape.TRIANGLE; }

	@Override
	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.BLUE; }
	public javafx.scene.paint.Color viewFillColor() { return javafx.scene.paint.Color.ORANGE; }
}
