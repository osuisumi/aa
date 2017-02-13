/* Copyright (c) 2012-2013, University of Edinburgh.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice, this
 *   list of conditions and the following disclaimer in the documentation and/or
 *   other materials provided with the distribution.
 *
 * * Neither the name of the University of Edinburgh nor the names of its
 *   contributors may be used to endorse or promote products derived from this
 *   software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 * This software is derived from (and contains code from) QTItools and MathAssessEngine.
 * QTItools is (c) 2008, University of Southampton.
 * MathAssessEngine is (c) 2010, University of Edinburgh.
 */
package com.haoyu.aip.qti.service.impl;


import uk.ac.ed.ph.jqtiplus.internal.util.Assert;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.haoyu.aip.qti.entity.Test;
import com.haoyu.aip.qti.entity.TestDelivery;
import com.haoyu.aip.qti.entity.TestDeliveryUser;
import com.haoyu.aip.qti.entity.TestPackage;
import com.haoyu.aip.qti.exception.QtiLogicException;
import com.haoyu.sip.core.entity.User;

/**
 * Service to manage the creation and deletion of filespaces/sandboxes
 * for storing things like uploaded {@link TestPackage}s and submitted files.
 * <p>
 * This is NO authorisation at this level.
 *
 * @author David McKain
 */
@Service
public final class FilespaceManager {

    private static final Logger logger = LoggerFactory.getLogger(FilespaceManager.class);
    
    @Value("#{commonConfig['qti.file.system.base']}")
    private String filesystemBaseString;
    
    private File filesystemBaseDirectory;

    @PostConstruct
    public void init() {
        logger.info("Filesystem base for client data is {}", filesystemBaseString);
        this.filesystemBaseDirectory = new File(filesystemBaseString);
    }

    public File createTempFile() {
        final String tmpFolderUri = filesystemBaseDirectory.toURI().toString()
                + "/tmp";
        final File tmpFolder = ensureCreateDirectory(tmpFolderUri);
        return new File(tmpFolder, createUniqueRequestComponent());
    }

    //-------------------------------------------------

    public File createTestPackageSandbox(final User owner) {
        Assert.notNull(owner, "owner");
        final String filespaceUri = getTestPackageSandboxBaseUri(owner)
                + "/" + createUniqueRequestComponent();
        return ensureCreateDirectory(filespaceUri);
    }

    public boolean deleteTestPackageSandbox(final TestPackage testPackage) {
        if (testPackage.getSandboxPath()==null) {
            throw new IllegalStateException("Built-in TestPackages may not be deleted");
        }
        return recursivelyDeleteDirectory(new File(testPackage.getSandboxPath()));
    }

    public boolean deleteTestPackageSandboxes(final User owner) {
        Assert.notNull(owner, "owner");
        return recursivelyDeleteDirectory(getTestPackageSandboxBaseUri(owner));
    }

    public boolean deleteAllTestPackages() {
        return recursivelyDeleteDirectory(getTestPackageSandboxBaseUri());
    }

    private String getTestPackageSandboxBaseUri(final User owner) {
        Assert.notNull(owner, "owner");
        return getTestPackageSandboxBaseUri()
            + "/" + owner.getId();
    }

    private String getTestPackageSandboxBaseUri() {
        return filesystemBaseDirectory.toURI().toString()
                + "/tests";
    }

    //-------------------------------------------------

    public File createCandidateUploadFile(final TestDeliveryUser testDeliveryUser) {
        Assert.notNull(testDeliveryUser, "testDeliveryUser");
        final String uploadBaseUri = getTestDeliveryUserUploadBaseUri(testDeliveryUser);
        final File candidateResponseFolder = ensureCreateDirectory(uploadBaseUri);
        return new File(candidateResponseFolder, createUniqueRequestComponent());
    }

    public boolean deleteCandidateUploads(final TestDelivery delivery) {
        Assert.notNull(delivery, "delivery");
        return recursivelyDeleteDirectory(getTestDeliveryUserUploadBaseUri(delivery));
    }

    public boolean deleteCandidateUploads(final TestDeliveryUser testDeliveryUser) {
        Assert.notNull(testDeliveryUser, "testDeliveryUser");
        return recursivelyDeleteDirectory(getTestDeliveryUserUploadBaseUri(testDeliveryUser));
    }

    public boolean deleteAllCandidateUploads() {
        return recursivelyDeleteDirectory(getCandidateUploadBaseUri());
    }

    private String getCandidateUploadBaseUri() {
        return filesystemBaseDirectory.toURI().toString()
                + "/responses";
    }

