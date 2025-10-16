package org.gwtbootstrap3.extras.slider.client.ui;

import static org.gwtbootstrap3.client.shared.js.JQuery.$;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.RootPanel;
import org.gwtbootstrap3.client.GwtBootstrap3EntryPoint;
import org.gwtbootstrap3.extras.slider.client.SliderEntryPoint;
import org.gwtbootstrap3.extras.slider.client.ui.base.FormatterCallback;
import org.junit.Ignore;

public class RangeSliderTest extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "org.gwtbootstrap3.extras.slider.Slider";
  }

  @Override
  protected void gwtSetUp() throws Exception {
    super.gwtSetUp();
    RootPanel.get().clear();
    new GwtBootstrap3EntryPoint().onModuleLoad();
    new SliderEntryPoint().onModuleLoad();
  }

  public void testSliderNotAttached() {
    RangeSlider slider = new RangeSlider();
    Range value = Range.create(1d, 2d);
    slider.setValue(value);
    assertEquals(value.toString(), slider.getValue().toString());
  }

  public void testSliderAttached() {
    RangeSlider slider = new RangeSlider();
    RootPanel.get().add(slider);
    Range value = Range.create(1d, 2d);
    slider.setValue(value);
    assertEquals(value.toString(), slider.getValue().toString());
  }

  @Ignore
  public void testJsFunctionFormatter() {
    RangeSlider slider = new RangeSlider();
    RootPanel.get().add(slider);
    // using @JsFunction
    slider.setFormatter(range -> "Range: [" + range.getAt(0) + "," + range.getAt(1) + "]");
    slider.setValue(Range.create(1d, 2d));
    assertEquals("Range: [1,2]", $(slider.getParent()).find(".tooltip-main .tooltip-inner").text());
  }

  public void testJsniFormatter() {
    RangeSlider slider = new RangeSlider();
    RootPanel.get().add(slider);
    // using jsni
    slider.setFormatter(rangeFormatter());
    slider.setValue(Range.create(1d, 2d));
    assertEquals("Range: [1,2]", $(slider.getParent()).find(".tooltip-main .tooltip-inner").text());
  }

  private native FormatterCallback<Range> rangeFormatter() /*-{
    return function (value) {
      return "Range: [" + value[0] + "," + value[1] + "]";
    };
  }-*/;
}