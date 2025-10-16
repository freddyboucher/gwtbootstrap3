package org.gwtbootstrap3.extras.select.client.ui;

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

import static org.gwtbootstrap3.extras.select.client.ui.SelectBase.SelectJQuery.$;
import static org.gwtbootstrap3.extras.select.client.ui.SelectOptions.ACTIONS_BOX;
import static org.gwtbootstrap3.extras.select.client.ui.SelectOptions.DESELECT_ALL_TEXT;
import static org.gwtbootstrap3.extras.select.client.ui.SelectOptions.MAX_OPTIONS;
import static org.gwtbootstrap3.extras.select.client.ui.SelectOptions.MULTIPLE_SEPARATOR;
import static org.gwtbootstrap3.extras.select.client.ui.SelectOptions.SELECTED_TEXT_FORMAT;
import static org.gwtbootstrap3.extras.select.client.ui.SelectOptions.SELECT_ALL_TEXT;

import elemental2.core.JsArray;
import java.util.List;
import java.util.stream.Collectors;
import jsinterop.base.Js;
import org.gwtbootstrap3.extras.select.client.ui.constants.SelectedTextFormat;

/**
 * Multiple select box.
 *
 * @author Xiaodong Sun
 */
public class MultipleSelect extends SelectBase<List<String>> {

  private static final String MULTIPLE = "multiple";

  public MultipleSelect() {
    attrMixin.setAttribute(MULTIPLE, "");
  }

  @Override
  public final boolean isMultiple() {
    return true;
  }

  /**
   * When set to <code>true</code>, adds two buttons to the top of
   * the drop-down menu (<b>Select All</b> & <b>Deselect All</b>).<br>
   * <br>
   * Defaults to <code>false</code>.
   *
   * @param showActionsBox
   */
  public void setShowActionsBox(boolean showActionsBox) {
    if (showActionsBox) {
      attrMixin.setAttribute(ACTIONS_BOX, Boolean.toString(true));
    } else {
      attrMixin.removeAttribute(ACTIONS_BOX);
    }
  }

  /**
   * The text on the button that deselects all options when
   * <b>actionsBox</> is enabled.<br>
   * <br>
   * Defaults to <code>Deselect All</code>.
   *
   * @param deselectAllText
   */
  public void setDeselectAllText(String deselectAllText) {
    if (deselectAllText != null) {
      attrMixin.setAttribute(DESELECT_ALL_TEXT, deselectAllText);
    } else {
      attrMixin.removeAttribute(DESELECT_ALL_TEXT);
    }
  }

  /**
   * The text on the button that selects all options when
   * <b>actionsBox</> is enabled.<br>
   * <br>
   * Defaults to <code>Select All</code>.
   *
   * @param selectAllText
   */
  public void setSelectAllText(String selectAllText) {
    if (selectAllText != null) {
      attrMixin.setAttribute(SELECT_ALL_TEXT, selectAllText);
    } else {
      attrMixin.removeAttribute(SELECT_ALL_TEXT);
    }
  }

  /**
   * When set to a positive value and in a multi-select, the
   * number of selected options cannot exceed the given value.
   * When set to a strict negative value (less than zero), this
   * options will be deactivated.
   *
   * @param maxOptions
   */
  public void setMaxOptions(int maxOptions) {
    attrMixin.setAttribute(MAX_OPTIONS, Integer.toString(maxOptions));
  }

  /**
   * Sets the handler to get texts displayed when {@link SelectOptions#MAX_OPTIONS}
   * is enabled and the maximum number of options within the entire select or an
   * option group have been selected.
   *
   * @param handler
   * @see #setMaxOptions(int)
   */
  public void setMaxOptionsTextHandler(MaxOptionsTextHandler handler) {
    options.maxOptionsText = handler;
  }

  /**
   * Sets the character displayed in the button that separates
   * selected options.<br>
   * <br>
   * Defaults to <code>, </code>.
   *
   * @param multipleSeparator
   */
  public void setMultipleSeparator(String multipleSeparator) {
    if (multipleSeparator != null) {
      attrMixin.setAttribute(MULTIPLE_SEPARATOR, multipleSeparator);
    } else {
      attrMixin.removeAttribute(MULTIPLE_SEPARATOR);
    }
  }

  /**
   * Specifies how the selection is displayed with a multiple select.<br>
   * <br>
   * Defaults to {@link SelectedTextFormat#VALUES}.
   *
   * @param format
   * @see SelectedTextFormat
   */
  public void setSelectedTextFormat(SelectedTextFormat format) {
    if (format != null) {
      attrMixin.setAttribute(SELECTED_TEXT_FORMAT, format.getFormat());
    } else {
      attrMixin.removeAttribute(SELECTED_TEXT_FORMAT);
    }
  }

  /**
   * Specifies the minimum count of the <code>count > #</code> selection
   * format with a multiple select.
   *
   * @param minCount
   * @see SelectedTextFormat#getFormat(int)
   */
  public void setCountSelectedTextFormat(int minCount) {
    attrMixin.setAttribute(SELECTED_TEXT_FORMAT, SelectedTextFormat.COUNT.getFormat(minCount));
  }

  @Override
  public List<String> getValue() {
    if (isAttached()) {
      return Js.cast($(this).selectpicker(SelectCommand.VAL).asArrayLike().asList());
    }
    return getSelectedValues();
  }

  private List<String> getSelectedValues() {
    return getItems().stream().filter(Option::isSelected).map(Option::getValue).collect(Collectors.toList());
  }

  @Override
  protected void setSelectedValue(List<String> value) {
    if (isAttached()) {
      $(this).selectpicker(SelectCommand.VAL, JsArray.of(value.toArray(new String[0])));
    } else {
      getItems().forEach(item -> item.setSelected(value.contains(item.getValue())));
    }
  }

  /**
   * Returns the selected items list. If no item is selected, this method
   * returns an empty list.
   *
   * @return the selected items list
   */
  public List<Option> getSelectedItems() {
    return getItems().stream().filter(Option::isSelected).collect(Collectors.toList());
  }

  /**
   * Select all items in a multi-select.
   */
  public void selectAll() {
    setSelectAll(true);
  }

  /**
   * Deselect all items in a multi-select.
   */
  public void deselectAll() {
    setSelectAll(false);
  }

  private void setSelectAll(boolean selected) {
    if (isAttached()) {
      $(this).selectpicker(selected ? SelectCommand.SELECT_ALL : SelectCommand.DESELECT_ALL);
    } else {
      getItems().forEach(item -> item.setSelected(selected));
    }
  }
}
