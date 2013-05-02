/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lifestyle.DAO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import util.DateUtil;
import util.HibernateUtil;

import com.lifestyle.models.LocationPlaces;
import com.lifestyle.models.Locationlogs;
import com.lifestyle.models.PlaceHistory;

/**
 *
 * @author Leon
 */
public class PlaceDAO {

    public static boolean PutOrUpdatePlace(List<LocationPlaces> allPlaces) {

        Session session = HibernateUtil.beginTransaction();
        for (LocationPlaces place : allPlaces) {
            session.saveOrUpdate(place);
        }
        HibernateUtil.commitTransaction();
        if (session.isOpen()) {
            HibernateUtil.closeSession();
        }
        return true;
    }

    public static double ComputMeanLatFromList(List<Locationlogs> logList, int i, int j) {
        double sum = 0;
        int counter = 0;
        for (int k = i; k < j; k++) {
            sum = sum + logList.get(k).getLat();
            counter++;
        }
        return sum / ((double) counter);
    }

    public static double ComputMeanLonFromList(List<Locationlogs> logList, int i, int j) {
        double sum = 0;
        int counter = 0;
        for (int k = i; k < j; k++) {
            sum = sum + logList.get(k).getLon();
            counter++;
        }
        return sum / ((double) counter);
    }

    public List<LocationPlaces> retrieveAllPlaces(String userID) {

        Session session = HibernateUtil.beginTransaction();
        Criteria criteria = session.createCriteria(LocationPlaces.class);
        criteria.add(Restrictions.eq("userID", userID));
        List<LocationPlaces> allPlaces = criteria.list();
        if (session.isOpen()) {
            session.close();
        }
        return allPlaces;
    }

    public static boolean existPlace(List<LocationPlaces> allPlaces, double lat, double lon) {
        boolean exist = false;
        for (LocationPlaces pl : allPlaces) {
            double distance = distFrom(pl.getLat(), pl.getLon(), lat, lon);
            if (distance < pl.getRadius()) {
                exist = true;
                return exist;
            }
        }
        return exist;
    }

    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;
        int meterConversion = 1609;
        return new Double(dist * meterConversion).doubleValue() / 1000.00;
    }

    public static List<LocationPlaces> updatePlaces() {

        LocationLogDAO logDao = new LocationLogDAO();
        List<Locationlogs> logList = logDao.getLocatioonLogs("leoncool", null, null);
        PlaceDAO placeDao = new PlaceDAO();
        List<LocationPlaces> allPlaces = placeDao.retrieveAllPlaces("leoncool");

        long timeThreh = 600;
        double distanceThreh = 0.20;
        int i = 0;
        int j = 0;
        while (i < logList.size()) {
            i = i + 1;
            j = i + 1;
            while (j < logList.size()) {
                double distance = distFrom(logList.get(i).getLat(), logList.get(i).getLon(), logList.get(j).getLat(), logList.get(j).getLon());


                if (distance > distanceThreh) {
                    long timeDiff = Math.abs((logList.get(j).getDatetime().getTime() - logList.get(i).getDatetime().getTime()) / 1000);
                    System.out.println("distance:" + distance);
                    System.out.println("timeDiff:" + timeDiff);
                    if (timeDiff > timeThreh) {
                        double meanLat = ComputMeanLatFromList(logList, i, j);
                        double meanLon = ComputMeanLonFromList(logList, i, j);
                        System.out.println("meanLat:" + meanLat + ",meanLon:" + meanLon);
                        if (!existPlace(allPlaces, meanLat, meanLon)) {
                            UUID uuid = UUID.randomUUID();
                            LocationPlaces newPlace = new LocationPlaces(meanLat, meanLon, distanceThreh, "name", "leoncool");
                            allPlaces.add(newPlace);
                            System.out.println("newPlace:" + newPlace.getLat() + "," + newPlace.getLon() + "," + logList.get(j).getDatetime());
                        }
                    }
                    i = j;
                    break;
                }
                j = j + 1;
            }
        }
        PutOrUpdatePlace(allPlaces);
        return allPlaces;
    }

