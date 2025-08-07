package org.gwtbootstrap3.client.ui;

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

import org.gwtbootstrap3.client.ui.base.ComplexWidget;
import org.gwtbootstrap3.client.ui.base.HasEmphasis;
import org.gwtbootstrap3.client.ui.base.HasSize;
import org.gwtbootstrap3.client.ui.base.HasType;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.ElementTags;
import org.gwtbootstrap3.client.ui.constants.Emphasis;
import org.gwtbootstrap3.client.ui.constants.IconFlip;
import org.gwtbootstrap3.client.ui.constants.IconRotate;
import org.gwtbootstrap3.client.ui.constants.IconSize;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.Styles;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiConstructor;

/**
 * Simple put, an icon.
 *
 * @author Sven Jacobs
 * @see IconType
 * @see IconSize
 */
public class Icon extends ComplexWidget implements HasType<IconType>, HasSize<IconSize>, HasEmphasis, HasClickHandlers {

    public Icon() {
        setElement(Document.get().createElement(ElementTags.I));
        addStyleName(Styles.FONT_AWESOME_BASE);
    }

    @UiConstructor
    public Icon(IconType type) {
        this();
        setType(type);
    }

    @Override
    public void setType(IconType type) {
        StyleHelper.addUniqueEnumStyleName(this, IconType.class, type);
    }

    @Override
    public IconType getType() {
        return IconType.fromStyleName(getStyleName());
    }

    public void setBorder(boolean border) {
        StyleHelper.toggleStyleName(this, border, Styles.ICON_BORDER);
    }

    public boolean isBorder() {
        return StyleHelper.containsStyle(Styles.ICON_BORDER, getStyleName());
    }

    public void setStackBase(boolean stackBase) {
        StyleHelper.toggleStyleName(this, stackBase, Styles.ICON_STACK_BASE);
    }

    public boolean isStackBase() {
        return StyleHelper.containsStyle(Styles.ICON_STACK_BASE, getStyleName());
    }

    public void setFixedWidth(boolean fixedWidth) {
        StyleHelper.toggleStyleName(this, fixedWidth, Styles.ICON_FIXED_WIDTH);
    }

    public boolean isFixedWidth() {
        return StyleHelper.containsStyle(Styles.ICON_FIXED_WIDTH, getStyleName());
    }

    public void setStackTop(boolean stackTop) {
        StyleHelper.toggleStyleName(this, stackTop, Styles.ICON_STACK_TOP);
    }

    public boolean isStackTop() {
        return StyleHelper.containsStyle(Styles.ICON_STACK_TOP, getStyleName());
    }

    public void setInverse(boolean inverse) {
        StyleHelper.toggleStyleName(this, inverse, Styles.ICON_INVERSE);
    }

    public boolean isInverse() {
        return StyleHelper.containsStyle(Styles.ICON_INVERSE, getStyleName());
    }

    public void setSpin(boolean spin) {
        StyleHelper.toggleStyleName(this, spin, Styles.ICON_SPIN);
    }

    public boolean isSpin() {
        return StyleHelper.containsStyle(Styles.ICON_SPIN, getStyleName());
    }

    public void setPulse(boolean pulse) {
        StyleHelper.toggleStyleName(this, pulse, Styles.ICON_PULSE);
    }

    public boolean isPulse() {
        return StyleHelper.containsStyle(Styles.ICON_PULSE, getStyleName());
    }

    public void setRotate(IconRotate iconRotate) {
        if (iconRotate == null) {
            return;
        }
        StyleHelper.addUniqueEnumStyleName(this, IconRotate.class, iconRotate);
    }

    public IconRotate getRotate() {
        return IconRotate.fromStyleName(getStyleName());
    }

    public void setFlip(IconFlip iconFlip) {
        if (iconFlip == null) {
            return;
        }

        StyleHelper.addUniqueEnumStyleName(this, IconFlip.class, iconFlip);
    }

    public IconFlip getFlip() {
        return IconFlip.fromStyleName(getStyleName());
    }

    @Override
    public void setSize(IconSize iconSize) {
        StyleHelper.addUniqueEnumStyleName(this, IconSize.class, iconSize);
    }

    @Override
    public IconSize getSize() {
        return IconSize.fromStyleName(getStyleName());
    }

    @Override
    public void setEmphasis(Emphasis emphasis) {
        StyleHelper.addUniqueEnumStyleName(this, Emphasis.class, emphasis);
    }

    @Override
    public Emphasis getEmphasis() {
        return Emphasis.fromStyleName(getStyleName());
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return addDomHandler(handler, ClickEvent.getType());
    }
}
