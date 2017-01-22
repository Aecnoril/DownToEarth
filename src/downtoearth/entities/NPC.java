/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.entities;

import downtoearth.enums.DirectionType;
import downtoearth.enums.MobType;
import downtoearth.enums.SpriteLocation;
import shared.Coordinate;
import downtoearth.gameUtil.SpriteManager;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Demian
 * @author Sander
 */
public class NPC extends LivingEntity {

    //<editor-fold defaultstate="collapsed" desc="Fields & properties">
    private final MobType TYPE;
    private final int COUNT;
    private final boolean SHOULD_ATTACK;
    private final int TIME_TO_MOVE_BASED_ON_PLAYER;
    private final int viewingDistance;

    private long startedMovingBasedOnPlayer = System.currentTimeMillis();
    private int viewingAngle; //Max 178 degrees

    private final World WORLD;
    private final Rectangle BOUNDS;
    private final SpriteManager SMANAGER;
    private byte dir;
    private boolean moving;

    private Rectangle colBox;

    public Rectangle getColBox() {
        return colBox;
    }

    /**
     * Get the value of BOUNDS
     *
     * @return the value of BOUNDS
     */
    public Rectangle getBounds() {
        return BOUNDS;
    }

    /**
     * Gets the value of the mob type
     *
     * @return returns the type as a MobType enum
     */
    public MobType getType() {
        return TYPE;
    }

    public byte getDir() {
        return dir;
    }

    //</editor-fold>
    public NPC(String name, Coordinate location, int hitPoints, MobType type, String path, World world, boolean SHOULD_ATTACK, int viewingAngle, int viewingDistance, int TIME_TO_MOVE_BASED_ON_PLAYER) throws SlickException {
        super(name, location, hitPoints, path);
        this.COUNT = 0;
        this.TYPE = type;
        this.BOUNDS = new Rectangle(location.getXint() + 2, location.getYint() + 2, 32, 32);
        this.dir = DirectionType.SOUTH;
        this.SMANAGER = new SpriteManager(path);
        this.moving = true;
        this.WORLD = world;
        this.SHOULD_ATTACK = SHOULD_ATTACK;
        this.viewingAngle = viewingAngle;
        this.viewingDistance = viewingDistance;
        this.TIME_TO_MOVE_BASED_ON_PLAYER = TIME_TO_MOVE_BASED_ON_PLAYER;
    }

    private void setRandomMovingDirection() {
        int randomMoveStateChange = new Random().nextInt(250);
        if ((!moving && randomMoveStateChange != 1) || (moving && randomMoveStateChange == 1)) {
            moving = !moving;
        }
        int randomDirChange = new Random().nextInt(125);
        if (randomDirChange == 1) {
            int randomDir = new Random().nextInt(8) + 1;
            int difference = randomDir - (int) dir;
            if (difference < 0) {
                difference = difference * -1;
            }
            if (difference > 2) {
                randomDir += 2;
                if (randomDir > 8) {
                    randomDir -= 4;
                }
            }
            dir = (byte) randomDir;
        }
    }

