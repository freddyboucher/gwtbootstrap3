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

import org.gwtbootstrap3.client.ui.constants.ColumnOffset;
import org.gwtbootstrap3.client.ui.constants.ColumnPull;
import org.gwtbootstrap3.client.ui.constants.ColumnPush;
import org.gwtbootstrap3.client.ui.constants.ColumnSize;
import org.gwtbootstrap3.client.ui.html.Div;

import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Widget;

/**
 * A column of Bootstrap's fluid grid system.
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 * @author Pontus Enmark
 * @see Row
 */
public class Column extends Div {

    private static final String SEPARATOR = "[, ]+";

    /**
     * Creates a column with one size, and with one or more additional widgets added.
     * <p/>
     * Additional sizes can be added with {@link #addSize(ColumnSize...)}.
     * Additional widgets can be added with {@link #add(Widget)}.
     *
     * @param size         Size of column
     * @param firstWidget  Widget to add
     * @param otherWidgets Other widgets to add
     */
    public Column(ColumnSize size, Widget firstWidget, Widget... otherWidgets) {
        this(size);

        add(firstWidget);
        for (Widget widget : otherWidgets) {
            add(widget);
        }
    }

    /**
     * Creates column with one or more additional sizes.
     * <p/>
     * Additional sizes can be added with {@link #addSize(ColumnSize...)}
     *
     * @param firstSize  Size of column
     * @param otherSizes Other sizes of column
     * @see #addSize(ColumnSize...)
     */
    public Column(ColumnSize firstSize, ColumnSize... otherSizes) {
        setSize(firstSize, otherSizes);
    }

    /**
     * Convenience constructor for UiBinder to create a Column with one or more
     * sizes.
     * <p/>
     * Size needs to be a space-separated String of {@link ColumnSize} enum
     * names, e.g. "SM_3 LG_3"
     *
     * @param size Space-separated String of {@link ColumnSize}
     * @see ColumnSize
     */
    @UiConstructor
    public Column(String size) {
        setSize(size);
    }

    /**
     * Adds one or more additional column sizes.
     *
     * @param firstSize  Column size
     * @param otherSizes Additional column sizes
     */
    public void setSize(ColumnSize firstSize, ColumnSize... otherSizes) {
        addEnumVarargsValues(new ColumnSize[]{firstSize}, ColumnSize.class, true);
        addEnumVarargsValues(otherSizes, ColumnSize.class, false);
    }

    public void setSize(String sizes) {
        addEnumStringValues(sizes, ColumnSize.class, true);
    }

    public void addSize(ColumnSize... sizes) {
        addEnumVarargsValues(sizes, ColumnSize.class, false);
    }

    public void addSize(String sizes) {
        addEnumStringValues(sizes, ColumnSize.class, false);
    }

    public void setPull(ColumnPull... pulls) {
        addEnumVarargsValues(pulls, ColumnPull.class, true);
    }

    public void setPull(String pulls) {
        addEnumStringValues(pulls, ColumnPull.class, true);
    }

    public void addPull(ColumnPull... pulls) {
        addEnumVarargsValues(pulls, ColumnPull.class, false);
    }

    public void addPull(String pulls) {
        addEnumStringValues(pulls, ColumnPull.class, false);
    }

    public void setPush(ColumnPush... pushes) {
        addEnumVarargsValues(pushes, ColumnPush.class, true);
    }

    public void setPush(String pushes) {
        addEnumStringValues(pushes, ColumnPush.class, true);
    }

    public void addPush(ColumnPush... pushes) {
        addEnumVarargsValues(pushes, ColumnPush.class, false);
    }

    public void addPush(String pushes) {
        addEnumStringValues(pushes, ColumnPush.class, false);
    }

    public void setOffset(ColumnOffset... offsets) {
        addEnumVarargsValues(offsets, ColumnOffset.class, true);
    }

    public void setOffset(String offsets) {
        addEnumStringValues(offsets, ColumnOffset.class, true);
    }

    public void addOffset(ColumnOffset... offsets) {
        addEnumVarargsValues(offsets, ColumnOffset.class, false);
    }

    public void addOffset(String offsets) {
        addEnumStringValues(offsets, ColumnOffset.class, false);
    }

    private <E extends Enum<? extends Style.HasCssName>> void addEnumVarargsValues(E[] values,
                                                                                   Class<E> enumClass,
                                                                                   boolean clearOld) {
        if (clearOld) {
            // Remove the previous values
            removeStyleNames(enumClass);
        }

        for (E value : values) {
            addStyleName(((Style.HasCssName) value).getCssName());
        }
    }

    private <E extends Enum<? extends Style.HasCssName>> void addEnumStringValues(String values,
                                                                                  Class<E> enumClass,
                                                                                  boolean clearOld) {
        if (clearOld) {
            // Remove the previous values
            removeStyleNames(enumClass);
        }

        // Add new ones
        String[] valuesSplit = values.split(SEPARATOR);
        for (String value : valuesSplit) {
            for (E constant : enumClass.getEnumConstants()) {
                if (value.equalsIgnoreCase(constant.name())) {
                    addStyleName(((Style.HasCssName) constant).getCssName());
                }
            }
        }
    }

    private <E extends Enum<? extends Style.HasCssName>> void removeStyleNames(Class<E> enumClass) {
        for (E constant : enumClass.getEnumConstants()) {
            removeStyleName(((Style.HasCssName) constant).getCssName());
        }
    }
}