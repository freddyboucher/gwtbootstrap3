package org.gwtbootstrap3.extras.bootbox.client.options;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2016 GwtBootstrap3
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

import elemental2.dom.Element;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.JsPropertyMap;
import org.gwtbootstrap3.extras.bootbox.client.callback.SimpleCallback;

/**
 * Bootbox dialog options.
 *
 * @author Xiaodong Sun
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class DialogOptions implements JsPropertyMap {

  @JsOverlay
  private static final String BUTTON_PREFIX = "bootbox_btn_";
  @JsOverlay
  private static int BUTTON_INDEX;
  public String message;
  public String title;
  // Default language used when generating buttons for alert, confirm, and prompt dialogs
  public String locale;
  // Show the dialog immediately by default
  public boolean show;
  // Show backdrop or not. Default to static so user has to interact with dialog
  public Boolean backdrop;
  // Whether or not to include a close button
  public boolean closeButton;
  // Animate the modal in/out
  public boolean animate;
  // Additional class string applied to the top level dialog
  public String className;
  // The size of the modal to generate
  public String size;
  public SimpleCallback onEscape;
  public JsPropertyMap<ButtonJs> buttons;
  // Dialog container
  public String container;
  // Default value (used by the prompt helper)
  public String value;
  // Default input type (used by the prompt helper)
  public String inputType;
  // Custom error message to report if prompt fails validation
  public String errorMessage;
  // Switch button order from cancel/confirm (default) to confirm/cancel
  public Boolean swapButtonOrder;
  // Center modal vertically in page
  public Boolean centerVertical;
  // Append "multiple" property to the select when using the "prompt" helper
  public Boolean multiple;
  // Automatically scroll modal content when height exceeds viewport height
  public Boolean scrollable;
  // Whether or not to destroy the modal on hide
  public Boolean reusable;
  // The element which triggered the dialog
  public Object   relatedTarget;
  // A unique identifier for this modal
  public String id;

  @JsOverlay
  public static DialogOptions newOptions(String message) {
    DialogOptions options = new DialogOptions();
    options.message = message;
    return options;
  }

  /**
   * The locale settings used to translate the three standard button
   * labels: <b>OK</b>, <b>CONFIRM</b>, <b>CANCEL</b>.
   *
   * @param locale
   */
  @JsOverlay
  public final void setLocale(BootboxLocale locale) {
    BootboxLocale l = (locale != null) ? locale : BootboxLocale.getDefault();
    this.locale = l.getLocale();
  }

  /**
   * Adds the relevant Bootstrap modal size class to the dialog wrapper.<br>
   * <br>
   * Defaults to <code>null</code>.
   *
   * @param size
   */
  @JsOverlay
  public final void setSize(BootboxSize size) {
    if (size != null) {
      this.size = size.getSize();
    } else {
      this.size = null;
    }
  }

  /**
   * Adds a custom button.
   *
   * @param label
   */
  @JsOverlay
  public final void addButton(String label) {
    addButton(label, (String) null);
  }

  /**
   * Adds a custom button with a class name.
   *
   * @param label
   * @param className
   */
  @JsOverlay
  public final void addButton(String label, String className) {
    addButton(label, className, SimpleCallback.DEFAULT_SIMPLE_CALLBACK);
  }

  /**
   * Adds a custom button with a callback.
   *
   * @param label
   * @param callback
   */
  @JsOverlay
  public final void addButton(String label, SimpleCallback callback) {
    addButton(label, null, callback);
  }

  /**
   * Adds a custom button with a class name and a callback.
   *
   * @param label
   * @param className
   * @param callback
   */
  @JsOverlay
  public final void addButton(String label, String className, SimpleCallback callback) {
    addButton(BUTTON_PREFIX + BUTTON_INDEX++, label, className, callback != null ? callback : SimpleCallback.DEFAULT_SIMPLE_CALLBACK);
  }

  @JsOverlay
  public final void addButton(String name, String label, String className, SimpleCallback callback) {
    if (buttons == null) {
      buttons = JsPropertyMap.of();
    }
    ButtonJs button = new ButtonJs();
    button.label = label;
    button.className = className;
    button.callback = callback;
    buttons.set(name, button);
  }

  @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
  public static class ButtonJs implements JsPropertyMap {
    public String label;
    public SimpleCallback callback;
    public String className;
  }
}