    private boolean isInRange(double playerX, double playerY) {
        if (viewingAngle > 178) {
            viewingAngle = 178;
        }
        float ownX = this.location.getX();
        float ownY = this.location.getY();

        if (dir == DirectionType.NORTH || dir == DirectionType.NORTHEAST || dir == DirectionType.NORTHWEST) {
            double alpha = viewingAngle / 2;
            double x = Math.sin(alpha) * viewingDistance;
            double y = Math.cos(alpha) * viewingDistance;
            double leftYX = y / (x * -1);
            double rightYX = y / x;

            if (playerY <= ownY + (playerX - ownX) * leftYX
                    && playerY <= ownY + (playerX - ownX) * rightYX) {
                double distance = Math.sqrt(Math.pow(Math.abs(ownX - playerX), 2) + Math.pow(Math.abs(ownY - playerY), 2));
                if (distance <= viewingDistance) {
//                    System.out.println("Player in view! (North)");
                    return true;
                }
            }
        } else if (dir == DirectionType.SOUTH || dir == DirectionType.SOUTHEAST || dir == DirectionType.SOUTHWEST) {
            double alpha = viewingAngle / 2;
            double x = Math.sin(alpha) * viewingDistance;
            double y = Math.cos(alpha) * viewingDistance;
            double leftYX = (y * -1) / (x * -1);
            double rightYX = (y * -1) / x;

            if (playerY >= ownY + (playerX - ownX) * leftYX
                    && playerY >= ownY + (playerX - ownX) * rightYX) {
                double distance = Math.sqrt(Math.pow(Math.abs(ownX - playerX), 2) + Math.pow(Math.abs(ownY - playerY), 2));
                if (distance <= viewingDistance) {
//                    System.out.println("Player in view! (South)");
                    return true;
                }
            }
        } else if (dir == DirectionType.WEST) {
            double alpha = viewingAngle / 2;
            double y = Math.sin(alpha) * viewingDistance;
            double x = Math.cos(alpha) * viewingDistance;
            double bottomXY = (x * -1) / (y * -1);
            double topXY = x / (y * -1);

            if (playerX <= ownX + (playerY - ownY) * bottomXY
                    && playerX <= ownX + (playerY - ownY) * topXY) {
                double distance = Math.sqrt(Math.pow(Math.abs(ownX - playerX), 2) + Math.pow(Math.abs(ownY - playerY), 2));
                if (distance <= viewingDistance) {
//                    System.out.println("Player in view! (West)");
                    return true;
                }
            }
        } else if (dir == DirectionType.EAST) {
            double alpha = viewingAngle / 2;
            double y = Math.sin(alpha) * viewingDistance;
            double x = Math.cos(alpha) * viewingDistance;
            double topXY = (x * -1) / (y * -1);
            double bottomXY = x / (y * -1);

            if (playerX >= ownX + (playerY - ownY) * bottomXY
                    && playerX >= ownX + (playerY - ownY) * topXY) {
                double distance = Math.sqrt(Math.pow(Math.abs(ownX - playerX), 2) + Math.pow(Math.abs(ownY - playerY), 2));
                if (distance <= viewingDistance) {
//                    System.out.println("Player in view! (East)");
                    return true;
                }
            }
        }
        return false;
    }

    private Opponent getContestantInRange(List<Opponent> opponents) {
        for (Opponent opponent : opponents) {
            if (isInRange(opponent.getLocation().getX(), opponent.getLocation().getY())) {
                return opponent;
            }
        }
        return null;
    }

    private Player getPlayerInRange(Player player) {
        if (isInRange(player.getCamX(), player.getCamY())) {
            return player;
        }
        return null;
    }

