package org.gwtbootstrap3.extras.tagsinput.client.ui.base;

import elemental2.core.JsArray;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.JsPropertyMap;

/**
 * Tags Input options, used for customisation of TagsInput widget.
 *
 * @author Marko Nikolić <marko.nikolic@iten.rs>
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class TagsInputOptions implements JsPropertyMap {

  public Object tagClass;
  public Object itemValue;
  public Object itemText;
  public JsArray<Integer> confirmKeys;
  public Integer maxTags;
  public Integer maxChars;
  public Boolean trimValue;
  public Boolean allowDuplicates;
  public String focusClass;
  public Boolean freeInput;
  public Boolean cancelConfirmKeysOnEmpty;
  public Object onTagExists;
}
