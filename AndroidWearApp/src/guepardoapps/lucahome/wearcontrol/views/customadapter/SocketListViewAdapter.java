package guepardoapps.lucahome.wearcontrol.views.customadapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import guepardoapps.lucahome.R;
import guepardoapps.lucahome.common.Constants;
import guepardoapps.lucahome.wearcontrol.helper.MessageSendHelper;
import guepardoapps.lucahome.wearcontrol.views.listitem.SocketListViewItem;

import guepardoapps.toolset.common.Logger;

public class SocketListViewAdapter extends BaseAdapter {

	private static final String TAG = SocketListViewAdapter.class.getName();
	private Logger _logger;

	private List<SocketListViewItem> _list;

	private Context _context;
	private MessageSendHelper _messageSendHelper;

	private static LayoutInflater _inflater = null;

	public SocketListViewAdapter(Context context, MessageSendHelper messageSendHelper,
			List<SocketListViewItem> _viewItemList) {
		_logger = new Logger(TAG, Constants.DEBUGGING_ENABLED);

		_list = _viewItemList;
		_context = context;
		_messageSendHelper = messageSendHelper;

		_inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return _list.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public class Holder {
		private TextView _name;
		private Button _state;
	}

	@SuppressWarnings("deprecation")
	@SuppressLint({ "InflateParams", "ViewHolder" })
	@Override
	public View getView(final int index, View convertView, ViewGroup parent) {
		Holder holder = new Holder();
		View rowView = _inflater.inflate(R.layout.list_item_socket, null);

		Drawable drawable = _context.getResources().getDrawable(_list.get(index).GetImageResource());
		drawable.setBounds(0, 0, 20, 20);

		holder._name = (TextView) rowView.findViewById(R.id.list_socket_item_name);
		holder._name.setText(_list.get(index).GetName());
		holder._name.setCompoundDrawablesRelative(drawable, null, null, null);

		holder._state = (Button) rowView.findViewById(R.id.list_socket_item_button);
		holder._state.setText(_list.get(index).GetIsActivated() ? "ON" : "OFF");
		holder._state.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				_logger.Debug("Toggle state of " + _list.get(index).GetName());
				if (_messageSendHelper != null) {
					_messageSendHelper.SendMessage(_list.get(index).GetCommandText());
				}
			}
		});

		return rowView;
	}
}