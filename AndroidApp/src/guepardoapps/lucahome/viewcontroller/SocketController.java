package guepardoapps.lucahome.viewcontroller;

import android.content.Context;

import guepardoapps.lucahome.R;
import guepardoapps.lucahome.common.Constants;
import guepardoapps.lucahome.common.controller.*;
import guepardoapps.lucahome.common.enums.LucaObject;
import guepardoapps.lucahome.common.enums.RaspberrySelection;
import guepardoapps.lucahome.dto.WirelessSocketDto;
import guepardoapps.lucahome.services.PackageService;
import guepardoapps.toolset.controller.SharedPrefController;

public class SocketController {

	private Context _context;
	private ServiceController _serviceController;
	private SharedPrefController _sharedPrefController;
	private PackageService _packageService;

	public SocketController(Context context) {
		_context = context;
		_serviceController = new ServiceController(_context);
		_sharedPrefController = new SharedPrefController(_context, Constants.SHARED_PREF_NAME);
		_packageService = new PackageService(_context);
	}

	public void SetSocket(WirelessSocketDto socket, boolean newState) {
		_serviceController.StartRestService(socket.GetName(), socket.GetCommandSet(newState),
				Constants.BROADCAST_RELOAD_SOCKETS, LucaObject.WIRELESS_SOCKET, RaspberrySelection.BOTH);
	}

	public void LoadSockets() {
		_serviceController.StartRestService(Constants.SOCKET_DOWNLOAD, Constants.ACTION_GET_SOCKETS,
				Constants.BROADCAST_DOWNLOAD_SOCKET_FINISHED, LucaObject.WIRELESS_SOCKET, RaspberrySelection.BOTH);
	}

	public boolean ValidateSocketCode(String code) {
		if (code.length() != 6) {
			return false;
		}

		for (int charIndex = 0; charIndex < 5; charIndex++) {
			if (code.charAt(charIndex) == '0' || code.charAt(charIndex) == '1') {
				continue;
			} else {
				return false;
			}
		}

		if (code.charAt(5) == 'A' || code.charAt(5) == 'B' || code.charAt(5) == 'C' || code.charAt(5) == 'D'
				|| code.charAt(5) == 'E') {
			return true;
		} else {
			return false;
		}
	}

	public int GetDrawable(WirelessSocketDto socket) {
		if (socket.GetName().contains("TV")) {
			if (socket.GetIsActivated()) {
				return R.drawable.tv_on;
			} else {
				return R.drawable.tv_off;
			}
		} else if (socket.GetName().contains("Light")) {
			if (socket.GetName().contains("Sleeping")) {
				if (socket.GetIsActivated()) {
					return R.drawable.bed_light_on;
				} else {
					return R.drawable.bed_light_off;
				}
			}
		} else if (socket.GetName().contains("Sound")) {
			if (socket.GetName().contains("Sleeping")) {
				if (socket.GetIsActivated()) {
					return R.drawable.bed_sound_on;
				} else {
					return R.drawable.bed_sound_off;
				}
			} else if (socket.GetName().contains("Living")) {
				if (socket.GetIsActivated()) {
					return R.drawable.sound_on;
				} else {
					return R.drawable.sound_off;
				}
			}
		} else if (socket.GetName().contains("PC")) {
			if (socket.GetIsActivated()) {
				return R.drawable.laptop_on;
			} else {
				return R.drawable.laptop_off;
			}
		} else if (socket.GetName().contains("Printer")) {
			if (socket.GetIsActivated()) {
				return R.drawable.printer_on;
			} else {
				return R.drawable.printer_off;
			}
		} else if (socket.GetName().contains("Storage")) {
			if (socket.GetIsActivated()) {
				return R.drawable.storage_on;
			} else {
				return R.drawable.storage_off;
			}
		} else if (socket.GetName().contains("Heating")) {
			if (socket.GetName().contains("Sleeping")) {
				if (socket.GetIsActivated()) {
					return R.drawable.bed_heating_on;
				} else {
					return R.drawable.bed_heating_off;
				}
			}
		} else if (socket.GetName().contains("Farm")) {
			if (socket.GetIsActivated()) {
				return R.drawable.watering_on;
			} else {
				return R.drawable.watering_off;
			}
		}
		return -1;
	}

	public void CheckMedia(WirelessSocketDto socket) {
		if (socket.GetName().contains("Sound")) {
			if (socket.GetIsActivated()) {
				return;
			}

			if (_sharedPrefController.LoadBooleanValueFromSharedPreferences(Constants.START_AUDIO_APP)) {
				if (_packageService.IsPackageInstalled(Constants.PACKAGE_BUBBLE_UPNP)) {
					_packageService.StartApplication(Constants.PACKAGE_BUBBLE_UPNP);
				}
			}
		}
	}
}