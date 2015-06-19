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

public class GuestBusinessRecord implements KvmSerializable {

    public String _RecordID;
    public String _BussTitle;
    public String _BussTags;
    public String _ImageName;
    public boolean bookmark;
    public String bookID;
    public String addedBy;

    public GuestBusinessRecord() {
    }

    public GuestBusinessRecord(SoapObject soapObject) {
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
        if (soapObject.hasProperty("Bookmark")) {
            Object obj = soapObject.getProperty("Bookmark");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                bookmark = Boolean.parseBoolean(j.toString());
            } else if (obj != null && obj instanceof Boolean) {
                bookmark = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("BookID")) {
            Object obj = soapObject.getProperty("BookID");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                bookID = j.toString();
            } else if (obj != null && obj instanceof String) {
                bookID = (String) obj;
            }
        }
        if (soapObject.hasProperty("AddedBy")) {
            Object obj = soapObject.getProperty("AddedBy");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                addedBy = j.toString();
            } else if (obj != null && obj instanceof String) {
                addedBy = (String) obj;
            }
        }
    }
    @Override
    public Object getProperty(int arg0) {
        switch (arg0) {
            case 0:
                return _RecordID;
            case 1:
                return _BussTitle;
            case 2:
                return _BussTags;
            case 3:
                return _ImageName;
            case 4:
                return bookmark;
            case 5:
                return bookID;
            case 6:
                return addedBy;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 7;
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
                info.name = "_BussTitle";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "_BussTags";
                break;
            case 3:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "_ImageName";
                break;
            case 4:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "Bookmark";
                break;
            case 5:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "BookID";
                break;
            case 6:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "AddedBy";
                break;
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
