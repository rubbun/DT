package app.WebService;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.util.Hashtable;

/**
 * Created by Yaju on 5/30/2015.
 */
public class GCPResponse implements KvmSerializable {
    public  boolean GCPResult;
    public String cid;
    public String cimage;

    public GCPResponse(SoapObject soapObject){
        if (soapObject == null)
            return;
        if (soapObject.hasProperty("GCPResult")) {
            Object obj = soapObject.getProperty("GCPResult");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                GCPResult = Boolean.parseBoolean(j.toString());
            } else if (obj != null && obj instanceof Boolean) {
                GCPResult = (Boolean) obj;
            }
        }

        if (soapObject.hasProperty("cid")) {
            Object obj = soapObject.getProperty("cid");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                cid = j.toString();
            } else if (obj != null && obj instanceof String) {
                cid = (String) obj;
            }
        }

        if (soapObject.hasProperty("cimage")) {
            Object obj = soapObject.getProperty("cimage");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                cimage = j.toString();
            } else if (obj != null && obj instanceof String) {
                cimage = (String) obj;
            }
        }


    }

    @Override
    public Object getProperty(int arg0) {
        switch(arg0){
            case 0:
                return GCPResult;
            case 1:
                return cid;
            case 2:
                return cimage;

        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 3;
    }

    @Override
    public void setProperty(int i, Object o) {

    }

    @Override
    public void getPropertyInfo(int index, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        switch (index) {
            case 0:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "GCPResult";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "cid";
                break;
            case 2:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "cimage";
                break;

        }
    }
}
