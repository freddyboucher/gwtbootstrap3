package org.gwtbootstrap3.demo.client.ui;

import static org.junit.Assert.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;

public class PrettyPreTest {

  @Test
  public void codeToXml() {
    Pattern pattern = Pattern.compile("^  ", Pattern.MULTILINE);
    String input = "  @UiHandler(\"template\")\n" + "  public void handleTemplate(ClickEvent event) {\n" +
                       "    NotifySettings settings = new NotifySettings();\n" + "    settings.template =\n" +
                       "        \"<div data-notify=\\\"container\\\" class=\\\"col-xs-11 col-sm-4 alert alert-{0}\\\" role=\\\"alert\\\"><button type=\\\"button\\\" \" +\n" +
                       "            \"aria-hidden=\\\"true\\\" class=\\\"close\\\" data-notify=\\\"dismiss\\\">&times;</button><span data-notify=\\\"icon\\\"></span> <span \" +\n" +
                       "            \"data-notify=\\\"title\\\">CUSTOM CODE {1}</span> <span data-notify=\\\"message\\\">{2}</span><div class=\\\"progress\\\" \" +\n" +
                       "            \"data-notify=\\\"progressbar\\\"><div class=\\\"progress-bar progress-bar-{0}\\\" role=\\\"progressbar\\\" aria-valuenow=\\\"0\\\" \" +\n" +
                       "            \"aria-valuemin=\\\"0\\\" aria-valuemax=\\\"100\\\" style=\\\"width: 0%;\\\"></div></div><a href=\\\"{3}\\\" target=\\\"{4}\\\" \" +\n" +
                       "            \"data-notify=\\\"url\\\"></a></div>\";\n" + "    Notify.notifyDefaults(settings);\n" +
                       "    Notify notify = Notify.notify(\"Title\", \"Message\", IconType.SMILE_O);\n" + "  }";
    Matcher matcher = pattern.matcher(input);
    assertEquals("@UiHandler(&quot;template&quot;)\\n\n" + "public void handleTemplate(ClickEvent event) {\\n\n" +
                     "\\sNotifySettings settings = new NotifySettings();\\n\n" + "\\ssettings.template =\\n\n" +
                     "\\s\\s\\s&quot;&lt;div data-notify=\\&quot;container\\&quot; class=\\&quot;col-xs-11 col-sm-4 alert alert-{&#8203;0&#8203;}\\&quot; role=\\&quot;alert\\&quot;&gt;&lt;button type=\\&quot;button\\&quot; &quot; +\\n\n" +
                     "\\s\\s\\s\\s\\s&quot;aria-hidden=\\&quot;true\\&quot; class=\\&quot;close\\&quot; data-notify=\\&quot;dismiss\\&quot;&gt;&amp;times;&lt;/button&gt;&lt;span data-notify=\\&quot;icon\\&quot;&gt;&lt;/span&gt; &lt;span &quot; +\\n\n" +
                     "\\s\\s\\s\\s\\s&quot;data-notify=\\&quot;title\\&quot;&gt;CUSTOM CODE {&#8203;1&#8203;}&lt;/span&gt; &lt;span data-notify=\\&quot;message\\&quot;&gt;{&#8203;2&#8203;}&lt;/span&gt;&lt;div class=\\&quot;progress\\&quot; &quot; +\\n\n" +
                     "\\s\\s\\s\\s\\s&quot;data-notify=\\&quot;progressbar\\&quot;&gt;&lt;div class=\\&quot;progress-bar progress-bar-{&#8203;0&#8203;}\\&quot; role=\\&quot;progressbar\\&quot; aria-valuenow=\\&quot;0\\&quot; &quot; +\\n\n" +
                     "\\s\\s\\s\\s\\s&quot;aria-valuemin=\\&quot;0\\&quot; aria-valuemax=\\&quot;100\\&quot; style=\\&quot;width: 0%;\\&quot;&gt;&lt;/div&gt;&lt;/div&gt;&lt;a href=\\&quot;{&#8203;3&#8203;}\\&quot; target=\\&quot;{&#8203;4&#8203;}\\&quot; &quot; +\\n\n" +
                     "\\s\\s\\s\\s\\s&quot;data-notify=\\&quot;url\\&quot;&gt;&lt;/a&gt;&lt;/div&gt;&quot;;\\n\n" +
                     "\\sNotify.notifyDefaults(settings);\\n\n" +
                     "\\sNotify notify = Notify.notify(&quot;Title&quot;, &quot;Message&quot;, IconType.SMILE_O);\\n\n" + "}",
        StringEscapeUtils.escapeXml11(matcher.replaceAll("")).replaceAll("  ", "\\\\s").replace("\n", "\\n\n")
            .replaceAll("\\{(\\d)\\}", "{&#8203;$1&#8203;}"));
  }
}