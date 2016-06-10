/*
 * Copyright 2015 Tobias Schumacher
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package de.tschumacher.templateservice.watermark;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

import de.tschumacher.templateservice.domain.WatermarkItem;
import de.tschumacher.templateservice.exception.TemplateServiceException;

public class DefaultTemplateWatermarkService implements TemplateWatermarkService {
  @Override
  public void watermark(WatermarkItem watermark) {
    try {
      final PdfReader reader = createReader(watermark);
      final PdfStamper pdfStamper = createStamper(watermark, reader);
      final Image image = createWatermarkImage(watermark);
      addWatermarks(watermark, reader, pdfStamper, image);
      pdfStamper.close();
    } catch (IOException | DocumentException e) {
      throw new TemplateServiceException(e);
    }
  }

  private void addWatermarks(WatermarkItem watermark, final PdfReader reader,
      final PdfStamper pdfStamper, final Image image) throws DocumentException {
    for (int i = 1; i <= reader.getNumberOfPages(); i++) {
      addWatermark(watermark, pdfStamper, image, i);
    }
  }

  private void addWatermark(WatermarkItem watermark, final PdfStamper pdfStamper,
      final Image image, int i) throws DocumentException {
    final PdfContentByte content = pdfStamper.getUnderContent(i);
    image.setAbsolutePosition(watermark.getWatermarkImage().getAbsoluteX(), watermark
        .getWatermarkImage().getAbsoluteY());
    content.addImage(image);
  }

  private Image createWatermarkImage(WatermarkItem watermark) throws BadElementException,
      MalformedURLException, IOException {
    final Image image =
        Image.getInstance(watermark.getWatermarkImage().getImageFile().getAbsolutePath());
    return image;
  }

  private PdfStamper createStamper(WatermarkItem watermark, final PdfReader reader)
      throws DocumentException, IOException, FileNotFoundException {
    final PdfStamper pdfStamper =
        new PdfStamper(reader, new FileOutputStream(watermark.getOutputFile()));
    return pdfStamper;
  }

  private PdfReader createReader(WatermarkItem watermark) throws IOException {
    final PdfReader reader = new PdfReader(watermark.getInputFile().getAbsolutePath());
    return reader;
  }
}
