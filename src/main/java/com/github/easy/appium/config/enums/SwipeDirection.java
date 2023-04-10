
package com.github.easy.appium.config.enums;


public enum SwipeDirection {
    /**
     * Swipe up.
     */
    DOWN(0, 1),
    /**
     * Swipe left.
     */
    LEFT(-1, 0),
    /**
     * Swipe right.
     */
    RIGHT(1, 0),
    /**
     * Swipe up.
     */
    UP(0, -1);

    private final int x;
    private final int y;

    /**
     * @author Rabindra Biswal
     * @since Oct 20, 2017 7:56:06 PM
     */
    SwipeDirection(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     * @author Rabindra Biswal
     * @since Oct 20, 2017 7:57:09 PM
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return the y
     * @author Rabindra Biswal
     * @since Oct 20, 2017 7:57:09 PM
     */
    public int getY() {
        return this.y;
    }
}