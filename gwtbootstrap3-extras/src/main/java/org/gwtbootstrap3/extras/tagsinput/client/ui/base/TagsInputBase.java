package org.gwtbootstrap3.extras.tagsinput.client.ui.base;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2016 GwtBootstrap3
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

import static org.gwtbootstrap3.client.shared.js.JQuery.$;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;
import org.gwtbootstrap3.client.shared.js.JQuery;
import org.gwtbootstrap3.client.ui.gwt.Widget;
import org.gwtbootstrap3.extras.tagsinput.client.callback.ItemTextCallback;
import org.gwtbootstrap3.extras.tagsinput.client.callback.ItemValueCallback;
import org.gwtbootstrap3.extras.tagsinput.client.callback.OnTagExistsCallback;
import org.gwtbootstrap3.extras.tagsinput.client.callback.TagClassCallback;
import org.gwtbootstrap3.extras.tagsinput.client.event.BeforeItemAddEvent;
import org.gwtbootstrap3.extras.tagsinput.client.event.BeforeItemAddHandler;
import org.gwtbootstrap3.extras.tagsinput.client.event.BeforeItemRemoveEvent;
import org.gwtbootstrap3.extras.tagsinput.client.event.BeforeItemRemoveHandler;
import org.gwtbootstrap3.extras.tagsinput.client.event.ItemAddedEvent;
import org.gwtbootstrap3.extras.tagsinput.client.event.ItemAddedHandler;
import org.gwtbootstrap3.extras.tagsinput.client.event.ItemAddedOnInitEvent;
import org.gwtbootstrap3.extras.tagsinput.client.event.ItemAddedOnInitHandler;
import org.gwtbootstrap3.extras.tagsinput.client.event.ItemRemovedEvent;
import org.gwtbootstrap3.extras.tagsinput.client.event.ItemRemovedHandler;
import org.gwtbootstrap3.extras.typeahead.client.base.Dataset;
import org.gwtbootstrap3.extras.typeahead.client.ui.Typeahead;

/**
 * Wrapper for Bootstrap Tags Input component.
 * <p>
 * Type T represents type of the tags. Tags can be objects or in special case strings.
 * If tags are strings, no special handling is necessary. However, if tags are objects,
 * it is required to set in options 'itemValue' and 'itemText'. If 'itemText' is not
 * set, 'itemValue' is used instead.
 *
 * @author Marko Nikolić <marko.nikolic@iten.rs>
 */
class TagsInputBase<T> extends Widget implements HasAllTagsInputEvents<T>, HasChangeHandlers {

  @SuppressWarnings("unchecked")
  private TagsInputOptions options = new TagsInputOptions();

  private Collection<? extends Dataset<T>> datasets;

  private Typeahead<T> typeahead;
  private ScheduledCommand attachTypeahead = new ScheduledCommand() {
    @Override
    public void execute() {
      typeahead = new Typeahead<>(input(), datasets);
      typeahead.reconfigure();
      typeahead.addTypeaheadSelectedHandler(event -> add(event.getSuggestion().getData()));
    }
  };

  public void setDatasets(Dataset<T> dataset) {
    datasets = Arrays.asList(dataset);
  }

  public void setDatasets(Collection<? extends Dataset<T>> datasets) {
    this.datasets = datasets;
  }

  /**
   * Sets classname for the tags.
   *
   * @param tagClass Classname for the tags
   */
  public void setTagClass(String tagClass) {
    options.tagClass = tagClass;
  }

  public void setTagClass(TagClassCallback<T> cb) {
    options.tagClass = cb;
  }

  /**
   * Sets item value name that will be used if tags are objects other then String.
   *
   * @param itemValue name of field used for the tag value
   */
  public void setItemValue(String itemValue) {
    options.itemValue = itemValue;
  }

  public void setItemValue(ItemValueCallback<T> cb) {
    options.itemValue = cb;
  }

  /**
   * Sets item text name that will be used if tags are objects other then String.
   * If it item text name is not provided, item value will be used instead.
   *
   * @param itemText name of field used for the tag text
   */
  public void setItemText(String itemText) {
    options.itemText = itemText;
  }

  public void setItemText(ItemTextCallback<T> cb) {
    options.itemText = cb;
  }

  /**
   * Array of keycodes which will add a tag when typing in the input.
   * (default: [13, 188], which are ENTER and comma)
   *
   * @param confirmKeys Array of keycodes
   */
  public void setConfirmKeys(List<Integer> confirmKeys) {
    elemental2.core.JsArray<Integer> keys = new elemental2.core.JsArray<>();

    for (int key : confirmKeys) {
      keys.push(key);
    }
    options.confirmKeys = keys;
  }

