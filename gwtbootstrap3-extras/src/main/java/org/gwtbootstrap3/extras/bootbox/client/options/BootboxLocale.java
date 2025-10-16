package org.gwtbootstrap3.extras.bootbox.client.options;

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

/**
 * Bootbox locale.
 *
 * @author Xiaodong Sun
 */
public enum BootboxLocale {

  AR("ar"),
  AZ("az"),
  BG_BG("bg_BG"),
  CS("cs"),
  DA("da"),
  DE("de"),
  EL("el"),
  EN("en"),
  ES("es"),
  ET("et"),
  EU("eu"),
  FA("fa"),
  FI("fi"),
  FR("fr"),
  HE("he"),
  HR("hr"),
  HU("hu"),
  ID("id"),
  IT("it"),
  JA("ja"),
  KA("ka"),
  KO("ko"),
  LT("lt"),
  LV("lv"),
  NL("nl"),
  NO("no"),
  PL("pl"),
  PT_BR("pt-BR"),
  PT("pt"),
  RU("ru"),
  SK("sk"),
  SL("sl"),
  SQ("sq"),
  SV("sv"),
  SW("sw"),
  TA("ta"),
  TH("th"),
  TR("tr"),
  UK("uk"),
  VI("vi"),
  ZH_CN("zh_CN"),
  ZH_TW("zh_TW"),
  ;

  private final String locale;

  BootboxLocale(String locale) {
    this.locale = locale;
  }

  /**
   * Returns the locale.
   *
   * @return the locale
   */
  public String getLocale() {
    return locale;
  }

  /**
   * Returns the default locale: {@link #EN}.
   *
   * @return the default locale.
   */
  public static BootboxLocale getDefault() {
    return EN;
  }
}
