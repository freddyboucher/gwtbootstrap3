package org.gwtbootstrap3.extras.notify.client.ui;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.JsPropertyMap;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class NotifyOptions implements JsPropertyMap {
  public String message;
  public String title;
  public String icon;
  public String url;
  public String target;
}
