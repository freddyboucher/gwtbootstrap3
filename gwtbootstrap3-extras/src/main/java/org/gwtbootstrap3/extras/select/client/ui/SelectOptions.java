package org.gwtbootstrap3.extras.select.client.ui;

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

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.JsPropertyMap;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.Styles;

/**
 * This class represents select options, that you can use to
 * customize the select picker.
 *
 * @author Xiaodong Sun
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class SelectOptions implements JsPropertyMap {

  @JsOverlay
  private static final String DATA_PREFIX = "data-";

  // Select (<select data-xx="xx">) options
  @JsOverlay
  static final String ACTIONS_BOX = DATA_PREFIX + "actions-box";
  @JsOverlay
  static final String CONTAINER = DATA_PREFIX + "container";
  @JsOverlay
  static final String DESELECT_ALL_TEXT = DATA_PREFIX + "deselect-all-text";
  @JsOverlay
  static final String DROPDOWN_ALIGN_RIGHT = DATA_PREFIX + "dropdown-align-right";
  @JsOverlay
  static final String DROPUP_AUTO = DATA_PREFIX + "dropup-auto";
  @JsOverlay
  static final String HEADER = DATA_PREFIX + "header";
  @JsOverlay
  static final String HIDE_DISABLED = DATA_PREFIX + "hide-disabled";
  @JsOverlay
  static final String LIVE_SEARCH = DATA_PREFIX + "live-search";
  @JsOverlay
  static final String LIVE_SEARCH_NORMALIZE = DATA_PREFIX + "live-search-normalize";
  @JsOverlay
  static final String LIVE_SEARCH_PLACEHOLDER = DATA_PREFIX + "live-search-placeholder";
  @JsOverlay
  static final String LIVE_SEARCH_STYLE = DATA_PREFIX + "live-search-style";
  @JsOverlay
  static final String MAX_OPTIONS = DATA_PREFIX + "max-options";
  @JsOverlay
  static final String MOBILE = DATA_PREFIX + "mobile";
  @JsOverlay
  static final String MULTIPLE_SEPARATOR = DATA_PREFIX + "multiple-separator";
  @JsOverlay
  static final String NONE_SELECTED_TEXT = DATA_PREFIX + "none-selected-text";
  @JsOverlay
  static final String SELECT_ALL_TEXT = DATA_PREFIX + "select-all-text";
  @JsOverlay
  static final String SELECTED_TEXT_FORMAT = DATA_PREFIX + "selected-text-format";
  @JsOverlay
  static final String SELECT_ON_TAB = DATA_PREFIX + "select-on-tab";
  @JsOverlay
  static final String SHOW_CONTENT = DATA_PREFIX + "show-content";
  @JsOverlay
  static final String SHOW_ICON = DATA_PREFIX + "show-icon";
  @JsOverlay
  static final String SHOW_SUBTEXT = DATA_PREFIX + "show-subtext";
  @JsOverlay
  static final String SHOW_TICK = DATA_PREFIX + "show-tick";
  @JsOverlay
  static final String SIZE = DATA_PREFIX + "size";
  @JsOverlay
  static final String STYLE = DATA_PREFIX + "style";
  @JsOverlay
  static final String STYLE_BASE = DATA_PREFIX + "style-base";
  @JsOverlay
  static final String TITLE = DATA_PREFIX + "title";
  @JsOverlay
  static final String WIDTH = DATA_PREFIX + "width";
  @JsOverlay
  static final String WINDOW_PADDING = DATA_PREFIX + "window-padding";

  // Option (<option data-xx="xx">) options
  @JsOverlay
  static final String DIVIDER = DATA_PREFIX + "divider";
  @JsOverlay
  static final String SUBTEXT = DATA_PREFIX + "subtext";
  @JsOverlay
  static final String ICON = DATA_PREFIX + "icon";
  @JsOverlay
  static final String TOKENS = DATA_PREFIX + "tokens";
  @JsOverlay
  static final String CONTENT = DATA_PREFIX + "content";
  @JsOverlay
  static final String HIDDEN = DATA_PREFIX + "hidden";

  public String iconBase;
  public String tickIcon;
  public CountSelectedTextHandler countSelectedText;
  public MaxOptionsTextHandler maxOptionsText;

  /**
   * Creates a new instance of {@link SelectOptions}.
   *
   * @return a new instance of {@link SelectOptions}.
   */
  @JsOverlay
  static SelectOptions newOptions() {
    SelectOptions options = new SelectOptions();
    options.iconBase = Styles.FONT_AWESOME_BASE;
    options.tickIcon = IconType.CHECK.getCssName();
    return options;
  }
}
