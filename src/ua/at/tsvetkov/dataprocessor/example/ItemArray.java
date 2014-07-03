package ua.at.tsvetkov.dataprocessor.example;

import java.util.ArrayList;

import org.json.JSONArray;

import ua.at.tsvetkov.dataprocessor.interfaces.StringDataInterface;

public class ItemArray implements StringDataInterface {

   public ArrayList<Item> array = new ArrayList<Item>();

   @Override
   public void fillFromString(String src) throws Exception {
      JSONArray jsonArray = new JSONArray(src);
      for (int i = 0; i < jsonArray.length(); i++) {
         Item item = new Item(jsonArray.getJSONObject(i));
         array.add(item);
      }
   }

}
