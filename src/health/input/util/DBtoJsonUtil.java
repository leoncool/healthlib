/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package health.input.util;

import health.database.DAO.DatastreamDAO;
import health.database.models.DataSummary;
import health.database.models.Datastream;
import health.database.models.DatastreamBlocks;
import health.database.models.DatastreamUnits;
import health.database.models.DeviceBinding;
import health.database.models.Follower;
import health.database.models.SleepDataSummary;
import health.database.models.Subject;
import health.database.models.UserAvatar;
import health.database.models.Users;
import health.database.models.merge.UserInfo;
import health.input.jsonmodels.JsonDataSummary;
import health.input.jsonmodels.JsonDatastream;
import health.input.jsonmodels.JsonDatastreamBlock;
import health.input.jsonmodels.JsonDatastreamUnits;
import health.input.jsonmodels.JsonFollower;
import health.input.jsonmodels.JsonSleepDataSummary;
import health.input.jsonmodels.JsonSubject;
import health.input.jsonmodels.JsonUser;
import health.input.jsonmodels.JsonUserAvatar;
import health.input.jsonmodels.JsonUserInfo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;

import util.AllConstants.ServerConfigs;
import util.DateUtil;
import util.ServerConfigUtil;
import device.input.jsonmodels.JsonDevice;
import device.input.jsonmodels.JsonDeviceBinding;

/**
 * 
 * @author Leon
 */
public class DBtoJsonUtil {
	public DatastreamUnits convert_a_jdatastream_unit(JsonDatastreamUnits junit) {
		DatastreamUnits unit = new DatastreamUnits();
		Date now = new Date();
		unit.setCreatedTime(now);
		unit.setUpdatedTime(now);
		unit.setMaxValue(junit.getMax_value());
		unit.setMinValue(junit.getMin_value());
		unit.setCurrentValue(junit.getCurrent_value());
		unit.setUnitLabel(junit.getUnit_label());
		unit.setValueType(junit.getValue_type());
		unit.setUnitSymbol(junit.getUnit_symbol());
		unit.setUnitID(UUID.randomUUID().toString()); // only use it as primary
														// key in DB
		unit.setShortUnitID(RandomStringUtils.randomAlphanumeric(5)); // only
																		// use
																		// it
																		// for
																		// storage
		return unit;
	}

	public Datastream convert_a_JdataStream(JsonDatastream jdatastream,
			boolean createNew, Datastream datastream) {
		if(datastream==null)
		{
			datastream=new Datastream();
		}
		Date now = new Date();
		if (createNew == true) {
			UUID streamUUID = UUID.randomUUID();
			datastream.setStreamId(streamUUID.toString());
			datastream.setCreatedTime(now);
			datastream.setOwner(jdatastream.getOwner());
			datastream.setTitle(jdatastream.getTitle());
			datastream.setNote(jdatastream.getNote());
			datastream.setDescription(jdatastream.getDesc());
			datastream.setTags(jdatastream.getTags());
			ArrayList<DatastreamUnits> datastreamUnits = new ArrayList<DatastreamUnits>();
			for (JsonDatastreamUnits unit : jdatastream.getUnits_list()) {

				DatastreamUnits dsUnit = new DatastreamUnits();
				dsUnit.setStreamID(datastream);
				dsUnit.setCreatedTime(new Date());
				dsUnit.setUpdatedTime(new Date());
				dsUnit.setMaxValue(unit.getMax_value());
				dsUnit.setMinValue(unit.getMin_value());
				dsUnit.setCurrentValue(unit.getCurrent_value());
				dsUnit.setUnitLabel(unit.getUnit_label());
				dsUnit.setValueType(unit.getValue_type());
				dsUnit.setUnitSymbol(unit.getUnit_symbol());
				dsUnit.setUnitID(UUID.randomUUID().toString()); // only use it
																// as primary
																// key in DB
				dsUnit.setShortUnitID(RandomStringUtils.randomAlphanumeric(5)); // only
																				// use
																				// it
																				// for
																				// storage
				datastreamUnits.add(dsUnit);
			}
			datastream.setDatastreamUnitsList(datastreamUnits);
		} else {
			datastream.setStreamId(jdatastream.getDatastream_id());
			if (jdatastream.getTitle() != null&&jdatastream.getTitle().length()>1) {
				datastream.setTitle(jdatastream.getTitle());
			}

			if (jdatastream.getDesc() != null&&jdatastream.getDesc().length()>1) {

				datastream.setDescription(jdatastream.getDesc());
			}
			if (jdatastream.getNote() != null&&jdatastream.getNote().length()>1) {
				datastream.setNote(jdatastream.getNote());
			}
			if (jdatastream.getTags() != null&&jdatastream.getTags().length()>1) {
				datastream.setTags(jdatastream.getTags());
			}
			
		}
		datastream.setUpdated(now);

		return datastream;
	}

