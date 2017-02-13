/**
 * VideoResourcePath.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class VideoResourcePath  implements java.io.Serializable {
    private boolean isRTMP;

    private java.lang.String SHD;

    private double SHDFileSize;

    private java.lang.String HD;

    private double HDFileSize;

    private java.lang.String NR;

    private double NRFileSize;

    private java.lang.String LW;

    private double LWFileSize;

    private java.lang.String SRC;

    private java.lang.String AVASRC;

    private java.lang.String captionsFile;

    private java.lang.String routeWebSite;

    private java.lang.String routeRecourseSite;

    private java.lang.String message;

    private double seconds;

    private java.util.Calendar lastViewTime;

    private int errCode;

    public VideoResourcePath() {
    }

    public VideoResourcePath(
           boolean isRTMP,
           java.lang.String SHD,
           double SHDFileSize,
           java.lang.String HD,
           double HDFileSize,
           java.lang.String NR,
           double NRFileSize,
           java.lang.String LW,
           double LWFileSize,
           java.lang.String SRC,
           java.lang.String AVASRC,
           java.lang.String captionsFile,
           java.lang.String routeWebSite,
           java.lang.String routeRecourseSite,
           java.lang.String message,
           double seconds,
           java.util.Calendar lastViewTime,
           int errCode) {
           this.isRTMP = isRTMP;
           this.SHD = SHD;
           this.SHDFileSize = SHDFileSize;
           this.HD = HD;
           this.HDFileSize = HDFileSize;
           this.NR = NR;
           this.NRFileSize = NRFileSize;
           this.LW = LW;
           this.LWFileSize = LWFileSize;
           this.SRC = SRC;
           this.AVASRC = AVASRC;
           this.captionsFile = captionsFile;
           this.routeWebSite = routeWebSite;
           this.routeRecourseSite = routeRecourseSite;
           this.message = message;
           this.seconds = seconds;
           this.lastViewTime = lastViewTime;
           this.errCode = errCode;
    }


    /**
     * Gets the isRTMP value for this VideoResourcePath.
     * 
     * @return isRTMP
     */
    public boolean isIsRTMP() {
        return isRTMP;
    }


    /**
     * Sets the isRTMP value for this VideoResourcePath.
     * 
     * @param isRTMP
     */
    public void setIsRTMP(boolean isRTMP) {
        this.isRTMP = isRTMP;
    }


    /**
     * Gets the SHD value for this VideoResourcePath.
     * 
     * @return SHD
     */
    public java.lang.String getSHD() {
        return SHD;
    }


    /**
     * Sets the SHD value for this VideoResourcePath.
     * 
     * @param SHD
     */
    public void setSHD(java.lang.String SHD) {
        this.SHD = SHD;
    }


    /**
     * Gets the SHDFileSize value for this VideoResourcePath.
     * 
     * @return SHDFileSize
     */
    public double getSHDFileSize() {
        return SHDFileSize;
    }


    /**
     * Sets the SHDFileSize value for this VideoResourcePath.
     * 
     * @param SHDFileSize
     */
    public void setSHDFileSize(double SHDFileSize) {
        this.SHDFileSize = SHDFileSize;
    }


    /**
     * Gets the HD value for this VideoResourcePath.
     * 
     * @return HD
     */
    public java.lang.String getHD() {
        return HD;
    }


    /**
     * Sets the HD value for this VideoResourcePath.
     * 
     * @param HD
     */
    public void setHD(java.lang.String HD) {
        this.HD = HD;
    }


    /**
     * Gets the HDFileSize value for this VideoResourcePath.
     * 
     * @return HDFileSize
     */
    public double getHDFileSize() {
        return HDFileSize;
    }


    /**
     * Sets the HDFileSize value for this VideoResourcePath.
     * 
     * @param HDFileSize
     */
    public void setHDFileSize(double HDFileSize) {
        this.HDFileSize = HDFileSize;
    }


    /**
     * Gets the NR value for this VideoResourcePath.
     * 
     * @return NR
     */
    public java.lang.String getNR() {
        return NR;
    }


    /**
     * Sets the NR value for this VideoResourcePath.
     * 
     * @param NR
     */
    public void setNR(java.lang.String NR) {
        this.NR = NR;
    }


    /**
     * Gets the NRFileSize value for this VideoResourcePath.
     * 
     * @return NRFileSize
     */
    public double getNRFileSize() {
        return NRFileSize;
    }


    /**
     * Sets the NRFileSize value for this VideoResourcePath.
     * 
     * @param NRFileSize
     */
    public void setNRFileSize(double NRFileSize) {
        this.NRFileSize = NRFileSize;
    }


    /**
     * Gets the LW value for this VideoResourcePath.
     * 
     * @return LW
     */
    public java.lang.String getLW() {
        return LW;
    }


    /**
     * Sets the LW value for this VideoResourcePath.
     * 
     * @param LW
     */
    public void setLW(java.lang.String LW) {
        this.LW = LW;
    }


    /**
     * Gets the LWFileSize value for this VideoResourcePath.
     * 
     * @return LWFileSize
     */
    public double getLWFileSize() {
        return LWFileSize;
    }


    /**
     * Sets the LWFileSize value for this VideoResourcePath.
     * 
     * @param LWFileSize
     */
    public void setLWFileSize(double LWFileSize) {
        this.LWFileSize = LWFileSize;
    }


    /**
     * Gets the SRC value for this VideoResourcePath.
     * 
     * @return SRC
     */
    public java.lang.String getSRC() {
        return SRC;
    }


    /**
     * Sets the SRC value for this VideoResourcePath.
     * 
     * @param SRC
     */
    public void setSRC(java.lang.String SRC) {
        this.SRC = SRC;
    }


    /**
     * Gets the AVASRC value for this VideoResourcePath.
     * 
     * @return AVASRC
     */
    public java.lang.String getAVASRC() {
        return AVASRC;
    }


    /**
     * Sets the AVASRC value for this VideoResourcePath.
     * 
     * @param AVASRC
     */
    public void setAVASRC(java.lang.String AVASRC) {
        this.AVASRC = AVASRC;
    }


    /**
     * Gets the captionsFile value for this VideoResourcePath.
     * 
     * @return captionsFile
     */
    public java.lang.String getCaptionsFile() {
        return captionsFile;
    }


    /**
     * Sets the captionsFile value for this VideoResourcePath.
     * 
     * @param captionsFile
     */
    public void setCaptionsFile(java.lang.String captionsFile) {
        this.captionsFile = captionsFile;
    }


    /**
     * Gets the routeWebSite value for this VideoResourcePath.
     * 
     * @return routeWebSite
     */
    public java.lang.String getRouteWebSite() {
        return routeWebSite;
    }


    /**
     * Sets the routeWebSite value for this VideoResourcePath.
     * 
     * @param routeWebSite
     */
    public void setRouteWebSite(java.lang.String routeWebSite) {
        this.routeWebSite = routeWebSite;
    }


    /**
     * Gets the routeRecourseSite value for this VideoResourcePath.
     * 
     * @return routeRecourseSite
     */
    public java.lang.String getRouteRecourseSite() {
        return routeRecourseSite;
    }


    /**
     * Sets the routeRecourseSite value for this VideoResourcePath.
     * 
     * @param routeRecourseSite
     */
    public void setRouteRecourseSite(java.lang.String routeRecourseSite) {
        this.routeRecourseSite = routeRecourseSite;
    }


    /**
     * Gets the message value for this VideoResourcePath.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this VideoResourcePath.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the seconds value for this VideoResourcePath.
     * 
     * @return seconds
     */
    public double getSeconds() {
        return seconds;
    }


    /**
     * Sets the seconds value for this VideoResourcePath.
     * 
     * @param seconds
     */
    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }


    /**
     * Gets the lastViewTime value for this VideoResourcePath.
     * 
     * @return lastViewTime
     */
    public java.util.Calendar getLastViewTime() {
        return lastViewTime;
    }


    /**
     * Sets the lastViewTime value for this VideoResourcePath.
     * 
     * @param lastViewTime
     */
    public void setLastViewTime(java.util.Calendar lastViewTime) {
        this.lastViewTime = lastViewTime;
    }


    /**
     * Gets the errCode value for this VideoResourcePath.
     * 
     * @return errCode
     */
    public int getErrCode() {
        return errCode;
    }


    /**
     * Sets the errCode value for this VideoResourcePath.
     * 
     * @param errCode
     */
    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VideoResourcePath)) return false;
        VideoResourcePath other = (VideoResourcePath) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.isRTMP == other.isIsRTMP() &&
            ((this.SHD==null && other.getSHD()==null) || 
             (this.SHD!=null &&
              this.SHD.equals(other.getSHD()))) &&
            this.SHDFileSize == other.getSHDFileSize() &&
            ((this.HD==null && other.getHD()==null) || 
             (this.HD!=null &&
              this.HD.equals(other.getHD()))) &&
            this.HDFileSize == other.getHDFileSize() &&
            ((this.NR==null && other.getNR()==null) || 
             (this.NR!=null &&
              this.NR.equals(other.getNR()))) &&
            this.NRFileSize == other.getNRFileSize() &&
            ((this.LW==null && other.getLW()==null) || 
             (this.LW!=null &&
              this.LW.equals(other.getLW()))) &&
            this.LWFileSize == other.getLWFileSize() &&
            ((this.SRC==null && other.getSRC()==null) || 
             (this.SRC!=null &&
              this.SRC.equals(other.getSRC()))) &&
            ((this.AVASRC==null && other.getAVASRC()==null) || 
             (this.AVASRC!=null &&
              this.AVASRC.equals(other.getAVASRC()))) &&
            ((this.captionsFile==null && other.getCaptionsFile()==null) || 
             (this.captionsFile!=null &&
              this.captionsFile.equals(other.getCaptionsFile()))) &&
            ((this.routeWebSite==null && other.getRouteWebSite()==null) || 
             (this.routeWebSite!=null &&
              this.routeWebSite.equals(other.getRouteWebSite()))) &&
            ((this.routeRecourseSite==null && other.getRouteRecourseSite()==null) || 
             (this.routeRecourseSite!=null &&
              this.routeRecourseSite.equals(other.getRouteRecourseSite()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            this.seconds == other.getSeconds() &&
            ((this.lastViewTime==null && other.getLastViewTime()==null) || 
             (this.lastViewTime!=null &&
              this.lastViewTime.equals(other.getLastViewTime()))) &&
            this.errCode == other.getErrCode();
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
        _hashCode += (isIsRTMP() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getSHD() != null) {
            _hashCode += getSHD().hashCode();
        }
        _hashCode += new Double(getSHDFileSize()).hashCode();
        if (getHD() != null) {
            _hashCode += getHD().hashCode();
        }
        _hashCode += new Double(getHDFileSize()).hashCode();
        if (getNR() != null) {
            _hashCode += getNR().hashCode();
        }
        _hashCode += new Double(getNRFileSize()).hashCode();
        if (getLW() != null) {
            _hashCode += getLW().hashCode();
        }
        _hashCode += new Double(getLWFileSize()).hashCode();
        if (getSRC() != null) {
            _hashCode += getSRC().hashCode();
        }
        if (getAVASRC() != null) {
            _hashCode += getAVASRC().hashCode();
        }
        if (getCaptionsFile() != null) {
            _hashCode += getCaptionsFile().hashCode();
        }
        if (getRouteWebSite() != null) {
            _hashCode += getRouteWebSite().hashCode();
        }
        if (getRouteRecourseSite() != null) {
            _hashCode += getRouteRecourseSite().hashCode();
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        _hashCode += new Double(getSeconds()).hashCode();
        if (getLastViewTime() != null) {
            _hashCode += getLastViewTime().hashCode();
        }
        _hashCode += getErrCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VideoResourcePath.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "VideoResourcePath"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isRTMP");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IsRTMP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SHD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SHD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SHDFileSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SHDFileSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("HD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "HD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("HDFileSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "HDFileSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NR");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "NR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NRFileSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "NRFileSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LW");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LW"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LWFileSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LWFileSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SRC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SRC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AVASRC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AVASRC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("captionsFile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CaptionsFile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("routeWebSite");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "RouteWebSite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("routeRecourseSite");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "RouteRecourseSite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seconds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Seconds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastViewTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "LastViewTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ErrCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
