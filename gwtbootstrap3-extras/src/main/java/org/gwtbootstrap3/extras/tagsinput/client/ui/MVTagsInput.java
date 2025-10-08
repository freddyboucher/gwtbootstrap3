package org.gwtbootstrap3.extras.tagsinput.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.OptionElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.gwtbootstrap3.extras.tagsinput.client.ui.base.MultiValueTagsInput;
import org.gwtbootstrap3.extras.typeahead.client.base.StringDataset;

/**
 * Wrapper for Bootstrap Tags Input component with multi value support.
 *
 * @author Marko Nikolić <marko.nikolic@iten.rs>
 */
public class MVTagsInput extends MultiValueTagsInput<String> {

  public MVTagsInput() {
  }

  public MVTagsInput(StringDataset dataset) {
    super(dataset);
  }

  public MVTagsInput(Element e) {
    super(e);
  }

  public MVTagsInput(Element e, StringDataset dataset) {
    super(e, dataset);
  }

  @Override
  public void add(String tag) {
    if (isAttached()) {
      super.add(tag);
    } else {
      OptionElement option = Document.get().createOptionElement();
      option.setValue(tag);
      option.setInnerText(tag);
      getElement().appendChild(option);
    }
  }

  @Override
  public List<String> getValue() {
    if (isAttached()) {
      return super.getValue();
    } else {
      List<String> value = new ArrayList<>();
      for (int i = 0; i < getElement().getChildCount(); i++) {
        value.add(getElement().getChild(i).getNodeValue());
      }
      return value;
    }
  }

  public void setValue(String value) {
    removeAll();
    add(Arrays.asList(value.split(",")));
  }
}