	public JsonDatastreamBlock convert_a_Datablock(DatastreamBlocks block) {
		JsonDatastreamBlock jblock = new JsonDatastreamBlock();
		jblock.setBlockid(block.getBlockId());
		if (block.getBlockDesc() == null) {
			jblock.setBlockdesc("");
		} else {
			jblock.setBlockdesc(block.getTags());
		}
		if (block.getDisplayName() == null) {
			jblock.setBlockname(block.getDisplayName());
		} else {
			jblock.setBlockname(block.getDisplayName());
		}

		if (block.getTags() == null) {
			jblock.setTags("");
		} else {
			jblock.setTags(block.getTags());
		}
		jblock.setCreated_time(Long.toString(block.getCreated().getTime()));
		jblock.setUpdate_time(Long.toString(block.getUpdated().getTime()));
		return jblock;
	}

	public JsonSubject convert_a_Subject(Subject subject) throws ParseException {
		JsonSubject jsub = new JsonSubject();
		jsub.setId(subject.getId());
		jsub.setTitle(subject.getTitle());
		jsub.setPrivate_set(subject.getPrivateSet());
		jsub.setDescription(subject.getDescription());
		jsub.setLoginid(subject.getLoginID());
		jsub.setParent_subject(subject.getParentSub());
		jsub.setTags(subject.getTags());
		jsub.setStatus(subject.getStatus());
		DateUtil dateUtil = new DateUtil();
		jsub.setCreated_time(dateUtil.format(subject.getCreatedTime(),
				dateUtil.millisecFormat));
		jsub.setUpdated_time(dateUtil.format(subject.getUpdated(),
				dateUtil.millisecFormat));
		jsub.setIcon(subject.getIcon());
		return jsub;
	}

	public JsonUserInfo convert_a_userinfo(UserInfo userinfo,
			Map<String, String> followerMap, Map<String, String> followingMap) {
		JsonUserInfo juserinfo = new JsonUserInfo();
		if (followerMap != null && followingMap != null) {
			// juserinfo.setIs_follower(false);
			// juserinfo.setIs_following("null");
			if (followerMap.containsKey((String) userinfo.getUser()
					.getLoginID())) {
				juserinfo.setIs_follower(true);
			} else {
				juserinfo.setIs_follower(false);
			}
			if (followingMap.containsKey((String) userinfo.getUser()
					.getLoginID())) {
				juserinfo.setIs_following(true);
			} else {
				juserinfo.setIs_following(false);
			}
		}
		juserinfo.setUser(convert_a_user(userinfo.getUser()));
		if (userinfo.getAvatar() != null) {
			juserinfo.setAvatar(convert_a_userAvatar(userinfo.getAvatar()));
		}
		return juserinfo;
	}

	public JsonUserInfo convert_a_userinfo(UserInfo userinfo) {
		JsonUserInfo juserinfo = new JsonUserInfo();

		juserinfo.setUser(convert_a_user(userinfo.getUser()));
		if (userinfo.getAvatar() != null) {
			juserinfo.setAvatar(convert_a_userAvatar(userinfo.getAvatar()));
		}
		return juserinfo;
	}

