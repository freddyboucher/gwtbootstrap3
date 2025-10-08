package org.gwtbootstrap3.client.ui.base;

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

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.ui.client.adapters.HasTextEditor;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.base.mixin.IdMixin;
import org.gwtbootstrap3.client.ui.base.mixin.PullMixin;
import org.gwtbootstrap3.client.ui.constants.DeviceSize;
import org.gwtbootstrap3.client.ui.constants.Pull;

/**
 * Base class for {@link Widget} that just contains text.
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 */
public abstract class AbstractTextWidget extends Widget
    implements HasId, HasHTML, HasResponsiveness, HasInlineStyle, IsEditor<LeafValueEditor<String>>, HasPull {
  private final PullMixin<AbstractTextWidget> pullMixin = new PullMixin<>(this);
  private final IdMixin<AbstractTextWidget> idMixin = new IdMixin<>(this);
  private LeafValueEditor<String> editor;

  protected AbstractTextWidget(Element element) {
    setElement(element);
  }

  @Override
  public void setId(String id) {
    idMixin.setId(id);
  }

  @Override
  public String getId() {
    return idMixin.getId();
  }

  @Override
  public void setText(String text) {
    getElement().setInnerText(text);
  }

  @Override
  public String getText() {
    return getElement().getInnerText();
  }

  @Override
  public String getHTML() {
    return getElement().getInnerHTML();
  }

  @Override
  public void setHTML(String html) {
    getElement().setInnerHTML(html);
  }

  @Override
  public void setVisibleOn(DeviceSize deviceSize) {
    StyleHelper.setVisibleOn(this, deviceSize);
  }

  @Override
  public void setHiddenOn(DeviceSize deviceSize) {
    StyleHelper.setHiddenOn(this, deviceSize);
  }

  @Override
  public void setMarginTop(double margin) {
    getElement().getStyle().setMarginTop(margin, Style.Unit.PX);
  }

  @Override
  public void setMarginLeft(double margin) {
    getElement().getStyle().setMarginLeft(margin, Style.Unit.PX);
  }

  @Override
  public void setMarginRight(double margin) {
    getElement().getStyle().setMarginRight(margin, Style.Unit.PX);
  }

  @Override
  public void setMarginBottom(double margin) {
    getElement().getStyle().setMarginBottom(margin, Style.Unit.PX);
  }

  @Override
  public void setPaddingTop(double padding) {
    getElement().getStyle().setPaddingTop(padding, Style.Unit.PX);
  }

  @Override
  public void setPaddingLeft(double padding) {
    getElement().getStyle().setPaddingLeft(padding, Style.Unit.PX);
  }

  @Override
  public void setPaddingRight(double padding) {
    getElement().getStyle().setPaddingRight(padding, Style.Unit.PX);
  }

  @Override
  public void setPaddingBottom(double padding) {
    getElement().getStyle().setPaddingBottom(padding, Style.Unit.PX);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setColor(String color) {
    getElement().getStyle().setColor(color);
  }

  @Override
  public LeafValueEditor<String> asEditor() {
    if (editor == null) {
      editor = HasTextEditor.of(this);
    }
    return editor;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setPull(Pull pull) {
    pullMixin.setPull(pull);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Pull getPull() {
    return pullMixin.getPull();
  }
}
