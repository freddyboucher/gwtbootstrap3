package org.gwtbootstrap3.client.ui.gwt;

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

import java.util.List;

import org.gwtbootstrap3.client.ui.base.HasContextualBackground;
import org.gwtbootstrap3.client.ui.base.HasDataSpy;
import org.gwtbootstrap3.client.ui.base.HasDataTarget;
import org.gwtbootstrap3.client.ui.base.HasId;
import org.gwtbootstrap3.client.ui.base.HasInlineStyle;
import org.gwtbootstrap3.client.ui.base.HasResponsiveness;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.base.mixin.DataSpyMixin;
import org.gwtbootstrap3.client.ui.base.mixin.DataTargetMixin;
import org.gwtbootstrap3.client.ui.base.mixin.IdMixin;
import org.gwtbootstrap3.client.ui.constants.ContextualBackground;
import org.gwtbootstrap3.client.ui.constants.DeviceSize;
import org.gwtbootstrap3.client.ui.constants.Spy;

import com.google.gwt.dom.client.Style;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Sven Jacobs
 * @author Grant Slender
 * @author David Buhler
 */
public class HTMLPanel extends com.google.gwt.user.client.ui.HTMLPanel implements HasId, HasDataSpy, HasDataTarget, HasResponsiveness, HasInlineStyle, HasContextualBackground {

    private final DataSpyMixin<HTMLPanel> spyMixin = new DataSpyMixin<>(this);
    private final DataTargetMixin<HTMLPanel> targetMixin = new DataTargetMixin<>(this);
    private final IdMixin<HTMLPanel> idMixin = new IdMixin<>(this);

    public HTMLPanel(String html) {
        super(html);
    }

    public HTMLPanel(SafeHtml safeHtml) {
        super(safeHtml);
    }

    public HTMLPanel(String tag, String html) {
        super(tag, html);
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
    public void setDataSpy(Spy spy) {
        spyMixin.setDataSpy(spy);
    }

    @Override
    public Spy getDataSpy() {
        return spyMixin.getDataSpy();
    }

    @Override
    public void setDataTargetWidgets(List<Widget> widgets) {
        targetMixin.setDataTargetWidgets(widgets);
    }

    @Override
    public void setDataTargetWidget(Widget widget) {
        targetMixin.setDataTargetWidget(widget);
    }

    @Override
    public void setDataTarget(String dataTarget) {
        targetMixin.setDataTarget(dataTarget);
    }

    @Override
    public String getDataTarget() {
        return targetMixin.getDataTarget();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContextualBackground(ContextualBackground contextualBackground) {
        StyleHelper.addUniqueEnumStyleName(this, ContextualBackground.class, contextualBackground);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContextualBackground getContextualBackground() {
        return ContextualBackground.fromStyleName(getStyleName());
    }
}
