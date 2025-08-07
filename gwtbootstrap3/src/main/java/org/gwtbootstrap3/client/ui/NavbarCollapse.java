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

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;

import org.gwtbootstrap3.client.shared.event.HiddenEvent;
import org.gwtbootstrap3.client.shared.event.HiddenHandler;
import org.gwtbootstrap3.client.shared.event.HideEvent;
import org.gwtbootstrap3.client.shared.event.HideHandler;
import org.gwtbootstrap3.client.shared.event.ShowEvent;
import org.gwtbootstrap3.client.shared.event.ShowHandler;
import org.gwtbootstrap3.client.shared.event.ShownEvent;
import org.gwtbootstrap3.client.shared.event.ShownHandler;
import org.gwtbootstrap3.client.shared.js.JQuery;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.CollapseParam;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.gwt.FlowPanel;

/**
 * Container for collapsible items within a {@link Navbar}.
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 * @see NavbarCollapseButton
 */
public class NavbarCollapse extends FlowPanel {

    // Default hidden
    private boolean toggle;

    public NavbarCollapse() {
        setStyleName(Styles.COLLAPSE);
        addStyleName(Styles.NAVBAR_COLLAPSE);
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        // Bind jquery events
        bindJavaScriptEvents(getElement());

        // Configure the collapse
        if (toggle) {
            addStyleName(Styles.IN);
        }
    }

    @Override
    protected void onUnload() {
        super.onUnload();

        // Unbind the events
        unbindJavaScriptEvents(getElement());
    }

    /**
     * Causes the collapse to show or hide without animation and events
     *
     * @param in show or hide the collapse
     */
    public void setIn(boolean in) {
        if (in) {
            addStyleName(Styles.IN);
        } else {
            removeStyleName(Styles.IN);
        }
    }

    /**
     * Causes the collapse to show or hide
     */
    public void toggle() {
        fireMethod(getElement(), CollapseParam.TOGGLE);
    }

    /**
     * Causes the collapse to show
     */
    public void show() {
        fireMethod(getElement(), CollapseParam.SHOW);
    }

    /**
     * Causes the collapse to hide
     */
    public void hide() {
        fireMethod(getElement(), CollapseParam.HIDE);
    }

    public boolean isShown() {
        return StyleHelper.containsStyle(getStyleName(), Styles.IN);
    }

    public boolean isHidden() {
        return !isShown();
    }

    public boolean isCollapsing() {
        return StyleHelper.containsStyle(getStyleName(), Styles.COLLAPSING);
    }

    public HandlerRegistration addShowHandler(ShowHandler showHandler) {
        return addHandler(showHandler, ShowEvent.getType());
    }

    public HandlerRegistration addShownHandler(ShownHandler shownHandler) {
        return addHandler(shownHandler, ShownEvent.getType());
    }

    public HandlerRegistration addHideHandler(HideHandler hideHandler) {
        return addHandler(hideHandler, HideEvent.getType());
    }

    public HandlerRegistration addHiddenHandler(HiddenHandler hiddenHandler) {
        return addHandler(hiddenHandler, HiddenEvent.getType());
    }

    /**
     * Fired when the collapse is starting to show
     */
    private void onShow(Event evt) {
        fireEvent(new ShowEvent(evt));
    }

    /**
     * Fired when the collapse has shown
     */
    private void onShown(Event evt) {
        fireEvent(new ShownEvent(evt));
    }

    /**
     * Fired when the collapse is starting to hide
     */
    private void onHide(Event evt) {
        fireEvent(new HideEvent(evt));
    }

    /**
     * Fired when the collapse has hidden
     */
    private void onHidden(Event evt) {
        fireEvent(new HiddenEvent(evt));
    }

    private void bindJavaScriptEvents(Element e) {
        JQuery collapse = JQuery.jQuery(e);

        collapse.on("show.bs.collapse", this::onShow);

        collapse.on("shown.bs.collapse", this::onShown);

        collapse.on("hide.bs.collapse", this::onHide);

        collapse.on("hidden.bs.collapse", this::onHidden);
    }

    private void unbindJavaScriptEvents(Element e) {
        JQuery.jQuery(e).off("show.bs.collapse");
        JQuery.jQuery(e).off("shown.bs.collapse");
        JQuery.jQuery(e).off("hide.bs.collapse");
        JQuery.jQuery(e).off("hidden.bs.collapse");
    }

    private void fireMethod(Element e, String command) {
        JQuery.jQuery(e).collapse(command);
    }
}
