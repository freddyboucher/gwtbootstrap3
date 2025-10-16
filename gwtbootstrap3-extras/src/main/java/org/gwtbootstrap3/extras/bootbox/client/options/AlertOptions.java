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

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import org.gwtbootstrap3.extras.bootbox.client.callback.SimpleCallback;

/**
 * Alert options.
 *
 * @author Xiaodong Sun
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class AlertOptions extends DialogOptions {

  public SimpleCallback callback;

  @JsOverlay
  public static final AlertOptions newOptions(String message) {
    AlertOptions options = new AlertOptions();
    options.message = message;
    return options;
  }
}
