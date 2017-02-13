
package com.haoyu.aip.qti.entity;

/**
 * Encapsulates how an {@link QuestionPackage} was imported.
 *
 * @see QuestionPackage
 *
 * @author lianghuahuang
 */
public enum PackageImportType {


    /** Uploaded as an IMS Content Package */
    CONTENT_PACKAGE,

    /** Uploaded as a standalone assessmentItem XML file */
    STANDALONE_ITEM_XML,

    /** QTI sample bundled into the Engine */
    BUNDLED_SAMPLE,

    ;

}
