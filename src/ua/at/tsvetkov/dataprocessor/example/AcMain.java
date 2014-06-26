package ua.at.tsvetkov.dataprocessor.example;

import java.io.IOException;

import org.apache.http.HttpStatus;

import ua.at.tsvetkov.dataprocessor.DataProcessor;
import ua.at.tsvetkov.dataprocessor.requests.GetRequest;
import ua.at.tsvetkov.dataprocessor.requests.Request;
import ua.at.tsvetkov.util.Log;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

public class AcMain extends Activity {

	private ListView		acMainListItems;
	private ItemAdapter	adapter;
	private FrameLayout	acMainProgressLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_main);
		acMainListItems = (ListView) findViewById(R.id.acMainListItems);
		acMainProgressLayout = (FrameLayout) findViewById(R.id.acMainProgressLayout);

		adapter = new ItemAdapter(this);
		acMainListItems.setAdapter(adapter);
		getData();
	}

	private void getData() {
		// http://mysafeinfo.com/api/data?list=englishmonarchs&format=json
		Request request = GetRequest.newInstance()
				.setPath("api/data")
				.addGetParam("list", "englishmonarchs")
				.addGetParam("format", "json")
				.setLogTag("List of English monarchs")
				.build();
		DataProcessor.getInstance().executeAsync(request, new ItemArray(), handler);
	}

	@SuppressLint("HandlerLeak")
	private Handler	handler	= new Handler() {

											@Override
											public void handleMessage(Message msg) {
												acMainProgressLayout.setVisibility(View.GONE);
												if (msg.what == HttpStatus.SC_OK) {
													adapter.init(msg.obj);
												} else {
													Exception ex = (Exception) msg.obj;
													if (ex instanceof IOException) {
														Log.e("IO Error", ex);
													} else {
														Log.e("Error", ex);
													}
												}
											}

										};
}
