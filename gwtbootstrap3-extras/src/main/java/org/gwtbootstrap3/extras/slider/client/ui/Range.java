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

import elemental2.core.Global;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import jsinterop.base.JsArrayLike;

/**
 * Slider range with a min value and a max value.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Array")
public class Range implements JsArrayLike<Double> {

  private Range() {
  }

  /**
   * Create a slider range with a min value and a max value.
   *
   * @param minValue
   * @param maxValue
   */
  @JsOverlay
  public static Range create(double minValue, double maxValue) {
    Range range = new Range();
    range.setAt(0, minValue);
    range.setAt(1, maxValue);
    return range;
  }

  /**
   * Returns the min value.
   *
   * @return the min value
   */
  @JsOverlay
  public final double getMinValue() {
    return getAt(0);
  }

  /**
   * Returns the max value.
   *
   * @return the max value
   */
  @JsOverlay
  public final double getMaxValue() {
    return getAt(1);
  }

  /**
   * Converts the given string to a range instance.<br>
   * <br>
   * Useful when using UiBinder.
   *
   * @param value
   * @return
   */
  @JsOverlay
  public static Range fromString(String value) {
    if (value == null || value.isEmpty()) {
      return null;
    }
    return Js.cast(Global.eval(value));
  }
}
