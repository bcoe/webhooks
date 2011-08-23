package org.elasticsearch.webhooks;

import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;

import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.settings.Settings;

public class WebHooksResponder {
    
    private String callback;
    private ESLogger logger;
    
    public WebHooksResponder(ESLogger logger, Settings settings) {
        this.logger = logger;
        this.callback = settings.get("webhooks.callback");
    }
    
    public void respond(String event, String indexName) {
        try {
          String content = "event=" + URLEncoder.encode(event) + "&index=" + URLEncoder.encode(indexName);
          URL url = new URL(this.callback);
          URLConnection connection = url.openConnection();

          connection.setDoInput (true);
          connection.setDoOutput (true);
          connection.setUseCaches (false);
          connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

          DataOutputStream out = new DataOutputStream (connection.getOutputStream ());
          out.writeBytes(content);
          out.flush ();
          out.close ();

          BufferedReader in = new BufferedReader (new InputStreamReader(connection.getInputStream ()));
          in.close ();
        } catch (Exception e) {
            this.logger.warn(e.toString());
        }
    }
}