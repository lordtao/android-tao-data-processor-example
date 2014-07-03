package ua.at.tsvetkov.dataprocessor.example;

import ua.at.tsvetkov.application.AppConfig;
import ua.at.tsvetkov.dataprocessor.DataProcessor;
import ua.at.tsvetkov.dataprocessor.DataProcessorConfiguration;
import android.app.Application;

public class TestApplication extends Application {

   /*
    * (non-Javadoc)
    * @see android.app.Application#onCreate()
    */
   @Override
   public void onCreate() {
      super.onCreate();
      AppConfig.init(this);
      initDataProcessor();
   }

   /**
    * Example of Data Processor init
    */
   private void initDataProcessor() {
      DataProcessorConfiguration configuration = DataProcessorConfiguration.getBuilder().setHost("mysafeinfo.com").setLogEnabled(true).setShowProcessingTime(true).build();
      DataProcessor.getInstance().init(configuration);
   }

}
