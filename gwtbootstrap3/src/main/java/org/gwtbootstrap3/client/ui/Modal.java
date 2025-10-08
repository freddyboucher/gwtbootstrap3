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
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import org.gwtbootstrap3.client.shared.event.ModalHiddenEvent;
import org.gwtbootstrap3.client.shared.event.ModalHiddenHandler;
import org.gwtbootstrap3.client.shared.event.ModalHideEvent;
import org.gwtbootstrap3.client.shared.event.ModalHideHandler;
import org.gwtbootstrap3.client.shared.event.ModalShowEvent;
import org.gwtbootstrap3.client.shared.event.ModalShowHandler;
import org.gwtbootstrap3.client.shared.event.ModalShownEvent;
import org.gwtbootstrap3.client.shared.event.ModalShownHandler;
import org.gwtbootstrap3.client.shared.js.JQuery;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.base.modal.ModalContent;
import org.gwtbootstrap3.client.ui.base.modal.ModalDialog;
import org.gwtbootstrap3.client.ui.constants.Attributes;
import org.gwtbootstrap3.client.ui.constants.ModalBackdrop;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.html.Div;

/**
 * Modal dialog.
 * <p/>
 * <h3>UiBinder example</h3>
 * <p/>
 * <pre>
 * {@code
 *     <b:Modal title="Important information" b:id="modal1">
 *         <b:ModalBody>
 *             <g:HTML>Lorem ipsum...</g:HTML>
 *         </b:ModalBody>
 *         <b:ModalFooter>
 *             <b:Button type="PRIMARY">Do something</b:Button>
 *             <b:Button type="DANGER" dismiss="MODAL">Close</b:Button>
 *         </b:ModalFooter>
 *     </b:Modal>
 *     <b:Button target="#modal1" toggle="MODAL">Show modal</b:Button>
 * }
 * </pre>
 * <p/>
 * It's also possible to specify a custom modal header:
 * <p/>
 * <pre>
 * {@code
 *     <b:Modal>
 *         <b:ModalHeader>
 *             <g:HTML>
 *                 <h4>Custom header</h4>
 *             </g:HTML>
 *         </b:ModalHeader>
 *         ...
 *     </b:Modal>
 * }
 * </pre>
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 * @see ModalHeader
 * @see ModalBody
 * @see ModalFooter
 * @see ModalShowEvent
 * @see ModalShownEvent
 * @see ModalHideEvent
 * @see ModalHiddenEvent
 */
public class Modal extends Div implements IsClosable {

  private final static String TOGGLE = "toggle";
  private final static String HIDE = "hide";
  private final static String SHOW = "show";

  private final ModalContent content = new ModalContent();
  private final ModalDialog dialog = new ModalDialog();
  private ModalHeader header = new ModalHeader();

  private HandlerRegistration removeOnHideHandlerReg;

  private boolean hideOtherModals;

  public Modal() {
    setStyleName(Styles.MODAL);

    // Set the z-index to match bootstrap's .modal
    getElement().getStyle().setZIndex(1050);

    content.add(header);
    dialog.add(content);

    add(dialog);
  }

  @Override
  public void setWidth(String width) {
    dialog.setWidth(width);
  }

  public void setSize(ModalSize size) {
    StyleHelper.addUniqueEnumStyleName(dialog, ModalSize.class, size);
  }

  @Override
  protected void onLoad() {
    super.onLoad();
    bindJavaScriptEvents(getElement());
  }

  @Override
  protected void onUnload() {
    super.onUnload();
    unbindAllHandlers(getElement());
  }

  @Override
  public void add(Widget w) {
    // User can supply own ModalHeader
    if (w instanceof ModalHeader) {
      header.removeFromParent();
      header = (ModalHeader) w;
    }

    if (w instanceof ModalComponent) {
      content.add(w);
    } else {
      super.add(w);
    }
  }

  @Override
  public void setTitle(String title) {
    header.setTitle(title);
  }

  @Override
  public void setClosable(boolean closable) {
    header.setClosable(closable);
  }

  @Override
  public boolean isClosable() {
    return header.isClosable();
  }

  /**
   * If set to true, when the modal is shown it will force hide all other modals
   *
   * @param hideOtherModals - true to force hide other modals, false to keep them shown
   */
  public void setHideOtherModals(boolean hideOtherModals) {
    this.hideOtherModals = hideOtherModals;
  }

  /**
   * If set to true, will remove the modal from the DOM completely and unbind any events to the modal
   *
   * @param removeOnHide - true to remove modal and unbind events on hide, false to keep it in the DOM
   */
  public void setRemoveOnHide(boolean removeOnHide) {
    if (removeOnHideHandlerReg != null) {
      removeOnHideHandlerReg.removeHandler();
      removeOnHideHandlerReg = null;
    }
    if (removeOnHide) {
      removeOnHideHandlerReg = addHiddenHandler(evt -> {
        // Do logical detach
        removeFromParent();
      });
    }
  }

