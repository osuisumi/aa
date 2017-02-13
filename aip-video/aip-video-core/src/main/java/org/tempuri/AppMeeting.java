/**
 * AppMeeting.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class AppMeeting  implements java.io.Serializable {
    private java.lang.String guid;

    private int status;

    private java.lang.String userGuid;

    private java.lang.String userName;

    private java.lang.String userTrueName;

    private java.lang.String userTypeGuid;

    private java.lang.String userTypeName;

    private java.lang.String userDomainGuid;

    private java.lang.String userDomainName;

    private java.lang.String name;

    private java.lang.String password;

    private java.lang.String masterPwd;

    private java.lang.String coverUrl;

    private java.lang.String adUrl;

    private int personCount;

    private int guestEnable;

    private int videoCount;

    private int videoView;

    private int period;

    private int commentCount;

    private int collectCount;

    private java.lang.String description;

    private java.util.Calendar beginTime;

    private java.util.Calendar endTime;

    private java.lang.String compere;

    private java.util.Calendar cutoffTime;

    private java.util.Calendar createTime;

    private java.lang.String meetingServerGuid;

    private java.lang.String meetingServerName;

    public AppMeeting() {
    }

    public AppMeeting(
           java.lang.String guid,
           int status,
           java.lang.String userGuid,
           java.lang.String userName,
           java.lang.String userTrueName,
           java.lang.String userTypeGuid,
           java.lang.String userTypeName,
           java.lang.String userDomainGuid,
           java.lang.String userDomainName,
           java.lang.String name,
           java.lang.String password,
           java.lang.String masterPwd,
           java.lang.String coverUrl,
           java.lang.String adUrl,
           int personCount,
           int guestEnable,
           int videoCount,
           int videoView,
           int period,
           int commentCount,
           int collectCount,
           java.lang.String description,
           java.util.Calendar beginTime,
           java.util.Calendar endTime,
           java.lang.String compere,
           java.util.Calendar cutoffTime,
           java.util.Calendar createTime,
           java.lang.String meetingServerGuid,
           java.lang.String meetingServerName) {
           this.guid = guid;
           this.status = status;
           this.userGuid = userGuid;
           this.userName = userName;
           this.userTrueName = userTrueName;
           this.userTypeGuid = userTypeGuid;
           this.userTypeName = userTypeName;
           this.userDomainGuid = userDomainGuid;
           this.userDomainName = userDomainName;
           this.name = name;
           this.password = password;
           this.masterPwd = masterPwd;
           this.coverUrl = coverUrl;
           this.adUrl = adUrl;
           this.personCount = personCount;
           this.guestEnable = guestEnable;
           this.videoCount = videoCount;
           this.videoView = videoView;
           this.period = period;
           this.commentCount = commentCount;
           this.collectCount = collectCount;
           this.description = description;
           this.beginTime = beginTime;
           this.endTime = endTime;
           this.compere = compere;
           this.cutoffTime = cutoffTime;
           this.createTime = createTime;
           this.meetingServerGuid = meetingServerGuid;
           this.meetingServerName = meetingServerName;
    }


    /**
     * Gets the guid value for this AppMeeting.
     * 
     * @return guid
     */
    public java.lang.String getGuid() {
        return guid;
    }


    /**
     * Sets the guid value for this AppMeeting.
     * 
     * @param guid
     */
    public void setGuid(java.lang.String guid) {
        this.guid = guid;
    }


    /**
     * Gets the status value for this AppMeeting.
     * 
     * @return status
     */
    public int getStatus() {
        return status;
    }


    /**
     * Sets the status value for this AppMeeting.
     * 
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }


    /**
     * Gets the userGuid value for this AppMeeting.
     * 
     * @return userGuid
     */
    public java.lang.String getUserGuid() {
        return userGuid;
    }


    /**
     * Sets the userGuid value for this AppMeeting.
     * 
     * @param userGuid
     */
    public void setUserGuid(java.lang.String userGuid) {
        this.userGuid = userGuid;
    }


    /**
     * Gets the userName value for this AppMeeting.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this AppMeeting.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the userTrueName value for this AppMeeting.
     * 
     * @return userTrueName
     */
    public java.lang.String getUserTrueName() {
        return userTrueName;
    }


    /**
     * Sets the userTrueName value for this AppMeeting.
     * 
     * @param userTrueName
     */
    public void setUserTrueName(java.lang.String userTrueName) {
        this.userTrueName = userTrueName;
    }


    /**
     * Gets the userTypeGuid value for this AppMeeting.
     * 
     * @return userTypeGuid
     */
    public java.lang.String getUserTypeGuid() {
        return userTypeGuid;
    }


    /**
     * Sets the userTypeGuid value for this AppMeeting.
     * 
     * @param userTypeGuid
     */
    public void setUserTypeGuid(java.lang.String userTypeGuid) {
        this.userTypeGuid = userTypeGuid;
    }


    /**
     * Gets the userTypeName value for this AppMeeting.
     * 
     * @return userTypeName
     */
    public java.lang.String getUserTypeName() {
        return userTypeName;
    }


    /**
     * Sets the userTypeName value for this AppMeeting.
     * 
     * @param userTypeName
     */
    public void setUserTypeName(java.lang.String userTypeName) {
        this.userTypeName = userTypeName;
    }


    /**
     * Gets the userDomainGuid value for this AppMeeting.
     * 
     * @return userDomainGuid
     */
    public java.lang.String getUserDomainGuid() {
        return userDomainGuid;
    }


    /**
     * Sets the userDomainGuid value for this AppMeeting.
     * 
     * @param userDomainGuid
     */
    public void setUserDomainGuid(java.lang.String userDomainGuid) {
        this.userDomainGuid = userDomainGuid;
    }


    /**
     * Gets the userDomainName value for this AppMeeting.
     * 
     * @return userDomainName
     */
    public java.lang.String getUserDomainName() {
        return userDomainName;
    }


    /**
     * Sets the userDomainName value for this AppMeeting.
     * 
     * @param userDomainName
     */
    public void setUserDomainName(java.lang.String userDomainName) {
        this.userDomainName = userDomainName;
    }


    /**
     * Gets the name value for this AppMeeting.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this AppMeeting.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the password value for this AppMeeting.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this AppMeeting.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the masterPwd value for this AppMeeting.
     * 
     * @return masterPwd
     */
    public java.lang.String getMasterPwd() {
        return masterPwd;
    }


    /**
     * Sets the masterPwd value for this AppMeeting.
     * 
     * @param masterPwd
     */
    public void setMasterPwd(java.lang.String masterPwd) {
        this.masterPwd = masterPwd;
    }


    /**
     * Gets the coverUrl value for this AppMeeting.
     * 
     * @return coverUrl
     */
    public java.lang.String getCoverUrl() {
        return coverUrl;
    }


    /**
     * Sets the coverUrl value for this AppMeeting.
     * 
     * @param coverUrl
     */
    public void setCoverUrl(java.lang.String coverUrl) {
        this.coverUrl = coverUrl;
    }


    /**
     * Gets the adUrl value for this AppMeeting.
     * 
     * @return adUrl
     */
    public java.lang.String getAdUrl() {
        return adUrl;
    }


    /**
     * Sets the adUrl value for this AppMeeting.
     * 
     * @param adUrl
     */
    public void setAdUrl(java.lang.String adUrl) {
        this.adUrl = adUrl;
    }


    /**
     * Gets the personCount value for this AppMeeting.
     * 
     * @return personCount
     */
    public int getPersonCount() {
        return personCount;
    }


    /**
     * Sets the personCount value for this AppMeeting.
     * 
     * @param personCount
     */
    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }


    /**
     * Gets the guestEnable value for this AppMeeting.
     * 
     * @return guestEnable
     */
    public int getGuestEnable() {
        return guestEnable;
    }


    /**
     * Sets the guestEnable value for this AppMeeting.
     * 
     * @param guestEnable
     */
    public void setGuestEnable(int guestEnable) {
        this.guestEnable = guestEnable;
    }


    /**
     * Gets the videoCount value for this AppMeeting.
     * 
     * @return videoCount
     */
    public int getVideoCount() {
        return videoCount;
    }


    /**
     * Sets the videoCount value for this AppMeeting.
     * 
     * @param videoCount
     */
    public void setVideoCount(int videoCount) {
        this.videoCount = videoCount;
    }


    /**
     * Gets the videoView value for this AppMeeting.
     * 
     * @return videoView
     */
    public int getVideoView() {
        return videoView;
    }


    /**
     * Sets the videoView value for this AppMeeting.
     * 
     * @param videoView
     */
    public void setVideoView(int videoView) {
        this.videoView = videoView;
    }


    /**
     * Gets the period value for this AppMeeting.
     * 
     * @return period
     */
    public int getPeriod() {
        return period;
    }


    /**
     * Sets the period value for this AppMeeting.
     * 
     * @param period
     */
    public void setPeriod(int period) {
        this.period = period;
    }


    /**
     * Gets the commentCount value for this AppMeeting.
     * 
     * @return commentCount
     */
    public int getCommentCount() {
        return commentCount;
    }


    /**
     * Sets the commentCount value for this AppMeeting.
     * 
     * @param commentCount
     */
    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }


    /**
     * Gets the collectCount value for this AppMeeting.
     * 
     * @return collectCount
     */
    public int getCollectCount() {
        return collectCount;
    }


    /**
     * Sets the collectCount value for this AppMeeting.
     * 
     * @param collectCount
     */
    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }


    /**
     * Gets the description value for this AppMeeting.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this AppMeeting.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the beginTime value for this AppMeeting.
     * 
     * @return beginTime
     */
    public java.util.Calendar getBeginTime() {
        return beginTime;
    }


    /**
     * Sets the beginTime value for this AppMeeting.
     * 
     * @param beginTime
     */
    public void setBeginTime(java.util.Calendar beginTime) {
        this.beginTime = beginTime;
    }


    /**
     * Gets the endTime value for this AppMeeting.
     * 
     * @return endTime
     */
    public java.util.Calendar getEndTime() {
        return endTime;
    }


    /**
     * Sets the endTime value for this AppMeeting.
     * 
     * @param endTime
     */
    public void setEndTime(java.util.Calendar endTime) {
        this.endTime = endTime;
    }


    /**
     * Gets the compere value for this AppMeeting.
     * 
     * @return compere
     */
    public java.lang.String getCompere() {
        return compere;
    }


    /**
     * Sets the compere value for this AppMeeting.
     * 
     * @param compere
     */
    public void setCompere(java.lang.String compere) {
        this.compere = compere;
    }


    /**
     * Gets the cutoffTime value for this AppMeeting.
     * 
     * @return cutoffTime
     */
    public java.util.Calendar getCutoffTime() {
        return cutoffTime;
    }


    /**
     * Sets the cutoffTime value for this AppMeeting.
     * 
     * @param cutoffTime
     */
    public void setCutoffTime(java.util.Calendar cutoffTime) {
        this.cutoffTime = cutoffTime;
    }


    /**
     * Gets the createTime value for this AppMeeting.
     * 
     * @return createTime
     */
    public java.util.Calendar getCreateTime() {
        return createTime;
    }


    /**
     * Sets the createTime value for this AppMeeting.
     * 
     * @param createTime
     */
    public void setCreateTime(java.util.Calendar createTime) {
        this.createTime = createTime;
    }


    /**
     * Gets the meetingServerGuid value for this AppMeeting.
     * 
     * @return meetingServerGuid
     */
    public java.lang.String getMeetingServerGuid() {
        return meetingServerGuid;
    }


    /**
     * Sets the meetingServerGuid value for this AppMeeting.
     * 
     * @param meetingServerGuid
     */
    public void setMeetingServerGuid(java.lang.String meetingServerGuid) {
        this.meetingServerGuid = meetingServerGuid;
    }


    /**
     * Gets the meetingServerName value for this AppMeeting.
     * 
     * @return meetingServerName
     */
    public java.lang.String getMeetingServerName() {
        return meetingServerName;
    }


    /**
     * Sets the meetingServerName value for this AppMeeting.
     * 
     * @param meetingServerName
     */
    public void setMeetingServerName(java.lang.String meetingServerName) {
        this.meetingServerName = meetingServerName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AppMeeting)) return false;
        AppMeeting other = (AppMeeting) obj;
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
            this.status == other.getStatus() &&
            ((this.userGuid==null && other.getUserGuid()==null) || 
             (this.userGuid!=null &&
              this.userGuid.equals(other.getUserGuid()))) &&
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName()))) &&
            ((this.userTrueName==null && other.getUserTrueName()==null) || 
             (this.userTrueName!=null &&
              this.userTrueName.equals(other.getUserTrueName()))) &&
            ((this.userTypeGuid==null && other.getUserTypeGuid()==null) || 
             (this.userTypeGuid!=null &&
              this.userTypeGuid.equals(other.getUserTypeGuid()))) &&
            ((this.userTypeName==null && other.getUserTypeName()==null) || 
             (this.userTypeName!=null &&
              this.userTypeName.equals(other.getUserTypeName()))) &&
            ((this.userDomainGuid==null && other.getUserDomainGuid()==null) || 
             (this.userDomainGuid!=null &&
              this.userDomainGuid.equals(other.getUserDomainGuid()))) &&
            ((this.userDomainName==null && other.getUserDomainName()==null) || 
             (this.userDomainName!=null &&
              this.userDomainName.equals(other.getUserDomainName()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.masterPwd==null && other.getMasterPwd()==null) || 
             (this.masterPwd!=null &&
              this.masterPwd.equals(other.getMasterPwd()))) &&
            ((this.coverUrl==null && other.getCoverUrl()==null) || 
             (this.coverUrl!=null &&
              this.coverUrl.equals(other.getCoverUrl()))) &&
            ((this.adUrl==null && other.getAdUrl()==null) || 
             (this.adUrl!=null &&
              this.adUrl.equals(other.getAdUrl()))) &&
            this.personCount == other.getPersonCount() &&
            this.guestEnable == other.getGuestEnable() &&
            this.videoCount == other.getVideoCount() &&
            this.videoView == other.getVideoView() &&
            this.period == other.getPeriod() &&
            this.commentCount == other.getCommentCount() &&
            this.collectCount == other.getCollectCount() &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.beginTime==null && other.getBeginTime()==null) || 
             (this.beginTime!=null &&
              this.beginTime.equals(other.getBeginTime()))) &&
            ((this.endTime==null && other.getEndTime()==null) || 
             (this.endTime!=null &&
              this.endTime.equals(other.getEndTime()))) &&
            ((this.compere==null && other.getCompere()==null) || 
             (this.compere!=null &&
              this.compere.equals(other.getCompere()))) &&
            ((this.cutoffTime==null && other.getCutoffTime()==null) || 
             (this.cutoffTime!=null &&
              this.cutoffTime.equals(other.getCutoffTime()))) &&
            ((this.createTime==null && other.getCreateTime()==null) || 
             (this.createTime!=null &&
              this.createTime.equals(other.getCreateTime()))) &&
            ((this.meetingServerGuid==null && other.getMeetingServerGuid()==null) || 
             (this.meetingServerGuid!=null &&
              this.meetingServerGuid.equals(other.getMeetingServerGuid()))) &&
            ((this.meetingServerName==null && other.getMeetingServerName()==null) || 
             (this.meetingServerName!=null &&
              this.meetingServerName.equals(other.getMeetingServerName())));
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
        _hashCode += getStatus();
        if (getUserGuid() != null) {
            _hashCode += getUserGuid().hashCode();
        }
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        if (getUserTrueName() != null) {
            _hashCode += getUserTrueName().hashCode();
        }
        if (getUserTypeGuid() != null) {
            _hashCode += getUserTypeGuid().hashCode();
        }
        if (getUserTypeName() != null) {
            _hashCode += getUserTypeName().hashCode();
        }
        if (getUserDomainGuid() != null) {
            _hashCode += getUserDomainGuid().hashCode();
        }
        if (getUserDomainName() != null) {
            _hashCode += getUserDomainName().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getMasterPwd() != null) {
            _hashCode += getMasterPwd().hashCode();
        }
        if (getCoverUrl() != null) {
            _hashCode += getCoverUrl().hashCode();
        }
        if (getAdUrl() != null) {
            _hashCode += getAdUrl().hashCode();
        }
        _hashCode += getPersonCount();
        _hashCode += getGuestEnable();
        _hashCode += getVideoCount();
        _hashCode += getVideoView();
        _hashCode += getPeriod();
        _hashCode += getCommentCount();
        _hashCode += getCollectCount();
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getBeginTime() != null) {
            _hashCode += getBeginTime().hashCode();
        }
        if (getEndTime() != null) {
            _hashCode += getEndTime().hashCode();
        }
        if (getCompere() != null) {
            _hashCode += getCompere().hashCode();
        }
        if (getCutoffTime() != null) {
            _hashCode += getCutoffTime().hashCode();
        }
        if (getCreateTime() != null) {
            _hashCode += getCreateTime().hashCode();
        }
        if (getMeetingServerGuid() != null) {
            _hashCode += getMeetingServerGuid().hashCode();
        }
        if (getMeetingServerName() != null) {
            _hashCode += getMeetingServerName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AppMeeting.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "AppMeeting"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("guid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Guid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userGuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserGuid"));
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
        elemField.setFieldName("userTrueName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserTrueName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("userTypeName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserTypeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userDomainGuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserDomainGuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userDomainName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserDomainName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Name"));
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
        elemField.setFieldName("masterPwd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MasterPwd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coverUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CoverUrl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AdUrl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("personCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PersonCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("guestEnable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GuestEnable"));
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
        elemField.setFieldName("videoView");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "VideoView"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("period");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Period"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commentCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CommentCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("collectCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CollectCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BeginTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "EndTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("compere");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Compere"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cutoffTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CutoffTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CreateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("meetingServerGuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MeetingServerGuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("meetingServerName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MeetingServerName"));
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
