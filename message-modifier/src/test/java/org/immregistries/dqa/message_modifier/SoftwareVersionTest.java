package org.immregistries.dqa.message_modifier;

import org.immregistries.dqa.message_modifier.SoftwareVersion;

import junit.framework.TestCase;

public class SoftwareVersionTest extends TestCase {
  public void test()
  {
    assertEquals(SoftwareVersion.VERSION, "0.0.1");
  }
}
