package fr.insee.eno.postprocessing.pdf;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.insee.eno.Constants;
import fr.insee.eno.postprocessing.Postprocessor;

/**
 * PDF postprocessor.
 */
public class PDFTableColumnPostprocessorFake implements Postprocessor {

	private static final Logger logger = LoggerFactory.getLogger(PDFTableColumnPostprocessorFake.class);

	@Override
	public File process(File input, byte[] parameters, String survey) throws Exception {

		File outputForFOFile = new File(input.getPath().replace(Constants.MAILING_FO_EXTENSION,
				Constants.TABLE_COL_SIZE_PDF_EXTENSION));

		FileUtils.copyFile(input, outputForFOFile);

		logger.debug("End of TableColumn post-processing (Fake)");
		return outputForFOFile;

	}

}
