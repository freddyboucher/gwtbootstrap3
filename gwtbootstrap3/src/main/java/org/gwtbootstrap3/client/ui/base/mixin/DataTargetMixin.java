package org.gwtbootstrap3.client.ui.base.mixin;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 GwtBootstrap3
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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import java.util.List;
import org.gwtbootstrap3.client.ui.base.HasDataTarget;
import org.gwtbootstrap3.client.ui.constants.Attributes;

/**
 * @author Sven Jacobs
 */
public class DataTargetMixin<T extends UIObject & HasDataTarget> extends AbstractMixin implements HasDataTarget {

  /**
   * Ensures the given element has a non-empty id.
   *
   * @param element The element being examined.
   * @see Document#createUniqueId()
   */
  private void ensureId(Element element) {
    String id = element.getId();
    if (id == null || id.isEmpty()) {
      element.setId(Document.get().createUniqueId());
    }
  }

  public DataTargetMixin(T uiObject) {
    super(uiObject);
  }

  @Override
  public void setDataTargetWidget(Widget widget) {
    Element element = widget.getElement();
    ensureId(element);
    setDataTarget("#" + element.getId());
  }

  @Override
  public void setDataTargetWidgets(List<Widget> widgets) {
    String styleName = Document.get().createUniqueId();
    for (Widget widget : widgets) {
      widget.addStyleName(styleName);
    }
    setDataTarget("." + styleName);
  }

  @Override
  public void setDataTarget(String dataTarget) {
    if (dataTarget != null) {
      uiObject.getElement().setAttribute(Attributes.DATA_TARGET, dataTarget);
    } else {
      uiObject.getElement().removeAttribute(Attributes.DATA_TARGET);
    }
  }

  @Override
  public String getDataTarget() {
    return uiObject.getElement().getAttribute(Attributes.DATA_TARGET);
  }
}
