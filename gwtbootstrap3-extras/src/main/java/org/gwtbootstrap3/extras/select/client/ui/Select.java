package org.gwtbootstrap3.extras.select.client.ui;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2016 GwtBootstrap3
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

import static org.gwtbootstrap3.extras.select.client.ui.SelectOptions.SHOW_TICK;

/**
 * Standard select box.
 *
 * @author Xiaodong Sun
 */
public class Select extends SelectBase<String> {

    public Select() {
    }

    @Override
    public final boolean isMultiple() {
        return false;
    }

    /**
     * Set to <code>true</code> to show check mark icon on
     * standard select boxes.<br>
     * <br>
     * Defaults to <code>false</code>.
     *
     * @param showTick
     */
    public void setShowTick(boolean showTick) {
        if (showTick)
            attrMixin.setAttribute(SHOW_TICK, Boolean.toString(true));
        else
            attrMixin.removeAttribute(SHOW_TICK);
    }

    @Override
    public String getValue() {
        return getItems().stream().filter(Option::isSelected).findFirst().map(Option::getValue).orElse(null);
    }

    @Override
    protected void setSelectedValue(String value) {
        if (isAttached()) {
            setValue(getElement(), value);
        } else {
            getItems().forEach(item -> item.setSelected(item.getValue().equals(value)));
        }
    }

    /**
     * Returns the selected item or <code>null</code> if no item is selected.
     *
     * @return the selected items list
     */
    public Option getSelectedItem() {
        return getItems().stream().filter(Option::isSelected).findFirst().orElse(null);
    }

    private native String getValue(Element e) /*-{
        return $wnd.jQuery(e).selectpicker('val');
    }-*/;

    private native void setValue(Element e, String value) /*-{
        $wnd.jQuery(e).selectpicker('val', value);
    }-*/;

}
