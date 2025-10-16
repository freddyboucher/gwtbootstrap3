package org.gwtbootstrap3.extras.slider.client.ui.base;

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

import static org.gwtbootstrap3.extras.slider.client.ui.base.SliderBase.SliderJQuery.$;
import static org.gwtbootstrap3.extras.slider.client.ui.base.SliderBase.SliderJQuery.jQuery;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import elemental2.core.Global;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.JsPropertyMap;
import org.gwtbootstrap3.client.shared.js.JQuery;
import org.gwtbootstrap3.client.ui.base.HasId;
import org.gwtbootstrap3.client.ui.base.HasResponsiveness;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.base.mixin.AttributeMixin;
import org.gwtbootstrap3.client.ui.constants.DeviceSize;
import org.gwtbootstrap3.extras.slider.client.ui.base.constants.HandleType;
import org.gwtbootstrap3.extras.slider.client.ui.base.constants.OrientationType;
import org.gwtbootstrap3.extras.slider.client.ui.base.constants.ScaleType;
import org.gwtbootstrap3.extras.slider.client.ui.base.constants.SelectionType;
import org.gwtbootstrap3.extras.slider.client.ui.base.constants.TooltipPosition;
import org.gwtbootstrap3.extras.slider.client.ui.base.constants.TooltipType;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.HasAllSlideHandlers;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideDisabledEvent;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideDisabledHandler;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideEnabledEvent;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideEnabledHandler;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideEvent;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideHandler;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideStartEvent;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideStartHandler;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideStopEvent;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideStopHandler;

/**
 *
 *
 * @param <T> slider value type
 * @author Xiaodong Sun
 * @see https://github.com/seiyria/bootstrap-slider
 */
