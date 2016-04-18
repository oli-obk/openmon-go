package net.osmand.osmandapidemo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OsmAndHelper {
	private static final String PREFIX = "osmand.api://";

	// Result codes
	// RESULT_OK == -1
	// RESULT_CANCELED == 0
	// RESULT_FIRST_USER == 1
	// from Activity
	public static final int RESULT_CODE_ERROR_UNKNOWN = -3;
	public static final int RESULT_CODE_ERROR_NOT_IMPLEMENTED = -2;
	public static final int RESULT_CODE_ERROR_PLUGIN_INACTIVE = 10;
	public static final int RESULT_CODE_ERROR_GPX_NOT_FOUND = 20;
	public static final int RESULT_CODE_ERROR_INVALID_PROFILE = 30;

	// Information
	private static final String GET_INFO = "get_info";

	// Related to recording media
	private static final String RECORD_AUDIO = "record_audio";
	private static final String RECORD_VIDEO = "record_video";
	private static final String RECORD_PHOTO = "record_photo";
	private static final String STOP_AV_REC = "stop_av_rec";

	private static final String ADD_FAVORITE = "add_favorite";
	private static final String ADD_MAP_MARKER = "add_map_marker";

	private static final String SHOW_GPX = "show_gpx";
	private static final String NAVIGATE_GPX = "navigate_gpx";

	private static final String NAVIGATE = "navigate";

	private static final String START_GPX_REC = "start_gpx_rec";
	private static final String STOP_GPX_REC = "stop_gpx_rec";

	// Parameters
	public static final String API_CMD_SUBSCRIBE_VOICE_NOTIFICATIONS = "subscribe_voice_notifications";

	public static final String PARAM_NAME = "name";
	public static final String PARAM_DESC = "desc";
	public static final String PARAM_CATEGORY = "category";
	public static final String PARAM_LAT = "lat";
	public static final String PARAM_LON = "lon";
	public static final String PARAM_COLOR = "color";
	public static final String PARAM_VISIBLE = "visible";

	public static final String PARAM_PATH = "path";
	public static final String PARAM_DATA = "data";
	public static final String PARAM_FORCE = "force";

	public static final String PARAM_START_NAME = "start_name";
	public static final String PARAM_DEST_NAME = "dest_name";
	public static final String PARAM_START_LAT = "start_lat";
	public static final String PARAM_START_LON = "start_lon";
	public static final String PARAM_DEST_LAT = "dest_lat";
	public static final String PARAM_DEST_LON = "dest_lon";
	public static final String PARAM_PROFILE = "profile";

	public static final String PARAM_ETA = "eta";
	public static final String PARAM_TIME_LEFT = "time_left";
	public static final String PARAM_DISTANCE_LEFT = "time_distance_left";

	private final int mRequestCode;
	private final Activity mActivity;
	private final OnOsmandMissingListener mOsmandMissingListener;

	public OsmAndHelper(Activity activity, int requestCode, OnOsmandMissingListener listener) {
		this.mRequestCode = requestCode;
		mActivity = activity;
		mOsmandMissingListener = listener;
	}

	public void getInfo() {
		// test get info
		Uri uri = Uri.parse(getUriString(GET_INFO, null));
		sendRequest(uri);
	}

	public void recordAudio(double lat, double lon) {
		// test record audio
		Map<String, String> params = new HashMap<>();
		params.put(PARAM_LAT, String.valueOf(lat));
		params.put(PARAM_LON, String.valueOf(lon));
		Uri uri = Uri.parse(getUriString(RECORD_AUDIO, params));
		sendRequest(uri);
	}

	public void recordVideo(double lat, double lon) {
		// test record video
		Map<String, String> params = new HashMap<>();
		params.put(PARAM_LAT, String.valueOf(lat));
		params.put(PARAM_LON, String.valueOf(lon));
		Uri uri = Uri.parse(getUriString(RECORD_VIDEO, params));
		sendRequest(uri);
	}

	public void recordPhoto(double lat, double lon) {
		// test record photo
		Map<String, String> params = new HashMap<>();
		params.put(PARAM_LAT, String.valueOf(lat));
		params.put(PARAM_LON, String.valueOf(lon));
		Uri uri = Uri.parse(getUriString(RECORD_PHOTO, params));
		sendRequest(uri);
	}

	public void stopAvRec() {
		// test stop recording
		Uri uri = Uri.parse(getUriString(STOP_AV_REC, null));
		sendRequest(uri);
	}

	public void addMapMarker(double lat, double lon, String name) {
		// test marker
		Map<String, String> params = new HashMap<>();
		params.put(PARAM_LAT, String.valueOf(lat));
		params.put(PARAM_LON, String.valueOf(lon));
		params.put(PARAM_NAME, name);
		Uri uri = Uri.parse(getUriString(ADD_MAP_MARKER, params));
		sendRequest(uri);
	}

	// TODO covert color to set
	public void addFavorite(double lat, double lon, String name,
							String description, String category, String color,
							boolean visible) {
		// test favorite
		Map<String, String> params = new HashMap<>();
		params.put(PARAM_LAT, String.valueOf(lat));
		params.put(PARAM_LON, String.valueOf(lon));
		params.put(PARAM_NAME, name);
		params.put(PARAM_DESC, description);
		params.put(PARAM_CATEGORY, category);
		params.put(PARAM_COLOR, color);
		params.put(PARAM_VISIBLE, String.valueOf(visible));
		Uri uri = Uri.parse(getUriString(ADD_FAVORITE, params));
		sendRequest(uri);
	}

	public void startGpxRec() {
		// test start gpx recording
		Uri uri = Uri.parse(getUriString(START_GPX_REC, null));
		sendRequest(uri);
	}

	public void stopGpxRec() {
		// test stop gpx recording
		Uri uri = Uri.parse(getUriString(STOP_GPX_REC, null));
		sendRequest(uri);
	}

	public void showGpx() {
		// test show gpx (path)
		//File gpx = new File(app.getAppPath(IndexConstants.GPX_INDEX_DIR), gpxName);
		//uri = Uri.parse("osmand.api://show_gpx?path=" + URLEncoder.encode(gpx.getAbsolutePath(), "UTF-8"));

		// test show gpx (data)
		Uri uri = Uri.parse(getUriString(SHOW_GPX, null));
		sendRequest(uri);
		//intent.putExtra("data", AndroidUtils.getFileAsString(
		//		new File(app.getAppPath(IndexConstants.GPX_INDEX_DIR), gpxName)));
	}

	public void navigateGpx(boolean force, String path) {
		// test navigate gpx (path)
		//File gpx = new File(app.getAppPath(IndexConstants.GPX_INDEX_DIR), gpxName);
		//uri = Uri.parse("osmand.api://navigate_gpx?force=true&path=" + URLEncoder.encode(gpx.getAbsolutePath(), "UTF-8"));

		Map<String, String> params = new HashMap<>();
		params.put(PARAM_FORCE, String.valueOf(force));
		params.put(PARAM_PATH, path);
		Uri uri = Uri.parse(getUriString(NAVIGATE_GPX, params));
		sendRequest(uri);
		//intent.putExtra("data", AndroidUtils.getFileAsString(
		//		new File(app.getAppPath(IndexConstants.GPX_INDEX_DIR), gpxName)));
	}

	public void navigate(String startName, double startLat, double startLon,
						 String destName, double destLat, double destLon,
						 String profile) {
		// test navigate
		Map<String, String> params = new HashMap<>();
		params.put(PARAM_START_LAT, String.valueOf(startLat));
		params.put(PARAM_START_LON, String.valueOf(startLon));
		params.put(PARAM_START_NAME, startName);
		params.put(PARAM_DEST_LAT, String.valueOf(destLat));
		params.put(PARAM_DEST_LON, String.valueOf(destLon));
		params.put(PARAM_DEST_NAME, destName);
		params.put(PARAM_PROFILE, profile);
		Uri uri = Uri.parse(getUriString(NAVIGATE, params));
		sendRequest(uri);
	}

	private String getUriString(@NonNull @NotNull String command,
								@Nullable Map<String, String> parameters) {
		StringBuilder stringBuilder = new StringBuilder(PREFIX);
		stringBuilder.append(command);
		if (parameters != null && parameters.size() > 0) {
			stringBuilder.append("?");
			for (String key : parameters.keySet()) {
				stringBuilder.append(key).append("=").append(parameters.get(key)).append("&");
			}
			stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
		}
		return stringBuilder.toString();
	}

	private void sendRequest(Uri uri) {
		sendRequest(uri, mRequestCode);
	}

	private void sendRequest(Uri uri, int requestCode) {
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		if (isIntentSafe(intent)) {
			mActivity.startActivityForResult(intent, requestCode);
		} else {
			mOsmandMissingListener.osmandMissing();
		}
	}

	public boolean isIntentSafe(Intent intent) {
		PackageManager packageManager = mActivity.getPackageManager();
		List activities = packageManager.queryIntentActivities(intent,
				PackageManager.MATCH_DEFAULT_ONLY);
		return activities.size() > 0;
	}

	public interface OnOsmandMissingListener {
		void osmandMissing();
	}
}
