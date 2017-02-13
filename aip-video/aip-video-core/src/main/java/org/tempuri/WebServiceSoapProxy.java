package org.tempuri;

public class WebServiceSoapProxy implements org.tempuri.WebServiceSoap {
  private String _endpoint = null;
  private org.tempuri.WebServiceSoap webServiceSoap = null;
  
  public WebServiceSoapProxy() {
    _initWebServiceSoapProxy();
  }
  
  public WebServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initWebServiceSoapProxy();
  }
  
  private void _initWebServiceSoapProxy() {
    try {
      webServiceSoap = (new org.tempuri.WebServiceLocator()).getWebServiceSoap();
      if (webServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)webServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)webServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (webServiceSoap != null)
      ((javax.xml.rpc.Stub)webServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.WebServiceSoap getWebServiceSoap() {
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap;
  }
  
  public java.lang.String AAA() throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.AAA();
  }
  
  public java.lang.String helloAVA() throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.helloAVA();
  }
  
  public org.tempuri.MessageResultOfString about(java.lang.String name) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.about(name);
  }
  
  public java.lang.String helloWorld(java.lang.String name) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.helloWorld(name);
  }
  
  public org.tempuri.MessageResultOfString throwError() throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.throwError();
  }
  
  public org.tempuri.MessageResultOfListOfAppVideoForXIAN listVideoForXIAN(org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize, int orderBy) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listVideoForXIAN(param, pageIndex, pageSize, orderBy);
  }
  
  public org.tempuri.MessageResultOfListOfAppVideo listVideo(org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize, int orderBy) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listVideo(param, pageIndex, pageSize, orderBy);
  }
  
  public org.tempuri.MessageResultOfListOfAppTextbook listTextbook(org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize, int orderBy) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listTextbook(param, pageIndex, pageSize, orderBy);
  }
  
  public org.tempuri.MessageResultOfListOfAppAlbum listAlbum(org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize, int orderBy) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listAlbum(param, pageIndex, pageSize, orderBy);
  }
  
  public org.tempuri.MessageResultOfListOfAppSubject listSubject(org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize, int orderBy) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listSubject(param, pageIndex, pageSize, orderBy);
  }
  
  public org.tempuri.MessageResultOfListOfAppMeeting listMeeting(org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize, int orderBy) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listMeeting(param, pageIndex, pageSize, orderBy);
  }
  
  public org.tempuri.MessageResultOfListOfAppApplication listApplication(org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize, int orderBy) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listApplication(param, pageIndex, pageSize, orderBy);
  }
  
  public org.tempuri.MessageResultOfListOfAppRecordEquipment listRecordEquipment(org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize, int orderBy) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listRecordEquipment(param, pageIndex, pageSize, orderBy);
  }
  
  public org.tempuri.MessageResultOfListOfAppApplication listRoom(org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize, int orderBy) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listRoom(param, pageIndex, pageSize, orderBy);
  }
  
  public org.tempuri.MessageResultOfListOfCommentDTO listComment(java.lang.String targetType, java.lang.String targetGuid, org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listComment(targetType, targetGuid, param, pageIndex, pageSize);
  }
  
  public org.tempuri.MessageResultOfAppVideo video(java.lang.String guid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.video(guid);
  }
  
  public org.tempuri.MessageResultOfListOfVideoAttachmentDTO videoAttachment(java.lang.String videoGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.videoAttachment(videoGuid);
  }
  
  public org.tempuri.MessageResultOfListOfAppKeyValue videoChannel(java.lang.String videoGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.videoChannel(videoGuid);
  }
  
  public org.tempuri.MessageResultOfListOfVideoSWDTO videoSW(java.lang.String videoGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.videoSW(videoGuid);
  }
  
  public org.tempuri.MessageResultOfListOfVideoSWPatternDTO videoSWPattern(java.lang.String videoGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.videoSWPattern(videoGuid);
  }
  
  public org.tempuri.MessageResultOfListOfVideoTagDTO videoTag(java.lang.String videoGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.videoTag(videoGuid);
  }
  
  public org.tempuri.MessageResultOfListOfVideoTimeInfoDTO videoTimeInfo(java.lang.String videoGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.videoTimeInfo(videoGuid);
  }
  
  public org.tempuri.MessageResultOfListOfVideoTimeInfoOtherDTO videoTimeInfoOther(java.lang.String videoGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.videoTimeInfoOther(videoGuid);
  }
  
  public org.tempuri.MessageResultOfListOfLessonDTO listTextbookLesson(java.lang.String textbookGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listTextbookLesson(textbookGuid);
  }
  
  public org.tempuri.MessageResultOfAppAlbum album(java.lang.String guid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.album(guid);
  }
  
  public org.tempuri.MessageResultOfListOfAppVideo albumVideo(java.lang.String guid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.albumVideo(guid);
  }
  
  public org.tempuri.MessageResultOfAppApplication application(java.lang.String guid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.application(guid);
  }
  
  public org.tempuri.MessageResultOfListOfApplicationAttachmentDTO applicationAttachment(java.lang.String applicationGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.applicationAttachment(applicationGuid);
  }
  
  public org.tempuri.MessageResultOfListOfAppKeyValue applicationChannel(java.lang.String applicationGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.applicationChannel(applicationGuid);
  }
  
  public org.tempuri.MessageResultOfListOfAppKeyValue applicationCategory(java.lang.String applicationGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.applicationCategory(applicationGuid);
  }
  
  public org.tempuri.MessageResultOfListOfApplicationTimeSettingDTO listApplicationTimeSetting(java.lang.String domainGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listApplicationTimeSetting(domainGuid);
  }
  
  public org.tempuri.MessageResultOfListOfCreditsDTO listCredits() throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listCredits();
  }
  
  public org.tempuri.MessageResultOfAppApplication applicationAdd(org.tempuri.ApplicationAddItem add, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.applicationAdd(add, userGuid, token);
  }
  
  public org.tempuri.MessageResultOfAppVideo videoAddAllParam(java.lang.String addCreateUserGuid, java.lang.String addSpeaker, java.lang.String addName, java.lang.String addSummary, java.lang.String addDescription, java.lang.String addCategoryGuid, java.lang.String addGradeGuid, java.lang.String addLessonGuid, int addPeriod, java.util.Calendar addRectime, byte[] addCover, java.lang.String addFileName, double addFileSize, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.videoAddAllParam(addCreateUserGuid, addSpeaker, addName, addSummary, addDescription, addCategoryGuid, addGradeGuid, addLessonGuid, addPeriod, addRectime, addCover, addFileName, addFileSize, userGuid, token);
  }
  
  public org.tempuri.MessageResultOfAppVideo videoAdd(org.tempuri.VideoAddItem add, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.videoAdd(add, userGuid, token);
  }
  
  public org.tempuri.MessageResultOfVideoResourcePath videoResourcePath(java.lang.String videoGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.videoResourcePath(videoGuid);
  }
  
  public org.tempuri.MessageResultOfListOfAppKeyValue listMasterCategory() throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listMasterCategory();
  }
  
  public org.tempuri.MessageResultOfListOfAppKeyValue listSubCategory(java.lang.String categoryGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listSubCategory(categoryGuid);
  }
  
  public org.tempuri.MessageResultOfListOfAppKeyValue listCategory() throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listCategory();
  }
  
  public org.tempuri.MessageResultOfListOfAppKeyValue listChannel() throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listChannel();
  }
  
  public org.tempuri.MessageResultOfListOfAppKeyValue listMasterChannel() throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listMasterChannel();
  }
  
  public org.tempuri.MessageResultOfListOfAppKeyValue listSubChannel(java.lang.String channelGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listSubChannel(channelGuid);
  }
  
  public org.tempuri.MessageResultOfListOfAppKeyValue listMasterGrade() throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listMasterGrade();
  }
  
  public org.tempuri.MessageResultOfListOfAppKeyValue listSubGrade(java.lang.String gradeGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listSubGrade(gradeGuid);
  }
  
  public org.tempuri.MessageResultOfListOfAppKeyValue listGrade() throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listGrade();
  }
  
  public org.tempuri.MessageResultOfListOfAppKeyValue listPress() throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listPress();
  }
  
  public org.tempuri.MessageResultOfListOfAppKeyValue listAlbumLevel() throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listAlbumLevel();
  }
  
  public org.tempuri.MessageResultOfListOfAppKeyValue listDomain() throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listDomain();
  }
  
  public org.tempuri.MessageResultOfString applicationStartRecord(java.lang.String applicationGuid, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.applicationStartRecord(applicationGuid, userGuid, token);
  }
  
  public org.tempuri.MessageResultOfString applicationEndRecord(java.lang.String applicationGuid, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.applicationEndRecord(applicationGuid, userGuid, token);
  }
  
  public org.tempuri.MessageResultOfString applicationLiveUrl(java.lang.String applicationGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.applicationLiveUrl(applicationGuid);
  }
  
  public org.tempuri.MessageResultOfString applicationLiveUrlRTSP(java.lang.String applicationGuid, java.lang.String userGuid, java.lang.String token, java.lang.String privateKey) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.applicationLiveUrlRTSP(applicationGuid, userGuid, token, privateKey);
  }
  
  public org.tempuri.MessageResultOfString applicationLiveUrlRTMP(java.lang.String applicationGuid, java.lang.String userGuid, java.lang.String token, java.lang.String privateKey) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.applicationLiveUrlRTMP(applicationGuid, userGuid, token, privateKey);
  }
  
  public org.tempuri.MessageResultOfString recordEquipmentIPAVAASURL(java.lang.String ip, int progID) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.recordEquipmentIPAVAASURL(ip, progID);
  }
  
  public org.tempuri.MessageResultOfString recordEquipmentAVAASURL(java.lang.String recordEquipmentGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.recordEquipmentAVAASURL(recordEquipmentGuid);
  }
  
  public org.tempuri.MessageResultOfString recordEquipmentLiveURL(java.lang.String recordEquipmentGuid, java.lang.String userGuid, java.lang.String token, java.lang.String privateKey) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.recordEquipmentLiveURL(recordEquipmentGuid, userGuid, token, privateKey);
  }
  
  public org.tempuri.MessageResultOfString recordEquipmentStatus(java.lang.String recordEquipmentGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.recordEquipmentStatus(recordEquipmentGuid);
  }
  
  public org.tempuri.MessageResultOfString recordEquipmentSHUTDOWN(java.lang.String recordEquipmentGuid, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.recordEquipmentSHUTDOWN(recordEquipmentGuid, userGuid, token);
  }
  
  public org.tempuri.MessageResultOfString recordEquipmentSLEEP(java.lang.String recordEquipmentGuid, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.recordEquipmentSLEEP(recordEquipmentGuid, userGuid, token);
  }
  
  public org.tempuri.MessageResultOfString recordEquipmentWAKE_MACHINE(java.lang.String recordEquipmentGuid, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.recordEquipmentWAKE_MACHINE(recordEquipmentGuid, userGuid, token);
  }
  
  public org.tempuri.MessageResultOfString recordEquipmentStatusUpdate(java.lang.String recordEquipmentGuid, java.lang.String status, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.recordEquipmentStatusUpdate(recordEquipmentGuid, status, userGuid, token);
  }
  
  public org.tempuri.MessageResultOfString recordEquipmentLiveURLUpdate(java.lang.String recordEquipmentGuid, java.lang.String url, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.recordEquipmentLiveURLUpdate(recordEquipmentGuid, url, userGuid, token);
  }
  
  public org.tempuri.MessageResultOfUserDTO userLogin(java.lang.String domainGuid, java.lang.String username, java.lang.String password) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.userLogin(domainGuid, username, password);
  }
  
  public org.tempuri.MessageResultOfUserDTO userLoginLocal(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.userLoginLocal(username, password);
  }
  
  public org.tempuri.MessageResultOfListOfAppUser listUser(org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize, int orderBy, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.listUser(param, pageIndex, pageSize, orderBy, userGuid, token);
  }
  
  public org.tempuri.MessageResultOfListOfAppKeyValue userCategory(java.lang.String userGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.userCategory(userGuid);
  }
  
  public org.tempuri.MessageResultOfAppUser loginUser(java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.loginUser(userGuid, token);
  }
  
  public org.tempuri.MessageResultOfListOfAppPV PVUser(java.lang.String userGuid, java.lang.String pvTarget) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.PVUser(userGuid, pvTarget);
  }
  
  public org.tempuri.MessageResultOfAppPV PVAdd(java.lang.String userGuid, int pvType, java.lang.String pvTarget, java.lang.String pvGuid, java.lang.String token) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.PVAdd(userGuid, pvType, pvTarget, pvGuid, token);
  }
  
  public org.tempuri.MessageResultOfString PVDelete(java.lang.String userGuid, java.lang.String guid, java.lang.String token) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.PVDelete(userGuid, guid, token);
  }
  
  public org.tempuri.MessageResultOfString PVHas(java.lang.String userGuid, int pvType, java.lang.String pvTarget, java.lang.String pvGuid) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.PVHas(userGuid, pvType, pvTarget, pvGuid);
  }
  
  public org.tempuri.MessageResultOfListOfAppKeyValue appSetting() throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.appSetting();
  }
  
  public org.tempuri.MessageResultOfCommentDTO commentAdd(java.lang.String userGuid, java.lang.String targetType, java.lang.String targetGuid, java.lang.String title, java.lang.String commentText, java.lang.String token) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.commentAdd(userGuid, targetType, targetGuid, title, commentText, token);
  }
  
  public org.tempuri.MessageResultOfString getUploadType(java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.getUploadType(userGuid, token);
  }
  
  
}