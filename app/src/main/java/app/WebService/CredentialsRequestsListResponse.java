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

public class CredentialsRequestsListResponse implements KvmSerializable {

    public VectorCReqMem credentialRequestsList;
    public boolean success;
    public String error;

    public CredentialsRequestsListResponse() {
    }

    public CredentialsRequestsListResponse(SoapObject soapObject) {
        if (soapObject == null)
            return;
        if (soapObject.hasProperty("CredentialRequestsList")) {
            SoapObject j = (SoapObject) soapObject.getProperty("CredentialRequestsList");
            credentialRequestsList = new VectorCReqMem(j);
        }
        if (soapObject.hasProperty("Success")) {
            Object obj = soapObject.getProperty("Success");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                success = Boolean.parseBoolean(j.toString());
            } else if (obj != null && obj instanceof Boolean) {
                success = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("Error")) {
            Object obj = soapObject.getProperty("Error");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                error = j.toString();
            } else if (obj != null && obj instanceof String) {
                error = (String) obj;
            }
        }
    }

    @Override
    public Object getProperty(int arg0) {
        switch (arg0) {
            case 0:
                return credentialRequestsList;
            case 1:
                return success;
            case 2:
                return error;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 3;
    }

    @Override
    public void getPropertyInfo(int index, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        switch (index) {
            case 0:
                info.type = PropertyInfo.VECTOR_CLASS;
                info.name = "CredentialRequestsList";
                break;
            case 1:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "Success";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Error";
                break;
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
