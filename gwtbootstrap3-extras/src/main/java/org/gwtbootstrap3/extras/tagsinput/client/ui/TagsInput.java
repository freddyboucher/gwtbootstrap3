package org.gwtbootstrap3.extras.tagsinput.client.ui;

import com.google.gwt.dom.client.Element;
import java.util.Arrays;
import org.gwtbootstrap3.client.ui.base.mixin.AttributeMixin;
import org.gwtbootstrap3.extras.tagsinput.client.ui.base.SingleValueTagsInput;
import org.gwtbootstrap3.extras.typeahead.client.base.StringDataset;

/**
 * Wrapper for Bootstrap Tags Input component.
 *
 * @author Marko Nikolić <marko.nikolic@iten.rs>
 */
public class TagsInput extends SingleValueTagsInput<String> {

  public TagsInput() {
  }

  public TagsInput(StringDataset dataset) {
    super(dataset);
  }

  public TagsInput(Element e) {
    super(e);
  }

  public TagsInput(Element e, StringDataset dataset) {
    super(e, dataset);
  }

  private final AttributeMixin<TagsInput> attributeMixin = new AttributeMixin<>(this);

  @Override
  public void add(String tag) {
    if (isAttached()) {
      super.add(tag);
    } else {
      String currentValue = attributeMixin.getAttribute("value");
      attributeMixin.setAttribute("value", (currentValue.isEmpty() ? "" : currentValue + ",") + tag);
    }
  }

  @Override
  public String getValue() {
    if (isAttached()) {
      return super.getValue();
    } else {
      return attributeMixin.getAttribute("value");
    }
  }

  public void setValue(String value) {
    removeAll();
    add(Arrays.asList(value.split(",")));
  }
}
