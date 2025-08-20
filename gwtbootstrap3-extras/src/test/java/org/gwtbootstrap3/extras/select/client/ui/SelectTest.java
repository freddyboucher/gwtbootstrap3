package org.gwtbootstrap3.extras.select.client.ui;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.RootPanel;
import org.gwtbootstrap3.client.GwtBootstrap3EntryPoint;
import org.gwtbootstrap3.client.shared.js.JQuery;
import org.gwtbootstrap3.extras.select.client.SelectEntryPoint;

public class SelectTest extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "org.gwtbootstrap3.extras.select.Select";
    }

    public void testSelect() {
        new GwtBootstrap3EntryPoint().onModuleLoad();
        new SelectEntryPoint().onModuleLoad();
        Select select = new Select();
        assertNull(select.getValue());

        Option option = new Option();
        option.setText("text");
        option.setValue("value");
        select.add(option);
        // getValue returns the first option value when the Select is NOT ATTACHED to the DOM
        assertEquals("value", select.getValue());

        RootPanel.get().add(select);
        // getValue returns the first option value when the Select is ATTACHED to the DOM
        assertEquals("value", select.getValue());
        assertEquals("text", readText(select));

        select.setValue("xxx");
        assertNull(select.getValue());
        assertEquals("Nothing selected", readText(select));

        select.setValue("value");
        assertEquals("value", select.getValue());
        assertEquals("text", readText(select));

        select.setValue("xxx");
        assertNull(select.getValue());
        assertEquals("Nothing selected", readText(select));

        select.setValue("");
        assertNull(select.getValue());
        assertEquals("Nothing selected", readText(select));

        select.setValue(null);
        assertNull(select.getValue());
        assertEquals("Nothing selected", readText(select));

        select.setValue("xxx");
        assertNull(select.getValue());
        assertEquals("Nothing selected", readText(select));
    }

    private String readText(Select select) {
        return JQuery.jQuery(select.getElement().getParentElement()).find(".filter-option").text();
    }

    public void testPlaceholderSelect() {
        new GwtBootstrap3EntryPoint().onModuleLoad();
        new SelectEntryPoint().onModuleLoad();
        Select select = new Select();
        assertNull(select.getValue());

        select.setPlaceholder("placeholder");
        assertNull(null, select.getValue());

        Option option = new Option();
        option.setText("text");
        option.setValue("value");
        select.add(option);
        // getValue returns the first option value when the Select is NOT ATTACHED to the DOM
        assertEquals("value", select.getValue());

        RootPanel.get().add(select);
        // getValue does not return the same value when the Select is ATTACHED to the DOM
        assertEquals("", select.getValue());
        assertEquals("placeholder", readText(select));

        select.setValue("xxx");
        assertNull(select.getValue());
        assertEquals("placeholder", readText(select));

        select.setValue("value");
        assertEquals("value", select.getValue());
        assertEquals("text", readText(select));

        select.setValue("xxx");
        assertNull(select.getValue());
        assertEquals("placeholder", readText(select));

        select.setValue("");
        assertEquals("", select.getValue());
        assertEquals("placeholder", readText(select));

        select.setValue(null);
        assertEquals("", select.getValue());
        assertEquals("placeholder", readText(select));

        select.setValue("xxx");
        assertNull(select.getValue());
        assertEquals("placeholder", readText(select));
    }
}
