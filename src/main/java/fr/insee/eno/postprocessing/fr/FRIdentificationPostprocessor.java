package fr.insee.eno.postprocessing.fr;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.insee.eno.Constants;
import fr.insee.eno.postprocessing.Postprocessor;
import fr.insee.eno.transform.xsl.XslTransformation;

public class FRIdentificationPostprocessor implements Postprocessor {

	private static final Logger logger = LoggerFactory.getLogger(FRIdentificationPostprocessor.class);

	// FIXME Inject !
	private static XslTransformation saxonService = new XslTransformation();

	@Override
	public File process(File input, byte[] parameters, String survey) throws Exception {

		File outputForFOFile = new File(
				input.getPath().replace(Constants.EDIT_PATRON_FR_EXTENSION, Constants.IDENTIFICATION_FR_EXTENSION));
		System.out.println(input.getPath());
		String surveyName = survey;
		String formName = getFormName(input);
		
		InputStream FO_XSL = Constants.getInputStreamFromPath(Constants.IDENTIFICATION_FR_EXTENSION);

		InputStream inputStream = FileUtils.openInputStream(input);
		OutputStream outputStream = FileUtils.openOutputStream(outputForFOFile);

		//FIXME changer identification pour la rendre paramétrable en fonction de studyUnit 
		saxonService.transformSimple(inputStream, outputStream, FO_XSL);
		
		inputStream.close();
		outputStream.close();
		FO_XSL.close();
		logger.info("End of identification post-processing " + input.getAbsolutePath());

		return outputForFOFile;
	}

	private String getFormName(File input) {
		return FilenameUtils.getBaseName(input.getParentFile().getParent());
	}

}
