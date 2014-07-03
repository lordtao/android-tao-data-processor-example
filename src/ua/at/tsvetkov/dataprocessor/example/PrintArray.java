package ua.at.tsvetkov.dataprocessor.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ua.at.tsvetkov.dataprocessor.interfaces.InputStreamDataInterface;
import ua.at.tsvetkov.util.Log;

public class PrintArray implements InputStreamDataInterface {

   public String src = "Empty";

   @Override
   public void fillFromInputStream(InputStream in) throws IOException {
      if (in == null) {
         Log.w("InputStream is null. Parsing aborted.");
         return;
      }
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      String line;
      while ((line = reader.readLine()) != null) {
         Log.v(line);
      }
   }

}
