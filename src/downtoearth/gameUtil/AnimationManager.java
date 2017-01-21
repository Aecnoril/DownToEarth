/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package downtoearth.gameUtil;
  
import downtoearth.enums.DirectionType;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import shared.Coordinate;
  
  /**
   *
   * @author Vernoxius
   */
  public class AnimationManager {
      
      private int width, height;
      
      private SpriteSheet northSheet, westSheet, eastSheet, southSheet;
      private SpriteSheet northAtt, eastAtt, westAtt, southAtt;
      private Animation northAnim, westAnim, eastAnim, southAnim;
      private Animation northAttAnim, westAttAnim, eastAttAnim, southAttAnim;
      private byte dir;
      
      public boolean isStopped(){
          if(!northAttAnim.isStopped()){
              return false;
          }
          if(!eastAttAnim.isStopped()){
              return false;
          }
          if(!westAttAnim.isStopped()){
              return false;
          }
          if(!southAttAnim.isStopped()){
              return false;
          }  
          
          return true;
      }
  
      public AnimationManager(int width, int height) throws SlickException {
          this.northSheet = new SpriteSheet("res/northAnim.png", width, height);
          this.northAnim = new Animation(northSheet, 100);
          this.westSheet = new SpriteSheet("res/westAnim.png", width, height);
          this.westAnim = new Animation(westSheet, 100);
          this.eastSheet = new SpriteSheet("res/eastAnim.png", width, height);
          this.eastAnim = new Animation(eastSheet, 100);
          this.southSheet = new SpriteSheet("res/southAnim.png", width, height);
          this.southAnim = new Animation(southSheet, 100);  
          
          this.northAtt = new SpriteSheet("res/swipeNorth.png", width, height);
          this.northAttAnim = new Animation(northAtt, 25);
          this.eastAtt = new SpriteSheet("res/swipeEast.png", width, height);
          this.eastAttAnim = new Animation(eastAtt, 25); 
          this.westAtt = new SpriteSheet("res/swipeWest.png", width, height);
          this.westAttAnim = new Animation(westAtt, 25);
          this.southAtt = new SpriteSheet("res/swipeSouth.png", width, height);
          this.southAttAnim = new Animation(southAtt, 25);
          
          this.width = width;
          this.height = height;
          this.dir = DirectionType.NORTH;
      }
  
      public void DrawAnimation(byte dir, GameContainer con) throws SlickException {
          if(dir == DirectionType.NORTH){
              northAnim.draw(con.getWidth() / 2 - 16, con.getHeight() / 2 - 16);
          }
          if(dir == DirectionType.WEST){
              westAnim.draw(con.getWidth() / 2 - 16, con.getHeight() / 2 - 16);
          }
          if(dir == DirectionType.EAST){
              eastAnim.draw(con.getWidth() / 2 - 16, con.getHeight() / 2 - 16);
          }
          if(dir == DirectionType.SOUTH){
              southAnim.draw(con.getWidth() / 2 - 16, con.getHeight() / 2 - 16);
          }
      }
      
      public void DrawAnimation(byte dir, Coordinate coords) throws SlickException {
          if(dir == DirectionType.NORTH){
              northAnim.draw(coords.getXint(), coords.getYint());
          }
          if(dir == DirectionType.WEST){
              westAnim.draw(coords.getXint(), coords.getYint());
          }
          if(dir == DirectionType.EAST){
              eastAnim.draw(coords.getXint(), coords.getYint());
          }
          if(dir == DirectionType.SOUTH){
              southAnim.draw(coords.getXint(), coords.getYint());
          }
      }
      
      public void DrawAttack(byte dir, GameContainer con) throws SlickException {
        if(dir == DirectionType.NORTH){
            northAttAnim.draw(con.getWidth() / 2 - 16, con.getHeight() / 2 - 32);
        }
        if(dir == DirectionType.EAST){
            eastAttAnim.draw(con.getWidth() / 2, con.getHeight() / 2 - 16);
        }
        if(dir == DirectionType.WEST){
            westAttAnim.draw(con.getWidth() / 2 - 32, con.getHeight() / 2 - 16);
        }
        if(dir == DirectionType.SOUTH){
            southAttAnim.draw(con.getWidth() / 2 - 16, con.getHeight() / 2);
        } 
      }
      
      public void DrawAttack(byte dir, Coordinate coords) throws SlickException {
        if(dir == DirectionType.NORTH){
            northAttAnim.draw(coords.getXint(), coords.getYint());
        }
        if(dir == DirectionType.EAST){
            eastAttAnim.draw(coords.getXint(), coords.getYint());
        }
        if(dir == DirectionType.WEST){
            westAttAnim.draw(coords.getXint(), coords.getYint());
        }
        if(dir == DirectionType.SOUTH){
            southAttAnim.draw(coords.getXint(), coords.getYint());
        } 
      }
  }