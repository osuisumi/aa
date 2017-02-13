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

import uk.ac.ed.ph.jqtiplus.JqtiExtensionManager;
import uk.ac.ed.ph.jqtiplus.JqtiExtensionPackage;
import uk.ac.ed.ph.jqtiplus.node.AssessmentObject;
import uk.ac.ed.ph.jqtiplus.node.item.AssessmentItem;
import uk.ac.ed.ph.jqtiplus.node.test.AssessmentTest;
import uk.ac.ed.ph.jqtiplus.reading.AssessmentObjectXmlLoader;
import uk.ac.ed.ph.jqtiplus.reading.QtiObjectReader;
import uk.ac.ed.ph.jqtiplus.reading.QtiXmlReader;
import uk.ac.ed.ph.jqtiplus.resolution.ResolvedAssessmentObject;
import uk.ac.ed.ph.jqtiplus.utils.contentpackaging.QtiContentPackageExtractor;
import uk.ac.ed.ph.jqtiplus.validation.AssessmentObjectValidationResult;
import uk.ac.ed.ph.jqtiplus.xmlutils.CustomUriScheme;
import uk.ac.ed.ph.jqtiplus.xmlutils.SimpleSchemaCache;
import uk.ac.ed.ph.jqtiplus.xmlutils.XmlReadResult;
import uk.ac.ed.ph.jqtiplus.xmlutils.XmlResourceNotFoundException;
import uk.ac.ed.ph.jqtiplus.xmlutils.locators.ChainedResourceLocator;
import uk.ac.ed.ph.jqtiplus.xmlutils.locators.FileSandboxResourceLocator;
import uk.ac.ed.ph.jqtiplus.xmlutils.locators.NetworkHttpResourceLocator;
import uk.ac.ed.ph.jqtiplus.xmlutils.locators.ResourceLocator;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.w3c.dom.Document;

import com.haoyu.aip.qti.entity.TestPackage;
import com.haoyu.aip.qti.exception.QtiLogicException;

/**
 * Provides read-only access (and related services) to {@link TestPackage}
 * files.
 * <p>
 * This is NO authorisation at this level.
 *
 * @author David McKain
 */
@Service
public class TestPackageFileService {

	private static final Logger logger = LoggerFactory
			.getLogger(TestPackageFileService.class);


//	@Resource
	private QtiXmlReader qtiXmlReader=new QtiXmlReader(jqtiExtensionManager(), new SimpleSchemaCache());;

	@Resource
	private FilespaceManager filespaceManager;



	// -------------------------------------------------

	/**
	 * Attempts to extract the title from an {@link AssessmentItem} or
	 * {@link AssessmentTest} for bootstrapping the initial state of the
	 * resulting {@link TestPackage}.
	 * <p>
	 * This performs a low level XML parse to save time; proper read/validation
	 * using JQTI+ is expected to happen later on.
	 *
	 * @param assessmentPackage
	 * @return extracted title, or an empty String if nothing could be
	 *         extracted.
	 */
	public String extractAssessmentTitle(final TestPackage assessmentPackage) {
		Assert.notNull(assessmentPackage, "assessmentPackage");
		final ResourceLocator inputResourceLocator = createResolvingResourceLocator(assessmentPackage);
		final URI assessmentSystemId = createAssessmentObjectUri(assessmentPackage);
		XmlReadResult xmlReadResult;
		try {
			xmlReadResult = qtiXmlReader.read(inputResourceLocator,
					assessmentSystemId, false);
		} catch (final XmlResourceNotFoundException e) {
			throw new QtiLogicException(
					"Assessment resource missing for package "
							+ assessmentPackage, e);
		}
		/*
		 * Let's simply extract the title attribute from the document element,
		 * and not worry about anything else at this point.
		 */
		final Document document = xmlReadResult.getDocument();
		return document != null ? document.getDocumentElement().getAttribute(
				"title") : "";
	}

	// -------------------------------------------------

	/**
	 * Creates a {@link ResourceLocator} for the given {@link TestPackage} that
	 * is capable only of reading in files within the package.
	 * <p>
	 * Do NOT use this for parsing (e.g. via {@link QtiObjectReader}), as it
	 * won't be able to resolve references.
	 */
	public ResourceLocator createPackageFileResourceLocator(
			final TestPackage testPackage) {
		/*
		 * Uploaded by user, so resource lives in a sandbox within the
		 * filesystem
		 */
		final File sandboxDirectory = new File(testPackage.getSandboxPath());
		final CustomUriScheme packageUriScheme = QtiContentPackageExtractor.PACKAGE_URI_SCHEME;
		final ResourceLocator result = new FileSandboxResourceLocator(packageUriScheme,
				sandboxDirectory);
		return result;
	}

