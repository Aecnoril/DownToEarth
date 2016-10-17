 /*
   * To change this license header, choose License Headers in Project Properties.
   * To change this template file, choose Tools | Templates
   * and open the template in the editor.
   */
  package downtoearth.gameUtil;
  
import downtoearth.enums.DirectionType;
import downtoearth.enums.SpriteLocation;
  import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
  import org.newdawn.slick.SlickException;
  import org.newdawn.slick.SpriteSheet;
  
  /**
   *
   * @author Vernoxius
   */
  public class AnimationManager {
      
      private int width, height;
      
      private SpriteSheet northSheet, westSheet, eastSheet, southSheet;
      private Animation northAnim, westAnim, eastAnim, southAnim;
      private byte dir;
  
      public AnimationManager(int width, int height) throws SlickException {
          this.northSheet = new SpriteSheet("res/northAnim.png", width, height);
          this.northAnim = new Animation(northSheet, 100);
          this.westSheet = new SpriteSheet("res/westAnim.png", width, height);
          this.westAnim = new Animation(westSheet, 100);
          this.eastSheet = new SpriteSheet("res/eastAnim.png", width, height);
          this.eastAnim = new Animation(eastSheet, 100);
          this.southSheet = new SpriteSheet("res/southAnim.png", width, height);
          this.southAnim = new Animation(southSheet, 100);
          this.width = width;
          this.height = height;
          this.dir = DirectionType.NORTH;
      }
  
      public void Animate(int nrOfFrames, byte dir) throws SlickException {

      }
  
      public void DrawAnimation(byte dir, GameContainer con) throws SlickException {
          if(dir == DirectionType.NORTH){
              northAnim.draw(con.getWidth() / 2, con.getHeight() / 2);
          }
          if(dir == DirectionType.WEST){
              westAnim.draw(con.getWidth() / 2, con.getHeight() / 2);
          }
          if(dir == DirectionType.EAST){
              eastAnim.draw(con.getWidth() / 2, con.getHeight() / 2);
          }
          if(dir == DirectionType.SOUTH){
              southAnim.draw(con.getWidth() / 2, con.getHeight() / 2);
          }
      }
  }