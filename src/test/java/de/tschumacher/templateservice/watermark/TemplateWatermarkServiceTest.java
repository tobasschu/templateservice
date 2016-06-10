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

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.tschumacher.templateservice.domain.WatermarkImage;
import de.tschumacher.templateservice.domain.WatermarkItem;


public class TemplateWatermarkServiceTest {

  private TemplateWatermarkService service = null;

  @Before
  public void setUp() {
    this.service = new DefaultTemplateWatermarkService();
  }

  @After
  public void afterTest() {}

  @Test
  public void createTest() {

    final String outputFileName = "watermarkTest.pdf";
    final File outputFile = new File(outputFileName);
    final File inputFile = new File("src/test/resources/watermark.pdf");
    final File imageFile = new File("src/test/resources/watermark.png");;


    final WatermarkImage watermarkImage = new WatermarkImage(imageFile, -100, 0);
    final WatermarkItem watermark =
        WatermarkItem.newBuilder().withInputFile(inputFile).withOutputFile(outputFile)
            .withWatermarkImage(watermarkImage).build();
    this.service.watermark(watermark);

    final File f = new File(outputFileName);
    assertTrue(f.exists());
    assertTrue(!f.isDirectory());
    assertTrue(f.length() > 0);

    f.delete();

  }
}
