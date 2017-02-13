/**
 * VideoAddItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class VideoAddItem  implements java.io.Serializable {
    private java.lang.String createUserGuid;

    private java.lang.String speaker;

    private java.lang.String name;

    private java.lang.String summary;

    private java.lang.String description;

    private java.lang.String categoryGuid;

    private java.lang.String gradeGuid;

    private java.lang.String lessonGuid;

    private java.lang.String applicationGuid;

    private int period;

    private java.util.Calendar recTime;

    private byte[] cover;

    private java.lang.String fileName;

    private double fileSize;

    private java.lang.String sessionID;

    public VideoAddItem() {
    }

    public VideoAddItem(
           java.lang.String createUserGuid,
           java.lang.String speaker,
           java.lang.String name,
           java.lang.String summary,
           java.lang.String description,
           java.lang.String categoryGuid,
           java.lang.String gradeGuid,
           java.lang.String lessonGuid,
           java.lang.String applicationGuid,
           int period,
           java.util.Calendar recTime,
           byte[] cover,
           java.lang.String fileName,
           double fileSize,
           java.lang.String sessionID) {
           this.createUserGuid = createUserGuid;
           this.speaker = speaker;
           this.name = name;
           this.summary = summary;
           this.description = description;
           this.categoryGuid = categoryGuid;
           this.gradeGuid = gradeGuid;
           this.lessonGuid = lessonGuid;
           this.applicationGuid = applicationGuid;
           this.period = period;
           this.recTime = recTime;
           this.cover = cover;
           this.fileName = fileName;
           this.fileSize = fileSize;
           this.sessionID = sessionID;
    }


    /**
     * Gets the createUserGuid value for this VideoAddItem.
     * 
     * @return createUserGuid
     */
    public java.lang.String getCreateUserGuid() {
        return createUserGuid;
    }


    /**
     * Sets the createUserGuid value for this VideoAddItem.
     * 
     * @param createUserGuid
     */
    public void setCreateUserGuid(java.lang.String createUserGuid) {
        this.createUserGuid = createUserGuid;
    }


    /**
     * Gets the speaker value for this VideoAddItem.
     * 
     * @return speaker
     */
    public java.lang.String getSpeaker() {
        return speaker;
    }


    /**
     * Sets the speaker value for this VideoAddItem.
     * 
     * @param speaker
     */
    public void setSpeaker(java.lang.String speaker) {
        this.speaker = speaker;
    }


    /**
     * Gets the name value for this VideoAddItem.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this VideoAddItem.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the summary value for this VideoAddItem.
     * 
     * @return summary
     */
    public java.lang.String getSummary() {
        return summary;
    }


    /**
     * Sets the summary value for this VideoAddItem.
     * 
     * @param summary
     */
    public void setSummary(java.lang.String summary) {
        this.summary = summary;
    }


    /**
     * Gets the description value for this VideoAddItem.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this VideoAddItem.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the categoryGuid value for this VideoAddItem.
     * 
     * @return categoryGuid
     */
    public java.lang.String getCategoryGuid() {
        return categoryGuid;
    }


    /**
     * Sets the categoryGuid value for this VideoAddItem.
     * 
     * @param categoryGuid
     */
    public void setCategoryGuid(java.lang.String categoryGuid) {
        this.categoryGuid = categoryGuid;
    }


    /**
     * Gets the gradeGuid value for this VideoAddItem.
     * 
     * @return gradeGuid
     */
    public java.lang.String getGradeGuid() {
        return gradeGuid;
    }


    /**
     * Sets the gradeGuid value for this VideoAddItem.
     * 
     * @param gradeGuid
     */
    public void setGradeGuid(java.lang.String gradeGuid) {
        this.gradeGuid = gradeGuid;
    }


    /**
     * Gets the lessonGuid value for this VideoAddItem.
     * 
     * @return lessonGuid
     */
    public java.lang.String getLessonGuid() {
        return lessonGuid;
    }


    /**
     * Sets the lessonGuid value for this VideoAddItem.
     * 
     * @param lessonGuid
     */
    public void setLessonGuid(java.lang.String lessonGuid) {
        this.lessonGuid = lessonGuid;
    }


    /**
     * Gets the applicationGuid value for this VideoAddItem.
     * 
     * @return applicationGuid
     */
    public java.lang.String getApplicationGuid() {
        return applicationGuid;
    }


    /**
     * Sets the applicationGuid value for this VideoAddItem.
     * 
     * @param applicationGuid
     */
    public void setApplicationGuid(java.lang.String applicationGuid) {
        this.applicationGuid = applicationGuid;
    }


    /**
     * Gets the period value for this VideoAddItem.
     * 
     * @return period
     */
    public int getPeriod() {
        return period;
    }


    /**
     * Sets the period value for this VideoAddItem.
     * 
     * @param period
     */
    public void setPeriod(int period) {
        this.period = period;
    }


    /**
     * Gets the recTime value for this VideoAddItem.
     * 
     * @return recTime
     */
    public java.util.Calendar getRecTime() {
        return recTime;
    }


    /**
     * Sets the recTime value for this VideoAddItem.
     * 
     * @param recTime
     */
    public void setRecTime(java.util.Calendar recTime) {
        this.recTime = recTime;
    }


    /**
     * Gets the cover value for this VideoAddItem.
     * 
     * @return cover
     */
    public byte[] getCover() {
        return cover;
    }


    /**
     * Sets the cover value for this VideoAddItem.
     * 
     * @param cover
     */
    public void setCover(byte[] cover) {
        this.cover = cover;
    }


    /**
     * Gets the fileName value for this VideoAddItem.
     * 
     * @return fileName
     */
    public java.lang.String getFileName() {
        return fileName;
    }


    /**
     * Sets the fileName value for this VideoAddItem.
     * 
     * @param fileName
     */
    public void setFileName(java.lang.String fileName) {
        this.fileName = fileName;
    }


    /**
     * Gets the fileSize value for this VideoAddItem.
     * 
     * @return fileSize
     */
    public double getFileSize() {
        return fileSize;
    }


    /**
     * Sets the fileSize value for this VideoAddItem.
     * 
     * @param fileSize
     */
    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }


    /**
     * Gets the sessionID value for this VideoAddItem.
     * 
     * @return sessionID
     */
    public java.lang.String getSessionID() {
        return sessionID;
    }


    /**
     * Sets the sessionID value for this VideoAddItem.
     * 
     * @param sessionID
     */
    public void setSessionID(java.lang.String sessionID) {
        this.sessionID = sessionID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VideoAddItem)) return false;
        VideoAddItem other = (VideoAddItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.createUserGuid==null && other.getCreateUserGuid()==null) || 
             (this.createUserGuid!=null &&
              this.createUserGuid.equals(other.getCreateUserGuid()))) &&
            ((this.speaker==null && other.getSpeaker()==null) || 
             (this.speaker!=null &&
              this.speaker.equals(other.getSpeaker()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.summary==null && other.getSummary()==null) || 
             (this.summary!=null &&
              this.summary.equals(other.getSummary()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.categoryGuid==null && other.getCategoryGuid()==null) || 
             (this.categoryGuid!=null &&
              this.categoryGuid.equals(other.getCategoryGuid()))) &&
            ((this.gradeGuid==null && other.getGradeGuid()==null) || 
             (this.gradeGuid!=null &&
              this.gradeGuid.equals(other.getGradeGuid()))) &&
            ((this.lessonGuid==null && other.getLessonGuid()==null) || 
             (this.lessonGuid!=null &&
              this.lessonGuid.equals(other.getLessonGuid()))) &&
            ((this.applicationGuid==null && other.getApplicationGuid()==null) || 
             (this.applicationGuid!=null &&
              this.applicationGuid.equals(other.getApplicationGuid()))) &&
            this.period == other.getPeriod() &&
            ((this.recTime==null && other.getRecTime()==null) || 
             (this.recTime!=null &&
              this.recTime.equals(other.getRecTime()))) &&
            ((this.cover==null && other.getCover()==null) || 
             (this.cover!=null &&
              java.util.Arrays.equals(this.cover, other.getCover()))) &&
            ((this.fileName==null && other.getFileName()==null) || 
             (this.fileName!=null &&
              this.fileName.equals(other.getFileName()))) &&
            this.fileSize == other.getFileSize() &&
            ((this.sessionID==null && other.getSessionID()==null) || 
             (this.sessionID!=null &&
              this.sessionID.equals(other.getSessionID())));
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
        if (getCreateUserGuid() != null) {
            _hashCode += getCreateUserGuid().hashCode();
        }
        if (getSpeaker() != null) {
            _hashCode += getSpeaker().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getSummary() != null) {
            _hashCode += getSummary().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getCategoryGuid() != null) {
            _hashCode += getCategoryGuid().hashCode();
        }
        if (getGradeGuid() != null) {
            _hashCode += getGradeGuid().hashCode();
        }
        if (getLessonGuid() != null) {
            _hashCode += getLessonGuid().hashCode();
        }
        if (getApplicationGuid() != null) {
            _hashCode += getApplicationGuid().hashCode();
        }
        _hashCode += getPeriod();
        if (getRecTime() != null) {
            _hashCode += getRecTime().hashCode();
        }
        if (getCover() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCover());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCover(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFileName() != null) {
            _hashCode += getFileName().hashCode();
        }
        _hashCode += new Double(getFileSize()).hashCode();
        if (getSessionID() != null) {
            _hashCode += getSessionID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VideoAddItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "VideoAddItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createUserGuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CreateUserGuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("speaker");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Speaker"));
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
        elemField.setFieldName("summary");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Summary"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("categoryGuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CategoryGuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gradeGuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GradeGuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lessonGuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LessonGuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationGuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ApplicationGuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("period");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Period"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "RecTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cover");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Cover"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FileSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SessionID"));
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
