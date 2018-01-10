/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.immregistries.dqa.message_modifier.legacy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 
 * @author nathan
 */
public abstract class Connector {

  public static final String PURPOSE_GENERAL = "General";
  public static final String PURPOSE_UPDATE = "Update";
  public static final String PURPOSE_QUERY = "Query";

  private boolean setupGlobalKeyStore = true;

  public boolean isSetupGlobalKeyStore() {
    return setupGlobalKeyStore;
  }

  public void setSetupGlobalKeyStore(boolean setupGlobalKeyStore) {
    this.setupGlobalKeyStore = setupGlobalKeyStore;
  }

  protected abstract void setupFields(List<String> fields);

  

  public static enum TransferType {
    NEAR_REAL_TIME_LINK, RECIPROCAL_BATCH_UPDATE, MANUAL
  };

  protected String label = "";
  protected String type = "";
  protected String userid = "";
  protected String otherid = "";
  protected String password = "";
  protected String facilityid = "";
  protected String url = "";
  protected String currentFilename = "";
  protected String currentControlId = "";
  protected String enableTimeStart = "";
  protected String enableTimeEnd = "";
  protected boolean disableServerCertificateCheck = false;
  protected TransferType transferType = TransferType.NEAR_REAL_TIME_LINK;
  private String customTransformations = "";
  private String assessmentTransformations = "";
  private String[] quickTransformations;
  private KeyStore keyStore = null;
  private String keyStorePassword = null;
  private Map<String, Connector> otherConnectorMap = new HashMap<String, Connector>();
  private String purpose = "";
  private Set<String> queryResponseFieldsNotReturnedSet = null;
  private Map<String, String> scenarioTransformationsMap = new HashMap<String, String>();
  private String segmentSeparator = "\r";
  private String rxaFilterFacilityId = "";
  private String aartPublicIdCode = "";
  private String aartAccessPasscode = "";

  public String getAssessmentTransformations() {
    return assessmentTransformations;
  }

  public void setAssessmentTransformations(String assessmentTransformations) {
    this.assessmentTransformations = assessmentTransformations;
  }

  public String getAartPublicIdCode() {
    return aartPublicIdCode;
  }

  public void setAartPublicIdCode(String aartPublicIdCode) {
    this.aartPublicIdCode = aartPublicIdCode;
  }

  public String getAartAccessPasscode() {
    return aartAccessPasscode;
  }

  public void setAartAccessPasscode(String aartAccessPasscode) {
    this.aartAccessPasscode = aartAccessPasscode;
  }

  public String getRxaFilterFacilityId() {
    return rxaFilterFacilityId;
  }

  public void setRxaFilterFacilityId(String rxaFilterFacilityId) {
    this.rxaFilterFacilityId = rxaFilterFacilityId;
  }

  public boolean isRxaFilter() {
    return rxaFilterFacilityId != null && !rxaFilterFacilityId.equals("");
  }

  public String getSegmentSeparator() {
    return segmentSeparator;
  }

  public void setSegmentSeparator(String segmentSeparator) {
    this.segmentSeparator = segmentSeparator;
  }

  public boolean isDisableServerCertificateCheck() {
    return disableServerCertificateCheck;
  }

  public void setDisableServerCertificateCheck(boolean disableServerCertificateCheck) {
    this.disableServerCertificateCheck = disableServerCertificateCheck;
  }

  public void setScenarioTransformationsMap(Map<String, String> scenarioTransformationsMap) {
    this.scenarioTransformationsMap = scenarioTransformationsMap;
  }

  public Map<String, String> getScenarioTransformationsMap() {
    return scenarioTransformationsMap;
  }

  public void setQueryResponseFieldsNotReturnedSet(Set<String> queryResponseFieldsNotReturnedSet) {
    this.queryResponseFieldsNotReturnedSet = queryResponseFieldsNotReturnedSet;
  }

  public Set<String> getQueryResponseFieldsNotReturnedSet() {
    return queryResponseFieldsNotReturnedSet;
  }

