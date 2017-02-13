/**
 * WebServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface WebServiceSoap extends java.rmi.Remote {

    /**
     * 所有接口调用方法可以查看“参数说明”和“返回说明”，“参数说明”中的参数说明中带有“Guid”字样说明是外键，可以根据接口中的相应List方法取得。</br>有Token参数的必须要先进行登录(UserLogin)，在返回的Data.Token中
     */
    public java.lang.String AAA() throws java.rmi.RemoteException;
    public java.lang.String helloAVA() throws java.rmi.RemoteException;

    /**
     * 所有方法的标准返回请参考当前说明</br><a href='Help/About.xml'>参数说明</a></br><a
     * href='Help/AboutResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfString about(java.lang.String name) throws java.rmi.RemoteException;
    public java.lang.String helloWorld(java.lang.String name) throws java.rmi.RemoteException;

    /**
     * 抛起一个错误，可以进行错误测试
     */
    public org.tempuri.MessageResultOfString throwError() throws java.rmi.RemoteException;

    /**
     * 分页获取点播视频列表</br><a href='Help/ListVideo.xml'>参数说明</a></br><a
     * href='Help/ListVideoResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppVideoForXIAN listVideoForXIAN(org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize, int orderBy) throws java.rmi.RemoteException;

    /**
     * 分页获取点播视频列表</br><a href='Help/ListVideo.xml'>参数说明</a></br><a
     * href='Help/ListVideoResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppVideo listVideo(org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize, int orderBy) throws java.rmi.RemoteException;

    /**
     * 分页获取课本列表</br><a href='Help/ListTextbook.xml'>参数说明</a></br><a
     * href='Help/ListTextbookResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppTextbook listTextbook(org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize, int orderBy) throws java.rmi.RemoteException;

    /**
     * 分页获取课例专辑列表</br><a href='Help/ListAlbum.xml'>参数说明</a></br><a
     * href='Help/ListAlbumResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppAlbum listAlbum(org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize, int orderBy) throws java.rmi.RemoteException;

    /**
     * 分页获取优课评比列表</br><a href='Help/ListSubject.xml'>参数说明</a></br><a
     * href='Help/ListSubjectResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppSubject listSubject(org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize, int orderBy) throws java.rmi.RemoteException;

    /**
     * 分页获取教研互动列表</br><a href='Help/ListMeeting.xml'>参数说明</a></br><a
     * href='Help/ListMeetingResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppMeeting listMeeting(org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize, int orderBy) throws java.rmi.RemoteException;

    /**
     * 分页获取预约录制列表</br><a href='Help/ListApplication.xml'>参数说明</a></br><a
     * href='Help/ListApplicationResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppApplication listApplication(org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize, int orderBy) throws java.rmi.RemoteException;

    /**
     * 分页获取录播课室列表</br><a href='Help/ListRecordEquipment.xml'>参数说明</a></br><a
     * href='Help/ListRecordEquipmentResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppRecordEquipment listRecordEquipment(org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize, int orderBy) throws java.rmi.RemoteException;

    /**
     * 分页获取最近直播的录播课室</br><a href='Help/ListRoom.xml'>参数说明</a></br><a
     * href='Help/ListRoomResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppApplication listRoom(org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize, int orderBy) throws java.rmi.RemoteException;

    /**
     * 分页获取评论列表2秒缓存</br><a href='Help/ListComment.xml'>参数说明</a></br><a
     * href='Help/ListCommentResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfCommentDTO listComment(java.lang.String targetType, java.lang.String targetGuid, org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize) throws java.rmi.RemoteException;

    /**
     * 视频信息5秒缓存<br /><a href='Help/Video.xml'>参数说明</a><br /><a href='Help/VideoResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfAppVideo video(java.lang.String guid) throws java.rmi.RemoteException;

    /**
     * 视频附件列表5秒缓存<br /><a href='Help/VideoAttachment.xml'>参数说明</a><br
     * /><a href='Help/VideoAttachmentResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfVideoAttachmentDTO videoAttachment(java.lang.String videoGuid) throws java.rmi.RemoteException;

    /**
     * 视频课程列表5秒缓存<br /><a href='Help/VideoChannel.xml'>参数说明</a><br
     * /><a href='Help/VideoChannelResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppKeyValue videoChannel(java.lang.String videoGuid) throws java.rmi.RemoteException;

    /**
     * 视频教学环节列表5秒缓存<br /><a href='Help/VideoSW.xml'>参数说明</a><br /><a
     * href='Help/VideoSWResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfVideoSWDTO videoSW(java.lang.String videoGuid) throws java.rmi.RemoteException;

    /**
     * 视频教学行为列表5秒缓存<br /><a href='Help/VideoSWPattern.xml'>参数说明</a><br
     * /><a href='Help/VideoSWPatternResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfVideoSWPatternDTO videoSWPattern(java.lang.String videoGuid) throws java.rmi.RemoteException;

    /**
     * 视频标签列表5秒缓存<br /><a href='Help/VideoTag.xml'>参数说明</a><br /><a
     * href='Help/VideoTagResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfVideoTagDTO videoTag(java.lang.String videoGuid) throws java.rmi.RemoteException;

    /**
     * 视频知识点列表5秒缓存<br /><a href='Help/VideoTimeInfo.xml'>参数说明</a><br
     * /><a href='Help/VideoTimeInfoResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfVideoTimeInfoDTO videoTimeInfo(java.lang.String videoGuid) throws java.rmi.RemoteException;

    /**
     * 视频精彩点评列表2秒缓存<br /><a href='Help/VideoTimeInfoOther.xml'>参数说明</a><br
     * /><a href='Help/VideoTimeInfoOtherResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfVideoTimeInfoOtherDTO videoTimeInfoOther(java.lang.String videoGuid) throws java.rmi.RemoteException;

    /**
     * 单元课列表5秒缓存</br><a href='Help/ListTextbookLesson.xml'>参数说明</a></br><a
     * href='Help/ListTextbookLessonResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfLessonDTO listTextbookLesson(java.lang.String textbookGuid) throws java.rmi.RemoteException;

    /**
     * 专辑信息5秒缓存<br /><a href='Help/Album.xml'>参数说明</a><br /><a href='Help/AlbumResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfAppAlbum album(java.lang.String guid) throws java.rmi.RemoteException;

    /**
     * 专辑视频列表5秒缓存<br /><a href='Help/AlbumVideo.xml'>参数说明</a><br /><a
     * href='Help/AlbumVideoResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppVideo albumVideo(java.lang.String guid) throws java.rmi.RemoteException;

    /**
     * 预约信息5秒缓存<br /><a href='Help/Application.xml'>参数说明</a><br /><a
     * href='Help/ApplicationResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfAppApplication application(java.lang.String guid) throws java.rmi.RemoteException;

    /**
     * 直播附件列表5秒缓存<br /><a href='Help/ApplicationAttachment.xml'>参数说明</a><br
     * /><a href='Help/ApplicationAttachmentResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfApplicationAttachmentDTO applicationAttachment(java.lang.String applicationGuid) throws java.rmi.RemoteException;

    /**
     * 直播关联课程列表5秒缓存<br /><a href='Help/ApplicationChannel.xml'>参数说明</a><br
     * /><a href='Help/ApplicationChannelResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppKeyValue applicationChannel(java.lang.String applicationGuid) throws java.rmi.RemoteException;

    /**
     * 直播关联学科列表5秒缓存<br /><a href='Help/ApplicationCategory.xml'>参数说明</a><br
     * /><a href='Help/ApplicationCategoryResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppKeyValue applicationCategory(java.lang.String applicationGuid) throws java.rmi.RemoteException;

    /**
     * 取得关联平台的直播时间表5秒缓存<br /><a href='Help/ListApplicationTimeSetting.xml'>参数说明</a><br
     * /><a href='Help/ListApplicationTimeSettingResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfApplicationTimeSettingDTO listApplicationTimeSetting(java.lang.String domainGuid) throws java.rmi.RemoteException;

    /**
     * 取得关联片头片尾设置5秒缓存<br /><a href='Help/ListCredits.xml'>参数说明</a><br
     * /><a href='Help/ListCreditsResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfCreditsDTO listCredits() throws java.rmi.RemoteException;

    /**
     * 申请预约<br /><a href='Help/ApplicationAdd.xml'>参数说明</a><br /><a
     * href='Help/ApplicationAddResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfAppApplication applicationAdd(org.tempuri.ApplicationAddItem add, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException;

    /**
     * 提交视频信息，参数为所有字段<br /><a href='Help/VideoAdd.xml'>参数说明</a><br
     * /><a href='Help/VideoAddResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfAppVideo videoAddAllParam(java.lang.String addCreateUserGuid, java.lang.String addSpeaker, java.lang.String addName, java.lang.String addSummary, java.lang.String addDescription, java.lang.String addCategoryGuid, java.lang.String addGradeGuid, java.lang.String addLessonGuid, int addPeriod, java.util.Calendar addRectime, byte[] addCover, java.lang.String addFileName, double addFileSize, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException;

    /**
     * 提交视频信息<br /><a href='Help/VideoAdd.xml'>参数说明</a><br /><a href='Help/VideoAddResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfAppVideo videoAdd(org.tempuri.VideoAddItem add, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException;

    /**
     * 取得视频资源地址<br /><a href='Help/VideoResourcePath.xml'>参数说明</a><br
     * /><a href='Help/VideoResourcePathResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfVideoResourcePath videoResourcePath(java.lang.String videoGuid) throws java.rmi.RemoteException;

    /**
     * 取得学科父级列表5秒缓存</br><a href='Help/ListMasterCategory.xml'>参数说明</a></br><a
     * href='Help/ListMasterCategoryResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppKeyValue listMasterCategory() throws java.rmi.RemoteException;

    /**
     * 取得学科子级列表5秒缓存</br><a href='Help/ListSubCategory.xml'>参数说明</a></br><a
     * href='Help/ListSubCategoryResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppKeyValue listSubCategory(java.lang.String categoryGuid) throws java.rmi.RemoteException;

    /**
     * 取得学科列表5秒缓存</br><a href='Help/ListCategory.xml'>参数说明</a></br><a
     * href='Help/ListCategoryResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppKeyValue listCategory() throws java.rmi.RemoteException;

    /**
     * 取得课程列表5秒缓存</br><a href='Help/ListChannel.xml'>参数说明</a></br><a
     * href='Help/ListChannelResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppKeyValue listChannel() throws java.rmi.RemoteException;

    /**
     * 取得课程父级列表5秒缓存</br><a href='Help/ListMasterChannel.xml'>参数说明</a></br><a
     * href='Help/ListMasterChannelResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppKeyValue listMasterChannel() throws java.rmi.RemoteException;

    /**
     * 取得课程子级列表5秒缓存</br><a href='Help/ListSubChannel.xml'>参数说明</a></br><a
     * href='Help/ListSubChannelResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppKeyValue listSubChannel(java.lang.String channelGuid) throws java.rmi.RemoteException;

    /**
     * 取得年级父级列表5秒缓存</br><a href='Help/ListMasterGrade.xml'>参数说明</a></br><a
     * href='Help/ListMasterGradeResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppKeyValue listMasterGrade() throws java.rmi.RemoteException;

    /**
     * 取得年级子级列表5秒缓存</br><a href='Help/ListSubGrade.xml'>参数说明</a></br><a
     * href='Help/ListSubGradeResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppKeyValue listSubGrade(java.lang.String gradeGuid) throws java.rmi.RemoteException;

    /**
     * 取得年级列表5秒缓存</br><a href='Help/ListGrade.xml'>参数说明</a></br><a
     * href='Help/ListGradeResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppKeyValue listGrade() throws java.rmi.RemoteException;

    /**
     * 取得出版社列表5秒缓存</br><a href='Help/ListPress.xml'>参数说明</a></br><a
     * href='Help/ListPressResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppKeyValue listPress() throws java.rmi.RemoteException;

    /**
     * 取得专辑级别5秒缓存</br><a href='Help/ListAlbumLevel.xml'>参数说明</a></br><a
     * href='Help/ListAlbumLevelResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppKeyValue listAlbumLevel() throws java.rmi.RemoteException;

    /**
     * 取得平台列表5秒缓存</br><a href='Help/ListDomain.xml'>参数说明</a></br><a
     * href='Help/ListDomainResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppKeyValue listDomain() throws java.rmi.RemoteException;

    /**
     * 预约启动录制<br /><a href='Help/ApplicationStartRecord.xml'>参数说明</a><br
     * /><a href='Help/ApplicationStartRecordResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfString applicationStartRecord(java.lang.String applicationGuid, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException;

    /**
     * 预约停止录制<br /><a href='Help/ApplicationEndRecord.xml'>参数说明</a><br
     * /><a href='Help/ApplicationEndRecordResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfString applicationEndRecord(java.lang.String applicationGuid, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException;

    /**
     * 预约转播地址<br /><a href='Help/ApplicationLiveUrl.xml'>参数说明</a><br
     * /><a href='Help/ApplicationLiveUrlResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfString applicationLiveUrl(java.lang.String applicationGuid) throws java.rmi.RemoteException;

    /**
     * 预约RTSP直播地址<br /><a href='Help/ApplicationLiveUrlRTSP.xml'>参数说明</a><br
     * /><a href='Help/ApplicationLiveUrlRTSPResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfString applicationLiveUrlRTSP(java.lang.String applicationGuid, java.lang.String userGuid, java.lang.String token, java.lang.String privateKey) throws java.rmi.RemoteException;

    /**
     * 预约RTMP直播地址<br /><a href='Help/ApplicationLiveUrlRTMP.xml'>参数说明</a><br
     * /><a href='Help/ApplicationLiveUrlRTMPResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfString applicationLiveUrlRTMP(java.lang.String applicationGuid, java.lang.String userGuid, java.lang.String token, java.lang.String privateKey) throws java.rmi.RemoteException;

    /**
     * 课室根据IP转播地址<br /><a href='Help/RecordEquipmentIPAVAASURL.xml'>参数说明</a><br
     * /><a href='Help/RecordEquipmentIPAVAASURLResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfString recordEquipmentIPAVAASURL(java.lang.String ip, int progID) throws java.rmi.RemoteException;

    /**
     * 课室转播地址<br /><a href='Help/RecordEquipmentAVAASURL.xml'>参数说明</a><br
     * /><a href='Help/RecordEquipmentAVAASURLResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfString recordEquipmentAVAASURL(java.lang.String recordEquipmentGuid) throws java.rmi.RemoteException;

    /**
     * 课室直播地址<br /><a href='Help/RecordEquipmentLiveURL.xml'>参数说明</a><br
     * /><a href='Help/RecordEquipmentLiveURLResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfString recordEquipmentLiveURL(java.lang.String recordEquipmentGuid, java.lang.String userGuid, java.lang.String token, java.lang.String privateKey) throws java.rmi.RemoteException;

    /**
     * 取得录播课室状态<br /><a href='Help/RecordEquipmentStatus.xml'>参数说明</a><br
     * /><a href='Help/RecordEquipmentStatusResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfString recordEquipmentStatus(java.lang.String recordEquipmentGuid) throws java.rmi.RemoteException;

    /**
     * 录播课室关机<br /><a href='Help/RecordEquipmentSHUTDOWN.xml'>参数说明</a><br
     * /><a href='Help/RecordEquipmentSHUTDOWNResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfString recordEquipmentSHUTDOWN(java.lang.String recordEquipmentGuid, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException;

    /**
     * 录播课室休眠<br /><a href='Help/RecordEquipmentSLEEP.xml'>参数说明</a><br
     * /><a href='Help/RecordEquipmentSLEEPResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfString recordEquipmentSLEEP(java.lang.String recordEquipmentGuid, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException;

    /**
     * 录播课室开机<br /><a href='Help/RecordEquipmentWAKE_MACHINE.xml'>参数说明</a><br
     * /><a href='Help/RecordEquipmentWAKE_MACHINEResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfString recordEquipmentWAKE_MACHINE(java.lang.String recordEquipmentGuid, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException;

    /**
     * 更新录播状态 成功返回：1<br /><a href='Help/RecordEquipmentStatusUpdate.xml'>参数说明</a><br
     * /><a href='Help/RecordEquipmentStatusUpdateResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfString recordEquipmentStatusUpdate(java.lang.String recordEquipmentGuid, java.lang.String status, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException;

    /**
     * 更新录播状态 成功返回：1<br /><a href='Help/RecordEquipmentStatusUpdate.xml'>参数说明</a><br
     * /><a href='Help/RecordEquipmentStatusUpdateResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfString recordEquipmentLiveURLUpdate(java.lang.String recordEquipmentGuid, java.lang.String url, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException;

    /**
     * 用户登录：令牌在返回数据中的Token中<br /><a href='Help/UserLogin.xml'>参数说明</a><br
     * /><a href='Help/UserLoginResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfUserDTO userLogin(java.lang.String domainGuid, java.lang.String username, java.lang.String password) throws java.rmi.RemoteException;

    /**
     * 用户登录(默认平台)：令牌在返回数据中的Token中<br /><a href='Help/UserLogin.xml'>参数说明</a><br
     * /><a href='Help/UserLoginResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfUserDTO userLoginLocal(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException;

    /**
     * 分页获取用户列表（暂无返回数据）</br><a href='Help/ListUser.xml'>参数说明</a></br><a
     * href='Help/ListUserResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppUser listUser(org.tempuri.AppKeyValue[] param, int pageIndex, int pageSize, int orderBy, java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException;

    /**
     * 取得用户关联学科</br><a href='Help/UserCategory.xml'>参数说明</a></br><a
     * href='Help/UserCategoryResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppKeyValue userCategory(java.lang.String userGuid) throws java.rmi.RemoteException;

    /**
     * 取得登录用户信息</br><a href='Help/LoginUser.xml'>参数说明</a></br><a href='Help/LoginUserResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfAppUser loginUser(java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException;

    /**
     * 用户收藏</br><a href='Help/PVUser.xml'>参数说明</a></br><a href='Help/PVUserResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppPV PVUser(java.lang.String userGuid, java.lang.String pvTarget) throws java.rmi.RemoteException;

    /**
     * 收藏添加</br><a href='Help/PVAdd.xml'>参数说明</a></br><a href='Help/PVAddResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfAppPV PVAdd(java.lang.String userGuid, int pvType, java.lang.String pvTarget, java.lang.String pvGuid, java.lang.String token) throws java.rmi.RemoteException;

    /**
     * 收藏删除</br><a href='Help/PVAdd.xml'>参数说明</a></br><a href='Help/PVAddResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfString PVDelete(java.lang.String userGuid, java.lang.String guid, java.lang.String token) throws java.rmi.RemoteException;

    /**
     * 用户是否收藏</br><a href='Help/PVAdd.xml'>参数说明</a></br><a href='Help/PVAddResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfString PVHas(java.lang.String userGuid, int pvType, java.lang.String pvTarget, java.lang.String pvGuid) throws java.rmi.RemoteException;

    /**
     * App信息<br /><a href='Help/AppSetting.xml'>参数说明</a><br /><a href='Help/AppSettingResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfListOfAppKeyValue appSetting() throws java.rmi.RemoteException;

    /**
     * 添加评论</br><a href='Help/CommentAdd.xml'>参数说明</a></br><a href='Help/CommentAddResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfCommentDTO commentAdd(java.lang.String userGuid, java.lang.String targetType, java.lang.String targetGuid, java.lang.String title, java.lang.String commentText, java.lang.String token) throws java.rmi.RemoteException;

    /**
     * 获取允许上传的文件类型</br><a href='Help/CommentAdd.xml'>参数说明</a></br><a
     * href='Help/GetUploadTypeResult.xml'>返回说明</a>
     */
    public org.tempuri.MessageResultOfString getUploadType(java.lang.String userGuid, java.lang.String token) throws java.rmi.RemoteException;
}
