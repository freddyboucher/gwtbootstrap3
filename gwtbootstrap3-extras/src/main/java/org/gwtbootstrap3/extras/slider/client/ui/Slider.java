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
 * This slider simply takes a numeric value.
 *
 * @author Xiaodong SUN
 */
public class Slider extends SliderBase<Double> {

  /**
   * Creates a numerical slider.
   */
  public Slider() {
    setRange(false);
  }

  /**
   * Creates a numerical slider with min, max, and value.
   *
   * @param min
   * @param max
   * @param value
   */
  @UiConstructor
  public Slider(double min, double max, double value) {
    this();
    setMin(min);
    setMax(max);
    setValue(value);
  }

  @Override
  protected Double getValue(Element e) {
    if (isBoostrapSliderNamespaceAvailable()) {
      return $(e).bootstrapSlider(SliderCommand.GET_VALUE).asDouble();
    } else {
      return $(e).slider(SliderCommand.GET_VALUE).asDouble();
    }
  }

  @Override
  protected void setFormatterOption(JsPropertyMap options) {
    options.set(SliderOption.FORMATTER.getName(), createFormatter());
  }

  @Override
  protected FormatterCallback<Double> defaultFormatter() {
    return Object::toString;
  }

  @Override
  protected Double convertValue(String value) {
    if (value == null || value.isEmpty()) {
      return null;
    }
    return Double.valueOf(value);
  }

  @Override
  protected void onSlide(Event event) {
    fireSlideEvent(Js.asPropertyMap(event).getAsAny("value").asDouble());
  }

  @Override
  protected void onSlideStart(Event event) {
    fireSlideStartEvent(Js.asPropertyMap(event).getAsAny("value").asDouble());
  }

  @Override
  protected void onSlideStop(Event event) {
    fireSlideStopEvent(Js.asPropertyMap(event).getAsAny("value").asDouble());
  }

  @Override
  protected void onSlideChange(Event event) {
    fireChangeEvent(Js.asPropertyMap(event).getAsAny("value").asPropertyMap().getAsAny("newValue").asDouble());
  }
}