  private int tchForecastTesterSoftwareId = 0;
  private int tchForecastTesterTaskGroupId = 0;

  public int getTchForecastTesterTaskGroupId() {
    return tchForecastTesterTaskGroupId;
  }

  public void setTchForecastTesterTaskGroupId(int tchForecastTesterTaskGroupId) {
    this.tchForecastTesterTaskGroupId = tchForecastTesterTaskGroupId;
  }

  public int getTchForecastTesterSoftwareId() {
    return tchForecastTesterSoftwareId;
  }

  public void setTchForecastTesterSoftwareId(int tchForecastTesterSoftwareId) {
    this.tchForecastTesterSoftwareId = tchForecastTesterSoftwareId;
  }

  public Map<String, Connector> getOtherConnectorMap() {
    return otherConnectorMap;
  }

  public String getPurpose() {
    return purpose;
  }

  public void setPurpose(String purpose) {
    this.purpose = purpose;
  }

  public String getOtherid() {
    return otherid;
  }

  public void setOtherid(String otherid) {
    this.otherid = otherid;
  }

  public String getCurrentControlId() {
    return currentControlId;
  }

  public void setCurrentControlId(String currentControlId) {
    this.currentControlId = currentControlId;
  }

  public String getEnableTimeStart() {
    return enableTimeStart;
  }

  public void setEnableTimeStart(String enableTimeStart) {
    this.enableTimeStart = enableTimeStart;
  }

  public String getEnableTimeEnd() {
    return enableTimeEnd;
  }

  public void setEnableTimeEnd(String enableTimeEnd) {
    this.enableTimeEnd = enableTimeEnd;
  }

  public TransferType getTransferType() {
    return transferType;
  }

  public void setTransferType(TransferType transferType) {
    this.transferType = transferType;
  }

  protected boolean throwExceptions = false;

  public String getCurrentFilename() {
    return currentFilename;
  }

  public void setCurrentFilename(String currentFilename) {
    this.currentFilename = currentFilename;
  }

  public boolean isThrowExceptions() {
    return throwExceptions;
  }

  public void setThrowExceptions(boolean throwExceptions) {
    this.throwExceptions = throwExceptions;
  }

  public String getKeyStorePassword() {
    return keyStorePassword;
  }

  public void setKeyStorePassword(String keyStorePassword) {
    this.keyStorePassword = keyStorePassword;
  }

  public KeyStore getKeyStore() {
    return keyStore;
  }

  public void setKeyStore(KeyStore keyStore) {
    this.keyStore = keyStore;
  }

  public String[] getQuickTransformations() {
    return quickTransformations;
  }

  public void setQuickTransformations(String[] quickTransformations) {
    this.quickTransformations = quickTransformations;
  }

  public String getCustomTransformations() {
    return customTransformations;
  }

  public void setCustomTransformations(String customTransformations) {
    this.customTransformations = customTransformations;
  }

  public void addCustomTransformation(String customTransformation) {
    if (this.customTransformations == null) {
      this.customTransformations = customTransformation + "/n";
    } else {
      this.customTransformations += customTransformation + "/n";
    }
  }

  public void addAsssementTransformation(String assessmentTransformation) {
    if (this.assessmentTransformations == null) {
      this.assessmentTransformations = assessmentTransformations + "/n";
    } else {
      this.assessmentTransformations += assessmentTransformations + "/n";
    }
  }

  public String getUrl() {
    return url;
  }

  public String getUrlShort() {
    if (url != null && url.length() > 28) {
      return url.substring(0, 28) + "..";
    }
    return url;
  }

