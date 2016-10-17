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
      
      private SpriteSheet spriteSheet;
      private Animation animation;
      private byte dir;
  
      public AnimationManager(String path, int width, int height) throws SlickException {
          this.spriteSheet = new SpriteSheet(path, width, height, 0);
          this.width = width;
          this.height = height;
          this.animation = new Animation();
          this.dir = DirectionType.NORTH;
      }
  
      public void Animate(int nrOfFrames, byte dir) throws SlickException {
          this.animation = new Animation();
          animation.setAutoUpdate(true);
          SpriteLocation start = DirectionType.getStartingPos(dir);
          for (int frame = 0; frame < nrOfFrames; frame++) {
              animation.addFrame(spriteSheet.getSprite(start.getSpriteX() + frame, start.getSpriteY()), 200);
          }
      }
  
      public void DrawAnimation(byte dir, GameContainer con) throws SlickException {
          if(dir != this.dir){
              Animate(4, dir);
          }
          animation.draw(con.getWidth() / 2, con.getHeight() / 2);
      }
  }