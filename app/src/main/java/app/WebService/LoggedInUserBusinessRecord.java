package app.WebService;

//------------------------------------------------------------------------------
// <wsdl2code-generated>
//    This code was generated by http://www.wsdl2code.com version  2.5
//
// Date Of Creation: 4/15/2015 10:32:00 PM
//    Please dont change this code, regeneration will override your changes
//</wsdl2code-generated>
//
//------------------------------------------------------------------------------
//
//This source code was auto-generated by Wsdl2Code  Version
//
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import java.util.Hashtable;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

public class LoggedInUserBusinessRecord implements KvmSerializable {

    public String _RecordID;
    public String _BussCategoryName;
    public String _BussTitle;
    public String _BussTags;
    public String _ImageName;
    public String date_added;
    public String dateApproved;
    public boolean _isApproved;
    public boolean _isExpired;
    public String date_expiry;
    public String recordID;

    public LoggedInUserBusinessRecord() {
    }

    public LoggedInUserBusinessRecord(SoapObject soapObject) {
        if (soapObject == null)
            return;
        if (soapObject.hasProperty("_RecordID")) {
            Object obj = soapObject.getProperty("_RecordID");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                _RecordID = j.toString();
            } else if (obj != null && obj instanceof String) {
                _RecordID = (String) obj;
            }
        }
        if (soapObject.hasProperty("_BussCategoryName")) {
            Object obj = soapObject.getProperty("_BussCategoryName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                _BussCategoryName = j.toString();
            } else if (obj != null && obj instanceof String) {
                _BussCategoryName = (String) obj;
            }
        }
        if (soapObject.hasProperty("_BussTitle")) {
            Object obj = soapObject.getProperty("_BussTitle");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                _BussTitle = j.toString();
            } else if (obj != null && obj instanceof String) {
                _BussTitle = (String) obj;
            }
        }
        if (soapObject.hasProperty("_BussTags")) {
            Object obj = soapObject.getProperty("_BussTags");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                _BussTags = j.toString();
            } else if (obj != null && obj instanceof String) {
                _BussTags = (String) obj;
            }
        }
        if (soapObject.hasProperty("_ImageName")) {
            Object obj = soapObject.getProperty("_ImageName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                _ImageName = j.toString();
            } else if (obj != null && obj instanceof String) {
                _ImageName = (String) obj;
            }
        }
        if (soapObject.hasProperty("Date_added")) {
            Object obj = soapObject.getProperty("Date_added");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                date_added = j.toString();
            } else if (obj != null && obj instanceof String) {
                date_added = (String) obj;
            }
        }
        if (soapObject.hasProperty("DateApproved")) {
            Object obj = soapObject.getProperty("DateApproved");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                dateApproved = j.toString();
            } else if (obj != null && obj instanceof String) {
                dateApproved = (String) obj;
            }
        }
        if (soapObject.hasProperty("_isApproved")) {
            Object obj = soapObject.getProperty("_isApproved");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                _isApproved = Boolean.parseBoolean(j.toString());
            } else if (obj != null && obj instanceof Boolean) {
                _isApproved = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("_isExpired")) {
            Object obj = soapObject.getProperty("_isExpired");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                _isExpired = Boolean.parseBoolean(j.toString());
            } else if (obj != null && obj instanceof Boolean) {
                _isExpired = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("Date_expiry")) {
            Object obj = soapObject.getProperty("Date_expiry");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                date_expiry = j.toString();
            } else if (obj != null && obj instanceof String) {
                date_expiry = (String) obj;
            }
        }
        if (soapObject.hasProperty("RecordID")) {
            Object obj = soapObject.getProperty("RecordID");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                recordID = j.toString();
            } else if (obj != null && obj instanceof String) {
                recordID = (String) obj;
            }
        }
    }
    @Override
    public Object getProperty(int arg0) {
        switch (arg0) {
            case 0:
                return _RecordID;
            case 1:
                return _BussCategoryName;
            case 2:
                return _BussTitle;
            case 3:
                return _BussTags;
            case 4:
                return _ImageName;
            case 5:
                return date_added;
            case 6:
                return dateApproved;
            case 7:
                return _isApproved;
            case 8:
                return _isExpired;
            case 9:
                return date_expiry;
            case 10:
                return recordID;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 11;
    }

    @Override
    public void getPropertyInfo(int index, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        switch (index) {
            case 0:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "_RecordID";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "_BussCategoryName";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "_BussTitle";
                break;
            case 3:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "_BussTags";
                break;
            case 4:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "_ImageName";
                break;
            case 5:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Date_added";
                break;
            case 6:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "DateApproved";
                break;
            case 7:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "_isApproved";
                break;
            case 8:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "_isExpired";
                break;
            case 9:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Date_expiry";
                break;
            case 10:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "RecordID";
                break;
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}