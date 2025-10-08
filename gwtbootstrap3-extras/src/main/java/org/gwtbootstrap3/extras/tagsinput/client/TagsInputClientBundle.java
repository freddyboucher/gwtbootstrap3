package org.gwtbootstrap3.extras.tagsinput.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author Marko Nikolić <marko.nikolic@iten.rs>
 */
public interface TagsInputClientBundle extends ClientBundle {
  TagsInputClientBundle INSTANCE = GWT.create(TagsInputClientBundle.class);
  String VERSION = "0.8.0";

  @Source("resource/js/bootstrap-tagsinput-" + VERSION + ".min.cache.js")
  TextResource tagsinput();
}