    public void move(Input input, List<Tile> tiles, List<NPC> entities, List<Opponent> opponents) throws SlickException {
        double inRangePlayerX = -1;
        double inRangePlayerY = -1;
        Player inRangePlayer = getPlayerInRange(WORLD.getPlayer());
        if (inRangePlayer == null && opponents != null) {
            Opponent inRangeContestant = getContestantInRange(opponents);
            if (inRangeContestant != null) {
                inRangePlayerX = inRangeContestant.getLocation().getX();
                inRangePlayerY = inRangeContestant.getLocation().getY();
            }
        } else if (inRangePlayer != null) {
            inRangePlayerX = inRangePlayer.getCamX();
            inRangePlayerY = inRangePlayer.getCamY();
        }

        if (inRangePlayerX != -1
                && ((startedMovingBasedOnPlayer > 0
                && (System.currentTimeMillis() - startedMovingBasedOnPlayer) < TIME_TO_MOVE_BASED_ON_PLAYER)
                || (startedMovingBasedOnPlayer < 0
                && (System.currentTimeMillis() - -startedMovingBasedOnPlayer) > (TIME_TO_MOVE_BASED_ON_PLAYER * 0.1)))) {
            if (startedMovingBasedOnPlayer < 0) {
                System.out.println("Started moving based on player");
                startedMovingBasedOnPlayer = System.currentTimeMillis();
            }
            moving = true;
            float ownX = this.location.getX();
            float ownY = this.location.getY();
            if (SHOULD_ATTACK) {
                if ((inRangePlayerX - ownX) > 2) {
                    dir = DirectionType.EAST;
                    if ((inRangePlayerY - ownY) > 2) {
                        dir = DirectionType.SOUTHEAST;
                    } else if ((inRangePlayerY - ownY) < -2) {
                        dir = DirectionType.NORTHEAST;
                    }
                } else if ((inRangePlayerX - ownX) < -2) {
                    dir = DirectionType.WEST;
                    if ((inRangePlayerY - ownY) > 2) {
                        dir = DirectionType.SOUTHWEST;
                    } else if ((inRangePlayerY - ownY) < -2) {
                        dir = DirectionType.NORTHWEST;
                    }
                } else if ((inRangePlayerY - ownY) > 2) {
                    dir = DirectionType.SOUTH;
                } else {
                    dir = DirectionType.NORTH;
                }
            } else if ((inRangePlayerX - ownX) > 2) {
                dir = DirectionType.WEST;
                if ((inRangePlayerY - ownY) > 2) {
                    dir = DirectionType.NORTHWEST;
                } else if ((inRangePlayerY - ownY) < -2) {
                    dir = DirectionType.SOUTHWEST;
                }
            } else if ((inRangePlayerX - ownX) < -2) {
                dir = DirectionType.EAST;
                if ((inRangePlayerY - ownY) > 2) {
                    dir = DirectionType.NORTHEAST;
                } else if ((inRangePlayerY - ownY) < -2) {
                    dir = DirectionType.SOUTHEAST;
                }
            } else if ((inRangePlayerY - ownY) > 2) {
                dir = DirectionType.NORTH;
            } else {
                dir = DirectionType.SOUTH;
            }
        } else {
            if (startedMovingBasedOnPlayer > 0) {
                System.out.println("Stoped moving based on player");
                startedMovingBasedOnPlayer = -System.currentTimeMillis();
            }
            setRandomMovingDirection();
        }

        if (moving) {
            float xa = 0;
            float ya = 0;

            switch (dir) {
                case DirectionType.NORTH:
                    ya = SPEED * -1;
                    break;
                case DirectionType.NORTHEAST:
                    ya = SPEED * -1;
                    xa = SPEED;
                    break;
                case DirectionType.EAST:
                    xa = SPEED;
                    break;
                case DirectionType.SOUTHEAST:
                    ya = SPEED;
                    xa = SPEED;
                    break;
                case DirectionType.SOUTH:
                    ya = SPEED;
                    break;
                case DirectionType.SOUTHWEST:
                    ya = SPEED;
                    xa = SPEED * -1;
                    break;
                case DirectionType.WEST:
                    xa = SPEED * -1;
                    break;
                case DirectionType.NORTHWEST:
                    ya = SPEED * -1;
                    xa = SPEED * -1;
            }

            if (!collision(tiles, entities, opponents)) {
                float newX = this.location.getX() + xa;
                if (xa != 0 && newX >= 0 && newX <= this.WORLD.getMapSize().width) {
                    this.location.setX(newX);
                }
                float newY = this.location.getY() + ya;
                if (ya != 0 && newY >= 0 && newY <= this.WORLD.getMapSize().height) {
                    this.location.setY(newY);
                }
            }
        }
    }

    public boolean collision(List<Tile> tiles, List<NPC> entities, List<Opponent> opponents) throws SlickException {
        switch (dir) {
            case DirectionType.NORTH:
            case DirectionType.NORTHEAST:
            case DirectionType.NORTHWEST:
                colBox = new Rectangle(540 - 13, 360 - 14, 26, 1);
                break;

            case DirectionType.EAST:
                colBox = new Rectangle(540 + 14, 360 - 13, 1, 26);
                break;

            case DirectionType.SOUTH:
            case DirectionType.SOUTHEAST:
            case DirectionType.SOUTHWEST:
                colBox = new Rectangle(540 - 13, 360 + 14, 26, 1);
                break;

            case DirectionType.WEST:
                colBox = new Rectangle(540 - 16, 360 - 13, 1, 26);
                break;
        }
        for (Tile tile : tiles) {
            if (this.getColBox().intersects(tile.getBounds())) {
                return true;
            }
        }
        for (NPC npc : entities) {
            if (this.getColBox().intersects(npc.getBounds())) {
                return true;
            }
        }
        for (Opponent opponent : opponents) {
            if (this.getColBox().intersects(opponent.getBounds())) {
                return true;
            }
        }
        return false;
    }

    public void draw(int posX, int posY) {
        byte spriteDir = dir;
        switch (spriteDir) {
            case DirectionType.NORTHEAST:
            case DirectionType.NORTHWEST:
                spriteDir = DirectionType.NORTH;
                break;
            case DirectionType.SOUTHEAST:
            case DirectionType.SOUTHWEST:
                spriteDir = DirectionType.SOUTH;
                break;
        }
        SpriteLocation pos = DirectionType.getStandingSprite(spriteDir);
        BOUNDS.setX(location.getX() - 16 - posX);
        BOUNDS.setY(location.getY() - 16 - posY);
        SMANAGER.drawSprite(pos.getSpriteX(), pos.getSpriteY(), location.getXint() - posX - 16, location.getYint() - posY - 16);
    }
}
