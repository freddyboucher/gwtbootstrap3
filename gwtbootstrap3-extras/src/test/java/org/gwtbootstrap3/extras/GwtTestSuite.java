package org.gwtbootstrap3.extras;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2014 GwtBootstrap3
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

import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestCase;
import org.gwtbootstrap3.extras.datepicker.client.ui.DatePickerTest;
import org.gwtbootstrap3.extras.notify.client.ui.NotifyTest;
import org.gwtbootstrap3.extras.select.client.ui.SelectTest;
import org.gwtbootstrap3.extras.slider.client.ui.RangeSliderTest;
import org.gwtbootstrap3.extras.slider.client.ui.SliderTest;

public class GwtTestSuite extends TestCase {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("gwtbootstrap3-extras suite");
    suite.addTestSuite(SelectTest.class);
    suite.addTestSuite(NotifyTest.class);
    suite.addTestSuite(DatePickerTest.class);
    suite.addTestSuite(SliderTest.class);
    suite.addTestSuite(RangeSliderTest.class);
    return suite;
  }
}