  /**
   * When set, no more than the given number of tags are allowed to add (default: undefined).
   * When maxTags is reached, a class 'bootstrap-tagsinput-max' is placed on the tagsinput element.
   *
   * @param maxTags max number of tags
   */
  public void setMaxTags(int maxTags) {
    options.maxTags = maxTags;
  }

  /**
   * Defines the maximum length of a single tag. (default: undefined)
   *
   * @param maxChars max number of chars
   */
  public void setMaxChars(int maxChars) {
    options.maxChars = maxChars;
  }

  /**
   * When true, automatically removes all whitespace around tags. (default: false)
   *
   * @param trimValue
   */
  public void setTrimValue(boolean trimValue) {
    options.trimValue = trimValue;
  }

  /**
   * When true, the same tag can be added multiple times. (default: false)
   *
   * @param allowDuplicates
   */
  public void setAllowDuplicaties(boolean allowDuplicates) {
    options.allowDuplicates = allowDuplicates;
  }

  /**
   * When the input container has focus, the class specified by this config
   * option will be applied to the container.
   *
   * @param focusClass classname
   */
  public void setFocusClass(String focusClass) {
    options.focusClass = focusClass;
  }

  /**
   * Callback called when adding tag that already exists.
   *
   * @param callback callback method.
   */
  public void onTagExists(OnTagExistsCallback<T> callback) {
    options.onTagExists = callback;
  }

  @Override
  public HandlerRegistration addItemAddedOnInitHandler(ItemAddedOnInitHandler<T> handler) {
    return addHandler(handler, ItemAddedOnInitEvent.getType());
  }

  @Override
  public HandlerRegistration addBeforeItemAddHandler(BeforeItemAddHandler<T> handler) {
    return addHandler(handler, BeforeItemAddEvent.getType());
  }

  @Override
  public HandlerRegistration addItemAddedHandler(ItemAddedHandler<T> handler) {
    return addHandler(handler, ItemAddedEvent.getType());
  }

  @Override
  public HandlerRegistration addBeforeItemRemoveHandler(BeforeItemRemoveHandler<T> handler) {
    return addHandler(handler, BeforeItemRemoveEvent.getType());
  }

  @Override
  public HandlerRegistration addItemRemovedHandler(ItemRemovedHandler<T> handler) {
    return addHandler(handler, ItemRemovedEvent.getType());
  }

  @Override
  public HandlerRegistration addChangeHandler(ChangeHandler handler) {
    return addDomHandler(handler, ChangeEvent.getType());
  }

  public void reconfigure() {
    destroy();
    initialize(getElement(), options);

    // Deferred to make sure the tagsinput component creates <input> field
    // on which typeahead should attach.
    Scheduler.get().scheduleDeferred(attachTypeahead);
  }

  /**
   * Initialises tags input component with given options.
   *
   * @param e       tags input element
   * @param options tags input options
   */
  private void initialize(Element e, JsPropertyMap options) {
    $(e).on(HasAllTagsInputEvents.ITEM_ADDED_ON_INIT_EVENT,
        event -> ItemAddedOnInitEvent.fire(this, (T) Js.asPropertyMap(event).get("item")));

    $(e).tagsinput(options);

    $(e).on(HasAllTagsInputEvents.BEFORE_ITEM_ADD_EVENT, event -> BeforeItemAddEvent.fire(this, (T) Js.asPropertyMap(event).get("item")));

    $(e).on(HasAllTagsInputEvents.ITEM_ADDED_EVENT, event -> ItemAddedEvent.fire(this, (T) Js.asPropertyMap(event).get("item")));

    $(e).on(HasAllTagsInputEvents.BEFORE_ITEM_REMOVE_EVENT,
        event -> BeforeItemRemoveEvent.fire(this, (T) Js.asPropertyMap(event).get("item")));

    $(e).on(HasAllTagsInputEvents.ITEM_REMOVED_EVENT, event -> ItemRemovedEvent.fire(this, (T) Js.asPropertyMap(event).get("item")));

    // 'change' event does not work properly if fired from jQuery and it is not cached by GWT. Workaround to make it working properly
    // is to have at least one function assigned to the 'change' event.
    //
    // Probably related to the issue https://github.com/jquery/jquery/issues/1783.
    //
    // Even if firing of ValueChangeEvent is removed, there should remain empty function and 'change' event will be properly cached by GWT.
    $(e).on(HasAllTagsInputEvents.ITEM_CHANGED_EVENT, event -> {
      Object currentValue = $(e).val();

      if (JQuery.isArray(currentValue)) {
        ValueChangeEvent.fire((HasValueChangeHandlers<List<String>>) this, toMultiValue(Js.cast(currentValue)));
      } else {
        ValueChangeEvent.fire((HasValueChangeHandlers<String>) this, (String) currentValue);
      }
    });
  }

