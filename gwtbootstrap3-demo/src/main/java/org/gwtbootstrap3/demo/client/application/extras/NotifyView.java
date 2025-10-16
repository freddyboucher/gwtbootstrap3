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
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.animate.client.ui.constants.Animation;
import org.gwtbootstrap3.extras.notify.client.constants.NotifyPlacement;
import org.gwtbootstrap3.extras.notify.client.constants.NotifyType;
import org.gwtbootstrap3.extras.notify.client.ui.Notify;
import org.gwtbootstrap3.extras.notify.client.ui.NotifySettings;

/**
 * @author Xiaodong SUN
 */
public class NotifyView extends ViewImpl implements NotifyPresenter.MyView {

  @UiField
  Button basicNotify;
  @UiField
  Button basicNotifyWithTitleIcon;
  @UiField
  Button changeBackgroundAndDismiss;
  @UiField
  Button positionAndLink;
  @UiField
  Button animationAndOffset;
  @UiField
  Button template;

  @UiHandler("basicNotify")
  public void handleBasicNotify(ClickEvent event) {
    Notify.notify("Message");
  }

  @UiHandler("basicNotifyWithTitleIcon")
  public void handleBasicNotifyWithTitleIcon(ClickEvent event) {
    Notify.notify("Title", "Message", IconType.SMILE_O);
  }

  @UiHandler("showProgressbar")
  public void handleShowProgressbar(ClickEvent event) {
    NotifySettings settings = new NotifySettings();
    settings.showProgressbar = true;
    settings.setPauseOnMouseOver(true);
    Notify.notify("Title", "Message", IconType.SMILE_O, settings);
  }

  @UiHandler("changeBackgroundAndDismiss")
  public void handleChangeBackgroundAndDismiss(ClickEvent event) {
    NotifySettings settings = new NotifySettings();
    settings.setType(NotifyType.SUCCESS);
    settings.allow_dismiss = false;
    Notify.notify("Title", "Message", IconType.SMILE_O, settings);
  }

  @UiHandler("positionAndLink")
  public void handlePositionAndFormatting(ClickEvent event) {
    NotifySettings settings = new NotifySettings();
    settings.setPlacement(NotifyPlacement.TOP_CENTER);
    Notify.notify("Title", "Message", IconType.SMILE_O, "https://github.com/gwtbootstrap3/gwtbootstrap3", settings);
  }

  @UiHandler("animationAndOffset")
  public void handleAnimationAndOffset(ClickEvent event) {
    NotifySettings settings = new NotifySettings();
    settings.setAnimation(Animation.TADA, Animation.LIGHTSPEED_OUT);
    settings.setOffset(200, 140);
    Notify.notify("Title", "Message", IconType.SMILE_O, settings);
  }

  @UiHandler("template")
  public void handleTemplate(ClickEvent event) {
    NotifySettings settings = new NotifySettings();
    settings.template =
        "<div data-notify=\"container\" class=\"col-xs-11 col-sm-4 alert alert-{0}\" role=\"alert\"><button type=\"button\" " +
            "aria-hidden=\"true\" class=\"close\" data-notify=\"dismiss\">&times;</button><span data-notify=\"icon\"></span> <span " +
            "data-notify=\"title\">CUSTOM CODE {1}</span> <span data-notify=\"message\">{2}</span><div class=\"progress\" " +
            "data-notify=\"progressbar\"><div class=\"progress-bar progress-bar-{0}\" role=\"progressbar\" aria-valuenow=\"0\" " +
            "aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 0%;\"></div></div><a href=\"{3}\" target=\"{4}\" " +
            "data-notify=\"url\"></a></div>";
    Notify.notifyDefaults(settings);
    Notify notify = Notify.notify("Title", "Message", IconType.SMILE_O);
  }

  interface Binder extends UiBinder<Widget, NotifyView> {
  }

  @Inject
  NotifyView(Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
