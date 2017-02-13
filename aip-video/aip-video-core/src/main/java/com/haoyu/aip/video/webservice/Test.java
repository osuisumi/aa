package com.haoyu.aip.video.webservice;

import java.rmi.RemoteException;

import org.tempuri.AppAlbum;
import org.tempuri.AppKeyValue;
import org.tempuri.AppTextbook;
import org.tempuri.AppVideo;
import org.tempuri.MessageResultOfListOfAppAlbum;
import org.tempuri.MessageResultOfListOfAppKeyValue;
import org.tempuri.MessageResultOfListOfAppTextbook;
import org.tempuri.MessageResultOfListOfAppVideo;
import org.tempuri.MessageResultOfString;
import org.tempuri.MessageResultOfVideoResourcePath;
import org.tempuri.WebServiceSoapProxy;

public class Test {

	public static void main(String[] args) {
        WebServiceSoapProxy proxy = new WebServiceSoapProxy();
        try {
        	MessageResultOfListOfAppVideo result = proxy.listVideo(null, 1, 100, 0);
        	System.out.println(result.getData().length);
        	for (AppVideo video : result.getData()) {
				System.out.println(video.getName());
        		
        		MessageResultOfVideoResourcePath string = proxy.videoResourcePath(video.getGuid());
            	System.out.println(string.getData().getHD());
            	System.out.println(string.getData().getNR());
			}
//        	MessageResultOfListOfAppAlbum result = proxy.listAlbum(null, 1, 50, 0);
//        	for (AppAlbum vo : result.getData()) {
//				System.out.println(vo.getName());
//			}
//        	MessageResultOfListOfAppTextbook result = proxy.listTextbook(null, 1, 50, 0);
//        	for (AppTextbook vo : result.getData()) {
//				System.out.println(vo.getName());
//			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
