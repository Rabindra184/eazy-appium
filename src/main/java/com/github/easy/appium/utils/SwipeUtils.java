package com.github.easy.appium.utils;

import static com.github.easy.appium.utils.ErrorHandler.throwError;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.text.MessageFormat.format;
import static java.time.Duration.ofMillis;

import com.github.easy.appium.config.device.DelaySetting;
import com.github.easy.appium.config.enums.Message;
import com.github.easy.appium.config.enums.SwipeDirection;
import com.github.easy.appium.config.enums.SwipeStartPosition;
import io.appium.java_client.TouchAction;
import lombok.Builder;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

/**
 * @author Rabindra Biswal
 * @since 21-Mar-2021
 */
@Builder (builderMethodName = "init", buildMethodName = "prepare")
public final class SwipeUtils<T extends TouchAction<T>> {
    private final T                  actions;
    @Builder.Default
    private final SwipeDirection     direction       = SwipeDirection.DOWN;
    @Builder.Default
    private final int                distancePercent = 20;
    private final Point              elementLocation;
    private final Dimension          elementSize;
    @Builder.Default
    private final int                maxSwipe        = 10;
    private final Dimension          screenSize;
    private final DelaySetting       setting;
    @Builder.Default
    private final SwipeStartPosition startPosition   = SwipeStartPosition.CENTER;
    private final WebElement         targetElement;

    /**
     * @param fromElement Drag element
     * @param toElement Drop `fromElement` to this element
     *
     * @return touch action
     *
     * @author Rabindra Biswal
     * @since Feb 2, 2018 3:25:54 PM
     */
    public T dragTo (final WebElement fromElement, final WebElement toElement) {
        final Point source = fromElement.getLocation ();
        final Point target = toElement.getLocation ();
        return this.actions.press (point (source.getX (), source.getY ()))
            .waitAction (waitOptions (ofMillis (this.setting.getBeforeSwipe ())))
            .moveTo (point (target.getX (), target.getY ()))
            .release ()
            .waitAction (waitOptions (ofMillis (this.setting.getAfterSwipe ())));
    }

    /**
     * @return action
     *
     * @author Wasiq Bhamla
     * @since 21-Mar-2021
     */
    public T swipeTo () {
        T result = swipe ();
        if (this.targetElement != null) {
            boolean found = false;
            for (int index = 0; index < this.maxSwipe; index++) {
                if (this.targetElement.isDisplayed ()) {
                    found = true;
                    break;
                }
                result = swipe ();
            }
            if (!found) {
                final String message = "Element not found even after {0} swipes in {1} direction.";
                throwError (Message.DEVICE_ELEMENT_NOT_DISPLAYED_ERROR, format (message, this.direction, this.maxSwipe));
            }
        }
        return result;
    }

    private Point getStartPoint() {
        int x = 0;
        int y = 0;
        int width = this.screenSize.getWidth();
        int height = this.screenSize.getHeight();
        Point location = new Point(0, 0);

        if (this.elementSize != null) {
            width = this.elementSize.getWidth();
            height = this.elementSize.getHeight();
            location = this.elementLocation;
        }

        switch (this.startPosition) {
            case BOTTOM:
                x = width / 2;
                y = this.elementSize != null && height + location.getY() < this.screenSize.getHeight()
                    ? height
                    : height - 5;
                break;
            case CENTER:
                x = width / 2;
                y = height / 2;
                break;
            case LEFT:
                x = this.elementSize != null && width + location.getX() > 0 ? 0 : 5;
                y = height / 2;
                break;
            case RIGHT:
                x = this.elementSize != null && width + location.getX() < this.screenSize.getWidth()
                    ? width
                    : width - 5;
                y = height / 2;
                break;
            default:
                x = width / 2;
                y = 5;
                break;
        }
        return new Point(location.getX() + x, location.getY() + y);
    }



    private T swipe () {
        final double distance = this.distancePercent / 100.0;
        final Point source = getStartPoint ();
        int endX = source.getX () + (int) (source.getX () * this.direction.getX () * distance);
        int endY = source.getY () + (int) (source.getY () * this.direction.getY () * distance);
        if (this.elementSize != null) {
            endX = source.getX () + (int) (this.elementSize.getWidth () * this.direction.getX () * distance);
            endY = source.getY () + (int) (this.elementSize.getHeight () * this.direction.getY () * distance);
        }
        return this.actions.press (point (source.getX (), source.getY ()))
            .waitAction (waitOptions (ofMillis (this.setting.getBeforeSwipe ())))
            .moveTo (point (endX, endY))
            .release ();
    }
}