  public String getLabelDisplay() {
    if (purpose.equals("")) {
      return label + " (" + type + ")";
    }
    return label + " - " + purpose + " (" + type + ")";
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getFacilityid() {
    return facilityid;
  }

  public void setFacilityid(String facilityid) {
    this.facilityid = facilityid;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getLabel() {
    return label;
  }

  public boolean isVerify() {
    return url.indexOf("VerifyServlet") > 0;
  }

  public Connector(String label, String type) {
    this.label = label;
    this.type = type;
  }

  public abstract String submitMessage(String message, boolean debug) throws Exception;

  public abstract String connectivityTest(String message) throws Exception;

  public void printTransformString(StringBuilder sb, String transformString) {
    try {
      BufferedReader inTransform = new BufferedReader(new StringReader(transformString));
      String line;
      while ((line = inTransform.readLine()) != null) {
        line = line.trim();
        sb.append(" + " + line + "\n");
      }
    } catch (IOException ioe) {
      // IOException not expected when reading a string
      throw new RuntimeException("Exception while reading string", ioe);
    }
  }

  protected abstract void makeScriptAdditions(StringBuilder sb);

  protected static String readValue(String line) {
    int pos = line.indexOf(":");
    if (pos == -1) {
      return "";
    }
    return line.substring(pos + 1).trim();
  }

  // private SSLServerSocket createServerSocketFromKeyStore()
  // {
  // SSLServerSocketFactory ssf; // server socket factory
  // SSLServerSocket skt; // server socket
  //
  // // LOAD EXTERNAL KEY STORE
  // KeyStore mstkst;
  // try
  // {
  // mstkst = KeyStore.getInstance("jks");
  // mstkst.load(new FileInputStream(keyStoreFile),
  // keyStorePassword.toCharArray());
  // } catch (java.security.GeneralSecurityException thr)
  // {
  // throw new IOException("Cannot load keystore (" + thr + ")");
  // }
  //
  // // CREATE EPHEMERAL KEYSTORE FOR THIS SOCKET USING DESIRED CERTIFICATE
  // try
  // {
  // SSLContext ctx = SSLContext.getInstance("TLS");
  // KeyManagerFactory kmf =
  // KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
  // KeyStore sktkst;
  // char[] blkpwd = new char[0];
  //
  // sktkst = KeyStore.getInstance("jks");
  // sktkst.load(null, blkpwd);
  // sktkst.setKeyEntry(svrctfals, mstkst.getKey(svrctfals, blkpwd), blkpwd,
  // mstkst.getCertificateChain(svrctfals));
  // kmf.init(sktkst, blkpwd);
  // ctx.init(kmf.getKeyManagers(), null, null);
  // ssf = ctx.getServerSocketFactory();
  // } catch (java.security.GeneralSecurityException thr)
  // {
  // throw new IOException("Cannot create secure socket (" + thr + ")");
  // }
  //
  // // CREATE AND INITIALIZE SERVER SOCKET
  // skt = (SSLServerSocket) ssf.createServerSocket(prt, bcklog, adr);
  // return skt;
  // }

  protected static class SavingTrustManager implements X509TrustManager {

    private final X509TrustManager tm;
    private X509Certificate[] chain;

    SavingTrustManager(X509TrustManager tm) {
      this.tm = tm;
    }

    public X509Certificate[] getAcceptedIssuers() {
      return tm.getAcceptedIssuers();
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
      throw new UnsupportedOperationException();
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
      this.chain = chain;
      tm.checkServerTrusted(chain, authType);
    }

  }

  protected static TrustManager[] trustAllCerts;

  private static void installTrustAllCerts() {
    if (trustAllCerts != null) {
      trustAllCerts = new TrustManager[] { new X509TrustManager() {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
          return null;
        }

        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
        }
      } };
    }

    // Install the all-trusting trust manager
    try {
      SSLContext sc = SSLContext.getInstance("SSL");
      sc.init(null, trustAllCerts, new java.security.SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    } catch (GeneralSecurityException e) {
      // ignore
    }

  }

  protected static String replaceAmpersand(String s) {
    String s2 = "";
    int pos = s.indexOf("&");
    while (pos != -1) {
      s2 = s2 + s.substring(0, pos);
      s2 = s2 + "&amp;";
      s = s.substring(pos + 1);
      pos = s.indexOf("&");
    }
    s2 = s2 + s;
    return s2;
  }

}
