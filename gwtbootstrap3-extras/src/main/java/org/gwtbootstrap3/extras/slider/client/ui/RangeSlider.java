package org.gwtbootstrap3.extras.slider.client.ui;

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

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.Event;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;
import org.gwtbootstrap3.extras.slider.client.ui.base.FormatterCallback;
import org.gwtbootstrap3.extras.slider.client.ui.base.SliderBase;
import org.gwtbootstrap3.extras.slider.client.ui.base.SliderCommand;
import org.gwtbootstrap3.extras.slider.client.ui.base.SliderOption;

/**
 * This slider takes as value a range with a min value and a max value.
 *
 * @author Xiaodong SUN
 */
public class RangeSlider extends SliderBase<Range> {

  /**
   * Creates a range slider.
   */
  public RangeSlider() {
    setRange(true);
  }

  /**
   * Creates a range slider with min, max, and range value.
   *
   * @param min
   * @param max
   * @param range
   */
  public RangeSlider(double min, double max, Range range) {
    this();
    setMin(min);
    setMax(max);
    setValue(range);
  }

  /**
   * Creates a range slider with min, max, and range value.<br>
   * <br>
   * Useful for UiBinder.
   *
   * @param min
   * @param max
   * @param value
   */
  @UiConstructor
  public RangeSlider(double min, double max, String value) {
    this(min, max, Range.fromString(value));
  }

  @Override
  protected Range getValue(Element e) {
    if (isBoostrapSliderNamespaceAvailable()) {
      return $(e).bootstrapSlider(SliderCommand.GET_VALUE).cast();
    } else {
      return $(e).slider(SliderCommand.GET_VALUE).cast();
    }
  }

  @Override
  protected void setFormatterOption(JsPropertyMap options) {
    options.set(SliderOption.FORMATTER.getName(), createFormatter());
  }

  @Override
  protected FormatterCallback<Range> defaultFormatter() {
    return range -> range.getMinValue() + " : " + range.getMaxValue();
  }

  @Override
  protected Range convertValue(String value) {
    return Range.fromString(value);
  }

  @Override
  protected void onSlide(Event event) {
    fireSlideEvent(Js.asPropertyMap(event).getAsAny("value").cast());
  }

  @Override
  protected void onSlideStart(Event event) {
    fireSlideStartEvent(Js.asPropertyMap(event).getAsAny("value").cast());
  }

  @Override
  protected void onSlideStop(Event event) {
    fireSlideStopEvent(Js.asPropertyMap(event).getAsAny("value").cast());
  }

  @Override
  protected void onSlideChange(Event event) {
    fireChangeEvent(Js.asPropertyMap(event).getAsAny("value").asPropertyMap().getAsAny("newValue").cast());
  }
}
