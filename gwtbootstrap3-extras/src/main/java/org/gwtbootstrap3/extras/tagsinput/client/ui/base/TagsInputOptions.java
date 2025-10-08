package org.gwtbootstrap3.extras.tagsinput.client.ui.base;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayInteger;
import org.gwtbootstrap3.extras.tagsinput.client.callback.ItemTextCallback;
import org.gwtbootstrap3.extras.tagsinput.client.callback.ItemValueCallback;
import org.gwtbootstrap3.extras.tagsinput.client.callback.OnTagExistsCallback;
import org.gwtbootstrap3.extras.tagsinput.client.callback.TagClassCallback;

/**
 * Tags Input options, used for customisation of TagsInput widget.
 *
 * @author Marko Nikolić <marko.nikolic@iten.rs>
 */
public class TagsInputOptions<T> extends JavaScriptObject {

  protected TagsInputOptions() {
  }

  /**
   * Creates a new instance of {@link TagsInputOptions}.
   *
   * @return a new instance of {@link TagsInputOptions}.
   */
  @SuppressWarnings("rawtypes")
  static TagsInputOptions create() {
    return JavaScriptObject.createObject().cast();
  }

  public final native void setTagClass(String className) /*-{
    this.tagClass = className;
  }-*/;

  public final native void setTagClass(TagClassCallback<T> callback) /*-{
    this.tagClass = function (item) {
      return callback.@org.gwtbootstrap3.extras.tagsinput.client.callback.TagClassCallback::getTagClass(Ljava/lang/Object;)(item);
    }
  }-*/;

  public final native void setItemValue(String itemValue) /*-{
    this.itemValue = itemValue;
  }-*/;

  public final native void setItemValue(ItemValueCallback<T> callback) /*-{
    this.itemValue = function (item) {
      return callback.@org.gwtbootstrap3.extras.tagsinput.client.callback.ItemValueCallback::getItemValue(Ljava/lang/Object;)(item);
    }
  }-*/;

  public final native void setItemText(String itemText) /*-{
    this.itemText = itemText;
  }-*/;

  public final native void setItemText(ItemTextCallback<T> callback) /*-{
    this.itemText = function (item) {
      return callback.@org.gwtbootstrap3.extras.tagsinput.client.callback.ItemTextCallback::getItemText(Ljava/lang/Object;)(item);
    }
  }-*/;

  public final native void setConfirmKeys(JsArrayInteger keys) /*-{
    this.confirmKeys = keys;
  }-*/;

  public final native void setMaxTags(int maxTags) /*-{
    this.maxTags = maxTags;
  }-*/;

  public final native void setMaxChars(int maxChars) /*-{
    this.maxChars = maxChars;
  }-*/;

  public final native void setTrimValue(boolean trimValue) /*-{
    this.trimValue = trimValue;
  }-*/;

  public final native void setAllowDuplicates(boolean allowDuplicates) /*-{
    this.allowDuplicates = allowDuplicates;
  }-*/;

  public final native void setFocusClass(String focusClass) /*-{
    this.focusClass = focusClass;
  }-*/;

  public final native void setFreeInput(boolean freeInput) /*-{
    this.freeInput = freeInput;
  }-*/;

  public final native void setCancelConfirmKeysOnEmpty(boolean cancelConfirmKeysOnEmpty) /*-{
    this.cancelConfirmKeysOnEmpty = cancelConfirmKeysOnEmpty;
  }-*/;

  public final native void onTagExists(OnTagExistsCallback<T> callback) /*-{
    this.onTagExists = function (item, tag) {
      callback.@org.gwtbootstrap3.extras.tagsinput.client.callback.OnTagExistsCallback::onTagExists(Ljava/lang/Object;
Lcom/google/gwt/dom/client/Element;)(item, tag[0]);
    }
  }-*/;
}
