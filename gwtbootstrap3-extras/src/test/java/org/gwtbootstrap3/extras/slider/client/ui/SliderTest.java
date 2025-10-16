package org.gwtbootstrap3.extras.slider.client.ui;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.RootPanel;
import org.gwtbootstrap3.client.GwtBootstrap3EntryPoint;
import org.gwtbootstrap3.extras.slider.client.SliderEntryPoint;

public class SliderTest extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "org.gwtbootstrap3.extras.slider.Slider";
  }

  @Override
  protected void gwtSetUp() throws Exception {
    super.gwtSetUp();
    new GwtBootstrap3EntryPoint().onModuleLoad();
    new SliderEntryPoint().onModuleLoad();
  }

  public void testSliderNotAttached() {
    Slider slider = new Slider();
    slider.setValue(2d);
    assertEquals(2d, slider.getValue());
  }

  public void testSliderAttached() {
    Slider slider = new Slider();
    RootPanel.get().add(slider);
    slider.setValue(2d);
    assertEquals(2d, slider.getValue());
  }
}