package ua.at.tsvetkov.dataprocessor.example;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

	public ArrayList<Item>	data	= new ArrayList<Item>();
	private Activity			activity;
	private LayoutInflater	inflater;

	public ItemAdapter(Activity activity) {
		this.activity = activity;
		inflater = LayoutInflater.from(activity);
	}

	public void init(final Object obj) {
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				data = ((ItemArray) obj).array;
				notifyDataSetChanged();
			}
		});

	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		InfoHolder holder = new InfoHolder();
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.adt_main, null);
			holder.name = (TextView) convertView.findViewById(R.id.adtMainTxtName);
			holder.city = (TextView) convertView.findViewById(R.id.adtMainTxtCity);
			holder.house = (TextView) convertView.findViewById(R.id.adtMainTxtHouse);
			holder.years = (TextView) convertView.findViewById(R.id.adtMainTxtYears);
			convertView.setTag(holder);
		} else {
			holder = (InfoHolder) convertView.getTag();
		}
		Item item = data.get(position);
		holder.name.setText(item.nm);
		holder.city.setText(item.cty);
		holder.house.setText(item.hse);
		holder.years.setText(item.yrs);
		return convertView;
	}

	class InfoHolder {

		TextView		name;
		TextView		city;
		TextView		house;
		TextView		years;
	}


}