  protected static List<String> toMultiValue(JavaScriptObject js_multi_value) {
    List<String> retValue = new ArrayList<>();

    if (js_multi_value != null) {
      JsArrayString js_string_array = js_multi_value.cast();

      for (int i = 0; i < js_string_array.length(); i++) {
        retValue.add(js_string_array.get(i));
      }
    }

    return retValue;
  }

  @Override
  protected void onLoad() {
    super.onLoad();

    initialize(getElement(), options);

    // Deferred to make sure the tagsinput component creates <input> field
    // on which typeahead should attach.
    Scheduler.get().scheduleDeferred(attachTypeahead);
  }

  @Override
  protected void onUnload() {
    super.onUnload();

    command(getElement(), TagsInputCommand.DESTROY);
  }

  /**
   * Returns list of items contained in the
   *
   * @return List of items
   */
  public List<T> getItems() {
    JsArray<JavaScriptObject> js_items = getItems(getElement());
    List<T> items = new ArrayList<>();

    for (int i = 0; i < js_items.length(); i++) {
      @SuppressWarnings("unchecked") T item = (T) js_items.get(i);
      items.add(item);
    }

    return items;
  }

  /**
   * Adds a tag
   *
   * @param tag tag to add
   */
  public void add(T tag) {
    if (isAttached()) {
      add(getElement(), tag);
    }
  }

  /**
   * Adds list of tags
   *
   * @param tags list of tags to add
   */
  public void add(List<T> tags) {
    if (tags != null) {
      for (T tag : tags) {
        add(tag);
      }
    }
  }

  /**
   * Removes a tag
   *
   * @param tag tag to remove
   */
  public void remove(T tag) {
    if (isAttached()) {
      remove(getElement(), tag);
    }
  }

  /**
   * Removes all tags
   */
  public void removeAll() {
    if (isAttached()) {
      command(getElement(), TagsInputCommand.REMOVE_ALL);
    }
  }

  /**
   * Sets focus in the tagsinput
   */
  public void focus() {
    if (isAttached()) {
      command(getElement(), TagsInputCommand.FOCUS);
    }
  }

  /**
   * Returns the tagsinput's internal <input />, which is used for adding tags.
   */
  public Element input() {
    if (isAttached()) {
      return input(getElement());
    } else {
      return null;
    }
  }

  /**
   * Refreshes the tags input UI.
   */
  public void refresh() {
    if (isAttached()) {
      command(getElement(), TagsInputCommand.REFRESH);
    }
  }

  /**
   * Removes tagsinput behaviour
   */
  public void destroy() {
    if (isAttached()) {
      destroy(getElement());
    }
  }

  private void command(Element e, String command) {
    $(e).tagsinput(command);
  }

  private void add(Element e, T tag) {
    $(e).tagsinput(TagsInputCommand.ADD, tag);
  }

  private void remove(Element e, T tag) {
    $(e).tagsinput(TagsInputCommand.REMOVE, tag);
  }

  private Element input(Element e) {
    return $(e).tagsinput(TagsInputCommand.INPUT);
  }

  JavaScriptObject getValue(Element e) {
    return Js.cast($(e).val());
  }

  private JsArray<JavaScriptObject> getItems(Element e) {
    return Js.cast($(e).tagsinput(TagsInputCommand.ITEMS));
  }

  private void destroy(Element e) {
    $(e).off(HasAllTagsInputEvents.ITEM_ADDED_ON_INIT_EVENT);
    $(e).off(HasAllTagsInputEvents.BEFORE_ITEM_ADD_EVENT);
    $(e).off(HasAllTagsInputEvents.ITEM_ADDED_EVENT);
    $(e).off(HasAllTagsInputEvents.BEFORE_ITEM_REMOVE_EVENT);
    $(e).off(HasAllTagsInputEvents.ITEM_REMOVED_EVENT);

    $(e).tagsinput(TagsInputCommand.DESTROY);
  }
}
