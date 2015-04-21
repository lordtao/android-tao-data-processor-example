package ua.at.tsvetkov.dataprocessor.example;

import org.json.JSONException;
import org.json.JSONObject;

public class Monarch {

   public String nm;
   public String cty;
   public String hse;
   public String yrs;

   public Monarch(JSONObject obj) throws JSONException {
      nm = obj.getString("nm");
      cty = obj.getString("cty");
      hse = obj.getString("hse");
      yrs = obj.getString("yrs");
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Monarch [nm=");
      builder.append(nm);
      builder.append(", cty=");
      builder.append(cty);
      builder.append(", hse=");
      builder.append(hse);
      builder.append(", yrs=");
      builder.append(yrs);
      builder.append("]");
      return builder.toString();
   }

}
