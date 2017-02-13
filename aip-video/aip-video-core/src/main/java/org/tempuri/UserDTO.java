/**
 * UserDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class UserDTO  implements java.io.Serializable {
    private java.lang.String guid;

    private int version;

    private int status;

    private java.lang.String userTypeGuid;

    private java.lang.String domainGuid;

    private java.lang.String userName;

    private java.lang.String password;

    private java.lang.String trueName;

    private java.lang.String unitGuid;

    private java.lang.String groups;

    private java.lang.String IDCard;

    private java.lang.String ICCard;

    private java.lang.String telephone;

    private java.lang.String mobilePhone;

    private java.lang.String email;

    private java.lang.String MSN;

    private java.lang.String QQ;

    private java.lang.String introduction;

    private java.lang.String otherContactWay;

    private java.lang.String headPortrait;

    private java.util.Calendar createTime;

    private java.util.Calendar loginTime;

    private java.util.Calendar loginOutTime;

    private java.lang.String loginIP;

    private java.util.Calendar lastLoginTime;

    private java.lang.String lastLoginIP;

    private int loginCount;

    private java.lang.String theme;

    private java.lang.String remark;

    private int albumCount;

    private int videoCount;

    private int spaceView;

    private int videoView;

    private int verify;

    private int meetingCount;

    private int subjectCount;

    private int publicClassCount;

    private java.lang.String address;

    private java.lang.String postalCode;

    private java.lang.String userTypeName;

    private java.lang.String unitName;

    private java.lang.String domainName;

    private java.lang.String domainCode;

    private java.lang.String token;

    private java.lang.String categorys;

    public UserDTO() {
    }

    public UserDTO(
           java.lang.String guid,
           int version,
           int status,
           java.lang.String userTypeGuid,
           java.lang.String domainGuid,
           java.lang.String userName,
           java.lang.String password,
           java.lang.String trueName,
           java.lang.String unitGuid,
           java.lang.String groups,
           java.lang.String IDCard,
           java.lang.String ICCard,
           java.lang.String telephone,
           java.lang.String mobilePhone,
           java.lang.String email,
           java.lang.String MSN,
           java.lang.String QQ,
           java.lang.String introduction,
           java.lang.String otherContactWay,
           java.lang.String headPortrait,
           java.util.Calendar createTime,
           java.util.Calendar loginTime,
           java.util.Calendar loginOutTime,
           java.lang.String loginIP,
           java.util.Calendar lastLoginTime,
           java.lang.String lastLoginIP,
           int loginCount,
           java.lang.String theme,
           java.lang.String remark,
           int albumCount,
           int videoCount,
           int spaceView,
           int videoView,
           int verify,
           int meetingCount,
           int subjectCount,
           int publicClassCount,
           java.lang.String address,
           java.lang.String postalCode,
           java.lang.String userTypeName,
           java.lang.String unitName,
           java.lang.String domainName,
           java.lang.String domainCode,
           java.lang.String token,
           java.lang.String categorys) {
           this.guid = guid;
           this.version = version;
           this.status = status;
           this.userTypeGuid = userTypeGuid;
           this.domainGuid = domainGuid;
           this.userName = userName;
           this.password = password;
           this.trueName = trueName;
           this.unitGuid = unitGuid;
           this.groups = groups;
           this.IDCard = IDCard;
           this.ICCard = ICCard;
           this.telephone = telephone;
           this.mobilePhone = mobilePhone;
           this.email = email;
           this.MSN = MSN;
           this.QQ = QQ;
           this.introduction = introduction;
           this.otherContactWay = otherContactWay;
           this.headPortrait = headPortrait;
           this.createTime = createTime;
           this.loginTime = loginTime;
           this.loginOutTime = loginOutTime;
           this.loginIP = loginIP;
           this.lastLoginTime = lastLoginTime;
           this.lastLoginIP = lastLoginIP;
           this.loginCount = loginCount;
           this.theme = theme;
           this.remark = remark;
           this.albumCount = albumCount;
           this.videoCount = videoCount;
           this.spaceView = spaceView;
           this.videoView = videoView;
           this.verify = verify;
           this.meetingCount = meetingCount;
           this.subjectCount = subjectCount;
           this.publicClassCount = publicClassCount;
           this.address = address;
           this.postalCode = postalCode;
           this.userTypeName = userTypeName;
           this.unitName = unitName;
           this.domainName = domainName;
           this.domainCode = domainCode;
           this.token = token;
           this.categorys = categorys;
    }


    /**
     * Gets the guid value for this UserDTO.
     * 
     * @return guid
     */
    public java.lang.String getGuid() {
        return guid;
    }


    /**
     * Sets the guid value for this UserDTO.
     * 
     * @param guid
     */
    public void setGuid(java.lang.String guid) {
        this.guid = guid;
    }


    /**
     * Gets the version value for this UserDTO.
     * 
     * @return version
     */
    public int getVersion() {
        return version;
    }


    /**
     * Sets the version value for this UserDTO.
     * 
     * @param version
     */
    public void setVersion(int version) {
        this.version = version;
    }


    /**
     * Gets the status value for this UserDTO.
     * 
     * @return status
     */
    public int getStatus() {
        return status;
    }


    /**
     * Sets the status value for this UserDTO.
     * 
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }


    /**
     * Gets the userTypeGuid value for this UserDTO.
     * 
     * @return userTypeGuid
     */
    public java.lang.String getUserTypeGuid() {
        return userTypeGuid;
    }


    /**
     * Sets the userTypeGuid value for this UserDTO.
     * 
     * @param userTypeGuid
     */
    public void setUserTypeGuid(java.lang.String userTypeGuid) {
        this.userTypeGuid = userTypeGuid;
    }


    /**
     * Gets the domainGuid value for this UserDTO.
     * 
     * @return domainGuid
     */
    public java.lang.String getDomainGuid() {
        return domainGuid;
    }


    /**
     * Sets the domainGuid value for this UserDTO.
     * 
     * @param domainGuid
     */
    public void setDomainGuid(java.lang.String domainGuid) {
        this.domainGuid = domainGuid;
    }


    /**
     * Gets the userName value for this UserDTO.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this UserDTO.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the password value for this UserDTO.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this UserDTO.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the trueName value for this UserDTO.
     * 
     * @return trueName
     */
    public java.lang.String getTrueName() {
        return trueName;
    }


    /**
     * Sets the trueName value for this UserDTO.
     * 
     * @param trueName
     */
    public void setTrueName(java.lang.String trueName) {
        this.trueName = trueName;
    }


    /**
     * Gets the unitGuid value for this UserDTO.
     * 
     * @return unitGuid
     */
    public java.lang.String getUnitGuid() {
        return unitGuid;
    }


    /**
     * Sets the unitGuid value for this UserDTO.
     * 
     * @param unitGuid
     */
    public void setUnitGuid(java.lang.String unitGuid) {
        this.unitGuid = unitGuid;
    }


    /**
     * Gets the groups value for this UserDTO.
     * 
     * @return groups
     */
    public java.lang.String getGroups() {
        return groups;
    }


    /**
     * Sets the groups value for this UserDTO.
     * 
     * @param groups
     */
    public void setGroups(java.lang.String groups) {
        this.groups = groups;
    }


    /**
     * Gets the IDCard value for this UserDTO.
     * 
     * @return IDCard
     */
    public java.lang.String getIDCard() {
        return IDCard;
    }


    /**
     * Sets the IDCard value for this UserDTO.
     * 
     * @param IDCard
     */
    public void setIDCard(java.lang.String IDCard) {
        this.IDCard = IDCard;
    }


    /**
     * Gets the ICCard value for this UserDTO.
     * 
     * @return ICCard
     */
    public java.lang.String getICCard() {
        return ICCard;
    }


    /**
     * Sets the ICCard value for this UserDTO.
     * 
     * @param ICCard
     */
    public void setICCard(java.lang.String ICCard) {
        this.ICCard = ICCard;
    }


    /**
     * Gets the telephone value for this UserDTO.
     * 
     * @return telephone
     */
    public java.lang.String getTelephone() {
        return telephone;
    }


    /**
     * Sets the telephone value for this UserDTO.
     * 
     * @param telephone
     */
    public void setTelephone(java.lang.String telephone) {
        this.telephone = telephone;
    }


    /**
     * Gets the mobilePhone value for this UserDTO.
     * 
     * @return mobilePhone
     */
    public java.lang.String getMobilePhone() {
        return mobilePhone;
    }


    /**
     * Sets the mobilePhone value for this UserDTO.
     * 
     * @param mobilePhone
     */
    public void setMobilePhone(java.lang.String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }


    /**
     * Gets the email value for this UserDTO.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this UserDTO.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the MSN value for this UserDTO.
     * 
     * @return MSN
     */
    public java.lang.String getMSN() {
        return MSN;
    }


    /**
     * Sets the MSN value for this UserDTO.
     * 
     * @param MSN
     */
    public void setMSN(java.lang.String MSN) {
        this.MSN = MSN;
    }


    /**
     * Gets the QQ value for this UserDTO.
     * 
     * @return QQ
     */
    public java.lang.String getQQ() {
        return QQ;
    }


    /**
     * Sets the QQ value for this UserDTO.
     * 
     * @param QQ
     */
    public void setQQ(java.lang.String QQ) {
        this.QQ = QQ;
    }


    /**
     * Gets the introduction value for this UserDTO.
     * 
     * @return introduction
     */
    public java.lang.String getIntroduction() {
        return introduction;
    }


    /**
     * Sets the introduction value for this UserDTO.
     * 
     * @param introduction
     */
    public void setIntroduction(java.lang.String introduction) {
        this.introduction = introduction;
    }


    /**
     * Gets the otherContactWay value for this UserDTO.
     * 
     * @return otherContactWay
     */
    public java.lang.String getOtherContactWay() {
        return otherContactWay;
    }


    /**
     * Sets the otherContactWay value for this UserDTO.
     * 
     * @param otherContactWay
     */
    public void setOtherContactWay(java.lang.String otherContactWay) {
        this.otherContactWay = otherContactWay;
    }


    /**
     * Gets the headPortrait value for this UserDTO.
     * 
     * @return headPortrait
     */
    public java.lang.String getHeadPortrait() {
        return headPortrait;
    }


    /**
     * Sets the headPortrait value for this UserDTO.
     * 
     * @param headPortrait
     */
    public void setHeadPortrait(java.lang.String headPortrait) {
        this.headPortrait = headPortrait;
    }


    /**
     * Gets the createTime value for this UserDTO.
     * 
     * @return createTime
     */
    public java.util.Calendar getCreateTime() {
        return createTime;
    }


    /**
     * Sets the createTime value for this UserDTO.
     * 
     * @param createTime
     */
    public void setCreateTime(java.util.Calendar createTime) {
        this.createTime = createTime;
    }


    /**
     * Gets the loginTime value for this UserDTO.
     * 
     * @return loginTime
     */
    public java.util.Calendar getLoginTime() {
        return loginTime;
    }


    /**
     * Sets the loginTime value for this UserDTO.
     * 
     * @param loginTime
     */
    public void setLoginTime(java.util.Calendar loginTime) {
        this.loginTime = loginTime;
    }


    /**
     * Gets the loginOutTime value for this UserDTO.
     * 
     * @return loginOutTime
     */
    public java.util.Calendar getLoginOutTime() {
        return loginOutTime;
    }


    /**
     * Sets the loginOutTime value for this UserDTO.
     * 
     * @param loginOutTime
     */
    public void setLoginOutTime(java.util.Calendar loginOutTime) {
        this.loginOutTime = loginOutTime;
    }


    /**
     * Gets the loginIP value for this UserDTO.
     * 
     * @return loginIP
     */
    public java.lang.String getLoginIP() {
        return loginIP;
    }


    /**
     * Sets the loginIP value for this UserDTO.
     * 
     * @param loginIP
     */
    public void setLoginIP(java.lang.String loginIP) {
        this.loginIP = loginIP;
    }


    /**
     * Gets the lastLoginTime value for this UserDTO.
     * 
     * @return lastLoginTime
     */
    public java.util.Calendar getLastLoginTime() {
        return lastLoginTime;
    }


    /**
     * Sets the lastLoginTime value for this UserDTO.
     * 
     * @param lastLoginTime
     */
    public void setLastLoginTime(java.util.Calendar lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }


    /**
     * Gets the lastLoginIP value for this UserDTO.
     * 
     * @return lastLoginIP
     */
    public java.lang.String getLastLoginIP() {
        return lastLoginIP;
    }


    /**
     * Sets the lastLoginIP value for this UserDTO.
     * 
     * @param lastLoginIP
     */
    public void setLastLoginIP(java.lang.String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }


    /**
     * Gets the loginCount value for this UserDTO.
     * 
     * @return loginCount
     */
    public int getLoginCount() {
        return loginCount;
    }


    /**
     * Sets the loginCount value for this UserDTO.
     * 
     * @param loginCount
     */
    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }


    /**
     * Gets the theme value for this UserDTO.
     * 
     * @return theme
     */
    public java.lang.String getTheme() {
        return theme;
    }


    /**
     * Sets the theme value for this UserDTO.
     * 
     * @param theme
     */
    public void setTheme(java.lang.String theme) {
        this.theme = theme;
    }


    /**
     * Gets the remark value for this UserDTO.
     * 
     * @return remark
     */
    public java.lang.String getRemark() {
        return remark;
    }


    /**
     * Sets the remark value for this UserDTO.
     * 
     * @param remark
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }


    /**
     * Gets the albumCount value for this UserDTO.
     * 
     * @return albumCount
     */
    public int getAlbumCount() {
        return albumCount;
    }


    /**
     * Sets the albumCount value for this UserDTO.
     * 
     * @param albumCount
     */
    public void setAlbumCount(int albumCount) {
        this.albumCount = albumCount;
    }


    /**
     * Gets the videoCount value for this UserDTO.
     * 
     * @return videoCount
     */
    public int getVideoCount() {
        return videoCount;
    }


    /**
     * Sets the videoCount value for this UserDTO.
     * 
     * @param videoCount
     */
    public void setVideoCount(int videoCount) {
        this.videoCount = videoCount;
    }


    /**
     * Gets the spaceView value for this UserDTO.
     * 
     * @return spaceView
     */
    public int getSpaceView() {
        return spaceView;
    }


    /**
     * Sets the spaceView value for this UserDTO.
     * 
     * @param spaceView
     */
    public void setSpaceView(int spaceView) {
        this.spaceView = spaceView;
    }


    /**
     * Gets the videoView value for this UserDTO.
     * 
     * @return videoView
     */
    public int getVideoView() {
        return videoView;
    }


    /**
     * Sets the videoView value for this UserDTO.
     * 
     * @param videoView
     */
    public void setVideoView(int videoView) {
        this.videoView = videoView;
    }


    /**
     * Gets the verify value for this UserDTO.
     * 
     * @return verify
     */
    public int getVerify() {
        return verify;
    }


    /**
     * Sets the verify value for this UserDTO.
     * 
     * @param verify
     */
    public void setVerify(int verify) {
        this.verify = verify;
    }


    /**
     * Gets the meetingCount value for this UserDTO.
     * 
     * @return meetingCount
     */
    public int getMeetingCount() {
        return meetingCount;
    }


    /**
     * Sets the meetingCount value for this UserDTO.
     * 
     * @param meetingCount
     */
    public void setMeetingCount(int meetingCount) {
        this.meetingCount = meetingCount;
    }


    /**
     * Gets the subjectCount value for this UserDTO.
     * 
     * @return subjectCount
     */
    public int getSubjectCount() {
        return subjectCount;
    }


    /**
     * Sets the subjectCount value for this UserDTO.
     * 
     * @param subjectCount
     */
    public void setSubjectCount(int subjectCount) {
        this.subjectCount = subjectCount;
    }


    /**
     * Gets the publicClassCount value for this UserDTO.
     * 
     * @return publicClassCount
     */
    public int getPublicClassCount() {
        return publicClassCount;
    }


    /**
     * Sets the publicClassCount value for this UserDTO.
     * 
     * @param publicClassCount
     */
    public void setPublicClassCount(int publicClassCount) {
        this.publicClassCount = publicClassCount;
    }


    /**
     * Gets the address value for this UserDTO.
     * 
     * @return address
     */
    public java.lang.String getAddress() {
        return address;
    }


    /**
     * Sets the address value for this UserDTO.
     * 
     * @param address
     */
    public void setAddress(java.lang.String address) {
        this.address = address;
    }


    /**
     * Gets the postalCode value for this UserDTO.
     * 
     * @return postalCode
     */
    public java.lang.String getPostalCode() {
        return postalCode;
    }


    /**
     * Sets the postalCode value for this UserDTO.
     * 
     * @param postalCode
     */
    public void setPostalCode(java.lang.String postalCode) {
        this.postalCode = postalCode;
    }


    /**
     * Gets the userTypeName value for this UserDTO.
     * 
     * @return userTypeName
     */
    public java.lang.String getUserTypeName() {
        return userTypeName;
    }


    /**
     * Sets the userTypeName value for this UserDTO.
     * 
     * @param userTypeName
     */
    public void setUserTypeName(java.lang.String userTypeName) {
        this.userTypeName = userTypeName;
    }


    /**
     * Gets the unitName value for this UserDTO.
     * 
     * @return unitName
     */
    public java.lang.String getUnitName() {
        return unitName;
    }


    /**
     * Sets the unitName value for this UserDTO.
     * 
     * @param unitName
     */
    public void setUnitName(java.lang.String unitName) {
        this.unitName = unitName;
    }


    /**
     * Gets the domainName value for this UserDTO.
     * 
     * @return domainName
     */
    public java.lang.String getDomainName() {
        return domainName;
    }


    /**
     * Sets the domainName value for this UserDTO.
     * 
     * @param domainName
     */
    public void setDomainName(java.lang.String domainName) {
        this.domainName = domainName;
    }


    /**
     * Gets the domainCode value for this UserDTO.
     * 
     * @return domainCode
     */
    public java.lang.String getDomainCode() {
        return domainCode;
    }


    /**
     * Sets the domainCode value for this UserDTO.
     * 
     * @param domainCode
     */
    public void setDomainCode(java.lang.String domainCode) {
        this.domainCode = domainCode;
    }


    /**
     * Gets the token value for this UserDTO.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this UserDTO.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the categorys value for this UserDTO.
     * 
     * @return categorys
     */
    public java.lang.String getCategorys() {
        return categorys;
    }


    /**
     * Sets the categorys value for this UserDTO.
     * 
     * @param categorys
     */
    public void setCategorys(java.lang.String categorys) {
        this.categorys = categorys;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UserDTO)) return false;
        UserDTO other = (UserDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.guid==null && other.getGuid()==null) || 
             (this.guid!=null &&
              this.guid.equals(other.getGuid()))) &&
            this.version == other.getVersion() &&
            this.status == other.getStatus() &&
            ((this.userTypeGuid==null && other.getUserTypeGuid()==null) || 
             (this.userTypeGuid!=null &&
              this.userTypeGuid.equals(other.getUserTypeGuid()))) &&
            ((this.domainGuid==null && other.getDomainGuid()==null) || 
             (this.domainGuid!=null &&
              this.domainGuid.equals(other.getDomainGuid()))) &&
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.trueName==null && other.getTrueName()==null) || 
             (this.trueName!=null &&
              this.trueName.equals(other.getTrueName()))) &&
            ((this.unitGuid==null && other.getUnitGuid()==null) || 
             (this.unitGuid!=null &&
              this.unitGuid.equals(other.getUnitGuid()))) &&
            ((this.groups==null && other.getGroups()==null) || 
             (this.groups!=null &&
              this.groups.equals(other.getGroups()))) &&
            ((this.IDCard==null && other.getIDCard()==null) || 
             (this.IDCard!=null &&
              this.IDCard.equals(other.getIDCard()))) &&
            ((this.ICCard==null && other.getICCard()==null) || 
             (this.ICCard!=null &&
              this.ICCard.equals(other.getICCard()))) &&
            ((this.telephone==null && other.getTelephone()==null) || 
             (this.telephone!=null &&
              this.telephone.equals(other.getTelephone()))) &&
            ((this.mobilePhone==null && other.getMobilePhone()==null) || 
             (this.mobilePhone!=null &&
              this.mobilePhone.equals(other.getMobilePhone()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.MSN==null && other.getMSN()==null) || 
             (this.MSN!=null &&
              this.MSN.equals(other.getMSN()))) &&
            ((this.QQ==null && other.getQQ()==null) || 
             (this.QQ!=null &&
              this.QQ.equals(other.getQQ()))) &&
            ((this.introduction==null && other.getIntroduction()==null) || 
             (this.introduction!=null &&
              this.introduction.equals(other.getIntroduction()))) &&
            ((this.otherContactWay==null && other.getOtherContactWay()==null) || 
             (this.otherContactWay!=null &&
              this.otherContactWay.equals(other.getOtherContactWay()))) &&
            ((this.headPortrait==null && other.getHeadPortrait()==null) || 
             (this.headPortrait!=null &&
              this.headPortrait.equals(other.getHeadPortrait()))) &&
            ((this.createTime==null && other.getCreateTime()==null) || 
             (this.createTime!=null &&
              this.createTime.equals(other.getCreateTime()))) &&
            ((this.loginTime==null && other.getLoginTime()==null) || 
             (this.loginTime!=null &&
              this.loginTime.equals(other.getLoginTime()))) &&
            ((this.loginOutTime==null && other.getLoginOutTime()==null) || 
             (this.loginOutTime!=null &&
              this.loginOutTime.equals(other.getLoginOutTime()))) &&
            ((this.loginIP==null && other.getLoginIP()==null) || 
             (this.loginIP!=null &&
              this.loginIP.equals(other.getLoginIP()))) &&
            ((this.lastLoginTime==null && other.getLastLoginTime()==null) || 
             (this.lastLoginTime!=null &&
              this.lastLoginTime.equals(other.getLastLoginTime()))) &&
            ((this.lastLoginIP==null && other.getLastLoginIP()==null) || 
             (this.lastLoginIP!=null &&
              this.lastLoginIP.equals(other.getLastLoginIP()))) &&
            this.loginCount == other.getLoginCount() &&
            ((this.theme==null && other.getTheme()==null) || 
             (this.theme!=null &&
              this.theme.equals(other.getTheme()))) &&
            ((this.remark==null && other.getRemark()==null) || 
             (this.remark!=null &&
              this.remark.equals(other.getRemark()))) &&
            this.albumCount == other.getAlbumCount() &&
            this.videoCount == other.getVideoCount() &&
            this.spaceView == other.getSpaceView() &&
            this.videoView == other.getVideoView() &&
            this.verify == other.getVerify() &&
            this.meetingCount == other.getMeetingCount() &&
            this.subjectCount == other.getSubjectCount() &&
            this.publicClassCount == other.getPublicClassCount() &&
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress()))) &&
            ((this.postalCode==null && other.getPostalCode()==null) || 
             (this.postalCode!=null &&
              this.postalCode.equals(other.getPostalCode()))) &&
            ((this.userTypeName==null && other.getUserTypeName()==null) || 
             (this.userTypeName!=null &&
              this.userTypeName.equals(other.getUserTypeName()))) &&
            ((this.unitName==null && other.getUnitName()==null) || 
             (this.unitName!=null &&
              this.unitName.equals(other.getUnitName()))) &&
            ((this.domainName==null && other.getDomainName()==null) || 
             (this.domainName!=null &&
              this.domainName.equals(other.getDomainName()))) &&
            ((this.domainCode==null && other.getDomainCode()==null) || 
             (this.domainCode!=null &&
              this.domainCode.equals(other.getDomainCode()))) &&
            ((this.token==null && other.getToken()==null) || 
             (this.token!=null &&
              this.token.equals(other.getToken()))) &&
            ((this.categorys==null && other.getCategorys()==null) || 
             (this.categorys!=null &&
              this.categorys.equals(other.getCategorys())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getGuid() != null) {
            _hashCode += getGuid().hashCode();
        }
        _hashCode += getVersion();
        _hashCode += getStatus();
        if (getUserTypeGuid() != null) {
            _hashCode += getUserTypeGuid().hashCode();
        }
        if (getDomainGuid() != null) {
            _hashCode += getDomainGuid().hashCode();
        }
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getTrueName() != null) {
            _hashCode += getTrueName().hashCode();
        }
        if (getUnitGuid() != null) {
            _hashCode += getUnitGuid().hashCode();
        }
        if (getGroups() != null) {
            _hashCode += getGroups().hashCode();
        }
        if (getIDCard() != null) {
            _hashCode += getIDCard().hashCode();
        }
        if (getICCard() != null) {
            _hashCode += getICCard().hashCode();
        }
        if (getTelephone() != null) {
            _hashCode += getTelephone().hashCode();
        }
        if (getMobilePhone() != null) {
            _hashCode += getMobilePhone().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getMSN() != null) {
            _hashCode += getMSN().hashCode();
        }
        if (getQQ() != null) {
            _hashCode += getQQ().hashCode();
        }
        if (getIntroduction() != null) {
            _hashCode += getIntroduction().hashCode();
        }
        if (getOtherContactWay() != null) {
            _hashCode += getOtherContactWay().hashCode();
        }
        if (getHeadPortrait() != null) {
            _hashCode += getHeadPortrait().hashCode();
        }
        if (getCreateTime() != null) {
            _hashCode += getCreateTime().hashCode();
        }
        if (getLoginTime() != null) {
            _hashCode += getLoginTime().hashCode();
        }
        if (getLoginOutTime() != null) {
            _hashCode += getLoginOutTime().hashCode();
        }
        if (getLoginIP() != null) {
            _hashCode += getLoginIP().hashCode();
        }
        if (getLastLoginTime() != null) {
            _hashCode += getLastLoginTime().hashCode();
        }
        if (getLastLoginIP() != null) {
            _hashCode += getLastLoginIP().hashCode();
        }
        _hashCode += getLoginCount();
        if (getTheme() != null) {
            _hashCode += getTheme().hashCode();
        }
        if (getRemark() != null) {
            _hashCode += getRemark().hashCode();
        }
        _hashCode += getAlbumCount();
        _hashCode += getVideoCount();
        _hashCode += getSpaceView();
        _hashCode += getVideoView();
        _hashCode += getVerify();
        _hashCode += getMeetingCount();
        _hashCode += getSubjectCount();
        _hashCode += getPublicClassCount();
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        if (getPostalCode() != null) {
            _hashCode += getPostalCode().hashCode();
        }
        if (getUserTypeName() != null) {
            _hashCode += getUserTypeName().hashCode();
        }
        if (getUnitName() != null) {
            _hashCode += getUnitName().hashCode();
        }
        if (getDomainName() != null) {
            _hashCode += getDomainName().hashCode();
        }
        if (getDomainCode() != null) {
            _hashCode += getDomainCode().hashCode();
        }
        if (getToken() != null) {
            _hashCode += getToken().hashCode();
        }
        if (getCategorys() != null) {
            _hashCode += getCategorys().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UserDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "UserDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("guid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Guid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userTypeGuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserTypeGuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("domainGuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DomainGuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trueName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TrueName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unitGuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UnitGuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groups");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Groups"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IDCard");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IDCard"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ICCard");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ICCard"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telephone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Telephone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mobilePhone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MobilePhone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MSN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MSN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("QQ");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "QQ"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("introduction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Introduction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("otherContactWay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "OtherContactWay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("headPortrait");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "HeadPortrait"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CreateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LoginTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginOutTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LoginOutTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginIP");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LoginIP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastLoginTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LastLoginTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastLoginIP");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LastLoginIP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LoginCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("theme");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Theme"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remark");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Remark"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("albumCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AlbumCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("videoCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "VideoCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("spaceView");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SpaceView"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("videoView");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "VideoView"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("verify");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Verify"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("meetingCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MeetingCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subjectCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SubjectCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publicClassCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PublicClassCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postalCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PostalCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userTypeName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserTypeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unitName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UnitName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("domainName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DomainName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("domainCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DomainCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categorys");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Categorys"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