public abstract class SliderBase<T> extends Widget
    implements HasValue<T>, IsEditor<LeafValueEditor<T>>, HasEnabled, HasId, HasResponsiveness, HasAllSlideHandlers<T> {

  private FormatterCallback<T> formatterCallback;
  private LeafValueEditor<T> editor;

  private final AttributeMixin<SliderBase<T>> attributeMixin = new AttributeMixin<>(this);

  protected SliderBase() {
    setElement(Document.get().createTextInputElement());
  }

  @Override
  protected void onLoad() {
    super.onLoad();
    JsPropertyMap options = JsPropertyMap.of();
    if (formatterCallback != null) {
      setFormatterOption(options);
    }
    initSlider(getElement(), options);
    bindSliderEvents();
  }

  /**
   * Sets formatter option if defined when attaching to the DOM.
   *
   * @param options
   */
  protected abstract void setFormatterOption(JsPropertyMap options);

  @Override
  protected void onUnload() {
    super.onUnload();
    unbindSliderEvents();
    sliderCommand(getElement(), SliderCommand.DESTROY);
  }

  /**
   * Sets the id of the slider element when it's created.
   */
  @Override
  public void setId(String id) {
    updateSlider(SliderOption.ID, id);
  }

  @Override
  public String getId() {
    return getStringAttribute(SliderOption.ID);
  }

  public double getMin() {
    return getDoubleAttribute(SliderOption.MIN, 0);
  }

  /**
   * Sets the minimum possible value.
   *
   * @param min
   */
  public void setMin(double min) {
    updateSlider(SliderOption.MIN, min);
  }

  public double getMax() {
    return getDoubleAttribute(SliderOption.MAX, 10);
  }

  /**
   * Sets the maximum possible value.
   *
   * @param max
   */
  public void setMax(double max) {
    updateSlider(SliderOption.MAX, max);
  }

  public double getStep() {
    return getDoubleAttribute(SliderOption.STEP, 1);
  }

  /**
   * Sets the increment step.
   *
   * @param step
   */
  public void setStep(double step) {
    updateSlider(SliderOption.STEP, step);
  }

  public double getPrecision() {
    return getDoubleAttribute(SliderOption.PRECISION, 0);
  }

  /**
   * Sets the number of digits shown after the decimal.<br>
   * <br>
   * Defaults to the number of digits after the decimal of step value.
   *
   * @param precision
   */
  public void setPrecision(double precision) {
    updateSlider(SliderOption.PRECISION, precision);
  }

  public OrientationType getOrientation() {
    return getEnumAttribute(SliderOption.ORIENTATION, OrientationType.class, OrientationType.HORIZONTAL);
  }

  /**
   * Sets the orientation.
   *
   * @param orientation
   * @see OrientationType
   */
  public void setOrientation(OrientationType orientation) {
    updateSlider(SliderOption.ORIENTATION, orientation.getType());
  }

  protected boolean isRange() {
    return getBooleanAttribute(SliderOption.RANGE, false);
  }

  /**
   * Make range slider if set to <code>true</code>. If initial value is scalar,
   * max will be used for second value.
   *
   * @param range
   */
  protected void setRange(boolean range) {
    updateSlider(SliderOption.RANGE, range);
  }

  public SelectionType getSelection() {
    return getEnumAttribute(SliderOption.SELECTION, SelectionType.class, SelectionType.BEFORE);
  }

  /**
   * Sets the selection type.
   *
   * @param selection
   * @see SelectionType
   */
  public void setSelection(SelectionType selection) {
    updateSlider(SliderOption.SELECTION, selection.getType());
  }

  public TooltipType getTooltip() {
    return getEnumAttribute(SliderOption.TOOLTIP, TooltipType.class, TooltipType.SHOW);
  }

  /**
   * Sets the tool-tip type.
   *
   * @param tooltip
   * @see TooltipType
   */
  public void setTooltip(TooltipType tooltip) {
    updateSlider(SliderOption.TOOLTIP, tooltip.getType());
  }

  public boolean isTooltipSplit() {
    return getBooleanAttribute(SliderOption.TOOLTIP_SPLIT, false);
  }

  /**
   * Show one too-tip if set to <code>false</code>, otherwise
   * show two tool-tips one for each handler.
   *
   * @param tooltipSplit
   */
  public void setTooltipSplit(boolean tooltipSplit) {
    updateSlider(SliderOption.TOOLTIP_SPLIT, tooltipSplit);
  }

  public TooltipPosition getTooltipPosition() {
    TooltipPosition defaultPosition = getOrientation() == OrientationType.HORIZONTAL ? TooltipPosition.TOP : TooltipPosition.RIGHT;
    return getEnumAttribute(SliderOption.TOOLTIP_POSITION, TooltipPosition.class, defaultPosition);
  }

  /**
   * Sets the tool-tip position.
   *
   * @param position
   * @see TooltipPosition
   */
  public void setTooltipPosition(TooltipPosition position) {
    updateSlider(SliderOption.TOOLTIP_POSITION, position.getPosition());
  }

  public HandleType getHandle() {
    return getEnumAttribute(SliderOption.HANDLE, HandleType.class, HandleType.ROUND);
  }

  /**
   * Sets the handle shape.
   *
   * @param handle
   * @see HandleType
   */
  public void setHandle(HandleType handle) {
    updateSlider(SliderOption.HANDLE, handle.getType());
  }

  public boolean isReversed() {
    return getBooleanAttribute(SliderOption.REVERSED, false);
  }

  /**
   * Sets whether or not the slider should be reversed.
   *
   * @param reversed
   */
  public void setReversed(boolean reversed) {
    updateSlider(SliderOption.REVERSED, reversed);
  }

  @Override
  public boolean isEnabled() {
    if (isAttached()) {
      return isEnabled(getElement());
    }
    return getBooleanAttribute(SliderOption.ENABLED, true);
  }

  @Override
  public void setEnabled(boolean enabled) {
    if (isAttached()) {
      if (enabled) {
        sliderCommand(getElement(), SliderCommand.ENABLE);
      } else {
        sliderCommand(getElement(), SliderCommand.DISABLE);
      }
    } else {
      updateSlider(SliderOption.ENABLED, enabled);
    }
  }

  /**
   * Sets the formatter callback.
   *
   * @param formatterCallback
   */
  public void setFormatter(FormatterCallback<T> formatterCallback) {
    this.formatterCallback = formatterCallback;
    if (isAttached()) {
      setFormatter();
      refresh();
    }
  }

  /**
   * Sets the callback function of the {@link SliderOption#FORMATTER} attribute.
   */
  protected void setFormatter() {
    if (isBoostrapSliderNamespaceAvailable()) {
      $(this).bootstrapSlider(SliderCommand.SET_ATTRIBUTE, SliderOption.FORMATTER.getName(), createFormatter());
    } else {
      $(this).slider(SliderCommand.SET_ATTRIBUTE, SliderOption.FORMATTER.getName(), createFormatter());
    }
  }

  protected FormatterCallback<T> createFormatter() {
    if (formatterCallback != null) {
      return formatterCallback;
    }
    return defaultFormatter();
  }

  protected abstract FormatterCallback<T> defaultFormatter();

  public boolean isNaturalArrowKeys() {
    return getBooleanAttribute(SliderOption.NATURAL_ARROW_KEYS, false);
  }

  /**
   * The natural order is used for the arrow keys. Arrow up select the
   * upper slider value for vertical sliders, arrow right the righter
   * slider value for a horizontal slider ; no matter if the slider
   * was reversed or not.<br>
   * <br>
   * By default the arrow keys are oriented by arrow up/right to the
   * higher slider value, arrow down/left to the lower slider value.
   *
   * @param naturalArrowKeys
   */
  public void setNaturalArrowKeys(boolean naturalArrowKeys) {
    updateSlider(SliderOption.NATURAL_ARROW_KEYS, naturalArrowKeys);
  }

  public List<Double> getTicks() {
    return getNumberArrayAttribute(SliderOption.TICKS, Collections.emptyList());
  }

  /**
   * Sets the values of ticks. Tick marks are indicators to denote
   * special values in the range.<br>
   * <br>
   * This option overwrites min and max options.
   *
   * @param ticks
   */
  public void setTicks(List<Double> ticks) {
    updateSliderForNumberArray(SliderOption.TICKS, ticks);
  }

  public List<Double> getTicksPositions() {
    return getNumberArrayAttribute(SliderOption.TICKS_POSITIONS, Collections.emptyList());
  }

  /**
   * Defines the positions of the tick values in percentages.<br>
   * The first value should always be 0, the last value should always be 100 percent.
   *
   * @param ticksPositions
   */
  public void setTicksPositions(List<Double> ticksPositions) {
    updateSliderForNumberArray(SliderOption.TICKS_POSITIONS, ticksPositions);
  }

  public List<String> getTicksLabels() {
    return getStringArrayAttribute(SliderOption.TICKS_LABELS, Collections.emptyList());
  }

  /**
   * Sets the labels below the tick marks.<br>
   * <br>
   * Accepts HTML input.
   *
   * @param ticksLabels
   */
  public void setTicksLabels(List<String> ticksLabels) {
    updateSliderForStringArray(SliderOption.TICKS_LABELS, ticksLabels);
  }

  public double getTicksSnapBounds() {
    return getDoubleAttribute(SliderOption.TICKS_SNAP_BOUNDS, 0);
  }

  /**
   * Sets the snap bounds of a tick. Snaps to the tick if value
   * is within these bounds.
   *
   * @param ticksSnapBounds
   */
  public void setTicksSnapBounds(double ticksSnapBounds) {
    updateSlider(SliderOption.TICKS_SNAP_BOUNDS, ticksSnapBounds);
  }

  public ScaleType getScale() {
    return getEnumAttribute(SliderOption.SCALE, ScaleType.class, ScaleType.LINEAR);
  }

  /**
   * Focus the appropriate slider handle after a value change.
   * Defaults to false.
   *
   * @param focus
   */
  public void setFocusHandle(boolean focus) {
    updateSlider(SliderOption.FOCUS, focus);
  }

  public boolean getFocusHandle() {
    return getBooleanAttribute(SliderOption.FOCUS, false);
  }

  /**
   * Sets the slider scale type.
   *
   * @param scale
   * @see ScaleType
   */
  public void setScale(ScaleType scale) {
    updateSlider(SliderOption.SCALE, scale.getType());
  }

  @Override
  public void setVisible(boolean visible) {
    if (isAttached()) {
      setVisible(getElement(getElement()), visible);
    } else {
      super.setVisible(visible);
    }
  }

  @Override
  public boolean isVisible() {
    if (isAttached()) {
      return isVisible(getElement(getElement()));
    }
    return isVisible();
  }

  @Override
  public void setVisibleOn(DeviceSize deviceSize) {
    StyleHelper.setVisibleOn(this, deviceSize);
  }

  @Override
  public void setHiddenOn(DeviceSize deviceSize) {
    StyleHelper.setHiddenOn(this, deviceSize);
  }

  @Override
  public void setValue(T value) {
    setValue(value, false);
  }

  @Override
  public void setValue(T value, boolean fireEvents) {
    T oldValue = fireEvents ? getValue() : null;

    if (isAttached()) {
      if (isBoostrapSliderNamespaceAvailable()) {
        $(this).bootstrapSlider(SliderCommand.SET_VALUE, value);
      } else {
        $(this).slider(SliderCommand.SET_VALUE, value);
      }
    } else {
      String attrVal = (value == null) ? null : Global.JSON.stringify(value);
      attributeMixin.setAttribute(SliderOption.VALUE.getDataAttribute(), attrVal);
    }

    if (fireEvents) {
      T newValue = getValue();
      ValueChangeEvent.fireIfNotEqual(this, oldValue, newValue);
    }
  }

  @Override
  public T getValue() {
    if (isAttached()) {
      return getValue(getElement());
    }
    String attrVal = attributeMixin.getAttribute(SliderOption.VALUE.getDataAttribute());
    return convertValue(attrVal);
  }

  /**
   * Returns the value by invoking the JSNI <strong>getValue</strong> command.
   *
   * @param e
   * @return
   */
  protected abstract T getValue(Element e);

  /**
   * Converts the value of the {@link SliderOption#VALUE} attribute to the
   * slider value.
   *
   * @param value
   * @return
   */
  protected abstract T convertValue(String value);

  @SuppressWarnings("deprecation")
  @Override
  public com.google.gwt.user.client.Element getStyleElement() {
    if (isAttached()) {
      return (com.google.gwt.user.client.Element) getElement(getElement());
    }
    return super.getStyleElement();
  }

  /**
   * Toggles the slider between enabled and disabled.
   */
  public void toggle() {
    if (isAttached()) {
      sliderCommand(getElement(), SliderCommand.TOGGLE);
    } else {
      setEnabled(!isEnabled());
    }
  }

  /**
   * Refreshes the current slider. This method does nothing if the slider has
   * not been initialized.
   */
  public void refresh() {
    if (isAttached()) {
      sliderCommand(getElement(), SliderCommand.REFEESH);
    }
  }

  /**
   * Renders the tool-tip again, after initialization. Useful in situations
   * when the slider and tool-tip are initially hidden.
   */
  public void relayout() {
    if (isAttached()) {
      sliderCommand(getElement(), SliderCommand.RELAYOUT);
    }
  }

  @Override
  public LeafValueEditor<T> asEditor() {
    if (editor == null) {
      editor = TakesValueEditor.of(this);
    }
    return editor;
  }

  @Override
  public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
    return addHandler(handler, ValueChangeEvent.getType());
  }

  @Override
  public HandlerRegistration addSlideHandler(SlideHandler<T> handler) {
    return addHandler(handler, SlideEvent.getType());
  }

  @Override
  public HandlerRegistration addSlideStartHandler(SlideStartHandler<T> handler) {
    return addHandler(handler, SlideStartEvent.getType());
  }

  @Override
  public HandlerRegistration addSlideStopHandler(SlideStopHandler<T> handler) {
    return addHandler(handler, SlideStopEvent.getType());
  }

  @Override
  public HandlerRegistration addSlideEnabledHandler(SlideEnabledHandler handler) {
    return addHandler(handler, SlideEnabledEvent.getType());
  }

  @Override
  public HandlerRegistration addSlideDisabledHandler(SlideDisabledHandler handler) {
    return addHandler(handler, SlideDisabledEvent.getType());
  }

  private void updateSlider(SliderOption option, String value) {
    if (isAttached()) {
      setAttribute(getElement(), option.getName(), value);
      refresh();
    } else {
      attributeMixin.setAttribute(option.getDataAttribute(), value);
    }
  }

  private void updateSlider(SliderOption option, boolean value) {
    if (isAttached()) {
      setAttribute(getElement(), option.getName(), value);
      refresh();
    } else {
      attributeMixin.setAttribute(option.getDataAttribute(), Boolean.toString(value));
    }
  }

  private void updateSlider(SliderOption option, double value) {
    if (isAttached()) {
      setAttribute(getElement(), option.getName(), value);
      refresh();
    } else {
      attributeMixin.setAttribute(option.getDataAttribute(), Double.toString(value));
    }
  }

  private void updateSliderForNumberArray(SliderOption option, List<Double> value) {
    JsArrayNumber array = JavaScriptObject.createArray().cast();
    for (Double val : value) {
      array.push(val);
    }
    if (isAttached()) {
      setAttribute(getElement(), option.getName(), array);
      refresh();
    } else {
      String arrayStr = JsonUtils.stringify(array);
      attributeMixin.setAttribute(option.getDataAttribute(), arrayStr);
    }
  }

  private void updateSliderForStringArray(SliderOption option, List<String> value) {
    JsArrayString array = JavaScriptObject.createArray().cast();
    for (String val : value) {
      array.push(val);
    }
    if (isAttached()) {
      setAttribute(getElement(), option.getName(), array);
      refresh();
    } else {
      String arrayStr = JsonUtils.stringify(array);
      attributeMixin.setAttribute(option.getDataAttribute(), arrayStr);
    }
  }

  private String getStringAttribute(SliderOption option) {
    if (isAttached()) {
      return getAttribute(getElement(), option.getName());
    }
    return attributeMixin.getAttribute(option.getDataAttribute());
  }

  private boolean getBooleanAttribute(SliderOption option, boolean defaultValue) {
    if (isAttached()) {
      return getAttribute(getElement(), option.getName());
    }
    String value = attributeMixin.getAttribute(option.getDataAttribute());
    if (value != null && !value.isEmpty()) {
      return Boolean.parseBoolean(value);
    }
    return defaultValue;
  }

  private double getDoubleAttribute(SliderOption option, double defaultValue) {
    if (isAttached()) {
      return getAttribute(getElement(), option.getName());
    }
    String value = attributeMixin.getAttribute(option.getDataAttribute());
    if (value != null && !value.isEmpty()) {
      return Double.parseDouble(value);
    }
    return defaultValue;
  }

  private <E extends Enum<E>> E getEnumAttribute(SliderOption option, Class<E> clazz, E defaultValue) {
    String value;
    if (isAttached()) {
      value = getAttribute(getElement(), option.getName());
    } else {
      value = attributeMixin.getAttribute(option.getDataAttribute());
    }
    try {
      return Enum.valueOf(clazz, value);
    } catch (Throwable e) {
      return defaultValue;
    }
  }

  private List<Double> getNumberArrayAttribute(SliderOption option, List<Double> defaultValue) {

    // Get array attribute
    JsArrayNumber array = null;
    if (isAttached()) {
      array = getAttribute(getElement(), option.getName());
    } else {
      String value = attributeMixin.getAttribute(option.getDataAttribute());
      if (value != null && !value.isEmpty()) {
        array = JsonUtils.safeEval(value);
      }
    }

    // Attribute not set
    if (array == null) {
      return defaultValue;
    }

    // Put array to list
    List<Double> list = new ArrayList<>(array.length());
    for (int i = 0; i < array.length(); i++) {
      list.add(array.get(i));
    }
    return list;
  }

  private List<String> getStringArrayAttribute(SliderOption option, List<String> defaultValue) {

    // Get array attribute
    JsArrayString array = null;
    if (isAttached()) {
      array = getAttribute(getElement(), option.getName());
    } else {
      String value = attributeMixin.getAttribute(option.getDataAttribute());
      if (value != null && !value.isEmpty()) {
        array = JsonUtils.safeEval(value);
      }
    }

    // Attribute not set
    if (array == null) {
      return defaultValue;
    }

    // Put array to list
    List<String> list = new ArrayList<>(array.length());
    for (int i = 0; i < array.length(); i++) {
      list.add(array.get(i));
    }
    return list;
  }

  protected boolean isBoostrapSliderNamespaceAvailable() {
    return jQuery.asPropertyMap().getAsAny("fn").asPropertyMap().get("bootstrapSlider") != null;
  }

  private void initSlider(Element e, JsPropertyMap options) {
    if (isBoostrapSliderNamespaceAvailable()) {
      $(e).bootstrapSlider(options);
    } else {
      $(e).slider(options);
    }
  }

  /**
   * Called when a {@link SlideEvent} is fired.
   *
   * @param event the native event
   */
  protected abstract void onSlide(Event event);

  /**
   * Fires a {@link SlideEvent} event.
   *
   * @param value the new slide value
   */
  protected void fireSlideEvent(T value) {
    SlideEvent.fire(this, value);
  }

  /**
   * Called when a {@link SlideStartEvent} is fired.
   *
   * @param event the native event
   */
  protected abstract void onSlideStart(Event event);

  /**
   * Fires a {@link SlideStartEvent} event.
   *
   * @param value the new slide value
   */
  protected void fireSlideStartEvent(T value) {
    SlideStartEvent.fire(this, value);
  }

  /**
   * Called when a {@link SlideStopEvent} is fired.
   *
   * @param event the native event
   */
  protected abstract void onSlideStop(Event event);

  /**
   * Fires a {@link SlideStopEvent} event.
   *
   * @param value the new slide value
   */
  protected void fireSlideStopEvent(T value) {
    SlideStopEvent.fire(this, value);
  }

  /**
   * Called when a {@link ValueChangeEvent} is fired.
   *
   * @param event the native event
   */
  protected abstract void onSlideChange(Event event);

  /**
   * Fires a {@link ValueChangeEvent} event.
   *
   * @param value the new slide value
   */
  protected void fireChangeEvent(T value) {
    ValueChangeEvent.fire(this, value);
  }

  /**
   * Binds the slider events.
   */
  private void bindSliderEvents() {
    $(this).on(SLIDE_EVENT, this::onSlide);
    $(this).on(SLIDE_START_EVENT, this::onSlideStart);
    $(this).on(SLIDE_STOP_EVENT, this::onSlideStop);
    $(this).on(SLIDE_CHANGE_EVENT, this::onSlideChange);
    $(this).on(SLIDE_ENABLED_EVENT, event -> SlideEnabledEvent.fire(this));
    $(this).on(SLIDE_DISABLED_EVENT, event -> SlideDisabledEvent.fire(this));
  }

  /**
   * Unbinds the slider events.
   */
  private void unbindSliderEvents() {
    $(this).off(SLIDE_EVENT);
    $(this).off(SLIDE_START_EVENT);
    $(this).off(SLIDE_STOP_EVENT);
    $(this).off(SLIDE_CHANGE_EVENT);
    $(this).off(SLIDE_ENABLED_EVENT);
    $(this).off(SLIDE_DISABLED_EVENT);
  }

  private boolean isEnabled(Element e) {
    if (isBoostrapSliderNamespaceAvailable()) {
      return $(e).bootstrapSlider(SliderCommand.IS_ENABLED).asBoolean();
    } else {
      return $(e).slider(SliderCommand.IS_ENABLED).asBoolean();
    }
  }

  private void sliderCommand(Element e, String cmd) {
    if (isBoostrapSliderNamespaceAvailable()) {
      $(e).bootstrapSlider(cmd);
    } else {
      $(e).slider(cmd);
    }
  }

  private Element getElement(Element e) {
    if (isBoostrapSliderNamespaceAvailable()) {
      return $(e).bootstrapSlider(SliderCommand.GET_ELEMENT).cast();
    } else {
      return $(e).slider(SliderCommand.GET_ELEMENT).cast();
    }
  }

  private void setAttribute(Element e, String attr, Object value) {
    if (isBoostrapSliderNamespaceAvailable()) {
      $(e).bootstrapSlider(SliderCommand.SET_ATTRIBUTE, attr, value);
    } else {
      $(e).slider(SliderCommand.SET_ATTRIBUTE, attr, value);
    }
  }

  private <U> U getAttribute(Element e, String attr) {
    if (isBoostrapSliderNamespaceAvailable()) {
      return $(e).bootstrapSlider(SliderCommand.GET_ATTRIBUTE, attr).cast();
    } else {
      return $(e).slider(SliderCommand.GET_ATTRIBUTE, attr).cast();
    }
  }

  @JsType(isNative = true, namespace = JsPackage.GLOBAL)
  public static class SliderJQuery extends JQuery {
    @JsMethod(namespace = JsPackage.GLOBAL, name = "$")
    public static native SliderJQuery $(Element element);

    @JsOverlay
    public static SliderJQuery $(IsWidget widget) {
      return $(widget.asWidget().getElement());
    }

    public native SliderJQuery slider(Object obj);

    public native SliderJQuery slider(String obj, Object att);

    public native SliderJQuery slider(Object obj, String att, Object value);

    public native SliderJQuery bootstrapSlider(Object obj);

    public native SliderJQuery bootstrapSlider(String obj, Object att);

    public native SliderJQuery bootstrapSlider(Object obj, String att, Object value);
  }
}
