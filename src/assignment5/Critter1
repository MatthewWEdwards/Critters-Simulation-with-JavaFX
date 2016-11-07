package assignment5;

import assignment5.Critter;
import assignment5.Critter1;
import assignment5.Critter.CritterShape;


public class Critter1 extends Critter {

		
		@Override
		/**
		 *  Representation of Critter1 in worldView, 1
		 */
		public String toString() { return "1"; }
		
		@Override
		/**
		 * Behavior of Critter1 in timeStep, reproduces if able to, changes direction and walks with 50% chance
		 */
		public void doTimeStep() {
			Critter1 babyBunny = new Critter1();
			reproduce(babyBunny, Critter.getRandomInt(8));
			if(Critter.getRandomInt(2) % 2 == 1){
				super.walk(getRandomInt(8));
			}
		}

		@Override
		/**
		 * Behavior of Critter1 in fight,always fights algae, else tries to flee by looking, if occupied, fights
		 * @param opponent, name of other critter this critter is fighting
		 * @return boolean, true if decides to fight, false if not
		 */
		public boolean fight(String opponent) {
			if(opponent.equals("@")){
				return true;
			}
			else{
				int dir = getRandomInt(8);
				String look = this.look(dir, false);
				if(look == null){
					super.walk(dir);
					return false;
				}
				else{
					return true;
				}
			}
		
		}
		
	
	@Override
	public CritterShape viewShape() { return CritterShape.SQUARE; }

	@Override
	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.BLUE; }
	public javafx.scene.paint.Color viewFillColor() { return javafx.scene.paint.Color.BLUE; }
}
