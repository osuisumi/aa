/**
 * AppTextbook.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class AppTextbook  implements java.io.Serializable {
    private java.lang.String guid;

    private java.lang.String pressGuid;

    private java.lang.String pressName;

    private java.lang.String gradeGuid;

    private java.lang.String gradeName;

    private java.lang.String categoryGuid;

    private java.lang.String categoryName;

    private java.lang.String name;

    private java.lang.String description;

    private java.lang.String coverUrl;

    public AppTextbook() {
    }

    public AppTextbook(
           java.lang.String guid,
           java.lang.String pressGuid,
           java.lang.String pressName,
           java.lang.String gradeGuid,
           java.lang.String gradeName,
           java.lang.String categoryGuid,
           java.lang.String categoryName,
           java.lang.String name,
           java.lang.String description,
           java.lang.String coverUrl) {
           this.guid = guid;
           this.pressGuid = pressGuid;
           this.pressName = pressName;
           this.gradeGuid = gradeGuid;
           this.gradeName = gradeName;
           this.categoryGuid = categoryGuid;
           this.categoryName = categoryName;
           this.name = name;
           this.description = description;
           this.coverUrl = coverUrl;
    }


    /**
     * Gets the guid value for this AppTextbook.
     * 
     * @return guid
     */
    public java.lang.String getGuid() {
        return guid;
    }


    /**
     * Sets the guid value for this AppTextbook.
     * 
     * @param guid
     */
    public void setGuid(java.lang.String guid) {
        this.guid = guid;
    }


    /**
     * Gets the pressGuid value for this AppTextbook.
     * 
     * @return pressGuid
     */
    public java.lang.String getPressGuid() {
        return pressGuid;
    }


    /**
     * Sets the pressGuid value for this AppTextbook.
     * 
     * @param pressGuid
     */
    public void setPressGuid(java.lang.String pressGuid) {
        this.pressGuid = pressGuid;
    }


    /**
     * Gets the pressName value for this AppTextbook.
     * 
     * @return pressName
     */
    public java.lang.String getPressName() {
        return pressName;
    }


    /**
     * Sets the pressName value for this AppTextbook.
     * 
     * @param pressName
     */
    public void setPressName(java.lang.String pressName) {
        this.pressName = pressName;
    }


    /**
     * Gets the gradeGuid value for this AppTextbook.
     * 
     * @return gradeGuid
     */
    public java.lang.String getGradeGuid() {
        return gradeGuid;
    }


    /**
     * Sets the gradeGuid value for this AppTextbook.
     * 
     * @param gradeGuid
     */
    public void setGradeGuid(java.lang.String gradeGuid) {
        this.gradeGuid = gradeGuid;
    }


    /**
     * Gets the gradeName value for this AppTextbook.
     * 
     * @return gradeName
     */
    public java.lang.String getGradeName() {
        return gradeName;
    }


    /**
     * Sets the gradeName value for this AppTextbook.
     * 
     * @param gradeName
     */
    public void setGradeName(java.lang.String gradeName) {
        this.gradeName = gradeName;
    }


    /**
     * Gets the categoryGuid value for this AppTextbook.
     * 
     * @return categoryGuid
     */
    public java.lang.String getCategoryGuid() {
        return categoryGuid;
    }


    /**
     * Sets the categoryGuid value for this AppTextbook.
     * 
     * @param categoryGuid
     */
    public void setCategoryGuid(java.lang.String categoryGuid) {
        this.categoryGuid = categoryGuid;
    }


    /**
     * Gets the categoryName value for this AppTextbook.
     * 
     * @return categoryName
     */
    public java.lang.String getCategoryName() {
        return categoryName;
    }


    /**
     * Sets the categoryName value for this AppTextbook.
     * 
     * @param categoryName
     */
    public void setCategoryName(java.lang.String categoryName) {
        this.categoryName = categoryName;
    }


    /**
     * Gets the name value for this AppTextbook.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this AppTextbook.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the description value for this AppTextbook.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this AppTextbook.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the coverUrl value for this AppTextbook.
     * 
     * @return coverUrl
     */
    public java.lang.String getCoverUrl() {
        return coverUrl;
    }


    /**
     * Sets the coverUrl value for this AppTextbook.
     * 
     * @param coverUrl
     */
    public void setCoverUrl(java.lang.String coverUrl) {
        this.coverUrl = coverUrl;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AppTextbook)) return false;
        AppTextbook other = (AppTextbook) obj;
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
            ((this.pressGuid==null && other.getPressGuid()==null) || 
             (this.pressGuid!=null &&
              this.pressGuid.equals(other.getPressGuid()))) &&
            ((this.pressName==null && other.getPressName()==null) || 
             (this.pressName!=null &&
              this.pressName.equals(other.getPressName()))) &&
            ((this.gradeGuid==null && other.getGradeGuid()==null) || 
             (this.gradeGuid!=null &&
              this.gradeGuid.equals(other.getGradeGuid()))) &&
            ((this.gradeName==null && other.getGradeName()==null) || 
             (this.gradeName!=null &&
              this.gradeName.equals(other.getGradeName()))) &&
            ((this.categoryGuid==null && other.getCategoryGuid()==null) || 
             (this.categoryGuid!=null &&
              this.categoryGuid.equals(other.getCategoryGuid()))) &&
            ((this.categoryName==null && other.getCategoryName()==null) || 
             (this.categoryName!=null &&
              this.categoryName.equals(other.getCategoryName()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.coverUrl==null && other.getCoverUrl()==null) || 
             (this.coverUrl!=null &&
              this.coverUrl.equals(other.getCoverUrl())));
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
        if (getPressGuid() != null) {
            _hashCode += getPressGuid().hashCode();
        }
        if (getPressName() != null) {
            _hashCode += getPressName().hashCode();
        }
        if (getGradeGuid() != null) {
            _hashCode += getGradeGuid().hashCode();
        }
        if (getGradeName() != null) {
            _hashCode += getGradeName().hashCode();
        }
        if (getCategoryGuid() != null) {
            _hashCode += getCategoryGuid().hashCode();
        }
        if (getCategoryName() != null) {
            _hashCode += getCategoryName().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getCoverUrl() != null) {
            _hashCode += getCoverUrl().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AppTextbook.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "AppTextbook"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("guid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Guid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pressGuid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PressGuid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pressName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PressName"));
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
        elemField.setFieldName("gradeName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GradeName"));
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
        elemField.setFieldName("categoryName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CategoryName"));
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
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Description"));
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
