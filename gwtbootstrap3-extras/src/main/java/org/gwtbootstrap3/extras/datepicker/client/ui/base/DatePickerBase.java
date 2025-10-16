package org.gwtbootstrap3.extras.datepicker.client.ui.base;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2016 GwtBootstrap3
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

import static org.gwtbootstrap3.extras.datepicker.client.ui.base.DatePickerBase.DatePickerJQuery.$;

import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.Element;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import elemental2.core.JsDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.JsPropertyMap;
import org.gwtbootstrap3.client.shared.event.HideEvent;
import org.gwtbootstrap3.client.shared.event.HideHandler;
import org.gwtbootstrap3.client.shared.event.ShowEvent;
import org.gwtbootstrap3.client.shared.event.ShowHandler;
import org.gwtbootstrap3.client.shared.js.JQuery;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.base.HasId;
import org.gwtbootstrap3.client.ui.base.HasPlaceholder;
import org.gwtbootstrap3.client.ui.base.HasResponsiveness;
import org.gwtbootstrap3.client.ui.base.ValueBoxBase;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.base.mixin.BlankValidatorMixin;
import org.gwtbootstrap3.client.ui.base.mixin.ErrorHandlerMixin;
import org.gwtbootstrap3.client.ui.constants.DeviceSize;
import org.gwtbootstrap3.client.ui.form.error.ErrorHandler;
import org.gwtbootstrap3.client.ui.form.error.ErrorHandlerType;
import org.gwtbootstrap3.client.ui.form.error.HasErrorHandler;
import org.gwtbootstrap3.client.ui.form.validator.HasBlankValidator;
import org.gwtbootstrap3.client.ui.form.validator.HasValidators;
import org.gwtbootstrap3.client.ui.form.validator.ValidationChangedEvent.ValidationChangedHandler;
import org.gwtbootstrap3.client.ui.form.validator.Validator;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.DatePickerDayOfWeek;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.DatePickerLanguage;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.DatePickerMinView;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.DatePickerPosition;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.HasAutoClose;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.HasDateTimePickerHandlers;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.HasDaysOfWeekDisabled;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.HasEndDate;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.HasForceParse;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.HasFormat;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.HasHighlightToday;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.HasKeyboardNavigation;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.HasLanguage;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.HasMinView;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.HasPosition;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.HasShowClearButton;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.HasShowTodayButton;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.HasStartDate;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.HasStartView;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.constants.HasWeekStart;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.events.ChangeDateEvent;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.events.ChangeDateHandler;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.events.ChangeMonthEvent;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.events.ChangeMonthHandler;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.events.ChangeYearEvent;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.events.ChangeYearHandler;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.events.ClearDateEvent;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.events.ClearDateHandler;

/**
 * @author Joshua Godi
 * @author Steven Jardine
 */
