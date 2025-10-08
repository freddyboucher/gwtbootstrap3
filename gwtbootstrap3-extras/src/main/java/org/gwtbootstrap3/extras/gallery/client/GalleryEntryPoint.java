package org.gwtbootstrap3.extras.gallery.client;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2016 GwtBootstrap3
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

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;

/**
 * @author Ben Dol
 */
public class GalleryEntryPoint implements EntryPoint {

  @Override
  public void onModuleLoad() {
    ScriptInjector.fromUrl(GWT.getModuleBaseURL() + GalleryClientBundle.BLUEIMP_JS).setRemoveTag(true).setWindow(ScriptInjector.TOP_WINDOW)
        .setCallback(new Callback<Void, Exception>() {
          @Override
          public void onFailure(Exception reason) {
            throw new RuntimeException(reason);
          }

          @Override
          public void onSuccess(Void result) {
            ScriptInjector.fromUrl(GWT.getModuleBaseURL() + GalleryClientBundle.GALLERY_JS).setRemoveTag(true)
                .setWindow(ScriptInjector.TOP_WINDOW).inject();
          }
        }).inject();
  }
}
