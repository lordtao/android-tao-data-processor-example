package ua.at.tsvetkov.dataprocessor.example;

import java.util.ArrayList;

import org.json.JSONArray;

import ua.at.tsvetkov.data_processor.interfaces.StringDataInterface;

public class MonarchArray implements StringDataInterface {

   public ArrayList<Monarch> array = new ArrayList<Monarch>();

   @Override
   public void fillFromString(String src) throws Exception {
      JSONArray jsonArray = new JSONArray(src);
      for (int i = 0; i < jsonArray.length(); i++) {
         Monarch item = new Monarch(jsonArray.getJSONObject(i));
         array.add(item);
      }
   }

}
