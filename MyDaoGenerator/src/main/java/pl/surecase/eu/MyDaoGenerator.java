package pl.surecase.eu;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "lionsclub.com.directoryapp");

        Entity district=schema.addEntity("District");
        district.addIdProperty();
        district.addStringProperty("distId");
        district.addStringProperty("distName");



        Entity club = schema.addEntity("Club");
        club.addIdProperty();
        club.addStringProperty("clubId");
        club.addStringProperty("name");
        club.addIntProperty("memcount");


        Property distIdPropertyForClub=club.addStringProperty("distId").notNull().getProperty();
        ToMany clubsInDistrict=district.addToMany(club, distIdPropertyForClub);
        clubsInDistrict.setName("Clubs");


        Entity member = schema.addEntity("Member");
        member.addIdProperty();
        member.addStringProperty("name");
        member.addStringProperty("profileImage");
        member.addBooleanProperty("online");
        member.addStringProperty("distId");
        member.addStringProperty("email");
        member.addStringProperty("mobile");
        member.addBooleanProperty("canEdit");
        Property mIntProp = member.addLongProperty("mIntNum").getProperty();
        member.addStringProperty("profession");
        member.addStringProperty("spouse");
        member.addStringProperty("aboutMe");
        member.addStringProperty("joinDate");
        member.addStringProperty("city");
        member.addStringProperty("phone");
        member.addStringProperty("altPhone");
        member.addStringProperty("altMobile");
        member.addStringProperty("altEmail");
        member.addStringProperty("residentialAddress");
        member.addStringProperty("businessAddress");
        member.addStringProperty("dob");
        member.addStringProperty("marriageDate");
        member.addStringProperty("profilePicUrl");
        member.addStringProperty("userName");
        member.addStringProperty("password");
        member.addStringProperty("clubName");
        member.addStringProperty("district");
        member.addBooleanProperty("isProfileDownloaded");
        Property memberClubIdProperty=member.addStringProperty("clubId").notNull().getProperty();
        ToMany membersInClub = club.addToMany(member, memberClubIdProperty);
        membersInClub.setName("memberList");


        Entity credentialRequestQueue = schema.addEntity("CredentialRequestQueue");
        credentialRequestQueue.addStringProperty("dateTimeRequested");
        credentialRequestQueue.addBooleanProperty("isProcessed");
        credentialRequestQueue.addStringProperty("dateTimeProcessed");
        credentialRequestQueue.addLongProperty("memInt").notNull();


        new DaoGenerator().generateAll(schema, args[0]);
    }
}
