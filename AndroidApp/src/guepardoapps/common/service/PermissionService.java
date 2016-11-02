package guepardoapps.common.service;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import guepardoapps.common.Logger;
import guepardoapps.common.enums.Permissions;

public class PermissionService {

	private static String TAG = "PermissionService";
	private Logger _logger;

	private Context _context;

	public PermissionService(Context context) {
		_logger = new Logger(TAG);
		_context = context;
	}

	public void RequestPermission(Permissions permission) {
		ActivityCompat.requestPermissions((Activity) _context, new String[] { permission.GetPermissionString() },
				permission.GetRequestId());
	}

	public boolean CheckPermission(Permissions permission) {
		_logger.Debug("Checking permission: " + permission.GetPermissionString());
		int res = _context.checkCallingOrSelfPermission(permission.GetPermissionString());
		boolean hasPermission = res == PackageManager.PERMISSION_GRANTED;
		_logger.Debug("Permission allowed: " + String.valueOf(hasPermission));
		return hasPermission;
	}
}