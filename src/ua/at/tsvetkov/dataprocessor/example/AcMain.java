package ua.at.tsvetkov.dataprocessor.example;

import java.io.IOException;

import org.apache.http.HttpStatus;

import ua.at.tsvetkov.dataprocessor.DataProcessor;
import ua.at.tsvetkov.dataprocessor.Scheme;
import ua.at.tsvetkov.dataprocessor.requests.GetRequest;
import ua.at.tsvetkov.dataprocessor.requests.Request;
import ua.at.tsvetkov.util.Log;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

public class AcMain extends Activity {

   private ListView    acMainListItems;
   private ItemAdapter adapter;
   private FrameLayout acMainProgressLayout;

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
      DataProcessor.getInstance().executeAsync(request, ItemArray.class, callback);
      // http://mysafeinfo.com/api/data?list=states
      request = GetRequest.newInstance()
            .setScheme(Scheme.HTTP.toString())
            .setPath("api/data")
            .addGetParam("list", "states")
            .setLogTag("List of States")
            .build();
      DataProcessor.getInstance().executeAsync(request, PrintArray.class);
   }

   private DataProcessor.Callback callback = new DataProcessor.Callback() {

                                              @Override
                                              public void onFinish(final Object obj, final int what) {
                                                 acMainProgressLayout.setVisibility(View.GONE);
                                                 if (what == HttpStatus.SC_OK) {
                                                    adapter.init(obj);
                                                 } else {
                                                    Exception ex = (Exception) obj;
                                                    if (ex instanceof IOException) {
                                                       Log.e("IO Error", ex);
                                                    } else {
                                                       Log.e("Error", ex);
                                                    }
                                                 }
                                              }
                                           };

}