  /**
   * If set Modal will fade in/out.
   *
   * @param fade If {@code true} modal will fade in/out
   */
  public void setFade(boolean fade) {
    if (fade) {
      addStyleName(Styles.FADE);
    } else {
      removeStyleName(Styles.FADE);
    }
  }

  /**
   * Sets backdrop of modal.
   *
   * @param backdrop Backdrop of modal
   * @see ModalBackdrop
   */
  public void setDataBackdrop(ModalBackdrop backdrop) {
    if (backdrop != null) {
      getElement().setAttribute(Attributes.DATA_BACKDROP, backdrop.getBackdrop());
    } else {
      getElement().removeAttribute(Attributes.DATA_BACKDROP);
    }
  }

  public void setDataKeyboard(boolean keyboard) {
    getElement().setAttribute(Attributes.DATA_KEYBOARD, Boolean.toString(keyboard));

    // tabindex must be set to -1 for ESC key to work
    if (keyboard) {
      getElement().setAttribute(Attributes.TABINDEX, "-1");
    }
  }

  public void toggle() {
    modal(getElement(), TOGGLE);
  }

  public void show() {
    checkIsAttached();
    modal(getElement(), SHOW);
  }

  public void hide() {
    modal(getElement(), HIDE);
  }

  public HandlerRegistration addShowHandler(ModalShowHandler modalShowHandler) {
    return addHandler(modalShowHandler, ModalShowEvent.getType());
  }

  public HandlerRegistration addShownHandler(ModalShownHandler modalShownHandler) {
    return addHandler(modalShownHandler, ModalShownEvent.getType());
  }

  public HandlerRegistration addHideHandler(ModalHideHandler modalHideHandler) {
    return addHandler(modalHideHandler, ModalHideEvent.getType());
  }

  public HandlerRegistration addHiddenHandler(ModalHiddenHandler modalHiddenHandler) {
    return addHandler(modalHiddenHandler, ModalHiddenEvent.getType());
  }

  /**
   * Can be override by subclasses to handle Modal's "show" event however it's
   * recommended to add an event handler to the modal.
   *
   * @param evt Event
   * @see ModalShowEvent
   */
  protected void onShow(Event evt) {
    if (hideOtherModals) {
      hideOtherModals();
    }
    fireEvent(new ModalShowEvent(this, evt));
  }

  /**
   * Can be override by subclasses to handle Modal's "shown" event however
   * it's recommended to add an event handler to the modal.
   *
   * @param evt Event
   * @see ModalShownEvent
   */
  protected void onShown(Event evt) {
    fireEvent(new ModalShownEvent(this, evt));
  }

  /**
   * Can be override by subclasses to handle Modal's "hide" event however it's
   * recommended to add an event handler to the modal.
   *
   * @param evt Event
   * @see ModalHideEvent
   */
  protected void onHide(Event evt) {
    fireEvent(new ModalHideEvent(this, evt));
  }

  /**
   * Can be override by subclasses to handle Modal's "hidden" event however
   * it's recommended to add an event handler to the modal.
   *
   * @param evt Event
   * @see ModalHiddenEvent
   */
  protected void onHidden(Event evt) {
    fireEvent(new ModalHiddenEvent(this, evt));
  }

  private void checkIsAttached() {
    if (!isAttached()) {
      RootPanel.get().add(this);
    }
  }

  private void bindJavaScriptEvents(Element e) {
    JQuery modal = JQuery.jQuery(e);

    modal.on("show.bs.modal", this::onShow);

    modal.on("shown.bs.modal", this::onShown);

    modal.on("hide.bs.modal", this::onHide);

    modal.on("hidden.bs.modal", this::onHidden);
  }

  private void modal(Element e, String arg) {
    JQuery.jQuery(e).modal(arg);
  }

  // Will iterate over all the modals, if they are visible it will hide them
  private void hideOtherModals() {
    JQuery hasfade = JQuery.jQuery(".modal.in.fade");
    hasfade.removeClass("fade");
    JQuery.jQuery(".modal.in").modal("hide");
    hasfade.addClass("fade");
  }

  // Unbinds all the handlers
  private void unbindAllHandlers(Element e) {
    JQuery je = JQuery.jQuery(e);
    je.off("show.bs.modal");
    je.off("shown.bs.modal");
    je.off("hide.bs.modal");
    je.off("hidden.bs.modal");
  }

  public ModalHeader getHeader() {
    return header;
  }

  public ModalContent getContent() {
    return content;
  }

  public ModalDialog getDialog() {
    return dialog;
  }
}
