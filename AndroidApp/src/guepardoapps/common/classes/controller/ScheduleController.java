package guepardoapps.common.classes.controller;

import android.content.Context;

import guepardoapps.common.Constants;
import guepardoapps.common.classes.Schedule;
import guepardoapps.common.controller.*;
import guepardoapps.common.enums.LucaObject;
import guepardoapps.common.enums.RaspberrySelection;

public class ScheduleController {

	@SuppressWarnings("unused")
	private static String TAG = "ScheduleController";

	private Context _context;

	private ServiceController _serviceController;

	public ScheduleController(Context context) {
		_context = context;
		_serviceController = new ServiceController(_context);
	}

	public void SetSchedule(Schedule schedule, boolean newState) {
		_serviceController.StartRestService(schedule.GetName(), schedule.GetCommandSet(newState),
				Constants.BROADCAST_RELOAD_SCHEDULE, LucaObject.SCHEDULE, RaspberrySelection.BOTH);
	}
}