package com.alexanthony.dreambumps.web.rest;

import com.alexanthony.dreambumps.config.Constants;
import com.alexanthony.dreambumps.domain.Crew;
import com.alexanthony.dreambumps.domain.CrewMember;
import com.alexanthony.dreambumps.domain.CrewPositionHistory;
import com.alexanthony.dreambumps.domain.enumeration.Sex;
import org.apache.commons.lang3.text.WordUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by alex on 01/05/17.
 */
public class JSoupScratch {

  @Test
  public void listColleges() throws IOException {
    Document entriesPage = Jsoup.connect("https://entries.ourcs.org.uk/events/event/174/crew_lists/").get();
    Elements links = entriesPage.getElementsByTag("a");
    for (Element link : links) {
      if (link.attributes().get("href").startsWith("#club-")) {
        System.out.println("{value: '"+ link.attributes().get("href").substring(6)+"', label: '"+link.text()+"'},");
      }
    }
  }

  @Test
  public void testRun() throws IOException {
//    Document entriesPage = Jsoup.connect("http://www.ourcs.org.uk/?p=racing/entries&comp=162").get();
    Document entriesPage = Jsoup.connect("https://entries.ourcs.org.uk/events/event/174/crew_lists/").get();
    Elements tables = entriesPage.getElementsByTag("div");
    Elements collegePanels = entriesPage.getElementsByAttributeValueStarting("id", "club-");

    List<Crew> crewList = new ArrayList<>();

    for (Element collegePanel : collegePanels) {
      String college = null;
      Elements links = collegePanel.getElementsByTag("a");
      for (Element link : links) {
        for (Attribute attr : link.attributes()) {
          if (attr.getKey().equals("href") && attr.getValue().startsWith("/clubs/")) {
            college = link.text();
            System.out.println(college);
          }
        }
      }
      Elements crewHeaders = collegePanel.getElementsByTag("h4");
      for (Element crewHeader : crewHeaders) {
        Crew crew = new Crew();
        crewList.add(crew);
        String crewName = crewHeader.text();
        crew.setName(crewName);
        if (crewName.contains("W1") || crewName.contains("W2") || crewName.contains("W3") || crewName.contains("W4")) {
          crew.setSex(Sex.female);
        } else {
          crew.setSex(Sex.male);
        }
        crew.setCrewMembers(new HashSet<>());
        System.out.println(crewHeader.text());
        Element crewPanel = crewHeader.parent().parent();
        Elements crewMemberRows = crewPanel.getElementsByTag("tr");
        for (Element crewMemberRow : crewMemberRows) {
//          System.out.println(crewMemberRow.text());
          String seat = crewMemberRow.child(0).text();
          String name = crewMemberRow.child(1).text();
          if (!"Coach".equals(seat)) {
            CrewMember member = new CrewMember();
            member.setCrew(crew);
            member.setName(WordUtils.capitalizeFully(name, ' ', '-', '\''));
            if (seat.equals("Bow")) {
              member.setSeat(1);
            } else if (seat.equals("Str")) {
              member.setSeat(8);
            } else if (seat.equals("Cox")) {
              member.setSeat(9);
            } else {
              member.setSeat(Integer.parseInt(seat));
            }
            crew.getCrewMembers().add(member);
          }
        }
      }
    }

    for (Crew crew: crewList) {
      System.out.println(crew.toString());
      for (CrewMember member: crew.getCrewMembers()) {
        System.out.println(member.toString());
      }
    }
  }

  @Test
  public void testStartOrder() throws IOException {
    Document entriesPage = Jsoup.connect("http://eodg.atm.ox.ac.uk/user/dudhia/rowing/eights/e16fin.html").get();
    Element bodyTableTbody = entriesPage.getElementsByTag("table").get(1).child(0);
    for (Element divisionTable: bodyTableTbody.getElementsByTag("table")) {
      Elements rows = divisionTable.getElementsByTag("tr");
      if (rows.size() > 0) {
        String divisionName = rows.get(0).text();
        System.out.println(divisionName);
        if (divisionName.startsWith("Men")) {
          System.out.print("Male Crews");
        } else {
          System.out.print("Female Crews");
        }
        String divNameWithoutTime = divisionName.split(" \\(")[0].trim();
        String[] divNameComponents = divNameWithoutTime.split(" ");
        String divNumber = divNameComponents[divNameComponents.length-1];
        int divNum = 0;
        switch (divNumber) {
          case "I":
            System.out.println("Division 1");
            divNum = 1;
            break;
          case "II":
            System.out.println("Division 2");
            divNum = 2;
            break;
          case "III":
            System.out.println("Division 3");
            divNum = 3;
            break;
          case "IV":
            System.out.println("Division 4");
            divNum = 4;
            break;
          case "V":
            System.out.println("Division 5");
            divNum = 5;
            break;
          case "VI":
            System.out.println("Division 6");
            divNum = 6;
            break;
          case "VII":
            System.out.println("Division 7");
            divNum = 7;
            break;
        }

        if (divisionName.startsWith("Men") && divNum >= 6) {
          continue;
        }

        if (divisionName.startsWith("Women") && divNum >= 5) {
          continue;
        }

        for (int i = 1; i < rows.size(); i++) {
          String crewName = rows.get(i).getElementsByTag("a").text();
          int overallPosition = (divNum - 1)* Constants.CREWS_PER_DIVISION+i;
          System.out.println(Integer.toString(i) +"("+Integer.toString(overallPosition)+")"+ ": " + crewName);
          CrewPositionHistory crewPositionHistory = new CrewPositionHistory();
          crewPositionHistory.day(0).dividend(BigDecimal.ZERO).bumps(0).position(overallPosition);


        }
      }
    }
  }
}
