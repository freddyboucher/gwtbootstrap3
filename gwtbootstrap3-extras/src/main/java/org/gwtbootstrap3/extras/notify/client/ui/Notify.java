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

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import org.gwtbootstrap3.client.shared.js.JQuery;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.extras.notify.client.constants.NotifyPlacement;
import org.gwtbootstrap3.extras.notify.client.constants.NotifyType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class Notify extends JQuery {

  @JsProperty
  public JQuery $ele;

  @JsOverlay
  public static final Notify notify(NotifyOptions options) {
    return notify(options, null);
  }

  @JsMethod(namespace = JsPackage.GLOBAL, name = "jQuery.notifyDefaults")
  public static final native void notifyDefaults(NotifySettings settings);

  @JsMethod(namespace = JsPackage.GLOBAL, name = "jQuery.notify")
  public static final native Notify notify(NotifyOptions options, NotifySettings settings);

  @JsMethod(namespace = JsPackage.GLOBAL, name = "jQuery.notify")
  public static final native Notify notify(String message);

  /**
   * Display Notify with custom title, message, and default settings.
   *
   * @param title   Title to set
   * @param message Message to set
   * @return Displayed Notify for update or hiding.
   */
  @JsOverlay
  public static final Notify notify(String title, String message) {
    NotifyOptions options = new NotifyOptions();
    options.message = message;
    options.title = title;
    return notify(options);
  }

  /**
   * Display Notify with custom title, message, icon, and default settings.
   *
   * @param title   Title to set
   * @param message Message to set
   * @param icon    Icon to set
   * @return Displayed Notify for update or hiding.
   */
  @JsOverlay
  public static final Notify notify(String title, String message, String icon) {
    NotifyOptions options = new NotifyOptions();
    options.message = message;
    options.title = title;
    options.icon = icon;
    return notify(options);
  }

  /**
   * Display Notify with custom title, message, icon, and default settings.
   *
   * @param title    Title to set
   * @param message  Message to set
   * @param iconType IconType to set
   * @return Displayed Notify for update or hiding.
   */
  @JsOverlay
  public static final Notify notify(String title, String message, IconType iconType) {
    return Notify.notify(title, message, Styles.FONT_AWESOME_BASE + " " + iconType.getCssName());
  }

  /**
   * Display Notify with custom title, message, icon, URL, and default settings.
   *
   * @param title   Title to set
   * @param message Message to set
   * @param icon    IconType to set
   * @param url     Url to set
   * @return Displayed Notify for update or hiding.
   */
  @JsOverlay
  public static final Notify notify(String title, String message, String icon, String url) {
    NotifyOptions options = new NotifyOptions();
    options.message = message;
    options.title = title;
    options.icon = icon;
    options.url = url;
    return notify(options);
  }

  /**
   * Display Notify with custom title, message, icon, url and default settings.
   *
   * @param title    Title to set
   * @param message  Message to set
   * @param iconType IconType to set
   * @param url      Url to set
   * @return Displayed Notify for update or hiding.
   */
  @JsOverlay
  public static final Notify notify(String title, String message, IconType iconType, String url) {
    return Notify.notify(title, message, Styles.FONT_AWESOME_BASE + " " + iconType.getCssName(), url);
  }

  /**
   * Display Notify with custom message, type and default settings.
   *
   * @param message Message to set
   * @param type    NotifyType
   * @return Displayed Notify for update or hiding.
   * @see NotifyType
   */
  @JsOverlay
  public static final Notify notify(String message, NotifyType type) {
    NotifyOptions options = new NotifyOptions();
    options.message = message;
    NotifySettings settings = new NotifySettings();
    settings.setType(type);
    return notify(options, settings);
  }

  /**
   * Display Notify with custom title, message, type and default settings.
   *
   * @param title   Title to set
   * @param message Message to set
   * @param type    NotifyType
   * @return Displayed Notify for update or hiding.
   * @see NotifyType
   */
  @JsOverlay
  public static final Notify notify(String title, String message, NotifyType type) {
    NotifyOptions options = new NotifyOptions();
    options.message = message;
    options.title = title;
    NotifySettings settings = new NotifySettings();
    settings.setType(type);
    return notify(options, settings);
  }

  /**
   * Display Notify with custom title, message, icon, type and default settings.
   *
   * @param title   Title to set
   * @param message Message to set
   * @param icon    Icon to set
   * @param type    NotifyType
   * @return Displayed Notify for update or hiding.
   * @see NotifyType
   */
  @JsOverlay
  public static final Notify notify(String title, String message, String icon, NotifyType type) {
    NotifyOptions options = new NotifyOptions();
    options.message = message;
    options.title = title;
    options.icon = icon;
    NotifySettings settings = new NotifySettings();
    settings.setType(type);
    return notify(options, settings);
  }

  /**
   * Display Notify with custom title, message, icon, type and default settings.
   *
   * @param title    Title to set
   * @param message  Message to set
   * @param iconType IconType to set (css name of icon form FONT AWESOME)
   * @param type     NotifyType
   * @return Displayed Notify for update or hiding.
   * @see NotifyType
   */
  @JsOverlay
  public static final Notify notify(String title, String message, IconType iconType, NotifyType type) {
    return Notify.notify(title, message, Styles.FONT_AWESOME_BASE + " " + iconType.getCssName(), type);
  }

  /**
   * Display Notify with custom title, message, icon, url, type and default settings.
   *
   * @param title   Title to set
   * @param message Message to set
   * @param icon    Icon to set
   * @param url     Url to set
   * @param type    NotifyType
   * @return Displayed Notify for update or hiding.
   * @see NotifyType
   */
  @JsOverlay
  public static final Notify notify(String title, String message, String icon, String url, NotifyType type) {
    NotifyOptions options = new NotifyOptions();
    options.message = message;
    options.title = title;
    options.icon = icon;
    options.url = url;
    NotifySettings settings = new NotifySettings();
    settings.setType(type);
    return notify(options, settings);
  }

  /**
   * Display Notify with custom title, message, icon, url, type and default settings.
   *
   * @param title    Title to set
   * @param message  Message to set
   * @param iconType IconType to set (css name of icon form FONT AWESOME)
   * @param url      Url to set
   * @param type     NotifyType
   * @return Displayed Notify for update or hiding.
   * @see NotifyType
   */
  @JsOverlay
  public static final Notify notify(String title, String message, IconType iconType, String url, NotifyType type) {
    return Notify.notify(title, message, Styles.FONT_AWESOME_BASE + " " + iconType.getCssName(), url, type);
  }

  /**
   * Display Notify with custom message and custom settings.
   *
   * @param message  Message to set
   * @param settings custom settings
   * @return Displayed Notify for update or hiding.
   * @see NotifySettings
   */
  @JsOverlay
  public static final Notify notify(String message, NotifySettings settings) {
    NotifyOptions options = new NotifyOptions();
    options.message = message;
    return notify(options, settings);
  }

  /**
   * Display Notify with custom title, message and custom settings.
   *
   * @param title    Title to set
   * @param message  Message to set
   * @param settings custom settings
   * @return Displayed Notify for update or hiding.
   * @see NotifySettings
   */
  @JsOverlay
  public static final Notify notify(String title, String message, NotifySettings settings) {
    NotifyOptions options = new NotifyOptions();
    options.message = message;
    options.title = title;
    return notify(options, settings);
  }

  /**
   * Display Notify with custom title, message, icon and custom settings.
   *
   * @param title    Title to set
   * @param message  Message to set
   * @param icon     Icon to set
   * @param settings custom settings
   * @return Displayed Notify for update or hiding.
   * @see NotifySettings
   */
  @JsOverlay
  public static final Notify notify(String title, String message, String icon, NotifySettings settings) {
    NotifyOptions options = new NotifyOptions();
    options.message = message;
    options.title = title;
    options.icon = icon;
    return notify(options, settings);
  }

  /**
   * Display Notify with custom title, message, icon and custom settings.
   *
   * @param title    Title to set
   * @param message  Message to set
   * @param iconType IconType to set (css name of icon form FONT AWESOME)
   * @param settings custom settings
   * @return Displayed Notify for update or hiding.
   * @see NotifySettings
   */
  @JsOverlay
  public static final Notify notify(String title, String message, IconType iconType, NotifySettings settings) {
    return notify(title, message, Styles.FONT_AWESOME_BASE + " " + iconType.getCssName(), settings);
  }

  /**
   * Display Notify with custom title, message, icon, URL and custom settings.
   *
   * @param title    Title to set
   * @param message  Message to set
   * @param icon     Icon to set
   * @param url      Url to set
   * @param settings custom settings
   * @return Displayed Notify for update or hiding.
   * @see NotifySettings
   */
  @JsOverlay
  public static final Notify notify(String title, String message, String icon, String url, NotifySettings settings) {
    NotifyOptions options = new NotifyOptions();
    options.message = message;
    options.title = title;
    options.icon = icon;
    options.url = url;
    return notify(options, settings);
  }

  /**
   * Display Notify with custom title, message, icon, URL and custom settings.
   *
   * @param title    Title to set
   * @param message  Message to set
   * @param iconType IconType to set
   * @param url      Url to set
   * @param settings custom settings
   * @return Displayed Notify for update or hiding.
   * @see NotifySettings
   */
  @JsOverlay
  public static final Notify notify(String title, String message, IconType iconType, String url, NotifySettings settings) {
    return notify(title, message, Styles.FONT_AWESOME_BASE + " " + iconType.getCssName(), url, settings);
  }

  /**
   * Hide all displayed Notifies.
   */
  @JsMethod(namespace = JsPackage.GLOBAL, name = "jQuery.notifyClose")
  public static final native void notifyClose();

  @JsMethod(namespace = JsPackage.GLOBAL, name = "jQuery.notifyClose")
  public static final native void notifyClose(String command);

  @JsOverlay
  public static final void hideAll() {
    notifyClose();
  }

  /**
   * Hide all displayed Notifies on specific screen location.
   *
   * @param placement Notify's placement on screen.
   * @see NotifyPlacement
   */
  @JsOverlay
  public static final void hideAll(NotifyPlacement placement) {
    if (placement != null) {
      notifyClose(placement.getPlacement());
    }
  }

  /**
   * Updates title parameter of once displayed Notify.
   *
   * @param title Title to set
   */
  @JsOverlay
  public final void updateTitle(String title) {
    update("title", title);
  }

  /**
   * Updates message parameter of once displayed Notify.
   *
   * @param message Message to set
   */
  @JsOverlay
  public final void updateMessage(String message) {
    update("message", message);
  }

  /**
   * Updates Icon parameter of once displayed Notify.
   *
   * @param icon Icon to set
   */
  @JsOverlay
  public final void updateIcon(String icon) {
    update("icon", icon);
  }

  /**
   * Updates Icon parameter of once displayed Notify.
   * This method is shortcut when using FONT AWESOME iconic font.
   *
   * @param type IconType to get CSS class name to set
   */
  @JsOverlay
  public final void updateIcon(IconType type) {
    if (type != null) {
      updateIcon(Styles.FONT_AWESOME_BASE + " " + type.getCssName());
    }
  }

  /**
   * Update type of once displayed Notify (CSS style class name).
   *
   * @param type one of INFO, WARNING, DANGER, SUCCESS
   * @see NotifyType
   */
  @JsOverlay
  public final void updateType(NotifyType type) {
    if (type != null) {
      updateType(type.getCssName());
    }
  }

  /**
   * Update type of once displayed Notify (CSS style class name).
   * Resulting class name to use is "alert-[type]".
   *
   * @param type CSS class name to set
   */
  @JsOverlay
  private void updateType(String type) {
    update("type", type);
  }

  /**
   * Update URL target of once displayed Notify.
   *
   * @param target URL target to set
   */
  @JsOverlay
  private void updateTarget(String target) {
    update("target", target);
  }

  @JsMethod
  public final native void update(String command, Object any);

  @JsMethod
  public final native void close();
}
