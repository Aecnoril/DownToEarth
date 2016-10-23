/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.gameUtil;

/**
 *
 * @author Sanko
 */
public class CollisionCheck {
    
        private boolean northCol;

    /**
     * Get the value of northCol
     *
     * @return the value of northCol
     */
    public boolean isNorthCol() {
        return northCol;
    }

    /**
     * Set the value of northCol
     *
     * @param northCol new value of northCol
     */
    public void setNorthCol(boolean northCol) {
        this.northCol = northCol;
    }

        private boolean northEastCol;

    /**
     * Get the value of northEastCol
     *
     * @return the value of northEastCol
     */
    public boolean isNorthEastCol() {
        return northEastCol;
    }

    /**
     * Set the value of northEastCol
     *
     * @param northEastCol new value of northEastCol
     */
    public void setNorthEastCol(boolean northEastCol) {
        this.northEastCol = northEastCol;
    }
    
        private boolean eastCol;

    /**
     * Get the value of eastCol
     *
     * @return the value of eastCol
     */
    public boolean isEastCol() {
        return eastCol;
    }

    /**
     * Set the value of eastCol
     *
     * @param eastCol new value of eastCol
     */
    public void setEastCol(boolean eastCol) {
        this.eastCol = eastCol;
    }

        private boolean southEastCol;

    /**
     * Get the value of southEastCol
     *
     * @return the value of southEastCol
     */
    public boolean isSouthEastCol() {
        return southEastCol;
    }

    /**
     * Set the value of southEastCol
     *
     * @param southEastCol new value of southEastCol
     */
    public void setSouthEastCol(boolean southEastCol) {
        this.southEastCol = southEastCol;
    }

        private boolean southCol;

    /**
     * Get the value of southCol
     *
     * @return the value of southCol
     */
    public boolean isSouthCol() {
        return southCol;
    }

    /**
     * Set the value of southCol
     *
     * @param southCol new value of southCol
     */
    public void setSouthCol(boolean southCol) {
        this.southCol = southCol;
    }

        private boolean southWestCol;

    /**
     * Get the value of southWestCol
     *
     * @return the value of southWestCol
     */
    public boolean isSouthWestCol() {
        return southWestCol;
    }

    /**
     * Set the value of southWestCol
     *
     * @param southWestCol new value of southWestCol
     */
    public void setSouthWestCol(boolean southWestCol) {
        this.southWestCol = southWestCol;
    }

        private boolean westCol;

    /**
     * Get the value of westCol
     *
     * @return the value of westCol
     */
    public boolean isWestCol() {
        return westCol;
    }

    /**
     * Set the value of westCol
     *
     * @param westCol new value of westCol
     */
    public void setWestCol(boolean westCol) {
        this.westCol = westCol;
    }

        private boolean northWestCol;

    /**
     * Get the value of northWestCol
     *
     * @return the value of northWestCol
     */
    public boolean isNorthWestCol() {
        return northWestCol;
    }

    /**
     * Set the value of northWestCol
     *
     * @param northWestCol new value of northWestCol
     */
    public void setNorthWestCol(boolean northWestCol) {
        this.northWestCol = northWestCol;
    }

    public void clearCol()
    {
        northCol = false;
        northEastCol = false;
        eastCol = false;
        southEastCol = false;
        southCol = false;
        southWestCol = false;
        westCol = false;
        northWestCol = false;
    }
    
}
