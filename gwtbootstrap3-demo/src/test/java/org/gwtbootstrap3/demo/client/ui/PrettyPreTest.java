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
    Matcher matcher = pattern.matcher("  protected void addEvents(FullCalendar fc) {\n" + "    List<Event> events = new ArrayList<>();\n" +
                                          "    for (int i = 0; i < 15; i++) {\n" +
                                          "      Event calEvent = new Event(\"\" + i, \"This is Event: \" + i);\n" +
                                          "      int day = Random.nextInt(10);\n" + "      Date start = new Date();\n" +
                                          "      CalendarUtil.addDaysToDate(start, -1 * day);\n" + "      calEvent.setStart(start);\n" +
                                          "      if (day % 3 == 0) {\n" + "        calEvent.setAllDay(true);\n" + "      } else {\n" +
                                          "        Date d = new Date(start.getTime());\n" + "        d.setHours(d.getHours() + 1);\n" +
                                          "        calEvent.setEnd(d);\n" + "      }\n" + "      events.add(calEvent);\n" + "    }\n" +
                                          "    fc.addEvents(events);\n" + "  }");
    assertEquals("protected void addEvents(FullCalendar fc) {\\n\n" + "\\sList&lt;Event&gt; events = new ArrayList&lt;&gt;();\\n\n" +
                     "\\sfor (int i = 0; i &lt; 15; i++) {\\n\n" +
                     "\\s\\sEvent calEvent = new Event(&quot;&quot; + i, &quot;This is Event: &quot; + i);\\n\n" +
                     "\\s\\sint day = Random.nextInt(10);\\n\n" + "\\s\\sDate start = new Date();\\n\n" +
                     "\\s\\sCalendarUtil.addDaysToDate(start, -1 * day);\\n\n" + "\\s\\scalEvent.setStart(start);\\n\n" +
                     "\\s\\sif (day % 3 == 0) {\\n\n" + "\\s\\s\\scalEvent.setAllDay(true);\\n\n" + "\\s\\s} else {\\n\n" +
                     "\\s\\s\\sDate d = new Date(start.getTime());\\n\n" + "\\s\\s\\sd.setHours(d.getHours() + 1);\\n\n" +
                     "\\s\\s\\scalEvent.setEnd(d);\\n\n" + "\\s\\s}\\n\n" + "\\s\\sevents.add(calEvent);\\n\n" + "\\s}\\n\n" +
                     "\\sfc.addEvents(events);\\n\n" + "}",
        StringEscapeUtils.escapeXml11(matcher.replaceAll("")).replaceAll("  ", "\\\\s").replace("\n", "\\n\n"));
  }
}