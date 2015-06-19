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

import app.utils.Global;

public class MemberProfile implements KvmSerializable {

    public String error;
    public boolean updatingProfile;
    public String name;
    public String memberNumber;
    public String profession;
    public String spouse;
    public String aboutMe;
    public String joinDate;
    public String city;
    public String phone;
    public String alternatePhone;
    public String mobile;
    public String alternateMobile;
    public String email;
    public String alternateEmail;
    public String residentialAddress;
    public String businessAddress;
    public String captchaId;
    public String captchaValue;
    public String dateOfBirth;
    public String marriageDate;
    public String photo;
    public String userName;
    public String password;
    public String confirmPassw;
    public String clubName;
    public String district;
    public String clubID;
    public String districtID;


    public MemberProfile() {
    }

    public MemberProfile(SoapObject soapObject) {
        if (soapObject == null)
            return;
        if (soapObject.hasProperty("error")) {
            Object obj = soapObject.getProperty("error");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                error = j.toString();
            } else if (obj != null && obj instanceof String) {
                error = (String) obj;
            }
        }
        if (soapObject.hasProperty("UpdatingProfile")) {
            Object obj = soapObject.getProperty("UpdatingProfile");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                updatingProfile = Boolean.parseBoolean(j.toString());
            } else if (obj != null && obj instanceof Boolean) {
                updatingProfile = (Boolean) obj;
            }
        }
        if (soapObject.hasProperty("Name")) {
            Object obj = soapObject.getProperty("Name");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                name = j.toString();
            } else if (obj != null && obj instanceof String) {
                name = (String) obj;
            }
            /*name = name+"~"+ Global.val;*/
        }
        if (soapObject.hasProperty("MemberNumber")) {
            Object obj = soapObject.getProperty("MemberNumber");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                memberNumber = j.toString();
            } else if (obj != null && obj instanceof String) {
                memberNumber = (String) obj;
            }
        }
        if (soapObject.hasProperty("Profession")) {
            Object obj = soapObject.getProperty("Profession");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                profession = j.toString();
            } else if (obj != null && obj instanceof String) {
                profession = (String) obj;
            }
        }
        if (soapObject.hasProperty("Spouse")) {
            Object obj = soapObject.getProperty("Spouse");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                spouse = j.toString();
            } else if (obj != null && obj instanceof String) {
                spouse = (String) obj;
            }
        }
        if (soapObject.hasProperty("AboutMe")) {
            Object obj = soapObject.getProperty("AboutMe");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                aboutMe = j.toString();
            } else if (obj != null && obj instanceof String) {
                aboutMe = (String) obj;
            }
        }
        if (soapObject.hasProperty("joinDate")) {
            Object obj = soapObject.getProperty("joinDate");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                joinDate = j.toString();
            } else if (obj != null && obj instanceof String) {
                joinDate = (String) obj;
            }
        }
        if (soapObject.hasProperty("City")) {
            Object obj = soapObject.getProperty("City");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                city = j.toString();
            } else if (obj != null && obj instanceof String) {
                city = (String) obj;
            }
        }
        if (soapObject.hasProperty("Phone")) {
            Object obj = soapObject.getProperty("Phone");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                phone = j.toString();
            } else if (obj != null && obj instanceof String) {
                phone = (String) obj;
            }
        }
        if (soapObject.hasProperty("AlternatePhone")) {
            Object obj = soapObject.getProperty("AlternatePhone");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                alternatePhone = j.toString();
            } else if (obj != null && obj instanceof String) {
                alternatePhone = (String) obj;
            }
        }
        if (soapObject.hasProperty("Mobile")) {
            Object obj = soapObject.getProperty("Mobile");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                mobile = j.toString();
            } else if (obj != null && obj instanceof String) {
                mobile = (String) obj;
            }
        }
        if (soapObject.hasProperty("AlternateMobile")) {
            Object obj = soapObject.getProperty("AlternateMobile");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                alternateMobile = j.toString();
            } else if (obj != null && obj instanceof String) {
                alternateMobile = (String) obj;
            }
        }
        if (soapObject.hasProperty("Email")) {
            Object obj = soapObject.getProperty("Email");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                email = j.toString();
            } else if (obj != null && obj instanceof String) {
                email = (String) obj;
            }
        }
        if (soapObject.hasProperty("AlternateEmail")) {
            Object obj = soapObject.getProperty("AlternateEmail");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                alternateEmail = j.toString();
            } else if (obj != null && obj instanceof String) {
                alternateEmail = (String) obj;
            }
        }
        if (soapObject.hasProperty("ResidentialAddress")) {
            Object obj = soapObject.getProperty("ResidentialAddress");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                residentialAddress = j.toString();
            } else if (obj != null && obj instanceof String) {
                residentialAddress = (String) obj;
            }
        }
        if (soapObject.hasProperty("BusinessAddress")) {
            Object obj = soapObject.getProperty("BusinessAddress");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                businessAddress = j.toString();
            } else if (obj != null && obj instanceof String) {
                businessAddress = (String) obj;
            }
        }
        if (soapObject.hasProperty("captchaId")) {
            Object obj = soapObject.getProperty("captchaId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                captchaId = j.toString();
            } else if (obj != null && obj instanceof String) {
                captchaId = (String) obj;
            }
        }
        if (soapObject.hasProperty("CaptchaValue")) {
            Object obj = soapObject.getProperty("CaptchaValue");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                captchaValue = j.toString();
            } else if (obj != null && obj instanceof String) {
                captchaValue = (String) obj;
            }
        }
        if (soapObject.hasProperty("DateOfBirth")) {
            Object obj = soapObject.getProperty("DateOfBirth");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                dateOfBirth = j.toString();
            } else if (obj != null && obj instanceof String) {
                dateOfBirth = (String) obj;
            }
        }
        if (soapObject.hasProperty("MarriageDate")) {
            Object obj = soapObject.getProperty("MarriageDate");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                marriageDate = j.toString();
            } else if (obj != null && obj instanceof String) {
                marriageDate = (String) obj;
            }
        }
        if (soapObject.hasProperty("Photo")) {
            Object obj = soapObject.getProperty("Photo");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                photo = j.toString();
            } else if (obj != null && obj instanceof String) {
                photo = (String) obj;
            }
        }
        if (soapObject.hasProperty("UserName")) {
            Object obj = soapObject.getProperty("UserName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                userName = j.toString();
            } else if (obj != null && obj instanceof String) {
                userName = (String) obj;
            }
        }
        if (soapObject.hasProperty("password")) {
            Object obj = soapObject.getProperty("password");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                password = j.toString();
            } else if (obj != null && obj instanceof String) {
                password = (String) obj;
            }
        }
        if (soapObject.hasProperty("confirmPassw")) {
            Object obj = soapObject.getProperty("confirmPassw");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                confirmPassw = j.toString();
            } else if (obj != null && obj instanceof String) {
                confirmPassw = (String) obj;
            }
        }
        if (soapObject.hasProperty("ClubName")) {
            Object obj = soapObject.getProperty("ClubName");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                clubName = j.toString();
            } else if (obj != null && obj instanceof String) {
                clubName = (String) obj;
            }
        }
        if (soapObject.hasProperty("District")) {
            Object obj = soapObject.getProperty("District");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                district = j.toString();
            } else if (obj != null && obj instanceof String) {
                district = (String) obj;
            }
        }
        if (soapObject.hasProperty("ClubID")) {
            Object obj = soapObject.getProperty("ClubID");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                clubID = j.toString();
            } else if (obj != null && obj instanceof String) {
                clubID = (String) obj;
            }
        }
        if (soapObject.hasProperty("DistrictID")) {
            Object obj = soapObject.getProperty("DistrictID");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)) {
                SoapPrimitive j = (SoapPrimitive) obj;
                districtID = j.toString();
            } else if (obj != null && obj instanceof String) {
                districtID = (String) obj;
            }
        }

    }
    @Override
    public Object getProperty(int arg0) {
        switch (arg0) {
            case 0:
                return error;
            case 1:
                return updatingProfile;
            case 2:
                return name;
            case 3:
                return memberNumber;
            case 4:
                return profession;
            case 5:
                return spouse;
            case 6:
                return aboutMe;
            case 7:
                return joinDate;
            case 8:
                return city;
            case 9:
                return phone;
            case 10:
                return alternatePhone;
            case 11:
                return mobile;
            case 12:
                return alternateMobile;
            case 13:
                return email;
            case 14:
                return alternateEmail;
            case 15:
                return residentialAddress;
            case 16:
                return businessAddress;
            case 17:
                return captchaId;
            case 18:
                return captchaValue;
            case 19:
                return dateOfBirth;
            case 20:
                return marriageDate;
            case 21:
                return photo;
            case 22:
                return userName;
            case 23:
                return password;
            case 24:
                return confirmPassw;
            case 25:
                return clubName;
            case 26:
                return district;
            case 27:
                return clubID;
            case 28:
                return districtID;

        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 29;
    }

    @Override
    public void getPropertyInfo(int index, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        switch (index) {
            case 0:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "error";
                break;
            case 1:
                info.type = PropertyInfo.BOOLEAN_CLASS;
                info.name = "UpdatingProfile";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Name";
                break;
            case 3:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "MemberNumber";
                break;
            case 4:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Profession";
                break;
            case 5:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Spouse";
                break;
            case 6:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "AboutMe";
                break;
            case 7:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "joinDate";
                break;
            case 8:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "City";
                break;
            case 9:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Phone";
                break;
            case 10:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "AlternatePhone";
                break;
            case 11:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Mobile";
                break;
            case 12:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "AlternateMobile";
                break;
            case 13:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Email";
                break;
            case 14:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "AlternateEmail";
                break;
            case 15:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "ResidentialAddress";
                break;
            case 16:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "BusinessAddress";
                break;
            case 17:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "captchaId";
                break;
            case 18:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "CaptchaValue";
                break;
            case 19:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "DateOfBirth";
                break;
            case 20:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "MarriageDate";
                break;
            case 21:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Photo";
                break;
            case 22:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "UserName";
                break;
            case 23:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "password";
                break;
            case 24:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "confirmPassw";
                break;
            case 25:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "ClubName";
                break;
            case 26:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "District";
                break;
            case 27:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "ClubID";
                break;
            case 28:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "DistrictID";
                break;

        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
        switch (arg0) {
            case 0:
                error = (String) arg1;

                break;
            case 1:
                updatingProfile = (boolean) arg1;

                break;
            case 2:
                name = (String) arg1;

                break;
            case 3:
                memberNumber = (String) arg1;

                break;
            case 4:
                profession = (String) arg1;

                break;
            case 5:
                spouse = (String) arg1;

                break;
            case 6:
                aboutMe = (String) arg1;

                break;
            case 7:
                joinDate = (String) arg1;

                break;
            case 8:
                city = (String) arg1;

                break;
            case 9:
                phone = (String) arg1;

                break;
            case 10:
                alternatePhone = (String) arg1;

                break;
            case 11:
                mobile = (String) arg1;

                break;
            case 12:
                alternateMobile = (String) arg1;

                break;
            case 13:
                email = (String) arg1;

                break;
            case 14:
                alternateEmail = (String) arg1;

                break;
            case 15:
                residentialAddress = (String) arg1;

                break;
            case 16:
                businessAddress = (String) arg1;

                break;
            case 17:
                captchaId = (String) arg1;

                break;
            case 18:
                captchaValue = (String) arg1;

                break;
            case 19:
                dateOfBirth = (String) arg1;

                break;
            case 20:
                marriageDate = (String) arg1;

                break;
            case 21:
                photo = (String) arg1;

                break;
            case 22:
                userName = (String) arg1;

                break;
            case 23:
                password = (String) arg1;

                break;
            case 24:
                confirmPassw = (String) arg1;

                break;
            case 25:
                clubName = (String) arg1;

                break;
            case 26:
                district = (String) arg1;

                break;
            case 27:
                clubID = (String) arg1;

                break;
            case 28:
                districtID = (String) arg1;

                break;

        }
    }

}
