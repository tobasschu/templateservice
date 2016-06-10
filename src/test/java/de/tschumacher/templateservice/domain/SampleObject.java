package de.tschumacher.templateservice.domain;

public class SampleObject {
  private final String name;
  private final boolean show;
  private final String showText;

  public SampleObject(String name, boolean show, String showText) {
    super();
    this.name = name;
    this.show = show;
    this.showText = showText;
  }

  public String getName() {
    return this.name;
  }

  public boolean isShow() {
    return this.show;
  }

  public String getShowText() {
    return this.showText;
  }


}