	public JsonUser convert_a_user(Users user) {
		JsonUser juser = new JsonUser();
		juser.setLoginid(user.getLoginID());
		juser.setScreenname(user.getScreenname());
		juser.setCreated_time(user.getCreatedTime());
		juser.setBirthday(user.getBirthday());
		if (user.getUserDetails() != null) {
			if (user.getUserDetails().getCountry() != null) {
				juser.setCountry(user.getUserDetails().getCountry());
			}
			if (user.getUserDetails().getCity() != null) {
				juser.setCity(user.getUserDetails().getCity());
			}
			if (user.getUserDetails().getHeight() != null) {
				juser.setHeight_cm(Double.toString(user.getUserDetails()
						.getHeight()));
			}
			if (user.getUserDetails().getWeight() != null) {
				juser.setWeight_kg(Double.toString(user.getUserDetails()
						.getWeight()));
			}
		}
		juser.setEmail(user.getEmail());
		juser.setGender(user.getGender());
		juser.setLanguage(user.getLanguage());
		juser.setTimezone(user.getTimezone());
		return juser;
	}

	public JsonUserAvatar convert_a_userAvatar(UserAvatar avatar) {
		JsonUserAvatar javar = new JsonUserAvatar();
		javar.setLoginid(avatar.getUsers().getLoginID());
		javar.setUrl(ServerConfigUtil
				.getConfigValue(ServerConfigs.AvatarAccessPoint)
				+ avatar.getUrl());
		javar.setHash(avatar.getHash());
		return javar;
	}

	public JsonFollower convert_a_Follower(Follower follower)
			throws ParseException {
		JsonFollower jfollwer = new JsonFollower();
		jfollwer.setLoginid(follower.getLoginID());
		jfollwer.setFollower_id(follower.getFollowerID());
		jfollwer.setNote(follower.getNote());
		jfollwer.setStatus(follower.getStatus());
		return jfollwer;
	}

	public JsonSubject convertSubjectContainDatastreams(Subject subject)
			throws ParseException {
		JsonSubject jsub = new JsonSubject();
		jsub.setId(subject.getId());
		DatastreamDAO dstreamDao = new DatastreamDAO();
		List<Datastream> dstreamList = dstreamDao.getDatastreamList(
				subject.getId(), true, false);
		List<JsonDatastream> jdstreamList = new ArrayList<JsonDatastream>();
		for (int i = 0; i < dstreamList.size(); i++) {
			JsonDatastream jsonDs = convertDatastream(dstreamList.get(i), null);
			jdstreamList.add(jsonDs);
		}
		jsub.setDatastreams(jdstreamList);
		jsub.setTitle(subject.getTitle());
		jsub.setPrivate_set(subject.getPrivateSet());
		return jsub;
	}