public class DatePickerBase extends Widget
    implements HasEnabled, HasId, HasResponsiveness, HasVisibility, HasPlaceholder, HasAutoClose, HasDaysOfWeekDisabled, HasEndDate,
                   HasForceParse, HasFormat, HasHighlightToday, HasKeyboardNavigation, HasMinView, HasShowTodayButton, HasShowClearButton,
                   HasStartDate, HasStartView, HasWeekStart, HasDateTimePickerHandlers, HasLanguage, HasName, HasValue<Date>, HasPosition,
                   LeafValueEditor<Date>, HasEditorErrors<Date>, HasErrorHandler, HasValidators<Date>, HasBlankValidator<Date> {

  private final TextBox textBox;
  private final ErrorHandlerMixin<Date> errorHandlerMixin = new ErrorHandlerMixin<>(this);
  private final DatePickerValidatorMixin validatorMixin = new DatePickerValidatorMixin(this, errorHandlerMixin.getErrorHandler());

  /**
   * DEFAULT values
   */
  private String format = "mm/dd/yyyy";
  private DatePickerDayOfWeek weekStart = DatePickerDayOfWeek.SUNDAY;
  private DatePickerDayOfWeek[] daysOfWeekDisabled = {};
  private boolean autoClose;
  private DatePickerMinView startView = DatePickerMinView.DAY;
  private DatePickerMinView minView = DatePickerMinView.DAY;

  private boolean showTodayButton;
  private boolean showClearButton;
  private boolean highlightToday;
  private boolean keyboardNavigation = true;
  private boolean forceParse = true;

  private Widget container;
  private DatePickerLanguage language = DatePickerLanguage.EN;
  private DatePickerPosition position = DatePickerPosition.AUTO;

  public DatePickerBase() {
    textBox = new TextBox();
    setElement((Element) textBox.getElement());
    $(this).datepicker(createOptions()).on("show", this::onShow).on("hide", this::onHide).on("changeDate", this::onChangeDate)
        .on("changeYear", this::onChangeYear).on("changeMonth", this::onChangeMonth).on("clearDate", this::onClearDate);
  }

  public void setContainer(Widget container) {
    this.container = container;
  }

  public Widget getContainer() {
    return container;
  }

  public TextBox getTextBox() {
    return textBox;
  }

  public void setAlignment(ValueBoxBase.TextAlignment align) {
    textBox.setAlignment(align);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setPlaceholder(String placeHolder) {
    textBox.setPlaceholder(placeHolder);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPlaceholder() {
    return textBox.getPlaceholder();
  }

  public void setReadOnly(boolean readOnly) {
    textBox.setReadOnly(readOnly);
  }

  public boolean isReadOnly() {
    return textBox.isReadOnly();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEnabled() {
    return textBox.isEnabled();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    textBox.setEnabled(enabled);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setId(String id) {
    textBox.setId(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getId() {
    return textBox.getId();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setName(String name) {
    textBox.setName(name);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return textBox.getName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setVisibleOn(DeviceSize deviceSize) {
    StyleHelper.setVisibleOn(this, deviceSize);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setHiddenOn(DeviceSize deviceSize) {
    StyleHelper.setHiddenOn(this, deviceSize);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setLanguage(DatePickerLanguage language) {
    this.language = language;

    // Inject the JS for the language
    if (language.getJs() != null && !language.isInjected()) {
      ScriptInjector.fromString(language.getJs().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
      language.setInjected(true);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DatePickerLanguage getLanguage() {
    return language;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setPosition(DatePickerPosition position) {
    this.position = position;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DatePickerPosition getPosition() {
    return position;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setAutoClose(boolean autoClose) {
    this.autoClose = autoClose;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onShow(Event e) {
    validatorMixin.setShowing(true);
    fireEvent(new ShowEvent(e));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public HandlerRegistration addShowHandler(ShowHandler showHandler) {
    return addHandler(showHandler, ShowEvent.getType());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onHide(Event e) {
    validatorMixin.setShowing(false);
    validate(getValidateOnBlur());
    fireEvent(new HideEvent(e));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public HandlerRegistration addHideHandler(HideHandler hideHandler) {
    return addHandler(hideHandler, HideEvent.getType());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onChangeDate(Event e) {
    fireEvent(new ChangeDateEvent(e));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public HandlerRegistration addChangeDateHandler(ChangeDateHandler changeDateHandler) {
    return addHandler(changeDateHandler, ChangeDateEvent.getType());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onChangeYear(Event e) {
    fireEvent(new ChangeYearEvent(e));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public HandlerRegistration addChangeYearHandler(ChangeYearHandler changeYearHandler) {
    return addHandler(changeYearHandler, ChangeYearEvent.getType());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onChangeMonth(Event e) {
    fireEvent(new ChangeMonthEvent(e));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public HandlerRegistration addChangeMonthHandler(ChangeMonthHandler changeMonthHandler) {
    return addHandler(changeMonthHandler, ChangeMonthEvent.getType());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onClearDate(Event e) {
    fireEvent(new ClearDateEvent(e));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public HandlerRegistration addClearDateHandler(ClearDateHandler clearDateHandler) {
    return addHandler(clearDateHandler, ClearDateEvent.getType());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setDaysOfWeekDisabled(DatePickerDayOfWeek... daysOfWeekDisabled) {
    $(this).datepicker("setDaysOfWeekDisabled", toDaysOfWeekDisabledString(daysOfWeekDisabled));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEndDate(Date endDate) {
    $(this).datepicker("setEndDate", endDate != null ? new JsDate((double) endDate.getTime()) : null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEndDate(String endDate) {
    $(this).datepicker("setEndDate", endDate);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clearEndDate() {
    setStartDate((String) null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setForceParse(boolean forceParse) {
    this.forceParse = forceParse;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setHighlightToday(boolean highlightToday) {
    this.highlightToday = highlightToday;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setHasKeyboardNavigation(boolean hasKeyboardNavigation) {
    keyboardNavigation = hasKeyboardNavigation;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setMinView(DatePickerMinView datePickerMinView) {
    minView = datePickerMinView;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setShowTodayButton(boolean showTodayButton) {
    this.showTodayButton = showTodayButton;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setShowClearButton(boolean showClearbutton) {
    showClearButton = showClearbutton;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setStartDate(Date startDate) {
    $(this).datepicker("setStartDate", startDate != null ? new JsDate((double) startDate.getTime()) : null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setStartDate(String startDate) {
    $(this).datepicker("setStartDate", startDate);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clearStartDate() {
    setStartDate((String) null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setStartView(DatePickerMinView datePickerMinView) {
    startView = datePickerMinView;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setWeekStart(DatePickerDayOfWeek weekStart) {
    this.weekStart = weekStart;
  }

  /**
   * Convert GWT date format to bootstrap date format
   *
   * @param format date format using GWT notation
   * @return date format using bootstrap notation
   */
  private static String toBootstrapDateFormat(String format) {
    String bootstrap_format = format;

    // Replace long day name "EEEE" with "DD"
    bootstrap_format = bootstrap_format.replace("EEEE", "DD");
    // Replace short day name "EE" with "DD"
    bootstrap_format = bootstrap_format.replaceAll("E{1,3}", "D");
    // If there are at least 3 Ms there is month name in wording
    if (bootstrap_format.contains("MMM")) {
      // Replace long date month "MMMM" with "MM"
      bootstrap_format = bootstrap_format.replace("MMMM", "MM");
      // Replace month name "MMM" with "M"
      bootstrap_format = bootstrap_format.replace("MMM", "M");
    } else {
      // Replace month number with leading 0 "MM" with "mm"
      bootstrap_format = bootstrap_format.replace("MM", "mm");
      // Replace month number "M" with "m"
      bootstrap_format = bootstrap_format.replace("M", "m");
    }
    if (!bootstrap_format.contains("yy")) {
      // Replace full year format "y" with "yyyy"
      bootstrap_format = bootstrap_format.replace("y", "yyyy");
    }

    return bootstrap_format;
  }

  /**
   * Sets format of the date using GWT notation
   *
   * @param format date format in GWT notation
   */
  public void setGWTFormat(String format) {
    setFormat(toBootstrapDateFormat(format));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setFormat(String format) {
    Date date = getValue();
    this.format = format;
    resetOptions();
    setValue(date);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Date getValue() {
    JsDate jsDate = $(this).datepicker("getDate").cast();
    return jsDate != null ? new Date((long) jsDate.getTime()) : null;
  }

  public String getBaseValue() {
    return textBox.getValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Date> dateValueChangeHandler) {
    textBox.addValueChangeHandler(event -> ValueChangeEvent.fire(this, getValue()));
    return addHandler(dateValueChangeHandler, ValueChangeEvent.getType());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(Date value) {
    setValue(value, false);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(Date value, boolean fireEvents) {
    errorHandlerMixin.clearErrors();
    $(this).datepicker("update", value != null ? new JsDate((double) value.getTime()) : "");

    if (fireEvents) {
      ValueChangeEvent.fire(this, value);
    }
  }

  public void resetOptions() {
    $(this).datepicker("destroy");
    $(this).datepicker(createOptions());
  }

  protected JsPropertyMap createOptions() {
    JsPropertyMap options = JsPropertyMap.of();
    options.set("format", format);
    options.set("language", language.getCode());
    options.set("weekStart", weekStart.getValue());
    options.set("daysOfWeekDisabled", toDaysOfWeekDisabledString(daysOfWeekDisabled));
    options.set("autoclose", autoClose);
    options.set("startView", startView.getValue());
    options.set("minViewMode", minView.getValue());
    options.set("todayBtn", showTodayButton ? "linked" : false);
    options.set("clearBtn", showClearButton);
    options.set("todayHighlight", highlightToday);
    options.set("keyboardNavigation", keyboardNavigation);
    options.set("forceParse", forceParse);
    options.set("orientation", position.getPosition());
    if (container != null) {
      options.set("container", container.getElement());
    }
    return options;
  }

  public void destroy() {
    $(this).datepicker("destroy");
    $(this).off("show");
    $(this).off("hide");
    $(this).off("changeDate");
    $(this).off("changeYear");
    $(this).off("changeMonth");
    $(this).off("clearDate");
  }

  public void show() {
    $(this).datepicker("show");
  }

  public void hide() {
    $(this).datepicker("hide");
  }

  public void update() {
    $(this).datepicker("update");
  }

  protected String toDaysOfWeekDisabledString(DatePickerDayOfWeek... datePickerDayOfWeeks) {
    daysOfWeekDisabled = datePickerDayOfWeeks;
    if (datePickerDayOfWeeks != null) {
      return Stream.of(datePickerDayOfWeeks).map(DatePickerDayOfWeek::getValue).map(String::valueOf).collect(Collectors.joining(","));
    }
    return "";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public com.google.web.bindery.event.shared.HandlerRegistration addValidationChangedHandler(ValidationChangedHandler handler) {
    return validatorMixin.addValidationChangedHandler(handler);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean getAllowBlank() {
    return validatorMixin.getAllowBlank();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setAllowBlank(boolean allowBlank) {
    validatorMixin.setAllowBlank(allowBlank);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addValidator(Validator<Date> validator) {
    validatorMixin.addValidator(validator);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean getValidateOnBlur() {
    return validatorMixin.getValidateOnBlur();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean removeValidator(Validator<Date> validator) {
    return validatorMixin.removeValidator(validator);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    validatorMixin.reset();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValidateOnBlur(boolean validateOnBlur) {
    validatorMixin.setValidateOnBlur(validateOnBlur);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValidators(Validator<Date>... validators) {
    validatorMixin.setValidators(validators);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean validate() {
    return validatorMixin.validate();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean validate(boolean show) {
    return validatorMixin.validate(show);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ErrorHandler getErrorHandler() {
    return errorHandlerMixin.getErrorHandler();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setErrorHandler(ErrorHandler errorHandler) {
    errorHandlerMixin.setErrorHandler(errorHandler);
    validatorMixin.setErrorHandler(errorHandler);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ErrorHandlerType getErrorHandlerType() {
    return errorHandlerMixin.getErrorHandlerType();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setErrorHandlerType(ErrorHandlerType errorHandlerType) {
    errorHandlerMixin.setErrorHandlerType(errorHandlerType);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void showErrors(List<EditorError> errors) {
    errorHandlerMixin.showErrors(errors);
  }

  @JsType(isNative = true, namespace = JsPackage.GLOBAL)
  public static class DatePickerJQuery extends JQuery {
    @JsMethod(namespace = JsPackage.GLOBAL, name = "$")
    public static native DatePickerJQuery $(Element element);

    @JsOverlay
    public static DatePickerJQuery $(IsWidget widget) {
      return $(widget.asWidget().getElement());
    }

    public native DatePickerJQuery datepicker(String cmd);

    public native DatePickerJQuery datepicker(Object cmd);

    public native DatePickerJQuery datepicker(String cmd, Object value);
  }

  static class DatePickerValidatorMixin extends BlankValidatorMixin<DatePickerBase, Date> {

    private boolean showing;

    public void setShowing(boolean showing) {
      this.showing = showing;
    }

    public DatePickerValidatorMixin(DatePickerBase inputWidget, ErrorHandler errorHandler) {
      super(inputWidget, errorHandler);
    }

    @Override
    protected com.google.web.bindery.event.shared.HandlerRegistration setupBlurValidation() {
      return getInputWidget().addDomHandler(event -> getInputWidget().validate(!showing && getValidateOnBlur()), BlurEvent.getType());
    }
  }
}
