package org.gwtbootstrap3.demo.client.application.extras;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2015 GwtBootstrap3
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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import org.gwtbootstrap3.client.ui.html.Paragraph;
import org.gwtbootstrap3.extras.slider.client.ui.Range;
import org.gwtbootstrap3.extras.slider.client.ui.RangeSlider;
import org.gwtbootstrap3.extras.slider.client.ui.Slider;
import org.gwtbootstrap3.extras.slider.client.ui.base.FormatterCallback;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideDisabledEvent;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideEnabledEvent;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideEvent;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideStartEvent;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideStopEvent;

/**
 * @author Xiaodong SUN
 */
public class SliderView extends ViewImpl implements SliderPresenter.MyView {

  @UiField
  Slider basicExample;
  @UiField
  RangeSlider rangeExample;
  @UiField
  Slider verticalExample;
  @UiField
  Slider formatterExample;
  @UiField
  RangeSlider rangeFormatterExample;
  @UiField
  Slider eventExample;
  @UiField
  RangeSlider eventRangeExample;
  @UiField
  FlowPanel logRow;

  interface Binder extends UiBinder<Widget, SliderView> {
  }

  @Inject
  SliderView(Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));

    formatterExample.setFormatter(value -> "Current value: " + value);

    // As of GWT 2.12.2, you must define the FormatterCallback with JSNI
    rangeFormatterExample.setFormatter(rangeFormatter());

    // This won't work
    //    rangeFormatterExample.setFormatter(range -> "Range: [" + range.getMinValue() + ", " + range.getMaxValue() + "]");
  }

  private native FormatterCallback<Range> rangeFormatter() /*-{
    return function (value) {
      return "Range: [" + value[0] + ", " + value[1] + "]";
    };
  }-*/;

  @UiHandler("enable")
  void onEnable(ClickEvent event) {
    basicExample.setEnabled(true);
  }

  @UiHandler("disable")
  void onDisable(ClickEvent event) {
    basicExample.setEnabled(false);
  }

  @UiHandler("toggle")
  void onToggle(ClickEvent event) {
    basicExample.toggle();
  }

  @UiHandler("isEnabled")
  void onIsEnabled(ClickEvent event) {
    Window.alert("" + basicExample.isEnabled());
  }

  @UiHandler("getValue")
  void onGetValue(ClickEvent event) {
    Window.alert(basicExample.getValue().toString());
  }

  @UiHandler("setValue")
  void onSetValue(ClickEvent event) {
    basicExample.setValue(10D);
  }

  @UiHandler("rangeEnable")
  void onRangeDisableEnable(ClickEvent event) {
    rangeExample.setEnabled(true);
  }

  @UiHandler("rangeDisable")
  void onRangeDisableDisable(ClickEvent event) {
    rangeExample.setEnabled(false);
  }

  @UiHandler("rangeToggle")
  void onRangeToggle(ClickEvent event) {
    rangeExample.toggle();
  }

  @UiHandler("rangeIsEnabled")
  void onRangeIsEnabled(ClickEvent event) {
    Window.alert("" + rangeExample.isEnabled());
  }

  @UiHandler("rangeGetValue")
  void onRangeGetValue(ClickEvent event) {
    Window.alert(rangeExample.getValue().toString());
  }

  @UiHandler("rangeSetValue")
  void onRangeSetValue(ClickEvent event) {
    rangeExample.setValue(Range.create(400, 700));
  }

  @UiHandler("verticalReversed")
  void onVerticalReversed(ClickEvent event) {
    verticalExample.setReversed(!verticalExample.isReversed());
  }

  @UiHandler("eventExample")
  void onSlide(SlideEvent<Double> event) {
    addEventLog("slide", "value = " + event.getValue());
  }

  @UiHandler("eventExample")
  void onSlideStart(SlideStartEvent<Double> event) {
    addEventLog("slideStart", "value = " + event.getValue());
  }

  @UiHandler("eventExample")
  void onSlideStop(SlideStopEvent<Double> event) {
    addEventLog("slideStop", "value = " + event.getValue());
  }

  @UiHandler("eventExample")
  void onChange(ValueChangeEvent<Double> event) {
    addEventLog("change", "value = " + event.getValue());
  }

  @UiHandler("eventExample")
  void onSlideEnabled(SlideEnabledEvent event) {
    addEventLog("slideEnabled", "");
  }

  @UiHandler("eventExample")
  void onSlideDisabled(SlideDisabledEvent event) {
    addEventLog("slideDisabled", "");
  }

  @UiHandler("eventToggle")
  void onEventToggle(ClickEvent event) {
    eventExample.toggle();
  }

  @UiHandler("eventSetValue")
  void onEventSetValue(ClickEvent event) {
    eventExample.setValue(50D);
  }

  @UiHandler("eventRangeExample")
  void onRangeSlide(SlideEvent<Range> event) {
    addEventLog("slide", "value = " + event.getValue());
  }

  @UiHandler("eventRangeExample")
  void onRangeSlideStart(SlideStartEvent<Range> event) {
    addEventLog("slideStart", "value = " + event.getValue());
  }

  @UiHandler("eventRangeExample")
  void onRangeSlideStop(SlideStopEvent<Range> event) {
    addEventLog("slideStop", "value = " + event.getValue());
  }

  @UiHandler("eventRangeExample")
  void onRangeChange(ValueChangeEvent<Range> event) {
    addEventLog("change", "value = " + event.getValue());
  }

  @UiHandler("eventRangeExample")
  void onRangeSlideEnabled(SlideEnabledEvent event) {
    addEventLog("slideEnabled", "");
  }

  @UiHandler("eventRangeExample")
  void onRangeSlideDisabled(SlideDisabledEvent event) {
    addEventLog("slideDisabled", "");
  }

  @UiHandler("eventRangeToggle")
  void onEventRangeToggle(ClickEvent event) {
    eventRangeExample.toggle();
  }

  @UiHandler("eventRangeSetValue")
  void onEventRangeSetValue(ClickEvent event) {
    eventRangeExample.setValue(Range.create(20, 50));
  }

  private void addEventLog(String eventName, String logSuffix) {
    Paragraph logEntry = new Paragraph();
    logEntry.setHTML("<b>" + eventName + "</b> event fired! " + logSuffix);
    logRow.add(logEntry);
  }

  @UiHandler("clearLogButton")
  void onClickclearLogButton(ClickEvent event) {
    logRow.clear();
  }
}