//    public static void toXml_Log_File() throws FileNotFoundException, IOException {
//        List<LocationPlaces> allPlaces = updatePlaces();
//        LocationLogDAO logDao = new LocationLogDAO();
//        List<Locationlogs> logList = logDao.getLocatioonLogs("leoncool", null, null);
//        XFactoryBufferedImpl factory = new XFactoryBufferedImpl();
//        XLog xmlLog = factory.createLog();
//        XOrganizationalExtension orgext = XOrganizationalExtension.instance();
//        XTimeExtension timeExt = XTimeExtension.instance();
//        XConceptExtension conceptExt = XConceptExtension.instance();
//        XLifecycleExtension lifeExt = XLifecycleExtension.instance();
//
//        xmlLog.getExtensions().add(orgext);
//        xmlLog.getExtensions().add(timeExt);
//        xmlLog.getExtensions().add(conceptExt);
//
//        XAttribute lifestyleModel = factory.createAttributeLiteral("lifecycle:model", "standard", null);
//        xmlLog.getAttributes().put("lifecycle:model", lifestyleModel);
//        String keys[] = new String[2];
//        keys[0] = "concept:name";
//        keys[1] = "lifecycle:transition";
//        XEventAttributeClassifier classifier1 = new XEventAttributeClassifier("MXML Legacy Classifier", keys);
//
//        xmlLog.getClassifiers().add(classifier1);
//        long timeThreh = 600;
//        double distanceThreh = 0.20;
//        int i = 0;
//        int j = 0;
//        int counter = 0;
//        HashMap<LocalDate, XTrace> traceList = new HashMap<LocalDate, XTrace>();
//        HashMap<LocalDate, List<XEvent>> traceEventList = new HashMap<LocalDate, List<XEvent>>();
//        for (Locationlogs log : logList) {
//            boolean existingPlace = false;
//            String eventName = "other";
//            for (LocationPlaces place : allPlaces) {
//                if (distFrom(place.getLat(), place.getLon(), log.getLat(), log.getLon()) <= place.getRadius()) {
//                    existingPlace = true;
//                    eventName = Integer.toString(place.getId());
//                    continue;
//                }
//            }
//            LocalDate date = LocalDate.fromDateFields(log.getDatetime());
//            if (traceList.get(date) == null) {
//                XTrace xtrace = factory.createTrace();
//                List<XEvent> eventList = new ArrayList<XEvent>();
//                XAttribute nameAttr = factory.createAttributeLiteral("concept:name", date.toString(), null);
//                xtrace.getAttributes().put("name", nameAttr);
//                traceList.put(date, xtrace);
//                traceEventList.put(date, eventList);
////                xmlLog.add(xtrace);
//            }
//            XEvent event = factory.createEvent();
//            XAttribute placeAttr = factory.createAttributeLiteral("org:resource", eventName, null);
//            XAttribute conceptAttr = factory.createAttributeLiteral("concept:name", eventName, null);
//            XAttribute timeattr = factory.createAttributeTimestamp("time:timestamp", log.getDatetime(), null);
//
////            event.getAttributes().put("place", placeAttr);
//            event.getAttributes().put("time", timeattr);
//            event.getAttributes().put("name", conceptAttr);
//            if (traceEventList.get(date).size() > 0) {
//                //getAttributes().get("org:resource").toString()
//                if (!traceEventList.get(date).get(traceEventList.get(date).size() - 1).getAttributes().get("name").toString().equalsIgnoreCase(eventName)) {
////                    XEvent eventComplete = factory.createEvent();
////                    eventComplete.getAttributes().put("time", timeattr);
////                    XAttribute completeConceptAttr = factory.createAttributeLiteral("concept:name", traceEventList.get(date).get(traceEventList.get(date).size() - 1).getAttributes().get("name").toString(), null);
////                    XAttribute completeLifecycle = factory.createAttributeLiteral("lifecycle:transition", "complete", lifeExt);
////                    eventComplete.getAttributes().put("name", completeConceptAttr);
////                    eventComplete.getAttributes().put("lifecycle", completeLifecycle);
////                    traceEventList.get(date).add(eventComplete);
////                    traceList.get(date).add(eventComplete);
//
////                    XAttribute startLifecycle = factory.createAttributeLiteral("lifecycle:transition", "start", lifeExt);
////                    event.getAttributes().put("lifecycle", startLifecycle);
//                    System.out.println("traceEventList:");
//                    traceEventList.get(date).add(event);
//                    traceList.get(date).add(event);
//                }
//            } else {
////                XAttribute lifecycle = factory.createAttributeLiteral("lifecycle:transition", "start", lifeExt);
////                event.getAttributes().put("lifecycle", lifecycle);
//                traceEventList.get(date).add(event);
//                traceList.get(date).add(event);
//            }
//            counter++;
//        }
//        Set<Map.Entry<LocalDate, XTrace>> entrySet = traceList.entrySet();
//        Iterator<Entry<LocalDate, XTrace>> iterator = entrySet.iterator();
//        while (iterator.hasNext()) {
//            Entry<LocalDate, XTrace> entry = iterator.next();
//            if (entry.getValue().size() > 2) {
//                xmlLog.add(entry.getValue());
//            }
//        }
//        XesXmlSerializer serializer = new XesXmlSerializer();
//        FileOutputStream outstream = new FileOutputStream(new File("F:/xmlfile.xes"));
//        serializer.serialize(xmlLog, outstream);
//
//    }

    public static void exportPlaceLogToExcel() throws FileNotFoundException, IOException, ParseException {
        List<LocationPlaces> allPlaces = updatePlaces();
        LocationLogDAO logDao = new LocationLogDAO();
        List<Locationlogs> logList = logDao.getLocatioonLogs("leoncool", null, null);
        HashMap<LocalDate, List<PlaceHistory>> datePlaceHistoryMap = new HashMap<LocalDate, List<PlaceHistory>>();

        for (Locationlogs log : logList) {
            boolean existingPlace = false;
            String eventName = "other";
            for (LocationPlaces place : allPlaces) {
                if (distFrom(place.getLat(), place.getLon(), log.getLat(), log.getLon()) <= place.getRadius()) {
                    existingPlace = true;
                    eventName = Integer.toString(place.getId());
                    continue;
                }
            }
            LocalDate date = LocalDate.fromDateFields(log.getDatetime());
            if (datePlaceHistoryMap.get(date) == null) {
                List<PlaceHistory> placehistory = new ArrayList<PlaceHistory>();
                datePlaceHistoryMap.put(date, placehistory);
            }

            if (datePlaceHistoryMap.get(date).size() > 0) {
                //getAttributes().get("org:resource").toString()
                if (!datePlaceHistoryMap.get(date).get(datePlaceHistoryMap.get(date).size() - 1).getName().equalsIgnoreCase(eventName)) {
                    PlaceHistory history = new PlaceHistory();
                    history.setName(eventName);
                    history.setLocaldatetime(LocalDateTime.fromDateFields(log.getDatetime()));
                    datePlaceHistoryMap.get(date).add(history);
                }
            } else {
                PlaceHistory history = new PlaceHistory();
                history.setName(eventName);
                history.setLocaldatetime(LocalDateTime.fromDateFields(log.getDatetime()));
                datePlaceHistoryMap.get(date).add(history);
            }

        }
//        Set<Map.Entry<LocalDate, List<PlaceHistory>>> entrySet = datePlaceHistoryMap.entrySet();
//        Iterator<Entry<LocalDate, List<PlaceHistory>>> iterator = entrySet.iterator();
//        while (iterator.hasNext()) {
//            Entry<LocalDate, List<PlaceHistory>> entry = iterator.next();
//            if (entry.getValue().size() > 2) {
//              
//            }
//        }

        DateUtil dateUtil = new DateUtil();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        HSSFRow firstRow = sheet.createRow((short) 0);
        firstRow.createCell(0).setCellValue("time");
        firstRow.createCell(1).setCellValue("place_tag");
//        firstRow.createCell(2).setCellValue("lat");
//        firstRow.createCell(3).setCellValue("lon");
//        firstRow.createCell(4).setCellValue("accuracy");
        LocationLogDAO locDao = new LocationLogDAO();
        List<Locationlogs> loclogList = locDao.getLocatioonLogs("leoncool", null, null);
        int rowCounter = 1;
        SortedSet<LocalDate> keys = new TreeSet<LocalDate>(datePlaceHistoryMap.keySet());
        Iterator<LocalDate> iter = keys.iterator();
        while (iter.hasNext()) {
            LocalDate date = iter.next();
            if (datePlaceHistoryMap.get(date).size() > 2) {
                List<PlaceHistory> placeList = datePlaceHistoryMap.get(date);
                for (PlaceHistory history : placeList) {
                    HSSFRow row = sheet.createRow(rowCounter);
                    rowCounter++;
                    row.createCell(0).setCellValue(history.getLocaldatetime().toString());
                    row.createCell(1).setCellValue(history.getName());
                }

            }
        }
        FileOutputStream fileOut = new FileOutputStream("F:/workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }

//    public static void exportPlaceLogToTxt() throws FileNotFoundException, IOException, ParseException {
//        List<LocationPlaces> allPlaces = updatePlaces();
//        LocationLogDAO logDao = new LocationLogDAO();
//        List<Locationlogs> logList = logDao.getLocatioonLogs("leoncool", null, null);
//        HashMap<LocalDate, List<PlaceHistory>> datePlaceHistoryMap = new HashMap<LocalDate, List<PlaceHistory>>();
//
//        for (Locationlogs log : logList) {
//            boolean existingPlace = false;
//            String eventName = "0";
//            for (LocationPlaces place : allPlaces) {
//                if (distFrom(place.getLat(), place.getLon(), log.getLat(), log.getLon()) <= place.getRadius()) {
//                    existingPlace = true;
//                    eventName = Integer.toString(place.getId());
//                    continue;
//                }
//            }
//            LocalDate date = LocalDate.fromDateFields(log.getDatetime());
//            if (datePlaceHistoryMap.get(date) == null) {
//                List<PlaceHistory> placehistory = new ArrayList<PlaceHistory>();
//                datePlaceHistoryMap.put(date, placehistory);
//            }
//
//            if (datePlaceHistoryMap.get(date).size() > 0) {
//                //getAttributes().get("org:resource").toString()
//                if (!datePlaceHistoryMap.get(date).get(datePlaceHistoryMap.get(date).size() - 1).getName().equalsIgnoreCase(eventName)) {
//                    PlaceHistory history = new PlaceHistory();
//                    history.setName(eventName);
//                    history.setLocaldatetime(LocalDateTime.fromDateFields(log.getDatetime()));
//                    history.setDate(log.getDatetime());
//                    datePlaceHistoryMap.get(date).add(history);
//                }
//                int sizeofHistoryMap = datePlaceHistoryMap.get(date).size();
//                if (sizeofHistoryMap > 1) {
//                    double diff = Math.abs(datePlaceHistoryMap.get(date).get(sizeofHistoryMap - 1).getDate().getTime() - datePlaceHistoryMap.get(date).get(sizeofHistoryMap - 2).getDate().getTime() / 1000);
//                    if (diff < 600) {
//                        System.out.println("remove==true");
//                        boolean remove;
//                        remove = datePlaceHistoryMap.get(date).remove(datePlaceHistoryMap.get(sizeofHistoryMap - 1));
//                        if (remove == true) {
//                            //  System.out.println("remove==true");
//                        }
//                    }
//                }
//            } else {
//                PlaceHistory history = new PlaceHistory();
//                history.setName(eventName);
//                history.setLocaldatetime(LocalDateTime.fromDateFields(log.getDatetime()));
//                history.setDate(log.getDatetime());
//                datePlaceHistoryMap.get(date).add(history);
//            }
//
//        }
////        Set<Map.Entry<LocalDate, List<PlaceHistory>>> entrySet = datePlaceHistoryMap.entrySet();
////        Iterator<Entry<LocalDate, List<PlaceHistory>>> iterator = entrySet.iterator();
////        while (iterator.hasNext()) {
////            Entry<LocalDate, List<PlaceHistory>> entry = iterator.next();
////            if (entry.getValue().size() > 2) {
////              
////            }
////        }
//
//        DateUtil dateUtil = new DateUtil();
//
//        LocationLogDAO locDao = new LocationLogDAO();
//        List<Locationlogs> loclogList = locDao.getLocatioonLogs("leoncool", null, null);
//        int rowCounter = 1;
//        SortedSet<LocalDate> keys = new TreeSet<LocalDate>(datePlaceHistoryMap.keySet());
//        Iterator<LocalDate> iter = keys.iterator();
//        FileOutputStream fileOut = new FileOutputStream("F:/dataset.txt");
//        PrintWriter writer = new PrintWriter(fileOut);
//        while (iter.hasNext()) {
//            LocalDate date = iter.next();
//            if (datePlaceHistoryMap.get(date).size() > 2) {
//                List<PlaceHistory> placeList = datePlaceHistoryMap.get(date);
//                for (PlaceHistory history : placeList) {
//                    if (!history.getName().equalsIgnoreCase("0")) {
//                        writer.write(history.getName() + " -1 ");
//                        rowCounter++;
//                        System.out.println(history.getLocaldatetime() + "," + history.getName());
//                    }
//                }
//                writer.write("-2\r\n");
//            }
//        }
//
//        writer.close();
//        fileOut.close();
//        toBIDE_Pattern();
//    }

//    public static void toBIDE_Pattern() throws IOException {
//
//        ca.pfv.spmf.sequentialpatterns.BIDEPlus_saveToFile.SequenceDatabase sequenceDatabase = new ca.pfv.spmf.sequentialpatterns.BIDEPlus_saveToFile.SequenceDatabase();
//        sequenceDatabase.loadFile("F:/dataset.txt");
//        sequenceDatabase.print();
//        int minsup = (int) (0.30 * sequenceDatabase.size());// we use a minimum support of 2 sequences.
//
//        AlgoBIDEPlus algo = new AlgoBIDEPlus();
//        algo.runAlgorithm(sequenceDatabase, "F:/output.txt", minsup);
//        algo.printStatistics(sequenceDatabase.size());
//        List<PatternSequence> patternList = algo.patternList;
//
//
//        Collections.sort(patternList, new Comparator<PatternSequence>() {
//            public int compare(PatternSequence a, PatternSequence b) {
//                return a.SUP - b.SUP;
//            }
//        });
//        for (PatternSequence seq : patternList) {
//            if (seq.sequence.size() > 2) {
//                System.out.println(seq.SUP + ":" + seq.sequence);
//            }
//        }
//    }

    public static void timeStateMatrix() throws FileNotFoundException, IOException, ParseException {
        List<LocationPlaces> allPlaces = updatePlaces();
        LocationLogDAO logDao = new LocationLogDAO();
        List<Locationlogs> logList = logDao.getLocatioonLogs("leoncool", null, null);
        HashMap<LocalDate, HashMap<LocalTime, HashMap<String, Integer>>> datePlaceHistoryMap = new HashMap<LocalDate, HashMap<LocalTime, HashMap<String, Integer>>>();

        for (Locationlogs log : logList) {
            boolean existingPlace = false;
            String eventName = "other";
            for (LocationPlaces place : allPlaces) {
                if (distFrom(place.getLat(), place.getLon(), log.getLat(), log.getLon()) <= place.getRadius()) {
                    existingPlace = true;
                    eventName = place.getCategory();
                    continue;
                }
            }

            LocalDate date = LocalDate.fromDateFields(log.getDatetime());
            if (datePlaceHistoryMap.get(date) == null) {
                HashMap<LocalTime, HashMap<String, Integer>> timeMatrix = new HashMap<LocalTime, HashMap<String, Integer>>();
                for (int i = 0; i < 48; i++) {
                    timeMatrix.put(LocalTime.MIDNIGHT.plusMinutes(30 * i), new HashMap<String, Integer>());
                    System.out.println(LocalTime.MIDNIGHT.plusMinutes(30 * i));
                }
                datePlaceHistoryMap.put(date, timeMatrix);
            }
            HashMap<LocalTime, HashMap<String, Integer>> timeMatrix = datePlaceHistoryMap.get(date);
//            System.out.println(log.getDatetime() + "----" + LocalTime.fromDateFields(log.getDatetime()).getHourOfDay());
            HashMap<String, Integer> frequencyMap = timeMatrix.get(LocalTime.fromDateFields(log.getDatetime()).getHourOfDay());
            if (frequencyMap.get(eventName) == null) {
                frequencyMap.put(eventName, 1);
            } else {
                frequencyMap.put(eventName, frequencyMap.get(eventName) + 1);
            }
        }
        DateUtil dateUtil = new DateUtil();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        HSSFRow firstRow = sheet.createRow((short) 0);
        firstRow.createCell(0).setCellValue("time");
        firstRow.createCell(1).setCellValue("place_tag");
        int rowCounter = 1;
        Set<Map.Entry<LocalDate, HashMap<LocalTime, HashMap<String, Integer>>>> entrySet = datePlaceHistoryMap.entrySet();
        Iterator<Entry<LocalDate, HashMap<LocalTime, HashMap<String, Integer>>>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Entry<LocalDate, HashMap<LocalTime, HashMap<String, Integer>>> dateMatrixMap = iterator.next();
            HashMap<LocalTime, HashMap<String, Integer>> timeMatrix = dateMatrixMap.getValue();
            for (int i = 0; i < 24; i++) {
                HSSFRow row = sheet.createRow(rowCounter);
                rowCounter++;
                row.createCell(0).setCellValue(dateMatrixMap.getKey().toString() + "-" + i);
                Set<String> keySet = timeMatrix.get(i).keySet();
                String allStatsOut = "";
                for (String str : keySet) {
                    allStatsOut = allStatsOut + str + "(" + timeMatrix.get(i).get(str) + "),";
                }
                row.createCell(1).setCellValue(allStatsOut);
            }

            FileOutputStream fileOut = new FileOutputStream("F:/workbook.xls");
            wb.write(fileOut);
            fileOut.close();
        }


    }

    public static void main(String args[]) throws FileNotFoundException, IOException, ParseException {
//        System.out.println(distFrom(51.499001, -0.176904, 51.494379, -0.188742));
//        LocationPlaces place = new LocationPlaces("1", 0.0, 0.0, 0.2, "name", "user");
//        PlaceDAO placeDao = new PlaceDAO();
//        toXml_Log_File();
//        exportPlaceLogToExcel();
//        timeStateMatrix();
//        exportPlaceLogToTxt();
//        toBIDE_Pattern();
    }
}
