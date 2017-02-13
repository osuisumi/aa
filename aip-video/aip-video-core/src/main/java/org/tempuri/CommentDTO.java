/**
 * CommentDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class CommentDTO  implements java.io.Serializable {
    private java.lang.String guid;

    private int version;

    private int status;

    private java.lang.String userGuid;

    private java.lang.String targetType;

    private java.lang.String targetGuid;

    private java.lang.String title;

    private java.lang.String commentText;

    private java.lang.String IP;

    private java.util.Calendar createTime;

    private java.lang.String media;

    private java.lang.Double position;

    private java.lang.String userUserTypeDomainGuid;

    private java.lang.String userUserTypeDomainName;

    private java.lang.String userUserTypeDomainCode;

    private java.lang.String userUserTypeGuid;

    private java.lang.String userUserTypeName;

    private java.lang.String userTrueName;

    private java.lang.String userUserName;

    private java.lang.String userHeadPortrait;

    public CommentDTO() {
    }

    public CommentDTO(
           java.lang.String guid,
           int version,
           int status,
           java.lang.String userGuid,
           java.lang.String targetType,
           java.lang.String targetGuid,
           java.lang.String title,
           java.lang.String commentText,
           java.lang.String IP,
           java.util.Calendar createTime,
           java.lang.String media,
           java.lang.Double position,
           java.lang.String userUserTypeDomainGuid,
           java.lang.String userUserTypeDomainName,
           java.lang.String userUserTypeDomainCode,
           java.lang.String userUserTypeGuid,
           java.lang.String userUserTypeName,
           java.lang.String userTrueName,
           java.lang.String userUserName,
           java.lang.String userHeadPortrait) {
           this.guid = guid;
           this.version = version;
           this.status = status;
           this.userGuid = userGuid;
           this.targetType = targetType;
           this.targetGuid = targetGuid;
           this.title = title;
           this.commentText = commentText;
           this.IP = IP;
           this.createTime = createTime;
           this.media = media;
           this.position = position;
           this.userUserTypeDomainGuid = userUserTypeDomainGuid;
           this.userUserTypeDomainName = userUserTypeDomainName;
           this.userUserTypeDomainCode = userUserTypeDomainCode;
           this.userUserTypeGuid = userUserTypeGuid;
           this.userUserTypeName = userUserTypeName;
           this.userTrueName = userTrueName;
           this.userUserName = userUserName;
           this.userHeadPortrait = userHeadPortrait;
    }


    /**
     * Gets the guid value for this CommentDTO.
     * 
     * @return guid
     */
    public java.lang.String getGuid() {
        return guid;
    }


    /**
     * Sets the guid value for this CommentDTO.
     * 
     * @param guid
     */
    public void setGuid(java.lang.String guid) {
        this.guid = guid;
    }


    /**
     * Gets the version value for this CommentDTO.
     * 
     * @return version
     */
    public int getVersion() {
        return version;
    }


    /**
     * Sets the version value for this CommentDTO.
     * 
     * @param version
     */
    public void setVersion(int version) {
        this.version = version;
    }


    /**
     * Gets the status value for this CommentDTO.
     * 
     * @return status
     */
    public int getStatus() {
        return status;
    }


    /**
     * Sets the status value for this CommentDTO.
     * 
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }


    /**
     * Gets the userGuid value for this CommentDTO.
     * 
     * @return userGuid
     */
    public java.lang.String getUserGuid() {
        return userGuid;
    }


    /**
     * Sets the userGuid value for this CommentDTO.
     * 
     * @param userGuid
     */
    public void setUserGuid(java.lang.String userGuid) {
        this.userGuid = userGuid;
    }


    /**
     * Gets the targetType value for this CommentDTO.
     * 
     * @return targetType
     */
    public java.lang.String getTargetType() {
        return targetType;
    }


    /**
     * Sets the targetType value for this CommentDTO.
     * 
     * @param targetType
     */
    public void setTargetType(java.lang.String targetType) {
        this.targetType = targetType;
    }


    /**
     * Gets the targetGuid value for this CommentDTO.
     * 
     * @return targetGuid
     */
    public java.lang.String getTargetGuid() {
        return targetGuid;
    }


    /**
     * Sets the targetGuid value for this CommentDTO.
     * 
     * @param targetGuid
     */
    public void setTargetGuid(java.lang.String targetGuid) {
        this.targetGuid = targetGuid;
    }


    /**
     * Gets the title value for this CommentDTO.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this CommentDTO.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the commentText value for this CommentDTO.
     * 
     * @return commentText
     */
    public java.lang.String getCommentText() {
        return commentText;
    }


    /**
     * Sets the commentText value for this CommentDTO.
     * 
     * @param commentText
     */
    public void setCommentText(java.lang.String commentText) {
        this.commentText = commentText;
    }


    /**
     * Gets the IP value for this CommentDTO.
     * 
     * @return IP
     */
    public java.lang.String getIP() {
        return IP;
    }


    /**
     * Sets the IP value for this CommentDTO.
     * 
     * @param IP
     */
    public void setIP(java.lang.String IP) {
        this.IP = IP;
    }


    /**
     * Gets the createTime value for this CommentDTO.
     * 
     * @return createTime
     */
    public java.util.Calendar getCreateTime() {
        return createTime;
    }


    /**
     * Sets the createTime value for this CommentDTO.
     * 
     * @param createTime
     */
    public void setCreateTime(java.util.Calendar createTime) {
        this.createTime = createTime;
    }


    /**
     * Gets the media value for this CommentDTO.
     * 
     * @return media
     */
    public java.lang.String getMedia() {
        return media;
    }


    /**
     * Sets the media value for this CommentDTO.
     * 
     * @param media
     */
    public void setMedia(java.lang.String media) {
        this.media = media;
    }


    /**
     * Gets the position value for this CommentDTO.
     * 
     * @return position
     */
    public java.lang.Double getPosition() {
        return position;
    }


    /**
     * Sets the position value for this CommentDTO.
     * 
     * @param position
     */
    public void setPosition(java.lang.Double position) {
        this.position = position;
    }


    /**
     * Gets the userUserTypeDomainGuid value for this CommentDTO.
     * 
     * @return userUserTypeDomainGuid
     */
    public java.lang.String getUserUserTypeDomainGuid() {
        return userUserTypeDomainGuid;
    }


    /**
     * Sets the userUserTypeDomainGuid value for this CommentDTO.
     * 
     * @param userUserTypeDomainGuid
     */
    public void setUserUserTypeDomainGuid(java.lang.String userUserTypeDomainGuid) {
        this.userUserTypeDomainGuid = userUserTypeDomainGuid;
    }


    /**
     * Gets the userUserTypeDomainName value for this CommentDTO.
     * 
     * @return userUserTypeDomainName
     */
    public java.lang.String getUserUserTypeDomainName() {
        return userUserTypeDomainName;
    }


    /**
     * Sets the userUserTypeDomainName value for this CommentDTO.
     * 
     * @param userUserTypeDomainName
     */
    public void setUserUserTypeDomainName(java.lang.String userUserTypeDomainName) {
        this.userUserTypeDomainName = userUserTypeDomainName;
    }


    /**
     * Gets the userUserTypeDomainCode value for this CommentDTO.
     * 
     * @return userUserTypeDomainCode
     */
    public java.lang.String getUserUserTypeDomainCode() {
        return userUserTypeDomainCode;
    }


    /**
     * Sets the userUserTypeDomainCode value for this CommentDTO.
     * 
     * @param userUserTypeDomainCode
     */
    public void setUserUserTypeDomainCode(java.lang.String userUserTypeDomainCode) {
        this.userUserTypeDomainCode = userUserTypeDomainCode;
    }


    /**
     * Gets the userUserTypeGuid value for this CommentDTO.
     * 
     * @return userUserTypeGuid
     */
    public java.lang.String getUserUserTypeGuid() {
        return userUserTypeGuid;
    }


    /**
     * Sets the userUserTypeGuid value for this CommentDTO.
     * 
     * @param userUserTypeGuid
     */
    public void setUserUserTypeGuid(java.lang.String userUserTypeGuid) {
        this.userUserTypeGuid = userUserTypeGuid;
    }


    /**
     * Gets the userUserTypeName value for this CommentDTO.
     * 
     * @return userUserTypeName
     */
    public java.lang.String getUserUserTypeName() {
        return userUserTypeName;
    }


    /**
     * Sets the userUserTypeName value for this CommentDTO.
     * 
     * @param userUserTypeName
     */
    public void setUserUserTypeName(java.lang.String userUserTypeName) {
        this.userUserTypeName = userUserTypeName;
    }


    /**
     * Gets the userTrueName value for this CommentDTO.
     * 
     * @return userTrueName
     */
    public java.lang.String getUserTrueName() {
        return userTrueName;
    }


    /**
     * Sets the userTrueName value for this CommentDTO.
     * 
     * @param userTrueName
     */
    public void setUserTrueName(java.lang.String userTrueName) {
        this.userTrueName = userTrueName;
    }


    /**
     * Gets the userUserName value for this CommentDTO.
     * 
     * @return userUserName
     */
    public java.lang.String getUserUserName() {
        return userUserName;
    }


    /**
     * Sets the userUserName value for this CommentDTO.
     * 
     * @param userUserName
     */
    public void setUserUserName(java.lang.String userUserName) {
        this.userUserName = userUserName;
    }


    /**
     * Gets the userHeadPortrait value for this CommentDTO.
     * 
     * @return userHeadPortrait
     */
    public java.lang.String getUserHeadPortrait() {
        return userHeadPortrait;
    }


    /**
     * Sets the userHeadPortrait value for this CommentDTO.
     * 
     * @param userHeadPortrait
     */
    public void setUserHeadPortrait(java.lang.String userHeadPortrait) {
        this.userHeadPortrait = userHeadPortrait;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CommentDTO)) return false;
        CommentDTO other = (CommentDTO) obj;
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
            ((this.userGuid==null && other.getUserGuid()==null) || 
             (this.userGuid!=null &&
              this.userGuid.equals(other.getUserGuid()))) &&
            ((this.targetType==null && other.getTargetType()==null) || 
             (this.targetType!=null &&
              this.targetType.equals(other.getTargetType()))) &&
            ((this.targetGuid==null && other.getTargetGuid()==null) || 
             (this.targetGuid!=null &&
              this.targetGuid.equals(other.getTargetGuid()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.commentText==null && other.getCommentText()==null) || 
             (this.commentText!=null &&
              this.commentText.equals(other.getCommentText()))) &&
            ((this.IP==null && other.getIP()==null) || 
             (this.IP!=null &&
              this.IP.equals(other.getIP()))) &&
            ((this.createTime==null && other.getCreateTime()==null) || 
             (this.createTime!=null &&
              this.createTime.equals(other.getCreateTime()))) &&
            ((this.media==null && other.getMedia()==null) || 
             (this.media!=null &&
              this.media.equals(other.getMedia()))) &&
            ((this.position==null && other.getPosition()==null) || 
             (this.position!=null &&
              this.position.equals(other.getPosition()))) &&
            ((this.userUserTypeDomainGuid==null && other.getUserUserTypeDomainGuid()==null) || 
             (this.userUserTypeDomainGuid!=null &&
              this.userUserTypeDomainGuid.equals(other.getUserUserTypeDomainGuid()))) &&
            ((this.userUserTypeDomainName==null && other.getUserUserTypeDomainName()==null) || 
             (this.userUserTypeDomainName!=null &&
              this.userUserTypeDomainName.equals(other.getUserUserTypeDomainName()))) &&
            ((this.userUserTypeDomainCode==null && other.getUserUserTypeDomainCode()==null) || 
             (this.userUserTypeDomainCode!=null &&
              this.userUserTypeDomainCode.equals(other.getUserUserTypeDomainCode()))) &&
            ((this.userUserTypeGuid==null && other.getUserUserTypeGuid()==null) || 
             (this.userUserTypeGuid!=null &&
              this.userUserTypeGuid.equals(other.getUserUserTypeGuid()))) &&
            ((this.userUserTypeName==null && other.getUserUserTypeName()==null) || 
             (this.userUserTypeName!=null &&
              this.userUserTypeName.equals(other.getUserUserTypeName()))) &&
            ((this.userTrueName==null && other.getUserTrueName()==null) || 
             (this.userTrueName!=null &&
              this.userTrueName.equals(other.getUserTrueName()))) &&
            ((this.userUserName==null && other.getUserUserName()==null) || 
             (this.userUserName!=null &&
              this.userUserName.equals(other.getUserUserName()))) &&
            ((this.userHeadPortrait==null && other.getUserHeadPortrait()==null) || 
             (this.userHeadPortrait!=null &&
              this.userHeadPortrait.equals(other.getUserHeadPortrait())));
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
        if (getUserGuid() != null) {
            _hashCode += getUserGuid().hashCode();
        }
        if (getTargetType() != null) {
            _hashCode += getTargetType().hashCode();
        }
        if (getTargetGuid() != null) {
            _hashCode += getTargetGuid().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getCommentText() != null) {
            _hashCode += getCommentText().hashCode();
        }
        if (getIP() != null) {
            _hashCode += getIP().hashCode();
        }
        if (getCreateTime() != null) {
            _hashCode += getCreateTime().hashCode();
        }
        if (getMedia() != null) {
            _hashCode += getMedia().hashCode();
        }
        if (getPosition() != null) {
            _hashCode += getPosition().hashCode();
        }
        if (getUserUserTypeDomainGuid() != null) {
            _hashCode += getUserUserTypeDomainGuid().hashCode();
        }
        if (getUserUserTypeDomainName() != null) {
            _hashCode += getUserUserTypeDomainName().hashCode();
        }
        if (getUserUserTypeDomainCode() != null) {
            _hashCode += getUserUserTypeDomainCode().hashCode();
        }
        if (getUserUserTypeGuid() != null) {
            _hashCode += getUserUserTypeGuid().hashCode();
        }
        if (getUserUserTypeName() != null) {
            _hashCode += getUserUserTypeName().hashCode();
        }
        if (getUserTrueName() != null) {
            _hashCode += getUserTrueName().hashCode();
        }
        if (getUserUserName() != null) {
            _hashCode += getUserUserName().hashCode();
        }
        if (getUserHeadPortrait() != null) {
            _hashCode += getUserHeadPortrait().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CommentDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "CommentDTO"));
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
        elemField.setFieldName("userGuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserGuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("targetType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TargetType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("targetGuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TargetGuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commentText");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CommentText"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IP");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CreateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("media");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Media"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("position");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Position"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userUserTypeDomainGuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserUserTypeDomainGuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userUserTypeDomainName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserUserTypeDomainName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userUserTypeDomainCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserUserTypeDomainCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userUserTypeGuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserUserTypeGuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userUserTypeName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserUserTypeName"));
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
        elemField.setFieldName("userUserName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserUserName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userHeadPortrait");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UserHeadPortrait"));
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
