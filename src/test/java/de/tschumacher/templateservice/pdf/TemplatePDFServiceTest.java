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
package de.tschumacher.templateservice.pdf;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.tschumacher.templateservice.domain.SampleObject;
import de.tschumacher.templateservice.domain.TemplateItem;


public class TemplatePDFServiceTest {

  private TemplatePDFService service = null;

  @Before
  public void setUp() {
    this.service = new DefaultTemplatePDFService();
  }

  @After
  public void afterTest() {}

  @Test
  public void createTest() {

    final Map<String, Object> context = new HashMap<String, Object>();
    context.put("project", new SampleObject("TestName", true, "showTexasd"));
    context.put("name", "Test");
    final String outputFileName = "hello.pdf";
    final File outputFile = new File(outputFileName);
    final File inputFile = new File("src/test/resources/hello.docx");

    final TemplateItem templateItem =
        TemplateItem.newBuilder().withInputFile(inputFile).withOutputFile(outputFile)
            .withContext(context).build();

    this.service.create(templateItem);

    final File f = new File(outputFileName);
    assertTrue(f.exists());
    assertTrue(!f.isDirectory());
    assertTrue(f.length() > 0);

    // TODO wieder rein
    // f.delete();

  }
}
