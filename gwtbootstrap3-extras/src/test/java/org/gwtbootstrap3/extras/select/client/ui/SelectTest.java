package org.gwtbootstrap3.extras.select.client.ui;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.RootPanel;
import org.gwtbootstrap3.client.GwtBootstrap3EntryPoint;
import org.gwtbootstrap3.client.shared.js.JQuery;
import org.gwtbootstrap3.extras.select.client.SelectEntryPoint;

import java.util.concurrent.atomic.AtomicBoolean;

public class SelectTest extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "org.gwtbootstrap3.extras.select.Select";
    }

    @Override
    protected void gwtSetUp() throws Exception {
        super.gwtSetUp();
        new GwtBootstrap3EntryPoint().onModuleLoad();
        new SelectEntryPoint().onModuleLoad();
    }

    public void testSelect() {
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

    public void testGetItems() {
        Select select = new Select();
        assertTrue(select.getItems().isEmpty());

        Option option = new Option();
        option.setText("text");
        option.setValue("value");
        select.add(option);
        assertEquals(1, select.getItems().size());
    }

    public void testEmptyValueOption() {
        Select select = new Select();

        Option option = new Option();
        option.setText("text");
        option.setValue("");
        select.add(option);
        assertEquals("", select.getValue());
    }

    public void testNullValueOption() {
        Select select = new Select();

        Option option = new Option();
        option.setText("text");
        option.setValue(null);
        select.add(option);
        assertNull(select.getValue());
    }

    public void testUndefinedValueOption() {
        Select select = new Select();

        Option option = new Option();
        option.setText("text");
        select.add(option);
        assertEquals("text", select.getValue());
    }

    public void testAddValueChangeHandler() {
        // Adding / Removing Option DO NOT TRIGGER ValueChangeEvent
        {
            Select select = new Select();
            select.addValueChangeHandler(event -> fail());

            Option option = new Option();
            option.setText("text");
            option.setValue("value");

            assertNull(select.getValue());
            select.add(option);
            assertEquals("value", select.getValue());
        }
        {
            Select select = new Select();
            select.addValueChangeHandler(event -> fail());

            RootPanel.get().add(select);
            Option option = new Option();
            option.setText("text");
            option.setValue("value");

            assertNull(select.getValue());
            select.add(option);
            assertEquals("value", select.getValue());
        }
        {
            Select select = new Select();

            Option option = new Option();
            option.setText("text");
            option.setValue("value");
            select.add(option);

            select.addValueChangeHandler(event -> fail());

            assertEquals("value", select.getValue());
            option.removeFromParent();
            assertNull(select.getValue());
        }
        {
            Select select = new Select();
            select.addValueChangeHandler(event -> fail());

            OptGroup optGroup = new OptGroup();
            Option option = new Option();
            option.setText("text");
            option.setValue("value");
            optGroup.add(option);

            assertNull(select.getValue());
            select.add(option);
            assertEquals("value", select.getValue());
        }
        {
            Select select = new Select();

            OptGroup optGroup = new OptGroup();
            Option option = new Option();
            option.setText("text");
            option.setValue("value");
            optGroup.add(option);
            select.add(optGroup);

            select.addValueChangeHandler(event -> fail());

            assertEquals("value", select.getValue());
            option.removeFromParent();
            assertNull(select.getValue());
        }
        // Only manuel firing and user interactions triggers ValueChangeEvent, adding/removing Option DO NOT TRIGGER ValueChangeEvent
        {
            Select select = new Select();

            select.addValueChangeHandler(event -> fail());

            Option option = new Option();
            option.setText("text");
            option.setValue("value");
            select.add(option);
            assertEquals("value", select.getValue());

            // already selected value won't trigger ValueChangeEvent
            select.setValue("value", true);
        }
        {
            AtomicBoolean called = new AtomicBoolean(false);
            Select select = new Select();

            select.addValueChangeHandler(event -> {
                assertFalse(called.get());
                called.set(true);
                assertEquals("value2", select.getValue());
            });

            Option option1 = new Option();
            option1.setText("text1");
            option1.setValue("value1");
            select.add(option1);

            Option option2 = new Option();
            option2.setText("text2");
            option2.setValue("value2");
            select.add(option2);

            select.setValue("value2", true);

            assertTrue(called.get());
            assertEquals("value2", select.getValue());
        }
        {
            AtomicBoolean called = new AtomicBoolean(false);
            Select select = new Select();

            select.addValueChangeHandler(event -> {
                assertFalse(called.get());
                called.set(true);
                assertEquals("value2", select.getValue());
            });

            Option option1 = new Option();
            option1.setText("text1");
            option1.setValue("value1");
            select.add(option1);

            Option option2 = new Option();
            option2.setText("text2");
            option2.setValue("value2");
            select.add(option2);

            RootPanel.get().add(select);
            assertEquals("value1", select.getValue());

            select.setFocus(true);
            JQuery.jQuery(select.getFocusElement()).trigger("click");
            JQuery.jQuery(select.getElement().getParentElement()).find(":contains('text2')").closest("a").trigger("click");

            assertTrue(called.get());
            assertEquals("value2", select.getValue());
        }
    }
}
