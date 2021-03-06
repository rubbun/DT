package lionsclub.com.directoryapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.List;

import app.utils.Global;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table MEMBER.
 */
public class MemberDao extends AbstractDao<Member, Long> {

    public static final String TABLENAME = "MEMBER";
    private Query<Member> club_MemberListQuery;
    ;

    public MemberDao(DaoConfig config) {
        super(config);
    }

    public MemberDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'MEMBER' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'NAME' TEXT," + // 1: name
                "'PROFILE_IMAGE' TEXT," + // 2: profileImage
                "'ONLINE' INTEGER," + // 3: online
                "'DIST_ID' TEXT," + // 4: distId
                "'EMAIL' TEXT," + // 5: email
                "'MOBILE' TEXT," + // 6: mobile
                "'CAN_EDIT' INTEGER," + // 7: canEdit
                "'M_INT_NUM' INTEGER," + // 8: mIntNum
                "'PROFESSION' TEXT," + // 9: profession
                "'SPOUSE' TEXT," + // 10: spouse
                "'ABOUT_ME' TEXT," + // 11: aboutMe
                "'JOIN_DATE' TEXT," + // 12: joinDate
                "'CITY' TEXT," + // 13: city
                "'PHONE' TEXT," + // 14: phone
                "'ALT_PHONE' TEXT," + // 15: altPhone
                "'ALT_MOBILE' TEXT," + // 16: altMobile
                "'ALT_EMAIL' TEXT," + // 17: altEmail
                "'RESIDENTIAL_ADDRESS' TEXT," + // 18: residentialAddress
                "'BUSINESS_ADDRESS' TEXT," + // 19: businessAddress
                "'DOB' TEXT," + // 20: dob
                "'MARRIAGE_DATE' TEXT," + // 21: marriageDate
                "'PROFILE_PIC_URL' TEXT," + // 22: profilePicUrl
                "'USER_NAME' TEXT," + // 23: userName
                "'PASSWORD' TEXT," + // 24: password
                "'CLUB_NAME' TEXT," + // 25: clubName
                "'DISTRICT' TEXT," + // 26: district
                "'IS_PROFILE_DOWNLOADED' INTEGER," + // 27: isProfileDownloaded
                "'CLUB_ID' TEXT NOT NULL );"); // 28: clubId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'MEMBER'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Member entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }

        String profileImage = entity.getProfileImage();
        if (profileImage != null) {
            stmt.bindString(3, profileImage);
        }

        Boolean online = entity.getOnline();
        if (online != null) {
            stmt.bindLong(4, online ? 1l: 0l);
        }

        String distId = entity.getDistId();
        if (distId != null) {
            stmt.bindString(5, distId);
        }

        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(6, email);
        }

        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(7, mobile);
        }

        Boolean canEdit = entity.getCanEdit();
        if (canEdit != null) {
            stmt.bindLong(8, canEdit ? 1l: 0l);
        }

        Long mIntNum = entity.getMIntNum();
        if (mIntNum != null) {
            stmt.bindLong(9, mIntNum);
        }

        String profession = entity.getProfession();
        if (profession != null) {
            stmt.bindString(10, profession);
        }

        String spouse = entity.getSpouse();
        if (spouse != null) {
            stmt.bindString(11, spouse);
        }

        String aboutMe = entity.getAboutMe();
        if (aboutMe != null) {
            stmt.bindString(12, aboutMe);
        }

        String joinDate = entity.getJoinDate();
        if (joinDate != null) {
            stmt.bindString(13, joinDate);
        }

        String city = entity.getCity();
        if (city != null) {
            stmt.bindString(14, city);
        }

        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(15, phone);
        }

        String altPhone = entity.getAltPhone();
        if (altPhone != null) {
            stmt.bindString(16, altPhone);
        }

        String altMobile = entity.getAltMobile();
        if (altMobile != null) {
            stmt.bindString(17, altMobile);
        }

        String altEmail = entity.getAltEmail();
        if (altEmail != null) {
            stmt.bindString(18, altEmail);
        }

        String residentialAddress = entity.getResidentialAddress();
        if (residentialAddress != null) {
            stmt.bindString(19, residentialAddress);
        }

        String businessAddress = entity.getBusinessAddress();
        if (businessAddress != null) {
            stmt.bindString(20, businessAddress);
        }

        String dob = entity.getDob();
        if (dob != null) {
            stmt.bindString(21, dob);
        }

        String marriageDate = entity.getMarriageDate();
        if (marriageDate != null) {
            stmt.bindString(22, marriageDate);
        }

        String profilePicUrl = entity.getProfilePicUrl();
        if (profilePicUrl != null) {
            stmt.bindString(23, profilePicUrl);
        }

        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(24, userName);
        }

        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(25, password);
        }

        String clubName = entity.getClubName();
        if (clubName != null) {
            stmt.bindString(26, clubName);
        }

        String district = entity.getDistrict();
        if (district != null) {
            stmt.bindString(27, district);
        }

        Boolean isProfileDownloaded = entity.getIsProfileDownloaded();
        if (isProfileDownloaded != null) {
            stmt.bindLong(28, isProfileDownloaded ? 1l: 0l);
        }
        stmt.bindString(29, entity.getClubId());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    /** @inheritdoc */
    @Override
    public Member readEntity(Cursor cursor, int offset) {
        Member entity = new Member( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // profileImage
                cursor.isNull(offset + 3) ? null : cursor.getShort(offset + 3) != 0, // online
                cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // distId
                cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // email
                cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // mobile
                cursor.isNull(offset + 7) ? null : cursor.getShort(offset + 7) != 0, // canEdit
                cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8), // mIntNum
                cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // profession
                cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // spouse
                cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // aboutMe
                cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // joinDate
                cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // city
                cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // phone
                cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // altPhone
                cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // altMobile
                cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // altEmail
                cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // residentialAddress
                cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // businessAddress
                cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // dob
                cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // marriageDate
                cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // profilePicUrl
                cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // userName
                cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // password
                cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // clubName
                cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // district
                cursor.isNull(offset + 27) ? null : cursor.getShort(offset + 27) != 0, // isProfileDownloaded
                cursor.getString(offset + 28) // clubId
        );
        return entity;
    }

    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Member entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setProfileImage(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setOnline(cursor.isNull(offset + 3) ? null : cursor.getShort(offset + 3) != 0);
        entity.setDistId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setEmail(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setMobile(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCanEdit(cursor.isNull(offset + 7) ? null : cursor.getShort(offset + 7) != 0);
        entity.setMIntNum(cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8));
        entity.setProfession(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setSpouse(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setAboutMe(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setJoinDate(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setCity(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setPhone(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setAltPhone(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setAltMobile(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setAltEmail(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setResidentialAddress(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setBusinessAddress(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setDob(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setMarriageDate(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setProfilePicUrl(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setUserName(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setPassword(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setClubName(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setDistrict(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setIsProfileDownloaded(cursor.isNull(offset + 27) ? null : cursor.getShort(offset + 27) != 0);
        entity.setClubId(cursor.getString(offset + 28));
    }

    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Member entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    /** @inheritdoc */
    @Override
    public Long getKey(Member entity) {
        if (entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }

    /** Internal query to resolve the "memberList" to-many relationship of Club. */
    public List<Member> _queryClub_MemberList(String clubId) {
        synchronized (this) {
            if (club_MemberListQuery == null) {
                QueryBuilder<Member> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ClubId.eq(null));
                club_MemberListQuery = queryBuilder.build();
            }
        }
        Query<Member> query = club_MemberListQuery.forCurrentThread();
        query.setParameter(0, clubId);
        return query.list();
    }
    
/**
     * Properties of entity Member.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property ProfileImage = new Property(2, String.class, "profileImage", false, "PROFILE_IMAGE");
        public final static Property Online = new Property(3, Boolean.class, "online", false, "ONLINE");
        public final static Property DistId = new Property(4, String.class, "distId", false, "DIST_ID");
        public final static Property Email = new Property(5, String.class, "email", false, "EMAIL");
        public final static Property Mobile = new Property(6, String.class, "mobile", false, "MOBILE");
        public final static Property CanEdit = new Property(7, Boolean.class, "canEdit", false, "CAN_EDIT");
    public final static Property MIntNum = new Property(8, Long.class, "mIntNum", false, "M_INT_NUM");
        public final static Property Profession = new Property(9, String.class, "profession", false, "PROFESSION");
        public final static Property Spouse = new Property(10, String.class, "spouse", false, "SPOUSE");
        public final static Property AboutMe = new Property(11, String.class, "aboutMe", false, "ABOUT_ME");
        public final static Property JoinDate = new Property(12, String.class, "joinDate", false, "JOIN_DATE");
        public final static Property City = new Property(13, String.class, "city", false, "CITY");
        public final static Property Phone = new Property(14, String.class, "phone", false, "PHONE");
        public final static Property AltPhone = new Property(15, String.class, "altPhone", false, "ALT_PHONE");
        public final static Property AltMobile = new Property(16, String.class, "altMobile", false, "ALT_MOBILE");
        public final static Property AltEmail = new Property(17, String.class, "altEmail", false, "ALT_EMAIL");
        public final static Property ResidentialAddress = new Property(18, String.class, "residentialAddress", false, "RESIDENTIAL_ADDRESS");
        public final static Property BusinessAddress = new Property(19, String.class, "businessAddress", false, "BUSINESS_ADDRESS");
        public final static Property Dob = new Property(20, String.class, "dob", false, "DOB");
        public final static Property MarriageDate = new Property(21, String.class, "marriageDate", false, "MARRIAGE_DATE");
        public final static Property ProfilePicUrl = new Property(22, String.class, "profilePicUrl", false, "PROFILE_PIC_URL");
        public final static Property UserName = new Property(23, String.class, "userName", false, "USER_NAME");
        public final static Property Password = new Property(24, String.class, "password", false, "PASSWORD");
        public final static Property ClubName = new Property(25, String.class, "clubName", false, "CLUB_NAME");
        public final static Property District = new Property(26, String.class, "district", false, "DISTRICT");
        public final static Property IsProfileDownloaded = new Property(27, Boolean.class, "isProfileDownloaded", false, "IS_PROFILE_DOWNLOADED");
        public final static Property ClubId = new Property(28, String.class, "clubId", false, "CLUB_ID");
    }

}