	/**
	 * Creates a {@link ResourceLocator} for reading in and resolving the
	 * resources associated with the given {@link TestPackage} using a
	 * {@link QtiObjectReader}.
	 * <p>
	 * For an {@link TestPackage} uploaded by a user, the resulting
	 * {@link ResourceLocator} will be restricted to the package's sandbox, plus
	 * bundled parser resources and external HTTP locations.
	 * <p>
	 * For the bundled samples, this will look within the ClassPath at the
	 * approptiate locations only.
	 */
	public ResourceLocator createResolvingResourceLocator(
			final TestPackage testPackage) {
		final ResourceLocator result = new ChainedResourceLocator(
				createPackageFileResourceLocator(testPackage), /*
																 * (to resolve
																 * things in
																 * this package)
																 */
				QtiXmlReader.JQTIPLUS_PARSER_RESOURCE_LOCATOR, /*
																 * (to resolve
																 * internal HTTP
																 * resources,
																 * e.g. RP
																 * templates)
																 */
				new NetworkHttpResourceLocator()); /*
													 * (to resolve external HTTP
													 * resources, e.g. RP
													 * templates, external
													 * items)
													 */
		return result;
	}

	// -------------------------------------------------

	/**
	 * Generates a URI for the {@link AssessmentObject} within the given
	 * {@link TestPackage} that can be passed to a {@link ResourceLocator}
	 * created by corresponding methods in this service.
	 * <p>
	 * For an {@link TestPackage} uploaded by a user, this will be a "package"
	 * URI that can access the package's sandbox directory.
	 * <p>
	 * For the bundled samples, this will be a ClassPath URI
	 *
	 * @param assessmentPackage
	 */
	public URI createAssessmentObjectUri(final TestPackage assessmentPackage) {
		return createAssessmentFileUri(assessmentPackage,
				assessmentPackage.getTestHref());
	}

	/**
	 * Generates a URI for the given file resource within the given
	 * {@link TestPackage} that can be passed to a {@link ResourceLocator}
	 * created by corresponding methods in this service.
	 * <p>
	 * For an {@link TestPackage} uploaded by a user, this will be a "package"
	 * URI that can access the package's sandbox directory.
	 * <p>
	 * For the bundled samples, this will be a ClassPath URI
	 *
	 * (NOTE: This does not check the existence of the resulting resource)
	 *
	 * @param assessmentPackage
	 */
	public URI createAssessmentFileUri(final TestPackage assessmentPackage,
			final String fileHref) {
		final CustomUriScheme packageUriScheme = QtiContentPackageExtractor.PACKAGE_URI_SCHEME;
		URI	result = packageUriScheme.decodedPathToUri(fileHref);
		return result;
	}

	// -------------------------------------------------

	/**
	 * Invokes the JQTI+ load & resolution process on the given
	 * {@link TestPackage}.
	 *
	 * @param testPackage
	 *            package to validate, which must not be null.
	 */
	@SuppressWarnings("unchecked")
	public <E extends ResolvedAssessmentObject<?>> E loadAndResolveAssessmentObject(
			final TestPackage testPackage) {
		final ResourceLocator inputResourceLocator = createResolvingResourceLocator(testPackage);
		final URI assessmentObjectSystemId = createAssessmentObjectUri(testPackage);
		final AssessmentObjectXmlLoader assessmentObjectXmlLoader = new AssessmentObjectXmlLoader(
				qtiXmlReader, inputResourceLocator);
		E result= (E) assessmentObjectXmlLoader
					.loadAndResolveAssessmentTest(assessmentObjectSystemId);
		return result;
	}

	/**
	 * Invokes the JQTI+ validator on the given {@link TestPackage}.
	 *
	 * @param assessmentPackage
	 *            package to validate, which must not be null.
	 */
	@SuppressWarnings("unchecked")
	public <E extends AssessmentObjectValidationResult<?>> E loadAndValidateAssessment(
			final TestPackage testPackage) {
		final ResourceLocator inputResourceLocator = createResolvingResourceLocator(testPackage);
		final URI assessmentObjectSystemId = createAssessmentObjectUri(testPackage);
		final AssessmentObjectXmlLoader assessmentObjectXmlLoader = new AssessmentObjectXmlLoader(
				qtiXmlReader, inputResourceLocator);
		E result;
			result = (E) assessmentObjectXmlLoader
					.loadResolveAndValidateTest(assessmentObjectSystemId);

		/* Record summary result back into TestPackage */
		return result;
	}

	// -------------------------------------------------
    public JqtiExtensionManager jqtiExtensionManager() {
        final List<JqtiExtensionPackage<?>> extensionPackages = new ArrayList<JqtiExtensionPackage<?>>();

        return new JqtiExtensionManager(extensionPackages);
    }

}
