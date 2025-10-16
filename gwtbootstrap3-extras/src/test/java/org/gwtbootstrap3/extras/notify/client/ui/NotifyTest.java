package org.gwtbootstrap3.extras.notify.client.ui;

import com.google.gwt.junit.client.GWTTestCase;
import org.gwtbootstrap3.client.GwtBootstrap3EntryPoint;
import org.gwtbootstrap3.extras.notify.client.NotifyEntryPoint;

public class NotifyTest extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "org.gwtbootstrap3.extras.notify.Notify";
  }

  @Override
  protected void gwtSetUp() throws Exception {
    super.gwtSetUp();
    new GwtBootstrap3EntryPoint().onModuleLoad();
    new NotifyEntryPoint().onModuleLoad();
  }

  public void testNotify() {
    Notify notify = Notify.notify("test");
    assertEquals("<div data-notify=\"container\" class=\"col-xs-11 col-sm-4 alert alert-info animated fadeInDown\" role=\"alert\" " +
                     "data-notify-position=\"top-right\" style=\"display: inline-block; margin: 0px auto; position: fixed; transition: " +
                     "all 0.5s " +
                     "ease-in-out; z-index: 1031; top: 20px; right: 20px; webkit-animation-iteration-count: 1;\"><button type=\"button\" " +
                     "aria-hidden=\"true\" class=\"close\" data-notify=\"dismiss\">×</button><span data-notify=\"icon\"></span> <span " +
                     "data-notify=\"title\"></span> <span data-notify=\"message\">test</span><a href=\"#\" target=\"_blank\" " +
                     "data-notify=\"url\"></a></div>", notify.$ele.get(0).getString());
    notify.updateMessage("xxxx");
    assertEquals(
        "<div data-notify=\"container\" class=\"col-xs-11 col-sm-4 alert alert-info animated fadeInDown\" role=\"alert\" " +
            "data-notify-position=\"top-right\" style=\"display: inline-block; margin: 0px auto; position: fixed; transition: all 0.5s " +
            "ease-in-out; z-index: 1031; top: 20px; right: 20px; webkit-animation-iteration-count: 1;\"><button type=\"button\" " +
            "aria-hidden=\"true\" class=\"close\" data-notify=\"dismiss\">×</button><span data-notify=\"icon\"></span> <span " +
            "data-notify=\"title\"></span> <span data-notify=\"message\">xxxx</span><a href=\"#\" target=\"_blank\" " +
            "data-notify=\"url\"></a></div>",
        notify.$ele.get(0).getString());
  }
}