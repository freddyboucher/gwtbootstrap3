package org.gwtbootstrap3.extras.notify.client.ui;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2015 GwtBootstrap3
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.JsPropertyMap;
import org.gwtbootstrap3.extras.animate.client.ui.constants.Animation;
import org.gwtbootstrap3.extras.notify.client.constants.NotifyIconType;
import org.gwtbootstrap3.extras.notify.client.constants.NotifyPlacement;
import org.gwtbootstrap3.extras.notify.client.constants.NotifyPosition;
import org.gwtbootstrap3.extras.notify.client.constants.NotifyType;
import org.gwtbootstrap3.extras.notify.client.constants.NotifyUrlTarget;
import org.gwtbootstrap3.extras.notify.client.event.NotifyCloseHandler;
import org.gwtbootstrap3.extras.notify.client.event.NotifyClosedHandler;
import org.gwtbootstrap3.extras.notify.client.event.NotifyShowHandler;
import org.gwtbootstrap3.extras.notify.client.event.NotifyShownHandler;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class NotifySettings implements JsPropertyMap {

  public String element;
  public String position;
  public String type;
  public Boolean allow_dismiss;
  public Boolean allow_duplicates;
  public Boolean newest_on_top;
  public Boolean showProgressbar;
  public JsPropertyMap placement;
  public JsPropertyMap offset;
  public Integer spacing;
  public Integer z_index;
  public Integer delay;
  public Integer timer;
  public String url_target;
  public String mouse_over;
  public JsPropertyMap animate;
  public NotifyShowHandler onShow;
  public NotifyShownHandler onShown;
  public NotifyCloseHandler onClose;
  public NotifyClosedHandler onClosed;
  public String icon_type;
  public String template;

  /**
   * Set custom position to the Notify container element. Default is null.
   *
   * @param position one of STATIC, FIXED, RELATIVE, ABSOLUTE, or null
   */
  @JsOverlay
  public final void setPosition(NotifyPosition position) {
    this.position = position != null ? position.getPosition() : null;
  }

  /**
   * Set type of Notify (CSS style class name). Default is INFO.
   *
   * @param type one of INFO, WARNING, DANGER, SUCCESS
   * @see NotifyType
   */
  @JsOverlay
  public final void setType(NotifyType type) {
    this.type = type != null ? type.getCssName() : NotifyType.INFO.getCssName();
  }

  /**
   * Set placement of Notify on screen. Default placement is {@link NotifyPlacement#TOP_RIGHT}.
   *
   * @param placement Notify's placement on screen
   * @see NotifyPlacement
   */
  @JsOverlay
  public final void setPlacement(NotifyPlacement placement) {
    NotifyPlacement notifyPlacement = placement != null ? placement : NotifyPlacement.TOP_RIGHT;
    this.placement = JsPropertyMap.of("from", notifyPlacement.getFrom(), "align", notifyPlacement.getAlign());
  }

  /**
   * Set offset (space between Notify and screen/browser edges) for each axis. Default is 20 PX for both.
   *
   * @param offX Offset for X axis in PX
   * @param offY Offset for Y axis in PX
   */
  @JsOverlay
  public final void setOffset(int offX, int offY) {
    offset = JsPropertyMap.of("x", offX, "y", offY);
  }

  /**
   * Set custom URL target.<br>
   * <br>
   * Defaults to {@link NotifyUrlTarget#BLANK}.
   *
   * @param urlTarget URL target
   */
  @JsOverlay
  public final void setUrlTarget(NotifyUrlTarget urlTarget) {
    url_target = urlTarget != null ? urlTarget.getTarget() : NotifyUrlTarget.BLANK.getTarget();
  }

  /**
   * Pause countdown of display timeout when mouse is hovering above the Notify.
   * Countdown continues (not restarted) if mouse leaves the Notify.
   *
   * @param pauseOnMouseOver TRUE = pause / FALSE = not pause
   */
  @JsOverlay
  public final void setPauseOnMouseOver(boolean pauseOnMouseOver) {
    mouse_over = pauseOnMouseOver ? "pause" : null;
  }

  /**
   * Set Animation to Notify when it enters and exit the screen.
   * <p>
   * Default is enter = Animation.FADE_IN_DOWN, exit = Animation.FADE_OUT_UP
   *
   * @param enter animation style when Notify enters the screen
   * @param exit  animation style when Notify exists the screen
   * @see Animation
   */
  @JsOverlay
  public final void setAnimation(Animation enter, Animation exit) {
    setAnimation(enter != null ? enter.getCssName() : Animation.NO_ANIMATION.getCssName(),
        exit != null ? exit.getCssName() : Animation.NO_ANIMATION.getCssName());
  }

  /**
   * Set custom CSS style for animations of Notify when it enters and exits the screen.
   * You must write your own CSS animation definition.
   *
   * @param enter animation style when Notify enters the screen
   * @param exit  animation style when Notify exists the screen
   */
  @JsOverlay
  public final void setAnimation(String enter, String exit) {
    animate = JsPropertyMap.of("enter", enter, "exit", exit);
  }

  /**
   * Set the Notify's show event handler. The show event fires immediately when
   * the show instance method is called.
   *
   * @param handler
   */
  @JsOverlay
  public final void setShowHandler(NotifyShowHandler handler) {
    onShow = handler != null ? handler : () -> {
    };
  }

  /**
   * Set the Notify's shown event handler. This event is fired when the modal has
   * been made visible to the user (will wait for CSS transitions to complete).
   *
   * @param handler
   */
  @JsOverlay
  public final void setShownHandler(NotifyShownHandler handler) {
    onShown = handler != null ? handler : () -> {
    };
  }

  /**
   * Set the Notify's close event handler. This event is fired immediately when
   * the notification is closing.
   *
   * @param handler
   */
  @JsOverlay
  public final void setCloseHandler(NotifyCloseHandler handler) {
    onClose = handler != null ? handler : () -> {
    };
  }

  /**
   * Set the Notify's closed event handler. This event is fired when the modal
   * has finished closing and is removed from the document (will wait for CSS
   * transitions to complete).
   *
   * @param handler
   */
  @JsOverlay
  public final void setClosedHandler(NotifyClosedHandler handler) {
    onClosed = handler != null ? handler : () -> {
    };
  }

  /**
   * Set icon type you will use for Notify. Default is 'class', which
   * allows to use iconic fonts like FontAwesome.
   * If you want to use images instead of class, set value to "image".<br>
   * <br>
   * Defaults to {@link NotifyIconType#CLASS}.
   *
   * @param iconType the icon type
   * @see NotifyIconType
   */
  @JsOverlay
  public final void setIconType(NotifyIconType iconType) {
    icon_type = iconType != null ? iconType.getType() : NotifyIconType.CLASS.getType();
  }
}
