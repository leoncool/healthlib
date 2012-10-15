/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import health.database.DAO.SubjectDAO;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Leon
 */
public class ServerUtil {

    public static int getSubjectID(String ServletPath) {
        String values[] = ServletPath.replaceFirst(AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_subject + "/", "").split("/");
        int subID = Integer.parseInt(values[0]); //Retrieve Subject ID from URL
        return subID;
    }

    public static String getStreamID(String ServletPath) {
        String values[] = ServletPath.replaceFirst(AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_subject + "/", "").split("/");
        String streamID = values[2];
        return streamID;
    }

    public static String getDeviceID(String ServletPath) {
        String values[] = ServletPath.replaceFirst(AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_device + "/", "").split("/");
        String DeviceID = values[0];
        return DeviceID;
    }

    public static String getDeviceSerialID(String ServletPath) {
        String values[] = ServletPath.replaceFirst(AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_devicebinding + "/", "").split("/");
        String serialID = values[0]; //Retrieve Subject ID from URL
        return serialID;
    }
    public static String getDatastreamBlockID(String ServletPath)
    {
    	  String values[] = ServletPath.replaceFirst(AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_subject + "/", "").split("/");
          String blockID = values[4];
          return blockID;
    }

    public static boolean isPostSubjectReq(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_subject + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPostDatastreamReq(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_subject + "/[0-9]+/" + AllConstants.api_entryPoints.api_datastream + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPostDataPointsReq(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_subject
                + "/[0-9]+/" + AllConstants.api_entryPoints.api_datastream
                + "/[-a-zA-Z0-9]+/" + AllConstants.api_entryPoints.api_datapoints + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPostDataBlocksReq(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_subject
                + "/[0-9]+/" + AllConstants.api_entryPoints.api_datastream
                + "/[-a-zA-Z0-9]+/" + AllConstants.api_entryPoints.api_datablocks + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPostFollower(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_follower + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPostDevice(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_device + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPostDeviceSerialBinding(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_devicebinding + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPostDeviceDatapoints(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_device + "/[-a-zA-Z0-9]+/" + AllConstants.api_entryPoints.api_datapoints + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPostUserRegister(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_user + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isGetSubjectsListReq(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_subject + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isGetDatastreamList(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_subject
                + "/[0-9]+[/]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isGetDatastreamBlocks(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_subject
                + "/[0-9]+/" + AllConstants.api_entryPoints.api_datastream
                + "/[-a-zA-Z0-9]+/" + AllConstants.api_entryPoints.api_datablocks + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isGetADatastream(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_subject
                + "/[0-9]+/" + AllConstants.api_entryPoints.api_datastream
                + "/[-a-zA-Z0-9]+" + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isGetDataPointsAllUnits(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_subject
                + "/[0-9]+/" + AllConstants.api_entryPoints.api_datastream
                + "/[-a-zA-Z0-9]+/" + AllConstants.api_entryPoints.api_datapoints + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isGetDataPointsAllUnitsJackson(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_subject
                + "/[0-9]+/" + AllConstants.api_entryPoints.api_datastream
                + "/[-a-zA-Z0-9]+/" + AllConstants.api_entryPoints.api_datapoints + "/jackson$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isGetDataPointsAllUnitsDebug(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_subject
                + "/[0-9]+/" + AllConstants.api_entryPoints.api_datastream
                + "/[-a-zA-Z0-9]+/" + AllConstants.api_entryPoints.api_datapoints + "/debug")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isGetFollowers(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_follower + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isGetFollowings(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_following + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isGetDeviceList(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_device + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isGetDeviceDataPointsAllUnits(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_device
                + "/[-a-zA-Z0-9]+/" + AllConstants.api_entryPoints.api_datapoints + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isGetDeviceSerialRegisters(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_devicebinding + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isGetaDeviceBinding(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_devicebinding + "/[-a-zA-Z0-9]+" + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSearchUsers(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_search + "/" + AllConstants.api_entryPoints.api_user
                + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isListUsers(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_list + "/" + AllConstants.api_entryPoints.api_user
                + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isGetUserinfo(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_user
                + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isDeleteASubjectRequest(String ServletPath) {
        if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_subject + "/[0-9]+"+"[/]*$")) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isDeleteADataStreamRequest(String ServletPath) {
    	 if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_subject
                 + "/[0-9]+/" + AllConstants.api_entryPoints.api_datastream
                 + "/[-a-zA-Z0-9]+" + "[/]*$")) {
             return true;
         } else {
             return false;
         }
    }
    public static boolean isDeleteADataBlock(String ServletPath) {
    	if (ServletPath.matches("^" + AllConstants.api_entryPoints.api_url + AllConstants.api_entryPoints.api_subject
                + "/[0-9]+/" + AllConstants.api_entryPoints.api_datastream
                + "/[-a-zA-Z0-9]+/" + AllConstants.api_entryPoints.api_datablocks + "[/]*$")) {
            return true;
        } else {
            return false;
        }
    }
}
