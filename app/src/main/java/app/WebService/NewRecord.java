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

public class NewRecord implements KvmSerializable {

    public String businessCategory;
    public String services;
    public String primaryMobileNo;
    public String website;
    public String addEmails;
    public String addMobileNumber;
    public String captchaID;
    public String captchaValue;
    public String businessTitle;
    public String primaryEmail;
    public String phoneNumbers;
    public String contactPerson;
    public String contactAddress;
    public String faxNumbers;
    public VectorByte logoImage;
    public boolean update;
    public String updateRecID;

    public NewRecord() {
    }

    public NewRecord(SoapObject soapObject) {
        if (soapObject == null)
            return;
        if (soapObject.hasProperty("BusinessCategory")) {
            Object obj = soapObject.getProperty("BusinessCategory");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                businessCategory = j.toString();
            } else if (obj != null && obj instanceof String) {
                businessCategory = (String) obj;
            }
        }
        if (soapObject.hasProperty("Services")) {
            Object obj = soapObject.getProperty("Services");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                services = j.toString();
            } else if (obj != null && obj instanceof String) {
                services = (String) obj;
            }
        }
        if (soapObject.hasProperty("PrimaryMobileNo")) {
            Object obj = soapObject.getProperty("PrimaryMobileNo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                primaryMobileNo = j.toString();
            } else if (obj != null && obj instanceof String) {
                primaryMobileNo = (String) obj;
            }
        }
        if (soapObject.hasProperty("Website")) {
            Object obj = soapObject.getProperty("Website");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                website = j.toString();
            } else if (obj != null && obj instanceof String) {
                website = (String) obj;
            }
        }
        if (soapObject.hasProperty("AddEmails")) {
            Object obj = soapObject.getProperty("AddEmails");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                addEmails = j.toString();
            } else if (obj != null && obj instanceof String) {
                addEmails = (String) obj;
            }
        }
        if (soapObject.hasProperty("AddMobileNumber")) {
            Object obj = soapObject.getProperty("AddMobileNumber");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                addMobileNumber = j.toString();
            } else if (obj != null && obj instanceof String) {
                addMobileNumber = (String) obj;
            }
        }
        if (soapObject.hasProperty("captchaID")) {
            Object obj = soapObject.getProperty("captchaID");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                captchaID = j.toString();
            } else if (obj != null && obj instanceof String) {
                captchaID = (String) obj;
            }
        }
        if (soapObject.hasProperty("captchaValue")) {
            Object obj = soapObject.getProperty("captchaValue");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                captchaValue = j.toString();
            } else if (obj != null && obj instanceof String) {
                captchaValue = (String) obj;
            }
        }
        if (soapObject.hasProperty("BusinessTitle")) {
            Object obj = soapObject.getProperty("BusinessTitle");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                businessTitle = j.toString();
            } else if (obj != null && obj instanceof String) {
                businessTitle = (String) obj;
            }
        }
        if (soapObject.hasProperty("PrimaryEmail")) {
            Object obj = soapObject.getProperty("PrimaryEmail");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                primaryEmail = j.toString();
            } else if (obj != null && obj instanceof String) {
                primaryEmail = (String) obj;
            }
        }
        if (soapObject.hasProperty("PhoneNumbers")) {
            Object obj = soapObject.getProperty("PhoneNumbers");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                phoneNumbers = j.toString();
            } else if (obj != null && obj instanceof String) {
                phoneNumbers = (String) obj;
            }
        }
        if (soapObject.hasProperty("ContactPerson")) {
            Object obj = soapObject.getProperty("ContactPerson");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                contactPerson = j.toString();
            } else if (obj != null && obj instanceof String) {
                contactPerson = (String) obj;
            }
        }
        if (soapObject.hasProperty("ContactAddress")) {
            Object obj = soapObject.getProperty("ContactAddress");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                contactAddress = j.toString();
            } else if (obj != null && obj instanceof String) {
                contactAddress = (String) obj;
            }
        }
        if (soapObject.hasProperty("FaxNumbers")) {
            Object obj = soapObject.getProperty("FaxNumbers");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                faxNumbers = j.toString();
            } else if (obj != null && obj instanceof String) {
                faxNumbers = (String) obj;
            }
        }
        if (soapObject.hasProperty("logoImage")) {
            SoapPrimitive j = (SoapPrimitive) soapObject.getProperty("logoImage");
            logoImage = new VectorByte(j);
        }
        if (soapObject.hasProperty("Update")) {
            Object obj = soapObject.getProperty("Update");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                update = Boolean.parseBoolean(j.toString());
            } else if (obj != null && obj instanceof Boolean) {
                update = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("UpdateRecID")) {
            Object obj = soapObject.getProperty("UpdateRecID");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                updateRecID = j.toString();
            } else if (obj != null && obj instanceof String) {
                updateRecID = (String) obj;
            }
        }
    }
    @Override
    public Object getProperty(int arg0) {
        switch (arg0) {
            case 0:
                return businessCategory;
            case 1:
                return services;
            case 2:
                return primaryMobileNo;
            case 3:
                return website;
            case 4:
                return addEmails;
            case 5:
                return addMobileNumber;
            case 6:
                return captchaID;
            case 7:
                return captchaValue;
            case 8:
                return businessTitle;
            case 9:
                return primaryEmail;
            case 10:
                return phoneNumbers;
            case 11:
                return contactPerson;
            case 12:
                return contactAddress;
            case 13:
                return faxNumbers;
            case 14:
                return logoImage.toString();
            case 15:
                return update;
            case 16:
                return updateRecID;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 17;
    }

    @Override
    public void getPropertyInfo(int index, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        switch (index) {
            case 0:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "BusinessCategory";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Services";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "PrimaryMobileNo";
                break;
            case 3:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Website";
                break;
            case 4:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "AddEmails";
                break;
            case 5:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "AddMobileNumber";
                break;
            case 6:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "captchaID";
                break;
            case 7:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "captchaValue";
                break;
            case 8:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "BusinessTitle";
                break;
            case 9:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "PrimaryEmail";
                break;
            case 10:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "PhoneNumbers";
                break;
            case 11:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "ContactPerson";
                break;
            case 12:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "ContactAddress";
                break;
            case 13:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "FaxNumbers";
                break;
            case 14:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "logoImage";
                break;
            case 15:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "Update";
                break;
            case 16:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "UpdateRecID";
                break;
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
    }

}
