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

import static org.gwtbootstrap3.client.shared.js.JQuery.$;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import jsinterop.base.JsPropertyMap;
import org.gwtbootstrap3.client.shared.event.CarouselSlidEvent;
import org.gwtbootstrap3.client.shared.event.CarouselSlidHandler;
import org.gwtbootstrap3.client.shared.event.CarouselSlideEvent;
import org.gwtbootstrap3.client.shared.event.CarouselSlideHandler;
import org.gwtbootstrap3.client.shared.js.JQuery;
import org.gwtbootstrap3.client.ui.constants.Attributes;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.html.Div;

/**
 * @author Joshua Godi
 */
public class Carousel extends Div {
  public static final String HOVER = "hover";
  public static final String CAROUSEL = "carousel";
  public static final String CYCLE = "cycle";
  public static final String PAUSE = "pause";
  public static final String PREV = "prev";
  public static final String NEXT = "next";

  // Bootstrap default values: http://getbootstrap.com/javascript/#carousel
  private int interval = 5000;
  private String pause = HOVER;
  private boolean wrap = true;

  public Carousel() {
    // Set the default styles
    setStyleName(Styles.CAROUSEL);
    addStyleName(Styles.SLIDE);

    // Set the default attribute
    getElement().setAttribute(Attributes.DATA_RIDE, CAROUSEL);
  }

  @Override
  protected void onLoad() {
    super.onLoad();

    // Bind jquery events
    bindJavaScriptEvents(getElement());

    // Configure the carousel
    $(this).carousel(JsPropertyMap.of("interval", interval, "pause", pause, "wrap", wrap));
  }

  @Override
  protected void onUnload() {
    super.onUnload();

    // Unbind events
    unbindJavaScriptEvents(getElement());
  }

  public void setInterval(int interval) {
    this.interval = interval;
  }

  public void setPause(String pause) {
    this.pause = pause;
  }

  public void setWrap(boolean wrap) {
    this.wrap = wrap;
  }

  /**
   * Causes the carousel to cycle
   */
  public void cycleCarousel() {
    fireMethod(getElement(), CYCLE);
  }

  /**
   * Causes the carousel to pause movement
   */
  public void pauseCarousel() {
    fireMethod(getElement(), PAUSE);
  }

  /**
   * Causes the carousel to jump to that slide
   */
  public void jumpToSlide(int slideNumber) {
    fireMethod(getElement(), slideNumber);
  }

  /**
   * Causes the carousel to go back
   */
  public void goToPrev() {
    fireMethod(getElement(), PREV);
  }

  /**
   * Causes the carousel to go to the next slide
   */
  public void goToNext() {
    fireMethod(getElement(), NEXT);
  }

  public HandlerRegistration addSlideHandler(CarouselSlideHandler carouselSlideHandler) {
    return addHandler(carouselSlideHandler, CarouselSlideEvent.getType());
  }

  public HandlerRegistration addSlidHandler(CarouselSlidHandler slidHandler) {
    return addHandler(slidHandler, CarouselSlidEvent.getType());
  }

  /**
   * Fired when the carousel is starting to change slides
   *
   * @param evt event
   */
  private void onSlide(Event evt) {
    fireEvent(new CarouselSlideEvent(this, evt));
  }

  /**
   * Fired when the carousel is finished changing slides
   *
   * @param evt event
   */
  private void onSlid(Event evt) {
    fireEvent(new CarouselSlidEvent(this, evt));
  }

  private void bindJavaScriptEvents(Element e) {
    JQuery carousel = $(e);

    carousel.on("slide.bs.carousel", this::onSlide);

    carousel.on("slid.bs.carousel", this::onSlid);
  }

  private void unbindJavaScriptEvents(Element e) {
    $(e).off("slide.bs.carousel");
    $(e).off("slid.bs.carousel");
  }

  private void fireMethod(Element e, String method) {
    $(e).carousel(method);
  }

  private void fireMethod(Element e, int slideNumber) {
    $(e).carousel(slideNumber);
  }
}
