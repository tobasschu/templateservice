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

public class WatermarkImage {
  private final File imageFile;
  private final float absoluteX;
  private final float absoluteY;


  public WatermarkImage(File imageFile, float absoluteX, float absoluteY) {
    super();
    this.imageFile = imageFile;
    this.absoluteX = absoluteX;
    this.absoluteY = absoluteY;
  }

  public File getImageFile() {
    return this.imageFile;
  }

  public float getAbsoluteX() {
    return this.absoluteX;
  }

  public float getAbsoluteY() {
    return this.absoluteY;
  }


}
