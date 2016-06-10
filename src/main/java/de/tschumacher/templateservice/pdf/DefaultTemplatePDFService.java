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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import de.tschumacher.templateservice.domain.TemplateItem;
import de.tschumacher.templateservice.exception.TemplateServiceException;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.converter.XDocConverterException;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;



public class DefaultTemplatePDFService implements TemplatePDFService {



  @Override
  public void create(TemplateItem item) {
    try {
      final IXDocReport report = loadReport(item.getInputFile());
      final IContext context = loadContext(report, item.getContext());
      final Options options = Options.getTo(ConverterTypeTo.PDF);
      createItem(item, report, context, options);
    } catch (IOException | XDocReportException e) {
      throw new TemplateServiceException(e);
    }
  }



  private void createItem(TemplateItem item, final IXDocReport report, final IContext context,
      final Options options) throws FileNotFoundException, XDocReportException,
      XDocConverterException, IOException {
    final OutputStream out = new FileOutputStream(item.getOutputFile());
    report.convert(context, options, out);
    out.close();
  }

  private IContext loadContext(final IXDocReport report, Map<String, Object> itemContext)
      throws XDocReportException {
    final IContext context = report.createContext();
    for (final String key : itemContext.keySet()) {
      context.put(key, itemContext.get(key));
    }
    return context;
  }

  private IXDocReport loadReport(File file) throws FileNotFoundException, IOException,
  XDocReportException {
    final InputStream in = new FileInputStream(file);
    final IXDocReport report =
        XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
    return report;
  }

}