	public JsonDatastream convertDatastream(Datastream datastream,
			Map<String, String> onlyListedUnitIDs) throws ParseException {
		JsonDatastream jd = new JsonDatastream();
		List<DatastreamUnits> unitsList = datastream.getDatastreamUnitsList();
		List<JsonDatastreamUnits> jdatastreamUnitList = new ArrayList<JsonDatastreamUnits>();
		jd.setDatastream_id(datastream.getStreamId());
		jd.setOwner(datastream.getOwner());
		jd.setNote(datastream.getNote());
		DateUtil dateUtil = new DateUtil();
		jd.setCreated_time(dateUtil.format(datastream.getCreatedTime(),
				dateUtil.millisecFormat));
		jd.setSubject_id(datastream.getSubId());
		jd.setTitle(datastream.getTitle());
//		System.out
//				.print("title:" + jd.getTitle() + "," + datastream.getTitle());
		jd.setDesc(datastream.getDescription());
		jd.setIcon(datastream.getIcon());
		jd.setPurpose(datastream.getPurpose());
		int totalblocks = 0;
		if (datastream.getDatastreamBlocksList() != null) {
			totalblocks = datastream.getDatastreamBlocksList().size();
		}
		jd.setTotal_blocks(totalblocks);
		int total_units = 0;
		if (unitsList != null) {
			total_units = unitsList.size();
		}
		jd.setTotal_units(total_units);
		if (datastream.getUpdated() != null) {
			jd.setUpdated_time(dateUtil.format(datastream.getUpdated(),
					dateUtil.millisecFormat));
		}
		for (int i = 0; i < unitsList.size(); i++) {
			JsonDatastreamUnits unit = new JsonDatastreamUnits();
			if (unitsList.get(i).getShortUnitID() != null
					&& unitsList.get(i).getShortUnitID().length() > 1) {
				unit.setUnit_id(unitsList.get(i).getShortUnitID());
			} else {
				unit.setUnit_id(unitsList.get(i).getUnitID());
			}
			unit.setMax_value(unitsList.get(i).getMaxValue());
			unit.setMin_value(unitsList.get(i).getMinValue());
			unit.setUnit_label(unitsList.get(i).getUnitLabel());
			unit.setUnit_symbol(unitsList.get(i).getUnitSymbol());
			unit.setUnit_type(unitsList.get(i).getUnitType());
			unit.setValue_type(unitsList.get(i).getValueType());
			if (onlyListedUnitIDs != null && onlyListedUnitIDs.size() > 0) {
				if (onlyListedUnitIDs.get(unitsList.get(i).getUnitID()) == null
						&& (unitsList.get(i).getShortUnitID() == null || onlyListedUnitIDs
								.get(unitsList.get(i).getShortUnitID()) == null)) {
				} else {
					jdatastreamUnitList.add(unit);
				}
			} else {
				jdatastreamUnitList.add(unit);
			}
		}
		jd.setUnits_list(jdatastreamUnitList);
		return jd;
	}

	public JsonDevice convertDatastreamToJsonDevice(Datastream datastream,
			Map<String, String> onlyListedUnitIDs) throws ParseException {
		JsonDevice jd = new JsonDevice();
		List<DatastreamUnits> unitsList = datastream.getDatastreamUnitsList();
		List<JsonDatastreamUnits> jdatastreamUnitList = new ArrayList<JsonDatastreamUnits>();
		jd.setDevice_id(datastream.getStreamId());
		jd.setOwner(datastream.getOwner());
		jd.setNote(datastream.getNote());
		DateUtil dateUtil = new DateUtil();
		jd.setCreated_time(dateUtil.format(datastream.getCreatedTime(),
				dateUtil.millisecFormat));
		jd.setDevice_name(datastream.getTitle());
		// System.out.print("title:" + jd.getTitle() + "," +
		// datastream.getTitle());
		jd.setDesc(datastream.getDescription());
		jd.setIcon(datastream.getIcon());
		if (datastream.getUpdated() != null) {
			jd.setUpdated_time(dateUtil.format(datastream.getUpdated(),
					dateUtil.millisecFormat));
		}
		for (int i = 0; i < unitsList.size(); i++) {
			JsonDatastreamUnits unit = new JsonDatastreamUnits();
			unit.setUnit_id(unitsList.get(i).getUnitID());
			unit.setMax_value(unitsList.get(i).getMaxValue());
			unit.setMin_value(unitsList.get(i).getMinValue());
			unit.setUnit_label(unitsList.get(i).getUnitLabel());
			unit.setUnit_symbol(unitsList.get(i).getUnitSymbol());
			unit.setUnit_type(unitsList.get(i).getUnitType());
			unit.setValue_type(unitsList.get(i).getValueType());
			if (onlyListedUnitIDs != null && onlyListedUnitIDs.size() > 0) {
				if (onlyListedUnitIDs.get(unitsList.get(i).getUnitID()) == null) {
				} else {
					jdatastreamUnitList.add(unit);
				}
			} else {
				jdatastreamUnitList.add(unit);
			}
		}
		jd.setUnits_list(jdatastreamUnitList);
		return jd;
	}

