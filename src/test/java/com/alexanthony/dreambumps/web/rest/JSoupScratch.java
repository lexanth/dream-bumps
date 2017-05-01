package com.alexanthony.dreambumps.web.rest;

import com.alexanthony.dreambumps.config.Constants;
import com.alexanthony.dreambumps.domain.Crew;
import com.alexanthony.dreambumps.domain.CrewMember;
import com.alexanthony.dreambumps.domain.CrewPositionHistory;
import com.alexanthony.dreambumps.domain.enumeration.Sex;
import org.apache.commons.lang3.text.WordUtils;
import org.jsoup.Jsoup;
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
  public void testRun() throws IOException {
    Document entriesPage = Jsoup.connect("http://www.ourcs.org.uk/?p=racing/entries&comp=162").get();
    Elements tables = entriesPage.getElementsByTag("table");
//    for (Element table: tables) {
//      System.out.println(table.text());
//    }
    Element crewListTable = tables.get(1);
    Elements alternatingHeadersAndCrewListsAndReturnToTop = crewListTable.getElementsByTag("tr");
    String college = null;

    List<Crew> crewList = new ArrayList<>();
    for (Element row: alternatingHeadersAndCrewListsAndReturnToTop) {
      if (row.children().size() > 0) {
        Elements collegeHeaders = row.getElementsByTag("h2");
        if (collegeHeaders.size() > 0) {
          college = collegeHeaders.get(0).text();
//          System.out.println("College: "+college);
        } else {
          Elements crewTables = row.getElementsByClass("entry_list");
          if (crewTables.size() > 0) {
            Elements mensCrews = row.children().get(0).children();
            String crewName = null;
            for (Element item: mensCrews) {
              if (item.tagName().equals("h3")) {
                crewName = "M"+item.text().split("n's ")[1].substring(0,1);
//                System.out.println("Crew: "+crewName);
              } else {
                Crew crew = new Crew();
                crewList.add(crew);
                crew.setName(college+" "+crewName);
                crew.setSex(Sex.male);
                crew.setCrewMembers(new HashSet<>());

                for (Element memberRow : item.getElementsByTag("tr")) {
                  String seat = memberRow.child(0).text();
                  String name = memberRow.child(1).text();
                  name = name.split("\\(")[0];
                  if (!seat.equals("Coach")) {
                    CrewMember member = new CrewMember();
                    member.setName(WordUtils.capitalizeFully(name, ' ', '-'));
                    member.setCrew(crew);
                    if (seat.equals("Bow")) {
                      member.setSeat(1);
                    } else if (seat.equals("Stroke")) {
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


            Elements womensCrews = row.children().get(1).children();
            crewName = null;
            for (Element item: womensCrews) {
              if (item.tagName().equals("h3")) {
                crewName = "W"+item.text().split("n's ")[1].substring(0,1);
                System.out.println("Crew: "+crewName);
              } else {
                Crew crew = new Crew();
                crewList.add(crew);
                crew.setName(college+" "+crewName);
                crew.setSex(Sex.female);
                crew.setCrewMembers(new HashSet<>());

                for (Element memberRow : item.getElementsByTag("tr")) {
                  String seat = memberRow.child(0).text();
                  String name = memberRow.child(1).text();
                  name = name.split("\\(")[0];
                  if (!seat.equals("Coach")) {
                    CrewMember member = new CrewMember();
                    member.setName(WordUtils.capitalizeFully(name, ' ', '-'));
                    member.setCrew(crew);
                    if (seat.equals("Bow")) {
                      member.setSeat(1);
                    } else if (seat.equals("Stroke")) {
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
