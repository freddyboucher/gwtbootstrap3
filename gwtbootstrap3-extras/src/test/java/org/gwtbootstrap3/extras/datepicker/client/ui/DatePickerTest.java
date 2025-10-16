package org.gwtbootstrap3.extras.datepicker.client.ui;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.RootPanel;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import org.gwtbootstrap3.client.GwtBootstrap3EntryPoint;
import org.gwtbootstrap3.extras.datepicker.client.DatePickerEntryPoint;

public class DatePickerTest extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "org.gwtbootstrap3.extras.datepicker.DatePicker";
  }

  @Override
  protected void gwtSetUp() throws Exception {
    super.gwtSetUp();
    new GwtBootstrap3EntryPoint().onModuleLoad();
    new DatePickerEntryPoint().onModuleLoad();
  }

  public void testResetOptions() {
    DatePicker datePicker = new DatePicker();
    RootPanel.get().add(datePicker);
    datePicker.setGWTFormat("yyyy.MM.dd");
    // setValue after setGWTFormat
    datePicker.setValue(new Date(1761096614468L));
    assertEquals("2025.10.22", datePicker.getBaseValue());
  }

  public void testResetOptions2() {
    DatePicker datePicker = new DatePicker();
    RootPanel.get().add(datePicker);
    // setValue before setGWTFormat
    datePicker.setValue(new Date(1761096614468L));
    datePicker.setGWTFormat("yyyy.MM.dd");
    assertEquals("2025.10.22", datePicker.getBaseValue());
  }

  public void testEvent() {
    AtomicBoolean called = new AtomicBoolean(false);
    DatePicker datePicker = new DatePicker();
    datePicker.addShowHandler(showEvent -> called.set(true));
    RootPanel.get().add(datePicker);
    datePicker.show();
    assertTrue(called.get());
  }

  public void testDestroy() {
    AtomicBoolean called = new AtomicBoolean(false);
    DatePicker datePicker = new DatePicker();
    datePicker.destroy();
    datePicker.addShowHandler(showEvent -> called.set(true));
    RootPanel.get().add(datePicker);
    datePicker.show();
    assertFalse(called.get());
  }
}