	public HashMap<String, String> ToDatastreamUnitsMap(Datastream datastream) {
		List<DatastreamUnits> dsUnitList = datastream.getDatastreamUnitsList();
		HashMap<String, String> mapUnits = new HashMap<String, String>();
		for (DatastreamUnits unit : dsUnitList) {
			if (unit.getShortUnitID() != null
					&& unit.getShortUnitID().length() > 1) {
				mapUnits.put(unit.getShortUnitID(), unit.getShortUnitID());
			} else {
				mapUnits.put(unit.getUnitID(), unit.getUnitID());
			}
		}
		return mapUnits;
	}

	public JsonDeviceBinding convertDeviceSerial(DeviceBinding device)
			throws ParseException {
		JsonDeviceBinding jDevice = new JsonDeviceBinding();
		jDevice.setSerial_id(device.getSerialID());
		jDevice.setActive_by(device.getActiveBy());
		jDevice.setTemplate_id(device.getTemplateid());
		jDevice.setDevice_model(device.getDeviceModel());
		jDevice.setDevice_type(device.getDeviceType());
		jDevice.setDevice_vendor(device.getDeviceVendor());
		if (device.getActiveTime() != null) {
			jDevice.setActive_time(Long.toString(device.getActiveTime()
					.getTime()));
		}
		if (device.getCreatedTime() != null) {
			jDevice.setCreated_time(Long.toString(device.getCreatedTime()
					.getTime()));
		}
		return jDevice;
	}

	public JsonDataSummary convertDataSummary(DataSummary summary)
			throws ParseException {
		JsonDataSummary jsummary = new JsonDataSummary();
		jsummary.setDatastream_id(summary.getDstreamID());
		jsummary.setValue(Double.toString(summary.getValue()));
		DateUtil dateUtil = new DateUtil();
		// System.out.println("debug converting:"+summary.getDate());
		// System.out.println("debug converting2:"+dateUtil.format(summary.getDate(),
		// dateUtil.YearMonthDay_DateFormat));
		jsummary.setDate(dateUtil.format(summary.getDate(),
				dateUtil.YearMonthDay_DateFormat));
		jsummary.setDate_long(Long.toString(summary.getDate().getTime()));
		jsummary.setGoal(Double.toString(summary.getGoal()));
		jsummary.setTitle(summary.getTitle());
		jsummary.setUnit_id(summary.getUnit_id());
		jsummary.setUpdate_time(Long.toString(summary.getUpdated().getTime()));
		return jsummary;
	}

	public JsonSleepDataSummary convertSleepDataSummary(SleepDataSummary summary)
			throws ParseException {
		JsonSleepDataSummary jsummary = new JsonSleepDataSummary();
		jsummary.setDatastream_id(summary.getDstreamID());
		DateUtil dateUtil = new DateUtil();
		jsummary.setDate(dateUtil.format(summary.getDate(),
				dateUtil.YearMonthDay_DateFormat));
		jsummary.setDate_long(Long.toString(summary.getDate().getTime()));
		jsummary.setTitle("sleep");
		jsummary.setSleep_start_time(dateUtil.format(summary.getStartTime(),
				dateUtil.isoformat));
		jsummary.setSleep_end_time(dateUtil.format(summary.getEndtime(),
				dateUtil.isoformat));
		jsummary.setSleep_start_long(summary.getStartTime().getTime());
		jsummary.setSleep_end_long(summary.getEndtime().getTime());
		jsummary.setAwakened_count(summary.getAwakeningCount());
		jsummary.setEfficiency(summary.getEfficiency());
		if (summary.getLoginID() != null) {
			jsummary.setLoginid(summary.getLoginID());
		}
		jsummary.setMinutes_afterwakeup(summary.getMinutesAfterWakeup());
		jsummary.setMinutes_asleep(summary.getMinutesAsleep());
		jsummary.setMinutes_awake(summary.getMinutesAwake());
		jsummary.setMinutes_inbed(summary.getInBedMinutes());
		jsummary.setMinutes_tofallasleep(summary.getMinutesToFallAsleep());
		jsummary.setUpdate_time(Long.toString(summary.getUpdated().getTime()));
		return jsummary;
	}
}
