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
package de.tschumacher.templateservice.domain;

import java.io.File;

public class WatermarkItem {
  private final File inputFile;
  private final File outputFile;
  private final WatermarkImage watermarkImage;

  public File getInputFile() {
    return this.inputFile;
  }

  public File getOutputFile() {
    return this.outputFile;
  }



  public WatermarkImage getWatermarkImage() {
    return this.watermarkImage;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  private WatermarkItem(Builder builder) {
    this.inputFile = builder.inputFile;
    this.outputFile = builder.outputFile;
    this.watermarkImage = builder.watermarkImage;
  }

  public static class Builder {

    private File inputFile;
    private File outputFile;
    private WatermarkImage watermarkImage;

    public Builder withInputFile(File inputFile) {
      this.inputFile = inputFile;
      return this;
    }

    public Builder withOutputFile(File outputFile) {
      this.outputFile = outputFile;
      return this;
    }

    public Builder withWatermarkImage(WatermarkImage watermarkImage) {
      this.watermarkImage = watermarkImage;
      return this;
    }

    public WatermarkItem build() {
      return new WatermarkItem(this);
    }
  }


}