    private String getTestDeliveryUserUploadBaseUri(final TestDelivery delivery) {
        final Test assessment = delivery.getTest();

        final String folderUri = getCandidateUploadBaseUri()
                + "/assessment" + assessment.getId()
                + "/delivery" + delivery.getId();
        return folderUri;
    }

    private String getTestDeliveryUserUploadBaseUri(final TestDeliveryUser testDeliveryUser) {
        final User candidate = testDeliveryUser.getDeliveryUser();
        final TestDelivery delivery = testDeliveryUser.getTestDelivery();

        final String folderUri = getTestDeliveryUserUploadBaseUri(delivery)
                + "/" + candidate.getId()
                + "/session" + testDeliveryUser.getId();
        return folderUri;
    }

    //-------------------------------------------------

    public File obtainTestDeliveryUserStateStore(final TestDeliveryUser testDeliveryUser) {
        Assert.notNull(testDeliveryUser, "testDeliveryUser");
        return ensureCreateDirectory(getTestDeliveryUserStoreUri(testDeliveryUser));
    }

    public boolean deleteTestDeliveryUserData(final TestDelivery delivery) {
        Assert.notNull(delivery, "delivery");
        return recursivelyDeleteDirectory(getTestDeliveryUserStoreBaseUri(delivery));
    }

    public boolean deleteTestDeliveryUserStore(final TestDeliveryUser testDeliveryUser) {
        Assert.notNull(testDeliveryUser, "testDeliveryUser");
        return recursivelyDeleteDirectory(getTestDeliveryUserStoreUri(testDeliveryUser));
    }

    public boolean deleteAllTestDeliveryUserData() {
        return recursivelyDeleteDirectory(getTestDeliveryUserStoreBaseUri());
    }

    private final String getTestDeliveryUserStoreBaseUri() {
        return filesystemBaseDirectory.toURI().toString()
                + "/sessions";
    }

    private final String getTestDeliveryUserStoreBaseUri(final TestDelivery delivery) {
        final Test assessment = delivery.getTest();
        final String folderBaseUri = getTestDeliveryUserStoreBaseUri()
                + "/assessment" + assessment.getId()
                + "/delivery" + delivery.getId();
        return folderBaseUri;
    }

    private final String getTestDeliveryUserStoreUri(final TestDeliveryUser testDeliveryUser) {
        final User candidate = testDeliveryUser.getDeliveryUser();
        final TestDelivery delivery = testDeliveryUser.getTestDelivery();
        final String folderUri = getTestDeliveryUserStoreBaseUri(delivery)
                + "/" + candidate.getId()
                + "/session" + testDeliveryUser.getId();
        return folderUri;
    }

    //-------------------------------------------------

    /**
     * Deletes all assignment and candidate data from the system.
     * <p>
     * This corresponds to having the database schema wiped.
     * Use with caution!!!
     */
    public void deleteAllUserData() {
        deleteAllTestPackages();
        deleteAllTestDeliveryUserData();
        deleteAllCandidateUploads();
    }

    //-------------------------------------------------


    public void deleteSandbox(final File sandboxDirectory) {
        Assert.notNull(sandboxDirectory, "sandboxDirectory");
        recursivelyDeleteDirectory(sandboxDirectory);
    }

    //-------------------------------------------------

    private final File ensureCreateDirectory(final String fileUri) {
        final File directory;
        try {
            directory = new File(URI.create(fileUri));
            return ServiceUtilities.ensureDirectoryCreated(directory);
        }
        catch (final RuntimeException e) {
            throw new QtiLogicException("Unexpected failure parsing File URI " + fileUri);
        }
    }

    private final boolean recursivelyDeleteDirectory(final String fileUri) {
        return recursivelyDeleteDirectory(fileUriToFile(fileUri));
    }

    private final boolean recursivelyDeleteDirectory(final File directory) {
        if (directory.exists()) {
            /* Do sanity check */
            if (!directory.isDirectory()) {
                throw new QtiLogicException("Expected " + directory.getAbsolutePath() + " to be a directory");
            }
            ServiceUtilities.recursivelyDelete(directory);
        }
        return true;
    }

    private File fileUriToFile(final String fileUri) {
        try {
            return new File(URI.create(fileUri));
        }
        catch (final RuntimeException e) {
            throw new QtiLogicException("Unexpected failure parsing File URI " + fileUri);
        }
    }

    private String createUniqueRequestComponent() {
     //   final Date timestamp = requestTimestampContext.getCurrentRequestTimestamp();
        final long threadId = Thread.currentThread().getId();
        return new SimpleDateFormat("yyyyMMdd-HHmmssSSS").format(new Date()) + "-" + String.valueOf(threadId);
    }
}
